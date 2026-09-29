package jlsuportes.model;
import java.time.LocalDate;
public class Chamado {
 private int id; private Cliente cliente; private String descricao,prioridade,status,tecnico; private LocalDate data;
 public Chamado(int id,Cliente cliente,String descricao,String prioridade,String status,String tecnico,LocalDate data){
  this.id=id;this.cliente=cliente;this.descricao=descricao;this.prioridade=prioridade;this.status=status;this.tecnico=tecnico;this.data=data;}
 public int getId(){return id;} public Cliente getCliente(){return cliente;} public String getDescricao(){return descricao;}
 public String getPrioridade(){return prioridade;} public String getStatus(){return status;} public String getTecnico(){return tecnico;} public LocalDate getData(){return data;}
}