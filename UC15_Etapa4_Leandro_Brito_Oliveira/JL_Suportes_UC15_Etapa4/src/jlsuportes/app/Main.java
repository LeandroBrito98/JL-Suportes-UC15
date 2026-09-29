package jlsuportes.app;
import jlsuportes.dao.*; import jlsuportes.model.*; import javax.swing.*; import javax.swing.border.EmptyBorder; import javax.swing.table.DefaultTableModel; import java.awt.*; import java.sql.*; import java.util.List;

public class Main {
 JFrame f; CardLayout cards=new CardLayout(); JPanel area=new JPanel(cards);
 ClienteDAO clienteDAO=new ClienteDAO(); UsuarioDAO usuarioDAO=new UsuarioDAO(); ChamadoDAO chamadoDAO=new ChamadoDAO();
 public static void main(String[] a){SwingUtilities.invokeLater(()->new Main().login());}
 JButton bt(String s){JButton b=new JButton(s);b.setBackground(new Color(25,118,210));b.setForeground(Color.WHITE);b.setPreferredSize(new Dimension(180,42));return b;}
 void login(){
  JFrame l=new JFrame("J&L Suportes - Login MySQL");l.setSize(650,420);l.setLocationRelativeTo(null);l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel p=new JPanel(new GridBagLayout());GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(8,8,8,8);g.fill=GridBagConstraints.HORIZONTAL;
  JTextField u=new JTextField("admin",18);JPasswordField s=new JPasswordField("123",18);JButton e=bt("Entrar");
  JLabel t=new JLabel("J&L Suportes - Etapa 4");t.setFont(new Font("Segoe UI",1,28));t.setForeground(new Color(13,71,161));
  g.gridx=0;g.gridy=0;g.gridwidth=2;p.add(t,g);g.gridwidth=1;g.gridy=1;p.add(new JLabel("Usuário:"),g);g.gridx=1;p.add(u,g);
  g.gridx=0;g.gridy=2;p.add(new JLabel("Senha:"),g);g.gridx=1;p.add(s,g);g.gridx=0;g.gridy=3;g.gridwidth=2;p.add(e,g);
  e.addActionListener(x->{try{if(usuarioDAO.autenticar(u.getText(),new String(s.getPassword()))){l.dispose();sistema();}else JOptionPane.showMessageDialog(l,"Usuário ou senha inválidos.");}catch(SQLException ex){erroBanco(l,ex);}});
  l.add(p);l.setVisible(true);
 }
 void sistema(){f=new JFrame("J&L Suportes - UC15 Etapa 4 - MySQL");f.setSize(1180,720);f.setLocationRelativeTo(null);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.add(menu(),BorderLayout.WEST);f.add(area);recarregar();f.setVisible(true);cards.show(area,"D");}
 JPanel menu(){JPanel p=new JPanel();p.setPreferredSize(new Dimension(210,0));p.setBackground(new Color(16,47,78));p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
  JLabel x=new JLabel("  J&L Suportes");x.setForeground(Color.WHITE);x.setFont(new Font("Segoe UI",1,20));x.setBorder(new EmptyBorder(25,5,25,5));p.add(x);
  menuBt(p,"Dashboard","D");menuBt(p,"Chamados","C");menuBt(p,"Novo chamado","N");menuBt(p,"Clientes","CL");menuBt(p,"Usuários","U");return p;}
 void menuBt(JPanel p,String s,String c){JButton b=new JButton(s);b.setMaximumSize(new Dimension(195,45));b.addActionListener(e->{recarregar();cards.show(area,c);});p.add(b);p.add(Box.createVerticalStrut(8));}
 JPanel base(String s){JPanel p=new JPanel(new BorderLayout(10,10));p.setBorder(new EmptyBorder(22,25,22,25));JLabel l=new JLabel(s);l.setFont(new Font("Segoe UI",1,27));l.setForeground(new Color(13,71,161));p.add(l,BorderLayout.NORTH);return p;}
 void recarregar(){area.removeAll();area.add(dash(),"D");area.add(chamados(),"C");area.add(novo(),"N");area.add(clientes(),"CL");area.add(usuarios(),"U");area.revalidate();area.repaint();}
 JPanel dash(){JPanel p=base("Dashboard - dados do MySQL");JTextArea a=new JTextArea();a.setEditable(false);try{a.setText("Conexão MySQL: OK\n\nClientes cadastrados: "+clienteDAO.listar().size()+"\nChamados cadastrados: "+chamadoDAO.listar().size()+"\n\nOs dados desta etapa são persistidos no banco jl_suportes.");}catch(SQLException e){a.setText("Falha ao consultar banco: "+e.getMessage());}a.setFont(new Font("Segoe UI",0,18));a.setBorder(new EmptyBorder(25,20,20,20));p.add(a);return p;}
 JPanel chamados(){JPanel p=base("Consulta de Chamados - MySQL");DefaultTableModel m=new DefaultTableModel(new String[]{"ID","Cliente","Descrição","Status","Prioridade","Técnico","Data"},0);
  try{for(Chamado c:chamadoDAO.listar())m.addRow(new Object[]{c.getId(),c.getCliente().getNome(),c.getDescricao(),c.getStatus(),c.getPrioridade(),c.getTecnico(),c.getData()});}catch(SQLException e){erroBanco(f,e);}
  JTable t=new JTable(m);t.setRowHeight(27);p.add(new JScrollPane(t));return p;}
 JPanel novo(){JPanel p=base("Abertura de Chamado - persistência MySQL");JPanel q=new JPanel(new GridBagLayout());GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(8,8,8,8);g.fill=GridBagConstraints.HORIZONTAL;g.weightx=1;
  JComboBox<Cliente> cli=new JComboBox<>();try{for(Cliente c:clienteDAO.listar())cli.addItem(c);}catch(SQLException e){erroBanco(f,e);}JTextArea d=new JTextArea(5,30);JComboBox<String> pr=new JComboBox<>(new String[]{"Baixa","Média","Alta"});
  campo(q,g,0,"Cliente *",cli);campo(q,g,1,"Descrição *",new JScrollPane(d));campo(q,g,2,"Prioridade *",pr);JButton b=bt("Abrir chamado");g.gridx=1;g.gridy=3;q.add(b,g);
  b.addActionListener(e->{try{Cliente c=(Cliente)cli.getSelectedItem();if(c==null||d.getText().trim().isEmpty()){JOptionPane.showMessageDialog(f,"Preencha os campos.");return;}chamadoDAO.inserir(c.getId(),d.getText().trim(),(String)pr.getSelectedItem());JOptionPane.showMessageDialog(f,"Chamado gravado no MySQL com sucesso.");recarregar();cards.show(area,"C");}catch(SQLException ex){erroBanco(f,ex);}});
  p.add(q);return p;}
 JPanel clientes(){JPanel p=base("Clientes - persistência MySQL");JPanel q=new JPanel(new GridBagLayout());GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(6,6,6,6);g.fill=GridBagConstraints.HORIZONTAL;g.weightx=1;JTextField n=new JTextField(),e=new JTextField(),t=new JTextField();
  campo(q,g,0,"Nome *",n);campo(q,g,1,"E-mail",e);campo(q,g,2,"Telefone",t);JButton b=bt("Salvar cliente");g.gridx=1;g.gridy=3;q.add(b,g);
  b.addActionListener(x->{try{if(n.getText().trim().isEmpty())return;clienteDAO.inserir(n.getText().trim(),e.getText().trim(),t.getText().trim());JOptionPane.showMessageDialog(f,"Cliente gravado no MySQL com sucesso.");recarregar();cards.show(area,"CL");}catch(SQLException ex){erroBanco(f,ex);}});
  JTextArea a=new JTextArea();a.setEditable(false);try{StringBuilder s=new StringBuilder("Registros armazenados no banco:\n\n");for(Cliente c:clienteDAO.listar())s.append(c.getId()).append(" - ").append(c.getNome()).append(" | ").append(c.getEmail()).append(" | ").append(c.getTelefone()).append("\n");a.setText(s.toString());}catch(SQLException ex){a.setText(ex.getMessage());}p.add(q,BorderLayout.NORTH);p.add(new JScrollPane(a));return p;}
 JPanel usuarios(){JPanel p=base("Cadastro de Usuário - MySQL");JPanel q=new JPanel(new GridBagLayout());GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(6,6,6,6);g.fill=GridBagConstraints.HORIZONTAL;g.weightx=1;JTextField n=new JTextField(),l=new JTextField();JPasswordField s=new JPasswordField();JComboBox<String> tp=new JComboBox<>(new String[]{"Administrador","Técnico","Cliente"});
  campo(q,g,0,"Nome *",n);campo(q,g,1,"Login *",l);campo(q,g,2,"Senha *",s);campo(q,g,3,"Tipo *",tp);JButton b=bt("Salvar usuário");g.gridx=1;g.gridy=4;q.add(b,g);
  b.addActionListener(x->{try{usuarioDAO.inserir(n.getText().trim(),l.getText().trim(),new String(s.getPassword()),(String)tp.getSelectedItem());JOptionPane.showMessageDialog(f,"Usuário gravado no MySQL com sucesso.");}catch(SQLException ex){erroBanco(f,ex);}});p.add(q,BorderLayout.NORTH);return p;}
 void campo(JPanel p,GridBagConstraints g,int y,String l,Component c){g.gridx=0;g.gridy=y;g.weightx=0;p.add(new JLabel(l),g);g.gridx=1;g.weightx=1;p.add(c,g);}
 void erroBanco(Component c,SQLException e){JOptionPane.showMessageDialog(c,"Erro de banco de dados:\n"+e.getMessage()+"\n\nConfira MySQL, banco jl_suportes, usuário/senha e Connector/J.","Erro",JOptionPane.ERROR_MESSAGE);}
}