import java.util.*;
import java.awt.Point;
import java.io.*;
/**
 * This class interacts with the user, allowing them to choose a maze and then
 * choose a solver (either BFS or DFS) for the maze, or to solve it themselves.
 * @author Crystal Chun ID# 012680952
 *
 */
public class MazeSolver 
{
	public static void main(String[] args) 
	{	
		boolean userPlaying = true;
		
		//Loop that runs while the user still wants to play
		while(userPlaying)
		{
			//Reads in each maze and stores them each time so it will "clear" the solved maze
			char [][] maze0 = readAndMaze("Maze-Level0.txt");
			char [][] maze1 = readAndMaze("Maze-Level1.txt");
			char [][] maze2 = readAndMaze("Maze-Level2.txt");
			char [][] maze3 = readAndMaze("Maze-Level3.txt");
			
			
			int userMaze = chooseMazeMenu();
			int solver = 0;
			
			//If the user chooses option 5, it means they want to exit the maze
			if(userMaze != 5)
			{
				solver = chooseSolver();
			}
			
			//Switches the maze choice and then goes to the user's solver choice (individual methods)
			switch(userMaze)
			{
				case 1:		//Level 0 maze
							if(solver == 1)
							{
								solverDFS(maze0);
							}
							else if(solver == 2)
							{
								solverBFS(maze0);
							}
							else
							{
								userSolving(maze0);
							}
							break;
							
				case 2:		//Level 1 maze
							if(solver == 1)
							{
								printMaze(maze1);
								solverDFS(maze1);
							}
							else if(solver == 2)
							{
								solverBFS(maze1);
							}
							else
							{
								userSolving(maze1);
							}
							break;
							
				case 3:		//Level 2 maze
							if(solver == 1)
							{
								solverDFS(maze2);
							}
							else if(solver == 2)
							{
								solverBFS(maze2);
							}
							else
							{
								userSolving(maze2);
							}
							break;
							
				case 4:		//Level 3 maze
							if(solver == 1)
							{
								solverDFS(maze3);
							}
							else if(solver == 2)
							{
								solverBFS(maze3);
							}
							else
							{
								userSolving(maze3);
							}
							break;
							
				case 5: 	//Leaving the game
							System.out.println("Escaping. . .");
							userPlaying = false;
							break;
			}
		}
	}
	
	/**
	 * Displays the maze levels and allows the user to choose a maze or quit.
	 * @return The user's maze choice (an integer value)
	 */
	public static int chooseMazeMenu()
	{
		System.out.println("What maze level would you like to solve?"
				+ "\r\n    1. Level 1"
				+ "\r\n    2. Level 2"
				+ "\r\n    3. Level 3"
				+ "\r\n    4. Level 4"
				+ "\r\n    5. Quit");
		return UserInput.getInt(1, 5);
	}
	
	/**
	 * Displays the solver choices for the user and allows them to choose 
	 * DFS (uses stacks), BFS (uses queues), or to solve it themselves (with
	 * stacks to hold their choices).
	 * @return The solver the uses chooses (an integer value)
	 */
	public static int chooseSolver()
	{
		System.out.println("Which solver would you like to use?"
				+ "\r\n    1. Depth First Search (DFS)"
				+ "\r\n    2. Breadth First Search (BFS)"
				+ "\r\n    3. I'll solve it myself (ISIM)");
		return UserInput.getInt(1, 3);
	}
	
	/**
	 * Reads in the text files for a maze and stores each character into a 
	 * 2d array.
	 * @param fileName The file where the maze is read from.
	 * @return The 2d array that holds the maze.
	 */
	public static char[][] readAndMaze(String fileName)
	{
		//Opens the text file and stores it into the array
		try
		{
			Scanner read = new Scanner(new File(fileName));
			
			int height = read.nextInt(); //The amount of lines top to bottom y value)
			int width = read.nextInt(); //How many columns width, x value)

			char [][] maze = new char [width][height];

			//Reads in each line and stores each individual character into the array
			read.nextLine();
			for(int thisLine = 0; thisLine < height; thisLine ++) //y value
			{
				String line = read.nextLine();
				for(int eachChar = 0; eachChar < width; eachChar ++) //x value
				{
					maze[eachChar][thisLine] = line.charAt(eachChar);
				}
			}
			read.close();
			return maze;
		}
		//If the file is not found, it returns null and tells the user that file isn't found
		catch(FileNotFoundException e)
		{
			System.out.println("File does not exist.");
			return null;
		}
	}
	
