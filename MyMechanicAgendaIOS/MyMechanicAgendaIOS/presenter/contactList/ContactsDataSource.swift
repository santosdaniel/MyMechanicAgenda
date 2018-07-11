import UIKit
import Contacts

/// Provide a list of contacts
public class ContactsDataSource: NSObject, UITableViewDelegate, UITableViewDataSource {
    
    private var tblContacts: UITableView!
    private var loading: UIActivityIndicatorView!
    private var context: UIViewController!
    
    public static let GENERIC_LIST_ITEM_XIB = "GenericListItem"
    public static let GENERIC_LIST_ITEM_ID = "genericListItemId"
    private static let CONTACT_KEYS: [CNKeyDescriptor] = [
        CNContactFormatter.descriptorForRequiredKeys(for: .fullName),
        CNContactImageDataAvailableKey,
        CNContactThumbnailImageDataKey] as! [CNKeyDescriptor]
    
    private var data: [CNContact] = [];
    
    init(_ tblContacts: UITableView!, _ loading: UIActivityIndicatorView, _ context: UIViewController) {
        super.init()
        self.tblContacts = tblContacts;
        self.tblContacts.delegate = self
        self.tblContacts.dataSource = self
        self.loading = loading
        self.loading.startAnimating();
        self.context = context;
        
        
        //Loading contacts
        let contactStore = CNContactStore()
        
        // Get all the containers
        var allContainers: [CNContainer]
        do {
            allContainers = try contactStore.containers(matching: nil)
        } catch {
            print("Error fetching containers")
            allContainers = []
        }
        
        
        // Iterate all containers and append their contacts to our results array
        for container in allContainers {
            let fetchPredicate = CNContact.predicateForContactsInContainer(withIdentifier: container.identifier)
            
            do {
                let containerResults = try contactStore.unifiedContacts(matching: fetchPredicate, keysToFetch: ContactsDataSource.CONTACT_KEYS)
                data.append(contentsOf: containerResults)
                self.loading.isHidden = true
            } catch {
                print("Error fetching results for container")
            }
        }
    }
    
    public func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.data.count
    }
    
    
    private func setUICell(_ contact: CNContact, _ cell: GenericListItem) {
        cell.title?.text = CNContactFormatter.string(from: contact, style: .fullName)
        
        
        cell.desc?.text = "fdd"
        
        // Set the contact image.
        UIHelper.roundImage(cell.thumbnail)
        if let thumbnailData = contact.thumbnailImageData {
            cell.thumbnail.image = UIImage(data: thumbnailData)
        } else {
            cell.thumbnail.image = nil
        }
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = tableView.dequeueReusableCell(withIdentifier: ContactsDataSource.GENERIC_LIST_ITEM_ID, for: indexPath) as! GenericListItem
        
        //Load data
        let contact = data[indexPath.row]
        
        setUICell(contact, cell)

        
        return cell
    }
    
    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        context.performSegue(withIdentifier: "showCustomerDetails", sender: self)
    }
}
