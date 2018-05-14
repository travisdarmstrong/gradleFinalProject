package com.randomrobotics.jokedisplaylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import timber.log.Timber;

public class JokeDisplayActivity extends AppCompatActivity {

    // ID for the intent to pass in the joke
    public static final String EXTRA_JOKE = "extra-joke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        // set up Timber logging
        Timber.plant(new Timber.DebugTree());

        String joke;
        if (getIntent().hasExtra(EXTRA_JOKE)) {
            joke = getIntent().getStringExtra(EXTRA_JOKE);
        } else {
            Timber.e("Intent does not include a joke!");
            joke = getString(R.string.error_no_joke);
        }
        Timber.v("Displaying joke: '%s'", joke);

        TextView jokeText = (TextView) findViewById(R.id.joke_display_text);
        jokeText.setText(joke);
    }
}
