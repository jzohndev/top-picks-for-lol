package com.github.jzohndev.loltoppicks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;

/**
 * Created by Jzohn on 7/17/2015.
 */
public class Website {
    private String TopPickURL = null;
    private String TopWinURL = null;
    private String TopLossURL = null;
    private String currentLine = null;
    private String htmlSource = null;

    public void setTopPickURL(String theTopPickURL) {
        TopPickURL = theTopPickURL;
    }

    public void setTopWinURL(String theTopWinURL) {
        TopWinURL = theTopWinURL;
    }

    public void setTopLossURL(String theTopLossURL) {
        TopLossURL = theTopLossURL;
    }

    public String getTopPick() throws IOException {
        URL url = new URL(TopPickURL);
        BufferedReader openHtml = new BufferedReader(new InputStreamReader(url.openStream()));
        while (((currentLine = openHtml.readLine()) != null)){
            htmlSource += currentLine;
        }
        return htmlSource;
    }

    public String getTopWin() throws IOException {
        URL url = new URL(TopWinURL);
        BufferedReader openHtml = new BufferedReader(new InputStreamReader(url.openStream()));
        while (((currentLine = openHtml.readLine()) != null)){
            htmlSource += currentLine;
        }
        return htmlSource;
    }

    public String getTopLoss() throws IOException {
        URL url = new URL(TopLossURL);
        BufferedReader openHtml = new BufferedReader(new InputStreamReader(url.openStream()));
        while (((currentLine = openHtml.readLine()) != null)){
            htmlSource += currentLine;
        }
        return htmlSource;
    }
}
























    /**
    private int[] hitLineNumber = new int[14];

    private static int lineNumber = 0;
    private String[] championArray = new String[14];
    private String champHit = null;

    public String getSource(String theWebsiteURL) throws IOException {
        URL url = null;
        BufferedReader in = null;
        String inputLine = "";
        String source = "";
        Crawler champs;

        url = new URL(theWebsiteURL);
        in = new BufferedReader(new InputStreamReader(url.openStream()));         //throws IOException
        champs = new Crawler();

        while ((inputLine = in.readLine()) != null){
            lineNumber ++;
            champHit = champs.compareLoop(inputLine);

            if (champHit != null && (!(Arrays.asList(championArray).contains(champHit)))) { //loop to see if the champion has already been added){
                for (int y = 0; y < 15; y++){
                    championArray[y] = champHit;
                    hitLineNumber[y] = lineNumber;
                }
            }
        }

        in.close();

    }

}**/
