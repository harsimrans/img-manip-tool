 

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import org.imgscalr.Scalr;

import static org.imgscalr.Scalr.*;

public class Picture {

    public static int COLOR = BufferedImage.TYPE_INT_RGB;
    public static int GRAY = BufferedImage.TYPE_BYTE_GRAY;

    protected static int defaultImageType = BufferedImage.TYPE_INT_RGB;

    protected int imageType;
    protected BufferedImage bufferedImage;
   // protected Image rImg;
    
    protected WritableRaster raster;

    Picture() { 
	imageType = defaultImageType;
	bufferedImage = null;
	raster = null;
    }

    Picture(int width, int height) {
	this(width, height, defaultImageType);
    }

    Picture(int width, int height, int type) {
	bufferedImage = new BufferedImage(width, height, type);
	raster = bufferedImage.getRaster();
	imageType = type;
    }

    Picture(String filename) {
	this(filename, defaultImageType);
    }

    Picture(String filename, int type) {
	if ((type != COLOR) && (type != GRAY)){
		type = defaultImageType; 
		}
	imageType = type;
	load(filename);
	
    }

    public int getImageType() { return imageType; }

    public int getWidth() { return bufferedImage.getWidth(); }
    public int getHeight() { return bufferedImage.getHeight(); }

    public BufferedImage getBufferedImage() { return bufferedImage; }
    public WritableRaster getRaster() { return raster; }

    public void load(String filename) {
	ImageIcon icon = new ImageIcon(filename);
	Image image = icon.getImage();
	bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),imageType);
	if(image.getHeight(null)<1024 && image.getWidth(null)<1024){
	Graphics g = bufferedImage.getGraphics();
	System.out.print("in first");
	g.drawImage(image, 0, 0, null);
	g.dispose();
	raster = bufferedImage.getRaster();
	}
	else{
		bufferedImage=Scalr.resize(bufferedImage, Scalr.Method.QUALITY,Scalr.Mode.AUTOMATIC,1024,900);
		Graphics g = bufferedImage.getGraphics();
		System.out.print("in second");
		g.drawImage(image, 0, 0,1024,900, null);
		g.dispose();
		raster = bufferedImage.getRaster();
	}
   }


    public void copy(Picture p) {
	imageType = p.getImageType();

	bufferedImage = new BufferedImage(p.getWidth(),
					  p.getHeight(),
					  imageType);

	raster = bufferedImage.getRaster();
	
	for (int x = 0; x < bufferedImage.getWidth(); x++) {
	    for (int y = 0; y < bufferedImage.getHeight(); y++) {
		setPixel(x,y, p.getPixel(x, y));
	    }
	}
    }

    public Pixel getPixel(int x, int y) {
	int[] pixelArray = null;

	try {
	    pixelArray = raster.getPixel(x, y, pixelArray); 
	} catch (Exception e) {
	    System.out.println("("+x+", "+y+") out of bounds.");
	    System.exit(1);
	}

	Pixel pixel = new Pixel(pixelArray);

	return pixel;
    }


    public void setPixel(int x, int y, Pixel pixel) {
	int[] pixelArray = pixel.getComponents();

	try {
	    raster.setPixel(x, y, pixelArray);
	} catch (Exception e) {
	    System.out.println("("+x+", "+y+") out of bounds.");
	    System.exit(1);
	}
    }
    
}
