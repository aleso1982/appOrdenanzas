package aleso1982.app.appordenanzas;

public class Fila {
	
	private String articulo, apartado, opcion, caso, hecho, cuantia, puntos;
	
	public Fila(String articulo, String apartado, String opcion, String caso,
			String hecho, String cuantia, String puntos) {
		super();
		this.articulo = articulo;
		this.apartado = apartado;
		this.opcion = opcion;
		this.caso = caso;
		this.hecho = hecho;
		this.cuantia = cuantia;
		this.puntos = puntos;
	}

	public Fila(String articulo, String apartado, String opcion, String hecho,
			String cuantia, String puntos) {
		super();
		this.articulo = articulo;
		this.apartado = apartado;
		this.opcion = opcion;
		this.hecho = hecho;
		this.cuantia = cuantia;
		this.puntos = puntos;
	}

	public Fila(String articulo, String apartado, String hecho, String cuantia,
			String puntos) {
		super();
		this.articulo = articulo;
		this.apartado = apartado;
		this.hecho = hecho;
		this.cuantia = cuantia;
		this.puntos = puntos;
	}

	

}
