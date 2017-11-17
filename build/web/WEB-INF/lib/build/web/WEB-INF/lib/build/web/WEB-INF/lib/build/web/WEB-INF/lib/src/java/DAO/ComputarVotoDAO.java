package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComputarVotoDAO {
    private final static String INSERE = "INSERT INTO votos(diretor, filme, id_usuario) VALUES (?, ?, ?)";

    public boolean computarVoto(int diretor, int filme, int usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement(INSERE);
            stmt.setInt(1, diretor);
            stmt.setInt(2, filme);
            stmt.setInt(3, usuario);
            stmt.executeUpdate();
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
