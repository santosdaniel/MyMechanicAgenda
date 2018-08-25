import Foundation
import UIKit

/// Helper to show dialogs in the application
public class DialogMessageHelper {
    
    /// Get the app delegate
    ///
    /// - Returns: The app delegate
    private class func getAppDelegate() -> AppDelegate {
            // swiftlint:disable force_cast
        return UIApplication.shared.delegate as! AppDelegate
    }
    
    /// Get the current list of view controller
    ///
    /// - Returns: List of view controllers
    private class func getListOfViewControllers() -> [UIViewController] {
        return (getAppDelegate().window?.rootViewController as! UINavigationController).viewControllers
    }
    
    /// Get the last known view controller
    ///
    /// - Returns: Returns the last known view controller
    private class func getLastKnownController() -> UIViewController {
        let viewControllersList: [UIViewController]  = getListOfViewControllers()
        return viewControllersList[viewControllersList.count - 1]
    }
    
    /// Shows one certain message in a dialog
    ///
    /// - Parameters:
    ///   - title: The title of the message
    ///   - message: The description of the message
    public class func showMessage(_ title: String, _ message: String) {
        
        let alertController = UIAlertController(
            title: title,
            message: message,
            preferredStyle: UIAlertControllerStyle.alert)
        
        //Adds the ok action to the dialog
        let dismissAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) { (_) -> Void in
        }
        alertController.addAction(dismissAction)
        
        let presentedViewController = getLastKnownController()
        
        presentedViewController.present(alertController, animated: true, completion: nil)
    }
}
