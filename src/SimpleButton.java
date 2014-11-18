
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleButton extends JButton {
    
    static Font defaultFont = new Font("Dialog",Font.PLAIN,24);
    static Color defaultColor = Color.cyan;
    static int counter = 0;
    static ActionListener defaultAction = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.err.println("This button has no assigned action.");
	    }
	};
    
    SimpleButton() {
	this("No Operation", defaultAction, defaultColor, defaultFont);
    }
    
    SimpleButton(String label, ActionListener listener) {
	this(label, listener, defaultColor, defaultFont);
    }

    SimpleButton(String label, ActionListener listener, Color color) {
	this(label, listener, color, defaultFont);
    }

    SimpleButton(String label, ActionListener listener, 
		 Color color, Font font) {
	setFont(font);
	setBackground(color);
	setText(label);
	addActionListener(listener);
	counter++;
    }
}
    
