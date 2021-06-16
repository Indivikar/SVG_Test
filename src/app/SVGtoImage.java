package app;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.ImageTranscoder;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class SVGtoImage {


    private float zoomFactor = 1;
	private InputStream is;
	private ReplacingInputStream replaceFill;
	private ReplacingInputStream replaceStroke;
	private ReplacingInputStream replaceStrokeWidth;
	private int width = 300;
	private int height = 44;
	private Color color;


	public SVGtoImage(InputStream is) {		
		this.is = is;
	}

    public void setZoomFactor(float zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
	
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setFill(String fillCode) {
    	System.out.println("setFill");
        this.replaceFill = new ReplacingInputStream(is, "fill:#2369b5", "fill:" + fillCode);
        this.replaceStroke = new ReplacingInputStream(replaceFill, "stroke:#2369b5", "stroke:" + fillCode);
        this.replaceStrokeWidth = new ReplacingInputStream(replaceStroke, "stroke-width:0.21600001", "stroke-width:0.0");
    }
    
    public void setBackgroundColor(Color color) {
        this.color = color;
    }

    public Image getImage() {
    	System.out.println("getImage");
    	BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
		transcoder.addTranscodingHint(ImageTranscoder.KEY_MAX_WIDTH, (float) (width * zoomFactor));
		transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) (height * zoomFactor));
		if (color != null) {
			transcoder.addTranscodingHint(ImageTranscoder.KEY_BACKGROUND_COLOR, color);
		}
			TranscoderInput transIn;
			if (replaceStrokeWidth == null) {
				System.out.println("getImage 1");
				transIn = new TranscoderInput(is);					
			} else {
				System.out.println("getImage 2");
				transIn = new TranscoderInput(replaceStrokeWidth);
			}
  
		    try {
		        transcoder.transcode(transIn, null);
		        Image img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
		        return img;
		    } catch (TranscoderException ex) {			    	
		        ex.printStackTrace();
		        return null;
		    }	
	}
}
