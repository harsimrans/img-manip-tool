
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

public class PictureFrame extends JFrame {

    private JLabel imageLabel;

    PictureFrame(String name, Picture picture) {
	super(name);
	framePicture(picture);
    }

    private void framePicture (Picture picture) {

	/* Put the Picture in an ImageIcon */
	ImageIcon icon = new ImageIcon();
	icon.setImage(picture.getBufferedImage());

	/* Put the Image Icon in a JLabel */
	imageLabel = new JLabel(icon);
	imageLabel.setIcon(icon);

	/* Put the JLabel in a JPanel */
	JPanel imagePanel = new JPanel();
	imagePanel.setBackground(Color.black);
	imagePanel.add(imageLabel);
	/* Add the JPanel to the PictureFrame */
	getContentPane().add(imagePanel, BorderLayout.CENTER);

    }

    public void refresh (Picture picture) {
	ImageIcon icon = new ImageIcon();
	icon.setImage(picture.getBufferedImage());
	imageLabel.setIcon(icon);
	pack();
    }

}
