package DataAccess;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dotinschool6 on 11/23/2016.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L; //TODO : chi mige?
    final static Logger logger = Logger.getLogger(Customer.class);

    public Integer customerNumber;
    private Integer id;

    public Customer(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Customer(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Integer getLastCustomerNumber(){
        Integer newCustomerNum = 1000;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where id=(select max(id) from Customer ) ");
            List list =   query.list();
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                Customer message = (Customer) iter.next();
                newCustomerNum = message.getCustomerNumber();
                logger.info( "Last Customer Number : "+message.getCustomerNumber());
            }
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        return newCustomerNum;
    }

    public static Integer insert(){
        Integer newCustomerNum = getLastCustomerNumber()+1;
        Customer customer = new Customer(newCustomerNum);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.save(customer);
        /* employeeID = (Integer) session.save(employee); NICE ; get the id!!! */
            logger.info("New Customer inserted.");
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        return newCustomerNum;
    }
}
