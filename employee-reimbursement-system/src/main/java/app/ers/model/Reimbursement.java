package app.ers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Component;

@Entity // makes DB table based on Class
@Component // 1/4 stereotypes - makes class a Bean
@Table(name = "Reimbursement") // specifies DB table name
@JsonInclude(Include.NON_EMPTY)
public class Reimbursement {

    @Id // declares field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // makes PK auto-increment
    private int reimbId;

    // @Column - setting DB table column name & constraint !NULL
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String status;

    /*
    JOIN column with FK of User so that Many Reimbursements
    can belong to one User - fetch dependency at runtime
     */
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // boiler plate - no args, all args, getter/setter, toString

    public Reimbursement() {
    }

    public Reimbursement(int reimbId, String description, int amount, String status, User user) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}