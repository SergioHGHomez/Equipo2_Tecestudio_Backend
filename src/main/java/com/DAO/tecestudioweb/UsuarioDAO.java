package com.DAO.tecestudioweb;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.DAO.tecestudioweb.*;
import com.DTO.tecestudioweb.*;

import java.sql.*;

public class UsuarioDAO {
	
Conexion conn = new Conexion();
	
	public List<UsuarioVO> traerTodos(){
		String query = "select * from usuario";
		List<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
		Conexion conn = new Conexion();
		UsuarioVO usuarioTemp;
		
		try {
			 PreparedStatement consulta =
			conn.getConnection().prepareStatement(query);
			 ResultSet result = consulta.executeQuery();
			
			 if(result == null){
				 
				 return null;
			 }
			 else {
				 
				 while(result.next()){
					 usuarioTemp = new UsuarioVO();
					 usuarioTemp.setDoc_usuario(result.getString("doc_usuario"));
					 usuarioTemp.setNom_usuario(result.getString("nom_usuario"));
					 usuarioTemp.setEmail_usuario(result.getString("email_usuario"));
					 usuarioTemp.setUsuario(result.getString("usuario"));
					 usuarioTemp.setPassword(result.getString("password"));
					 
					 listaUsuarios.add(usuarioTemp);
				 }
				 conn.desconectar();
			 }
			
		} catch (Exception e) {
			try {
				JOptionPane.showMessageDialog(null, "no se pudo proceder ");
				
				conn.desconectar();
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "no se pudo desconectar la db");
			}
		}
		
		return listaUsuarios;
	}

	public boolean eliminar(String cedula){
		String query = "delete from usuario where DOC_USUARIO = ?";
		Conexion conn = new Conexion();
		
		
		try {
			PreparedStatement pstmt =
			conn.getConnection().prepareStatement(query);
			pstmt.setString(1, cedula);
			pstmt.executeQuery();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no fue posible eliminar al usuario");
			return false;
		}
	}
}