package app.ers.model.DTO;

/**
 * This User Data Transfer Object will be used for modeling incoming JSON data for a new User.
 * DTO is used to convert to corresponding User model in the UserService.
 */
public class IncomingUserDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String role = "employee";

    // boilerplate - no args, all args, getter/setter, toString

    public IncomingUserDTO() {
    }

    public IncomingUserDTO(String firstname, String lastname, String username, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "IncomingUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}