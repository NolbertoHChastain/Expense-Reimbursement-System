package app.ers.model.DTO;

/**
 * This Reimbursement Data Transfer Object will be used for modeling
 * Outgoing JSON data for a new Reimbursement. DTO is used to convert
 * to corresponding Reimbursement model in the ReimbursementService
 */
public class OutgoingReimbursementDTO {

    private int reimbId;
    private String description;
    private int amount;
    private String status;

    // boilerplate - no args, all args, getter/setter, toString


    public OutgoingReimbursementDTO() {
    }

    public OutgoingReimbursementDTO(int reimbId, String description, int amount, String status) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
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

    @Override
    public String toString() {
        return "OutgoingReimbursementDTO{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}