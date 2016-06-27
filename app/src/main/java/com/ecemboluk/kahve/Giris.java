package com.ecemboluk.kahve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Giris extends AppCompatActivity {

    Button search;
    EditText data;
    public final static String EXTRA_MESSAGE = "com.ecemboluk.kahve.Sonuc";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Baslangıc nesneleri yaratılır.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        data = (EditText) findViewById(R.id.editText);
        search = (Button) findViewById(R.id.button);

    }

    public void SentToWord(View v) { //Kelimeyi diğer aktivity gönderir.
        //Not:Metodu activity_giris.xml 'de çağırılmaktadır.
        Intent intent = new Intent(this, Sonuc.class);
        EditText edit = (EditText) findViewById(R.id.editText);
        String data = edit.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, data);
        startActivity(intent);
    }

}
