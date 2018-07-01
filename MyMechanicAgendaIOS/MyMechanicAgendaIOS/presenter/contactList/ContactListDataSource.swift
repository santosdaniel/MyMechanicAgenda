import UIKit

/// Provide a list of contacts
public class ContactListDataSource {
    
    private var data: [String] = [];
    
    init() {
        for i in 0...1000 {
            data.append("\(i)")
        }
    }
    
    public func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        
        //Load data
        let text = data[indexPath.row]
        
        //Set ui
        cell.textLabel?.text = text
        cell.detailTextLabel?.text = text
        
        return cell
    }
    
    
}
