package jlsuportes.dao;
import jlsuportes.db.Conexao; import jlsuportes.model.*; import java.sql.*; import java.util.*;
public class ChamadoDAO {
 public void inserir(int cliente,String descricao,String prioridade)throws SQLException{
  String sql="INSERT INTO chamados(cliente_id,descricao,prioridade,status,tecnico_id,data_abertura) VALUES(?,?,?,'Aberto',1,CURDATE())";
  try(Connection c=Conexao.abrir();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,cliente);p.setString(2,descricao);p.setString(3,prioridade);p.executeUpdate();}
 }
 public List<Chamado> listar()throws SQLException{
  List<Chamado> l=new ArrayList<>();
  String sql="SELECT ch.id,c.id cid,c.nome,c.email,c.telefone,ch.descricao,ch.prioridade,ch.status,u.nome tecnico,ch.data_abertura FROM chamados ch JOIN clientes c ON c.id=ch.cliente_id LEFT JOIN usuarios u ON u.id=ch.tecnico_id ORDER BY ch.id";
  try(Connection co=Conexao.abrir();PreparedStatement p=co.prepareStatement(sql);ResultSet r=p.executeQuery()){
   while(r.next()){Cliente c=new Cliente(r.getInt("cid"),r.getString("nome"),r.getString("email"),r.getString("telefone"));
    l.add(new Chamado(r.getInt("id"),c,r.getString("descricao"),r.getString("prioridade"),r.getString("status"),r.getString("tecnico"),r.getDate("data_abertura").toLocalDate()));}
  } return l;
 }
}