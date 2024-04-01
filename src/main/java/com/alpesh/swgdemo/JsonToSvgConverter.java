package com.alpesh.swgdemo;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class JsonToSvgConverter {
    public static void main(String[] args) throws IOException {
        String svgFilePath = "/home/jaydeep/Projects/Temp/test-files/donut_shape.svg";
        // Sample JSON data
        Document document = createSvgCanvas(500, 400, "#ffffff", 255);
        /*Element root = document.getDocumentElement();

        Element roundRect = createRoundRectangle(document, 386.82, 206.86, 202.09, 110.69, "#000000", "#00ff1cff", "#ffffff", 10);
        root.appendChild(roundRect);

        Element line = createLine(document, 295.84, 303.56, 176.1, 63.73, "#000000", "#D9D9D9");
        root.appendChild(line);

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        FileWriter fileWriter = new FileWriter(svgFilePath);
        svgGenerator.stream(fileWriter, true);
        fileWriter.flush();
        fileWriter.close();*/
    }

    public static Element createDonutShape(Document document, double tx, double ty, double w, double h, String fillColor, String strokeColor) {
        Element donut = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");
        donut.setAttributeNS(null, "d", "M0,-37.31552C23.15253,-37.30693,41.9149,-20.59799,41.91008,0.00777C41.90525,20.61353,23.13506,37.31552,-0.01747,37.31552C-23.17001,37.31552,-41.94019,20.61353,-41.94502,0.00777C-41.94984,-20.59799,-23.18747,-37.30693,-0.03494,-37.31552M-0.03494,-24.87701C-15.46882,-24.86843,-27.97399,-13.72839,-27.96917,0.00777C-27.96434,13.74393,-15.45136,24.87701,-0.01747,24.87701C15.41642,24.87701,27.9294,13.74393,27.93423,0.00777C27.93905,-13.72839,15.43388,-24.86843,0,-24.87701Z");
        donut.setAttributeNS(null, "transform", "translate(" + tx + "," + ty + ")");
        donut.setAttributeNS(null, "fill", fillColor);
        donut.setAttributeNS(null, "stroke", strokeColor);
        donut.setAttributeNS(null, "stroke-width", "1");
        return donut;
    }

    public static Document createSvgCanvas(int width, int height, String backgroundColor, int opacity) throws SVGGraphics2DIOException {

        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document doc = impl.createDocument(svgNS, "svg", null);

        Element svgRoot = doc.getDocumentElement();

        svgRoot.setAttributeNS(null, "width", "500");
        svgRoot.setAttributeNS(null, "height", "400");

        Element roundRect = createRoundRectangle(doc, 386.82, 206.86, 202.09, 110.69, "#000000", "#00ff1cff", "#ffffff", 10);
        svgRoot.appendChild(roundRect);
        Element ellipse = createEllipse(doc, 85.78, 79.66, 83.02, 62.05, "#000000", "#000000", 255);
        svgRoot.appendChild(ellipse);
        Element line = createLine(doc, 295.84, 303.56, 176.1, 63.73, "#000000", "#D9D9D9");
        svgRoot.appendChild(line);

        /*Element rectangle = doc.createElementNS(svgNS, "rect");
        rectangle.setAttributeNS(null, "x", "10");
        rectangle.setAttributeNS(null, "y", "20");
        rectangle.setAttributeNS(null, "width", "100");
        rectangle.setAttributeNS(null, "height", "50");
        rectangle.setAttributeNS(null, "fill", "red");

        svgRoot.appendChild(rectangle);*/


        StringWriter writer = new StringWriter();
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);
        svgGenerator.stream(svgRoot, writer);

        String svgXmlContent = writer.toString();
        System.out.println(svgXmlContent);

        return null;
    }

    public static Element createRoundRectangle(Document document, double tx, double ty, double w, double h, String dcColor, String fcStartColor, String fcEndColor, int borderRadius) {
        Element roundRect = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
        roundRect.setAttributeNS(null, "x", String.valueOf(tx));
        roundRect.setAttributeNS(null, "y", String.valueOf(ty));
        roundRect.setAttributeNS(null, "width", String.valueOf(w));
        roundRect.setAttributeNS(null, "height", String.valueOf(h));
        roundRect.setAttributeNS(null, "rx", String.valueOf(borderRadius));
        roundRect.setAttributeNS(null, "ry", String.valueOf(borderRadius));
        roundRect.setAttributeNS(null, "stroke", dcColor);
        roundRect.setAttributeNS(null, "fill", "url(#gradient)");
        roundRect.setAttributeNS(null, "opacity", "1");

        // Add linear gradient
        Element linearGradient = createLinearGradient(document, fcStartColor, fcEndColor);
        document.getDocumentElement().appendChild(linearGradient);

        return roundRect;
    }

    public static Element createLinearGradient(Document document, String startColor, String endColor) {
        Element linearGradient = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "linearGradient");
        linearGradient.setAttributeNS(null, "id", "gradient");
        linearGradient.setAttributeNS(null, "x1", "0%");
        linearGradient.setAttributeNS(null, "x2", "100%");
        linearGradient.setAttributeNS(null, "y1", "50%");
        linearGradient.setAttributeNS(null, "y2", "50%");
        linearGradient.setAttributeNS(null, "spreadMethod", "pad");

        // Add color stops
        Element stop1 = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "stop");
        stop1.setAttributeNS(null, "offset", "0%");
        stop1.setAttributeNS(null, "stop-color", startColor);
        stop1.setAttributeNS(null, "stop-opacity", "1");
        linearGradient.appendChild(stop1);

        Element stop2 = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "stop");
        stop2.setAttributeNS(null, "offset", "100%");
        stop2.setAttributeNS(null, "stop-color", endColor);
        stop2.setAttributeNS(null, "stop-opacity", "1");
        linearGradient.appendChild(stop2);

        return linearGradient;
    }
    public static Element createLine(Document document, double tx, double ty, double w, double h, String dcColor, String fcColor) {
        Element line = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "line");
        line.setAttributeNS(null, "x1", String.valueOf(tx + w / 2)); // Calculate x1 coordinate
        line.setAttributeNS(null, "y1", String.valueOf(ty + h / 2)); // Calculate y1 coordinate
        line.setAttributeNS(null, "x2", String.valueOf(tx - w / 2)); // Calculate x2 coordinate
        line.setAttributeNS(null, "y2", String.valueOf(ty - h / 2)); // Calculate y2 coordinate
        line.setAttributeNS(null, "stroke", dcColor);
        line.setAttributeNS(null, "fill", fcColor);
        line.setAttributeNS(null, "stroke-width", "1");
        line.setAttributeNS(null, "opacity", "1");
        return line;
    }

    public static Element createEllipse(Document document, double tx, double ty, double w, double h, String dcColor, String fcColor, int opacity) {
        Element ellipse = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "ellipse");
        double cx = tx + w / 2; // Calculate cx value for ellipse
        double cy = ty + h / 2; // Calculate cy value for ellipse
        ellipse.setAttributeNS(null, "cx", String.valueOf(cx));
        ellipse.setAttributeNS(null, "cy", String.valueOf(cy));
        ellipse.setAttributeNS(null, "rx", String.valueOf(w / 2)); // Set rx attribute for ellipse
        ellipse.setAttributeNS(null, "ry", String.valueOf(h / 2)); // Set ry attribute for ellipse
        ellipse.setAttributeNS(null, "fill", fcColor);
        ellipse.setAttributeNS(null, "stroke", dcColor);
        ellipse.setAttributeNS(null, "stroke-width", "1");
        ellipse.setAttributeNS(null, "opacity", String.valueOf(opacity / 255.0)); // Convert opacity to float
        return ellipse;
    }
}
