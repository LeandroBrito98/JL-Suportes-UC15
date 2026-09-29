# J&L Suportes - UC15 Etapa 4

Projeto Java Swing com persistencia em MySQL, desenvolvido para a UC15 - Etapa 4.

## Requisitos
- JDK 17
- Apache NetBeans IDE 22 ou compativel
- MySQL Server 8.x
- MySQL Workbench (opcional, para executar o script)

## Banco de dados
1. Inicie o MySQL Server.
2. Abra `database/jl_suportes.sql` no MySQL Workbench.
3. Execute todo o script. Ele cria o banco `jl_suportes`, as tabelas e os registros iniciais.

## Credenciais do MySQL
A conexao esta em `src/jlsuportes/db/Conexao.java`.
Padrao do pacote:
- host: localhost
- porta: 3306
- banco: jl_suportes
- usuario: root
- senha: vazia

Se o MySQL do computador utilizar senha para o usuario `root`, altere somente a constante `SENHA` em `Conexao.java` para a senha local e execute Clean and Build.

## Connector/J - portabilidade
O driver esta incluido no proprio projeto em:
`lib/mysql-connector-j-26.7.0.jar`

O projeto NetBeans usa referencia relativa (`lib/...`), portanto nao depende de caminho `C:\\Users\\...` de outro computador.

## Executar no NetBeans
1. File > Open Project.
2. Selecione a pasta `JL_Suportes_UC15_Etapa4`.
3. Confirme que o JDK 17 esta selecionado.
4. Execute `Clean and Build`.
5. Execute o projeto.

## Login da aplicacao
- Usuario: admin
- Senha: 123

## Funcionalidades demonstradas
- autenticacao de usuario no MySQL;
- dashboard consultando o banco;
- cadastro e consulta de clientes;
- abertura e consulta de chamados;
- cadastro de usuarios;
- persistencia dos dados apos fechar e abrir novamente a aplicacao.

## Evidencias
A pasta `evidencias` contem os prints dos testes realizados, incluindo conexao MySQL OK, cliente persistido, consulta de chamados e persistencia apos reiniciar a aplicacao.
