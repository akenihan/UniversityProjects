import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
public class login
{
	private int attempts = 0;
	private boolean login = false;
	private String userInputUser;
	private String userInputPass;
	private String line;
	private String[] lineArray;
	private String lineUser;
	private String linePass;
	private String loginString;
	public boolean loginTry(String username, String password) throws IOException
	{
		int attempts = 0;
		boolean login = false;
		    FileReader aFileReader = new FileReader("Operators.txt");
	     	Scanner in = new Scanner(aFileReader);
			userInputUser = username;	// Inputted username
			userInputPass = password;	// Inputted password
			
			while(in.hasNext() && !login)
			{	
				line = in.nextLine();			//declares value as next line in 
				lineArray = line.split(","); 	//splits the read line from passwords.txt into an array at every ","
				lineUser = lineArray[0];		//format of passwords.txt suggests username at position 1 in split array
				linePass = lineArray[1];		//format of passwords.txt suggests password at position 2 in split array
				if(userInputUser.equalsIgnoreCase(lineUser) && userInputPass.equals(linePass)) //if the user inputted username (ignoring case) matches the one on the line and the user inputted password too, also checks if the called user type matches the usertype of the one on the line
		    		login = true; //telling the system the login succeeded
			}
		if (login == true)
		return true;
		else
		return false;
	
	}
}