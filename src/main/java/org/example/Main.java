package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:dbaluno", "sa", "sa");
            statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE alunos (id INTEGER not null, nome VARCHAR(255))");

            statement.executeUpdate("INSERT INTO alunos (id, nome) VALUES (1, 'Joao')");

            resultSet = statement.executeQuery("SELECT id, nome FROM alunos");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                System.out.println("O id "+ id +" e o nom são " + nome);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}