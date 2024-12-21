package app.ers.model.DTO;

/**
 * This Reimbursement Data Transfer Object will be used for modeling
 * incoming JSON data for a new Reimbursement. DTO is used to convert
 * to corresponding Reimbursement model in the ReimbursementService
 */
public class IncomingReimbursementDTO {

    private String description;
    private int amount;
    private String status;
    private int userId;

    // boilerplate - no args, all args, getter/setter, toString

    public IncomingReimbursementDTO() {
    }

    public IncomingReimbursementDTO(String description, int amount, String status, int userId) {
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "IncomingReimbursementDTO{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
