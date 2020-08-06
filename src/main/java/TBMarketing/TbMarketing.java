package TBMarketing;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Tbmarketing_table")
public class TbMarketing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String purchaseId;
    private String purchaseStatus;
    private String prdId;
    private Integer purchaseQty;
    private Integer purchaseAmt;
    private String prdNm;
    private String custNm;
    private String dmStatus;

    @PostPersist
    public void onPostPersist(){

        TbPurchased tbPurchased = new TbPurchased();
        BeanUtils.copyProperties(this, tbPurchased);
        tbPurchased.publishAfterCommit();

    }

    @PreUpdate
    public void onPreUpdate(){
        System.out.println(">>>>> this.getId()=" + this.getId());
        System.out.println(">>>>> this.getPurchaseStatus()=" + this.getPurchaseStatus());

        if("CANCELLED".equals(this.getPurchaseStatus())){
            ReceiverChanged receiverChanged = new ReceiverChanged();
            BeanUtils.copyProperties(this, receiverChanged);
            receiverChanged.setPurchaseStatus("발송연기");
            receiverChanged.publishAfterCommit();
        }
        else {
            ReceiverChanged receiverChanged = new ReceiverChanged();
            BeanUtils.copyProperties(this, receiverChanged);
            receiverChanged.setPurchaseStatus("DM발송");
            receiverChanged.publishAfterCommit();
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }
    public Integer getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(Integer purchaseQty) {
        this.purchaseQty = purchaseQty;
    }
    public Integer getPurchaseAmt() {
        return purchaseAmt;
    }

    public void setPurchaseAmt(Integer purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }
    public String getPrdNm() {
        return prdNm;
    }

    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }
    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }
    public String getDmStatus() {
        return dmStatus;
    }

    public void setDmStatus(String dmStatus) {
        this.dmStatus = dmStatus;
    }




}
