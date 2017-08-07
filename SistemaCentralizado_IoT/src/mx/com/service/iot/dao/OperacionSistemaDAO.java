package mx.com.service.iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class OperacionSistemaDAO {
	
	//static Logger logger = Logger.getLogger(ConexionesDAO.class.getName());
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@SuppressWarnings("unchecked")
	
	public boolean updateOperacionSistema(int modoOperacion) throws SQLException{
		
		Connection con = null;
		Statement st = null;
		boolean response = false;
		
		try{
			 
			 con = dataSource.getConnection();
			 st = con.createStatement();
			
			int result = st.executeUpdate("UPDATE IOT_PARAMETROS SET valor="+modoOperacion+" WHERE NOMBRE_PARAMETRO = 'MODO_OPERACION'");	

			
			 
			 if(result == 1 )
				 response = true;
			 else response = false; 
		
			
			
		     System.out.print("Result update Modo Operacion : " + response);

			
		}
		catch(Exception ex){
			System.out.println("Error "+ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			st.close();
			con.close();
			
		}
		
		return response;

	}
	
	public boolean updateEstadoActuador(int idActuador, int estado) throws SQLException{
		
		Connection con = null;
		Statement st = null;
		boolean response = false;
		
		try{
			 
			 con = dataSource.getConnection();
			 st = con.createStatement();
			
			int result = st.executeUpdate("update IOT_ACTUADOR set ESTADO="+estado+" where ID_ACTUADOR="+idActuador);	


			 if(result == 1 )
				 response = true;
			 else response = false; 
		
		     System.out.print("Result update Modo Estado Actuador : " + response);

		}
		catch(Exception ex){
			System.out.println("Error "+ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			st.close();
			con.close();
			
		}
		
		return response;

	}

			
	@SuppressWarnings("unchecked")
	
	public int selectModoOperacion() throws SQLException{
		
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs= null;
		int modoOperacion = 1;
		
		try{
			 

			 con = dataSource.getConnection();
			 st = con.prepareStatement("select VALOR from IOT_PARAMETROS WHERE NOMBRE_PARAMETRO = 'MODO_OPERACION'");
			
			
			 rs = st.executeQuery();	

			 if (rs.next()){
				 
				 modoOperacion = rs.getInt("VALOR");
				 
			 }

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
		
		return modoOperacion;

	}
	
	
}
	


