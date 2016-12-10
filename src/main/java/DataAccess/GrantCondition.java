package DataAccess;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by $Yasi on 12/4/2016.
 */
@Entity
@Table(name = "grantcondition") //, uniqueConstraints = @UniqueConstraint(columnNames = "NAME")
public class GrantCondition implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column( name = "GRANT_ID", unique = true )
    public Integer GRANT_ID;

    @Column(name = "LOAN_TYPE_ID")
    public Integer LOAN_TYPE_ID;
    @Column(name = "NAME")
    public String name;
    @Column(name = "MIN_PERIOD")
    public Integer minContractPeriod;
    @Column(name = "MAX_PERIOD")
    public Integer maxContractPeriod;
    @Column(name = "MIN_AMOUNT")
    public Double minContractAmount;
    @Column(name = "MAX_AMOUNT")
    public Double maxContractAmount;

    public GrantCondition( String name,Integer maximumContractPeriod, Integer minimumContractPeriod, Double maximumContractAmount, Double minimumContractAmount) {
        this.name = name;
        this.minContractPeriod = minimumContractPeriod;
        this.maxContractPeriod = maximumContractPeriod;
        this.minContractAmount = minimumContractAmount;
        this.maxContractAmount = maximumContractAmount;
    }

    public GrantCondition() {
    }

    public Integer getGRANT_ID() {
        return GRANT_ID;
    }

    public void setGRANT_ID(Integer id) {
        this.GRANT_ID = id;
    }

    public Integer getLOAN_TYPE_ID() {
        return LOAN_TYPE_ID;
    }

    public void setLOAN_TYPE_ID(Integer loanTypeID) {
        this.LOAN_TYPE_ID = loanTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinContractPeriod() {
        return minContractPeriod;
    }

    public void setMinContractPeriod(Integer minContractPeriod) {
        this.minContractPeriod = minContractPeriod;
    }

    public Integer getMaxContractPeriod() {
        return maxContractPeriod;
    }

    public void setMaxContractPeriod(Integer maxContractPeriod) {
        this.maxContractPeriod = maxContractPeriod;
    }

    public Double getMinContractAmount() {
        return minContractAmount;
    }

    public void setMinContractAmount(Double minContractAmount) {
        this.minContractAmount = minContractAmount;
    }

    public Double getMaxContractAmount() {
        return maxContractAmount;
    }

    public void setMaxContractAmount(Double maxContractAmount) {
        this.maxContractAmount = maxContractAmount;
    }
}
