package mx.com.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.service.iot.dao.OperacionSistemaDAO;
import mx.com.service.iot.vo.CambioEstadoVO;
import mx.com.service.iot.vo.ModoOperacionResponseVO;



@RestController
public class ModoSistemaController {
	
	
	 @Autowired
	private OperacionSistemaDAO operacionSistemaDAO;
	 
	
	@RequestMapping(value = "/modoSistema/ajusteModoOperacion", method = RequestMethod.POST,
            produces = "application/json")
    public ModoOperacionResponseVO cambioModoOperacion(@RequestParam(value="modoOperacion") int modoOperacion) throws SQLException {
		
		System.out.println("Entra en cambio modo Operacion");
		
		ModoOperacionResponseVO response = new ModoOperacionResponseVO();
		
		boolean cambio = false;
		
		try{
		
			if (modoOperacion == 0 || modoOperacion == 1){
				 cambio = operacionSistemaDAO.updateOperacionSistema(modoOperacion);
				 if(cambio){
					 response.setCodigo(0);
					 response.setDescripcionCodigo("Cambio de operacion exitoso");
				 }
				 else{
					 response.setCodigo(1);
					 response.setDescripcionCodigo("Error en cambio de operacion");
				 }
				
			}
			else {
				 response.setCodigo(1);
				 response.setDescripcionCodigo("Error. Opcion no soportada.");
			}
		}catch(Exception e){
			 response.setCodigo(2);
			 response.setDescripcionCodigo("Error de comunicacion");
		}

    
        return response;
    }
	
	
	@RequestMapping(value = "/modoSistema/encenderActuador", method = RequestMethod.POST,
            produces = "application/json")
    public CambioEstadoVO encenderActuador(@RequestParam(value="idActuador") int idActuador) throws SQLException {
		
		System.out.println("Entra a Encender Actuador");
		
		CambioEstadoVO response = new CambioEstadoVO();
		
		boolean cambio = false;
		
		try{
		
			 cambio = operacionSistemaDAO.updateEstadoActuador(idActuador, 1);//Estatus para encender
			 if(cambio){
				 response.setCodigo(0);
				 response.setDescripcionCodigo("Actuador encendido exitosamente");
			 }
			 else{
				 response.setCodigo(1);
				 response.setDescripcionCodigo("Error al encender Actuador");
			 }
			
			
		}catch(Exception e){
			 response.setCodigo(2);
			 response.setDescripcionCodigo("Error de comunicacion");
		}

    
        return response;
    }
	
	@RequestMapping(value = "/modoSistema/apagarActuador", method = RequestMethod.POST,
            produces = "application/json")
    public CambioEstadoVO apagarActuador(@RequestParam(value="idActuador") int idActuador) throws SQLException {
		
		System.out.println("Entra a Apagar Actuador");
		
		CambioEstadoVO response = new CambioEstadoVO();
		
		boolean cambio = false;
		
		try{
		
			 cambio = operacionSistemaDAO.updateEstadoActuador(idActuador, 0);//Estatus para apagar
			 if(cambio){
				 response.setCodigo(0);
				 response.setDescripcionCodigo("Actuador apagado exitosamente");
			 }
			 else{
				 response.setCodigo(1);
				 response.setDescripcionCodigo("Error al apagar Actuador");
			 }
			
			
		}catch(Exception e){
			 response.setCodigo(2);
			 response.setDescripcionCodigo("Error de comunicacion");
		}

    
        return response;
    }


	public OperacionSistemaDAO getOperacionSistemaDAO() {
		return operacionSistemaDAO;
	}


	public void setOperacionSistemaDAO(OperacionSistemaDAO operacionSistemaDAO) {
		this.operacionSistemaDAO = operacionSistemaDAO;
	}
	
}