package com.alpesh.swgdemo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@Log4j2
public class SVGController {

    @GetMapping(value = "/get", produces = MediaType.TEXT_PLAIN_VALUE)

    public String getSWGFile (@RequestBody JsonNode jsonNode) {
        log.info("input received is {} ", jsonNode);
        return generateSVG1(jsonNode);
    }

    public String generateSVG (JsonNode jsonInput) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        int width = jsonInput.get("_canvas").get("_w").asInt();
        int height = jsonInput.get("_canvas").get("_h").asInt();

        // Write SVG Header
        printWriter.println("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"" + " version=\"1.1\" width=\"" + width + "\"" + " height=\"" + height + "\" viewBox=\"0 0 " + width + " " + height + "\">");

        // Write SVG objects
        JsonNode objects = jsonInput.get("_objects");
        for (JsonNode object : objects) {
            String type = object.get("_xtype").asText();
            // Implement additional object types as needed
            switch (type) {
                case "ellipse":
                    printWriter.printf("<ellipse cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\"/>\n", object.get("_tx").asText(), object.get("_ty").asText(), object.get("_w").asText(), object.get("_h").asText());
                    break;
                case "line":
                    printWriter.printf("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"/>\n", object.get("_x1").asText(), object.get("_y1").asText(), object.get("_x2").asText(), object.get("_y2").asText());
                    break;
                case "rect":
                    printWriter.printf("<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"/>\n", object.get("_x").asText(), object.get("_y").asText(), object.get("_w").asText(), object.get("_h").asText());
                    break;
            }
        }

        // Write SVG Footer
        printWriter.println("</svg>");
        printWriter.close();

        String svgContent = stringWriter.toString();
        return svgContent;
    }

    public String generateSVG1 (JsonNode jsonInput) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        int width = jsonInput.get("_canvas").get("_w").asInt();
        int height = jsonInput.get("_canvas").get("_h").asInt();

        // Write SVG Header
        printWriter.println("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"" + " version=\"1.1\" width=\"" + width + "\"" + " height=\"" + height + "\" viewBox=\"0 0 " + width + " " + height + "\">");

        // Write SVG objects
        JsonNode objects = jsonInput.get("_objects");
        for (JsonNode object : objects) {
            String type = object.get("_xtype").asText();
            switch (type) {
                case "ellipse":
                    printEllipse(printWriter, object);
                    break;
                case "line":
                    printLine(printWriter, object);
                    break;
                case "rect":
                    printRect(printWriter, object);
                    break;
                case "shape":
                    printShape(printWriter, object);
                    break;
                case "path":
                    printPath(printWriter, object);
                    break;
                case "text":
                    printText(printWriter, object);
                    break;
                // Add more cases for other object types as needed
                default:
                    // Handle unsupported types
                    break;
            }
        }

        // Write SVG Footer
        printWriter.println("</svg>");
        printWriter.close();

        return stringWriter.toString();
    }

    private void printEllipse (PrintWriter printWriter, JsonNode object) {
        printWriter.printf("<ellipse cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\"/>\n", object.get("_tx").asText(), object.get("_ty").asText(), object.get("_w").asText(), object.get("_h").asText());
    }

    private void printLine (PrintWriter printWriter, JsonNode object) {
        printWriter.printf("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"/>\n", object.get("_x1").asText(), object.get("_y1").asText(), object.get("_x2").asText(), object.get("_y2").asText());
    }

    private void printRect (PrintWriter printWriter, JsonNode object) {
        printWriter.printf("<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"/>\n", object.get("_x").asText(), object.get("_y").asText(), object.get("_w").asText(), object.get("_h").asText());
    }

    private void printShape (PrintWriter printWriter, JsonNode object) {
        // Implement printing shape based on provided data
    }

    private void printPath (PrintWriter printWriter, JsonNode object) {
        // Implement printing path based on provided data
    }

    private void printText (PrintWriter printWriter, JsonNode object) {
        // Implement printing text based on provided data
    }


}