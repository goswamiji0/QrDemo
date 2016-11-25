package com.rgg.rahulgoswami.qrscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    Button scancode,generatecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scancode=(Button)findViewById(R.id.button2);
        generatecode=(Button)findViewById(R.id.button);

        mScannerView = new ZXingScannerView(MainActivity.this);
        generatecode.setOnClickListener(this);
        scancode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v==generatecode)
        {
            Intent i=new Intent(MainActivity.this,QRCodeActivity.class);
            startActivity(i);
        }
        if(v==scancode)
        {
            ScanCode();
        }
    }

    private void ScanCode() {

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        Toast.makeText(MainActivity.this,"This is your Text"+result.getText(),Toast.LENGTH_SHORT);
        Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
    }
}
