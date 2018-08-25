import UIKit

class ContactListController: UIViewController, UISearchBarDelegate {

    @IBOutlet weak var tblContacts: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
    @IBOutlet weak  var searchBar: UISearchBar!

    private var lstResults: ContactsDataSource!

    private func configureTableView() {
        lstResults = ContactsDataSource(self, tblContacts, loading)
        self.lstResults.fetchContacts(StringHelper.emptyString)
        let uiNib = UINib(nibName: GenericDataSource.genericListItemXib, bundle: nil)
        tblContacts.register(uiNib, forCellReuseIdentifier: GenericDataSource.genericListItemId)
    }

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        self.lstResults.fetchContacts(searchText)
        tblContacts.reloadData()
    }

    @IBAction
    func dismissKeyboard(sender: AnyObject) {
        self.searchBar.resignFirstResponder()
    }

    private func initViews() {
        self.configureTableView()
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == CustomerDetailsController.segueIdentifier {
            if let lSearchBar = searchBar as UISearchBar? {
                lSearchBar.resignFirstResponder()
            }

            if let indexPath = tblContacts.indexPathForSelectedRow {
                if segue.destination  is CustomerDetailsController {
                    let controller = (segue.destination as? CustomerDetailsController)
                    if controller != nil {
                        self.lstResults.itemSelected(itemIndex: indexPath, controller: controller!)
                    }

                }
            }
        }
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
