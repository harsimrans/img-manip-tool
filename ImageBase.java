
/**
 * Staring Point Code for Image Processing Project
 * @author Richard Wicentowski and Tia Newhall (2005)
 */

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;



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
	int numButtons = 18;

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

	buttons[counter++]=
			new SimpleButton("Black n White",new BlackImage());

	buttons [counter++]=
			new SimpleButton("Blurr",new Blurr());
	
	buttons [counter++]= new SimpleButton("Flip Vertical", new FlipVertical());		
    buttons [counter++]= new SimpleButton("Grey Scale", new GreyScale());		
    buttons[counter++] = new SimpleButton("Rotate 90", new Rotate90());
	buttons[counter++] = new SimpleButton("Negative", new Negative());
	buttons[counter++] = new SimpleButton("Polarize", new Polarize());
	buttons[counter++]= new SimpleButton("Sharpen",new Sharpen());
	buttons[counter++]= new SimpleButton("Emboss",new Emboss());
	buttons[counter++]= new SimpleButton("Lighten",new Lighten());
	buttons[counter++]= new SimpleButton("Contrast",new Contrast());
	buttons[counter++]= new SimpleButton("Darken",new Darken());
	buttons[counter++]= new SimpleButton("Scroll Horizontal", new ScrollHorizontal());
	buttons[counter++]= new SimpleButton("Posterize", new Posterize());
	
	
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
    
    
    class Negative implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    Pixel pix, pix2;
	    int r, g, b;
	    for (int x = 0; x < imageWidth; x++) {
		for (int y = 0; y < imageHeight; y++) {
		    pix = picture.getPixel(x, y);
		    r = pix.getRed();
            g = pix.getGreen();
            b = pix.getBlue();
            
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            
            pix.setRed(r);
            pix.setGreen(g);
            pix.setBlue(b);
            
            //settig the new pixel
            picture.setPixel(x, y, pix);
		}
	    }

	    pictureFrame.refresh(picture);
	}
    } 
    class FlipVertical implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    Pixel pix1, pix2;
	    
	    //System.out.println("Height: " + imageHeight + " Width: " + imageWidth);
	    for (int x = 0; x < imageWidth; x++) {
		    for (int y = 0; y < imageHeight/2; y++) {
		        //System.out.println("Considering: " + x + " " );
		        pix1 = picture.getPixel(x, y);
		        pix2 = picture.getPixel(x, imageHeight-y-1);
		        picture.setPixel(x, y, pix2);
		        picture.setPixel(x, imageHeight-y-1, pix1);
		    }
	    }

	    pictureFrame.refresh(picture);
	    }
    }
    
    class Polarize implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    Pixel pix;
	    int r = 0, g = 0, b = 0;
	    
	    //System.out.println("Height: " + imageHeight + " Width: " + imageWidth);
	    for (int x = 0; x < imageWidth; x++) {
		    for (int y = 0; y < imageHeight; y++) {
		        pix = picture.getPixel(x, y);
                r += pix.getRed();
                g += pix.getGreen();
                b += pix.getBlue();  
		    }
	    }
        
        r = r / (imageHeight * imageWidth);
        g = g / (imageHeight * imageWidth);
        b = b / (imageHeight * imageWidth);
	    
	    System.out.println(r + " " + g + "  " + b);
	    int rc, bc, gc;
	    for (int x = 0; x < imageWidth; x++) {
		    for (int y = 0; y < imageHeight; y++) {
		        pix = picture.getPixel(x, y);
                rc = pix.getRed();
                gc = pix.getGreen();
                bc = pix.getBlue();
                
                if (rc > r)
                {
                    pix.setRed(255);
                }
                else
                {
                    pix.setRed(0);
                }
                
                if (gc > g)
                {
                    pix.setGreen(255);
                }
                else
                {
                    pix.setGreen(0);
                }
                
                if (bc > b)
                {
                    pix.setBlue(255);
                }
                else
                {
                    pix.setBlue(0);
                }
                
                picture.setPixel(x, y, pix);  
		    }
	    }
	    
	    pictureFrame.refresh(picture);
	    }
    }
    
    class Sharpen implements ActionListener  {
     	public void actionPerformed (ActionEvent e){

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1 = new Pixel(0,0,0); 
    	    Pixel pix2 = new Pixel(0,0,0); 
    	   int r=0,g=0,b=0;
    	    float[][] matrix = new float[][]{
    	    		{0,-1,0},
    	            {-1,5,-1},
    	            {0,-1,0}
    	        };
    	    for (int x = 1; x < imageWidth-1; x++) {
    		for (int y = 1; y < imageHeight-1; y++) {
    			r=0;g=0;b=0;
    			
    				for(int k =0;k<3;k++){
    					for(int l=0;l<3;l++){
    						if(x+2<imageWidth && y+2< imageHeight)
    						pix1=picture.getPixel(x+k,y+l);
    						
    						r = r + (int)((pix1.getRed())*(matrix[k][l]));
    						g = g +(int)( (pix1.getGreen())* (matrix[k][l]));
    						b = b + (int)((pix1.getBlue())*(matrix[k][l]));	
    					}
    				}
    				if(r<0) r=0;
    				if(g<0) g=0;
    				if(b<0) b=0;
    				
    				if(r>255) r=255;
    				if(g>255) g=255;
    				if(b>255) b=255;
    				
    				if(r<255 && g<255 && b <255){
    				pix2.setRed(r);
    				pix2.setGreen(g);
    				pix2.setBlue(b);
    				picture.setPixel(x,y,pix2);
    				}
    					
    		}
    	}  
    	    pictureFrame.refresh(picture);
       }
     }
    
    class BlackImage implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1;
    	    Pixel pix2= new Pixel(0,0,0) ;
    	    for (int x = 0; x < imageWidth; x++) {
    		for (int y = 0; y < imageHeight; y++) {
    			
    		    pix1 = picture.getPixel(x, y);
    		   // pix2 = picture.getPixel(imageWidth-x-1, y);
    		   // if(neither black nor white)
    		    if(!(pix1.getRed()>=190 && pix1.getBlue()>=190  &&pix1.getGreen()>=190 )&&(!( pix1.getGreen()<=40&& pix1.getRed()<=40&& pix1.getBlue()<=40)))
    		    {
    		    	//if{
    		    	int z= pix1.getRed()+pix1.getBlue()+pix1.getGreen();
    		    	z=z/3;
    		    	pix2.setRed(z);
    		    	pix2.setGreen(z);
    		    	pix2.setBlue(z);
    		    	picture.setPixel(x, y, pix2);
    		    	//}
    		    }
    		   // picture.setPixel(imageWidth-x-1, y, pix1);
    		}
    	    }

    	    pictureFrame.refresh(picture);
    	}
        }
        
    class Lighten implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1;
    	    int r,g,b;
    	    Pixel pix2= new Pixel(0,0,0) ;
    	    for (int x = 0; x < imageWidth; x++) {
    		for (int y = 0; y < imageHeight; y++) {
    			
    		    pix1 = picture.getPixel(x, y);
    		    
    		    r=pix1.getRed()+30;
    		    g=pix1.getGreen()+30;
    		    b=pix1.getBlue()+30;
    		    
    		    if(r>255) r=255;
    		    if(g>255) g=255;
    		    if(b>255) b=255;
    		    
    		    pix2.setRed(r);
    		    pix2.setGreen(g);
    		    pix2.setBlue(b);
    		   
    		    picture.setPixel(x , y, pix2);
    		}
    	    }
    	    pictureFrame.refresh(picture);
    	}
        }
    
    class Darken implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1;
    	    int r,g,b;
    	    
    	    Pixel pix2= new Pixel(0,0,0) ;
    	    for (int x = 0; x < imageWidth; x++) {
    		for (int y = 0; y < imageHeight; y++) {
    			
    		    pix1 = picture.getPixel(x, y);
    		    
    		    r=(int)(pix1.getRed()*0.5);
    		    g=(int)(pix1.getGreen()*0.5);
    		    b=(int)(pix1.getBlue()*0.5);
    		    pix2.setRed(r);
    		    pix2.setGreen(g);
    		    pix2.setBlue(b);
    		   
    		    picture.setPixel(x , y, pix2);
    		}
    	    }
    	    pictureFrame.refresh(picture);
    	}
     }
    class Posterize implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1;
    	    int r,g,b;
    	    
    	    Pixel pix2= new Pixel(0,0,0) ;
    	    for (int x = 0; x < imageWidth; x++) {
    		for (int y = 0; y < imageHeight; y++) {
    			
    		    pix1 = picture.getPixel(x, y);
    		    
    		    r=(int)(pix1.getRed());
    		    g=(int)(pix1.getGreen());
    		    b=(int)(pix1.getBlue());
    		    
    		    if(r<127) r=0;
    		    else r=255;
    		    
    		    if(g<127) g=0;
    		     else  g=255;
    		    
    		    if(b<127) b=0;
    		    else b=255;
    		    
    		    pix2.setRed(r);
    		    pix2.setGreen(g);
    		    pix2.setBlue(b);
    		   
    		    picture.setPixel(x , y, pix2);
    		}
    	    }
    	    pictureFrame.refresh(picture);
    	}
     }
    class Contrast implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1;
    	    int r,g,b;
    	    Pixel pix2= new Pixel(0,0,0) ;
    	    for (int x = 0; x < imageWidth; x++) {
    		for (int y = 0; y < imageHeight; y++) {
    			
    		    pix1 = picture.getPixel(x, y);
    		    
    		    r=pix1.getRed();
    		    g=pix1.getGreen();
    		    b=pix1.getBlue();
    		    
    		    if(r<30) r=0;
    		    else if(r>225) r=255;
    		    else if (r>30 && r< 225)
    		    	r=(int)(Math.round((255.0/195.0)*(r-30)+0.5));
    		    
    		    if(g<30) g=0;
    		    else if(g>225) g=255;
    		    else if (g>30 && g< 225)
    		    	g=(int)(Math.round((255.0/195.0)*(g-30)+0.5));
    		    
    		    if(b<30) b=0;
    		    else if(b>225) b=255;
    		    else if (b>30 && b< 225)
    		    	b=(int)(Math.round((255.0/195.0)*(b-30)+0.5));
    		    
    		    
    		    pix2.setRed(r);
    		    pix2.setGreen(g);
    		    pix2.setBlue(b);
    		   
    		    picture.setPixel(x , y, pix2);
    		}
    	    }
    	    pictureFrame.refresh(picture);
    	}
        }
   class GreyScale implements ActionListener{
            public void actionPerformed (ActionEvent e){
    	        int imageWidth = picture.getWidth();
    	        int imageHeight = picture.getHeight();
                int r, g, b;
                Pixel pix = new Pixel(0, 0, 0);
                
                for (int x = 0; x < imageWidth; x++){
                    for (int y = 0 ; y < imageHeight; y++){
                        pix = picture.getPixel(x, y);
                        r = pix.getRed();
                        g = pix.getGreen();
                        b = pix.getBlue();
                        
                        int average = (r + g + b)/3;
                        
                        //assingn the average values
                        
                        pix.setRed(average);
                        pix.setGreen(average);
                        pix.setBlue(average);
                        
                        //update the pixels
                        picture.setPixel(x, y, pix);
                    }
                }
                pictureFrame.refresh(picture);
            }
        
        }
    
    class Blurr implements ActionListener  {
    	public void actionPerformed (ActionEvent e) {

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1 = new Pixel(0,0,0); 
    	    Pixel pix2 = new Pixel(0,0,0); 
    	   int r=0,g=0,b=0;
    	    float[][] matrix = new float[][]{
    	    		{.11f, .11f, .11f},
    	            {.11f, .11f, .11f},
    	            {.11f, .11f, .11f}
    	        };
    	    for (int x = 1; x < imageWidth-1; x++) {
    		for (int y = 1; y < imageHeight-1; y++) {
    			r=0;g=0;b=0;
    			
    				for(int k =0;k<3;k++){
    					for(int l=0;l<3;l++){
    						if(x+2<imageWidth && y+2< imageHeight)
    						pix1=picture.getPixel(x+k-1,y+l-1);
    						r = r + (int)((pix1.getRed())*(matrix[k][l]));
    						g = g +(int)( (pix1.getGreen())* (matrix[k][l]));
    						b = b + (int)((pix1.getBlue())*(matrix[k][l]));	
    					    }
    					}
    				pix2.setRed(r);
    				pix2.setGreen(g);
    				pix2.setBlue(b);
    				picture.setPixel(x,y,pix2);
    			
    		}
    	}  
    	    pictureFrame.refresh(picture);
       }
    }
    
    class Emboss implements ActionListener  {
     	public void actionPerformed (ActionEvent e){

    	    int imageWidth = picture.getWidth();
    	    int imageHeight = picture.getHeight();
    	    Pixel pix1 = new Pixel(0,0,0); 
    	    Pixel pix2 = new Pixel(0,0,0); 
    	   int r=0,g=0,b=0;
    	    float[][] matrix = new float[][]{
    	    		/*{1,1,1},
    	            {1,-7,1},
    	            {1,1,1}*/
    	    		{2,0,0},
    	            {0,-1,0},
    	            {0,0,-1}
    	        };
    	    for (int x = 1; x < imageWidth-1; x++) {
    		for (int y = 1; y < imageHeight-1; y++) {
    			r=0;g=0;b=0;
    			
    				for(int k =0;k<3;k++){
    					for(int l=0;l<3;l++){
    						if(x+2<imageWidth && y+2< imageHeight)
    						pix1=picture.getPixel(x+k,y+l);
    						
    						r = r + (int)((pix1.getRed())*(matrix[k][l]));
    						g = g +(int)( (pix1.getGreen())* (matrix[k][l]));
    						b = b + (int)((pix1.getBlue())*(matrix[k][l]));	
    						
    					
    					}
    				}
    		
    				if(r<0) r=0;
    				if(g<0) g=0;
    				if(b<0) b=0;
    				
    				if(r>255) r=255;
    				if(g>255) g=255;
    				if(b>255) b=255;
    				
    				r=127+r;
    				g=127+g;
    				b=127+b;
    				
    				if(r<255 && g<255 && b <255){
    				pix2.setRed(r);
    				pix2.setGreen(g);
    				pix2.setBlue(b);
    				picture.setPixel(x,y,pix2);
    				}
    					
    		}
    	}  
    	    pictureFrame.refresh(picture);
       }
     }
    
    class ScrollHorizontal implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    Pixel pix1, pix2;
	    Pixel pixelArray[][] = new Pixel [40][imageHeight];
	    for (int x= 0; x< 40; x++) {
		for (int y = 0; y < imageHeight; y++) {
		    pix1 = picture.getPixel(x, y);
		    pixelArray[x][y]=pix1;
		}
	   }
	   for(int x=40;x<imageWidth;x++){
	    	for(int y=0;y<imageHeight;y++){
	    		pix1 = picture.getPixel(x, y);
	 		    picture.setPixel(x-40 , y, pix1);
	    	}
	    }
	   int k=0;
	   for(int x=imageWidth-40;x<imageWidth;x++){
		   for(int y= 0;y< imageHeight;y++){
			   picture.setPixel(x,y,pixelArray[k][y]);
		   }
		   k++;
	   }
	    pictureFrame.refresh(picture);
	}
    }
    class Rotate90 implements ActionListener {
	public void actionPerformed (ActionEvent e) {

	    int imageWidth = picture.getWidth();
	    int imageHeight = picture.getHeight();
	    
	    //create a new image
	    Picture newpic = new Picture(imageHeight, imageWidth);
	   
	    for (int x = 0; x < imageWidth; x++) {
		    for (int y = 0; y < imageHeight; y++) {
		        newpic.setPixel(imageHeight - 1 - y , x, picture.getPixel(x, y));
		    }
	    }
        picture.copy(newpic);
	    pictureFrame.refresh(newpic);
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
