package elementsOnPlayground;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import elementsOnPlayground.helpClasses.Difficulty;
import elementsOnPlayground.helpClasses.Direction;
import elementsOnPlayground.helpClasses.Position;
import gui.GUI;
import gui.Playground;
import gui.listener.MyKeyListener;

/**
 * Snake:
 * <p>The snake class stores the position of the snake, which is stringed together with
 * squares. The snake grows with every food it eats and can't walk outside of the field or 
 * into itself. It is drawn by the playground.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class Snake{
	public static final int		MIN_LENGTH		= 5;
	public static final int		SNAKE_X_SIZE	= 10;
	public static final int		SNAKE_Y_SIZE	= 10;

	private Food				food;
	private static Direction	direction;
	private static Difficulty	difficulty;

	private static boolean		isAlive;
	private static boolean		pause;
	private int					eatenScore		= 0;
	private boolean				ate;
	private ArrayList<Position>	snakePositionBody;

	/**
	 * Constructor
	 * 
	 * @param startPositionHead			Startposition of the snakes head
	 * @param food						Food
	 */
	public Snake(Position startPositionHead, Food food){
		// set the attributes
		this.food = food;
		Snake.direction = Direction.DOWN;
		Snake.difficulty = Difficulty.MEDIUM;
		Snake.isAlive = true;
		Snake.pause = true;
		this.ate = false;
		this.snakePositionBody = new ArrayList<>(MIN_LENGTH);

		// calcs the position of the snake and stores it in an ArrayList
		snakePositionBody.add(startPositionHead);
		for(int i = 1; i < MIN_LENGTH; i++){
			snakePositionBody.add(new Position(startPositionHead.getX(), startPositionHead.getY()
					- i * SNAKE_Y_SIZE));
		}

		// checks the position of the snake if its on the same position as the food
		checkFoodPosition();
	}

	/**
	 * Updates the snakes position and length if food is eaten.
	 * 
	 * @param e						Actionevent if Button is pressed
	 * @param myKeyListener			MyKeylistener
	 * @param scoreLabel			JLabel Score
	 */
	public void update(ActionEvent e, MyKeyListener myKeyListener, JLabel scoreLabel){

		// Checks if a Actionevent is thrown
		if(e != null){
			// Pause is pressed
			if(e.getActionCommand().contentEquals(GUI.STR_PAUSE)){
				Snake.setPause();
			}
			// Start is pressed
			if(e.getActionCommand().contentEquals(GUI.STR_START)){
				if(Snake.pause){
					Snake.pause = false;
				}
			}

			// Sets the difficulty
			if(e.getActionCommand().contentEquals(GUI.STR_EINFACH)){
				// Blocks to change the difficulty to a easyier state.
				// Only medium to easy allowed 
				if(Snake.difficulty == Difficulty.MEDIUM){
					Snake.setDifficulty(Difficulty.EASY);
				}
			}
			else if(e.getActionCommand().contentEquals(GUI.STR_MITTEL)){
				// Blocks to change the difficulty to a easyier state.
				if(!(Snake.difficulty.level < Difficulty.MEDIUM.level)){
					Snake.setDifficulty(Difficulty.MEDIUM);
				}
			}
			else if(e.getActionCommand().contentEquals(GUI.STR_SCHWER)){
				// Blocks to change the difficulty to a easyier state.
				if(!(Snake.difficulty.level < Difficulty.HARD.level)){
					Snake.setDifficulty(Difficulty.HARD);
				}
			}
			else if(e.getActionCommand().contentEquals(GUI.STR_SCHWERER)){
				// Blocks to change the difficulty to a easyier state.
				if(!(Snake.difficulty.level < Difficulty.VERY_HARD.level)){
					Snake.setDifficulty(Difficulty.VERY_HARD);
				}
			}
			else if(e.getActionCommand().contentEquals(GUI.STR_KRANK)){
				// Blocks to change the difficulty to a easyier state.
				if(!(Snake.difficulty.level < Difficulty.SICK.level)){
					Snake.setDifficulty(Difficulty.SICK);
				}
			}
		}

		if(isAlive && !Snake.pause){
			// Changes the direction to up 
			if(myKeyListener.isUp()){
				// Calculates the the new yPosition and stores it in y_new 
				int y_new;
				// If up is pushed while the snake down left,
				// it would check the position wrong
				if(Snake.direction == Direction.DOWN){
					y_new = getHead().getY() + SNAKE_Y_SIZE;
				}
				else{
					y_new = getHead().getY() - SNAKE_Y_SIZE;
				}
				
				// Calculates the edge position and stores it in y_edge
				int y_edge = Playground.PLAYGROUND_HEIGHT - SNAKE_X_SIZE + 1;

				// Check if snake ran in north playground boundary
				if(y_new < 1 || y_new > y_edge){
					isAlive = false;
					getHead().printPosition();
					System.out.println("Snake ran into the wall! :(");
					System.out.println("Snake is dead! :(");
					return;
				}

				// Blocks the movement of running against the actual direction
				if(Snake.direction != Direction.DOWN){
					// Sets the new direction of the snake
					direction = Direction.UP;
				}
			}

			// Changes the direction to down
			else if(myKeyListener.isDown()){
				// Calculates the the new yPosition and stores it in y_new 
				int y_new; 
				// If down is pushed while the snake runs up,
				// it would check the position wrong
				if(Snake.direction == Direction.UP){
					y_new = getHead().getY() - SNAKE_Y_SIZE;
				}else{
					y_new = getHead().getY() + SNAKE_Y_SIZE;
				}
				
				// Calculates the edge position and stores it in y_edge
				int y_edge = Playground.PLAYGROUND_HEIGHT - SNAKE_X_SIZE + 1;

				// Check if snake ran in south playground boundary
				if((y_new > y_edge || y_new < 1)){
					isAlive = false;
					getHead().printPosition();
					System.out.println("Snake ran into the wall! :(");
					System.out.println("Snake is dead! :(");
					return;
				}

				// Blocks the movement of running against the actual direction
				if(Snake.direction != Direction.UP){
					// Sets the new direction of the snake
					direction = Direction.DOWN;
				}
			}

			// Changes the direction to left 
			else if(myKeyListener.isLeft()){
				// Calculates the the new yPosition and stores it in x_new
				int x_new; 
				// If right is pushed while the snake runs left,
				// it would check the position wrong
				if(Snake.direction == Direction.RIGHT){
					x_new = getHead().getX() + SNAKE_X_SIZE;
				}else{
					x_new = getHead().getX() - SNAKE_X_SIZE;
				}
				// Calculates the edge position and stores it in x_edge
				int x_edge = Playground.PLAYGROUND_WIDTH - Snake.SNAKE_X_SIZE + 1;

				// Check if snake ran in west playground boundary
				if(x_new < 1 || x_new > x_edge){
					isAlive = false;
					getHead().printPosition();
					System.out.println("Snake ran into the wall! :(");
					System.out.println("Snake is dead! :(");
					return;
				}

				// Blocks the movement of running against the actual direction
				if(Snake.direction != Direction.RIGHT){
					// Sets the new direction of the snake
					direction = Direction.LEFT;
				}
			}

			// Changes the diretion to right
			else if(myKeyListener.isRight()){
				// Calculates the the new yPosition and stores it in x_new
				int x_new;
				// If right is pushed while the snake runs left,
				// it would check the position wrong
				if(Snake.direction == Direction.LEFT){
					x_new = getHead().getX() - SNAKE_X_SIZE;
				}else{
					x_new = getHead().getX() + SNAKE_X_SIZE;
				}
				// Calculates the edge position and stores it in x_edge
				int x_edge = Playground.PLAYGROUND_WIDTH - Snake.SNAKE_X_SIZE + 1;

				// Check if snake ran in east playground boundary
				if(x_new > x_edge || x_new < 1){
					isAlive = false;
					getHead().printPosition();
					System.out.println("Snake ran into the wall! :(");
					System.out.println("Snake is dead! :(");
					return;
				}

				// Blocks the movement of running against the actual direction
				if(Snake.direction != Direction.LEFT){
					// Sets the new direction of the snake
					direction = Direction.RIGHT;
				}
			}

			getHead().printPosition();

			calcSnakePosition();

			if(didSnakeWalkIntoItself()){
				isAlive = false;
				System.out.println("Snake is dead! :(");
				return;
			}

			checkFoodPosition();

			// If the snake eats food the score is raised up
			if(ate()){
				scoreLabel.setText("Score: " + this.eatenScore);
				System.out.println("Score: " + this.eatenScore);
			}
		}
	}

	/**
	 * Calculates the positions of the snakes Bodyparts. The head isn't already on 
	 * the right position. Only the direction maybe has changed.
	 * If the snake ate food the length will be raised.
	 */
	private void calcSnakePosition(){

		Position snakeHead = getHead();

		// Snake moves down
		if(direction == Direction.DOWN){
			snakePositionBody.add(0,
					new Position(snakeHead.getX(), snakeHead.getY() + SNAKE_Y_SIZE));
		}

		// Snake moves right
		else if(direction == Direction.RIGHT){
			snakePositionBody.add(0,
					new Position(snakeHead.getX() + SNAKE_X_SIZE, snakeHead.getY()));
		}

		// Snake moves up
		else if(direction == Direction.UP){
			snakePositionBody.add(0,
					new Position(snakeHead.getX(), snakeHead.getY() - SNAKE_Y_SIZE));
		}

		// Snake moves left
		else if(direction == Direction.LEFT){
			snakePositionBody.add(0,
					new Position(snakeHead.getX() - SNAKE_X_SIZE, snakeHead.getY()));
		}

		// if snake didn't eat food remove last element, if it ate food
		// don't remove it, so the length is raised up.
		if(!didSnakeEatFood()){
			snakePositionBody.remove(snakePositionBody.size() - 1);
		}
	}

	/**
	 * Checks if the food is on the same position as the snake. If so
	 * the food is randomly replaced until it isn't.
	 */
	private void checkFoodPosition(){
		for(int i = 0; i < snakePositionBody.size(); i++){
			if(food.getPosition().compareTo(snakePositionBody.get(i)) == 0){
				// if its on the same position start checking from the beginning
				i = 0;
				food.reposition();
			}
		}
	}

	/**
	 * Checks if the Snake walked into itself.
	 * 
	 * @return true if it ran into itself or 
	 * 		   <p>false if not
	 */
	public boolean didSnakeWalkIntoItself(){
		Position snakeHead = getHead();

		// Checks every bodypart if it has the same position as the head
		for(int i = 1; i < snakePositionBody.size(); i++){
			if(snakeHead.compareTo(snakePositionBody.get(i)) == 0){
				System.out.println("Snake ran into itself! :(");
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the snake ate food.
	 * 
	 * @return true if snake ate food or
	 * 		   <p>false if not
	 */
	public boolean didSnakeEatFood(){
		// Checks if the head of the snake is on the same position as the food
		if(getHead().compareTo(food.getPosition()) == 0){
			System.out.println("Eat food! yay! :)");
			System.out.println("Food position: x=" + food.getPosition().getX() + " y="
					+ food.getPosition().getY());

			// If the position is the same the food need to be replaced
			food.reposition();

			// Highers the score
			eatenScore++;

			ate = true;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of ate.
	 * 
	 * @return true if snake ate food or
	 * 		   <p>false if not
	 */
	private boolean ate(){
		if(ate){
			ate = false;
			return true;
		}
		return false;
	}

	/**
	 * Sets the snake on pause or on running if it was on pause.
	 */
	private static void setPause(){
		if(Snake.pause){
			Snake.pause = false;
		}
		else{
			Snake.pause = true;
		}
	}

	/**
	 * Returns if the snake is on pause.
	 * 
	 * @return true if the snake is on pause or
	 * 		   <p>false if not
	 */
	public static boolean isPause(){
		return Snake.pause;
	}

	/**
	 * Sets the snakes direction.
	 * 
	 * @param direction			The new direction of the snake
	 */
	public static void setDirection(Direction direction){
		Snake.direction = direction;
	}

	/**
	 * Returns the Direction of the snake.
	 * 
	 * @return The direction of the Snake
	 */
	public static Direction getDirection(){
		return Snake.direction;
	}

	/**
	 * Sets the difficulty of the snake.
	 * 
	 * @param difficulty		The new difficulty of the snake
	 */
	public static void setDifficulty(Difficulty difficulty){
		Snake.difficulty = difficulty;
	}

	/**
	 * Returns the difficulty of the snake.
	 * 
	 * @return The difficulty of the snake
	 */
	public static Difficulty getDifficulty(){
		return Snake.difficulty;
	}

	/**
	 * Returns the Position of the snakes head.
	 * 
	 * @return Position of the snakes head
	 */
	public Position getHead(){
		return this.snakePositionBody.get(0);
	}

	/**
	 * Returns the position of the bodyparts of the snake.
	 * 
	 * @return ArrayList of the Positions from the bodyparts
	 */
	public ArrayList<Position> getSnakePosition(){
		return this.snakePositionBody;
	}

	/**
	 * Returns if the snake is alive.
	 * 
	 * @return true if the snake is alive or
	 * 		   <p>false if its not
	 */
	public static boolean isAlive(){
		return Snake.isAlive;
	}
}
