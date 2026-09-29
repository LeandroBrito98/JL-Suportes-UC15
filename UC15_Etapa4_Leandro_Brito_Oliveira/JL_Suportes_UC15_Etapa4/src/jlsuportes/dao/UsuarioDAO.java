package jlsuportes.dao;
import jlsuportes.db.Conexao; import java.sql.*;
public class UsuarioDAO {
 public boolean autenticar(String login,String senha)throws SQLException{
  try(Connection c=Conexao.abrir();PreparedStatement p=c.prepareStatement("SELECT id FROM usuarios WHERE login=? AND senha=?")){
   p.setString(1,login);p.setString(2,senha);try(ResultSet r=p.executeQuery()){return r.next();}
  }
 }
 public void inserir(String nome,String login,String senha,String tipo)throws SQLException{
  try(Connection c=Conexao.abrir();PreparedStatement p=c.prepareStatement("INSERT INTO usuarios(nome,login,senha,tipo) VALUES(?,?,?,?)")){
   p.setString(1,nome);p.setString(2,login);p.setString(3,senha);p.setString(4,tipo);p.executeUpdate();
  }
 }
}