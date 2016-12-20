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
//                grantCondition.setLOAN_TYPE_ID(id);
                session.save(grantCondition);
            }
//            session.update(loan);
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

    public static List<LoanType> loadAllLanTypes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List list = session.createQuery("FROM LoanType loan").list();
            transaction.commit();
            return list;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    public static void checkConditionAccuracy(LoanFile loanFile) throws ConflictInDataException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql ="From GrantCondition grantCondition Where (grantCondition.LOAN_TYPE_ID="+loanFile.getLoanTypeID()+")" +
                    " and (grantCondition.minContractAmount < " +loanFile.getAmount()+")"
                    +" and (grantCondition.maxContractAmount > " +loanFile.getAmount()+")"
                    +" and (grantCondition.minContractPeriod < "+loanFile.getPeriod()+")"
                    +" and (grantCondition.maxContractPeriod > "+loanFile.getPeriod()+")";
            /*Query query = session.createQuery("From GrantCondition grantCondition Where (grantCondition.LOAN_TYPE_ID=:loanID )"
                    + " and (grantCondition.minContractAmount < :amount)");
                    + " and (grantCondition.maxContractAmount > :amount)"
                    + " and (grantCondition.minContractPeriod < :period)"
                    + " and (grantCondition.maxContractPeriod > : period)"
            );*/

            //query.setParameter("loanID", loanFile.getLoanTypeID());
//            query.setParameter("amount", loanFile.getAmount());
            //query.setParameter("period", loanFile.getPeriod() );
            Query query = session.createQuery(sql);
            List list = query.list();
            logger.info("*******Search Queryyyyy " + query.toString());
            transaction.commit();
            logger.info("Search Queryyyyy ");
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                GrantCondition grant = (GrantCondition) iter.next();
                logger.info("Last GRANT ID : " + grant.getGRANT_ID());
            }
           if (list.size() == 0)
               throw new ConflictInDataException("امکان تعریف تسهیلاتی با این شرایط وجود ندارد.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }
}
