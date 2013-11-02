package com.pogodaandek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wypoczynek, menu);
		return true;
	}
	
	String listArray[] = { "Spacer","Spotkanie z przyjaci�mi","Wyj�cie do kina","Wyj�cie na kaw�","Sied� w domu","Wyj�cie na pla��","Grzybobranie","Zbieranie jag�d",
							"Kr�gle","Bilard","Spacer z psem","Puszczanie latawca","Wyjazd na dzia�k�/wie�",
							"Id� na wydarzenie w mie�cie","K�ko plastyczne","K�ko muzyczne","K�ko modelarskie",
							"Koncert","Muzeum","Biblioteka","Teatr","Fotografowanie","Rysowanie krajobrazu",
							"Piknik","Zoo","Cyrk","Aquapark","Przeja�d�ka promem","Wyjd� na imprez�",
							"Ogl�danie wschodu s�o�ca","Ogl�danie zachodu s�o�ca","Obserwowanie gwiazd",
							"Obserwowanie chmur","Randka w ciemno","Opalanie",
							"Zakupy","Id� na badania kontrolne"
	};
	
	
	

	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sporty);
		
	
		//Collections.sort(listArray);
		
		ArrayAdapter<String> array = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listArray);
		
		/*Collections.sort(array);
		
		Collections.sort(array, new Comparator<String>()
				{
				    @Override
				    public int compare(String text1, String text2)
				    {
				        return text1.compareToIgnoreCase(text2);
				    }
				});
		*/
		
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
	
}
