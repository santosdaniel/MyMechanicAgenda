import Foundation

/// Helper to handle strings
public class StringHelper {
    public static let emptyString = ""
    public static let space = " "

    /**
     * Checks if a string is null or empty
     */
    public class func isNullOrEmpty(_ str: String?) -> Bool {
        if let string = str {
            return string.isEmpty
        } else {
            return true
        }
    }

    /**
     * Check that is string is neither null or empty
     */
    public class func isNotNullOrEmpty(_ str: String?) -> Bool {
        return (!isNullOrEmpty(str))
    }
}
