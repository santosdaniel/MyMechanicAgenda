import UIKit

class CustomerDetailsController: UIViewController, UISearchBarDelegate {
    
    /// Identifier of the segue used to launch this controller
    public static let SEGUE_IDENTIFIER: String = "showCustomerDetails"
    private let IMAGE_NAME: String = "documents";
    
    @IBOutlet weak var tblContacts: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
    @IBOutlet weak var contactImage: UIImageView!
    @IBOutlet weak var processImage: UIButton!
    @IBOutlet weak var resultImage: UIImageView!

    private var lstResults: ContactsDataSource!
    
    
    
    private func configureTableView() {
        /*
         lstResults = ContactsDataSource(tblContacts, loading)
         let uiNib = UINib(nibName: ContactsDataSource.GENERIC_LIST_ITEM_XIB, bundle: nil);
         tblContacts.register(uiNib, forCellReuseIdentifier: ContactsDataSource.GENERIC_LIST_ITEM_ID)
         */
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        print("\(searchText)")
    }
    
    private func initViews() {
        self.configureTableView()
    }
    
    override func viewDidLoad() {
        self.initViews()
       
        let originalImage = UIImage(named: IMAGE_NAME)
        
        self.contactImage.image =  originalImage
        super.viewDidLoad()
    }
    
    @IBAction
    func processImageAction(sender: UIButton){
        let openCV = OpenCVWrapper()
        let originalImage = UIImage(named: IMAGE_NAME)
        self.resultImage.image = openCV.convert(toGrey: originalImage)
    }

    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}
