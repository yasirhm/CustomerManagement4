package DataAccess;

//import javax.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by $Yasi on 12/4/2016.
 */

@Entity
@Table(name = "loantype", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class LoanType implements Serializable  {
    @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column( name="LOAN_TYPE_ID", unique = true )
    protected Integer LOAN_TYPE_ID;

    @Column(name = "NAME", length = 50)
    public String name;
   // @Column(name = "INTEREST_RATE")
    public Double interestRate;

    @OneToMany(fetch = FetchType.LAZY) //, mappedBy = "LOAN_TYPE"
    @JoinColumn(name="LOAN_TYPE_ID", referencedColumnName="LOAN_TYPE_ID")
    //public List<GrantCondition> grantConditions = new ArrayList<GrantCondition>();
    public Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();

    public LoanType(String loanTypeName, Double interestRate) {
        name = loanTypeName;
        this.interestRate = interestRate;
    }

    public Integer getLOAN_TYPE_ID() {
        return LOAN_TYPE_ID;
    }

    public void setLOAN_TYPE_ID(Integer LOAN_TYPE_ID) {
        this.LOAN_TYPE_ID = LOAN_TYPE_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Set<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
    }

    public void setGrantConditions(GrantCondition grantConditions) {
        this.grantConditions.add(grantConditions);
    }
    public LoanType() {}
}
