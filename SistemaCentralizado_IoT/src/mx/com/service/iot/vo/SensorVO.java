package mx.com.service.iot.vo;

public class SensorVO {
	
	private int idSensor;
	private String nombreSensor;
	private int lecturaActual;
	private String unidadMedida;

	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public String getNombreSensor() {
		return nombreSensor;
	}
	public void setNombreSensor(String nombreSensor) {
		this.nombreSensor = nombreSensor;
	}
	public int getLecturaActual() {
		return lecturaActual;
	}
	public void setLecturaActual(int lecturaActual) {
		this.lecturaActual = lecturaActual;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	
	

}
