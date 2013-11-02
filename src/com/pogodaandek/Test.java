package com.pogodaandek;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Test extends Activity {

	public JSONObject testowy = new JSONObject();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		

		try {
			wlozDane();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	
	public void wlozDane() throws JSONException{
		
		EditText miasto = (EditText)findViewById(R.id.miasto);
		EditText localTime = (EditText)findViewById(R.id.localTime);
		EditText temp = (EditText)findViewById(R.id.temperatura);
		EditText pogoda = (EditText)findViewById(R.id.pogoda);
		EditText wiatr = (EditText)findViewById(R.id.wiatr);
		
		JSONObject display_location = new JSONObject();
		//JSONObject observation_location = new JSONObject();
		
		//"city" "local_time_rfc822" "temp_c" "feelslike_c" "weather" "wind_mph"
		
		display_location.put("city", miasto.getText().toString());
		testowy.put("display_location", display_location);	//nie wyœwietla miasta :C
		
		
		String message = "Mon, 11 Nov 2014 12:00:00 +0100";	//magia! Jeœli jest pobieranie z EditText to nie dzia³a!
		
		testowy.put("local_time_rfc822", message);
		testowy.put("weather", pogoda.getText().toString());
		testowy.put("temp_c", temp.getText());	
		testowy.put("wind_mph", wiatr.getText());
		testowy.put("feelslike_c", temp.getText());
		
		
		//testowy.put("observation_location", observation_location);
		
		
	}
	

	public void sporty(View view){
		
		Intent intent = new Intent(this, Sporty.class);
		intent.putExtra("Pogoda", testowy.toString());
		startActivity(intent);
		
	}
}
