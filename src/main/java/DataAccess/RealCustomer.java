package DataAccess;

import java.io.Serializable;

public class RealCustomer extends Customer implements Serializable {
    private static final long serialVersionUID = 1L; //TODO : chi mige?

    private String firstName;
    private String lastName;
    private String NationalId;
    private String fatherName;
    private String birthDate;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealCustomer(String firstName, String lastName, String fatherName, String nationalId, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.NationalId = nationalId;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationalId(String nationalId) {
        NationalId = nationalId;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalId() {
        return NationalId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public RealCustomer() {}
}

