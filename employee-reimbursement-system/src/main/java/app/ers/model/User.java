package app.ers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // annotation makes a DB table based on class
@Table(name="User") // annotation lets us specify table properties (like table name)
@Component // 1/4 stereotypes - makes class a Bean
@JsonInclude(Include.NON_EMPTY) // excludes fields that are null || empty
public class User {

    @Id // annotation makes field a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // makes our PK auto-increment as integer
    private int userId;

    // @Column - NOT necessary UNLESS setting DB Table column name or constraints
    // nullable : false - constraint ensures column MUST have value

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "employee"; // default role is employee

    /*
    One User can have Many Reimbursements mapped by user field and ALL
    changes to Reimbursement records will affect dependent User records
    - fetch dependency at runtime
     */
    @JsonIgnore // ignore field when converting User to JSON
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reimbursement> reimbursements;

    // boiler plate - no args, all args, getter/setter, toString

    public User() {
    }

    public User(int userId, String firstname, String lastname,
                String username, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<Reimbursement> getReimbursements() {
        return reimbursements;
    }

    public void setReimbursements(List<Reimbursement> reimbursements) {
        this.reimbursements = reimbursements;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", reimbursements=" + reimbursements +
                '}';
    }
}