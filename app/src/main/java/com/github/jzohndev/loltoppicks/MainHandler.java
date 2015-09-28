package com.github.jzohndev.loltoppicks;

import java.io.IOException;

/**
 * Created by Jzohn on 8/9/2015.
 */
public class MainHandler {
    private String popularURL = "http://www.lolking.net/charts?region=na&type=champion-popularity&range=daily&map=sr&queue=normal";
    private String winURL = "http://www.lolking.net/charts?region=na&type=champion-winrate&range=daily&map=sr&queue=normal";
    private String lossURL = "http://www.lolking.net/charts?region=na&type=champion-loserate&range=daily&map=sr&queue=normal";
    private static String[] TopPickChamps = null;
    private static String[] TopWinChamps = null;
    private static String[] TopLossChamps = null;
    private static String[] TopPickChampsPercent = null;
    private static String[] TopWinChampsPercent = null;
    private static String[] TopLossChampsPercent = null;
    private Champions popChamps;
    private Champions winChamps;
    private Champions lossChamps;
    private Website popWeb;
    private Website winWeb;
    private Website lossWeb;

    public MainHandler() throws IOException{
    }

    public void bufferTopPickChamps() throws IOException {
        popChamps = new Champions();
        popWeb = new Website();
        popWeb.setTopPickURL(popularURL);
        TopPickChamps = popChamps.TopPickChamps(popWeb.getTopPick());
        TopPickChampsPercent = popChamps.TopPickChampsPercentage(popWeb.getTopPick());
}

    public void bufferTopWinChamps() throws IOException {
        winChamps = new Champions();
        winWeb = new Website();
        winWeb.setTopWinURL(winURL);
        TopWinChamps = winChamps.TopWinChamps(winWeb.getTopWin());
        TopWinChampsPercent = winChamps.TopWinsChampsPercentage(winWeb.getTopWin());
    }

    public void bufferTopLossChamps() throws IOException {
        lossChamps = new Champions();
        lossWeb = new Website();
        lossWeb.setTopLossURL(lossURL);
        TopLossChamps = lossChamps.TopLossChamps(lossWeb.getTopLoss());
        TopLossChampsPercent = lossChamps.TopLossChampsPercentage(lossWeb.getTopLoss());
    }

    public String[] getTopPickChamps() throws IOException {
        return TopPickChamps;
    }

    public String[] getTopWinChamps() throws IOException {
        return TopWinChamps;
    }

    public String[] getTopLossChamps() throws IOException {
        return TopLossChamps;
    }

    public String[] getTopPickChampsPercent() throws IOException {
        return TopPickChampsPercent;
    }

    public String[] getTopWinChampsPercent() throws IOException {
        return TopWinChampsPercent;
    }

    public String[] getTopLossChampsPercent() throws IOException {
        return TopLossChampsPercent;
    }

    /*private void setURLs(){
        String allPopular = "http://www.lolking.net/charts?region=all&type=champion-popularity&range=daily&map=sr&queue=normal";
        String allWin = "http://www.lolking.net/charts?region=all&type=champion-winrate&range=daily&map=sr&queue=normal";
        String allLoss = "http://www.lolking.net/charts?region=all&type=champion-loserate&range=daily&map=sr&queue=normal";

        String naPopular = "http://www.lolking.net/charts?region=na&type=champion-popularity&range=daily&map=sr&queue=normal";
        String naWin = "http://www.lolking.net/charts?region=na&type=champion-winrate&range=daily&map=sr&queue=normal";
        String naLoss = "http://www.lolking.net/charts?region=na&type=champion-loserate&range=daily&map=sr&queue=normal";

        String euwPopular = "http://www.lolking.net/charts?region=euw&type=champion-popularity&range=daily&map=sr&queue=normal";
        String euwWin = "http://www.lolking.net/charts?region=euw&type=champion-winrate&range=daily&map=sr&queue=normal";
        String euLoss = "http://www.lolking.net/charts?region=euw&type=champion-loserate&range=daily&map=sr&queue=normal";

        String eunePopular = "http://www.lolking.net/charts?region=eune&type=champion-popularity&range=daily&map=sr&queue=normal";
        String euneWin = "http://www.lolking.net/charts?region=eune&type=champion-winrate&range=daily&map=sr&queue=normal";
        String euneLoss = "http://www.lolking.net/charts?region=eune&type=champion-loserate&range=daily&map=sr&queue=normal";

        String brazilPopular = "http://www.lolking.net/charts?region=br&type=champion-popularity&range=daily&map=sr&queue=normal";
        String brazilWin = "http://www.lolking.net/charts?region=br&type=champion-winrate&range=daily&map=sr&queue=normal";
        String brazilLoss = "http://www.lolking.net/charts?region=br&type=champion-loserate&range=daily&map=sr&queue=normal";
    }*/
}
