package BusinessLogic;

import DataAccess.RealCustomerCRUD;
import DataAccess.RealCustomer;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.sum;
import static java.lang.Integer.valueOf;

/**
 * Created by $Yasi on 11/26/2016.
 */
public class BusinessLogic {
    //*****REAL DataAccessCustomer*******//
    public static RealCustomer addRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        try {
            checkfullfilledInput(realCustomer);
            checkNationalId(realCustomer.getNationalId());
            return RealCustomerCRUD.addRealCustomerDataAccess(realCustomer);
        }catch (ConflictInDataException exp){
            throw exp;
        }
    }

    private static boolean checkNationalId(String NationalId) throws ConflictInDataException {
        if (NationalId.length() == 10){
            Integer total=0;
            for (int i = NationalId.length(); i > 1; i--){
                Integer val = parseInt(Character.toString(NationalId.charAt(10-i))) ;
                total += val*i;
            }
            if (total%11 == parseInt(Character.toString(NationalId.charAt(9))))
                return true;
            else if(total%11 == (11-parseInt(Character.toString(NationalId.charAt(9)))))
                return true;
            else throw new ConflictInDataException("کد ملی وارد شده معتبر نیست.");
        }
        else throw new ConflictInDataException("کد ملی باید 10 رقم باشد.");
    }

    private static boolean checkfullfilledInput(RealCustomer realCustomer)throws ConflictInDataException {
        if (realCustomer.getFirstName().equals("") || realCustomer.getLastName().equals("") || realCustomer.getFatherName().equals("") || realCustomer.getBirthDate().equals("") || realCustomer.getNationalId().equals("")) {
            throw new ConflictInDataException("اطلاعات ورودی کافی نیست.");
        }
        else
            return true;

    }

    public static List<RealCustomer> searchRealCustomerBiz(RealCustomer realCustomer){
        return RealCustomerCRUD.search(realCustomer);
    }
}
