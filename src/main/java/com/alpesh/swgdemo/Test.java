package com.alpesh.swgdemo;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args) {
        String svgFilePath = "/home/jaydeep/Projects/Temp/test-files/donut_shape.svg";
        // Sample JSON data
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
        Element root = document.getDocumentElement();

        // Create and add donut shape
        Element donutShape = createDonutShape(document, 395.62, 93.5, 83.86, 74.63, "#D9D9D9", "#000000");
        root.appendChild(donutShape);

        // Generate SVG file
        generateSvgFile(document, svgFilePath);
    }

    public static void generateSvgFile(Document document, String filePath) {

        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            // Use Batik's SVGGraphics2D to write the document to an output stream
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
            svgGenerator.stream(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
