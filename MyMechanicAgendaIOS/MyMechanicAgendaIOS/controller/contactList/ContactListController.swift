import UIKit

class ContactListController: UITableViewController {
    
    private var lstResults: ContactListDataSource!
    
    private func initializeComponents() {
        lstResults = ContactListDataSource()
    }
    
    override func viewDidLoad() {
                self.initializeComponents();
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.lstResults.tableView(tableView, numberOfRowsInSection: section)
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return self.lstResults.tableView(tableView, cellForRowAt: indexPath)
    }
}

