import java.awt.Point;
/**
 * This is a stack that's implemented with a 
 * LinkedList. The stack class keeps track of 
 * the first node and has functions to
 * manipulate the stack. It adds to the top and
 * removes from the top. Last in, first out.
 * @author Crystal Chun
 *
 */
public class LinkedStack 
{
	/**The first node in the stack*/
	private Node first;
	
	/**
	 * Constructs the stack by initializing
	 * the first node to null, indicating
	 * the stack is empty.
	 */
	public LinkedStack()
	{
		first = null;
	}
	
	/**
	 * Checks whether there is anything in the
	 * stack by testing if the first node is equal
	 * to null. 
	 * @return True if the stack is empty or false if the stack 
	 * is not empty
	 */
	public boolean isEmpty()
	{
		return first == null;
	}
	
	/**
	 * Counts how many nodes are in the stack and returns
	 * the count.
	 * @return The number of nodes in the stack
	 */
	public int size()
	{
		int count = 0;
		
		//Tests if the stack is empty
		if(isEmpty())
		{
			System.out.println("Nothing in stack");
			return 0;
		}
		//If it's not empty, iterates through nodes, adding one to count for each node, until it reaches null (end)
		else
		{
			Node n = first;
			while(n != null)
			{
				count ++;
				n = n.getNext();
			}
			return count;
		}
	}
	
	/**
	 * Constructs a new node and pushes a point onto the top of the stack (first position)
	 * @param p The point to be added to the stack
	 */
	public void push(Point p)
	{
		if(isEmpty())
		{
			first = new Node (p);
		}
		else
		{
			//sets the next value of the new node to the previous first node then sets the first node to the new node
			first = new Node(p, first);
		}
	}
	
	/**
	 * Removes the top (first) node of the stack
	 * @return The top (first) node that was removed
	 */
	public Point pop()
	{
		Point p = null;
		
		if(isEmpty())
		{
			System.out.println("There is nothing in this stack.");
		}
		else
		{
			p = first.getPoint();
			//sets first node to the one after the original first
			first = first.getNext();
		}
		return p;
	}
	
	/**
	 * Views the value in the first (top) node of the stack,
	 * and does not remove the first node.
	 * @return The point in the first (top) node
	 */
	public Point peek()
	{
		Point p = null;
		
		if(isEmpty())
		{
			System.out.println("There is nothing in this stack.");
		}
		else
		{
			p = first.getPoint();
		}
		return p;
	}
	
	/**
	 * Constructs the string representation of this class, which
	 * are the points printed in order from top of stack to bottom.
	 * @return The string representation of the stack
	 */
	@Override
	public String toString()
	{
		String links = "";
		Node n = first;
		
		while(n != null)
		{
			//prints each point on a separate line in the format: (x, y) 
			links = links + "(" + n.getPoint().getX() + ", " + n.getPoint().getY() + ")\r\n";
			n = n.getNext();
		}
		
		return links;
	}
}
