package jlsuportes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/jl_suportes"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=America/Sao_Paulo";

    private static final String USUARIO = "root";
    private static final String SENHA = ""; // Informe a senha local do MySQL, se houver

    public static Connection abrir() throws SQLException {

        try {
            // Carrega explicitamente o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Driver MySQL Connector/J não encontrado no projeto.", e
            );
        }

        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}