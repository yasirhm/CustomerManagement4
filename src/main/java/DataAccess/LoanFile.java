package DataAccess;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * Created by $Yasi on 12/12/2016.
 */
public class LoanFile implements Serializable {

    protected Integer loanFileID ;
    private String firstName;
    private String lastName;
    public Integer customerNumber;
    public Integer loanTypeID;
    public Integer period;
    public Integer amount;

    public LoanFile(String firstName, String lastName, Integer customerNumber, Integer loanTypeID, Integer period, Integer amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
        this.loanTypeID = loanTypeID;
        this.period = period;
        this.amount = amount;
    }

    public LoanFile() {}

    public Integer getLoanFileID() {
        return loanFileID;
    }

    public void setLoanFileID(Integer loanFileID) {
        this.loanFileID = loanFileID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getLoanTypeID() {
        return loanTypeID;
    }

    public void setLoanTypeID(Integer loanTypeID) {
        this.loanTypeID = loanTypeID;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
