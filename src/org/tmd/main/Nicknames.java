package org.tmd.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author yew_mentzaki
 */
public class Nicknames {
    private static String nicks[];
    private static boolean taken[];
    public static void init() {
        try {
            Scanner scanner = new Scanner(new File("res/text/nicknames.txt"));
            String s = new String();
            while(scanner.hasNextLine()){
                s += scanner.nextLine() + '\n';
            }
            nicks = s.split("\n");
            taken = new boolean[nicks.length];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Nicknames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String get(){
        int i = 0;
        do{
            i = Main.RANDOM.nextInt(taken.length);
        }while(taken[i]);
        taken[i] = true;
        return nicks[i];
    }
    
    public static void free(){
        for (int i = 0; i < taken.length; i++) {
            taken[i] = false;
        }
    }
    
}