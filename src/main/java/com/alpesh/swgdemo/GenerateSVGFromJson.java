package com.alpesh.swgdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringWriter;

public class GenerateSVGFromJson {
    public static void main(String[] args) throws IOException {
        // JSON input sample
        String jsonData = "{\"_user\":{\"_name\":\"Andrei\",\"_type\":2},\"_v\":\"1.0\",\"_title\":\"Sample Project\",\"_canvas\":{\"_w\":500,\"_h\":400,\"_c\":{\"_c\":\"#ffffff\",\"_o\":\"255\"}},\"_gs\":[{\"_c\":\"#FF0000\",\"_st\":0,\"_sz\":1,\"_v\":1,\"_pos\":25},{\"_c\":\"#FF0000\",\"_st\":0,\"_sz\":1,\"_v\":1,\"_pos\":475},{\"_c\":\"#FF0000\",\"_st\":0,\"_sz\":1,\"_v\":0,\"_pos\":20},{\"_c\":\"#FF0000\",\"_st\":0,\"_sz\":1,\"_v\":0,\"_pos\":380}],\"_objects\":[{\"_idx\":\"14\",\"_t\":\"Ellipse\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":85.78,\"_ty\":79.66,\"_x\":-41.51,\"_y\":-31.03,\"_w\":83.02,\"_h\":62.05,\"_a\":0,\"_dc\":{\"_c\":\"#000000\",\"_o\":\"255\"},\"_fc\":{\"_c\":\"#000000\",\"_o\":\"255\",\"_sm\":\"pad\",\"_ca\":[{\"_c\":\"#000000\",\"_f\":\"0%\",\"_o\":0},{\"_c\":\"#0057ff\",\"_f\":\"30%\",\"_o\":0.1},{\"_c\":\"#0057ff\",\"_f\":\"50%\",\"_o\":0.3},{\"_c\":\"#0089ff\",\"_f\":\"90%\",\"_o\":1}],\"_cx\":0.5,\"_cy\":0.5,\"_r\":0.8,\"_rt\":0,\"_usy\":1,\"_sy\":1,\"_sx\":1,\"_ay\":-90},\"_op\":1,\"_xtype\":\"ellipse\",\"_br\":0},{\"_idx\":\"16\",\"_t\":\"Trapezoid\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":281.17,\"_ty\":103.56,\"_x\":224.56,\"_y\":63.73,\"_w\":113.2,\"_h\":79.66,\"_a\":0,\"_dw\":2,\"_dc\":{\"_c\":\"#3CAF00\",\"_o\":\"255\"},\"_fc\":{\"_c\":\"#56E30C\",\"_o\":\"255\"},\"_op\":1,\"_xtype\":\"shape\",\"_sc\":{\"_id\":\"trapezoid\",\"_t\":\"Trapezoid\",\"_p\":\"M0,24l4,-24h16l4,24Z\",\"_w\":24,\"_h\":24},\"_path\":\"M-56.60219,39.83117L-37.73479,-39.83117L37.73479,-39.83117L56.60219,39.83117Z\"},{\"_idx\":\"18\",\"_t\":\"Path\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":175.34,\"_ty\":241.7,\"_x\":-111.36,\"_y\":-90.77,\"_w\":222.72,\"_h\":181.53,\"_a\":0,\"_dc\":{\"_c\":\"#000000\",\"_o\":\"255\"},\"_fc\":{\"_c\":\"none\",\"_o\":\"0\"},\"_op\":1,\"_xtype\":\"path\",\"_points\":[],\"_sgs\":[{\"_t\":\"M\",\"_id\":1,\"_c\":0,\"_v\":[-67.33634,-1.87022],\"_js\":{\"_ast\":{},\"_t\":\"Move\"}},{\"_t\":\"C\",\"_id\":3,\"_c\":1,\"_v\":[-126.03491,-41.6958,-126.03491,-91.59517,-67.33634,-90.75662],\"_js\":{\"_opp\":1,\"_ast\":{},\"_t\":\"Bezier 1\"}},{\"_t\":\"C\",\"_id\":5,\"_c\":1,\"_v\":[-7.79922,-89.07952,138.10865,-72.29732,107.08226,-41.6958],\"_js\":{\"_opp\":1,\"_ast\":{},\"_t\":\"Bezier 2\"}},{\"_t\":\"C\",\"_id\":7,\"_c\":1,\"_v\":[71.02457,-10.25573,60.96196,33.34892,25.74282,-1.87022],\"_js\":{\"_opp\":1,\"_ast\":{},\"_t\":\"Bezier 3\"}},{\"_t\":\"C\",\"_id\":9,\"_c\":1,\"_v\":[-9.47632,-37.08936,34.12833,-74.82415,-7.79922,-1.87022],\"_js\":{\"_opp\":1,\"_ast\":{},\"_t\":\"Bezier 4\"}},{\"_t\":\"C\",\"_id\":11,\"_c\":1,\"_v\":[-53.91953,72.76082,-57.27373,116.36547,-67.33634,74.43792],\"_js\":{\"_opp\":1,\"_ast\":{},\"_t\":\"Bezier 5\"}}]},{\"_idx\":\"19\",\"_t\":\"Round Rectangle\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":386.82,\"_ty\":206.86,\"_x\":-101.05,\"_y\":-55.34,\"_w\":202.09,\"_h\":110.69,\"_a\":0,\"_dc\":{\"_c\":\"#000000\",\"_o\":\"255\"},\"_fc\":{\"_o\":\"\",\"_x1\":\"0%\",\"_x2\":\"100%\",\"_y1\":\"50%\",\"_y2\":\"50%\",\"_sm\":\"pad\",\"_ca\":[{\"_c\":\"#00ff1cff\",\"_f\":\"0%\",\"_o\":1},{\"_c\":\"#ffffff\",\"_f\":\"100%\",\"_o\":1}],\"_cx\":0.5,\"_cy\":0.5,\"_r\":1,\"_rt\":0,\"_usy\":1,\"_sy\":1,\"_sx\":1,\"_ay\":-90},\"_op\":1,\"_xtype\":\"rect\",\"_br\":15},{\"_idx\":\"20\",\"_t\":\"Image\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":249.25,\"_ty\":197.7,\"_x\":0,\"_y\":0,\"_w\":176.25,\"_h\":136.5,\"_a\":0,\"_dc\":{\"_c\":\"#000000\",\"_o\":\"255\"},\"_fc\":{\"_c\":\"#000000\",\"_o\":\"255\",\"_sm\":\"pad\",\"_ca\":[{\"_c\":\"#000000\",\"_f\":\"0%\",\"_o\":1},{\"_c\":\"#0057ff\",\"_f\":\"30%\",\"_o\":1},{\"_c\":\"#0057ff\",\"_f\":\"50%\",\"_o\":1},{\"_c\":\"#0089ff\",\"_f\":\"90%\",\"_o\":1}],\"_cx\":0.5,\"_cy\":0.5,\"_r\":1,\"_rt\":0,\"_usy\":1,\"_sy\":1,\"_sx\":1,\"_ay\":-90},\"_op\":1,\"_xtype\":\"image\",\"_br\":0,\"_img\":{\"_st\":\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAAAAAAAD/4QBYRXhpZgAATU0AKgAAAAgABQESAAMAAAABAAEAAAEaAAUAAAABAAAASgEbAAUAAAABAAAAUgEoAAMAAAABAAIAAAExAAIAAAAeAAAAWodpAAQAAAABAAAAegAAAAAAABBkb2MAAAAAAAAAAABhbmQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA...\"}},{\"_idx\":\"21\",\"_t\":\"Text\",\"_evl\":[],\"_trx\":0,\"_try\":0,\"_tx\":333.64,\"_ty\":235.4,\"_x\":-9.77,\"_y\":-14.38,\"_w\":24.1,\"_h\":17.4,\"_a\":0,\"_dc\":{\"_c\":\"#000000\",\"_o\":\"255\"},\"_fc\":{\"_c\":\"#000000\",\"_o\":\"255\",\"_sm\":\"pad\",\"_ca\":[{\"_c\":\"#000000\",\"_f\":\"0%\",\"_o\":1},{\"_c\":\"#0057ff\",\"_f\":\"30%\",\"_o\":1},{\"_c\":\"#0057ff\",\"_f\":\"50%\",\"_o\":1},{\"_c\":\"#0089ff\",\"_f\":\"90%\",\"_o\":1}],\"_cx\":0.5,\"_cy\":0.5,\"_r\":1,\"_rt\":0,\"_usy\":1,\"_sy\":1,\"_sx\":1,\"_ay\":-90},\"_op\":1,\"_txt\":\"Sample Text\",\"_ff\":\"Helvetica\",\"_fs\":\"normal\",\"_fw\":400,\"_fl\":\"normal\",\"_fn\":10,\"_ls\":0,\"_tl\":\"left\",\"_tb\":0,\"_co\":{\"_c\":\"none\",\"_o\":\"0\"}}]}";

        // Parse JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        // Create a DOMImplementation
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();

        // Create an SVG document
        String svgNamespaceURI = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document document = domImpl.createDocument(svgNamespaceURI, "svg", null);
        Element root = document.getDocumentElement();

        // Set SVG attributes (width, height, viewBox)
        root.setAttributeNS(null, "width", jsonNode.path("_canvas").path("_w").asText());
        root.setAttributeNS(null, "height", jsonNode.path("_canvas").path("_h").asText());
        root.setAttributeNS(null, "viewBox", "0 0 " + jsonNode.path("_canvas").path("_w").asText() + " " + jsonNode.path("_canvas").path("_h").asText());

        // Create a group for SVG elements
        Element group = document.createElementNS(svgNamespaceURI, "g");
        root.appendChild(group);

        // Parse JSON data and create SVG elements
        parseJsonAndCreateSvgElements(jsonNode, group, document);

        // Save SVG document to a file
        StringWriter writer = new StringWriter();
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.stream(root, writer);
        String svgXmlContent = writer.toString();
        System.out.println(svgXmlContent);
    }

