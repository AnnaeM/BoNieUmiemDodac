package com.pogodaandek;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class GPS extends Activity implements LocationListener {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.g, menu);
		return true;
	}

	private LocationManager locationManager;

	protected void onResume(){
		
		super.onResume();
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("GPS nie jest w³¹czony")
				.setMessage("Czy chcesz przejœæ do ustawieñ GPS?")
				.setCancelable(true)
				.setPositiveButton("Tak",
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int id) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
						}
					})
			.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int id) {
					// TODO Auto-generated method stub
					dialog.cancel();
					finish();
				}
			});
			
			AlertDialog alert = builder.create();
			alert.show();		
			
		}
		
		else{
			//LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 
		    getSystemService(Context.LOCATION_SERVICE);		
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);		
	    		
			//TextView tekst=(TextView)this.findViewById(R.id.lokacja);
			Location location=null;
			
			int i=0;
			while(location==null){
				
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);			    		
				location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				i++;
			}
			
			double szerokosc = (double) (location.getLatitude());
		    double dlugosc = (double) (location.getLongitude());
		    
		    
		   // tekst.setText("D³ugoœæ "+String.valueOf(szerokosc)+"\nSzerokoœæ "+String.valueOf(dlugosc)+"\nPêtla przesz³a "+i+" razy");
		    doPogody(dlugosc, szerokosc);
		   		    
	
		}
		
		
	}

	public void doPogody(double dlugosc, double szerokosc){
		Intent intent = new Intent(this, ForecastActivity.class);	
		
		String dl=String.format("%.4f", dlugosc);
		String szer=String.format("%.4f", szerokosc);
	
		dl = dl.replace(',','.');
		szer = szer.replace(',','.');
		
		//String message = String.format("%.4f", dlugosc)+","+String.format("%.4f", szerokosc);
		String message = szer+","+dl;

		intent.putExtra("Lokacja", message);
		startActivity(intent);
		
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		double latitude = (double) (location.getLatitude());
		double longitude = (double) (location.getLongitude());

		Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: "
				+ longitude);

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
