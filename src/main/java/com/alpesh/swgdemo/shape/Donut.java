package com.alpesh.swgdemo.shape;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Donut {

    public static Element createDonutShape(Document document, int outerRadius, int innerRadius, String fillColor, String backgroundColor) {
        // Create the path data for the donut shape
        String pathData = "M" + (outerRadius + innerRadius) + " " + outerRadius + "A" + outerRadius + " " + outerRadius + " 0 1 0 " + (outerRadius + innerRadius) + " " + (3 * outerRadius) +
                "A" + outerRadius + " " + outerRadius + " 0 1 0 " + (outerRadius + innerRadius) + " " + outerRadius +
                "M" + (outerRadius - innerRadius) + " " + outerRadius + "A" + innerRadius + " " + innerRadius + " 0 1 1 " + (outerRadius - innerRadius) + " " + (3 * outerRadius) +
                "A" + innerRadius + " " + innerRadius + " 0 1 1 " + (outerRadius - innerRadius) + " " + outerRadius;

        // Create the donut shape path element
        Element path = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");
        path.setAttributeNS(null, "d", pathData);
        path.setAttributeNS(null, "fill", fillColor);
        path.setAttributeNS(null, "stroke", "none"); // optional: no stroke for the shape
        path.setAttributeNS(null, "stroke-width", "0"); // optional: set stroke width to 0

        // Create a background circle to simulate the donut hole
        Element backgroundCircle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "circle");
        backgroundCircle.setAttributeNS(null, "cx", String.valueOf(outerRadius));
        backgroundCircle.setAttributeNS(null, "cy", String.valueOf(outerRadius));
        backgroundCircle.setAttributeNS(null, "r", String.valueOf(innerRadius));
        backgroundCircle.setAttributeNS(null, "fill", backgroundColor);

        // Create a group element to contain both the donut shape and background circle
        Element group = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "g");
        group.appendChild(backgroundCircle);
        group.appendChild(path);

        return group;
    }


    private static void drawRectangle(SVGGraphics2D svgGenerator, JSONObject object) {
        double x = object.getDouble("_x");
        double y = object.getDouble("_y");
        double width = object.getDouble("_w");
        double height = object.getDouble("_h");
        svgGenerator.draw(new Rectangle2D.Double(x, y, width, height));
    }

    private static void drawEllipse(SVGGraphics2D svgGenerator, JSONObject object) {
        double cx = object.getDouble("_cx");
        double cy = object.getDouble("_cy");
        double rx = object.getDouble("_rx");
        double ry = object.getDouble("_ry");
        svgGenerator.draw(new Ellipse2D.Double(cx - rx, cy - ry, 2 * rx, 2 * ry));
    }

    private static void drawLine(SVGGraphics2D svgGenerator, JSONObject object) {
        double x1 = object.getDouble("_x1");
        double y1 = object.getDouble("_y1");
        double x2 = object.getDouble("_x2");
        double y2 = object.getDouble("_y2");
        svgGenerator.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    private static void drawPath(SVGGraphics2D svgGenerator, JSONObject object) {
        JSONArray points = object.getJSONArray("_pts");
        Path2D.Double path = new Path2D.Double();
        for (int i = 0; i < points.length(); i++) {
            JSONObject point = points.getJSONObject(i);
            double x = point.getDouble("_x");
            double y = point.getDouble("_y");
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        svgGenerator.draw(path);
    }

    private static Color getColorFromKey(JSONObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            String colorString = jsonObject.getString(key);
            return Color.decode(colorString);
        }
        return Color.black; // Default color if key not found
    }

    public static Element createCustomShape(Document document, String pathData, String fillColor) {
        Element path = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");
        path.setAttributeNS(null, "d", pathData);
        path.setAttributeNS(null, "fill", fillColor);
        return path;
    }
}
