import UIKit


// MARK: - Data source with generic stuff that should be common to all the data sources
public class GenericDataSource: NSObject {
    
    public static let NO_ELEMENTS: Int = 0
    private static let IMPLEMENTATION_NEED_ID: String = "Subclasses need to implement the `\(#function)` method."
    
    internal var controller: UIViewController
    internal var tableView: UITableView
    private var progressBar: UIActivityIndicatorView
    
    
    /// Initializar of the GenericDataSource
    ///
    /// - Parameters:
    ///   - controller: Reference to the controller where the table view is going to be show
    ///   - tableView: Reference to the tableView that is going to be show
    ///   - progressBar: Reference to the progressBar that is going to indicate tha the values are being load
    init(_ controller: UIViewController, _ tableView: UITableView, _ progressBar: UIActivityIndicatorView) {
        self.controller = controller
        self.tableView = tableView
        self.progressBar = progressBar
        super.init()
        if let tableViewDelegate = self as? UITableViewDelegate {
            tableView.delegate = tableViewDelegate;
        }
        if let dataSource = self as? UITableViewDataSource {
            tableView.dataSource = dataSource;
        }
        
    }
    
    
    
    /// Should be used by the controller to indicate that is able to receive content
    func startLoading() {
        self.progressBar.startAnimating();
    }
    
    /// Indicates that the ui list view is loading its content
    ///
    /// - Parameter isLoading: Flag that indicates if it is loading the content or not
    public func setIsLoading(_ isLoading: Bool) {
        if let lTableView = tableView as UITableView?, let lProgressBar = progressBar as UIActivityIndicatorView? {
            if (isLoading) {
                lTableView.isHidden = true
                lProgressBar.startAnimating()
                lProgressBar.isHidden = false
            } else {
                lTableView.isHidden = false
                lProgressBar.stopAnimating()
                lProgressBar.isHidden = true
            }
        }
    }
}
