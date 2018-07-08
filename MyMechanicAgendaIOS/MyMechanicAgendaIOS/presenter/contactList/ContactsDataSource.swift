import UIKit
import Contacts

/// Provide a list of contacts
public class ContactsDataSource {
    
    private var data: [CNContact] = [];
    
    private func configureTableView() {
        
    }
    
    init() {
        let contactStore = CNContactStore()
        let keysToFetch = [
            CNContactFormatter.descriptorForRequiredKeys(for: .fullName),
            CNContactEmailAddressesKey,
            CNContactPhoneNumbersKey,
            CNContactImageDataAvailableKey,
            CNContactThumbnailImageDataKey] as! [CNKeyDescriptor]
        
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
                let containerResults = try contactStore.unifiedContacts(matching: fetchPredicate, keysToFetch: keysToFetch)
                data.append(contentsOf: containerResults)
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
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        
        //Load data
        let contact = data[indexPath.row]
        
        //Set ui
        cell.textLabel?.text = contact.familyName
        cell.detailTextLabel?.text = contact.nickname
        
        return cell
    }
    
    
}
