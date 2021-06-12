package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        //Get the URL as text
        String url = mWebsiteEditText.getText().toString();
        //Parse the URI and create an intent
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Find an Activity to pass the Intent and start that Activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
            else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
        }

    public void openLocation(View view) {
        //Get the location as Text
        String loc = mLocationEditText.getText().toString();
        // Parse the string into a Uri object with a geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        //create a new intent with action view
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //find an activity to handle the intent and start that activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else { Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        //Create a variable to hold the text & get as string
        String txt = mShareTextEditText.getText().toString();
        //Define a mime type of the text, which constitutes type/subtype
        String mimeType = "text/plain";
        //Call the ShareCompatibility intent builder and call intent methods
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with:")
                .setText(txt)
                .startChooser();
    }
}