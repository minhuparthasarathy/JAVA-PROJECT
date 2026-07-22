// Concept Used: Inheritance
// Admin class inherits properties and methods from User class
public class Admin extends User {

    // Concept Used: Constructor in Subclass
    // super() is used to call the parent class constructor
    public Admin(String username, String password) {

        super(username, password);

    }

    // Concept Used: Method
    // Checks admin login credentials
    public boolean login(String username, String password) {

        return getUsername().equals(username)
                && getPassword().equals(password);

    }

}
