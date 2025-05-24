package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A minimal example of reading and using the JSON data from resources/sample.json.
 */
public class JSONTranslationExample {

    public static final int CANADA_INDEX = 30;
    private final JSONArray jsonArray;

    // Note: CheckStyle is configured so that we are allowed to omit javadoc for constructors
    public JSONTranslationExample() {
        try {
            // this next line of code reads in a file from the resources folder as a String,
            // which we then create a new JSONArray object from.
            // TODO CheckStyle: Line is longer than 120 characters
            //                  (note: you can split a line such that the next line starts with a .method()... call
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader()
                    .getResource("sample.json").toURI()));
            this.jsonArray = new JSONArray(jsonString); // populates JSONTranslationArray (each object is a dictionary)
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the Spanish translation of Canada.
     * @return the Spanish translation of Canada
     */
    public String getCanadaCountryNameSpanishTranslation() {

        // TODO Checkstyle: '30' is a magic number.
        /*JSONObject canada = this.jsonArray.getJSONObject(CANADA_INDEX);
        return canada.getString("es");*/
        return getCountryNameTranslation("can", "es");
    }

    // TODO Task: Complete the method below to generalize the above to get the country name
    //            for any country code and language code from sample.json.

    /**
     * Returns the name of the country based on the provided country and language codes.
     *
     * @param countryCode the country, as its three-letter code.
     * @param languageCode the language to translate to, as its two-letter code.
     *
     * @return the translation of country to the given language or "Country not found" if there is no translation.
     */
    public String getCountryNameTranslation(String countryCode, String languageCode) {

        JSONTranslator jsonTranslator = new JSONTranslator();
        List<String> countryCodes = jsonTranslator.getCountries();

        int index = countryCodes.indexOf(countryCode); // get index of the country code
        if(index < 0){
            return "Country not found";
        }

        JSONObject countryObj = this.jsonArray.getJSONObject(index); // finds the json object at that index

        if(!countryObj.has(languageCode)){
            return "Country not found";
        }
        return countryObj.getString(languageCode);

    }

    /**
     * Prints the Spanish translation of Canada.
     * @param args not used
     */
    public static void main(String[] args) {
        JSONTranslationExample jsonTranslationExample = new JSONTranslationExample();

        System.out.println(jsonTranslationExample.getCanadaCountryNameSpanishTranslation());
        String translation = jsonTranslationExample.getCountryNameTranslation("can", "es");
        System.out.println(translation);
    }
}
