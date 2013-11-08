package com.pogodaandek;

import java.util.ArrayList;

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
	int godzina;
	String pogoda;
	// String listArray[]=null;
	ArrayList<String> listArray = new ArrayList<String>();

	// String listArray[];
	/*
	 * String listArray[] = { "Bieganie", "P³ywanie", "Jazda na rowerze",
	 * "£y¿wy", "Narciarstwo", "Pi³ka siatkowa", "Koszykówka", "Pi³ka no¿na",
	 * "Badminton", "Tenis", "Squash", "Joga", "Wyjœcie na si³owniê",
	 * "¯eglarstwo", "Nordic walking", "Trekking", "Serfowanie", "Parkour",
	 * "Jazda na rolkach", "Deskorolka", "Jazda na bmx", "Jazda na quadzie",
	 * "Wyjœcie na cardy", "Siatkówka pla¿owa", "Kajakarstwo",
	 * "P³ywanie ³ódk¹/pontonem", "Hokej", "Golf", "Szermierka", "£ucznictwo",
	 * "Wyjœcie na strzelnicê", "Bungee", "Nurkowanie", "Paralotnia",
	 * "Skok ze spadochronem", "Windsurfing", "Jazda konna", "Wspinaczka",
	 * "Ping-pong", "Trening sztuk walki", "Narty wodne", "Polowanie",
	 * "Lot balonem", "Pi³ka wodna", "Paintball" };
	 */
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

		if (listArray.isEmpty()) {
			listArray.add("Zostañ w domu");
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
		pogoda = jObject.getString("weather");
		// miesiac = jObject.getInt("month");
		String m = jObject.getString("month");
		miesiac = Integer.parseInt(m);

		godzina = jObject.getInt("hour");
		dzienTygodnia = jObject.getString("weekDay");

		Log.i("Test", jObject.toString());

		char poraDnia = poraDnia();

		int i;
		if (pogoda.equals("pogodnie")) {
			Log.i("Info", "Pogodnie");
			ladnaPogoda(poraDnia);
		} else if (pogoda.equals("przewaga chmur"))
			i = 2;
		else if (pogoda.equals("ob³oki zanikaj¹ce"))
			i = 3;
		else if (pogoda.equals("œnieg"))
			i = 4;
		else if (pogoda.equals("niewielkie zachmurzenie"))
			i = 5;
		else if (pogoda.equals("deszcz"))
			i = 6;
		else if (pogoda.equals("lekki deszcz"))
			i = 7;
		else if (pogoda.equals("pochmurno"))
			i = 8;
		else if (pogoda.equals("p³atki mg³y"))
			i = 9;
		else if (pogoda.equals("lekkie przelotne deszcze "))
			i = 10;
		else
			i = 0;

	}

	public char poraDnia() {

		char pora;

		if ((godzina >= 6) && (godzina < 10))
			pora = 'r'; // ranek
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'p'; // "poludnie";
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'd'; // "dzieñ";
		else if ((godzina >= 14) && (godzina < 17))
			pora = 'o'; // "popo³udnie";
		else if ((godzina >= 18) && (godzina < 21))
			pora = 'w'; // "wieczór";
		else if ((godzina >= 22) && (godzina < 1))
			pora = 'n'; // "noc";
		else
			pora = 'g'; // "g³êboka noc";

		return pora;
	}

	public void ladnaPogoda(char poraDnia) {

		char poraRoku = poraRoku();

		switch (poraRoku) {
		case 'w': {
			// wiosna
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, wiosna, ranek, dowolny dzieñ tygodnia (bo
				// do 10 i tak wszystko zamkniête)

				if ((temp >= -5) && (temp <= 25)) {
					bieganie();
					standardowe();
				}
				break;
			}

			case 'p':
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia³ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("Œroda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi¹tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, wiosna, po³udnie+dzien+popoludnie+wieczór, na
					// tygodniu + sobota
					
					if ((temp>=-10)&&(temp<0)){
						naHali();
					}
					else if ((temp >= 0) && (temp < 35)) {
						bieganie();
						standardowe();
						naHali();
						naDworze();

					}

					else {

						// jest ladna pogoda, wiosna, po³udnie+dzien+popoludnie+wieczór,
						// niedziela
						if ((temp >= 5) && (temp <= 35)) {
							bieganie();
							standardowe();
							listArray.add("Jazda konna");
						}

					}
				}
				break;
			}

			case 'n':
			case 'g': {
				// jest ladna pogoda, wiosna, noc+g³êboka noc, dowolny dzien
				if ((temp >= -5) && (temp <= 30)) {
					bieganie();
					standardowe();
				}
				break;
			}
			default: {
				Log.i("B³¹d", "Nie ma poryDnia");
			}

			}

			break;
		}
		case 'l': {// lato

			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, lato, ranek, dowolny dzieñ tygodnia (bo do
				// 10 i tak wszystko zamkniête)

				if ((temp >= 5) && (temp <= 30)) {
					bieganie();
					standardowe();
				}

				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia³ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("Œroda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi¹tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, lato, po³udnie+dzien+popoludnie+wieczór, na
					// tygodniu + sobota
					
					if ((temp>=-5)&&(temp<5)){
						naHali();
					}
					else if ((temp >= 5) && (temp <= 35)) {
						bieganie();
						standardowe();
						naHali();
						naDworze();

					}

					else {

						// jest ladna pogoda, lato, po³udnie+dzien+popoludnie+wieczór,
						// niedziela
						if ((temp >= 5) && (temp <= 35)) {
							bieganie();
							standardowe();
							listArray.add("Jazda konna");
						}

					}
				}
				break;
			}

			case 'n': 
			case 'g': {
				// jest ladna pogoda, lato, noc+g³êboka noc, dowolny dzien
				if ((temp >= 0) && (temp <= 30)) {
					bieganie();
					standardowe();
				}
				break;
			}
			default: {
				Log.i("B³¹d", "Nie ma poryDnia");
			}
				break;
			}
		}
		case 'j': { // jesien
			
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, jesien, ranek, dowolny dzieñ tygodnia (bo do
				// 10 i tak wszystko zamkniête)

				if ((temp >= -5) && (temp <= 30)) {
					bieganie();
					standardowe();
				}

				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia³ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("Œroda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi¹tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, jesien, po³udnie+dzien+popoludnie+wieczór, na
					// tygodniu + sobota
					
					if((temp>=-30)&&(temp<-5)){						
						naHali();
						
					}
					else if ((temp >= -5) && (temp <= 35)) {
						bieganie();
						standardowe();
						naHali();
						naDworze();

					}
					

					else {

						// jest ladna pogoda, jesien, po³udnie+dzien+popoludnie+wieczór,
						// niedziela
						if ((temp >= -5) && (temp <= 35)) {
							bieganie();
							standardowe();
							listArray.add("Jazda konna");
						}

					}
				}
				break;
			}

			case 'n': 
			case 'g': {
				// jest ladna pogoda, jesien, noc+g³êboka noc, dowolny dzien
				if ((temp >= -5) && (temp <= 25)) {
					bieganie();
					standardowe();
				}

				break;
			}
			default: {
				Log.i("B³¹d", "Nie ma poryDnia");
			}
				break;
			}
			
			break;
		}
		case 'z': { // zima
			
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, zima, ranek, dowolny dzieñ tygodnia (bo do
				// 10 i tak wszystko zamkniête)
			
				if ((temp >= -15) && (temp <= 20)) {
					bieganie();
					standardowe();
				}

				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia³ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("Œroda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi¹tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, zima, po³udnie+dzien+popoludnie+wieczór, na
					// tygodniu + sobota
					
					if ((temp>=-30)&&(temp<=-15)){				
						naHali();
					}
						
					else if ((temp >= -15) && (temp < 20)) {
						bieganie();
						standardowe();
						naHali();
						naDworze();

					}

					else {

						// jest ladna pogoda, zima, po³udnie+dzien+popoludnie+wieczór,
						// niedziela
						if ((temp >= -10) && (temp <= 20)) {
							bieganie();
							standardowe();							
						}

					}
				}
				break;
			}

			case 'n': 
			case 'g': {
				// jest ladna pogoda, zima, noc+g³êboka noc, dowolny dzien
				if ((temp >= -10) && (temp <= 20)) {
					bieganie();
					standardowe();
				}

				break;
			}
			default: {
				Log.i("B³¹d", "Nie ma poryDnia");
			}
				break;
			}
			
			break;
		}
		default: {

		}

		}
	}

	public char poraRoku() {

		// mo¿na dodaæ np. przedwioœnie
		char c;

		if ((miesiac == 4) || (miesiac == 5)) { // kwiecien-maj
			c = 'w';
		} else if ((miesiac >= 6) && (miesiac <= 8)) { // czerwiec-sierpien
			c = 'l';
		} else if ((miesiac >= 9) && (miesiac <= 11)) { // wrzesien-list
			c = 'j';
		} else // grudzien-marzec
		{
			c = 'z';
		}

		return c;

	}

	public void bieganie() {
		listArray.add("Bieganie");

	}

	public void standardowe() {
		listArray.add("Rower");
		listArray.add("Joga");
		listArray.add("Nordic walking");
		listArray.add("Rolki");
		listArray.add("Wrotki");
		listArray.add("Deskorolka");

	}

	public void naHali() {

		listArray.add("Siatkówka");
		listArray.add("Koszykówka");
		listArray.add("Pi³ka no¿na");
		listArray.add("Badminton");
		listArray.add("Squash");
		listArray.add("Si³ownia");
		listArray.add("Szermierka");
		listArray.add("£ucznictwo");
		listArray.add("Strzelnica");
		listArray.add("Œciana wspinaczkowa");
		listArray.add("Trening sztuk walki");
		listArray.add("Basen");
		listArray.add("Ping-pong");

	}

	public void naDworze() {

		listArray.add("BMX");
		listArray.add("Quady");
		listArray.add("Gocardy");
		listArray.add("Golf");
		listArray.add("Jazda konna");
		listArray.add("Paintball");
		listArray.add("Tenis");

	}

	public void wodne() {
		listArray.add("P³ywanie");
		listArray.add("Kajaki");
		listArray.add("P³ywanie ³ódk¹"); // pontonem?
		listArray.add("Nurkowanie");
		listArray.add("Narty wodne");
		listArray.add("Pi³ka wodna");
	}

	public void wZimie() {
		listArray.add("£y¿wy");
		listArray.add("Narciarstwo");
		listArray.add("Hokej");
		listArray.add("Sanki");

	}

	public void nadMorzem() {
		listArray.add("Serfowanie");
		listArray.add("Siatkówka pla¿owa");

	}

	public void ekstremalne() {
		listArray.add("Parkour");
		listArray.add("Bungee");
		listArray.add("Paralotnia");
		listArray.add("Skok ze spadochronem");
		listArray.add("Windsurfing");
		// polowanie jednak nie
		listArray.add("Lot balonem");
	}

}
