package com.pogodaandek;

import org.json.JSONArray;
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
	String dzienTygodnia;
	Double temp;
	String miasto;
	int miesiac;
	//String dataGodzina;

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

		miasto = jObject.getString("city");
		temp = jObject.getDouble("feelslike_c"); // temp odczuwalna																
		String pogoda = jObject.getString("weather");
			
		Log.i("Test", jObject.toString());


		// -----------------------------

		//int samaGodzina = Integer.parseInt(samaGodzinaString);
		char poraDnia = poraDnia(jObject.getInt("hour"));

		dzienTygodnia = jObject.getString("weekDay");

		int pogodaInt = pogodaNaInt(pogoda);
		switch (pogodaInt) {
		case 1: {	//pogodnie
			ladnaPogoda(poraDnia);	
			break;
		}
		case 2: {	//przewaga chmur

			break;
		}
		case 3: {	//ob�oki zanikaj�ce

			break;
		}
		case 4: {	//�nieg

			break;
		}
		case 5: {	//niewielkie zachmurzenie

			break;
		}
		case 6: {	//deszcz

			break;
		}
		case 7: {	//lekki deszcz

			break;
		}
		case 8: {	//pochmurno

			break;
		}
		case 9: {	//p�atki mg�y

			break;
		}
		case 10: {	//lekkie przelotne deszcze

			break;
		}

		}

	}

	public void dataGodzina(){
		
	// tniemy dataGodzina ------------------------

	// Sat, 02 Nov 2013 08:32:38 +0100
	/*	String dzienTygodnia = "";

		int i = 0;
		do {
			dzienTygodnia = dzienTygodnia + dataGodzina.charAt(i);
			i++;
		} while (dataGodzina.charAt(i) != ',');*/

		//String dzien = String.valueOf(dataGodzina.charAt(5));
		//dzien = dzien + dataGodzina.charAt(6);
				
	//	String miesiacStr = String.valueOf(dataGodzina.charAt(8));
	//	miesiacStr = miesiacStr + dataGodzina.charAt(9) + dataGodzina.charAt(10);
		
	}
	public char poraDnia(int godzina) {

		char pora;

		if ((godzina >= 6) && (godzina < 10))
			pora = 	'm';	//"wczesny ranek";(morning)
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'r';			//"ranek";	
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'd';		//"dzie�";
		else if ((godzina >= 14) && (godzina < 17))
			pora = 'p';		//"popo�udnie";
		else if ((godzina >= 18) && (godzina < 21))
			pora = 	'w';	//"wiecz�r";
		else if ((godzina >= 22) && (godzina < 1))
			pora = 'n';	//"noc";
		else
			pora ='g';	//"g��boka noc";

		return pora;
	}

	public int pogodaNaInt(String pogoda){
		//pierwsza wersja:boPogodaMusiBycInt(foch)
		int i;
		
		if(pogoda=="pogodnie")
			i=1;
		else if (pogoda=="przewaga chmur")
			i=2;
		else if (pogoda=="ob�oki zanikaj�ce")
			i=3;
		else if (pogoda=="�nieg")
			i=4;
		else if (pogoda=="niewielkie zachmurzenie")
			i=5;
		else if (pogoda=="deszcz")
			i=6;
		else if (pogoda=="lekki deszcz")
			i=7;
		else if (pogoda=="pochmurno")
			i=8;
		else if (pogoda=="p�atki mg�y")
			i=9;
		else if (pogoda=="lekkie przelotne deszcze ")
			i=10;
		else
			i=0;
		return i;
	}
	
	
	public void ladnaPogoda(char poraDnia){
		
		switch (poraDnia) {
		case 'm': {
			if ((dzienTygodnia == "Poniedzia�ek")|| (dzienTygodnia == "Wtorek")|| (dzienTygodnia == "�roda")
					|| (dzienTygodnia == "Czwartek")|| (dzienTygodnia == "Pi�tek")) {
				
			//	naTygodniu();
				
				
			}

			else if (dzienTygodnia == "Sobota") {

			} else { // niedziela

			}

			break;
		}
		case 'r': {
			break;
		}
		case 'd': {
			break;
		}
		case 'p': {
			break;
		}
		case 'w': {
			break;
		}
		case 'n': {
			break;
		}
		case 'g': {
			break;
		}

		}
		
		
	}
	
}
