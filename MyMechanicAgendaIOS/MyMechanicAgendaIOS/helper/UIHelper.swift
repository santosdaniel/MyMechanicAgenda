

import UIKit

public class UIHelper {
    /// Make image borders rounded
    class func roundImage(_ imageVIEW: UIImageView) {
        imageVIEW.layer.cornerRadius = 10
        imageVIEW.clipsToBounds = true
        imageVIEW.layer.borderWidth = 3
        imageVIEW.layer.borderColor = UIColor.white.cgColor
    }
}



