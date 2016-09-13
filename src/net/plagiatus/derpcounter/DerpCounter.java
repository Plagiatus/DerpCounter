package net.plagiatus.derpcounter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DerpCounter {

	/**
	 * @param args
	 */
	static String path = "facepalm.png";
	static String displayText = new String("Derp Counter:");
	static String actionKey = "P";
	static int counter = 0;
	static float duration = 2.5f;
//	static boolean getNewKey = false;
	static Color textColor = Color.WHITE;
	static int xPosition = 40, yPosition = 100;
	static int textSize = 60;
//	static JFrame mainFrame = new JFrame("Derp Counter");
	static JTextField counterField = new JTextField(Integer.toString(counter));
	
	public static void main(String[] args) {
		
		
		
		
		
		
		
		//Init Main Frame
		JFrame mainFrame = new JFrame("Derp Counter");	
		mainFrame.setSize(330, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		/*
		 * Options
		 */
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		
		
		// Image
		
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
		JButton loadImageButton = new JButton("Select Image");
		loadImageButton.setToolTipText("Make sure the picture has the desired size");
		ActionListener imageButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectImage(mainFrame)){
//					System.out.println("reload Frame");
					SwingUtilities.updateComponentTreeUI(mainFrame);
				} else {
//					System.out.println("don't reload Frame");
				};
				
			}
		};
		
		loadImageButton.addActionListener(imageButtonListener);
		imagePanel.add(Box.createRigidArea(new Dimension(20,0))); //compensate for MyCanvas
		imagePanel.add(loadImageButton);
		imagePanel.add(Box.createRigidArea(new Dimension(10,0)));
		imagePanel.add(new JLabel("Preview:",SwingConstants.CENTER));
		imagePanel.add(new MyCanvas());

		
		
		//Duration
		
		JPanel durationPanel = new JPanel();
		durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.X_AXIS));
		
		durationPanel.add(new JLabel("Duration (in s)"));
		durationPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		JFormattedTextField durationField = new JFormattedTextField(float.class);
		durationField.setValue(duration);
		durationField.setMaximumSize(new Dimension(50,20));
		
		durationPanel.add(durationField);
		
		durationPanel.add(Box.createRigidArea(new Dimension(10,0)));
	
		
		ActionListener durationButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duration = Float.parseFloat(durationField.getText());

				SwingUtilities.updateComponentTreeUI(counterField);
				
			}
		};
		JButton durationButton = new JButton("Set Time");
		durationButton.addActionListener(durationButtonListener);
		durationButton.setToolTipText("The amount of time the image will be shown");
		durationPanel.add(durationButton);
		
		
		
		//Text to display
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		
		textPanel.add(new JLabel("Display Text",SwingConstants.CENTER));
		textPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		JTextField textField = new JTextField(displayText);
		Dimension d = new Dimension(100, 20);
		textField.setMaximumSize(d);
		textField.setPreferredSize(d);
		
		textPanel.add(textField);
		textPanel.add(Box.createRigidArea(new Dimension(10,0)));
	
		
		ActionListener textButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayText = textField.getText();
				
			}
		};
		JButton textButton = new JButton("Set Text");
		textButton.addActionListener(textButtonListener);
		textButton.setToolTipText("The text you'd like to display");
		textPanel.add(textButton);
		
		
		//Color
		
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
		
		JButton textColorButton = new JButton("Select Color");
		
		ActionListener textColorButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textColor = JColorChooser.showDialog(null, "Font Color", textColor);
				SwingUtilities.updateComponentTreeUI(mainPanel);
				
			}
		};
		textColorButton.addActionListener(textColorButtonListener);
		textColorButton.setToolTipText("The Color the Text will be displayed in");
		colorPanel.add(Box.createRigidArea(new Dimension(80,0)));
		colorPanel.add(textColorButton);
		colorPanel.add(new RectangleCanvas());
		
		
		
		//Font
		
		JPanel fontPanel = new JPanel();
		fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.X_AXIS));
		
		fontPanel.add(new JLabel("Text Size:"));
		fontPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		
		JFormattedTextField textSizeField = new JFormattedTextField(int.class);
		textSizeField.setValue(textSize);
		textSizeField.setMaximumSize(new Dimension(50,20));
		textSizeField.setPreferredSize(new Dimension(50,20));
		
		fontPanel.add(textSizeField);
		
		JButton textSizeButton = new JButton("Set Text Size");
		textSizeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textSize = Integer.parseInt(textSizeField.getText());		
			}
		});
		
		fontPanel.add(Box.createRigidArea(new Dimension(10,0)));
		fontPanel.add(textSizeButton);
		
		
		JPanel fontPanel2  = new JPanel();
		fontPanel2.setLayout(new BoxLayout(fontPanel2, BoxLayout.X_AXIS));
		
		fontPanel2.add(new JLabel("Text Position "));
		
		fontPanel2.add(new JLabel("x:"));
		JFormattedTextField xPositionField = new JFormattedTextField(int.class);
		xPositionField.setValue(xPosition);
		xPositionField.setPreferredSize(new Dimension(50,20));
		xPositionField.setMaximumSize(new Dimension(50,20));
		fontPanel2.add(xPositionField);
		
		fontPanel2.add(new JLabel(" y:"));
		JFormattedTextField yPositionField = new JFormattedTextField(int.class);
		yPositionField.setValue(yPosition);
		yPositionField.setMaximumSize(new Dimension(50,20));
		yPositionField.setPreferredSize(new Dimension(50,20));
		fontPanel2.add(yPositionField);
		
		JButton fontPositionButton = new JButton("Set Position");
		fontPositionButton.setToolTipText("bottom left position of the text on the image");
		
		ActionListener fontPositionButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xPosition = Integer.parseInt(xPositionField.getText());
				yPosition = Integer.parseInt(yPositionField.getText());
				
			}
		};
		fontPositionButton.addActionListener(fontPositionButtonListener);
		fontPanel2.add(Box.createRigidArea(new Dimension(10,0)));
		fontPanel2.add(fontPositionButton);
		
		JPanel fontPanel3  = new JPanel();
		fontPanel3.setLayout(new BoxLayout(fontPanel3, BoxLayout.X_AXIS));
		
		
		
		
		
		
		
		
		
		JPanel textFontPanel = new JPanel();
		textFontPanel.setBorder(BorderFactory.createTitledBorder("Text"));
		textFontPanel.setLayout(new BoxLayout(textFontPanel, BoxLayout.Y_AXIS));
		
		textFontPanel.add(textPanel);
		textFontPanel.add(Box.createRigidArea(new Dimension(0,10)));
		textFontPanel.add(colorPanel);
		textFontPanel.add(Box.createRigidArea(new Dimension(0,10)));
		textFontPanel.add(fontPanel);
		textFontPanel.add(Box.createRigidArea(new Dimension(0,10)));
		textFontPanel.add(fontPanel2);
		
		
		//Hotkey
		
		JPanel counterPanel1 = new JPanel();
		counterPanel1.setLayout(new BoxLayout(counterPanel1, BoxLayout.X_AXIS));
		counterPanel1.add(new JLabel("Hotkey:"));
		counterPanel1.add(Box.createRigidArea(new Dimension(10,0)));
		
		JTextField hotkeyField = new JTextField(actionKey);
		hotkeyField.setColumns(1);
		hotkeyField.setMaximumSize(new Dimension(30,20));
		
		counterPanel1.add(hotkeyField);

		counterPanel1.add(Box.createRigidArea(new Dimension(10,0)));
		
		JButton hotkeyButton = new JButton("Select Hotkey");
		hotkeyButton.setToolTipText("Which key do you want to activate this with?");
		
		ActionListener hotkeyButtonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newKey = hotkeyField.getText().substring(0, 1);
				newKey = newKey.toUpperCase();
				hotkeyField.setText(newKey);
				
				actionKey = newKey;
			}
			
		}; 
		
		hotkeyButton.addActionListener(hotkeyButtonListener);
		
		counterPanel1.add(hotkeyButton);
		
		
		//Counter
		
		
		JPanel counterPanel2 = new JPanel();
		counterPanel2.setLayout(new BoxLayout(counterPanel2, BoxLayout.X_AXIS));
		counterPanel2.add(new JLabel("Current Counter Value:"));
		
