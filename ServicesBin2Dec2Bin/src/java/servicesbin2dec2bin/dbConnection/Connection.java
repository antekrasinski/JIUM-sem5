package servicesbin2dec2bin.dbConnection;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import servicesbin2dec2bin.entities.HistoryEntity;

/**
 * Class required for creating connection to database.
 * @author Antoni Krasinski
 * @version 1.0
 */
public class Connection {
    
    /**
     * EntityManager required for connection with database.
     */
    private final EntityManager manager;
    
    /**
     * Constructor creating the connection.
     */
    public Connection()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ServicesBin2Dec2BinPU");
        manager = factory.createEntityManager();
    }
    
    /**
     * Getter of the History of conversions.
     * @return history of conversions transformed to html view.
     */
    public String getHistory()
    {
        List<HistoryEntity> history = manager.createQuery("Select * from" + HistoryEntity.class.getSimpleName()).getResultList();
        String ret="";
        for (HistoryEntity entity: history)
        {
            ret += entity.getResult() + "<br>";
        }
        return ret;
    }
    
    public void addEntity(String result)
    {
        manager.getTransaction().begin();
        manager.persist(new HistoryEntity(result));
        manager.getTransaction().commit();
    }
}
