//
//  OpenCVWrapper.m
//  MyMechanicAgendaIOS
//

#import <opencv2/opencv.hpp>
#import "OpenCVWrapper.h"

using namespace cv;
using namespace std;

RNG rng(12345);

@implementation OpenCVWrapper

/**
 <#Description#>

 @param image image description
 @return return value description
 */
- (cv::Mat)cvMatFromUIImage:(UIImage *)image
{
    CGColorSpaceRef colorSpace = CGImageGetColorSpace(image.CGImage);
    CGFloat cols = image.size.width;
    CGFloat rows = image.size.height;
    cv::Mat cvMat(rows, cols, CV_8UC4); // 8 bits per component, 4 channels (color channels + alpha)
    CGContextRef contextRef = CGBitmapContextCreate(cvMat.data,                 // Pointer to  data
                                                    cols,                       // Width of bitmap
                                                    rows,                       // Height of bitmap
                                                    8,                          // Bits per component
                                                    cvMat.step[0],              // Bytes per row
                                                    colorSpace,                 // Colorspace
                                                    kCGImageAlphaNoneSkipLast |
                                                    kCGBitmapByteOrderDefault); // Bitmap info flags
    CGContextDrawImage(contextRef, CGRectMake(0, 0, cols, rows), image.CGImage);
    CGContextRelease(contextRef);
    return cvMat;
}

/**
 <#Description#>

 @param image <#image description#>
 @return <#return value description#>
 */
- (cv::Mat)cvMatGrayFromUIImage:(UIImage *)image
{
    CGColorSpaceRef colorSpace = CGImageGetColorSpace(image.CGImage);
    CGFloat cols = image.size.width;
    CGFloat rows = image.size.height;
    cv::Mat cvMat(rows, cols, CV_8UC1); // 8 bits per component, 1 channels
    CGContextRef contextRef = CGBitmapContextCreate(cvMat.data,                 // Pointer to data
                                                    cols,                       // Width of bitmap
                                                    rows,                       // Height of bitmap
                                                    8,                          // Bits per component
                                                    cvMat.step[0],              // Bytes per row
                                                    colorSpace,                 // Colorspace
                                                    kCGImageAlphaNoneSkipLast |
                                                    kCGBitmapByteOrderDefault); // Bitmap info flags
    CGContextDrawImage(contextRef, CGRectMake(0, 0, cols, rows), image.CGImage);
    CGContextRelease(contextRef);
    return cvMat;
}



-(UIImage *)UIImageFromCVMat:(cv::Mat)cvMat
{
    NSData *data = [NSData dataWithBytes:cvMat.data length:cvMat.elemSize()*cvMat.total()];
    CGColorSpaceRef colorSpace;
    if (cvMat.elemSize() == 1) {
        colorSpace = CGColorSpaceCreateDeviceGray();
    } else {
        colorSpace = CGColorSpaceCreateDeviceRGB();
    }
    CGDataProviderRef provider = CGDataProviderCreateWithCFData((__bridge CFDataRef)data);
    // Creating CGImage from cv::Mat
    CGImageRef imageRef = CGImageCreate(cvMat.cols,                                 //width
                                        cvMat.rows,                                 //height
                                        8,                                          //bits per component
                                        8 * cvMat.elemSize(),                       //bits per pixel
                                        cvMat.step[0],                            //bytesPerRow
                                        colorSpace,                                 //colorspace
                                        kCGImageAlphaNone|kCGBitmapByteOrderDefault,// bitmap info
                                        provider,                                   //CGDataProviderRef
                                        NULL,                                       //decode
                                        false,                                      //should interpolate
                                        kCGRenderingIntentDefault                   //intent
                                        );
    // Getting UIImage from CGImage
    UIImage *finalImage = [UIImage imageWithCGImage:imageRef];
    CGImageRelease(imageRef);
    CGDataProviderRelease(provider);
    CGColorSpaceRelease(colorSpace);
    return finalImage;
}


/**
 Convert the image from RGB to GrayScale

 @param original The original image to process
 @return the openCV mat of the image in grey
 */
-(cv::Mat) _convertToGrey:(cv::Mat)originalMat {
    cv::Mat gray;
    cv::cvtColor(originalMat, gray, CV_RGBA2GRAY);
    return gray;
}

-(UIImage*) convertToGrey:(UIImage*)original {
    cv::Mat originalMat = [self cvMatFromUIImage:original];
    cv::Mat gray = [self _convertToGrey: originalMat];
    
    // Apply the gaussian blur to the above image
    cv::Mat gaussianBlur;
    cv::GaussianBlur(gray, gaussianBlur, cv::Size(5,5), 0);
    
    // Apply the Canny edge detection
    cv::Mat edges;
    //first threshold for the hysteresis procedure.
    int threshold1 = 100;
    //second threshold for the hysteresis procedure.
    int threshold2 = 200;

    cv::Canny(gaussianBlur, edges, threshold1 , threshold2 );
    
    //Finding countorns
    vector<vector<cv::Point>> contours;
    vector<Vec4i> hierarchy;
    cv::findContours(edges,  contours, hierarchy, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
    
    vector<cv::Point> detected;
    vector<vector<cv::Point>> biggerRectangles;
    double biggerArea = -1.0f;
    for( size_t i = 0; i< contours.size(); i++ )
    {
        vector<cv::Point> contourn = contours[i];
        bool closed = true;
        //Contour area
        double area = cv::contourArea(contourn);
        if((area > biggerArea)) {
            //Compute the perimeter
            double perimeter = cv::arcLength(contourn, closed);
            //It approximates a contour shape to another shape with less number of vertices depending upon the precision we specify
            cv::approxPolyDP(contourn, detected, 0.02 * perimeter, closed);
            if (detected.size() == 4) {
                biggerRectangles.clear();
                biggerRectangles.push_back(detected);
                biggerArea = area;
            }
        }
    }
    
    cv::Mat output;
    //     Mat drawing = Mat::zeros( gaussianBlur.size(), CV_8UC3 );
    if(biggerArea > 0) {
        
        // Input Quadilateral or Image plane coordinates
        Point2f inputQuad[4];
        // Output Quadilateral or World plane coordinates
        Point2f outputQuad[4];
        
        // The 4 points that select quadilateral on the input , from top-left in clockwise order
        // These four pts are the sides of the rect box used as input
        for(int i = 0;i < detected.size(); i++) {
            cv::Point point = detected[i];
            inputQuad[i] = Point2f( point.x, point.y );
        }
    
        // The 4 points where the mapping is to be done , from top-left in clockwise order
        int width = originalMat.cols;
        int height = originalMat.rows;
        outputQuad[0] = Point2f( 0,0 );
        outputQuad[1] = Point2f( width,0);
        outputQuad[2] = Point2f( width,height);
        outputQuad[3] = Point2f( 0,height  );
        
        cv::Mat perpective = cv::getPerspectiveTransform(inputQuad, outputQuad);
        
        
        warpPerspective(gray, output, perpective, output.size());
    }
    
    // convert modified matrix back to UIImage
    return [self UIImageFromCVMat:output];
}


@end
