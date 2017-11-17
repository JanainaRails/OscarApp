package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {
    private final static String BUSCA = "SELECT login, senha, voto FROM usuario WHERE login = ? AND senha = ?";
    
    public int autenticar(String login, String senha){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement(BUSCA); 
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println("DAO: Login: " + rs.getString(1) + " - Senha: " + rs.getString(2) + " - Voto: " + rs.getString(3));
                int voto = rs.getInt(3); //se tem o login e senha então retorna se votou ou não 1-sim e 2-não
                return voto;
            }
            return 2; //não tem acesso
        }catch(SQLException e){
            System.out.println("Error SQL "+e.getMessage());
            return 3; //erro
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
