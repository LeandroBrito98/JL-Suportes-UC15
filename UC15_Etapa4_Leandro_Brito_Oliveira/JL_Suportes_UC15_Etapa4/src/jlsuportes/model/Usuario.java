package jlsuportes.model;
public class Usuario {
 private int id; private String nome,login,senha,tipo;
 public Usuario(int id,String nome,String login,String senha,String tipo){this.id=id;this.nome=nome;this.login=login;this.senha=senha;this.tipo=tipo;}
 public int getId(){return id;} public String getNome(){return nome;} public String getLogin(){return login;} public String getTipo(){return tipo;}
}