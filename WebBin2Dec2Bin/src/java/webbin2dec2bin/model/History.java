/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbin2dec2bin.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class responsible for collecting data from all conversions.
 * @author Antoni Krasinski
 * @version 1.0
 */
public class History {
    private final List<String> history;
    
    public History()
    {
        history = new LinkedList<>();
    }
    
    public void addToHistory(String newOne)
    {
        history.add(newOne);
    }
    
    public List getHistory()
    {
        return history;
    }
}
