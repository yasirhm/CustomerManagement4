package DataAccess;

import BusinessLogic.ConflictInDataException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by $Yasi on 12/12/2016.
 */
public class LoanFileCRUD {
    final static Logger logger = Logger.getLogger(LoanFileCRUD.class);

    public static void addNewLoanFile(LoanFile loanFile) throws ConflictInDataException {
        Integer id;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(loanFile);
            logger.info("New Loan File record inserted.");
            transaction.commit();
            //return loan;
        } catch (Throwable e) {
            if (transaction != null) transaction.rollback();
            logger.error(e , e);
            throw new ConflictInDataException("");
        } finally {
            session.close();
        }
    }
}
