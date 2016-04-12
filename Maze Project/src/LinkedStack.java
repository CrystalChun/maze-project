import java.awt.Point;

public class LinkedStack 
{
	private Node first;
	
	public LinkedStack()
	{
		first = null;
	}
	
	public boolean isEmpty()
	{
		return first == null;
	}
	public int size()
	{
		int count = 0;
		if(isEmpty())
		{
			System.out.println("Nothing in stack");
			return 0;
		}
		else
		{
			Node n = first;
			while(n != first)
			{
				count ++;
				n = n.getNext();
			}
			return count;
		}
	}
	public void push(Point p)
	{
		if(isEmpty())
		{
			first = new Node (p);
		}
		else
		{
			first = new Node(p, first);
		}
	}
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
			first = first.getNext();
		}
		return p;
	}
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
	
	@Override
	public String toString()
	{
		String links = "";
		Node n = first;
		while(n != null)
		{
			links = links + "(" + n.getPoint().getX() + ", " + n.getPoint().getY() + ")\r\n";
			n = n.getNext();
		}
		return links;
	}
}
