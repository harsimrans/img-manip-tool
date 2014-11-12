/**
 * Staring Point Code for Image Processing Project
 * @author Richard Wicentowski and Tia Newhall (2005)
 */

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;


public class ImageBase {

    private JFrame menuFrame;
    private PictureFrame pictureFrame;
    private SimpleButton[] buttons;
    private JLabel photo;
    private Picture originalPicture, picture;

    ImageBase(String filename) {
	setupMenuFrame();
	setupImageFrame(filename);
    }

    private void setupMenuFrame() {

	/* Create the buttons */
	createButtons();

	/* Create a JPanel to put the buttons on */
	JPanel menuPanel = new JPanel();

	/* Make the menu background black */
	menuPanel.setBackground(Color.black);

	/* Make sure the buttons line up nicely */
	menuPanel.setLayout(new GridLayout(buttons.length/2,2));

	/* Add buttons to the menu, skipping the Restore and Quit buttons */
	for (int i = 2; i < buttons.length; i++) {
	    menuPanel.add(buttons[i]);
	}

	/* Add the Restore and Quit buttons at the end */
	menuPanel.add(buttons[0]);
	menuPanel.add(buttons[1]);

	/* Create the menu JFrame which will hold the menu JPanel */
	menuFrame = new JFrame("Image Manipulation Menu");

	/* The JPanel is now added to the menu JFrame */
	menuFrame.getContentPane().add(menuPanel, BorderLayout.CENTER);

	/* If the user closes the window, our program should exit */
	menuFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });

	/* Optimally size the menuFrame */
	menuFrame.pack();

	/* Place the menuFrame near the top-left of the screen */
	menuFrame.setLocation(20,20);

	/* Make the menuFrame visible */
	menuFrame.setVisible(true);
    }

    private void setupImageFrame(String filename) {

	/* Read a color picture from a file */
	picture = new Picture(filename);

	/* Make a copy of the picture in case we need it again */
	originalPicture = new Picture();
	originalPicture.copy(picture);

	/* Put the picture in a PictureFrame called "Image" */
	pictureFrame = new PictureFrame("Image", picture);

	/* Quit the program if the user closes the PictureFrame */
	pictureFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });

	/* Make the PictureFrame the optimal size for this picture */
	pictureFrame.pack();

	/* Place the PictureFrame 25 pixels to right of the MenuFrame */
	Point p = menuFrame.getLocation();
	pictureFrame.setLocation(p.x+menuFrame.getSize().width+25, p.y);

	/* Make the PictureFrame visible */
	pictureFrame.setVisible(true);

    }


    private void createButtons() {

	/* How many buttons do you want? */
	int numButtons = 14;

	/* Create an array of SimpleButtons */
	buttons = new SimpleButton[numButtons];

	/* Define some basic buttons */
	buttons[0] = 
	    new SimpleButton("Restore", new RestoreImage(), Color.green);

	buttons[1] = 
	    new SimpleButton("Quit", new Quit(), Color.red);

	/* Add your own button definitions here */

	int counter = 2;

	buttons[counter++] = 
	    new SimpleButton("Flip Horizontal", new FlipHorizontal());





	/* For buttons you did not define, create default buttons */
	for (int i = 0; i < numButtons; i++) {
	    if (buttons[i] == null) {
		buttons[i] = new SimpleButton();
	    }
	}

    }


    class RestoreImage implements ActionListener {
	public void actionPerformed (ActionEvent e) {
	    picture.copy(originalPicture);
	    pictureFrame.refresh(picture);
	}
    }

    class Quit implements ActionListener {
	public void actionPerformed (ActionEvent e) {
	    System.exit(0);
	}
    }


    class FlipHorizontal implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    Pixel pix1, pix2;
	    
	    for (int x = 0; x < imageWidth/2; x++) {
		for (int y = 0; y < imageHeight; y++) {
		    pix1 = picture.getPixel(x, y);
		    pix2 = picture.getPixel(imageWidth-x-1, y);
		    picture.setPixel(x, y, pix2);
		    picture.setPixel(imageWidth-x-1, y, pix1);
		}
	    }

	    pictureFrame.refresh(picture);
	}
    }


    public static void main (String[] args) {
	ImageBase app;

	if (args.length == 1) {
	    File f = new File (args[0]);
	    if (f.exists()) {
		app = new ImageBase(args[0]);
	    } else {
		System.err.println(args[0] + ": File not found.");
		System.exit(0);
	    }
	} else {
	    System.err.println("Image file required.");
	    System.exit(0);
	}
    }

}
