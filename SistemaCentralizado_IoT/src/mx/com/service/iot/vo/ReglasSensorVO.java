package mx.com.service.iot.vo;

public class ReglasSensorVO {
	
	private int idRegla;
	private String nombreReglas;
	private int idSensor;
	private int idActuador;
	private int valorMinimo;
	private int valorMaximo;
	
	
	public int getIdRegla() {
		return idRegla;
	}
	public void setIdRegla(int idRegla) {
		this.idRegla = idRegla;
	}
	public String getNombreReglas() {
		return nombreReglas;
	}
	public void setNombreReglas(String nombreReglas) {
		this.nombreReglas = nombreReglas;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public int getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(int valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public int getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(int valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	public int getIdActuador() {
		return idActuador;
	}
	public void setIdActuador(int idActuador) {
		this.idActuador = idActuador;
	}
	
	
	
	
}
