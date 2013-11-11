package com.pogodaandek;

import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class Ubrania extends Activity {
	
	JSONObject jObject;
	ImageView obrazek;
	Double temp;
	int miesiac;
	String pogoda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubrania);
		
		try {
			jObject = new JSONObject(getIntent().getStringExtra("Pogoda"));
			
			temp = jObject.getDouble("feelslike_c");
			//miesiac = jObject.getInt("month");
			
			ubierz();		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubrania, menu);
		return true;
	}
	
	public void ubierz(){
		
		
		
		obrazek = (ImageView)findViewById(R.id.obrazek);
		Resources r = getResources();
		/*
		obrazek.setImageDrawable(getResources().getDrawable(R.layout.layer));
		//Resources r = getResources();
		Drawable[] layers = new Drawable[4];
		layers[0] = r.getDrawable(R.drawable.kobieta);
		layers[1] = r.getDrawable(R.drawable.buty_k);
		layers[2] = r.getDrawable(R.drawable.spodniedl_k);		
		layers[3] = r.getDrawable(R.drawable.dlrekaw_k);	
		LayerDrawable layerDrawable = new LayerDrawable(layers);
		obrazek.setImageDrawable(layerDrawable);
		*/
		
		if(temp<10){	
			Drawable[] layers = new Drawable[6];
			layers[0] = r.getDrawable(R.drawable.kobieta);
			layers[1] = r.getDrawable(R.drawable.buty_k);
			layers[2] = r.getDrawable(R.drawable.spodniedl_k);		
			layers[3] = r.getDrawable(R.drawable.kurtka_k);	
			layers[4] = r.getDrawable(R.drawable.szalik_k);	
			layers[5] = r.getDrawable(R.drawable.czapka_k);	
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			obrazek.setImageDrawable(layerDrawable);
			
		}
		
		else if(temp<18){
			//10-17
			Drawable[] layers = new Drawable[5];
			layers[0] = r.getDrawable(R.drawable.kobieta);
			layers[1] = r.getDrawable(R.drawable.buty_k);
			layers[2] = r.getDrawable(R.drawable.spodniedl_k);		
			layers[3] = r.getDrawable(R.drawable.kurtka_k);	
			layers[4] = r.getDrawable(R.drawable.szalik_k);	
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			obrazek.setImageDrawable(layerDrawable);
			
		}
		
		else if(temp<23){
			//18-22
			Drawable[] layers = new Drawable[4];
			layers[0] = r.getDrawable(R.drawable.kobieta);
			layers[1] = r.getDrawable(R.drawable.buty_k);
			layers[2] = r.getDrawable(R.drawable.spodniedl_k);		
			layers[3] = r.getDrawable(R.drawable.dlrekaw_k);	
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			obrazek.setImageDrawable(layerDrawable);
			
		}
		
		else if(temp<28){
			//23-27
			Drawable[] layers = new Drawable[4];
			layers[0] = r.getDrawable(R.drawable.kobieta);
			layers[1] = r.getDrawable(R.drawable.sandalki_k);
			layers[2] = r.getDrawable(R.drawable.spodniedl_k);		
			layers[3] = r.getDrawable(R.drawable.tshirt_k);	
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			obrazek.setImageDrawable(layerDrawable);
		}
		
		else{
			//28 i w górê
			Drawable[] layers = new Drawable[4];
			layers[0] = r.getDrawable(R.drawable.kobieta);
			layers[1] = r.getDrawable(R.drawable.sandalki_k);
			layers[2] = r.getDrawable(R.drawable.spodniekr_k);		
			layers[3] = r.getDrawable(R.drawable.podkoszulek_k);	
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			obrazek.setImageDrawable(layerDrawable);
		}
		
		
		
	}

}
