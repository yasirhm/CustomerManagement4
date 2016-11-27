package BusinessLogic;

import DataAccess.RealCustomerCRUD;
import DataAccess.RealCustomer;

/**
 * Created by dotinschool6 on 11/26/2016.
 */
public class BusinessLogic {
    //*****REAL DataAccessCustomer*******//
    public static RealCustomer addRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
       // if(RealCustomerCRUD.checkRedundantData(realCustomer.getNationalId())){
            return RealCustomerCRUD.addRealCustomerDataAccess(realCustomer);
        //}
       // else throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد.");
    }
}
