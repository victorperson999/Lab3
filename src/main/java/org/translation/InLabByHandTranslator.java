package org.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// TODO Task: modify this class so that it also supports the Spanish language code "es" and
//            one more language code of your choice. Each member of your group should add
//            support for one additional langauge code on a branch; then push and create a pull request on GitHub.

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */
    public final static String CANADA = "can";
    public final static String USA = "us";
    public final static String MEXICO = "mex";

    private final static Map<String, Map<String, String>> TRANSLATIONS =
            Map.of(
                    CANADA, Map.of(
                            "de", "Kanada",
                            "en", "Canada",
                            "zh", "加拿大",
                            "es", "Canadá",
                            "ru", "Канада",
                            "fr", "Le Canada"
                    ),
                    USA, Map.of(
                            "en", "Canada",
                            "fr", "Le Canada",
                            "es", "Canadá"
                    ),
                    MEXICO, Map.of(
                            "es", "Canadá"
                    )
            );

    @Override
    public List<String> getCountryLanguages(String country) {
        // TODO Checkstyle: The String "can" appears 4 times in the file.
        /*if (CANADA.equals(country)) {
            return new ArrayList<>(List.of("de", "en", "zh", "es", "ru"));
        }else if(USA.equals(country)){
            return new ArrayList<>(List.of("en","fr","es"));
        }else if(MEXICO.equals(country)){
            return new ArrayList<>(List.of("es"));
        }
        return new ArrayList<>();*/
        Map<String, String> langs = TRANSLATIONS.get(country);
        if(langs == null){
            return new ArrayList<>();
        }
        return new ArrayList<>(langs.keySet());
    }

    // TODO Checkstyle: Static variable definition in wrong order.

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        // TODO Checkstyle: Return count is 5 (max allowed for non-void methods/ lambdas is 2).
        // TODO Checkstyle: String literal expressions should be on the left side of an equals comparison
        if (!CANADA.equals(country)) {
            return null;
        }

//        if (language.equals("de")) {
//            return "Kanada";
//        }
//        else if (language.equals("en")) {
//            return "Canada";
//        }
//        else if (language.equals("zh")) {
//            return "加拿大";
//        }
//        else {
//            return null;
//        }
        /*switch(language){ // switch cases much more efficient
            case "de":
                return "Kanada";
            case "en":
                return "Canada";
            case "zh":
                return "加拿大";
            case "es":
                return "Canadá";
            case "ru":
                return "Канада";
            case "fr":
                return "le Canada";
            default:
                return null;
        }*/
        Map<String, String> langs = TRANSLATIONS.get(country);
        if(langs == null){
            return null;
        }
        return langs.get(language);
    }
}
