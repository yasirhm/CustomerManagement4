package DataAccess;

import BusinessLogic.ConflictInDataException;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Iterator;
import java.util.List;

import static DataAccess.Customer.deleteCustomer;
import static java.awt.Event.END;
import static jdk.nashorn.internal.parser.TokenType.CASE;
import static jdk.nashorn.internal.parser.TokenType.ELSE;
import static org.hibernate.hql.internal.antlr.HqlTokenTypes.THEN;
import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHEN;

/**
 * Created by ${Yasi} on 11/26/2016.
 */
public class RealCustomerCRUD {
    final static Logger logger = Logger.getLogger(RealCustomerCRUD.class);

    public static RealCustomer addRealCustomerDataAccess(RealCustomer realCustomer) throws ConflictInDataException{
        logger.setLevel(Level.INFO);
        Integer customerNumber = realCustomer.insert();
        if (customerNumber != null) {
            realCustomer.setCustomerNumber(customerNumber);
            try{
                insertRealCustomer(realCustomer);
                return realCustomer;
            }catch (ConflictInDataException exp){
                realCustomer.deleteCustomer(realCustomer.getCustomerNumber());
                throw exp;
            }
        } else {
            logger.error("Can not insert in Customer database.");
            return null;
        }
    }

    private static void insertRealCustomer(RealCustomer realCustomer) throws ConflictInDataException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(realCustomer);
            transaction.commit();
        }catch (ConstraintViolationException exp){
            if (transaction!=null) transaction.rollback();
            logger.error(exp.getMessage());
            throw new ConflictInDataException ("رکوردی با کد ملی مشابه وجود دارد. ");
        } catch (HibernateException exp){
            if (transaction!=null) transaction.rollback();
            logger.error(exp.getMessage());
        }finally {
            session.close();
        }
    }

    public static List<RealCustomer> search(RealCustomer realCustomer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("Select firstName, lastName, fatherName, NationalId, birthDate, customerNumber" +
                    "  From RealCustomer realCustomer Where (realCustomer.firstName is null or realCustomer.firstName=:firstNme)" +
                    "and (realCustomer.lastName is null or realCustomer.lastName=:lastNme)"+
                    "and (realCustomer.fatherName is null or realCustomer.fatherName=:fatherNme)"+
                    "and (realCustomer.NationalId is null or realCustomer.NationalId=:nationalId)"+
                    "and (realCustomer.birthDate is null or realCustomer.birthDate=:birthDate)"+
                    "and (realCustomer.customerNumber is null or realCustomer.customerNumber=:customerNum)"
            );
            logger.info("Search Queryyyyy "+ realCustomer.getFirstName());

            query.setParameter("firstNme",  realCustomer.getFirstName());
            query.setParameter("lastNme",  realCustomer.getLastName());
            query.setParameter("fatherNme",  realCustomer.getFatherName());
            query.setParameter("nationalId",  realCustomer.getNationalId());
            query.setParameter("birthDate",  realCustomer.getBirthDate());
            query.setParameter("customerNum",  realCustomer.getCustomerNumber());

            List realCustomers = query.list();
            logger.info("*******Search Queryyyyy "+ query.toString());
            transaction.commit();
            logger.info("Search Queryyyyy ");
            for (Iterator iter = realCustomers.iterator(); iter.hasNext(); ) {
                RealCustomer message = (RealCustomer) iter.next();
                logger.info( "Last Customer Number : "+message.getCustomerNumber());
            }
            return realCustomers;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
            return null;
        }finally {
            session.close();
        }
    }

    /*
    private static String createQueryString(RealCustomer realCustomer){
        StringBuilder query = new StringBuilder("");
        query.append(
                "SELECT * FROM realcustomer" +
                "VALUES(" +
                "CASE"+
        "WHEN NameOfTheCompany IS NOT NULL"+
        "THEN '''' + NameOfTheCompany + ''', '"+
        "ELSE 'NULL, '"+
        "END "+
                "CASE"+
        "WHEN IndustryType IS NOT NULL"+
        "THEN '''' + IndustryType + ''', '"+
        "ELSE 'NULL, '"+
        "END" +
        ")"
       );

        String x ="Select *" +
                "From RealCustomer realCustomer" +
                "Where (realCustomer.FIRST_NAME is null or realCustomer.FIRST_NAME=:firstNme"+
                "and realCustomer.LAST_NAME is null or realCustomer.LAST_NAME=:lastNme"+
                "and realCustomer.FATHER_NAME is null or realCustomer.FATHER_NAME=:fatherNme"+
                "and realCustomer.NATIONAL_ID is null or realCustomer.NATIONAL_ID=:nationalId"+
                "and realCustomer.BIRTH_DATE is null or realCustomer.BIRTH_DATE=:birthDate"+
                "and realCustomer.CUSTOMER_NUMBER is null or realCustomer.CUSTOMER_NUMBER=:customerNum"+
                ")";
        query.setParameter("firstNme",  realCustomer.getFirstName());
        query.setParameter("lastNme",  realCustomer.getLastName());
        query.setParameter("fatherNme",  realCustomer.getFatherName());
        query.setParameter("nationalId",  realCustomer.getNationalId());
        query.setParameter("birthDate",  realCustomer.getBirthDate());
        query.setParameter("customerNum",  realCustomer.getCustomerNumber());
        return query.toString();
    }*/
}


