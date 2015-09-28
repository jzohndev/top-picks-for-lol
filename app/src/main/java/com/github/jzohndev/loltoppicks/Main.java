package com.github.jzohndev.loltoppicks;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import java.io.IOException;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private MainHandler loader;
    ImageView img;
    TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//makes activity fullscreen
        getSupportActionBar().hide(); //hides notification bar
        getSupportActionBar().setElevation(0); //removes shadow under ActionBar
        setContentView(R.layout.loadingscreen);
        new AsyncTasker().execute(); //starts AsyncTask
    }

    private class AsyncTasker extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() { //loading screen before app
            loadingText = (TextView) findViewById(R.id.loadingtext);
            loadingText.setText("Loading Champion Data. . .");
            img = (ImageView) findViewById(R.id.progress_bar_iv);
            RotateAnimation anim = new RotateAnimation(0f, 350f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); //loading screen animation
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(4000);
            img.startAnimation(anim); //starts animation
        }

        @Override
        protected Boolean doInBackground(Void... params) { //loads html docs from url
            try {
                loader = new MainHandler();
                loader.bufferTopPickChamps();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                loader.bufferTopWinChamps();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                loader.bufferTopLossChamps();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean v) { //sets up the activity for fragments
            int [] imageResId = {R.drawable.popular_icon_v1a, R.drawable.wins_icon_v1a, R.drawable.loses_icon_v1a};
            getSupportActionBar().show();
            getSupportActionBar().setTitle("Most Popular"); //first actionbar title that shows before the listener
            img.setAnimation(null);

            setContentView(R.layout.activity_main);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout); //tabs at top
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                @Override
                public void onPageSelected(int position) {
                    String [] titles = {"Popular: Games Played", "Wins: Percentage of Games", "Loses: Percentage of Games"};
                    super.onPageSelected(position);
                    getSupportActionBar().setTitle(titles[position]);
                }
            });

            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                tabLayout.getTabAt(i).setIcon(imageResId[i]);}
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}