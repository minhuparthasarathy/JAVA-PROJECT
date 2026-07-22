// Concept Used: Interface
// Interface defines the operations that UserManager must implement
public interface UserOperations {

    // Concept Used: Abstract Methods
    // These methods have no body.
    // The implementing class must provide the implementation.

    void registerStudent();

    void studentLogin();

    void adminLogin();

    void viewProfile();

    void updateProfile();

}
