package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComputarVotoDAO {
    private final static String INSERE = "INSERT INTO votos(diretor, filme, id_usuario) VALUES (?, ?, ?)";
    private final static String VOTOU = "UPDATE usuario SET voto = 1 WHERE id_usuario = ?";

    public boolean computarVoto(int diretor, int filme, int usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = new ConnectionFactory().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(INSERE);
            stmt.setInt(1, diretor);
            stmt.setInt(2, filme);
            stmt.setInt(3, usuario);
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(VOTOU);
            stmt.setInt(1, usuario);
            stmt.executeUpdate();
            conn.commit();
            return true;
        }catch(SQLException e){
            System.out.println("Erro SQL: " + e);
            return false;
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(Exception e){
                System.out.println("Erro: "+e.getMessage());
            }
        }
    }
}
