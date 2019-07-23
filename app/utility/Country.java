package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Country {

    private static Country INSTANCE;

    private String baseURL = "https://restcountries.eu/rest/v2/";

    /**
     * static method to create instance of Country class
     */
    public static Country getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new Country();

        return INSTANCE;
    }

    private Country(){}


    /**
     * Get the response from a URL as a string
     * @param requestURL - The request url
     * @return response - String response from url
     * @throws IOException
     */
    private String getResponseFromRequest(URL requestURL) throws IOException {
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = reader.lines().collect(Collectors.joining());


        return response;
    }


    /**
     * Get the response values of a given request for a given field
     * @param request - The request url as a string
     * @param targetField - The field to retrieve values for
     * @return A list of JsonNode objects holding the values found
     * @throws IOException
     */
    private List<JsonNode> getResponseValues(String request, String targetField) throws IOException {
        URL url = new URL(request);
        String response = getResponseFromRequest(url);
        ObjectMapper mapper = new ObjectMapper();


        return mapper.readTree(response).findValues(targetField);
    }


    /**
     * Get a list of all countries from the RESTCountries api
     *
     * @return countries - List of all country names
     */
    public List<String> getAllCountries() {
        List<String> countries = new ArrayList<>();
        try {
            List<JsonNode> valuesNode = getResponseValues(baseURL+"all?fields=name;", "name");
            for (JsonNode node : valuesNode) {
                countries.add(node.asText());   //Ensure countries are populated as text entry!
            }
        } catch (IOException exception) {
            return countries;
        }


        return countries;
    }


    /**
     * Get a country's name from its ISO code
     * @param code - the ISO code
     * @return name - Name of the country
     */
    public String getCountryNameByCode(String code) {
        String name = "";
        try {
            JsonNode valuesNode = getResponseValues(baseURL + "alpha/" + code, "name").get(0);
            name = valuesNode.asText();
        } catch (IOException exception) {
            return name;
        }


        return name;
    }


    /**
     * Check if a country exists
     * @param name - Name of country to check
     * @return boolean - True if exists, false otherwise
     */
    public boolean checkExists(String name) {
        boolean exists = true;
        try {
            List<JsonNode> node = getResponseValues(baseURL + "name/" + name, "name");
            exists = !node.get(0).asText().equals("404");
        } catch (IOException exception) {
            return false;
        }


        return exists;
    }

}
