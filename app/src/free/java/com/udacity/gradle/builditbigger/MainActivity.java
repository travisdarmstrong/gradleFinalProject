package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    // Interstitial ad shown between activities
    private InterstitialAd mInterstitialAd;
    AdRequest mInterstitialAdRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Timber logging
        Timber.plant(new Timber.DebugTree());

        // Initialize AdMob
        MobileAds.initialize(this, getString(R.string.joke_ad_unit_id_test));

        // Create an interstitial ad that will be displayed between getting and showing the joke
        Timber.d("Creating interstitial ad");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_test));
        mInterstitialAdRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(mInterstitialAdRequest);
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When the user presses the "Tell Joke" button
     * Show the interstitial ad. When the ad closes, get the joke and display it
     */
    public void tellJoke(View view) {
        Timber.d("starting asynctask to get joke");

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Load a new ad
                    mInterstitialAd.loadAd(mInterstitialAdRequest);
                    // Get and show the joke
                    new EndpointsAsyncTask(getApplicationContext()).execute();
                }
            });
            // Show the interstitial ad
            mInterstitialAd.show();
        } else {
            Timber.w("Interstitial ad wasn't loaded yet");
            new EndpointsAsyncTask(this).execute();
        }
    }
}




