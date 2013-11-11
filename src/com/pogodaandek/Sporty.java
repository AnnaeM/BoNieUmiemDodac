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
	ArrayList<String> listArray = new ArrayList<String>();

	/*
	 * String listArray[] = { "Bieganie", "P�ywanie", "Jazda na rowerze",
	 * "�y�wy", "Narciarstwo", "Pi�ka siatkowa", "Koszyk�wka", "Pi�ka no�na",
	 * "Badminton", "Tenis", "Squash", "Joga", "Wyj�cie na si�owni�",
	 * "�eglarstwo", "Nordic walking", "Trekking", "Serfowanie", "Parkour",
	 * "Jazda na rolkach", "Deskorolka", "Jazda na bmx", "Jazda na quadzie",
	 * "Wyj�cie na cardy", "Siatk�wka pla�owa", "Kajakarstwo",
	 * "P�ywanie ��dk�/pontonem", "Hokej", "Golf", "Szermierka", "�ucznictwo",
	 * "Wyj�cie na strzelnic�", "Bungee", "Nurkowanie", "Paralotnia",
	 * "Skok ze spadochronem", "Windsurfing", "Jazda konna", "Wspinaczka",
	 * "Ping-pong", "Trening sztuk walki", "Narty wodne", "Polowanie",
	 * "Lot balonem", "Pi�ka wodna", "Paintball" };
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
			
			miasto = jObject.getString("city");
			temp = jObject.getDouble("feelslike_c"); // temp odczuwalna
			pogoda = jObject.getString("weather");
			// miesiac = jObject.getInt("month");
			String m = jObject.getString("month");
			miesiac = Integer.parseInt(m);

			godzina = jObject.getInt("hour");
			dzienTygodnia = jObject.getString("weekDay");

			wyborSportow(jObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (listArray.isEmpty()) {
			listArray.add("Zosta� w domu");
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

	public void wyborSportow(JSONObject jObject){

		
		Log.i("Test", jObject.toString());

		char poraDnia = poraDnia();

		int i;
		if (pogoda.equals("pogodnie")) {
			ladnaPogoda(poraDnia);
		} else if (pogoda.equals("przewaga chmur"))
			ladnaPogoda(poraDnia);
		else if (pogoda.equals("ob�oki zanikaj�ce"))
			ladnaPogoda(poraDnia);
		else if (pogoda.equals("�nieg"))
			deszczowaPogoda(poraDnia);
		else if (pogoda.equals("niewielkie zachmurzenie"))
			ladnaPogoda(poraDnia);
		else if (pogoda.equals("deszcz"))
			deszczowaPogoda(poraDnia);
		else if (pogoda.equals("lekki deszcz"))
			deszczowaPogoda(poraDnia);
		else if (pogoda.equals("pochmurno"))
			ladnaPogoda(poraDnia);
		else if (pogoda.equals("p�atki mg�y"))
			ladnaPogoda(poraDnia);
		else if (pogoda.equals("lekkie przelotne deszcze"))
			deszczowaPogoda(poraDnia);
		else if(pogoda.equals("lekka m�awka"))	
			ladnaPogoda(poraDnia);
		else if(pogoda.equals("zamglenia"))
			ladnaPogoda(poraDnia);
		else if(pogoda.equals("m�awka"))
			deszczowaPogoda(poraDnia);
		else if(pogoda.equals("mg�a"))
			ladnaPogoda(poraDnia);
		else if(pogoda.equals("lekka mg�a"))
			ladnaPogoda(poraDnia);
		else if(pogoda.equals("cz�ciowe zamglenia"))
			ladnaPogoda(poraDnia);
		else
			listArray.add("Nieznany rodzaj pogody");

	}

	public char poraDnia() {

		char pora;

		if ((godzina >= 6) && (godzina < 10))
			pora = 'r'; // ranek
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'p'; // "poludnie";
		else if ((godzina >= 11) && (godzina < 13))
			pora = 'd'; // "dzie�";
		else if ((godzina >= 14) && (godzina < 17))
			pora = 'o'; // "popo�udnie";
		else if ((godzina >= 18) && (godzina < 21))
			pora = 'w'; // "wiecz�r";
		else if ((godzina >= 22) && (godzina < 1))
			pora = 'n'; // "noc";
		else
			pora = 'g'; // "g��boka noc";

		return pora;
	}

	public void ladnaPogoda(char poraDnia) {

		char poraRoku = poraRoku();

		switch (poraRoku) {
		case 'w': {
			// wiosna
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, wiosna, ranek, dowolny dzie� tygodnia (bo
				// do 10 i tak wszystko zamkni�te)

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
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, wiosna, po�udnie+dzien+popoludnie+wiecz�r, na
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

						// jest ladna pogoda, wiosna, po�udnie+dzien+popoludnie+wiecz�r,
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
				// jest ladna pogoda, wiosna, noc+g��boka noc, dowolny dzien
				if ((temp >= -5) && (temp <= 30)) {
					bieganie();
					standardowe();
				}
				break;
			}
			default: {
				Log.i("B��d", "Nie ma poryDnia");
			}

			}

			break;
		}
		case 'l': {// lato

			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, lato, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)

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
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, lato, po�udnie+dzien+popoludnie+wiecz�r, na
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

						// jest ladna pogoda, lato, po�udnie+dzien+popoludnie+wiecz�r,
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
				// jest ladna pogoda, lato, noc+g��boka noc, dowolny dzien
				if ((temp >= 0) && (temp <= 30)) {
					bieganie();
					standardowe();
				}
				break;
			}
			default: {
				Log.i("B��d", "Nie ma poryDnia");
			}
				break;
			}
		}
		case 'j': { // jesien
			
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, jesien, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)

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
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, jesien, po�udnie+dzien+popoludnie+wiecz�r, na
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

						// jest ladna pogoda, jesien, po�udnie+dzien+popoludnie+wiecz�r,
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
				// jest ladna pogoda, jesien, noc+g��boka noc, dowolny dzien
				if ((temp >= -5) && (temp <= 25)) {
					bieganie();
					standardowe();
				}

				break;
			}
			default: {
				Log.i("B��d", "Nie ma poryDnia");
			}
				break;
			}
			
			break;
		}
		case 'z': { // zima
			
			switch (poraDnia) {
			case 'r': {

				// jest ladna pogoda, zima, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)
			
				if ((temp >= -15) && (temp <= 20)) {
					bieganie();
					standardowe();
					listArray.add("Sanki");
				}

				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest ladna pogoda, zima, po�udnie+dzien+popoludnie+wiecz�r, na
					// tygodniu + sobota
					
					if ((temp>=-30)&&(temp<=-15)){				
						naHali();
					}
						
					else if ((temp >= -15) && (temp < 20)) {
						bieganie();
						standardowe();
						naHali();
						naDworze();
						wZimie();
						listArray.add("Sanki");

					}

					else {

						// jest ladna pogoda, zima, po�udnie+dzien+popoludnie+wiecz�r,
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
				// jest ladna pogoda, zima, noc+g��boka noc, dowolny dzien
				if ((temp >= -10) && (temp <= 20)) {
					bieganie();
					standardowe();
				}

				break;
			}
			default: {
				Log.i("B��d", "Nie ma poryDnia");
			}
				break;
			}
			
			break;
		}
		default: {
			Log.i("B��d","Z�a pora roku");
		}

		}
	}
	
	public void deszczowaPogoda(char poraDnia) {

		char poraRoku = poraRoku();

		switch (poraRoku) {
		case 'w': {
			// wiosna
			switch (poraDnia) {
			case 'r': {

				// jest deszczowa pogoda, wiosna, ranek, dowolny dzie� tygodnia (bo
				// do 10 i tak wszystko zamkni�te)

				if ((temp >= -5) && (temp <= 25)) {
					//co si� robi rano, kiedy pada?
					listArray.add("�pij dalej");
					listArray.add("Zosta� w domu");
				}
				break;
			}

			case 'p':
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest deszczowa pogoda, wiosna, po�udnie+dzien+popoludnie+wiecz�r, na
					// tygodniu + sobota
					
					if ((temp>=-10)&&(temp<0)){
						naHali();
					}
					else if ((temp >= 0) && (temp < 35)) {					
						naHali();					

					}

					else {

						// jest deszczowa pogoda, wiosna, po�udnie+dzien+popoludnie+wiecz�r,
						// niedziela
						if ((temp >= 5) && (temp <= 35)) {							
							listArray.add("Zosta� w domu");
						}

					}
				}
				break;
			}

			// w nocy i tak doda "Zosta� w domu"
			
			default: {		
				Log.i("Deszczowo", "Zosta� w domu");
			}
			}
			break;
		}
		case 'l': {// lato

			switch (poraDnia) {
			case 'r': {

				// jest deszczowa pogoda, lato, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)
				listArray.add("Czytaj ksi��k�");
				listArray.add("Zosta� w domu");
				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest deszczowa pogoda, lato, po�udnie+dzien+popoludnie+wiecz�r, na
					// tygodniu + sobota
					
					if ((temp>=-5)&&(temp<=35)){
						naHali();
					}

				}
				break;
			}

			
			default: {
				Log.i("Deszczowo", "Zosta� w domu");
			}
				break;
			}
		}
		case 'j': { // jesien
			
			switch (poraDnia) {
			case 'r': {

				// jest deszczowa pogoda, jesien, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)
				listArray.add("�pij dalej");
				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest deszczowa pogoda, jesien, po�udnie+dzien+popoludnie+wiecz�r, na
					// tygodniu + sobota
					
					if((temp>=-20)&&(temp<=35)){						
						naHali();							
					}
					

					else {

						// jest deszczowa pogoda, jesien, po�udnie+dzien+popoludnie+wiecz�r,
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
			default: {
				Log.i("Deszczowo", "Zosta� w domu");
			}
				break;
			}
			
			break;
		}
		case 'z': { // zima
			
			switch (poraDnia) {
			case 'r': {

				// jest deszczowa pogoda, zima, ranek, dowolny dzie� tygodnia (bo do
				// 10 i tak wszystko zamkni�te)
				listArray.add("�pij dalej");
				break;
			}

			case 'p': 
			case 'd':
			case 'o':
			case 'w': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))
						|| (dzienTygodnia.equals("Wtorek"))
						|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))
						|| (dzienTygodnia.equals("Pi�tek"))
						|| (dzienTygodnia.equals("Sobota"))) {

					// jest deszczowa pogoda, zima, po�udnie+dzien+popoludnie+wiecz�r, na
					// tygodniu + sobota
					
					if ((temp>=-30)&&(temp<=20)){				
						naHali();
					}																
				}
				break;
			}

			
			default: {
				Log.i("Deszczowo", "Zosta� w domu");
			}
				break;
			}
			
			break;
		}
		default: {
			Log.i("B��d","Z�a pora roku");
		}

		}
	}

	
	
	public char poraRoku() {

		// mo�na doda� np. przedwio�nie
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

		listArray.add("Siatk�wka");
		listArray.add("Koszyk�wka");
		listArray.add("Pi�ka no�na");
		listArray.add("Badminton");
		listArray.add("Squash");
		listArray.add("Si�ownia");
		listArray.add("Szermierka");
		listArray.add("�ucznictwo");
		listArray.add("Strzelnica");
		listArray.add("�ciana wspinaczkowa");
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
		listArray.add("P�ywanie");
		listArray.add("Kajaki");
		listArray.add("P�ywanie ��dk�"); // pontonem?
		listArray.add("Nurkowanie");
		listArray.add("Narty wodne");
		listArray.add("Pi�ka wodna");
	}

	public void wZimie() {
		listArray.add("�y�wy");
		listArray.add("Narciarstwo");
		listArray.add("Hokej");
		listArray.add("Sanki");

	}

	public void nadMorzem() {
		listArray.add("Serfowanie");
		listArray.add("Siatk�wka pla�owa");

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
