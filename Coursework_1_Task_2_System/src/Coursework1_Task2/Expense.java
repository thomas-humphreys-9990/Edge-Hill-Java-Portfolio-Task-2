package Coursework1_Task2;

public class Expense
{
	/*	====================================================================================
	 * 	Definition of encapsulated global variables
	 *  ====================================================================================
	 */
	private int expenseID;
	private String expenseName;
	private String expenseDate;
	private String expenseCategory;
	private double expenseAmount;
	
	/*	====================================================================================
	 * 	Getters and setters for encapsulation
	 *  ====================================================================================
	 */
	public void setExpenseID (int theExpenseID)
	{
		expenseID = theExpenseID;
	}
	public int getExpenseID ()
	{
		return expenseID;
	}
	
	public void setExpenseName (String theExpenseName)
	{
		expenseName = theExpenseName;
	}
	public String getExpenseName ()
	{
		return expenseName;
	}
	
	public void setExpenseDate (String theExpenseDate)
	{
		expenseDate = theExpenseDate;
	}
	public String getExpenseDate ()
	{
		return expenseDate;
	}
	
	public void setExpenseCategory (String theExpenseCategory)
	{
		expenseCategory = theExpenseCategory;
	}
	public String getExpenseCategory ()
	{
		return expenseCategory;
	}
	
	public void setExpenseAmount (double theExpenseAmount)
	{
		expenseAmount = theExpenseAmount;
	}
	public double getExpenseAmount ()
	{
		return expenseAmount;
	}
	
	/*	====================================================================================
	 * 	toString method to return all variables in an Expense object for file storage
	 *  ====================================================================================
	 */
	public String toString ()
	{
		String outString = "";
		
		outString = expenseID + "α" + expenseName + "α" + expenseDate + "α" + expenseCategory + "α" +  expenseAmount;
		
		return outString;
	}
}
