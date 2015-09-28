package com.github.jzohndev.loltoppicks;

/**
 * Created by Jzohn on 8/29/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentPopular tab1 = new FragmentPopular();
                return tab1;
            case 1:
                FragmentWins tab2 = new FragmentWins();
                return tab2;
            case 2:
                FragmentLoses tab3 = new FragmentLoses();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}