import java.awt.Point;

/**
 * This class is a node, which holds a point object
 * and the next node in the list.
 * @author Crystal Chun ID# 012680952
 *
 */
public class Node 
{
	/**The point to be stored in this node*/
	private Point p;
	/**The next node*/
	private Node next;
	
	/**
	 * Constructs the node (specifically for the queue)
	 * by setting next to null and setting the point to the 
	 * passed in point parameter.
	 * @param point The point to be stored in this node
	 */
	public Node(Point point)
	{
		p = point;
		next = null;
	}
	
	/**
	 * Constructs the node (specifically for the stacks)
	 * by setting both next and point to the passed in 
	 * parameters.
	 * @param point The point to be stored in this node.
	 * @param n The next node
	 */
	public Node(Point point, Node n)
	{
		p = point;
		next = n;
	}
	
	/**
	 * Sets/changes the point stored in this node to
	 * the point passed in.
	 * @param point The point passed in
	 */
	public void setPoint(Point point)
	{
		p = point;
	}
	
	/**
	 * Sets/changes what this node points to next.
	 * @param n The next node
	 */
	public void setNext(Node n)
	{
		next = n;
	}
	
	/**
	 * Gets the next node after this node
	 * @return The next node
	 */
	public Node getNext()
	{
		return next;
	}
	
	/**
	 * Gets the point stored in this node
	 * @return The point in this node
	 */
	public Point getPoint()
	{
		return p;
	}
}
