package csye6225Web.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "transaction_table")
public class Transaction {

    @Id
    @GeneratedValue
    private String id;
    //@ManyToOne
    //private User user;
    private String description;
    private String merchant;
    private String amount;
    private String date;
    private String category;
    @OneToMany(mappedBy = "transaction",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Receipt> attachments=new ArrayList<>();


    public Transaction() {
    }


    public Transaction(String id, String description, String merchant,
                       String amount, String date, String category, ArrayList<Receipt> attachments) {
        this.id = id;
        //this.user = new User();
        this.merchant = merchant;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.attachments=attachments;

    }

    public void addReceipt(Receipt receipt)
    {
        this.attachments.add(receipt);
        receipt.setTransaction(this);
    }

    public void removeReceipt(Receipt receipt)
    {
        this.attachments.remove(receipt);
        receipt.setTransaction(null);

    }
    public List<Receipt> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Receipt> attachments) {
        this.attachments = attachments;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public String getMerchant() {
        return merchant;
    }



    public String getUserid() {
        return "";
    }

    public void setUserid(String userid) {
        //this.userid = userid;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

}


