package mx.com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.service.iot.dao.ActuadorDAO;
import mx.com.service.iot.dao.SensorDAO;
import mx.com.service.iot.vo.ActuadorVO;
import mx.com.service.iot.vo.SensorVO;


@RestController
public class EstadoSistemaController {
	
	
	 @Autowired
	private SensorDAO sensorDAO;
	 
	 @Autowired
		private ActuadorDAO actuadorDAO;
	
	
	@RequestMapping(value = "/estadoSistema/lecturaSensores", method = RequestMethod.GET,
            produces = "application/json")
    public List<SensorVO> lecturaSensores() throws SQLException {
		
		System.out.println("Entra en lectura sensores");
		
		List<SensorVO> listaSensores = sensorDAO.select();
    	
    
        return listaSensores;
    }
	
	@RequestMapping(value = "/estadoSistema/actuadores", method = RequestMethod.GET,
            produces = "application/json")
    public List<ActuadorVO> estadoActuadores() throws SQLException {
		
		System.out.println("Entra en leer estado de actuadores");
		
		List<ActuadorVO> listaActuadores = actuadorDAO.select();
    	
    
        return listaActuadores;
    }



	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}


	public void setSensorDAO(SensorDAO sensorDAO) {
		this.sensorDAO = sensorDAO;
	}


	public ActuadorDAO getActuadorDAO() {
		return actuadorDAO;
	}


	public void setActuadorDAO(ActuadorDAO actuadorDAO) {
		this.actuadorDAO = actuadorDAO;
	}
	
	

}
