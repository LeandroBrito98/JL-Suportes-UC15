package jlsuportes.dao;
import jlsuportes.db.Conexao; import jlsuportes.model.Cliente; import java.sql.*; import java.util.*;
public class ClienteDAO {
 public List<Cliente> listar() throws SQLException {
  List<Cliente> l=new ArrayList<>(); String sql="SELECT id,nome,email,telefone FROM clientes ORDER BY id";
  try(Connection c=Conexao.abrir(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()){
   while(r.next()) l.add(new Cliente(r.getInt("id"),r.getString("nome"),r.getString("email"),r.getString("telefone")));
  } return l;
 }
 public void inserir(String nome,String email,String telefone)throws SQLException{
  try(Connection c=Conexao.abrir();PreparedStatement p=c.prepareStatement("INSERT INTO clientes(nome,email,telefone) VALUES(?,?,?)")){
   p.setString(1,nome);p.setString(2,email);p.setString(3,telefone);p.executeUpdate();
  }
 }
}