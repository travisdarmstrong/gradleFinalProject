package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.randomrobotics.jokedisplaylibrary.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Get the joke in a background task
 * Joke is retrieved from a Google Cloud Engine endpoint
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private WeakReference<Context> contextRef;

    /** Create a new EndpointsAsyncTask with reference to the context */
    public EndpointsAsyncTask(Context context) {
        this.contextRef = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(Void... args) {
        if (myApiService == null) { // only do this once
            Timber.d("Creating API service");
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //.setRootUrl("http://127.0.0.1:8080")
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }

                    });
            myApiService = builder.build();
        }

        // Get the joke and return it, or return the error message
        try {
            return myApiService.getJoke().execute().getData();
        }catch (IOException ioEx){
            Timber.e(ioEx);
            return ioEx.getMessage();
        }
    }

    /**
     * After the background task has executed, take the joke and start the Display Activity
     */
    @Override
    protected void onPostExecute(String result) {
        Intent i = new Intent(contextRef.get(), JokeDisplayActivity.class);
        i.putExtra(JokeDisplayActivity.EXTRA_JOKE, result);
        contextRef.get().startActivity(i);
    }
}