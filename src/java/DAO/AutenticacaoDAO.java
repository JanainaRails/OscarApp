package DAO;

import Model.Diretor;
import Model.Filme;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {
    private final static String BUSCA = "SELECT u.id, u.login, u.senha, u.voto, u.nome, v.diretor, v.filme FROM usuario u, votos v "
            + "WHERE u.id = v.id_usuario AND u.login = ? AND u.senha = ?";
    
    public Usuario autenticar(String login, String senha){
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
                Diretor d = new Diretor();
                Filme f = new Filme();
                System.out.println("DAO: Id: " + rs.getString(1) + " - Login: " + rs.getString(2) + " - Senha: " + rs.getString(3));
                u.setCod(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setVotou(rs.getBoolean(4));
                u.setNome(rs.getString(5));
                d.setNome(rs.getString(6));
                f.setNome(rs.getString(7));
                u.setDiretor(d);
                u.setFilme(f);
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
