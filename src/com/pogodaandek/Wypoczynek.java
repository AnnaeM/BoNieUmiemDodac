package com.pogodaandek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Wypoczynek extends ListActivity {

	public JSONObject jObject;
	String dzienTygodnia;
	Double temp;
	String miasto;
	int miesiac;
	int godzina;
	String pogoda;
	ArrayList<String> listArray = new ArrayList<String>();
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wypoczynek, menu);
		return true;
	}
	
	/*String listArray[] = { "Spacer","Spotkanie z przyjaci�mi","Wyj�cie do kina","Wyj�cie na kaw�","Sied� w domu","Wyj�cie na pla��","Grzybobranie","Zbieranie jag�d",
							"Kr�gle","Bilard","Spacer z psem","Puszczanie latawca","Wyjazd na dzia�k�/wie�",
							"Id� na wydarzenie w mie�cie","K�ko plastyczne","K�ko muzyczne","K�ko modelarskie",
							"Koncert","Muzeum","Biblioteka","Teatr","Fotografowanie","Rysowanie krajobrazu",
							"Piknik","Zoo","Cyrk","Aquapark","Przeja�d�ka promem","Wyjd� na imprez�",
							"Ogl�danie wschodu s�o�ca","Ogl�danie zachodu s�o�ca","Obserwowanie gwiazd",
							"Obserwowanie chmur","Randka w ciemno","Opalanie",
							"Zakupy","Id� na badania kontrolne"
	};
	
	*/
	

	
	
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

			//wyborSportow(jObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (listArray.isEmpty()) {
			listArray.add("Zosta� w domu");
		}
		//Collections.sort(listArray);
		
		ArrayAdapter<String> array = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listArray);
		
		
		IgnoreCaseComparator icc = new IgnoreCaseComparator();

		//java.util.Collections.sort(array,icc);
		
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
	
	
	public void podstawowe(){
		listArray.add("Spacer");
		listArray.add("Spotkanie z przyjaci�mi");
		listArray.add("Spacer z psem");		
		listArray.add("Fotografowanie");
		listArray.add("Rysowanie krajobrazu");
		listArray.add("Wyjazd na dzia�k�/wie�");
	}
	
	public void niePada(){
		listArray.add("Piknik");
		listArray.add("Zoo");
		
	}
	
	public void podDachem(){
		listArray.add("Kino");
		listArray.add("Kawa");
		listArray.add("Kr�gle");
		listArray.add("Bilard");
		listArray.add("Muzeum");
		listArray.add("Biblioteka");
		listArray.add("Teatr");
		listArray.add("Aquapark");
		listArray.add("Zakupy");
		listArray.add("K�ko plastyczne/muzyczne/modelarskie");
	}
	
	public void wLesie(){
		listArray.add("Grzybobranie");
		listArray.add("Zbieranie jag�d");
		
	}
	
	public void okazjonalne(){
		listArray.add("Koncert");
		listArray.add("Cyrk");
		listArray.add("Id� na wydarzenie w mie�cie");
	}
	
	public void wieczorem(){
		listArray.add("Impreza");		
		listArray.add("Randka w ciemno");
		
	}
	
	public void zalezne(){
		listArray.add("Ogl�danie zachodu s�o�ca");
		listArray.add("Ogl�danie wschodu s�o�ca");
		listArray.add("Obserwowanie gwiazd");
		listArray.add("Obserwowanie chmur");
		
		listArray.add("Opalanie");
		listArray.add("Puszczanie latawca");
		
		listArray.add("Wyj�cie na pla��");
		listArray.add("Przeja�d�ka promem");
		listArray.add("Id� na badania kontrolne");
		
	}
}
