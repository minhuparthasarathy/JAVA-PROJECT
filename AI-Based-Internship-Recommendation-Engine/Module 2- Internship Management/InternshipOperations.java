// Concept Used: Interface
// InternshipOperations defines the contract for internship CRUD operations
public interface InternshipOperations {

    // Concept Used: Abstract Methods
    // These methods have no body.
    // The implementing class must provide the implementation.

    // Adds a new internship to the collection
    void addInternship(Internship internship);

    // Updates an existing internship identified by its ID
    void updateInternship(int id, Internship updatedInternship);

    // Deletes an internship identified by its ID from the collection
    void deleteInternship(int id);

    // Displays all available internships
    void viewInternships();

}

