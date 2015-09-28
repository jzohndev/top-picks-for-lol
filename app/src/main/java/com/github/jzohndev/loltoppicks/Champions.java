package com.github.jzohndev.loltoppicks;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jzohn on 7/17/2015.
 */
public class Champions {
    private String[] tempHtmlSourceArray = null;
    private String splitHtmlSource = null;
    private String[] topChamps = new String[10];
    private String[] topChampsPercent = new String[10];
    private String[] champList = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie",
            "Ashe", "Azir", "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Cassiopeia",
            "Cho'Gath", "Corki", "Darius", "Diana", "Dr. Mundo", "Draven", "Ekko", "Elise",
            "Evelynn", "Ezreal", "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank",
            "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Irelia", "Janna",
            "Jarvan IV", "Jax", "Jayce", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin",
            "Katarina", "Kayle", "Kennen", "Kha'Zix", "Kog'Maw", "LeBlanc", "Lee Sin", "Leona",
            "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi",
            "Miss Fortune", "Mordekaiser", "Morgana", "Nautilus", "Nidalee", "Nocturne", "Nunu",
            "Olaf", "Orianna", "Pantheon", "Poppy", "Quinn", "Rammus", "Rek'Sai", "Renekton",
            "Rengar", "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana",
            "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra",
            "Tahm Kench", "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle",
            "Tryndamere", "Twisted Fate", "Twitch", "Udyr", "Urgot", "Varus", "Vayne",
            "Veigar", "Vel'Koz", "Vi", "Viktor", "Vladimir", "Volibear", "Warwick", "Wukong",
            "Xerath", "Xin Zhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};

    public String[] TopPickChamps(String htmlSource) throws IOException {
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        champExtractorLoop(splitHtmlSource);
        return topChamps;
    }

    public String[] TopWinChamps(String htmlSource) throws IOException {
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        champExtractorLoop(splitHtmlSource);
        return topChamps;
    }

    public String[] TopLossChamps(String htmlSource) throws IOException {
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        champExtractorLoop(splitHtmlSource);
        return topChamps;
    }

    public String[] TopPickChampsPercentage(String htmlSource) throws IOException{
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        percentExtractorLoop(splitHtmlSource);
        return topChampsPercent;
    }

    public String[] TopWinsChampsPercentage(String htmlSource) throws IOException{
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        percentExtractorLoop(splitHtmlSource);
        return topChampsPercent;
    }

    public String[] TopLossChampsPercentage(String htmlSource) throws IOException{
        tempHtmlSourceArray = htmlSource.split("LKChart.storeLegacyChart");
        splitHtmlSource = tempHtmlSourceArray[1]; //[1] is the second half of the split
        percentExtractorLoop(splitHtmlSource);
        return topChampsPercent;
    }

    private void percentExtractorLoop (String theSplitHtmlSource){
        String tempPercent="";
        String splitVariable = "values11x221x3A1x22";
        String splitSource = theSplitHtmlSource;
        splitSource = splitSource.replace("\\", "1");
        String[] extractedChampString = splitSource.split(splitVariable, 12); //12 parts, 0 and 11 are throw away values. array is 0-11;

        for (int i = 1; i < 11; i++){
            tempPercent = extractedChampString[i].substring(0, 5);
            if (tempPercent.contains(",")){
                tempPercent = tempPercent.replace(",", ""); //immutable
            }
            topChampsPercent[i -1] = tempPercent;
        }
    }

    private void champExtractorLoop(String theSplitHtmlSource) {
        String splitVariable = "1x22,1x22champion_id";
        String splitSource = theSplitHtmlSource;
        splitSource = splitSource.replace("\\", "1");
        String[] extractedChampString = splitSource.split(splitVariable, 12); //12 parts, 0 and 11 are throw away values. array is 0-11;

        for (int i = 1; i < 11; i++) { //i=1,2,3,4,5,6,7,8,9,10
            for (int x = 0; x < champList.length; x++) { //checks current string against every champion
                if (extractedChampString[i].contains(champList[x])) {
                    topChamps[i - 1] = champList[x];
                }
                else if (extractedChampString[i].contains("Dr.")) {
                    topChamps[i-1] = "Dr. Mundo";
                }
                else if (extractedChampString[i].contains("Jarvan")) {
                    topChamps[i-1] = "Jarvan IV";
                }
                else if (extractedChampString[i].contains("Lee")) {
                    topChamps[i-1] = "Lee Sin";
                }
                else if (extractedChampString[i].contains("Master")) {
                    topChamps[i - 1] = "Master Yi";
                }
                else if (extractedChampString[i].contains("Lee")) {
                    topChamps[i - 1] = "Lee Sin";
                }
                else if (extractedChampString[i].contains("Miss")) {
                    topChamps[i - 1] = "Miss Fortune";
                }
                else if (extractedChampString[i].contains("Tahm")) {
                    topChamps[i - 1] = "Tahm";
                }
                else if (extractedChampString[i].contains("Twisted")) {
                    topChamps[i - 1] = "Twisted Fate";
                }
                else if (extractedChampString[i].contains("Xin")) {
                    topChamps[i - 1] = "Xin Zhao";
                }
                else if (extractedChampString[i].contains("Kha")){
                    topChamps[i - 1] = "Kha'Zix";
                }
                else if (extractedChampString[i].contains("Rek")){
                    topChamps[i-1] = "Rek'Sai";
                }
                else if (extractedChampString[i].contains("Vel")){
                    topChamps[i-1] = "Vel'Koz";
                }
            }
        }
        topChamps = firstInLineFix(topChamps);
    }

    private String [] firstInLineFix(String[] champs){
        String [] revisedTopChamps = new String[10];

        revisedTopChamps[0] = champs[9];
        revisedTopChamps[1] = champs[0];
        revisedTopChamps[2] = champs[1];
        revisedTopChamps[3] = champs[2];
        revisedTopChamps[4] = champs[3];
        revisedTopChamps[5] = champs[4];
        revisedTopChamps[6] = champs[5];
        revisedTopChamps[7] = champs[6];
        revisedTopChamps[8] = champs[7];
        revisedTopChamps[9] = champs[8];
        return revisedTopChamps;
    }
}