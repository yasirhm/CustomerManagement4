package DataAccess;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by $Yasi on 12/4/2016.
 */
public class LoanType implements Serializable  {
    public Integer id;
    public String name;
    public Double interestRate;
    public Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();

    public LoanType(String loanTypeName, Double interestRate) {
        name = loanTypeName;
        this.interestRate = interestRate;
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

    public LoanType() {}
}
