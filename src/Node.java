import java.awt.Point;
/**
 * This class is a node, which holds a point object
 * and the next node in the list.
 * @author Crystal Chun ID# 012680952
 *
 */
public class Node 
{
	private Point p;
	private Node next;
	
	public Node(Point point)
	{
		p = point;
		next = null;
	}
	
	public Node(Point point, Node n)
	{
		p = point;
		next = n;
	}
	
	public void setPoint(Point point)
	{
		p = point;
	}
	
	public void setNext(Node n)
	{
		next = n;
	}
	
	public Node getNext()
	{
		return next;
	}
	
	public Point getPoint()
	{
		return p;
	}
}
