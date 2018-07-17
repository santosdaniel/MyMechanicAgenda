import Contacts

// Helper used to require permissions to the user in run time
public class PermissionsRequestHelper {
    
    public class func requestPermission(completionHandler: @escaping (_ accessGranted: Bool) -> Void){
        let authorizationStatus = CNContactStore.authorizationStatus(for: CNEntityType.contacts)
        
        switch authorizationStatus {
        case .authorized:
            completionHandler(true)
            
        case .denied, .notDetermined:
            let contactStore = CNContactStore()
            contactStore.requestAccess(for: CNEntityType.contacts, completionHandler: { (access, accessError) -> Void in
                if access {
                    completionHandler(access)
                }
                else {
                    if authorizationStatus == CNAuthorizationStatus.denied {
                        DispatchQueue.global(qos: .background).async  //concurrent queue, shared by system
                            {
                                //do long running work in the background here
                                //...
                                
                                DispatchQueue.main.async //serial queue
                                    {
                                        let title = "Does not has premissions"
                                        let description = "\(accessError!.localizedDescription)\n\nPlease allow the app to access your contacts through the Settings."
                                        DialogMessageHelper.showMessage(title, description)
                                }
                        }
                        
                    }
                }
            })
        default:
            completionHandler(false)
        }
    }
}