//		JTextField counterField = new JTextField(Integer.toString(counter)); //it's global now

		counterField.setMaximumSize(new Dimension(30,20));
		counterField.setHorizontalAlignment(JTextField.CENTER);
		counterField.setPreferredSize(new Dimension(30,20));
		counterField.setEditable(false);
		
		
		
		
		JButton minusOne = new JButton("-1");
		minusOne.setPreferredSize(new Dimension(50,20));
		minusOne.setMaximumSize(new Dimension(50,20));
		minusOne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;
				
				counterField.setText(Integer.toString(counter));
			}
		});
		
		JButton plusOne = new JButton("+1");
		plusOne.setPreferredSize(new Dimension(50,20));
		plusOne.setMaximumSize(new Dimension(50,20));
		plusOne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				
				counterField.setText(Integer.toString(counter));
			}
		});
		
		counterPanel2.add(Box.createRigidArea(new Dimension(5,0)));
		counterPanel2.add(minusOne);
		counterPanel2.add(counterField);
		counterPanel2.add(plusOne);
		
		JPanel counterPanel3 = new JPanel();
//		counterPanel3.setLayout(new BoxLayout(counterPanel3, BoxLayout.X_AXIS));
		JButton resetCounterButton = new JButton("Reset Counter To 0");
		resetCounterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				counter = 0;
				counterField.setText(Integer.toString(counter));
			}
		});
		counterPanel3.add(resetCounterButton);
		
		
		
		
		
		
		
		
		
		JPanel imageDurationPanel = new JPanel();
		imageDurationPanel.setBorder(BorderFactory.createTitledBorder("Display"));
		imageDurationPanel.setLayout(new BoxLayout(imageDurationPanel, BoxLayout.Y_AXIS));
		imageDurationPanel.add(imagePanel);
		imageDurationPanel.add(Box.createRigidArea(new Dimension(0,10)));
		imageDurationPanel.add(durationPanel);
		
		JPanel counterPanel = new JPanel();
		counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.Y_AXIS));
		counterPanel.setBorder(BorderFactory.createTitledBorder("Counter"));
		
		counterPanel.add(counterPanel1);
		counterPanel.add(Box.createRigidArea(new Dimension(0,5)));
		counterPanel.add(counterPanel2);
		counterPanel.add(Box.createRigidArea(new Dimension(0,5)));
		counterPanel.add(counterPanel3);
		
		optionsPanel.add(imageDurationPanel);
		optionsPanel.add(Box.createRigidArea(new Dimension(0,10)));
		optionsPanel.add(textFontPanel);
		optionsPanel.add(counterPanel);
		
		
		
		
		mainPanel.add(optionsPanel);
		
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
		
		startDetecting();
		
	}
	
	public static void showCounter(){
				
		JFrame counterFrame = new JFrame("Counter");
		ImageIcon image = new ImageIcon(path);
		//counterFrame.add(new JLabel(image));
		counterFrame.setSize(image.getIconWidth(), image.getIconHeight());
		counterFrame.setLocationRelativeTo(null);
		counterFrame.setUndecorated(true);
		counterFrame.add(new ImageCanvas());
		
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		counterFrame.setVisible(true);
		
		counterFrame.toFront();
		
		while (elapsedTime < duration*1000 / 2) {
		    elapsedTime = (System.currentTimeMillis() - startTime);
		}
		
		addOneToCounter();
		SwingUtilities.updateComponentTreeUI(counterFrame);
		
		while (elapsedTime < duration*1000) {
		    elapsedTime = (System.currentTimeMillis() - startTime);
		}

		
		counterFrame.setVisible(false);
	}
	
	public static boolean selectImage(JFrame suppressThis){
		JFileChooser imgChooser = new JFileChooser();
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Images","png","jpg","gif");
	
		imgChooser.addChoosableFileFilter(imgFilter);
		imgChooser.setFileFilter(imgFilter);
		int returnValue = imgChooser.showOpenDialog(suppressThis);
        
        /* Abfrage, ob auf "Öffnen" geklickt wurde */
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
        	//System.out.println("Die zu öffnende Datei ist: " + imgChooser.getSelectedFile().getAbsolutePath());
        	path = imgChooser.getSelectedFile().getAbsolutePath();
        	return true;
        }
        else {
        	return false;
        }
        
	}
	
	public static void startDetecting(){
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);
		
		 try {
	            GlobalScreen.registerNativeHook();
	        }
	        catch (NativeHookException ex) {
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(ex.getMessage());

	            System.exit(1);
	        }

	        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
	        //System.exit(0);
	    
	}
	
	public static void checkKeypress(String pressedKey){
//		System.out.println("Pressed: " + pressedKey.codePointAt(0) + " actionKey: " + actionKey.codePointAt(0));
		
		if(pressedKey.codePointAt(0) == actionKey.codePointAt(0)){
		showCounter();
		}
		
	}
	
	public static void addOneToCounter(){
		counter++;

		counterField.setText(Integer.toString(counter));
		SwingUtilities.updateComponentTreeUI(counterField);
	}

}