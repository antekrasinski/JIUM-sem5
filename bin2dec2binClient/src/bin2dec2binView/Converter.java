/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bin2dec2binView;

import bin2dec2binClient.Bin2dec2binClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Antek
 */
public class Converter{
    private static Bin2dec2binClient client;
    private Integer dec = 0;
    private String bin = "";
    private Integer option = 0;
    private List<String> binList;
    public void run()
    {
        client = new Bin2dec2binClient(".properties");
        boolean exit = false;
        while (exit == false) {
            showMenu();
            this.setOption();
            
            switch (option) {
                case 1:        
                    this.setBin();
                    System.out.println(client.bin2dec(bin));
                    break;
                case 2:
                    this.setDec();
                    System.out.println(client.dec2bin(dec));
                    break;
                    
                case 3:
                    this.setMultipleBin();
                    System.out.println(client.multipleBin2Dec(binList));
                    break;
            }   
            }
        }
    
    private static void showMenu()
    {
        System.out.println("-----------------");
        System.out.println("BIN2DEC2BIN Converter");
        System.out.println("-----------------");
        System.out.println("1) BIN2DEC");
        System.out.println("2) DEC2BIN");
        System.out.println("3) Multiple BIN2DEC");
        System.out.println("-----------------");
    }
    
    private void setOption()
    {
        Scanner scanner = new Scanner(System.in);
        option = Integer.parseInt(scanner.next());
    }
    
    private void setBin()
    {
        System.out.println("Podaj BIN: ");
        Scanner scanner = new Scanner(System.in);
        bin = scanner.next();
    }
     
    private void setDec()
    {
        System.out.println("Podaj DEC: ");
        Scanner scanner = new Scanner(System.in);
        dec = Integer.parseInt(scanner.next());
    }
    
    private void setMultipleBin()
    {
        System.out.println("Podaj trzy BIN: ");
        Scanner scanner = new Scanner(System.in);
        binList = new ArrayList<>();
        String tmp;
        for(int i=0; i<=2; i++)
        {
            tmp = scanner.next();
            binList.add(tmp);
        }
    }
}