    private static void parseJsonAndCreateSvgElements(JsonNode jsonNode, Element group, Document document) {
        JsonNode objectsNode = jsonNode.path("_objects");
        for (JsonNode objectNode : objectsNode) {
            String type = objectNode.path("_t").asText();
            switch (type) {
                case "Ellipse":
                    createEllipse(objectNode, group, document);
                    break;
                case "Trapezoid":
                    createTrapezoid(objectNode, group, document);
                    break;
                case "Path":
                    createPath(objectNode, group, document);
                    break;
                // Add cases for other object types as needed
                default:
                    // Handle unknown object types
                    break;
            }
        }
    }

    private static void createEllipse(JsonNode objectNode, Element group, Document document) {
        // Create Ellipse element and add attributes based on JSON data
        Element ellipse = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "ellipse");
        ellipse.setAttributeNS(null, "cx", objectNode.path("_tx").asText());
        ellipse.setAttributeNS(null, "cy", objectNode.path("_ty").asText());
        ellipse.setAttributeNS(null, "rx", objectNode.path("_w").asText());
        ellipse.setAttributeNS(null, "ry", objectNode.path("_h").asText());
        ellipse.setAttributeNS(null, "style", "stroke: " + objectNode.path("_dc").path("_c").asText() + "; fill: " + objectNode.path("_fc").path("_c").asText() + ";");
        group.appendChild(ellipse);
    }

    private static void createTrapezoid(JsonNode objectNode, Element group, Document document) {
        // Create Trapezoid element and add attributes based on JSON data
        // Implementation for Trapezoid creation goes here
    }

    private static void createPath(JsonNode objectNode, Element group, Document document) {
        // Create Path element and add attributes based on JSON data
        // Implementation for Path creation goes here
    }
}

