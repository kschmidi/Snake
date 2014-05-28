package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import elementsOnPlayground.Food;
import elementsOnPlayground.Snake;
import elementsOnPlayground.helpClasses.Position;

/**
 * Playground:
 * <p>The playground draws the snake and the food.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class Playground extends JLabel{
	private static final long	serialVersionUID	= 7089303919576346584L;

	public static int			PLAYGROUND_WIDTH	= 300;
	public static int			PLAYGROUND_HEIGHT	= 300;

	private Snake				snake;
	private Food				food;

	/**
	 * Constructor
	 * 
	 * @param snake		Snake
	 * @param food		Food
	 */
	public Playground(Snake snake, Food food){
		// Sets the attributes
		this.snake = snake;
		this.food = food;
	}

	/**
	 * Sets the bounds of the playground. If setBounds from the superclass is used
	 * the bounding wouldn't be right. The playground has to be bigger so the snake 
	 * fits in it and doesn't walks on the egde of the playground.
	 * 
	 * @param x		x-position of the Playground
	 * @param y		y-position of the playground
	 */
	public void setBounds(int x, int y){
		/*
		 * The playground has to be bigger because the painting of the 
		 * playgroun boundary needs 1 pixel space, so its made bigger by two
		 * on each way. 
		 */
		super.setBounds(x - 1, y - 1, PLAYGROUND_WIDTH + 2, PLAYGROUND_HEIGHT + 2);
	}

	@Override
	public void paint(Graphics g){
		// Calls the paint method of the superclass
		super.paint(g);

		// Paint the playground boundary
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

		// Paint food
		g.setColor(Color.GREEN);
		g.fillOval(food.getPosition().getX(), food.getPosition().getY(), Snake.SNAKE_X_SIZE,
				Snake.SNAKE_Y_SIZE);
		g.setColor(Color.BLACK);
		g.drawOval(food.getPosition().getX(), food.getPosition().getY(), Snake.SNAKE_X_SIZE,
				Snake.SNAKE_Y_SIZE);

		// Paint snake
		for(Position pos : snake.getSnakePosition()){
			g.setColor(Color.BLUE);
			g.fillRect(pos.getX(), pos.getY(), Snake.SNAKE_X_SIZE, Snake.SNAKE_Y_SIZE);
			g.setColor(Color.BLACK);
			g.drawRect(pos.getX(), pos.getY(), Snake.SNAKE_X_SIZE, Snake.SNAKE_Y_SIZE);
		}
	}

	/**
	 * If a new snake is created the playground need the new snake and food.
	 * 
	 * @param snake			The new snake
	 * @param food			The new Food
	 */
	public void giveNewElements(Snake snake, Food food){
		this.snake = snake;
		this.food = food;
	}
}
