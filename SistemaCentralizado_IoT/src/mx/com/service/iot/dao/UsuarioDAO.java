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

import mx.com.service.iot.vo.UsuarioVO;

@Repository
public class UsuarioDAO {
	
	//static Logger logger = Logger.getLogger(ConexionesDAO.class.getName());
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<String> getUserInfo(UsuarioVO usuarioVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs= null;
		List<String> listPrivilegios = new ArrayList<String>(); 
		
		try{
			 
			
			con = dataSource.getConnection();
			 st = con.prepareStatement("select p.PRIVILEGIO from IOT_USUARIO u, IOT_TIPO_USUARIO tu, IOT_PRIVILEGIOS_USUARIO p"+
										" where u.USUARIO= ? and u.PASSWORD= ?"+
										"		 and u.ID_TIPO_USUARIO = tu.ID_TIPO_USUARIO"+
										"		 and tu.ID_TIPO_USUARIO = p.ID_TIPO_USUARIO");
			 
			 
			 st.setString(1, usuarioVO.getUsuario());
			 st.setString(2, usuarioVO.getPassword());
			
			rs = st.executeQuery();	

			
			 
			 while (rs.next()){
				
				 listPrivilegios.add(rs.getString("PRIVILEGIO"));
			 }
		
			
		     System.out.print("Lista Privilegios  size : " + listPrivilegios.size());

			
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
		
		return listPrivilegios;

	}
}

