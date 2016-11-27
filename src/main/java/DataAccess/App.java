package DataAccess;

/**
 * Created by dotinschool6 on 11/23/2016.
 */
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


public class App extends Customer
{

    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        App obj = new App();
        obj.runMe("HibernateLog4j");
        logger.warn("WHAT IS WRONG WITH YOUUUUUUUUUUUUUUUUUUUUUUUU");
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        RealCustomer customer = new RealCustomer();

       //Customer customer1 = new Customer(8888);
        Query query = session.createQuery("from Customer where id=(select max(id) from Customer ) ");
        List list =   query.list();
        //logger.info("exequtedd: "+cu.get(0));

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Customer message = (Customer) iter.next();
            logger.info( "infoooooooooo: "+message.getCustomerNumber());
        }
        //final Serializable save = session.save(customer1);

        transaction.commit();
    }

    private void runMe(String parameter){

        if(logger.isDebugEnabled()){
            logger.debug("This is debug : " + parameter);
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);

    }

}
