package gui.keyfunctions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JScrollPane;

/**
 * WindowKeyFunction:
 * <p>This class is for the instructions on how to play the game or
 * which keys call what functions.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class WindowKeyfunctions extends JFrame{

	private static final long	serialVersionUID	= -5336418815000051126L;

	private JPanel				contentPane;

	public WindowKeyfunctions(String title){
		super(title);
		setTitle("Spielsteuerung");
		setBounds(190, 150, 300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Button to close the window
		JButton btn_Schliessen = new JButton("Schliessen");
		btn_Schliessen.setBounds(93, 243, 117, 29);
		btn_Schliessen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_Schliessen);

		// Textarea where the instructions and keyfunctions
		// are described
		JTextArea textArea = new JTextArea();
		textArea.setSize(288, 225);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		setText(textArea);

		// Scrollpane which is needed to scroll in the Textfield
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(6, 6, 288, 225);
		contentPane.add(scrollPane);

		// Set the scrollbar at the beginning
		textArea.setCaretPosition(0);
	}

	/**
	 * Sets the text for the Textarea from the File Spielsteuerung.txt
	 * 
	 * @param textArea		JTextarea
	 */
	private void setText(JTextArea textArea){
		// the text which is read from the file
		String text = "";

		try{
			// Reading the file and save the text in the String text
			Reader fr = new InputStreamReader(this.getClass().getResourceAsStream(
					"/resources/Spielsteuerung.txt"), "UTF-8");
			int value = fr.read();
			while(value != -1){
				text += (char) value;
				value = fr.read();
			}
			fr.close();

			// Exception catches
		}catch(FileNotFoundException e){
			System.err.println("File: Spielsteuerung, was not found!");
			e.printStackTrace();
		}catch(IOException e){
			System.err.println("Something went wrong by reading the file!");
			e.printStackTrace();
		}

		// Sets the text of the Textarea
		textArea.setText(text);
	}
}
