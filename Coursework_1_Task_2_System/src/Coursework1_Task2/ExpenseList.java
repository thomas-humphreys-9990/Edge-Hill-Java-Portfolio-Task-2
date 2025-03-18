package Coursework1_Task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ExpenseList
{
	/*	====================================================================================
	 * 	Global Variables
	 *  ====================================================================================
	 */
	Expense [] expenseList = new Expense [1000];
	int currentPosition = 0;
	
	/*	====================================================================================
	 * 	Add Method to add an Expense object to the array of Expenses.
	 *  ====================================================================================
	 */
	public void addExpense (Expense theExpense)
	{
		expenseList [currentPosition] = theExpense;
		currentPosition ++;
	}
	
	/*	====================================================================================
	 * 	Encryption to return Expense details in an encrypted format.
	 *  ====================================================================================
	 */
	public String encrypt (String toEncrypt)
	{
		String outString = "";
		
		for (int i = 0; i < toEncrypt.length (); i ++)
		{
			char currentChar = toEncrypt.charAt(i);
			int currentCharASCII = (int)currentChar;
			
			int encryptedCharASCII = currentCharASCII - 4;
			char encryptedChar = (char)encryptedCharASCII;
			
			outString += encryptedChar;
		}
		
		return outString;
	}
	
	/*	====================================================================================
	 * 	Decryption to convert cipher-text into plain-text to allow users to read it.
	 *  ====================================================================================
	 */
	public String decrypt (String encrypted)
	{
		String outString = "";
		
		for (int i = 0; i < encrypted.length (); i ++)
		{
			char currentChar = encrypted.charAt(i);
			int currentCharASCII = (int)currentChar;
			
			int encryptedCharASCII = currentCharASCII + 4;
			char encryptedChar = (char)encryptedCharASCII;
			
			outString += encryptedChar;
		}
		
		return outString;
	}
	
	/*	====================================================================================
	 * 	Write method to save Expense data in an encrypted format for later use, even after
	 *  the program has terminated.
	 *  ====================================================================================
	 */
	public void writeExpensesToFile ()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter (new FileWriter ("textfiles/Expense.txt"));
			
			Expense currentExpense = new Expense ();
			
			for (int i = 0; i < currentPosition; i ++)
			{
				currentExpense = expenseList [i];
				
				bw.write(encrypt (currentExpense.toString()) + "\n");
			}
			
			bw.close();
		}
		catch (Exception unWritable)
		{
			System.out.println ("Error when writing to file");
			unWritable.printStackTrace ();
		}
	}
	
	/*	====================================================================================
	 * 	Read method to ensure that Expense data can be retrieved from the text file in a
	 *  decrypted format for use in the main program.
	 *  ====================================================================================
	 */
	public void readExpensesFromFile ()
	{
		try
		{
			BufferedReader br = new BufferedReader (new FileReader ("textfiles/Expense.txt"));
			String encryptedCurrentLine = br.readLine ();
			
			while (encryptedCurrentLine != null)
			{
				String decryptedDetails = decrypt (encryptedCurrentLine);
				String [] splitDetails = decryptedDetails.split("α");
				
				Expense currentExpense = new Expense ();
				currentExpense.setExpenseID (Integer.parseInt(splitDetails [0]));
				currentExpense.setExpenseName (splitDetails [1]);
				currentExpense.setExpenseDate (splitDetails [2]);
				currentExpense.setExpenseCategory (splitDetails [3]);
				currentExpense.setExpenseAmount (Double.parseDouble (splitDetails [4]));
				
				addExpense (currentExpense);
				encryptedCurrentLine = br.readLine ();
			}
			
			br.close ();
		}
		catch (Exception unWritable)
		{
			System.out.println ("Error when reading from file");
			unWritable.printStackTrace ();
		}
	}
	
	/*	====================================================================================
	 * 	Method to display all Expense objects to the user
	 *  ====================================================================================
	 */
	public void returnAllExpenses ()
	{
		for (int i = 0; i < currentPosition; i ++)
		{
			Expense currentExpense = expenseList [i];
			
			System.out.println ("ID: " + currentExpense.getExpenseID() + " || Name: " + currentExpense.getExpenseName ()
								+ " || Date: " + currentExpense.getExpenseDate () + " || Category: "
								+ currentExpense.getExpenseCategory () + " || Amount: £" + currentExpense.getExpenseAmount ());
		}
	}
	
	/*	====================================================================================
	 * 	Method to filter through each category and return any Expenses that are from that
	 *  category.
	 *  ====================================================================================
	 */
	public Expense [] filterExpenses (String theCategory)
	{
		Expense [] filteredList = new Expense [currentPosition];
		int currentFilteredListPosition = 0;
		
		for (int i = 0; i < currentPosition; i ++)
		{
			Expense currentExpense = expenseList [i];
			
			if (currentExpense.getExpenseCategory ().equalsIgnoreCase(theCategory))
			{
				filteredList [currentFilteredListPosition] = currentExpense;
				currentFilteredListPosition ++;
			}
		}
		
		return filteredList;
	}
	
	/*	====================================================================================
	 * 	Method to calculate the total of each categories and then output the total for
	 * 	all categories combined.
	 *  ====================================================================================
	 */
	public void calculateTotals ()
	{
		// Initialisation of the local variables
		Expense [] foodList = new Expense [currentPosition];
		Expense [] transportList = new Expense [currentPosition];
		Expense [] utilitiesList = new Expense [currentPosition];
		int filteredListPosition = 0;
		
		double foodTotal = 0.0;
		double transportTotal = 0.0;
		double utilitiesTotal = 0.0;
		int numFood = 0;
		int numTransport = 0;
		int numUtilities = 0;
		
		// Gets the list of each of the categories for processing
		foodList = filterExpenses ("Food");
		transportList = filterExpenses ("Transportation");
		utilitiesList = filterExpenses ("Utilities");
		
		if (foodList.length != 0)
		// Validation to ensure an Out Of Bounds error does not occur if there are no Expenses in the system
		{
			Expense currentExpense = foodList [0];
			
			// Calculations to get the total amount for every category
			while (currentExpense != null)
			{
				foodTotal = foodTotal + currentExpense.getExpenseAmount();
				filteredListPosition ++;
				numFood ++;
				currentExpense = foodList [filteredListPosition];
			}
			
			filteredListPosition = 0;
			currentExpense = transportList [0];
			
			while (currentExpense != null)
			{
				transportTotal = transportTotal + currentExpense.getExpenseAmount();
				filteredListPosition ++;
				numTransport ++;
				currentExpense = transportList [filteredListPosition];
			}
			
			filteredListPosition = 0;
			currentExpense = utilitiesList [0];
			
			while (currentExpense != null)
			{
				utilitiesTotal = utilitiesTotal + currentExpense.getExpenseAmount();
				filteredListPosition ++;
				numUtilities ++;
				currentExpense = utilitiesList [filteredListPosition];
			}
			
			// Outputs all the totals, including the totals of the totals
			System.out.println ("Number of Food Expenses: " + numFood + " || Total cost of Food Expenses: £" + String.format ("%.2f", foodTotal)
								+ "\nNumber of Transport Expenses: " + numTransport + " || Total cost of Transport Expenses: £"
								+ String.format ("%.2f", transportTotal) + "\nNumber of Utilities Expenses: " + numUtilities
								+ " || Total cost of Utilities Expenses: £" + String.format ("%.2f", utilitiesTotal)
								+ "\n\nOverall cost of all Expenses: £" + String.format ("%.2f", (foodTotal + transportTotal + utilitiesTotal)));
		}
		else
		{
			System.out.println ("No Expenses to view");
		}
	}
}