	/**
	 * If the user chooses to solve using BFS (uses queue), this method
	 * solves the maze taking in the maze and by storing each point
	 * where you can move in the maze (empty space) into the stack and
	 * testing the four spots around that spot until the finish is found.
	 * @param maze The maze the user wants to solve.
	 */
	public static void solverBFS(char [][] maze)
	{
		int x = 0;
		int y = 0;
		
		//Goes through the array and finds the starting point
		for(int i = 0; i < maze[0].length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[j][i] == 's')
				{
					x = j;
					y = i;
				}
			}
		}
		
		//Initialize the queue and adds the starting point into the queue
		LinkedQueue queue = new LinkedQueue();
		queue.add(new Point(x, y));
		
		//Loop while the finish hasn't been found
		boolean solving = true;
		boolean start = true;
		while(solving)
		{
			//Removes the first queue in line and holds in a temporary variable
			Point temp = queue.remove();
			
			//Tests whether that point is the finish, if it is then exits the solving loop
			if(maze[(int) temp.getX()][(int) temp.getY()] == 'f')
			{
				solving = false;
			}
			//Otherwise, marks that position with a '.' (if not starting point) in the array to show path traveled
			else
			{
				if(start)
				{
					start = false;
				}
				else
				{
					maze[(int) temp.getX()][(int) temp.getY()] = '.';
				}
	
				//Tests for bounds of the current x-value
				if(temp.getX() == 0)
				{
					/*
					 * Tests the position after the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the queue if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's')
					{
						queue.add(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
				}
				else if(temp.getX() == maze.length - 1)
				{
					/*
					 * Tests the position before the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the queue if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						queue.add(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				//If the x value is not on the bounds, tests points to right and left of current position
				else
				{
					//To the right
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's')
					{
						//Adds position to queue if not a wall and haven't been there
						queue.add(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
					//To the left
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						//Adds position to queue if not a wall and haven't been there
						queue.add(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				
				//Tests the current y-value's bounds
				if(temp.getY() == 0)
				{
					/*
					 * Tests the position below the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the queue if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						queue.add(new Point((int) temp.getX(), ((int) temp.getY() + 1)));
					}
				}
				else if(temp.getY() == maze[0].length - 1)
				{
					/*
					 * Tests the position above the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the queue if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						queue.add(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
				//Otherwise the y-value exists somewhere in the middle, so it can test the points above and below it
				else
				{
					//Below
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						//Adds the position onto the queue if not a wall and haven't been there already
						queue.add(new Point((int)temp.getX(), ((int) temp.getY()+ 1)));
					}
					//Above
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						//Adds the position onto the queue if not a wall and haven't been there already
						queue.add(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
			}
		}
		printMaze(maze);
	}
	
	/**
	 * Solves the maze by the DFS method where each point is stored in a stack.
	 * It tests the points around that point until the finish is found.
	 * @param maze The maze that the user wants to solve.
	 */
	public static void solverDFS(char [][] maze)
	{
		int x = 0;
		int y = 0;
		
		//Goes through the maze to find the starting point
		for(int i = 0; i < maze[0].length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[j][i] == 's')
				{
					x = j;
					y = i;
				}
			}
		}
		
		//Creates a new linked stack and pushes the starting point into the stack
		LinkedStack stack = new LinkedStack();
		stack.push(new Point(x, y));
		
		//Loop while the program hasn't found the starting point
		boolean solving = true;
		boolean start = true;
		while(solving)
		{
			//Takes the point off the top and holds it in a temporary variable
			Point temp = stack.pop();
			
			//Tests the current point to see if it's the finish, if it is, exits the loop
			if(maze[(int) temp.getX()][(int) temp.getY()] == 'f')
			{
				solving = false;
			}
			//Otherwise, marks the point with a '.' if not start and tests the points around it 
			else
			{
				if(start)
				{
					start = false;
				}
				else
				{
					maze[(int) temp.getX()][(int) temp.getY()] = '.';
				}
				
				
				//Tests x value for bounds
				if(temp.getX() == 0)
				{
					/*
					 * Tests the position after the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's')
					{
						stack.push(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
				}
				else if(temp.getX() == maze.length - 1)
				{
					/*
					 * Tests the position before the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						stack.push(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				//Otherwise the x-value is in the middle and both points to the right and to the left can be tested
				else
				{
					//To the right
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
					//To the left
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				
				//Tests the y-values for bounds
				if(temp.getY() == 0)
				{
					/*
					 * Tests the position below the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						stack.push(new Point((int) temp.getX(), ((int) temp.getY()+ 1)));
					}
				}
				else if(temp.getY() == maze[0].length - 1)
				{
					/*
					 * Tests the position above the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						stack.push(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
				//Otherwise the y value is not on the edge and both the position below and above the current position can be tested
				else
				{
					//Below
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point((int) temp.getX(), ((int) temp.getY()+ 1)));
					}
					//Above
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
			}
		}
		printMaze(maze);
	}
	
	/**
	 * Prints out the full maze
	 * @param maze The specified maze that needs to be printed out
	 */
	public static void printMaze(char [][] maze)
	{
		System.out.println();
		//Loops through each character and prints it out row by row
		for(int i = 0; i < maze[0].length; i++) //Y value
		{
			for (int j = 0; j < maze.length; j++) //X value
			{
				System.out.print(maze[j][i]);
			}
			System.out.println();
		}
	}
	
	/**
	 * The method if the user decides to solve a maze on their own.
	 * Uses stacks to hold the points.
	 * @param maze The maze the user wants to solve.
	 */
	public static void userSolving(char [][] maze)
	{
		int x = 0;
		int y = 0;
		
		//Finds the starting point of the maze
		for(int i = 0; i < maze[0].length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[j][i] == 's')
				{
					x = j;
					y = i;
				}
			}
		}
		
		//Creates a new linked stack and pushes the starting point on the maze
		LinkedStack stack = new LinkedStack();
		stack.push(new Point(x, y));
		
		//Lets the user see where they are
		printMaze(maze);
		System.out.println("You are now at the start, marked with an \"s\".");
		
		boolean userSolving = true;
		boolean start = true;
		
		//Loop while the user hasn't finished or quit the maze
		while(userSolving)
		{
			//Gets the point at the top of the stack
			Point currentPosition = stack.peek();
			
			//Finds out how the user wants to move by displaying the menu
			int move = userSolveMenu();
			
			//Switches their move
			switch(move)
			{
				case 1:		//Move to the left
							//Tests for a wall to the left of the current position
							if(maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] == '*')
							{
								//If there is a wall, tells the user and reruns loop
								System.out.println("A wall stands in your way. Choose another path.");
							}
							
							//Tests if the position the user wants to move to is on the boundary (can't move left anymore)
							else if(currentPosition.getX() == 0)
							{
								//Tells the user they can't leave the maze and reruns loop
								System.out.println("Leaving the maze so soon? (Just kidding) Choose another way.");
							}
							
							//Tests if the user has already been to the position the user wants to move to
							else if(maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] == '.')
							{
								//If it is, then removes the current position and adds the previous position onto the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() - 1, (int) currentPosition.getY()));
								
								//Marks the position the user moved to with a snowman (indicating current position)
								maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] = '☃';
								
								//Marks the previous current position with an empty space
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								printMaze(maze);
							}
							
							//Tests if the user moved back to the start
							else if(maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] == 's')
							{
								//Removes the current position and adds the starting position back to stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() - 1, (int) currentPosition.getY()));
								
								//Marks the previous current position with an empty space
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								//Resets the boolean that indicates the user is at the start to true
								start = true;
								
								printMaze(maze);
								System.out.println("Well, looks like you're right back where you started.");
							}
							
							//Tests if the user is moving to the finish of the maze
							else if(maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] == 'f')
							{
								//Exits the loop
								userSolving = false;
								
								//Marks the position before finish with a dot
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								
								printMaze(maze);
								System.out.println("You've escaped the maze! Congrats!");
							}
							
							//Otherwise the user is moving to an empty space
							else
							{
								//Removes the current position and adds the new position (left of current) to the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() - 1, (int) currentPosition.getY()));
								
								//Marks the new current position with a snowman
								maze[(int) currentPosition.getX() - 1][(int) currentPosition.getY()] = '☃';
								
								//Tests whether the user moved from start
								if(start)
								{
									//If they did, doesn't change the character at the start position
									start = false;
								}
								//If it's not the start, marks the old current position (that just popped off stack)
								else
								{
									maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								}
								
								printMaze(maze);
							}
							break;
							
				case 2:		//Move to the right
							//Tests the position to the right of the current position for a wall
							if(maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] == '*')
							{
								System.out.println("A wall stands in your way. Choose another path.");
							}
							
							//Tests if the current position is on the right-most bounds
							else if(currentPosition.getX() == maze.length)
							{
								System.out.println("Leaving the maze so soon? (Just kidding) Choose another way.");
							}
							
							//Tests if the position to the right of the current position has already been traveled
							else if(maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] == '.')
							{
								//Removes the current position from the stack and adds the position to the right of it to the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() + 1, (int) currentPosition.getY()));
								
								//Puts a snowman at the new current position and places an empty space at the previous current position
								maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] = '☃';
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								printMaze(maze);
							}
							
							//Tests if the position to the right of the current position is the start
							else if(maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] == 's')
							{
								//Removes the current position and pushes the start position onto the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() + 1, (int) currentPosition.getY()));
								
								//Places a space at the previous current position and resets the start boolean to true
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								start = true;
								
								printMaze(maze);
								System.out.println("Well, looks like you're right back where you started.");
							}
							
							//Tests if the position to the right is the finish
							else if(maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] == 'f')
							{
								//Exits the loop if it is and places a dot at the position to the left of finish
								userSolving = false;
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								
								printMaze(maze);
								System.out.println("You've escaped the maze! Congrats!");
							}
							
							//Otherwise the position to the right is empty and the user can freely move there
							else
							{
								//Removes the current position from the stack and pushes the new position onto the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX() + 1, (int) currentPosition.getY()));
								
								//Marks the new position with a snowman indicating that it's where the user is in the maze
								maze[(int) currentPosition.getX() + 1][(int) currentPosition.getY()] = '☃';
								
								//Tests if the old current position was the beginning of the maze
								if(start)
								{
									//If it was, then sets boolean start to false indicating the user is no longer moving from the start
									start = false;
								}
								//Otherwise marks the previous current position with a dot indicating path traveled
								else
								{
									maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								}
								
								printMaze(maze);
							}
							break;
							
				case 3:		//Move up (y-value minus one)
							//Tests if the position above the current position is a wall
							if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] == '*')
							{
								System.out.println("A wall stands in your way. Choose another path.");
							}
							
							//Tests if the position is on the upper most bounds (indicating that they can't move up)
							else if(currentPosition.getY() == 0)
							{
								System.out.println("Leaving the maze so soon? (Just kidding) Choose another way.");
							}
							
							//Tests if the position above the current position has already been traveled
							else if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] == '.')
							{
								//Removes the current position and adds the new position to the stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX(), (int) currentPosition.getY() - 1));
								
								//Marks the new current position with a snowman and the previous current position with an empty space
								maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] = '☃';
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								printMaze(maze);
							}
							
							//Tests if the position above the current position is the start
							else if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] == 's')
							{
								//If it is, then removes current position from stack and adds start position to stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX(), (int) currentPosition.getY() - 1));
								
								//Marks the previous position with an empty space
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								//Changes start variable to true indicating user is at starting position
								start = true;
								
								printMaze(maze);
								System.out.println("Well, looks like you're right back where you started.");
							}
							
							//Tests if the position above the current position is the finish
							else if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] == 'f')
							{
								//Triggers to exit loop and mark current position (below finish) with a dot
								userSolving = false;
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								
								printMaze(maze);
								System.out.println("You've escaped the maze! Congrats!");
							}
							
							//Otherwise the position above the current position is free to move to
							else
							{
								//Removes current position from stack and adds new position to stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX(), (int) currentPosition.getY() - 1));
								
								//Marks new current position with a snowman indicating the user's position in the maze
								maze[(int) currentPosition.getX()][(int) currentPosition.getY() - 1] = '☃';
								
								//Tests whether or not the user just moved from start
								if(start)
								{
									start = false;
								}
								//If they did not, then marks previous current position with a dot
								else
								{
									maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								}
								
								printMaze(maze);
							}
							break;
							
				case 4:		//Move down (y-value plus one)
							//Tests position below current position to see if there's a wall
							if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] == '*')
							{
								System.out.println("A wall stands in your way. Choose another path.");
							}
							
							//Tests to see if current position is at very bottom of maze, indicating the user can't move down
							else if(currentPosition.getY() == maze[0].length)
							{
								System.out.println("Leaving the maze so soon? (Just kidding) Choose another way.");
							}
							
							//Tests if position below current position has already been traveled
							else if( maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] == '.')
							{
								//Removes current position from stack, adds new position to stack and marks with snowman
								stack.pop();
								stack.push(new Point((int) currentPosition.getX(), (int) currentPosition.getY() - 1));
								maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] = '☃';
								
								//Marks old current position with an empty space
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								printMaze(maze);
							}
							
							//Tests if position below current position is the start
							else if( maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] == 's')
							{
								//Removes current position from stack and removes character from that position, adds new position to stack
								stack.pop();
								stack.push(new Point((int) currentPosition.getX(), (int) currentPosition.getY() + 1));
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = ' ';
								
								start = true;
								printMaze(maze);
								System.out.println("Well, looks like you're right back where you started.");
							}
							
							//Tests if the position below the current position is the finish
							else if(maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] == 'f')
							{
								//Exits loop, marks current position above finish with a dot
								userSolving = false;
								maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								
								printMaze(maze);
								System.out.println("You've escaped the maze! Congrats!");
							}
							
							//Otherwise the position below the current position is empty and the user is free to move to it
							else
							{
								//Removes current position from stack, adds new position to stack and marks with snowman
								stack.pop();
								stack.push(new Point((int)currentPosition.getX(), (int)currentPosition.getY() + 1));
								maze[(int) currentPosition.getX()][(int) currentPosition.getY() + 1] = '☃';
								
								//Tests for moving from start
								if(start)
								{
									start = false;
								}
								//Not start, mark with dot (path traveled)
								else
								{
									maze[(int) currentPosition.getX()][(int) currentPosition.getY()] = '.';
								}
								
								printMaze(maze);
							}
							break;
							
				case 5: 	//The symbols and their meanings are displayed
							symbolMeaning();
							break;
							
				case 6:		//The user gave up, so the computer solves it from where the user left off using DFS
							userSolving = false;
							Point leftOff = stack.peek();
							solverDFS(maze, leftOff);
							break;
			}
		}
	}
	
	/**
	 * Displays the menu of the directions the user can choose to move in the maze if they
	 * decide to solve the maze themselves. Also lets them choose to see what the symbols
	 * mean or to quit.
	 * @return The user's direction to move in the maze or the other choices in the menu (int)
	 */
	public static int userSolveMenu()
	{
		System.out.println("Which way would you like to move?"
				+ "\r\n    1. ☜"
				+ "\r\n    2. ☞"
				+ "\r\n    3. ☝"
				+ "\r\n    4. ☟"
				+ "\r\n    5. Help!! What do these symbols mean?!?"
				+ "\r\n    6. Quit");
		
		return UserInput.getInt(1, 6);
	}
	
	/**
	 * Displays the meaning of the symbols in the maze to the user
	 */
	public static void symbolMeaning()
	{
		System.out.println("      +---------------------------------------------+"
				    + "\r\n        Symbol                 Meaning               " 
				    + "\r\n          ☃        You (current position)           "
					+ "\r\n          .         The path you've traveled         "
					+ "\r\n          s         The start of the maze            "
					+ "\r\n          f         The finish/end of the maze       "
					+ "\r\n          *         A wall                           "
					+ "\r\n           (Space)  Empty space, you can move here   "
					+ "\r\n         ☜         Left (move direction)            "
					+ "\r\n         ☞         Right (move direction)           "
					+ "\r\n         ☝         Up (move direction)              "
					+ "\r\n         ☟         Down (move direction)            "
					+ "\r\n      +---------------------------------------------+");
	}
	
	/**
	 * Overloaded method for solving the maze using DFS, except this happens
	 * when the user decides to solve it themselves and then they quit before
	 * they finish solving the maze. This method solves the maze using DFS
	 * and takes in an extra parameter (the point where the user left off)
	 * and marks it with the snowman symbol to show where they were in the 
	 * maze.
	 * @param maze The maze the user wants to solve
	 * @param whereUserLeftOff The point where the user left off while they tried
	 * to solve the maze themselves.
	 */
	public static void solverDFS(char [][] maze, Point whereUserLeftOff)
	{	
		int x = 0;
		int y = 0;
		
		//Goes through the maze to find the starting point
		for(int i = 0; i < maze[0].length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[j][i] == 's')
				{
					x = j;
					y = i;
				}
			}
		}
		
		//Creates a new linked stack and pushes the starting point into the stack
		LinkedStack stack = new LinkedStack();
		stack.push(new Point(x, y));
		
		//Loop while the program hasn't found the ending point
		boolean solving = true;
		boolean start = true;
		while(solving)
		{
			//Takes the point off the top and holds it in a temporary variable
			Point temp = stack.pop();
			
			//Tests the current point to see if it's the finish, if it is, exits the loop
			if(maze[(int) temp.getX()][(int) temp.getY()] == 'f')
			{
				solving = false;
			}
			//Otherwise, marks the point with a '.' if not start and tests the points around it 
			else
			{
				if(start)
				{
					start = false;
				}
				else
				{
					maze[(int) temp.getX()][(int) temp.getY()] = '.';
				}
				
				//Tests x value for bounds
				if(temp.getX() == 0)
				{
					/*
					 * Tests the position after the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.' 
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's' )
					{
						stack.push(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
				}
				else if(temp.getX() == maze.length - 1)
				{
					/*
					 * Tests the position before the current position (along the x-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						stack.push(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				//Otherwise the x-value is in the middle and both points to the right and to the left can be tested
				else
				{
					//To the right
					if(maze[(int) temp.getX() + 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() + 1][(int) temp.getY()] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point(((int) temp.getX() + 1), (int) temp.getY()));
					}
					//To the left
					if(maze[(int) temp.getX() - 1][(int) temp.getY()] != '*' 
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != '.'
						&& maze[(int) temp.getX() - 1][(int) temp.getY()] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point(((int) temp.getX() - 1), (int) temp.getY()));
					}
				}
				
				//Tests the y-values for bounds
				if(temp.getY() == 0)
				{
					/*
					 * Tests the position below the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						stack.push(new Point((int) temp.getX(), ((int) temp.getY() + 1)));
					}
				}
				else if(temp.getY() == maze[0].length - 1)
				{
					/*
					 * Tests the position above the current position (along the y-axis) only,
					 * since it's on the outer edge. Adds the position onto the stack if 
					 * the computer can move there (not a wall and hasn't been there already).
					 */
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						stack.push(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
				//Otherwise the y value is not on the edge and both the position below and above the current position can be tested
				else
				{
					//Below
					if(maze[(int) temp.getX()][(int) temp.getY() + 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() + 1] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point((int) temp.getX(), ((int) temp.getY()+ 1)));
					}
					//Above
					if(maze[(int) temp.getX()][(int) temp.getY() - 1] != '*' 
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != '.'
						&& maze[(int) temp.getX()][(int) temp.getY() - 1] != 's')
					{
						//Adds the position onto the stack if not a wall and haven't been there already
						stack.push(new Point((int) temp.getX(), (int) temp.getY() - 1));
					}
				}
			}
		}
		
		//Marks the spot in the maze where the user left
		maze[(int) whereUserLeftOff.getX()][(int) whereUserLeftOff.getY()] = '☃';
		
		printMaze(maze);
	}
}
