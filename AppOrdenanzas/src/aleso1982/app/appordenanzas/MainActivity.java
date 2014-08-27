package aleso1982.app.appordenanzas;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


public class MainActivity extends Activity implements TextWatcher{
	
	private Spinner spinner1;
	private EditText et1;
	private ListView lv1;
	
	private JSONObject cadena = new JSONObject();
	
	
	ArrayList<Fila> filtro = new ArrayList<Fila>();
	ArrayList<Fila> fila = new ArrayList<Fila>();
	
	MiAdaptador adapter;
    	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et1 = (EditText) findViewById(R.id.et1);
        et1.addTextChangedListener(this);
        
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        lv1 = (ListView) findViewById(R.id.lv1);
        
        String []opciones={"Articulo", "Hecho", "Cuantía", "Puntos"};       
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(arrayAdapter); 
        
        ArrayList<Fila> row = obtenerItem();
        filtro.addAll(row);
        adapter = new MiAdaptador(this, filtro);
        lv1.setAdapter(adapter);                
        
    }
    
    private ArrayList<Fila> obtenerItem(){
    	
    	fila.add(new Fila("Articulo: 6.1", 
    					  "Comportarse de forma que se entorpece indebidamente la circulación",
    					  "80 €", 
    					  "Puntos: 0")
    	);
    	
    	fila.add(new Fila("Articulo: 6.2.1", 
    					  "Conducir de forma negligente",
    					  "200 €",
    					  "Puntos: 0")
    	);
    	
    	fila.add(new Fila("Articulo: 6.2.2", 
    					  "Conducir de forma temeraria",
    					  "500 €",
    					  "Puntos: 6")
    	);
    	
    	fila.add(new Fila ("Articulo: 8.1.1", 
    					   "Conducir un vehículo sin estar en todo momento en condiciones de controlarlo", 
    					   "80 €", "Puntos: 0")
    	);
    	
    	return fila;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		String spinnerSelect = spinner1.getSelectedItem().toString();
		String buscar = s.toString();
		filtro.clear();
		
		if (spinnerSelect.equals("Articulo")) {
			for (int i = 0; i < fila.size(); i++) {
				if (fila.get(i).getArticulo().contains(buscar)) {
					getFilterRow(i);	
				}
			}
		}
		
		if (spinnerSelect.equals("Hecho")) {
			for (int i = 0; i < fila.size(); i++) {
				if (fila.get(i).getHecho().contains(buscar)) {
					getFilterRow(i);
				}				
			}
		}
		
		if (spinnerSelect.equals("Cuantía")) {
			for (int i = 0; i < fila.size(); i++) {
				if (fila.get(i).getCuantia().contains(buscar)) {
					getFilterRow(i);
				}				
			}
		}
		
		if (spinnerSelect.equals("Puntos")) {
			for (int i = 0; i < fila.size(); i++) {
				if (fila.get(i).getPuntos().contains(buscar)) {
					getFilterRow(i);
				}				
			}
		}
		
		adapter.notifyDataSetChanged();		
	}
	
	private void getFilterRow(int i) {
		filtro.add(new Fila(fila.get(i).getArticulo(), 
							fila.get(i).getHecho(),
							fila.get(i).getCuantia(),
							fila.get(i).getPuntos())
		);
	}
	
}
