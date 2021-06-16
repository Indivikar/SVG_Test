module SVG_Test {
	exports app;
	
	requires java.desktop;
	
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.swing;
		
	requires jdk.xml.dom;
	
	requires batik.anim;
	requires batik.awt.util;
	requires batik.bridge;
	requires batik.constants;
	requires batik.css;
	requires batik.dom;
	requires batik.gvt;
	requires batik.i18n;
	requires batik.shared.resources;
	requires batik.svg.dom;
//	requires batik.svggen;
	requires batik.transcoder;
	requires batik.util;
	requires batik.xml;
	requires batik.ext;
	requires batik.script;
	requires batik.parser;
	
//	requires rhino;
//	requires jython;
//	requires xml.apis;
	requires xml.apis.ext;
	requires org.scenicview.scenicview;
	requires java.xml;
	requires xmlgraphics.commons;
}