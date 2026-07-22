// Concept Used: Class
// User class stores common user information
public class User {

    // Concept Used: Encapsulation
    // Private data members protect data from direct access
    private String username;
    private String password;

    // Concept Used: Constructor
    // Initializes object values when an object is created
    public User(String username, String password) {

        this.username = username;
        this.password = password;

    }

    // Concept Used: Methods
    // Getter method is used to access private data
    public String getUsername() {

        return username;

    }

    public String getPassword() {

        return password;

    }

}
