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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity implements TextWatcher {

	private Spinner spinner1;
	private EditText et1;
	private ListView lv1;

	// InputStream is = getResources().openRawResource(R.raw.omc);
	// BufferedReader reader = new BufferedReader(is);
	// //JSONArray jsonArray = new JSONArray(is);

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

		String[] opciones = { "Articulo", "Hecho", "Cuantía", "Puntos" };
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, opciones);
		spinner1.setAdapter(arrayAdapter);

		// Obtiene y almacena los articulos el el arrayList fila
		leerArticulos(obtenerJson());

		//ArrayList<Fila> row = obtenerItem();
		filtro.addAll(fila);
		adapter = new MiAdaptador(this, filtro);
		lv1.setAdapter(adapter);

	}


	public void leerArticulos(JSONObject json) {
		String articulo = ""; // Almacena el articulo
		String hecho = ""; // Almacena el hecho
		String cuantia = ""; // Almacena la cuantia
		String puntos = ""; // Almacena los puntos

		JSONArray arrayArticulos; // Almacena los articulos
		JSONObject objeto; // Almacena un objeto
		JSONObject objetoFila; // Almacena el objeto Fila

		try {

			// Inicialización de campos
			objeto = new JSONObject();
			objetoFila = new JSONObject();

			// Almacena las filas
			arrayArticulos = new JSONArray(json.getJSONArray("omc").toString());

			// Recorre las ciudades
			for (int i = 0; i < arrayArticulos.length(); i++) {

				// Obtine la fila del articulo
				objeto = arrayArticulos.getJSONObject(i);
				objetoFila = objeto.getJSONObject("art");

				// Almacena los datos del articulo
				articulo = objetoFila.getString("Articulo"); // Almacena el articulo
				hecho = objetoFila.getString("Hecho"); // Almacena el hecho
				cuantia = objetoFila.getString("cuantia"); // Almacena la cuantia
				puntos = objeto.getString("Puntos"); // Almacena los puntos

				// Almacena la ciudad obtenida
				fila.add(new Fila(articulo, hecho, cuantia, puntos));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			InputStream fichero = getResources().openRawResource(R.raw.omc);

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

	private ArrayList<Fila> obtenerItem() {

		fila.add(new Fila(
				"Articulo: 6.1",
				"Comportarse de forma que se entorpece indebidamente la circulación",
				"80 €", "Puntos: 0"));

		fila.add(new Fila("Articulo: 6.2.1", "Conducir de forma negligente",
				"200 €", "Puntos: 0"));

		fila.add(new Fila("Articulo: 6.2.2", "Conducir de forma temeraria",
				"500 €", "Puntos: 6"));

		fila.add(new Fila(
				"Articulo: 8.1.1",
				"Conducir un vehículo sin estar en todo momento en condiciones de controlarlo",
				"80 €", "Puntos: 0"));

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
		filtro.add(new Fila(fila.get(i).getArticulo(), fila.get(i).getHecho(),
				fila.get(i).getCuantia(), fila.get(i).getPuntos()));
	}

}
