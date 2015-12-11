package ru.b7.rtphysics.Search;

/**
 * Created by Домашний on 29.11.2015.
 */
public class SearchLogic {

    private String phrase;

    public SearchLogic(String phrase) {

        this.phrase = phrase;

    }

    public String GetLinkedText(String text) {
        if (phrase == null)
            return null;
        else {
            String result = text.replaceAll(phrase, StyleOfLink(phrase));
            return result.matches("(.*|^)" + phrase + "(.*|$)") ? result : null;/*z*/
        }

    }

    private String StyleOfLink(String phrase) {
        return  "<b><font color=\"#ffc107\" size = \"4\" >" + phrase +"</font></b>";
    }
}