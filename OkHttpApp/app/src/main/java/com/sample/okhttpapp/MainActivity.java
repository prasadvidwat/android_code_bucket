package com.sample.okhttpapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String getURL = "https://reqres.in/api/users/2";
    final String postURL = "https://reqres.in/api/users";
    final String postBody = "{\n" +
            "\"name\":\"prasad\"," +
            "\"job\":\"software developer\""+
            "\n}";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    Button syncGetButton, asyncGetButton, asyncPostButton;
    TextView responseTextView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = findViewById(R.id.response_textview);
        progressBar = findViewById(R.id.progress_circular);

        syncGetButton = findViewById(R.id.sync_get_button);
        asyncGetButton = findViewById(R.id.async_get_button);
        asyncPostButton = findViewById(R.id.async_post_button);

        syncGetButton.setOnClickListener(this);
        asyncGetButton.setOnClickListener(this);
        asyncPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.sync_get_button:
                handleSynchronousGetRequest();
                break;
            case R.id.async_get_button:
                handleAsynchronousGetRequest();
                break;
            case R.id.async_post_button:
                handleAsynchronousPostRequest();
                break;
        }

    }

    // This method handles get request and fetch the data from server synchronously.
    private void handleAsynchronousPostRequest() {

        responseTextView.setText("");
        progressBar.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, postBody);
        Request request = new Request.Builder().url(postURL).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseTextView.setText("Data sent to server.. Response=["+responseText+"]");
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    // This method handles get request and fetch the data from server asynchronously.
    private void handleAsynchronousGetRequest() {

        runAsyncGetOperation();

    }

    private void runAsyncGetOperation() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(getURL).build();
        responseTextView.setText("");
        progressBar.setVisibility(View.VISIBLE);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        responseTextView.setText(responseText);
                    }
                });
            }
        });
    }

    // This method handles post request and sends the data to server asynchronously.
    private void handleSynchronousGetRequest() {

        OkHttpHandler handler = new OkHttpHandler();
        handler.execute(getURL);
    }

    public class OkHttpHandler extends AsyncTask<String, String, String>
    {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            Request.Builder requestBuilder = new Request.Builder();
            requestBuilder.url(params[0]);
            Request request = requestBuilder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String responseText) {
            progressBar.setVisibility(View.GONE);
            responseTextView.setText(responseText);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
