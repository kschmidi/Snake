package gui;

import java.awt.event.ActionEvent;

import elementsOnPlayground.Food;
import elementsOnPlayground.Snake;
import elementsOnPlayground.helpClasses.Position;
import gui.listener.MyKeyListener;

/**
 * Main:
 * <p>Mainclass which creates the snake the food the gui and the playground
 * and is needed to start the programm.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class Main{
	public static void main(String[] args){
		// Creates the position where the snake starts
		Position snakeStartPosition = new Position(Playground.PLAYGROUND_WIDTH / 2 + 1,
				Playground.PLAYGROUND_HEIGHT / 2 + (2 * Snake.SNAKE_Y_SIZE) + 1);

		// Creates the playground and the snake
		GUI gui;
		Food food = new Food(new Position(Position.random.nextInt(Playground.PLAYGROUND_WIDTH),
				Position.random.nextInt(Playground.PLAYGROUND_HEIGHT)));
		Snake snake = new Snake(snakeStartPosition, food);
		Playground playground = new Playground(snake, food);

		// Creates the gui and sets some attributes
		gui = new GUI("Snake", playground);
		gui.setVisible(true);
		gui.setResizable(false);

		// An endless loop where the hole logic of the game is handeled
		while(true){

			// Get the listeners from gui
			ActionEvent actionevent = gui.getActionListener().getActionEvent();
			MyKeyListener myKeyListener = gui.getMyKeyListener();

			// Creates a new snake and food if new Game gets pushed
			// new food because the food need to be replaced
			if(actionevent != null){
				if(actionevent.getActionCommand().contentEquals(GUI.STR_NEUES_SPIEL)){
					food = new Food(new Position(
							Position.random.nextInt(Playground.PLAYGROUND_WIDTH),
							Position.random.nextInt(Playground.PLAYGROUND_HEIGHT)));
					snake = new Snake(snakeStartPosition, food);
					playground.giveNewElements(snake, food);
					myKeyListener.setDefault();
					GUI.lbl_Score.setText("Score: 0");
					System.out.println("New Snake generated");
				}
			}

			// Update method in which the snakes position is recalculated 
			snake.update(actionevent, myKeyListener, GUI.lbl_Score);

			// Repaint the playground with the new coordinate of the snake or if needed of the food
			// Check if the snake is alive otherwise it runs into itself
			if(Snake.isAlive()){
				playground.repaint();
			}

			try{
				// Thread sleep for the difficulty of the game
				Thread.sleep(Snake.getDifficulty().level);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
