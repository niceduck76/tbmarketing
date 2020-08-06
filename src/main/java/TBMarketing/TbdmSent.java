package TBMarketing;

public class TbdmSent extends AbstractEvent {

    private Long id;
    private String DMStatus;
    private String purchaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDmStatus() {
        return DMStatus;
    }

    public void setDmStatus(String DMStatus) {
        this.DMStatus = DMStatus;
    }
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}