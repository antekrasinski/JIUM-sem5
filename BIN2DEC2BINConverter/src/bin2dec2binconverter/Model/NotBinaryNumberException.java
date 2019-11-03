/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bin2dec2binconverter.Model;

/**
 * Exception class defining the exception of incorrect binary number
 * @author Antoni Krasiński 
 * @version 1.0
 */
public class NotBinaryNumberException extends Exception 
{
    /**
     * Constructor of the exception of incorrect binary number
     * @param bin - wrong number that exception is thrown by
     */
    public NotBinaryNumberException(String bin) 
    {
        super(bin + " is not a binary number.");
    }
}
