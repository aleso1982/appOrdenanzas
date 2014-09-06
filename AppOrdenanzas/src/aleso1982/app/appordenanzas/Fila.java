package aleso1982.app.appordenanzas;

public class Fila {
	
	private String articulo, hecho, cuantia, puntos;
	
	public Fila(String articulo, String hecho, String cuantia,
			String puntos) {
		super();
		this.articulo = articulo;
		this.hecho = hecho;
		this.cuantia = cuantia;
		this.puntos = puntos;
	}

	public String getArticulo() {
		return articulo;
	}

	public String getHecho() {
		return hecho;
	}

	public String getCuantia() {
		return cuantia;
	}

	public String getPuntos() {
		return puntos;
	}

}
