package mx.com.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.service.iot.dao.OperacionSistemaDAO;
import mx.com.service.iot.dao.SensorDAO;
import mx.com.service.iot.vo.LecturaSensorResponseVO;
import mx.com.service.iot.vo.ReglasSensorVO;



@RestController
public class LecturaSensoresController {
	
	
	 @Autowired
	private SensorDAO sensorDAO;
	 
	 @Autowired
	private OperacionSistemaDAO operacionSistemaDAO;
	 
	
	@RequestMapping(value = "/lecturaSensores/reciveLectura", method = RequestMethod.POST,
            produces = "application/json")
    public LecturaSensorResponseVO cambioModoOperacion(@RequestParam(value="idSensor") int idSensor,
    												   @RequestParam(value="lecturaSensor") int lecturaSensor) throws SQLException {
		
		System.out.println("Recibiendo lectura de Sensor :"+idSensor);
		
		LecturaSensorResponseVO response = new LecturaSensorResponseVO();
		ReglasSensorVO reglasSensor = null;
		
		boolean cambio = false;
		int modoOperacion = 1;
		
		try{
		
			// Se actualiza la ultima lectura del Sensor
			
			cambio = sensorDAO.update(idSensor, lecturaSensor);
			
			 if(cambio){
				 
				 reglasSensor = sensorDAO.selectReglaSensor(idSensor);
				 modoOperacion = operacionSistemaDAO.selectModoOperacion();
				 if(modoOperacion == 1 && lecturaSensor > reglasSensor.getValorMaximo()){ // Modo oeracion Automatico y Cumple con alguna regla de encendido
					 operacionSistemaDAO.updateEstadoActuador(reglasSensor.getIdActuador(), 1); // Encender Actuador
				 }
				 else if (modoOperacion == 1){
					 operacionSistemaDAO.updateEstadoActuador(reglasSensor.getIdActuador(), 0); // Apagar Actuador
				 }
				 
				 response.setCodigo(0);
				 response.setDescripcionCodigo("Actualizacion de lectura exitosa");
			 }
			 else{
				 
				 response.setCodigo(1);
				 response.setDescripcionCodigo("Error en actulizacion de lectura");
			 }
				
			
		}catch(Exception e){
			 response.setCodigo(2);
			 response.setDescripcionCodigo("Error de comunicacion");
		}

    
        return response;
    }


	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}


	public void setSensorDAO(SensorDAO sensorDAO) {
		this.sensorDAO = sensorDAO;
	}


	public OperacionSistemaDAO getOperacionSistemaDAO() {
		return operacionSistemaDAO;
	}


	public void setOperacionSistemaDAO(OperacionSistemaDAO operacionSistemaDAO) {
		this.operacionSistemaDAO = operacionSistemaDAO;
	}
	
	
	
	
}