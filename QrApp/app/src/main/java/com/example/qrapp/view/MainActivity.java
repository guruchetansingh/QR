package com.example.qrapp.view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qrapp.R;
import com.example.qrapp.util.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by guruchetansingh on 12/1/15.
 */
public class MainActivity extends AppCompatActivity implements Constants,View.OnClickListener {

    private ImageView image_view_qrimage;
    private Button button_click;
    private EditText edit_text_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

        button_click.setOnClickListener(this);

    }

    private void initialization() {
        image_view_qrimage=(ImageView) findViewById(R.id.image_view_qrimage);
        edit_text_content=(EditText) findViewById(R.id.edit_text_content);
        button_click=(Button) findViewById(R.id.button_click);
    }


    public void createQRImage(String content,ImageView imageView)
    {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imageView.setImageBitmap(bmp);

        } catch (WriterException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_click:
                createQRImage(edit_text_content.getText().toString(),image_view_qrimage);
                break;
        }
    }
}
