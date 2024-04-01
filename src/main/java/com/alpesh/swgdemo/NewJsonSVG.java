package com.alpesh.swgdemo;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewJsonSVG {
    public static void main(String[] args) {
        String jsonData = "{\n" +
                "  \"defs\": {\n" +
                "    \"radialGradient\": {\n" +
                "      \"fill_pattern_14\": {\n" +
                "        \"cx\": 0.5,\n" +
                "        \"cy\": 0.5,\n" +
                "        \"r\": 0.8,\n" +
                "        \"spreadMethod\": \"pad\",\n" +
                "        \"stops\": [\n" +
                "          {\"offset\": \"0%\", \"stop-color\": \"#000000\", \"stop-opacity\": 0},\n" +
                "          {\"offset\": \"30%\", \"stop-color\": \"#0057ff\", \"stop-opacity\": 0.1},\n" +
                "          {\"offset\": \"50%\", \"stop-color\": \"#0057ff\", \"stop-opacity\": 0.3},\n" +
                "          {\"offset\": \"90%\", \"stop-color\": \"#0089ff\", \"stop-opacity\": 1}\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"elementsGroupElem\": [\n" +
                "    {\n" +
                "      \"type\": \"ellipse\",\n" +
                "      \"cx\": 100,\n" +
                "      \"cy\": 100,\n" +
                "      \"rx\": 50,\n" +
                "      \"ry\": 30,\n" +
                "      \"fill\": \"url(#fill_pattern_14)\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"rect\",\n" +
                "      \"x\": 200,\n" +
                "      \"width\": 100,\n" +
                "      \"y\": 150,\n" +
                "      \"height\": 50,\n" +
                "      \"fill\": \"url(#fill_pattern_19)\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JSONObject svgData = new JSONObject(jsonData);
        String svgCode = generateSvg(svgData);
        System.out.println(svgCode);
    }

    public static String generateSvg(JSONObject data) {
        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 500 400\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"500\" version=\"1.1\" height=\"400\">\n");

        // Generate gradients
        JSONObject defs = data.getJSONObject("defs");
        for (String gradientId : defs.keySet()) {
            JSONObject gradient = defs.getJSONObject(gradientId);
            svgBuilder.append(generateGradientElement(gradientId, gradient));
        }

        // Generate elements
        JSONArray elements = data.getJSONArray("elementsGroupElem");
        for (int i = 0; i < elements.length(); i++) {
            JSONObject element = elements.getJSONObject(i);
            svgBuilder.append(generateElement(element));
        }

        svgBuilder.append("</svg>");
        return svgBuilder.toString();
    }

    public static String generateGradientElement(String id, JSONObject gradient) {
        StringBuilder gradientBuilder = new StringBuilder();
        gradientBuilder.append("<radialGradient id=\"").append(id).append("\" ");
        for (String key : gradient.keySet()) {
            if (key.equals("stops")) {
                JSONArray stops = gradient.getJSONArray(key);
                gradientBuilder.append(key).append("=\"[");
                for (int i = 0; i < stops.length(); i++) {
                    JSONObject stop = stops.getJSONObject(i);
                    gradientBuilder.append("{");
                    for (String stopKey : stop.keySet()) {
                        gradientBuilder.append("\"").append(stopKey).append("\":\"").append(stop.get(stopKey)).append("\",");
                    }
                    gradientBuilder.deleteCharAt(gradientBuilder.length() - 1).append("},");
                }
                gradientBuilder.deleteCharAt(gradientBuilder.length() - 1).append("]\" ");
            } else {
                gradientBuilder.append(key).append("=\"").append(gradient.get(key)).append("\" ");
            }
        }
        gradientBuilder.append("></radialGradient>\n");
        return gradientBuilder.toString();
    }

    public static String generateElement(JSONObject element) {
        StringBuilder elementBuilder = new StringBuilder();
        elementBuilder.append("<").append(element.getString("type")).append(" ");
        element.remove("type"); // Remove type from attributes
        for (String key : element.keySet()) {
            elementBuilder.append(key).append("=\"").append(element.get(key)).append("\" ");
        }
        elementBuilder.append("/>\n");
        return elementBuilder.toString();
    }
}
