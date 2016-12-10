package DataAccess;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import BusinessLogic.ConflictInDataException;
import org.hibernate.query.Query;

import java.util.*;

/**
 * Created by $Yasi on 12/6/2016.
 */
public class LoanTypeCRUD {
    final static Logger logger = Logger.getLogger(LoanTypeCRUD.class);

    public static LoanType addNewRecord(LoanType loan) throws ConflictInDataException {
        Integer id;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(loan);
            for (GrantCondition grantCondition : loan.getGrantConditions()){
                grantCondition.setLOAN_TYPE_ID(id);
                session.save(grantCondition);
            }
            logger.info("New Loan Type record inserted.");
            transaction.commit();
            return loan;
        } catch (Throwable e) {
            if (transaction != null) transaction.rollback();
            logger.error(e , e);
            throw new ConflictInDataException("تسهیلاتی با این نام قبلا ثبت شده است.");
        } finally {
            session.close();
        }
    }

    public static LoanType getLoanId(String loanName, GrantCondition grantCondition) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM LoanType loan WHERE loan.name=:name");
            query.setParameter("name", loanName);
            List list = query.list();
            LoanType loanType = new LoanType();
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                loanType = (LoanType) iter.next();
            }
            transaction.commit();
            return loanType;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
}
