package mx.com.service.iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.service.iot.vo.ReglasSensorVO;
import mx.com.service.iot.vo.SensorVO;

@Repository
public class SensorDAO {
	
	//static Logger logger = Logger.getLogger(ConexionesDAO.class.getName());
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<SensorVO> select() throws SQLException{
		
		List<SensorVO> lstSensores = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs= null;
		
		try{
			 
			lstSensores = new ArrayList<SensorVO>();
			 con = dataSource.getConnection();
			 st = con.prepareStatement("select ID_SENSOR, NOMBRE_SENSOR, LECTURA_ACTUAL, UNIDAD_MEDIDA from IOT_SENSOR");
			
			rs = st.executeQuery();	

			
			 
			 while (rs.next()){
				 
				 SensorVO sensor = new SensorVO(); 
				 
				 sensor.setIdSensor(rs.getInt("ID_SENSOR"));
				 sensor.setNombreSensor(rs.getString("NOMBRE_SENSOR"));
				 sensor.setLecturaActual(rs.getInt("LECTURA_ACTUAL"));
				 sensor.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
				 
				 lstSensores.add(sensor);
			 }
		
			
			
		     System.out.print("Sensores  size : " + lstSensores.size());

			
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
		
		return lstSensores;

	}
	
	public boolean update(int idSensor, int lecturaActual) throws SQLException{
		
		Connection con = null;
		Statement st = null;
		boolean response = false;
		
		try{
			 
			 con = dataSource.getConnection();
			 st = con.createStatement();
			
			int result = st.executeUpdate("update IOT_SENSOR set LECTURA_ACTUAL="+lecturaActual+" where ID_SENSOR="+idSensor);	


			 if(result == 1 )
				 response = true;
			 else response = false; 
		
		     System.out.print("Result update ultima lectura Sensor "+idSensor+" : " + response);

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
	
	public ReglasSensorVO selectReglaSensor(int idSensor) throws SQLException{
		
		ReglasSensorVO reglaSensor = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs= null;
		
		try{
			 
			 reglaSensor = new ReglasSensorVO();
			 con = dataSource.getConnection();
			 st = con.prepareStatement("select ID_REGLA, NOMBRE_REGLA, ID_SENSOR, ID_ACTUADOR, VALOR_MIN, VALOR_MAX from IOT_REGLAS_ACTUADOR where id_sensor=?");
			 
			 st.setInt(1, idSensor);
			
			 rs = st.executeQuery();	

			 if (rs.next()){
				 
				 reglaSensor.setIdRegla(rs.getInt("ID_REGLA"));
				 reglaSensor.setNombreReglas("NOMBRE_REGLA");
				 reglaSensor.setIdSensor(rs.getInt("ID_SENSOR"));
				 reglaSensor.setValorMinimo(rs.getInt("VALOR_MIN"));
				 reglaSensor.setValorMaximo(rs.getInt("VALOR_MAX"));
				 reglaSensor.setIdActuador(rs.getInt("ID_ACTUADOR"));
				 
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
		
		return reglaSensor;

	}
	
	
}

