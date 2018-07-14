import UIKit
import Contacts


/// Provide a list of contacts
public class ContactsDataSource: GenericDataSource, UITableViewDelegate, UITableViewDataSource {

    
    public static let GENERIC_LIST_ITEM_XIB = "GenericListItem"
    public static let GENERIC_LIST_ITEM_ID = "genericListItemId"
    private static let CONTACT_KEYS: [CNKeyDescriptor] = [
        CNContactFormatter.descriptorForRequiredKeys(for: .fullName),
        CNContactImageDataAvailableKey,
        CNContactThumbnailImageDataKey] as! [CNKeyDescriptor]
    
    private var data: [CNContact] = [];
    

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
        // context.performSegue(withIdentifier: CustomerDetailsController.SEGUE_IDENTIFIER, sender: self)
    }
    
    public func fetchContacts(_ name: String)
    {
        //Loading contacts
        let contactStore = CNContactStore()
        
        self.setIsLoading(true)
        
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
            let predicate = name.isEmpty ? CNContact.predicateForContactsInContainer(withIdentifier: container.identifier) : CNContact.predicateForContacts(matchingName: name)
            
            do {
                let containerResults = try contactStore.unifiedContacts(matching: predicate, keysToFetch: ContactsDataSource.CONTACT_KEYS)
                data.removeAll()
                data.append(contentsOf: containerResults)
                self.setIsLoading(false)
            } catch {
                print("Error fetching results for container")
            }
        }
    }
}
