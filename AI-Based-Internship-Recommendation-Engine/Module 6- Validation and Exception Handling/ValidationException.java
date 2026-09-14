/**
 * Module 6 – Validation and Exception Handling
 * Custom exception for validation failures in the GUI.
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
