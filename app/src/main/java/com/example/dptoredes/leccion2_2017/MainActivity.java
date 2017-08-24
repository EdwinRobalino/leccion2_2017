package com.example.dptoredes.leccion2_2017;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DResultReceiver.Receiver{


    private DResultReceiver mReceiver;
    final String url = "http://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case Downloader.STATUS_RUNNING:
                Toast.makeText(this, "Buscando...", Toast.LENGTH_SHORT).show();
                break;

            case Downloader.STATUS_FINISHED:

                String[] results = resultData.getStringArray("result");
                /*
                //LLenando lista
                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, results);
                listView.setAdapter(arrayAdapter);*/
                break;

            case Downloader.STATUS_ERROR:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void startDownloader (View v){
        Toast.makeText(getApplication(), "asads", Toast.LENGTH_SHORT).show();

        mReceiver = new DResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, Downloader.class);

        intent.putExtra("url", url);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 101);

        startService(intent);


    }
}
