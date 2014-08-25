package aleso1982.app.appordenanzas;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MiAdaptador extends BaseAdapter {
	
	protected Activity activity;
	protected ArrayList<Fila> fila;
	
	public MiAdaptador(Activity activity, ArrayList<Fila> fila) {
		super();
		this.activity = activity;
		this.fila = fila;
	}

	@Override
	public int getCount() {
		return fila.size();
	}

	@Override
	public Object getItem(int position) {
		return fila.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		View vi = contentView;
		
		if (contentView == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.fila, null);
		}
		
		Fila row = fila.get(position);
		
		TextView tvArticulo = (TextView) vi.findViewById(R.id.tvArticulo);
		tvArticulo.setText(row.getArticulo());
		
		TextView tvHecho = (TextView) vi.findViewById(R.id.tvHecho);
		tvHecho.setText(row.getHecho());
		
		TextView tvCuantia = (TextView) vi.findViewById(R.id.tvCuantia);
		tvCuantia.setText(row.getCuantia());
		
		TextView tvPuntos = (TextView) vi.findViewById(R.id.tvPuntos);
		tvPuntos.setText(row.getPuntos());
		
		return vi;
	}

}
