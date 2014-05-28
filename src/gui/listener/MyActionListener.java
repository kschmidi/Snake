package gui.listener;

import gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MyActionListener:
 * <p>MyActionListener implements Actionlistener and is used to return an
 * Actionevent, which the snake uses in its update methodto react on user
 * input.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class MyActionListener implements ActionListener{
	
	private ActionEvent actionevent;

	@Override
	public void actionPerformed(ActionEvent e){
		// Neues Spiel Button pressed
		if(e.getActionCommand().contentEquals(GUI.STR_NEUES_SPIEL)){
			System.out.println("Neues Spiel wurde gedrueckt");
			actionevent = e;
		}

		// Start Button pressed
		else if(e.getActionCommand().contentEquals(GUI.STR_START)){
			System.out.println("Start wurde gedrueckt");
			actionevent = e;
		}

		// Pause Button pressed
		else if(e.getActionCommand().contentEquals(GUI.STR_PAUSE)){
			System.out.println("Pause wurde gedueckt");
			actionevent = e;
		}

		// Difficulty
		// Easy Button
		else if(e.getActionCommand().contentEquals(GUI.STR_EINFACH)){
			System.out.println("Einfach wurde gedrueckt");
			actionevent = e;
		}

		// Medium Button
		else if(e.getActionCommand().contentEquals(GUI.STR_MITTEL)){
			System.out.println("Medium wurde gedrueckt");
			actionevent = e;
		}

		// Hard Buttton
		else if(e.getActionCommand().contentEquals(GUI.STR_SCHWER)){
			System.out.println("Schwer wurde gedrueckt");
			actionevent = e;
		}
		
		// Very hard Button
		else if(e.getActionCommand().contentEquals(GUI.STR_SCHWERER)){
			System.out.println("Schwerer wurde gedrueckt");
			actionevent = e;
		}
		
		// Sick Button
		else if(e.getActionCommand().contentEquals(GUI.STR_KRANK)){
			System.out.println("Krank wurde gedrueckt");
			actionevent = e;
		} 
	}
	
	/**
	 * Returns the ActionEvent.
	 * 
	 * @return ActionEvent
	 */
	public ActionEvent getActionEvent(){
		ActionEvent returnEvent = actionevent;
		this.actionevent = null;
		return returnEvent;
	}
}
