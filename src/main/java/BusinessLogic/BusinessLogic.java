package BusinessLogic;

import DataAccess.*;

import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

/**
 * Created by $Yasi on 11/26/2016.
 */
public class BusinessLogic {
    //*****REAL DataAccessCustomer*******//
    public static RealCustomer addRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        try {
            checkFulFilledRealCustomerInput(realCustomer);
            checkNationalId(realCustomer.getNationalId());
            return RealCustomerCRUD.addRealCustomerDataAccess(realCustomer);
        } catch (ConflictInDataException exp) {
            throw exp;
        }
    }

    private static boolean checkNationalId(String NationalId) throws ConflictInDataException {
        if (NationalId.length() == 10) {
            Integer total = 0;
            for (int i = NationalId.length(); i > 1; i--) {
                Integer val = parseInt(Character.toString(NationalId.charAt(10 - i)));
                total += val * i;
            }
            if (total % 11 == parseInt(Character.toString(NationalId.charAt(9))))
                return true;
            else if (total % 11 == (11 - parseInt(Character.toString(NationalId.charAt(9)))))
                return true;
            else throw new ConflictInDataException("کد ملی وارد شده معتبر نیست.");
        } else throw new ConflictInDataException("کد ملی باید 10 رقم باشد.");
    }

    private static boolean checkFulFilledRealCustomerInput(RealCustomer realCustomer) throws ConflictInDataException {
        if (realCustomer.getFirstName().equals("") || realCustomer.getLastName().equals("") || realCustomer.getFatherName().equals("") || realCustomer.getBirthDate().equals("") || realCustomer.getNationalId().equals("")) {
            throw new ConflictInDataException("اطلاعات ورودی کافی نیست.");
        } else
            return true;
    }

    public static List<RealCustomer> searchRealCustomerBiz(RealCustomer realCustomer) {
        return RealCustomerCRUD.search(realCustomer);
    }

    public static RealCustomer getRealCustomerBiz(Integer id) {
     /*   RealCustomer realCustomer = new RealCustomer();
        realCustomer.setCustomerNumber(parseInt(id));
        List list = RealCustomerCRUD.search(realCustomer);
        return (RealCustomer) list.get(0);*/
        return RealCustomerCRUD.getRealCustomer(id);
    }

    public static void updateRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        RealCustomerCRUD.updateRealCustomer(realCustomer);
    }

    public static boolean deleteRealCustomerBiz(Integer customerNumber) {
        return Customer.deleteCustomer(customerNumber);
    }

    public static void addNewLoanType(LoanType loan) throws ConflictInDataException {
//        LoanTypeCRUD.addNewRecord(loan);
    }

    public static LoanType addLoanType(LoanType loan) throws ConflictInDataException {
        return LoanTypeCRUD.addNewRecord(loan);
    }

    public static Integer addNewGrant(GrantCondition grant) throws ConflictInDataException {
        //GrantCondition grantCondition= LoanTypeCRUD.addNewRecord(grant);
        Integer id = 0;
        return id;
    }
}
