import UIKit

class ContactListController: UIViewController {

    @IBOutlet weak var tblContacts: UITableView!
    private var lstResults: ContactsDataSource!
    

    private func configureTableView() {
        lstResults = ContactsDataSource()
        tblContacts.delegate = lstResults
        tblContacts.dataSource = lstResults
        let uiNib = UINib(nibName: ContactsDataSource.GENERIC_LIST_ITEM_XIB, bundle: nil);
        tblContacts.register(uiNib, forCellReuseIdentifier: ContactsDataSource.GENERIC_LIST_ITEM_ID)
    }
    
    override func viewDidLoad() {
                self.configureTableView();
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
