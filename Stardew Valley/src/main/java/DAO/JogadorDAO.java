package DAO;

import Model.Jogador;
import java.sql.*;
import java.util.ArrayList;

public class JogadorDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT — com inventário automático
    public boolean insertJogador(Jogador j) {
        connectToDB();

        try {
            // 1 — Criar inventário novo com capacidade padrão
            String sqlInv = "INSERT INTO Inventario (capacidade) VALUES (20)";
            pst = con.prepareStatement(sqlInv, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            int idInv = 0;

            if (rs.next()) {
                idInv = rs.getInt(1);
            }

            // 2 — Criar o jogador usando o inventário recém-criado
            String sqlJog = "INSERT INTO Jogador " +
                    "(nome, nome_fazenda, animal_favorito, coisa_favorita, genero, energia, saude, Inventario_idInventario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pst = con.prepareStatement(sqlJog);
            pst.setString(1, j.getNome());
            pst.setString(2, j.getNome_fazenda());
            pst.setString(3, j.getAnimal_favorito());
            pst.setString(4, j.getCoisa_favorita());
            pst.setString(5, j.getGenero());
            pst.setInt(6, j.getEnergia());
            pst.setInt(7, j.getSaude());
            pst.setInt(8, idInv);

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertJogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return sucesso;
    }

    // SELECT simples
    public ArrayList<Jogador> selectJogador() {
        connectToDB();
        ArrayList<Jogador> lista = new ArrayList<>();

        String sql = "SELECT * FROM Jogador";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n------ LISTA DE JOGADORES ------");

            while (rs.next()) {
                Jogador j = new Jogador(
                        rs.getInt("idJogador"),
                        rs.getString("nome"),
                        rs.getString("nome_fazenda"),
                        rs.getString("animal_favorito"),
                        rs.getString("coisa_favorita"),
                        rs.getString("genero"),
                        rs.getInt("energia"),
                        rs.getInt("saude"),
                        rs.getInt("Inventario_idInventario")
                );

                lista.add(j);

                // IMPRIME
                System.out.println("ID: " + j.getIdJogador());
                System.out.println("Nome: " + j.getNome());
                System.out.println("Fazenda: " + j.getNome_fazenda());
                System.out.println("Animal favorito: " + j.getAnimal_favorito());
                System.out.println("Coisa favorita: " + j.getCoisa_favorita());
                System.out.println("Gênero: " + j.getGenero());
                System.out.println("Energia: " + j.getEnergia());
                System.out.println("Saúde: " + j.getSaude());
                System.out.println("Inventário: " + j.getInventario());
                System.out.println("----------------------------------------");
            }

            if (lista.isEmpty()) {
                System.out.println("Nenhum jogador encontrado!");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectJogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return lista;
    }

    // SELECT Jogadores + Inventário
    public void selectJogadoresComInventario() {
        connectToDB();

        String sql =
                "SELECT j.nome, j.nome_fazenda, i.capacidade " +
                        "FROM Jogador j " +
                        "JOIN Inventario i ON i.idInventario = j.Inventario_idInventario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Jogadores com seus Inventários ---");

            while (rs.next()) {
                System.out.println("Jogador: " + rs.getString("nome"));
                System.out.println("Fazenda: " + rs.getString("nome_fazenda"));
                System.out.println("Capacidade do Inventário: " + rs.getInt("capacidade"));
                System.out.println("-------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // UPDATE
    public boolean updateJogador(Jogador j) {
        connectToDB();

        String sql = "UPDATE Jogador SET nome=?, nome_fazenda=?, animal_favorito=?, coisa_favorita=?, genero=?, energia=?, saude=? " +
                "WHERE idJogador=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, j.getNome());
            pst.setString(2, j.getNome_fazenda());
            pst.setString(3, j.getAnimal_favorito());
            pst.setString(4, j.getCoisa_favorita());
            pst.setString(5, j.getGenero());
            pst.setInt(6, j.getEnergia());
            pst.setInt(7, j.getSaude());
            pst.setInt(8, j.getIdJogador());

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateJogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteJogador(int id) {
        connectToDB();

        String sql = "DELETE FROM Jogador WHERE idJogador=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteJogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return sucesso;
    }
}
