import UIKit

class CustomerDetailsController: UIViewController, UISearchBarDelegate {
    
    @IBOutlet weak var tblContacts: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
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
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}
