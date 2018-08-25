import UIKit

class CustomerDetailsController: UIViewController {

    private let imageName: String = "documents"

    @IBOutlet weak var contactImage: UIImageView!
    @IBOutlet weak var processImage: UIButton!
    @IBOutlet weak var resultImage: UIImageView!

    override func viewDidLoad() {
        super.viewDidLoad()

        let originalImage = UIImage(named: imageName)
        self.contactImage.image =  originalImage

    }

    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    //Dispose of any resources that can be recreated.
    }

    @IBAction
    func processImageAction(sender: UIButton) {
        let openCV = OpenCVWrapper()
        let originalImage = UIImage(named: imageName)
        self.resultImage.image = openCV.convert(toGrey: originalImage)
    }

}
