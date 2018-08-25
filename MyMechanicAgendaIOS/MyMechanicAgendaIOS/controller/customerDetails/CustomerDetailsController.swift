import UIKit
import Contacts

class CustomerDetailsController: UIViewController, UISearchBarDelegate {

    /// Identifier of the segue used to launch this controller
    public static let segueIdentifier: String = "showCustomerDetails"

    @IBOutlet weak var tblContacts: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
    @IBOutlet weak var contactImage: UIImageView!
    @IBOutlet weak var processImage: UIButton!

    private var lstResults: ContactsDataSource!
    public var contact: CNContact!

    private func configureTableView() {
        /*
         lstResults = ContactsDataSource(tblContacts, loading)
         let uiNib = UINib(nibName: GenericDataSource.genericListItemXib, bundle: nil);
         tblContacts.register(uiNib, forCellReuseIdentifier: GenericDataSource.genericListItemId)
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

        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
