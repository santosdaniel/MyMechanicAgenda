import UIKit

class ContactListController: UIViewController, UISearchBarDelegate {
    
    @IBOutlet weak var tblContacts: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
    private var lstResults: ContactsDataSource!
    
    
    
    private func configureTableView() {
        lstResults = ContactsDataSource(self, tblContacts, loading)
        self.lstResults.fetchContacts("")
        let uiNib = UINib(nibName: ContactsDataSource.GENERIC_LIST_ITEM_XIB, bundle: nil);
        tblContacts.register(uiNib, forCellReuseIdentifier: ContactsDataSource.GENERIC_LIST_ITEM_ID)
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        self.lstResults.fetchContacts(searchText)
        tblContacts.reloadData()
    }
    
    private func initViews() {
        self.configureTableView()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == CustomerDetailsController.SEGUE_IDENTIFIER {
            /*
            if let indexPath = tblContacts.indexPathForSelectedRow {
                let object = objects[indexPath.row] as! NSDate
                let controller = (segue.destination as! UINavigationController).topViewController as! CustomerDetailsController
                // controller.detailItem = object
                controller.navigationItem.leftBarButtonItem = splitViewController?.displayModeButtonItem
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
 */
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
