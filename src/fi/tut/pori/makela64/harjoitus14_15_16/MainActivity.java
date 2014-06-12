package fi.tut.pori.makela64.harjoitus14_15_16;

import java.util.List;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {
	private HenkiloDataSource datasource;
	private int laskuri;
	private EditText syntymaaika;
	private EditText etunimi;
	private EditText sukunimi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		datasource = new HenkiloDataSource(this);
		datasource.open();
		List<Henkilo> values = datasource.getAllHenkilot();
		ArrayAdapter<Henkilo> adapter = new ArrayAdapter<Henkilo>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	public void onClick(View view){
		ArrayAdapter<Henkilo> adapter = (ArrayAdapter<Henkilo>) getListAdapter();
		Henkilo henkilo = null;
		switch (view.getId()) {
		case R.id.LisaaHenkiloButton:
			syntymaaika=(EditText) findViewById(R.id.SyntymaaikaEditText);
			etunimi=(EditText) findViewById(R.id.EtunimiEditText);
			sukunimi=(EditText) findViewById(R.id.SukunimiEditText);
			henkilo=datasource.createHenkilo(syntymaaika.getText().toString(), etunimi.getText().toString(), sukunimi.getText().toString());
			adapter.add(henkilo);
			break;
		}
		
		adapter.notifyDataSetChanged();
	}
	public void poistaHenkilo(View view){
		ArrayAdapter<Henkilo> adapter = (ArrayAdapter<Henkilo>) getListAdapter();
		Henkilo henkilo = null;
		switch (view.getId()) {
		case R.id.PoistaHenkiloButton:
			
			syntymaaika=(EditText) findViewById(R.id.SyntymaaikaEditText);
			etunimi=(EditText) findViewById(R.id.EtunimiEditText);
			sukunimi=(EditText) findViewById(R.id.SukunimiEditText);
			datasource.removeHenkilo(syntymaaika.getText().toString(), etunimi.getText().toString(), sukunimi.getText().toString());
			
			for(int i=0;i<adapter.getCount();i++){
				Log.d(Nimivakio.LOG, adapter.getItem(i).getSyntymaaika());
				Log.d(Nimivakio.LOG, syntymaaika.getText().toString());
				if(adapter.getItem(i).getSyntymaaika().equals(syntymaaika.getText().toString())  ){
					Log.d(Nimivakio.LOG, "Poista Henkilo -nappulaa painettiin");
					adapter.remove(adapter.getItem(i));
					
				}
			}
 		    
			break;
			
			
		}
		adapter.notifyDataSetChanged();
		
	}
	protected void onResume(){
		datasource.open();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
