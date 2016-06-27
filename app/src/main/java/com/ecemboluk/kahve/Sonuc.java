package com.ecemboluk.kahve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Sonuc extends AppCompatActivity {

    String data;
    String data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Textviweler oluşturuldu ve VeriCekme sınıfı çağırıldı.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        Intent intent=getIntent();
        data=intent.getStringExtra(Giris.EXTRA_MESSAGE);
        TextView text=(TextView) findViewById(R.id.textView2);
        text.setText(data);
        data1=data;
        new VeriCekme().execute();
    }
    private class VeriCekme extends AsyncTask<Void,Void,Void>
    {
        String URL="http://www.kahvefalinda.com/"+data1+"";
        String fal;
        String veri;
        private ProgressDialog progressDialog;
        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            progressDialog=new ProgressDialog(Sonuc.this);
            progressDialog.setTitle("Kaaave");
            progressDialog.setMessage("Fal Getiriliyor..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            try {
                Document doc= Jsoup.connect(URL).get();//URL bağlanır.
                Elements elements=doc.select("div[class=entry]");//entry adındaki classdaki verileri getirir.
                elements.select("p");//p tagdaki verileri getirir.
                elements.select("strong").remove();//strong tagındaki verileri siler.
                elements.select("script").remove();
                elements.select("br").remove();
                elements.select("h3").remove();
                elements.select("ins").remove();
                veri=elements.html();//istenilen html taglarını çeker.
                fal=Jsoup.parse(veri).text();//html taglarını texte çevirir.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            TextView text2=(TextView)findViewById(R.id.textView3);
            text2.setText(fal);
            progressDialog.dismiss();
        }
    }

}
