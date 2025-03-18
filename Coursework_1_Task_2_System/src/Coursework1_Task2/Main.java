package Coursework1_Task2;
import java.util.Calendar;					// Used to get the current date
import java.util.Scanner;
import java.text.SimpleDateFormat;			// Used to get the current date in the format dd/mm/yyyy

public class Main
{
	/*	====================================================================================
	 * 	Declarations of the scanner for inputs and the ExpenseList object
	 *  ====================================================================================
	 */
	Scanner input = new Scanner (System.in);
	ExpenseList el = new ExpenseList ();
	
	/*	====================================================================================
	 * 	Method to call the read from file method in the ExpenseList so that saved data
	 *  can be accessed from the text file Expense.txt.
	 *  ====================================================================================
	 */
	public void readTextFile ()
	{
		el.readExpensesFromFile();
	}
	
	/*	====================================================================================
	 * 	Method to prompt the user to enter the Expense's name.
	 * 
	 *  Made into its own method to simplify prompting the user for an Expense name,
	 *  and also so that it can be called multiple times if necessary.
	 *  ====================================================================================
	 */
	public String inputNewExpenseName ()
	{
		boolean validName = false;
		String theName = "";
		
		while (!validName)
		// Validation to ensure the user actually enters a name (Presence Check)
		{
			System.out.println ("Please enter the name for the Expense:\n(Enter e to return to options)");
			theName = input.nextLine ();
			
			if (theName.equals("") == false)
			{
				validName = true;
			}
			else if (theName.equalsIgnoreCase ("e"))
			// Allows the user to exit and return to the options
			{
				break;
			}
		}
		
		return theName;
	}
	
