package DAO;

import Model.Diretor;
import Model.Filme;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {
    private final static String BUSCA = "SELECT u.id, u.login, u.senha, u.voto, u.nome FROM usuario u "
            + "WHERE u.login = ? AND u.senha = ?";
    
    public Usuario autenticar(String login, String senha){
        System.out.println("senha = " + senha);
        System.out.println("login = " + login);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = null;
        try{
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement(BUSCA); 
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if(rs.next()){
                u = new Usuario();
                System.out.println("DAO: Id: " + rs.getString(1) + " - Login: " + rs.getString(2) + " - Senha: " + rs.getString(3));
                u.setCod(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setVotou(rs.getBoolean(4));
                u.setNome(rs.getString(5));
            }
            return u;
        }catch(SQLException e){
            System.out.println("Error SQL "+e.getMessage());
            return null; //erro
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
