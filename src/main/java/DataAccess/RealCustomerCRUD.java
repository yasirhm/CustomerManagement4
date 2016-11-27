package DataAccess;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ${Yasi} on 11/26/2016.
 */
public class RealCustomerCRUD {
    final static Logger logger = Logger.getLogger(RealCustomerCRUD.class);

    public static boolean checkRedundantData(String nationalId) {
        if (searchById(nationalId, "nationalId") == null) {
            return true;
        } else return false;
    }
    public static RealCustomer addRealCustomerDataAccess(RealCustomer realCustomer) {
        Integer customerNumber = realCustomer.insert();
        if (customerNumber != null) {
            realCustomer.setCustomerNumber(customerNumber);
            insertRealCustomer(realCustomer);
            return realCustomer;
        } else return null;
    }

    private static void insertRealCustomer(RealCustomer realCustomer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.save(realCustomer);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
    }

    private static String createQueryString(RealCustomer realCustomer){
        StringBuilder query = new StringBuilder("");
        query.append(
                "select 'INSERT INTO Organizations(.....) ' +
                'VALUES(' +
                CASE
        WHEN NameOfTheCompany IS NOT NULL
        THEN '''' + NameOfTheCompany + ''', '
        ELSE 'NULL, '
        END +
                CASE
        WHEN IndustryType IS NOT NULL
        THEN '''' + IndustryType + ''', '
        ELSE 'NULL, '
        END +
        ..... and so on ......
        + ')'
       +" );
    }
}


