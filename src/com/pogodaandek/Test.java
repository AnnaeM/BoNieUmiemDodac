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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	
	public void sporty(View view) {

		try {
			wlozDane();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent = new Intent(this, Sporty.class);
		intent.putExtra("Pogoda", testowy.toString());
		
		startActivity(intent);

	}

	public void wlozDane() throws JSONException {

		EditText miasto = (EditText) findViewById(R.id.miasto);
		EditText temp = (EditText) findViewById(R.id.temperatura);
		EditText pogoda = (EditText) findViewById(R.id.pogoda);
		EditText wiatr = (EditText) findViewById(R.id.wiatr);

		EditText dzienTygodnia = (EditText) findViewById(R.id.dzienTygodnia);
		EditText dzien = (EditText) findViewById(R.id.dzien);
		EditText miesiac = (EditText) findViewById(R.id.miesiac);
		EditText rok = (EditText) findViewById(R.id.rok);
		EditText godzina = (EditText) findViewById(R.id.godzina);

		JSONObject display_location = new JSONObject();

		display_location.put("city", miasto.getText().toString());
		testowy.put("display_location", display_location); 

		// String message = "Mon, 11 Nov 2014 12:00:00 +0100"; 

		int nrMiesiaca = Integer.parseInt(miesiac.getText().toString());
		String month = miesiace(nrMiesiaca);
		String message = dzienTygodnia.getText().toString() + ", "
				+ dzien.getText().toString() + " " + month + " "
				+ rok.getText().toString() + " " + godzina.getText().toString()
				+ " +0100";

		testowy.put("local_time_rfc822", message);	
		testowy.put("weather", pogoda.getText().toString());
		testowy.put("temp_c", temp.getText());
		testowy.put("wind_mph", wiatr.getText());
		testowy.put("feelslike_c", temp.getText());


	}

	public String miesiace(int mies) {

		String nazwa;
		switch (mies) {

		case 1:{
			nazwa = "Jan";
			break;
		}
		case 2: {
			nazwa = "Feb";
			break;
		}
		case 3: {
			nazwa = "Mar";
			break;
		}
		case 4: {
			nazwa = "Apr";
			break;
		}
		case 5: {
			nazwa = "May";
			break;
		}
		case 6: {
			nazwa = "Jun";
			break;
		}
		case 7: {
			nazwa = "Jul";
			break;
		}
		case 8: {
			nazwa = "Aug";
			break;
		}
		case 9: {
			nazwa = "Sept";
			break;
		}
		case 10: {
			nazwa = "Oct";
			break;
		}
		case 11: {
			nazwa = "Nov";
			break;
		}
		case 12: {
			nazwa = "Dec";
			break;
		}

		default: {
			nazwa = "Brak";
		}
		}

		return nazwa;
	}

	
}
