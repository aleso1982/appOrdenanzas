package aleso1982.app.appordenanzas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class Omc extends Activity implements TextWatcher{
	private Spinner spinner1;
	private EditText et1;
	private ListView lv1;

	ArrayList<Fila> filtro = new ArrayList<Fila>();
	ArrayList<Fila> fila = new ArrayList<Fila>();

	MiAdaptador adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omc);
		
		et1 = (EditText) findViewById(R.id.et1);
		et1.addTextChangedListener(this);

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		lv1 = (ListView) findViewById(R.id.lv1);
		
		getSpinnerOptions();

		ArrayList<Fila> row = leerArticulos(obtenerJson());
		filtro.addAll(row);
		adapter = new MiAdaptador(this, filtro);
		lv1.setAdapter(adapter);
	}
	
	private void getSpinnerOptions() {
		String[] opciones = { "Articulo", "Hecho", "Cuantía", "Puntos" };
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, opciones);
		spinner1.setAdapter(arrayAdapter);
	}
	
	public JSONObject obtenerJson() {
		JSONObject json = null; // Almacena el objeto JSON
		StringBuilder stringBuilder; // Almacena el contenido del fichero JSON
		String linea; // Almacena la línea leida del fichero JSON

		try {
			// Inicialización de campos
			stringBuilder = new StringBuilder();
			linea = null;
			
			// Obtiene el fichero JSON
			InputStream fichero = 
					getResources().openRawResource(R.raw.omc);

			// Prepara el fichero para su lectura
			BufferedReader bfreader = new BufferedReader(new InputStreamReader(
					fichero));

			// Lee el fichero JSON
			while ((linea = bfreader.readLine()) != null) {
				// Añade el contenido líena a línea
				stringBuilder.append(linea + "\n");
			}

			// Cierra el fichero
			fichero.close();
			// Crea el objeto JSON
			json = new JSONObject(stringBuilder.toString());
		} catch (Exception ex) {
		}
		return json;
	}

	public ArrayList<Fila> leerArticulos(JSONObject json) {
		String articulo, hecho, cuantia, puntos;

		JSONArray arrayArticulos; // Almacena los articulos
		JSONObject objeto; // Almacena un objeto

		try {
			objeto = new JSONObject();
			// Almacena las filas
			arrayArticulos = new JSONArray(json.getJSONArray("omc").toString());

			// Recorre los articulos
			for (int i = 0; i < arrayArticulos.length(); i++) {
				// Obtine la fila del articulo
				objeto = arrayArticulos.getJSONObject(i);
				//objetoFila = objeto.getJSONObject("art");

				// Almacena los datos del articulo
				articulo = objeto.getString("Articulo");
				hecho = objeto.getString("Hecho");
				cuantia = objeto.getString("Cuantia");
				puntos = objeto.getString("Puntos");

				// Almacena el articulo obtenido
				fila.add(new Fila("Art: " + articulo, hecho, "Cuantía: " + cuantia + "€", "Puntos: " + puntos));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fila;
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
		String buscar = s.toString().toLowerCase();
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
				if (fila.get(i).getHecho().toLowerCase().contains(buscar)) {
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
		filtro.add(new Fila(fila.get(i).getArticulo(), fila.get(i).getHecho(),
				fila.get(i).getCuantia(), fila.get(i).getPuntos()));
	}

	
	public void cerrar(View v){
		finish();
	}

}
