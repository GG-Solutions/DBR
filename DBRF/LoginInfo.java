package DBRF;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LoginInfo {
	static boolean success=false;
	
	// I commented out most of this and made it really easy to login so that we could demo it with out any issues
	// I am currently working on correcting these issues.
	
	public static boolean uLogin(String uLogin, String uPass) throws IOException{
	  	Scanner s1;
        s1=new Scanner(new FileInputStream("test.txt"));
       
        while(s1.hasNext()) {
        	String pHname = s1.next();
        	String pHpass = s1.next();
        	
        //	String pHname = "admin";
        //	String pHpass = "password";
        	
            if(uLogin.equals(pHname) && uPass.equals(pHpass)) {
            	 success=true;
            }                
       }
        	if(!success) {
			JOptionPane.showMessageDialog(null,"User Name or Password is Incorrect.");
            
			
        }
        s1.close();
		return success;
	}
	
	public static void addUser(String uName, String uPass) throws FileNotFoundException{
      // this will add a user 
		OutputStream output = new FileOutputStream("test.txt",true);
		PrintStream printStream = new PrintStream(output);
		printStream.println();
		printStream.print(uName);
		printStream.println();
		printStream.print(uPass);
		printStream.close();
	}
	
	public static void removeUser(String uName, String uPass) throws IOException{
      // this will remove a user
		File inputFile = new File("test.txt");
		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(uName) || trimmedLine.equals(uPass)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		inputFile.delete();
		boolean rename = tempFile.renameTo(inputFile);
		
		if(!rename){
			JOptionPane.showMessageDialog(null,"User remove failed");
		}
	}
}

