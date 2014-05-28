package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.keyfunctions.WindowKeyfunctions;
import gui.listener.MyActionListener;
import gui.listener.MyKeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * GUI:
 * <p>This class is the gui which the user can see and interact with. It 
 * contains the playground, where the snake is painted as well as the food.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.05.2014
 */
public class GUI extends JFrame{
	private static final long	serialVersionUID	= -1119133702245690578L;

	// Gameconstants
	private final int			RASTER				= 20;

	private final static int	BUTTON_HEIGHT		= 20;
	private final static int	BUTTON_WIDTH		= 120;

	private final static int	WINDOW_HEIGHT		= 380;
	private final static int	WINDOW_WIDTH		= 480;

	private JPanel				contentPane;
	private Playground			playground;
	private MyKeyListener		myKeyListener;
	private MyActionListener	myActionListener;

	// String Constants
	public static final String	STR_NEUES_SPIEL		= "Neues Spiel (N)";
	public static final String	STR_START			= "Start (S)";
	public static final String	STR_PAUSE			= "Pause (P)";
	public static final String	STR_EINFACH			= "Einfach (1)";
	public static final String	STR_MITTEL			= "Mittel (2)";
	public static final String	STR_SCHWER			= "Schwer (3)";
	public static final String	STR_SCHWERER		= "Schwerer (4)";
	public static final String	STR_KRANK			= "Krank (5)";

	// Button which are needed in MyKeyListener
	public static JButton		btn_NeuesSpiel;
	public static JButton		btn_Start;
	public static JButton		btn_Pause;
	public static JButton		btn_Einfach;
	public static JButton		btn_Mittel;
	public static JButton		btn_Schwer;
	public static JButton		btn_Schwerer;
	public static JButton		btn_Krank;
	public static JLabel		lbl_Score;
	private JButton				btn_Beenden;

	/**
	 * Constructor
	 * 
	 * @param playground 		Playground
	 */
	public GUI(String title, Playground playground){

		super(title);

		this.playground = playground;

		// Sets some attributes of the gui
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Listener
		myKeyListener = new MyKeyListener();
		myActionListener = new MyActionListener();

		// Label Menu
		JLabel menu = new JLabel("Menu");
		menu.setBounds(RASTER, RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		menu.addKeyListener(myKeyListener);
		getContentPane().add(menu);

		// Button Neues Spiel
		btn_NeuesSpiel = new JButton(GUI.STR_NEUES_SPIEL);
		btn_NeuesSpiel.setBounds(RASTER, 2 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_NeuesSpiel.setFocusable(false);
		btn_NeuesSpiel.addActionListener(myActionListener);
		btn_NeuesSpiel.addKeyListener(myKeyListener);
		getContentPane().add(btn_NeuesSpiel);

		// Button Start
		btn_Start = new JButton(GUI.STR_START);
		btn_Start.setBounds(RASTER, 3 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Start.setFocusable(false);
		btn_Start.addActionListener(myActionListener);
		btn_Start.addKeyListener(myKeyListener);
		getContentPane().add(btn_Start);

		// Button Pause
		btn_Pause = new JButton(GUI.STR_PAUSE);
		btn_Pause.setBounds(RASTER, 4 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Pause.setFocusable(false);
		btn_Pause.addActionListener(myActionListener);
		btn_Pause.addKeyListener(myKeyListener);
		getContentPane().add(btn_Pause);

		// Label Schwierigkeit
		JLabel schwierigkeit = new JLabel("Schwierigkeit");
		schwierigkeit.setBounds(RASTER, 6 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		schwierigkeit.addKeyListener(myKeyListener);
		getContentPane().add(schwierigkeit);

		// Button Einfach
		btn_Einfach = new JButton(GUI.STR_EINFACH);
		btn_Einfach.setBounds(RASTER, 7 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Einfach.addActionListener(myActionListener);
		btn_Einfach.setFocusable(false);
		btn_Einfach.addKeyListener(myKeyListener);
		getContentPane().add(btn_Einfach);

		// Button Mittel
		btn_Mittel = new JButton(GUI.STR_MITTEL);
		btn_Mittel.setBounds(RASTER, 8 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Mittel.addActionListener(myActionListener);
		btn_Mittel.setFocusable(false);
		btn_Mittel.addKeyListener(myKeyListener);
		getContentPane().add(btn_Mittel);

		// Buttton Schwer
		btn_Schwer = new JButton(GUI.STR_SCHWER);
		btn_Schwer.setBounds(RASTER, 9 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Schwer.addActionListener(myActionListener);
		btn_Schwer.setFocusable(false);
		btn_Schwer.addKeyListener(myKeyListener);
		getContentPane().add(btn_Schwer);

		// Button Schwerer
		btn_Schwerer = new JButton(GUI.STR_SCHWERER);
		btn_Schwerer.setBounds(RASTER, 10 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Schwerer.addActionListener(myActionListener);
		btn_Schwerer.setFocusable(false);
		btn_Schwerer.addKeyListener(myKeyListener);
		getContentPane().add(btn_Schwerer);

		// Button krank
		btn_Krank = new JButton(GUI.STR_KRANK);
		btn_Krank.setBounds(RASTER, 11 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Krank.addActionListener(myActionListener);
		btn_Krank.setFocusable(false);
		btn_Krank.addKeyListener(myKeyListener);
		getContentPane().add(btn_Krank);

		// Keyfunctions
		// Creates a new Window with a game guide and which keys have what functions
		JButton keyfunctions = new JButton("Steuerung");
		keyfunctions.setBounds(RASTER, 14 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		keyfunctions.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				WindowKeyfunctions window = new WindowKeyfunctions("Spielsteuerung");
				window.setVisible(true);
				window.setResizable(false);
			}
		});
		keyfunctions.setFocusable(false);
		keyfunctions.addKeyListener(myKeyListener);
		getContentPane().add(keyfunctions);

		// Button tho end the Programm
		btn_Beenden = new JButton("Beenden");
		btn_Beenden.setBounds(RASTER, 16 * RASTER, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_Beenden.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(NORMAL);
			}
		});
		btn_Beenden.setFocusable(false);
		btn_Beenden.addKeyListener(myKeyListener);
		contentPane.add(btn_Beenden);

		// Playground
		this.playground.setBounds(8 * RASTER - 1, 1 * RASTER - 1);
		this.playground.addKeyListener(myKeyListener);
		getContentPane().add(playground);
		this.playground.setFocusable(true);

		// Label Score under Playground
		lbl_Score = new JLabel("Score: " + 0);
		lbl_Score.setBounds(8 * RASTER, 16 * RASTER, Playground.PLAYGROUND_WIDTH, BUTTON_HEIGHT);
		lbl_Score.addKeyListener(myKeyListener);
		getContentPane().add(lbl_Score);
	}

	/**
	 * Returns the MyActionListener.
	 * 
	 * @return MyActionListener
	 */
	public MyActionListener getActionListener(){
		return this.myActionListener;
	}

	/**
	 * Returns the MyKeyListener.
	 * 
	 * @return MyKeyListener
	 */
	public MyKeyListener getMyKeyListener(){
		return this.myKeyListener;
	}
}
