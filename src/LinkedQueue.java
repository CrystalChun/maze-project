import java.awt.Point;
/**
 * This class is a queue that is implemented with a linked list.
 * It holds the nodes in a queue and keeps track of the
 * first and last nodes. When a new node is added, it is added
 * to the end of the list, and when a node is removed, it's
 * the first one in the list that is removed.
 * @author Crystal Chun ID# 012680952
 *
 */
public class LinkedQueue 
{
	/**The first node in the queue*/
	private Node first;
	/**The last node in the queue*/
	private Node last;
	
	/**
	 * Constructs the queue by setting first and last to null
	 * indicating an empty queue
	 */
	public LinkedQueue()
	{
		first = null;
		last = null;
	}
	
	/**
	 * Tests if the queue is empty by seeing if
	 * first points to null. If it is, then it is empty,
	 * if not then the queue is not empty.
	 * @return A true if the list is empty (first points to null) or false
	 * if the list is not empty (first does not point to null).
	 */
	public boolean isEmpty()
	{
		return first == null;
	}
	
	/**
	 * Gets how many nodes are in the queue.
	 * @return An integer value of how many nodes are in this list.
	 */
	public int size()
	{
		int count = 0;
		
		//Tests if it's empty first, indicating size = 0
		if(isEmpty())
		{
			System.out.println("There is no one in the British line. Queue is empty.");
		}
		//If it's not empty, loops through list until the node is null
		else
		{
			Node n = first;
			
			
			while(n != null)
			{
				//Adds one to count and gets the next node in the queue
				count ++;
				n = n.getNext();
			}
		}
		return count; 
	}
	
	/**
	 * Adds a new node to the end of the list
	 * @param p The value held in the node being added
	 */
	public void add(Point p)
	{
		//Tests if the list is empty
		if(isEmpty())
		{
			//Constructs the new node and sets both first and last to the new node
			first = new Node(p);
			last = first;
		}
		//If the list is not empty, the new node is added to the end
		else
		{
			Node n = new Node(p);
			//Sets previous last to the new node then sets last to new node
			last.setNext(n);
			last = n;
		}
	}
	
	/**
	 * Removes the first node from the list
	 * @return The first node's value (Point) in the queue
	 */
	public Point remove()
	{
		Point p = null;
		
		//Tests to see if the queue is empty
		if(isEmpty())
		{
			System.out.println("British line is empty.");
		}
		//If not empty, gets the first node's value to return, sets first to node after the original first value
		else
		{
			p = first.getPoint();
			first = first.getNext();
		}
		return p;
	}
	
	/**
	 * Peeks at the first node in the queue, but does not remove it
	 * @return The point in the first node
	 */
	public Point peek()
	{
		Point p = null;
		
		//Checks for emptiness
		if(isEmpty())
		{
			System.out.println("The British line is empty.");
		}
		//If it's not empty, gets the value at the first node to return
		else
		{
			p = first.getPoint();
		}
		return p;
	}
	
	/**
	 * Turns the variables in this class into a string format for
	 * easy printing.
	 * @return The string version of this class
	 */
	@Override
	public String toString()
	{
		String s = "";
		Node n = first;
		
		//Loops through whole list and adds the points to the string variable
		while(n != null)
		{
			s = s + "(" + n.getPoint().getX() + ", " + n.getPoint().getY() + ")\r\n";
			n = n.getNext();
		}
		return s;
	}
}
