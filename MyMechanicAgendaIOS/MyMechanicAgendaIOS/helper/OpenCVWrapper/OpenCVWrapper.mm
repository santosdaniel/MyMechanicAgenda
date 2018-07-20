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

-(UIImage*) convertToGrey:(UIImage*)original {
    cv::Mat originalMat = [self cvMatFromUIImage:original];
    
    // Convert the image from RGB to GrayScale
    cv::Mat gray;
    cv::cvtColor(originalMat, gray, CV_RGBA2GRAY);
    
    // Apply the gaussian blur to the above image
    cv::Mat gaussianBlur;
    cv::GaussianBlur(gray, gaussianBlur, cv::Size(5,5), 0);
    // Apply the Canny edge detection
    cv::Mat edges;
    cv::Canny(gaussianBlur, edges, 100, 200);
    
    //Finding countorns
    vector<vector<cv::Point>> contours;
    vector<Vec4i> hierarchy;
    cv::findContours(edges,  contours, hierarchy, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
    
    vector<vector<cv::Point>> filtered;
    for( size_t i = 0; i< contours.size(); i++ )
    {
        vector<cv::Point> contourn = contours[i];
        vector<cv::Point> detected;
        //approximate the contour
        double peri = cv::arcLength(contourn, true);
        cv::approxPolyDP(contourn, detected, 0.02 * peri, true);
        if (detected.size() == 4) {
            filtered.push_back(contourn);
        }
    }
    
    //     Mat drawing = Mat::zeros( gaussianBlur.size(), CV_8UC3 );
    for( size_t i = 0; i< filtered.size(); i++ )
    {
        Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) );
        cv::drawContours( originalMat, filtered, (int)i, color, 2, 8, hierarchy, 0, cv::Point() );
    }
    
    // convert modified matrix back to UIImage
    return [self UIImageFromCVMat:originalMat];
}


@end
