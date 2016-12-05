package DataAccess;

import java.io.Serializable;

/**
 * Created by $Yasi on 12/4/2016.
 */
public class GrantCondition implements Serializable {
    public Integer id;
    public Integer loanTypeID;
    public String name;
    public Integer minimumContractPeriod;
    public Integer maximumContractPeriod;
    public Double minimumContractAmount;
    public Double maximumContractAmount;

    public GrantCondition(Integer loanTypeID, String name, Integer minimumContractPeriod, Integer maximumContractPeriod, Double minimumContractAmount, Double maximumContractAmount) {
        this.loanTypeID = loanTypeID;
        this.name = name;
        this.minimumContractPeriod = minimumContractPeriod;
        this.maximumContractPeriod = maximumContractPeriod;
        this.minimumContractAmount = minimumContractAmount;
        this.maximumContractAmount = maximumContractAmount;
    }

    public GrantCondition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanTypeID() {
        return loanTypeID;
    }

    public void setLoanTypeID(Integer loanTypeID) {
        this.loanTypeID = loanTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumContractPeriod() {
        return minimumContractPeriod;
    }

    public void setMinimumContractPeriod(Integer minimumContractPeriod) {
        this.minimumContractPeriod = minimumContractPeriod;
    }

    public Integer getMaximumContractPeriod() {
        return maximumContractPeriod;
    }

    public void setMaximumContractPeriod(Integer maximumContractPeriod) {
        this.maximumContractPeriod = maximumContractPeriod;
    }

    public Double getMinimumContractAmount() {
        return minimumContractAmount;
    }

    public void setMinimumContractAmount(Double minimumContractAmount) {
        this.minimumContractAmount = minimumContractAmount;
    }

    public Double getMaximumContractAmount() {
        return maximumContractAmount;
    }

    public void setMaximumContractAmount(Double maximumContractAmount) {
        this.maximumContractAmount = maximumContractAmount;
    }
}
