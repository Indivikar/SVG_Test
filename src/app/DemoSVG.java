package app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.scenicview.ScenicView;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DemoSVG extends Application {

	
	float zoomFactor = 10;
	
	@Override
	public void start(Stage stage) throws Exception {

		ImageView githubImage = new ImageView();
//		BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
//		transcoder.addTranscodingHint(ImageTranscoder.KEY_MAX_WIDTH, (float) (300 * zoomFactor));
//		transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) (44 * zoomFactor));
//		transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) (44 * zoomFactor));
////		transcoder.addTranscodingHint(ImageTranscoder.KEY_BACKGROUND_COLOR, Color.black);
////		transcoder.addTranscodingHint(ImageTranscoder.KEY_ALTERNATE_STYLESHEET, "fill:red;");
//		
//		try (InputStream file = DemoSVG.class.getResourceAsStream("Indivikar_logo_kurz_Ink_v2.svg")) {
////		try (InputStream file = DemoSVG.class.getResourceAsStream("indivikar-logo-gross-grau-HP.svg")) {
//			
////			ReplacingInputStream fileFill = new ReplacingInputStream(file, "fill:#2369b5", "fill:#ffffff");
////			ReplacingInputStream fileStroke = new ReplacingInputStream(fileFill, "stroke:#2369b5", "stroke:#ffffff");
////			ReplacingInputStream strokeWidth = new ReplacingInputStream(fileStroke, "stroke-width:0.21600001", "stroke-width:0.0");
//			
//		    TranscoderInput transIn = new TranscoderInput(file);	
//		    
//		    
//		    
//		    try {
//		        transcoder.transcode(transIn, null);
//		        Image img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
//		        githubImage.setImage(img);
//		    } catch (TranscoderException ex) {
//		        ex.printStackTrace();
//		    }
//		}
//		catch (IOException io) {
//		    io.printStackTrace();
//		}
		
//		githubImage.setFitHeight(2000);
//		githubImage.setPreserveRatio(true);
		
		
		
//		//Get Document Builder
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		 
//		//Build Document
//		Document document = builder.parse(DemoSVG.class.getResourceAsStream("Indivikar_logo_kurz_Ink_v2.svg"));
//		 
//		//Normalize the XML Structure; It's just too important !!
//		document.getDocumentElement().normalize();
//		 
//		//Here comes the root node
//		Element element = document.getDocumentElement();
//		System.out.println(element.getNodeName());
//		 
//		//Get all employees
//		NodeList nList = document.getElementsByTagName("svg");
//		System.out.println("============================");
//		 
//		for (int temp = 0; temp < nList.getLength(); temp++)
//		{
//		 Node node = nList.item(temp);
//		 System.out.println("");    //Just a separator
//		 if (node.getNodeType() == Node.ELEMENT_NODE)
//		 {
//		    //Print each employee's detail
//		    Element eElement = (Element) node;
//		    System.out.println("First Name : "  + eElement.getElementsByTagName("height").item(0).getTextContent());
//		    System.out.println("Last Name : "   + eElement.getElementsByTagName("width").item(0).getTextContent());
//		 }
//		}
		
		SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(
                XMLResourceDescriptor.getXMLParserClassName());

        File file = new File(DemoSVG.class.getResource("Indivikar_logo_kurz_Ink_v2.svg").getFile());
        InputStream is = DemoSVG.class.getResourceAsStream("Indivikar_logo_kurz_Ink_v2.svg");
        
        Document document = factory.createDocument(
                file.toURI().toURL().toString(), is);
        UserAgent agent = new UserAgentAdapter();
        DocumentLoader loader = new DocumentLoader(agent);
        BridgeContext context = new BridgeContext(agent, loader);
        context.setDynamic(true);
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode graphicsNode = builder.build(context, document);

        System.out.println(graphicsNode.getPrimitiveBounds().getWidth());
        System.out.println(graphicsNode.getPrimitiveBounds().getHeight());
		
		SVGtoImage svgToImage = new SVGtoImage(DemoSVG.class.getResourceAsStream("Indivikar_logo_kurz_Ink_v2.svg"));
		svgToImage.setFill("#ffffff");
		svgToImage.setBackgroundColor(Color.black);

		
		githubImage.setImage(svgToImage.getImage());
		
		VBox vBox = new VBox(githubImage);
		vBox.setAlignment(Pos.TOP_LEFT);
		AnchorPane.setLeftAnchor(githubImage, 10.0);
		AnchorPane.setTopAnchor(githubImage, 10.0);
//		AnchorPane.setRightAnchor(githubImage, 10.0);
//		AnchorPane.setBottomAnchor(githubImage, 10.0);
		
		AnchorPane root = new AnchorPane(githubImage);
		
		Scene scene = new Scene(root, 1000, 800);
		
		stage.setScene(scene);
		stage.show();	
		
//		ScenicView.show(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	

}
