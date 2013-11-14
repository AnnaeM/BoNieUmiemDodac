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
	
	/*String listArray[] = { "Spacer","Spotkanie z przyjació³mi","Wyjœcie do kina","Wyjœcie na kawê","SiedŸ w domu","Wyjœcie na pla¿ê","Grzybobranie","Zbieranie jagód",
							"Krêgle","Bilard","Spacer z psem","Puszczanie latawca","Wyjazd na dzia³kê/wieœ",
							"IdŸ na wydarzenie w mieœcie","Kó³ko plastyczne","Kó³ko muzyczne","Kó³ko modelarskie",
							"Koncert","Muzeum","Biblioteka","Teatr","Fotografowanie","Rysowanie krajobrazu",
							"Piknik","Zoo","Cyrk","Aquapark","Przeja¿d¿ka promem","WyjdŸ na imprezê",
							"Ogl¹danie wschodu s³oñca","Ogl¹danie zachodu s³oñca","Obserwowanie gwiazd",
							"Obserwowanie chmur","Randka w ciemno","Opalanie",
							"Zakupy","IdŸ na badania kontrolne"
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
			listArray.add("Zostañ w domu");
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
		listArray.add("Spotkanie z przyjació³mi");
		listArray.add("Spacer z psem");		
		listArray.add("Fotografowanie");
		listArray.add("Rysowanie krajobrazu");
		listArray.add("Wyjazd na dzia³kê/wieœ");
	}
	
	public void niePada(){
		listArray.add("Piknik");
		listArray.add("Zoo");
		
	}
	
	public void podDachem(){
		listArray.add("Kino");
		listArray.add("Kawa");
		listArray.add("Krêgle");
		listArray.add("Bilard");
		listArray.add("Muzeum");
		listArray.add("Biblioteka");
		listArray.add("Teatr");
		listArray.add("Aquapark");
		listArray.add("Zakupy");
		listArray.add("Kó³ko plastyczne/muzyczne/modelarskie");
	}
	
	public void wLesie(){
		listArray.add("Grzybobranie");
		listArray.add("Zbieranie jagód");
		
	}
	
	public void okazjonalne(){
		listArray.add("Koncert");
		listArray.add("Cyrk");
		listArray.add("IdŸ na wydarzenie w mieœcie");
	}
	
	public void wieczorem(){
		listArray.add("Impreza");		
		listArray.add("Randka w ciemno");
		
	}
	
	public void zalezne(){
		listArray.add("Ogl¹danie zachodu s³oñca");
		listArray.add("Ogl¹danie wschodu s³oñca");
		listArray.add("Obserwowanie gwiazd");
		listArray.add("Obserwowanie chmur");
		
		listArray.add("Opalanie");
		listArray.add("Puszczanie latawca");
		
		listArray.add("Wyjœcie na pla¿ê");
		listArray.add("Przeja¿d¿ka promem");
		listArray.add("IdŸ na badania kontrolne");
		
	}
}
