package com.pogodaandek;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Sporty extends ListActivity {

	public JSONObject jObject;

	// String listArray[];
	String listArray[] = { "Bieganie", "P�ywanie", "Jazda na rowerze", "�y�wy",
			"Narciarstwo", "Pi�ka siatkowa", "Koszyk�wka", "Pi�ka no�na",
			"Badminton", "Tenis", "Squash", "Joga", "Wyj�cie na si�owni�",
			"�eglarstwo", "Nordic walking", "Trekking", "Serfowanie",
			"Parkour", "Jazda na rolkach", "Deskorolka", "Jazda na bmx",
			"Jazda na quadzie", "Wyj�cie na cardy", "Siatk�wka pla�owa",
			"Kajakarstwo", "P�ywanie ��dk�/pontonem", "Hokej", "Golf",
			"Szermierka", "�ucznictwo", "Wyj�cie na strzelnic�", "Bungee",
			"Nurkowanie", "Paralotnia", "Skok ze spadochronem", "Windsurfing",
			"Jazda konna", "Wspinaczka", "Ping-pong", "Trening sztuk walki",
			"Narty wodne", "Polowanie", "Lot balonem", "Pi�ka wodna",
			"Paintball" };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sporty, menu);
		return true;

	}

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sporty);


			try {
				jObject = new JSONObject(getIntent().getStringExtra("Pogoda"));				
				wyborSportow(jObject);
								
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		


		ArrayAdapter<String> array = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listArray);
		setListAdapter(array);
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}

	public void wyborSportow(JSONObject jObject) throws JSONException {

		
		JSONObject location = jObject.getJSONObject("display_location");
		String miasto = location.getString("city");	
		
		String dataGodzina = jObject.getString("local_time_rfc822"); // trzeba jeszcze wy�uska�
																		 
																		 
		 Double temp = jObject.getDouble("temp_c");
		// Double tempOdczuwalna = jObject.getDouble("feelslike_c"); //a nu� si� przyda

		 String pogoda = jObject.getString("weather");
		 Double predkoscWiatru = jObject.getDouble("wind_mph");

		// tniemy dataGodzina ------------------------

		// Sat, 02 Nov 2013 08:32:38 +0100
		String dzienTygodnia = "";

		int i = 0;
		do {
			dzienTygodnia = dzienTygodnia + dataGodzina.charAt(i);
			i++;
		} while (dataGodzina.charAt(i) != ',');


		String dzien = String.valueOf(dataGodzina.charAt(5));
		dzien = dzien+dataGodzina.charAt(6);

		Log.i("Miasto",miasto);
		Log.i("Dzien tygodnia", dzienTygodnia);
		Log.i("Dzien", dzien);

		// -----------------------------
	}

}
