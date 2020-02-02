package servicesbin2dec2bin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class responsible for mapping table
 * @author Antoni Krasinski
 * @version 1.0
 */
@Entity
public class HistoryEntity {
    
    /**
     * Auto incremented id, primary key of the table.
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int conversionId;
    
    /**
     * Result of the conversion.
     */
    private String conversionResult;
    
    /**
     * JPA required constructor.
     */
    public HistoryEntity(){}
    
    /**
     * Constructor setting the conversionResult field.
     * @param result 
     */
    public HistoryEntity(String result)
    {
        this.conversionResult = result;
    }
    
    /**
     * Gettter of conversionId.
     * @return 
     */
    public int getId()
    {
        return conversionId;
    }
    
    /**
     * Getter of conversionResult.
     * @return 
     */
    public String getResult()
    {
        return conversionResult;
    }
}
