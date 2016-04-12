import java.util.Scanner;
/**
 * This class is used to get a user's input. Its functions tests whether or not
 * the input is the correct data type and can be used to make sure
 * the user enters a valid choice.
 * @author Crystal Chun ID# 012680952
 *
 */
public class UserInput 
{
	/**
	 * Gets an integer value from the user's input. Keeps prompting the user to enter
	 * an integer until the user enters an integer.
	 * @return The integer the user entered.
	 */
	public static int getInt()
	{
		Scanner in = new Scanner(System.in);
		int input = -1;
		
		//The loop that keeps running until the user enters an integer
		while(input == -1)
		{
			//checks to see if the input is an integer, if it is, then assigns the input to the value entered
			if(in.hasNextInt())
			{
				input = in.nextInt();
			}
			//if not then tells the user to try again and clears the buffer
			else
			{
				System.out.println("Invalid input, try again");
				in.next();
			}
		}
		return input;
	}
	
	/**
	 * Overloaded getInt method that tests to see if the user's input is an integer
	 * and that their input is within the bounds.
	 * @param lowerBound The lowest number the user is allowed to enter
	 * @param upperBound The highest number the user is allowed to enter
	 * @return An integer value within the bounds passed in
	 */
	public static int getInt(int lowerBound, int upperBound)
	{
		Scanner in = new Scanner (System.in);
		int input = -1;
		
		//The loop that tests for input data type and checks the bounds
		while(input == -1)
		{
			//If it's an integer
			if(in.hasNextInt())
			{
				input = in.nextInt();
				//If the input is out of bounds (too big or too small) resets input to -1, tells user to try again, reruns loop
				if(input > upperBound || input < lowerBound)
				{
					input  = -1;
					System.out.println("Invalid choice, try again.");
				}
			}
			//Not an integer, tells user to try again, clears the buffer
			else
			{
				System.out.println("Invalid input, try again.");
				in.next();
			}
		}
		return input;
	}
	
	/**
	 * Gets a double (decimal) input from the user.
	 * @return The user's entered double value
	 */
	public static double getDecimal()
	{
		Scanner in = new Scanner(System.in);
		double input = -1.0;
		
		//loop while the user hasn't entered the correct input
		while(input == -1.0)
		{
			if(in.hasNextDouble())
			{
				input = in.nextDouble();
			}
			else
			{
				System.out.println("Invalid input, try again.");
				in.next();
			}
		}
		return input;
	}
	
	/**
	 * Overloaded method for getting a decimal value that tests to make sure
	 * the decimal value is within a specified range.
	 * @param lowerBound The lowest number the user can enter
	 * @param upperBound The highest number the user can enter
	 * @return  The decimal number within a specified range that the user entered
	 */
	public static double getDecimal(double lowerBound, double upperBound)
	{
		Scanner in = new Scanner(System.in);
		double input = -1.0;
		
		//loop that keeps running while the user hasn't entered the correct value in range
		while(input == -1.0)
		{
			if(in.hasNextDouble())
			{
				input = in.nextDouble();
				if(input < lowerBound || input > upperBound)
				{
					input = -1.0;
					System.out.println("Invalid choice, try again.");
				}
			}
			else
			{
				System.out.println("Invalid input, try again.");
				in.next();
			}
		}
		return input;
	}
}
