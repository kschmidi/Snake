package elementsOnPlayground;

import elementsOnPlayground.helpClasses.Position;
import gui.Playground;

/**
 * Food:
 * <p>This class is the food which the snake can eat. Its position is randomly on the playground
 * and then checked if its spawned in the snake. if so it is replaced on a new random position.
 * Also is the position set in the raster, so its correctly positioned for the snake to eat.
 * <p>The Food class only stores its position and is drawn by the playground
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class Food{

	private Position	position;

	/**
	 * Constructor
	 * 
	 * @param position		The startposition
	 */
	public Food(Position position){
		this.position = position;
		this.setFoodOnRightPosition();
	}

	/**
	 * Repositions the food and sets it correctly in the raster
	 */
	public void reposition(){
		this.position.setX(Position.random.nextInt(Playground.PLAYGROUND_WIDTH));
		this.position.setY(Position.random.nextInt(Playground.PLAYGROUND_HEIGHT));
		this.setFoodOnRightPosition();
	}

	/**
	 * Returns the Class Position of the Food
	 * 
	 * @return The position
	 */
	public Position getPosition(){
		return this.position;
	}

	/**
	 * Checks if the position is in the raster. If not it shifts the position
	 * slowly into the raster.
	 */
	private void setFoodOnRightPosition(){
		// Gets the actual x/y position to simplify the calculates
		int x = this.position.getX();
		int y = this.position.getY();

		// Checks if the x allignment is right, if not it is shifted to
		// the right position
		if(x % Snake.SNAKE_X_SIZE > 5){
			// Shifts it right
			while(!(x % Snake.SNAKE_X_SIZE == 0)){
				x++;
			}
		}
		else{
			// Shifts it left
			while(!(x % Snake.SNAKE_X_SIZE == 0)){
				x--;
			}
		}

		// Checks if the y allignment is right, if not it is shifted to
		// the right position
		if(y % Snake.SNAKE_Y_SIZE > 5){
			// Shifts it down 
			while(!(y % Snake.SNAKE_Y_SIZE == 0)){
				y++;
			}
		}
		else{
			// Shifts it up
			while(!(y % Snake.SNAKE_Y_SIZE == 0)){
				y--;
			}
		}

		/*
		 * Checks if the Food is inside of the Playground. If not it is shifted back
		 * in the playground
		 */
		if(x > (Playground.PLAYGROUND_WIDTH - Snake.SNAKE_X_SIZE)){
			x = Playground.PLAYGROUND_WIDTH - Snake.SNAKE_X_SIZE;
		}
		if(y > (Playground.PLAYGROUND_HEIGHT - Snake.SNAKE_Y_SIZE)){
			y = Playground.PLAYGROUND_HEIGHT - Snake.SNAKE_Y_SIZE;
		}

		// last shift to fit it in the raster
		this.position.setX(++x);
		this.position.setY(++y);
	}
}
