package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.MobileAds;
import com.randomrobotics.jokedisplaylibrary.JokeDisplayActivity;
import com.randomrobotics.jokesourcelibrary.JokeRepository;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    private JokeRepository jokeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Timber logging
        Timber.plant(new Timber.DebugTree());

        // Initialize AdMob
        MobileAds.initialize(this, getString(R.string.joke_ad_unit_id_test));

        // Initialize joke repository
        jokeRepository = new JokeRepository();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void tellJoke(View view) {
        String joke = jokeRepository.GetJoke();
        Intent i = new Intent(this, JokeDisplayActivity.class);
        i.putExtra(JokeDisplayActivity.EXTRA_JOKE, joke);
        startActivity(i);
    }


}
