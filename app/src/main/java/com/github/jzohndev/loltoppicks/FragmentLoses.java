package com.github.jzohndev.loltoppicks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class FragmentLoses extends Fragment {
    private String[] champArray;
    private String[] champArrayPercent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.fragmentloses_layout, container, false);
        setRetainInstance(true);
        try {
            MainHandler x = new MainHandler();
            champArray = x.getTopLossChamps();
            champArrayPercent = x.getTopLossChampsPercent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootView = uiAssigner(rootView);
        return rootView;
    }

    private View uiAssigner(View rView) {
        int[] imageViewIDs = new int[]{R.id.icon_1, R.id.icon_2, R.id.icon_3, R.id.icon_4,
                R.id.icon_5, R.id.icon_6, R.id.icon_7, R.id.icon_8, R.id.icon_9, R.id.icon_10};

        int[] textViewIDs = new int[]{R.id.name_1, R.id.name_2, R.id.name_3, R.id.name_4,
                R.id.name_5, R.id.name_6, R.id.name_7, R.id.name_8, R.id.name_9, R.id.name_10};
        int[] percTextViewIDs = new int[]{R.id.percent_1, R.id.percent_2, R.id.percent_3, R.id.percent_4,
                R.id.percent_5, R.id.percent_6, R.id.percent_7, R.id.percent_8, R.id.percent_9, R.id.percent_10};

        for (int i = 0; i < 10; i++) {
            String revisedName = nameFormatter(champArray[i]); //formats the champion name to get the corresponding icon
            String percentage = percentFormatter(champArrayPercent[i]);
            int id = getResources().getIdentifier(revisedName, "drawable", BuildConfig.APPLICATION_ID);

            ImageView currentIcon = (ImageView) rView.findViewById(imageViewIDs[i]);
            TextView currentName = (TextView) rView.findViewById(textViewIDs[i]);
            TextView currentPercent = (TextView) rView.findViewById(percTextViewIDs[i]);
            currentIcon.setImageResource(id); //assigns the icon to the ImageView in the XML file
            currentPercent.setText(percentage);
            currentName.setText(champArray[i]); //assigns the icon to the TextView in the XML file
        }
        return rView;
    }

    private String nameFormatter(String champion) {
        String name;

        name = ((champion + "_Square_0").toLowerCase()); //champion name --> champion icon jpeg
        name = name.replaceAll("\\s+", ""); //remove spaces if they exist
        name = name.replaceAll("\'", ""); //remove ' if they exist
        name = name.replaceAll("\\.", ""); //2 backslashes to replace "."
        return name;
    }

    private String percentFormatter(String percentInString) {
        double doublePercent = Math.round(Double.parseDouble(percentInString));
        int intPercent = (int) doublePercent;
        String percent = intPercent + "%";
        return percent;
    }
}