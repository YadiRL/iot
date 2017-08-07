package mx.com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.service.iot.dao.UsuarioDAO;
import mx.com.service.iot.vo.LoginResponseVO;
import mx.com.service.iot.vo.LogoutResponseVO;
import mx.com.service.iot.vo.UsuarioVO;



@RestController
public class AutenticacionController {
	
	
	 @Autowired
	private UsuarioDAO usuarioDAO;
	 
	
	@RequestMapping(value = "/autenticacion/login", method = RequestMethod.POST,
            produces = "application/json")
    public LoginResponseVO login(@RequestParam(value="usuario") String usuario,
    							 @RequestParam(value="password") String password) throws SQLException {
		
		System.out.println("Entra en Login");
		
		UsuarioVO usuarioVO = new UsuarioVO();
		LoginResponseVO loginResponse = new LoginResponseVO();
		
		usuarioVO.setUsuario(usuario);
		usuarioVO.setPassword(password);
		
		try{
		
		List<String> privilegios = usuarioDAO.getUserInfo(usuarioVO);
		
		if (privilegios.size() == 0 ){
			loginResponse.setCodigo(1); // Autenticacion fallida
			loginResponse.setDescripcionCodigo("Error Autenticacion");
		}
		else{
			loginResponse.setCodigo(0); // Autenticacion exitosa
			loginResponse.setDescripcionCodigo("Login Exitoso");
			loginResponse.setPermisoUsuario(privilegios);
		}
		}catch (Exception e){
			loginResponse.setCodigo(2); // Autenticacion fallida
			loginResponse.setDescripcionCodigo("Error Comunicacion");
		}
	
        return loginResponse;
    }
	

	@RequestMapping(value = "/autenticacion/logout", method = RequestMethod.POST,
            produces = "application/json")
    public LogoutResponseVO logout(@RequestParam(value="usuario") String usuario) throws SQLException {
		
		System.out.println("Entra en Logout");
		
		LogoutResponseVO logoutResponse = new LogoutResponseVO();
		logoutResponse.setCodigo(0);
		logoutResponse.setDescripcionCodigo("Logout Exitoso");
	
        return logoutResponse;
    }


	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	
	
}