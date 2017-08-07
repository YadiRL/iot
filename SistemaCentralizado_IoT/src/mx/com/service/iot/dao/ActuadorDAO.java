package mx.com.service.iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.service.iot.vo.ActuadorVO;

@Repository
public class ActuadorDAO {
	
	//static Logger logger = Logger.getLogger(ConexionesDAO.class.getName());
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<ActuadorVO> select() throws SQLException{
		
		List<ActuadorVO> lstActuadores = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs= null;
		
		try{
			 
			lstActuadores = new ArrayList<ActuadorVO>();
			 con = dataSource.getConnection();
			 st = con.prepareStatement("select ID_ACTUADOR, NOMBRE_ACTUADOR, ESTADO from IOT_ACTUADOR");
			
			rs = st.executeQuery();	

			
			 
			 while (rs.next()){
				 
				 ActuadorVO actuador = new ActuadorVO(); 
				 
				 actuador.setIdActuador(rs.getInt("ID_ACTUADOR"));
				 actuador.setNombreActuador(rs.getString("NOMBRE_ACTUADOR"));
				 actuador.setEstado(rs.getInt("ESTADO"));
				 
				 lstActuadores.add(actuador);
			 }
		
			
			
		     System.out.print("Actuadores  size : " + lstActuadores.size());

			
		}
		catch(Exception ex){
			System.out.println("Error "+ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			rs.close();
			st.close();
			con.close();
			
		}
		
		return lstActuadores;

	}
	
	
	
		
}