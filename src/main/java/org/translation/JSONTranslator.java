package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // TODO Task: pick appropriate instance variables for this class

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    private List<String> countryCodes = new ArrayList<>();
    private List<List<String>> languageCodes = new ArrayList<>(); // stores lists of languagecode for each country in json
    private List<Map<String, String>> translations = new ArrayList<>();

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // TODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.

            // 1) populate the countryCodes instance variable
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                String alpha3 = obj.getString("alpha3");
                countryCodes.add(alpha3);

                List<String> countryCodeSet = new ArrayList<>();
                Map<String, String> mapTranslations = new HashMap<>();

                for(String key: obj.keySet()){ // for each json array object (obj) we get the keyset
                    if(key.equals("id")||key.equals("alpha2")||key.equals("alpha3")){
                        continue;
                    }
                    countryCodeSet.add(key); // for each key we add to the temp arraylist
                    mapTranslations.put(key, obj.getString(key));
                }
                this.languageCodes.add(countryCodeSet);
                this.translations.add(mapTranslations);
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TODO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        String countryCode = country.toLowerCase();
        // we have the correct country code now, obtain the list of language codes
        int index = this.countryCodes.indexOf(countryCode);
        if(index<0){
            throw new IllegalArgumentException("Unknown country" + country);
        }
        return new ArrayList<>(this.languageCodes.get(index));
    }

    @Override
    public List<String> getCountries() {
        // TODO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        return new ArrayList<>(this.countryCodes); // this returns the contents of all the country codes
    }

    @Override
    public String translate(String country, String language) {
        // TODO Task: complete this method using your instance variables as needed
        String countryCode = country.toLowerCase();
        // again, we have the correct country code
        int index = this.countryCodes.indexOf(countryCode);

        if (index < 0) {
            throw new IllegalArgumentException("Unknown country: " + country);
        }
        // get the correct translation map
        Map<String, String> map = this.translations.get(index); // get the correct map within translations given index of the countrycode

        if(!map.containsKey(language)){
            throw new IllegalArgumentException(
                    "No translation for language '" + language + "' in country " + country);
        }
        return map.get(language);
    }

}
