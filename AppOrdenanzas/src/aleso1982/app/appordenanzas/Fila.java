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

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getHecho() {
		return hecho;
	}

	public void setHecho(String hecho) {
		this.hecho = hecho;
	}

	public String getCuantia() {
		return cuantia;
	}

	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	
}
