package DAO;

import Model.VotosUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscaVotosDAO {
    private final static String BUSCAR = "SELECT diretor, filme FROM votos WHERE id_usuario = ?";
    
    public VotosUsuario buscaVotosUsuario(int usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        VotosUsuario vu= null;
        try{
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement(BUSCAR); 
            stmt.setInt(1, usuario);
            rs = stmt.executeQuery();
            if(rs.next()){
                vu = new VotosUsuario();
                vu.setDiretor(rs.getInt(1));
                vu.setFilme(rs.getInt(2));
            }
            return vu;
        } catch(SQLException e){
            System.out.println("Error SQL "+e.getMessage());
            return null;
        } finally{
            try{
                stmt.close();
                conn.close();
                rs.close();
            }catch(Exception e){
                System.out.println("Error "+e.getMessage());
            }
        }
    }
}
