package gui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import elementsOnPlayground.Snake;
import gui.GUI;

/**
 * MyKeyListener:
 * <p>MyKeyListener implements KeyListener and is used to change the direction of the 
 * snake or to create ActionEvents with the buttons on the GUI.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class MyKeyListener implements KeyListener{

	/* 
	The attribute down has to be true at the beginning because 
	the snake method wouldn't check if it ran into the playground
	boundary. It wouldn't go into the check if(myKeyListener.isDown()) 
	*/
	private boolean	down	= true;
	private boolean	up		= false;
	private boolean	right	= false;
	private boolean	left	= false;

	@Override
	public void keyPressed(KeyEvent e){

		// Pause key pressed
		if(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("Pause! :) wurde getyped");

			// Presses the button pause
			GUI.btn_Pause.doClick();
		}

		// Up key pressed
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			System.out.println("Nach oben! ^");

			// makes sure that the direction is only changed when the snake runs
			if(!Snake.isPause()){
				this.up = true;
				this.down = false;
				this.left = false;
				this.right = false;
			}
		}

		// Down key pressed
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			System.out.println("Nach unten! v");

			// makes sure that the direction is only changed when the snake runs
			if(!Snake.isPause()){
				this.up = false;
				this.down = true;
				this.left = false;
				this.right = false;
			}
		}

		// Left key pressed
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			System.out.println("Nach links! <");

			// makes sure that the direction is only changed when the snake runs
			if(!Snake.isPause()){
				this.up = false;
				this.down = false;
				this.left = true;
				this.right = false;
			}
		}

		// Right key pressed
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			System.out.println("Nach rechts! >");

			// makes sure that the direction is only changed when the snake runs
			if(!Snake.isPause()){
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
			}
		}

		// N key for new Game pressed
		else if(e.getKeyCode() == KeyEvent.VK_N){
			System.out.println("Neues Spiel wurde getyped");

			// Code runs only if ths snake is on pause
			if(Snake.isPause() || !Snake.isAlive()){
				// Presses the button Neues Spiel
				GUI.btn_NeuesSpiel.doClick();
			}
		}

		// Space or s key pressed for start Game
		else if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_S){
			System.out.println("Start wurde getyped");

			// Presses the button Start
			GUI.btn_Start.doClick();
		}

		// Difficulty
		// Easy is pressed
		else if(e.getKeyCode() == KeyEvent.VK_1){
			System.out.println("Einfach wurde getyped");

			// Presses the button easy
			GUI.btn_Einfach.doClick();
		}

		// Medium is pressed
		else if(e.getKeyCode() == KeyEvent.VK_2){
			System.out.println("Medium wurde getyped");

			// Presses the button medium
			GUI.btn_Mittel.doClick();
		}

		// Hard is pressed
		else if(e.getKeyCode() == KeyEvent.VK_3){
			System.out.println("Schwer wurde getyped");

			// Presses the button hard
			GUI.btn_Schwer.doClick();
		}

		// Very hard is pressed
		else if(e.getKeyCode() == KeyEvent.VK_4){
			System.out.println("Sehr schwer wurde getyped");

			// Presses the button very hard
			GUI.btn_Schwerer.doClick();
		}

		// Sick is pressed
		else if(e.getKeyCode() == KeyEvent.VK_5){
			System.out.println("Krank wurde getyped");

			// Presses the button crank
			GUI.btn_Krank.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		// not needed
	}

	@Override
	public void keyTyped(KeyEvent e){
		// not needed
	}

	/**
	 * Returns if the upkey is pressed.
	 * 
	 * @return true if up is pressed or
	 * 		   <p>false if not
	 */
	public boolean isUp(){
		return up;
	}

	/**
	 * Returns if the downkey is pressed.
	 * 
	 * @return true if down is pressed or
	 * 		   <p>false if not
	 */
	public boolean isDown(){
		return down;
	}

	/**
	 * Returns if the rightkey is pressed.
	 * 
	 * @return true if right is pressed or
	 * 		   <p>false if not
	 */
	public boolean isRight(){
		return right;
	}

	/**
	 * Returns if the leftkey is pressed.
	 * 
	 * @return true if left is pressed or
	 * 		   <p>false if not
	 */
	public boolean isLeft(){
		return left;
	}

	/**
	 * If a new snake is created the MyKeyListener needs to be set on default.
	 */
	public void setDefault(){
		down = true;
		up = false;
		right = false;
		left = false;
	}
}
