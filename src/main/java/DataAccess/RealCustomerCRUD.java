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
import java.util.ArrayList;
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

    public static RealCustomer addRealCustomerDataAccess(RealCustomer realCustomer) throws ConflictInDataException {
        logger.setLevel(Level.INFO);
        Integer customerNumber = realCustomer.insert();
        if (customerNumber != null) {
            realCustomer.setCustomerNumber(customerNumber);
            try {
                insertRealCustomer(realCustomer);
                return realCustomer;
            } catch (ConflictInDataException exp) {
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
        } catch (ConstraintViolationException exp) {
            if (transaction != null) transaction.rollback();
            logger.error(exp.getMessage());
            throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد. ");
        } catch (HibernateException exp) {
            if (transaction != null) transaction.rollback();
            logger.error(exp.getMessage());
        } finally {
            session.close();
        }
    }

    public static List<RealCustomer> search(RealCustomer realCustomer) {
        List<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("From RealCustomer realCustomer Where (realCustomer.firstName=:firstNme or :firstNme is null )"
                    + "and (realCustomer.lastName=:lastNme or :lastNme is null )"
                    + "and (realCustomer.NationalId=:nationalId or :nationalId is null)"
                    + "and (realCustomer.customerNumber=:customerNum or :customerNum is null)"
            );
            logger.info("Search Queryyyyy " + realCustomer.getFirstName());

            query.setParameter("firstNme", realCustomer.getFirstName());
            query.setParameter("lastNme", realCustomer.getLastName());
            query.setParameter("nationalId", realCustomer.getNationalId());
            query.setParameter("customerNum", realCustomer.getCustomerNumber());

            List list = query.list();
            logger.info("*******Search Queryyyyy " + query.toString());
            transaction.commit();
            logger.info("Search Queryyyyy ");
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                RealCustomer real = (RealCustomer) iter.next();
                realCustomers.add(real);
                logger.info("Last Customer Number : " + real.getCustomerNumber());
            }
            return realCustomers;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    public static RealCustomer getRealCustomer(Integer customerNumber){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM RealCustomer realCustomer WHERE realCustomer.customerNumber=:CUSTOMER_NUMBER");
            query.setParameter("CUSTOMER_NUMBER",  customerNumber);
            List list = query.list();
            RealCustomer real = new RealCustomer();
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                real = (RealCustomer) iter.next();
            }
            transaction.commit();
            return real;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
            return null;
        }finally {
            session.close();
        }
    }

    public static void updateRealCustomer(RealCustomer realCustomer) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            /*Employee employee =
                    (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary( salary );*/

            Query query = session.createQuery("update RealCustomer realcustomer set realcustomer.firstName=:firstNme "
                   + " ,realcustomer.lastName=:lastNme"
                    +" ,realcustomer.fatherName=:father , realcustomer.birthDate=:birthDay"
                    +" where realcustomer.customerNumber = :CUSTOMER_NUMBER");
            query.setParameter("firstNme", realCustomer.getFirstName());
            query.setParameter("lastNme",realCustomer.getLastName());
            query.setParameter("father",realCustomer.getFatherName());
            query.setParameter("birthDay",realCustomer.getBirthDate());
            query.setParameter("CUSTOMER_NUMBER",  realCustomer.getCustomerNumber());
            int rowsAffected = query.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Updated ********" + rowsAffected + " rows.");
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
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


