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
	//String listArray[]=null;
	ArrayList<String> listArray = new ArrayList<String>();

	// String listArray[];
	/*String listArray[] = { "Bieganie", "P�ywanie", "Jazda na rowerze", "�y�wy",
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
		//miesiac = jObject.getInt("month");
		String m = jObject.getString("month");
		miesiac = Integer.parseInt(m);
		
		godzina = jObject.getInt("hour");
		dzienTygodnia = jObject.getString("weekDay");
		
		Log.i("Test", jObject.toString());

		char poraDnia = poraDnia();
			
		int i;
		if(pogoda.equals("pogodnie"))
			{Log.i("Info","Pogodnie");
			ladnaPogoda(poraDnia);}
		else if (pogoda.equals("przewaga chmur"))
			i=2;
		else if (pogoda.equals("ob�oki zanikaj�ce"))
			i=3;
		else if (pogoda.equals("�nieg"))
			i=4;
		else if (pogoda.equals("niewielkie zachmurzenie"))
			i=5;
		else if (pogoda.equals("deszcz"))
			i=6;
		else if (pogoda.equals("lekki deszcz"))
			i=7;
		else if (pogoda.equals("pochmurno"))
			i=8;
		else if (pogoda.equals("p�atki mg�y"))
			i=9;
		else if (pogoda.equals("lekkie przelotne deszcze "))
			i=10;
		else
			i=0;

	}


	public char poraDnia() {

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

	public void ladnaPogoda(char poraDnia){

		char poraRoku = poraRoku();
		
		switch (poraRoku){
		case 'w':{
			//wiosna
			switch (poraDnia) {
			case 'm': {
				if ((dzienTygodnia.equals("Poniedzia�ek"))|| (dzienTygodnia.equals("Wtorek"))|| (dzienTygodnia.equals("�roda"))
						|| (dzienTygodnia.equals("Czwartek"))|| (dzienTygodnia.equals("Pi�tek"))) {
				
					//jest ladna pogoda, wiosna, wczesny ranek i na tygodniu
					
					if(temp<10){
						listArray.add("Bieganie");
						listArray.add("Rower");
						listArray.add("Joga");
						
					}
					
					
				}

				else if (dzienTygodnia == "Sobota") {
					//jest ladna pogoda, wiosna, wczesny ranek i sobota	
				
				} else { // jest ladna pogoda, wiosna, wczesny ranek i niedziela

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
			default:{
				Log.i("Info","nie ma poryDnia");
			}

			}
			
			break;
		}
		case 'l':{//lato		
			break;
		}
		case 'j':{	//jesien
			break;
		}
		case 'z':{	//zima
			break;
		}
		default:{
			
		}
		
		}
		}
		
	
	public char poraRoku(){
		
		//mo�na doda� np. przedwio�nie
		char c;
		
		if((miesiac==4)||(miesiac==5)){	//kwiecien-maj
			c='w';
		}
		else if((miesiac>=6)&&(miesiac<=8)){	//czerwiec-sierpien
			c='l';
		}
		else if((miesiac>=9)&&(miesiac<=11)){	//wrzesien-list
			c='j';
		}
		else	//grudzien-marzec
			{c='z';}
		
		return c;
		
	}
	
}