	/*	====================================================================================
	 * 	Method to return if a year entered as a parameter is a leap year.
	 * 
	 * 	If a year is divisible by 4, it is a leap year.  If the year is divisible by 100
	 * 	though, it is not a leap year.  IF A YEAR IS DIVISIBLE BY 400, IT IS A LEAP YEAR.
	 * 
	 * 	(e.g. 2000 would return true, but 1900 would return false).
	 *  ====================================================================================
	 */
	public boolean isLeapYear (int theYear)
	{
		if (theYear % 4 == 0)
		{
			if (theYear % 100 == 0)
			{
				return theYear % 400 == 0;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	/*	====================================================================================
	 * 	This method will first ask the user if they want to use the current date, or type
	 * 	a custom date.  If they selected current date, the system will generate the
	 * 	current date.  Otherwise, the system will ask the user for a valid date.
	 * 
	 * 	The system will check if the year entered is of the correct type, as well as between
	 * 	the range 1900 and the current year.  It will also check if the year selected is a
	 * 	leap year.
	 * 	The system will check if the month is between 1 and 12 for years that aren't the
	 * 	current year.  If the year is the current year, then the range is shortened to
	 * 	being between 1 and the current month.
	 * 	The system will check is the day is between 1 and the number of days in the selected
	 * 	month.  If the chosen year is a leap year and February was selected as the chosen
	 * 	month, then the range is between 1 and 29, not 1 and 28.  If the selected year is
	 * 	the current year and the selected month is the current month, then the range is from
	 *  1 to the current day.
	 *  ====================================================================================
	 */
	public String inputNewExpenseDate ()
	{
		boolean validUserChoice = false;
		String userChoice = "";
		
		String chosenDate = "";
		
		
		String currentDate = new SimpleDateFormat ("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		// Gets the current date using the imported packages Calendar and SimpleDateFormat
		String [] currentDateDetails = currentDate.split ("/");
		String currentYear = currentDateDetails [2];
		String currentMonth = currentDateDetails [1];
		String currentDay = currentDateDetails [0];
		// Gets the current year, month and day from the current date
		
		while (!validUserChoice)
		{
			System.out.println ("Enter 1 to use the current date\n"
								+ "Enter 2 to manually enter a date\n"
								+ "(Enter e to return to options)");
			userChoice = input.nextLine ();
			
			if (userChoice.equals("1"))
			// The user wants to use the current date
			{
				validUserChoice = true;
				
				System.out.println ("You have chosen to use the current date - " + currentDate);
				chosenDate = currentDate;
			}
			else if (userChoice.equals("2"))
			// The user wants to enter a date manually
			{
				// Declarations of variables used in this section of the method
				boolean exit = false;
				boolean validYear = false;
				boolean validMonth = false;
				boolean validDay = false;
				boolean chosenLeapYear = false;
				String strChosenYear = "";
				String strChosenMonth = "";
				String strChosenDay = "";
				int chosenYear = -1;
				int chosenMonth = -1;
				int chosenDay = -1;
				
				int maxYear = Integer.parseInt (currentYear);
				int maxMonth = 12;
				int maxDay = -1;
				
				while (!validYear && !exit)
				// Gets the chosen year from the user
				{
					System.out.println ("Please enter the year of the Expense:\n(Enter e to exit)");
					strChosenYear = input.nextLine ();
					
					if (strChosenYear.equalsIgnoreCase("e") == false)
					{
						try
						// Ensures the year entered is an integer
						{
							chosenYear = Integer.parseInt (strChosenYear);
							
							if (chosenYear <= maxYear && chosenYear > 1899)
							// Ensures the year entered is between 1900 and the current year
							{
								validYear = true;
								System.out.println ("Chosen year: " + chosenYear);
								chosenLeapYear = isLeapYear (chosenYear);
								// Finds out if the chosen year is a leap year
							}
							else
							{
								System.out.println ("That year isn't between the bounds of 1900 and " + Integer.parseInt(currentYear));
							}
						}
						catch (Exception yearNotInt)
						{
							// yearNotInt.printStackTrace ();
							System.out.println ("Ensure you enter the year as an integer");
						}
					}
					else
					// Allows the user to return to options
					{
						exit = true;
					}
				}
				
				while (!validMonth && !exit)
				// Gets the chosen month from the user
				{
					System.out.println ("Please enter the month of the Expense:\n"
							+ "(Enter 1 for January, 2 for February etc.)\n"
							+ "(Enter e to return to options)");
					strChosenMonth = input.nextLine ();
					
					if (strChosenMonth.equalsIgnoreCase("e") == false)
					// This section of code will run if the user does not want to return to options
					{
						try
						// Ensures the chosen month is an integer
						{
							chosenMonth = Integer.parseInt (strChosenMonth);
							
							if (chosenYear == Integer.parseInt(currentYear))
							{
								if (chosenMonth > 0 && chosenMonth <= Integer.parseInt(currentMonth))
								// If the chosen year is the current year, the range that the chosen month must adhere
								// to is between 0 and the current month.
								{
									validMonth = true;
								}
								else
								{
									System.out.println ("Ensure the month entered is between 1 and " + Integer.parseInt (currentMonth));
								}
							}
							else
							{
								if (chosenMonth > 0 && chosenMonth <= maxMonth)
								// If the chosen year is not the current year, then the range is between 1 and 12.
								{
									validMonth = true;
								}
								else
								{
									System.out.println ("Ensure the month entered is between 1 and 12");
								}
							}
						}
						catch (Exception monthNotInt)
						{
							// monthNotInt.printStackTrace ();
							System.out.println ("Ensure you enter the month as an integer");
						}
					}
					else
					{
						exit = true;
					}
				}
				
				if (!exit)
				{
					switch (chosenMonth)
					// This Switch/Case will return the maximum number of days for the selected month
					{
						case 1:
							maxDay = 30;
							System.out.println ("Chosen month: January\nNumber of days in January: " + maxDay);
							break;
						case 2:
							if (chosenLeapYear)
							// The maxDay variable will be 29 if the year selected was a leap year and February was
							// selected.  If the year was not a leap year, then the maxDay variable will be 28.
							{
								maxDay = 29;
							}
							else
							{
								maxDay = 28;
							}
							System.out.println ("Chosen month: February\nNumber of days in February: " + maxDay);
							break;
						case 3:
							maxDay = 31;
							System.out.println ("Chosen month: March\nNumber of days in March: " + maxDay);
							break;
						case 4:
							maxDay = 30;
							System.out.println ("Chosen month: April\nNumber of days in April: " + maxDay);
							break;
						case 5:
							maxDay = 31;
							System.out.println ("Chosen month: May\nNumber of days in May: " + maxDay);
							break;
						case 6:
							maxDay = 30;
							System.out.println ("Chosen month: June\nNumber of days in June: " + maxDay);
							break;
						case 7:
							maxDay = 31;
							System.out.println ("Chosen month: July\nNumber of days in July: " + maxDay);
							break;
						case 8:
							maxDay = 31;
							System.out.println ("Chosen month: August\nNumber of days in August: " + maxDay);
							break;
						case 9:
							maxDay = 30;
							System.out.println ("Chosen month: September\nNumber of days in September: " + maxDay);
							break;
						case 10:
							maxDay = 31;
							System.out.println ("Chosen month: October\nNumber of days in October: " + maxDay);
							break;
						case 11:
							maxDay = 30;
							System.out.println ("Chosen month: November\nNumber of days in November: " + maxDay);
							break;
						case 12:
							maxDay = 31;
							System.out.println ("Chosen month: December\nNumber of days in December: " + maxDay);
							break;
					}
				}
				
				while (!validDay && !exit)
				// This will get the chosen day from the user
				{
					System.out.println ("Please enter the day for the Expense:\n(Enter e to return to options)");
					strChosenDay = input.nextLine ();
					
					if (strChosenDay.equalsIgnoreCase("e") == false)
					// If the user does not want to return to options, this code will run
					{
						try
						{
							chosenDay = Integer.parseInt (strChosenDay);
							
							if (chosenYear == Integer.parseInt(currentYear) && chosenMonth == Integer.parseInt(currentMonth))
							{
								// If the chosen year and chosen month are the current year and current month, then
								// the day must be between 1 and the current day.
								if (chosenDay <= Integer.parseInt(currentDay) && chosenDay > 0)
								{
									validDay = true;
									System.out.println ("Chosen day: " + chosenDay);
								}
								else
								{
									System.out.println ("The day needs to be between 1 and " + Integer.parseInt(currentDay));
								}
							}
							else
							{
								// If the chosen year or month is not the current year or month, the range that the day
								// must adhere to will be between 1 and the number of days in the chosen month
								if (chosenDay <= maxDay && chosenDay > 0)
								{
									validDay = true;
									System.out.println ("Chosen day: " + chosenDay);
								}
								else
								{
									System.out.println ("The day needs to be between 1 and " + maxDay);
								}
							}
						}
						catch (Exception dayNotInt)
						{
							// dayNotInt.printStackTrace ();
							System.out.println ("Ensure the day is an integer");
						}
					}
					else
					{
						exit = true;
					}
					
				}
				
				if (exit)
				// The method will return "e" if the user chose to exit
				{
					chosenDate = "e";
					System.out.println ("Exit successful");
					validUserChoice = true;
				}
				else
				// The method will build the chosen date from the data that the user entered
				{
					chosenDate = chosenDay + "/" + chosenMonth + "/" + chosenYear;
					System.out.println ("Chosen date - " + chosenDate);
					validUserChoice = true;
				}
				
			}
			else if (userChoice.equalsIgnoreCase ("e"))
			// The user wants to return to the options
			{
				chosenDate = "e";
				System.out.println ("Exit successful");
				break;
			}
		}
		
		return chosenDate;
	}
	
	public String inputExpenseCategory ()
	// This method will take the category of Expense from the user
	{
		boolean validCategory = false;
		String theCategory = "";
		
		while (!validCategory)
		{
			System.out.println ("Please enter the category of Expense:\n"
								+ "Enter f for Food | Enter t for Transportation | Enter u for Utilities"
								+ "\n(Enter e to return to options)");
			theCategory = input.nextLine ();
			
			if (theCategory.equalsIgnoreCase("f"))
			// This will make the category Food
			{
				System.out.println ("Food selected");
				theCategory = "Food";
				validCategory = true;
			}
			else if (theCategory.equalsIgnoreCase("t"))
			// This will make the category Transport
			{
				System.out.println ("Transportation selected");
				theCategory = "Transportation";
				validCategory = true;
			}
			else if (theCategory.equalsIgnoreCase("u"))
			// This will make the category Utilities
			{
				System.out.println ("Utilities selected");
				theCategory = "Utilities";
				validCategory = true;
			}
			else if (theCategory.equals("e"))
			// This will allow the user to return to the options
			{
				System.out.println ("Exit successful");
				validCategory = true;
			}
		}
		
		return theCategory;
	}
	
	public double inputExpenseAmount ()
	// This method allows the user to enter the amount of the Expense
	{
		boolean validAmount = false;
		String strAmount = "";
		double amount = -1.0;
		
		while (!validAmount)
		// This loop will only stop if the user enters a positive number
		{
			System.out.println ("Please enter the amount for the Expense:\n(Enter e to return to options)");
			strAmount = input.nextLine ();
			
			if (strAmount.equalsIgnoreCase("e") == false)
			{
				try
				{
					amount = Double.parseDouble (strAmount);
					
					if (0 <= amount)
					{
						strAmount = String.format ("%.2f", amount);
						amount = Double.parseDouble (strAmount);
						validAmount = true;
						System.out.println ("Selected amount: £" + amount);
					}
				}
				catch (Exception amountNotDouble)
				{
					// amountNotDouble.printStackTrace();
					System.out.println ("Ensure the amount is a number");
				}
			}
			else
			{
				amount = -1;
				validAmount = true;
				System.out.println ("Exit successful");
			}
		}
		
		return amount;
	}
	
	/*	====================================================================================
	 * 	Methods that are run when the user selects from the options
	 *  ====================================================================================
	 */
	public void addExpense ()
	// This method will add a new Expense to the system, by having the user enter all of the Expense's details
	// before adding them to a new Expense object, which will be saved to the system.
	{
		boolean exit = false;
		int newExpenseID = el.currentPosition + 1;
		String newExpenseName = "";
		String newExpenseDate = "";
		String newExpenseCategory = "";
		double newExpenseAmount = 0.0;
		
		newExpenseName = inputNewExpenseName();
		
		if (newExpenseName.equalsIgnoreCase("e"))
		{
			exit = true;
		}
		else
		{
			System.out.println ("=================================================================================");
		}
		
		if (!exit)
		{
			newExpenseDate = inputNewExpenseDate();
			
			if (newExpenseDate.equalsIgnoreCase("e"))
			{
				exit = true;
			}
			else
			{
				System.out.println ("=================================================================================");
			}
		}
		
		if (!exit)
		{
			newExpenseCategory = inputExpenseCategory ();
			
			if (newExpenseCategory.equalsIgnoreCase("e"))
			{
				exit = true;
			}
			else
			{
				System.out.println ("=================================================================================");
			}
		}
		
		if (!exit)
		{
			newExpenseAmount = inputExpenseAmount();
			
			if (newExpenseAmount == -1.0)
			{
				exit = true;
			}
			else
			{
				System.out.println ("=================================================================================");
			}
		}
		
		if (!exit)
		// If the user entered "e" to exit at any point, the variable exit will be set to true so no more code in this
		// method will run and no new Expense will be added to the system.
		{
			Expense newExpense = new Expense ();
			newExpense.setExpenseID (newExpenseID);
			newExpense.setExpenseName (newExpenseName);
			newExpense.setExpenseDate (newExpenseDate);
			newExpense.setExpenseCategory (newExpenseCategory);
			newExpense.setExpenseAmount (newExpenseAmount);

			el.addExpense (newExpense);
			el.writeExpensesToFile ();
			// Write to file to ensure that the new Expense is saved to the text file.
		}
		
	}
	
	public void viewAllExpenses ()
	// This method will show all the Expenses to the user
	{
		el.returnAllExpenses ();
	}
	
	public void calculatePrices ()
	// This method will calculate and display the number of records for each category, as well as their price totals
	// and the overall price total.
	{
		el.calculateTotals ();
	}
	
	public void filterExpenses ()
	// This method will filter the ExpenseList to only display Expenses from a chosen category
	{
		String chosenCategory = inputExpenseCategory ();
		// Gets the chosen category
		
		if (chosenCategory.equalsIgnoreCase("e") == false)
		{
			Expense [] filteredExpenses = el.filterExpenses (chosenCategory);
			int count = 0;
			double total = 0.0;
			
			if (filteredExpenses.length != 0)
			{
				Expense currentExpense = filteredExpenses [0];
				
				while (currentExpense != null)
				{
					System.out.println ("Record: " + (count + 1) + "	Name: " + currentExpense.getExpenseName()
										+ " || Date: " + currentExpense.getExpenseDate()
										+ " || Category: " + currentExpense.getExpenseCategory()
										+ " || Amount: £" + currentExpense.getExpenseAmount());
					// Outputs all of the Expenses that are from the chosen category
					
					total = total + currentExpense.getExpenseAmount ();
					count ++;
					currentExpense = filteredExpenses [count];
				}
				
				System.out.println ("Total of " + chosenCategory + ": £" + total);
				// Outputs the total amount from the chosen category
			}
			else
			{
				System.out.println ("No Expenses found in the system");
			}
		}
	}
	
	
	public boolean dislpayOptions ()
	// This method will display the options to the user and only stop looping if the user enters "e"
	{
		boolean continueLooping = true;
		boolean validInput = false;
		String strUserInput;
		int userInput = -1;
		
		System.out.println ("_________________________________________________________________________________\n"
				+ "Enter 1 to add an Expense to the system\n"
				+ "Enter 2 to view all Expenses\n"
				+ "Enter 3 to calculate the total of all Expenses\n"
				+ "Enter 4 to filter Expenses\n"
				+ "Enter e to exit\n"
				+ "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		// Outputs the options
		
		strUserInput = input.nextLine ();
		
		if (strUserInput.equalsIgnoreCase("e") == false)
		{
			while (!validInput)
			// Validation to ensure that the option is one of the provided options
			{
				try
				{
					userInput = Integer.parseInt (strUserInput);
					
					if (0 < userInput && userInput < 5)
					{
						validInput = true;
					}
					else
					{
						System.out.println ("Ensure you enter a number from the options provided");
						strUserInput = input.nextLine ();
					}
				}
				catch (Exception notInt)
				{
					//notInt.printStackTrace ();
					System.out.println ("Ensure you enter a number from the options provided");
					strUserInput = input.nextLine ();
				}
			}
			
			
		}
		else
		{
			continueLooping = false;
			System.out.println ("Exit successful");
		}
		
		if (continueLooping)
		{
			// Switch/Case to run the method that the user wanted to run
			switch (userInput)
			{
				case (1):
					//Add Expense to System
					addExpense ();									// COMPLETE
					break;
				case (2):
					//View all Expenses
					viewAllExpenses ();								// COMPLETE
					break;
				case (3):
					//Calculate total expense from Expenses
					calculatePrices ();								// COMPLETE
					break;
				case (4):
					//Filter Expenses
					filterExpenses ();								// COMPLETE
					break;
			}
		}
		
		return continueLooping;
		// Will return true if the user entered 1, 2, 3 or 4.  Will return false if the user entered "e"
	}
	
	public static void main (String [] args)
	// Main method which is where the system's execution will begin from
	{
		boolean loop = true;
		
		Main m = new Main ();
		
		m.readTextFile ();
		
		System.out.println (" __________________________________________________________\n"
							+ "|\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/|\n"
							+ "|              Welcome to the Expense Tracker              |\n"
							+ "|/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\|\n"
							+ " ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		
		while (loop)
		// This will infinitely loop unless the user entered "e" to exit
		{
			loop = m.dislpayOptions ();
		}
	}
}
