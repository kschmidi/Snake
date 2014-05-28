package elementsOnPlayground.helpClasses;

import java.util.Random;

/**
 * Position:
 * <p>In this class can a position be desribed in a two dimensional space.
 * It cares a Random class, which is used in some other classes in the Snake project.
 * It implements Comparable to compare itself to another position, but it is only checked
 * if the position is on the same as the other position. Otherwise it returns -1.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class Position implements Comparable<Position>{

	public static Random	random	= new Random();

	private int				x, y;

	/**
	 * Constructor
	 * 
	 * @param x		x-position
	 * @param y		y-position
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x-position
	 * 
	 * @return x-position
	 */
	public int getX(){
		return x;
	}

	/**
	 * Sets the new x-position
	 * 
	 * @param x		the new x-position
	 */
	public void setX(int x){
		this.x = x;
	}

	/**
	 * Returns the y-position
	 * 
	 * @return y-position
	 */
	public int getY(){
		return y;
	}

	/**
	 * Sets the new y-position
	 * 
	 * @param y		the new y-position
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Prints the actual Position
	 */
	public void printPosition(){
		System.out.println("x=" + this.x + " y=" + this.y);
	}

	
	@Override
	public int compareTo(Position o){
		// is only needed to ckeck if they are on the same position
		// that means return 0;
		if(x == o.getX() && y == o.getY()){
			return 0;
		}
		return -1;
	}

}
