package DAO;

import Model.Aldeoes;
import Model.Animais;
import Model.Amizade;
import Model.Jogador;

import java.sql.SQLException;
import java.util.ArrayList;

public class AmizadeDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertAmizade(Amizade amizade) {
        connectToDB();
        String sql = "INSERT INTO Amizade (niveis, Jogador_idJogador, Aldeoes_idAldeoes, Animais_idAnimais) VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, amizade.getNiveis());
            pst.setInt(2, amizade.getJogador().getIdJogador());
            pst.setInt(3, amizade.getAldeao().getIdAldeoes());
            pst.setInt(4, amizade.getAnimal().getIdAnimais());
            pst.execute();

            sucesso = true;

        } catch (SQLException exc) {
            System.out.println("Erro (insertAmizade): " + exc.getMessage());
            sucesso = false;

        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sucesso;
    }


    // UPDATE
    public boolean updateAmizade(String niveis, int idJogador, int idAldeao, int idAnimal, int idAmizade) {
        connectToDB();
        String sql = "UPDATE Amizade SET niveis=?, Jogador_idJogador=?, Aldeoes_idAldeoes=?, Animais_idAnimais=? WHERE idAmizade=?";

        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, niveis);
            pst.setInt(2, idJogador);
            pst.setInt(3, idAldeao);
            pst.setInt(4, idAnimal);
            pst.setInt(5, idAmizade);

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateAmizade): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sucesso;
    }


    // DELETE
    public boolean deleteAmizade(int id) {
        connectToDB();
        boolean sucesso = false;

        String sql = "DELETE FROM Amizade WHERE idAmizade=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteAmizade): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sucesso;
    }


    // SELECT
    public ArrayList<Amizade> selectAmizades() {
        ArrayList<Amizade> lista = new ArrayList<>();
        connectToDB();

        // JOIN triplo para carregar todos os objetos completos
        String sql =
                "SELECT * FROM Amizade " +
                        "JOIN Jogador ON Jogador.idJogador = Amizade.Jogador_idJogador " +
                        "JOIN Aldeoes ON Aldeoes.idAldeoes = Amizade.Aldeoes_idAldeoes " +
                        "JOIN Animais ON Animais.idAnimais = Amizade.Animais_idAnimais";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n-----> Amizades Listadas:");

            while (rs.next()) {

                // Jogador
                Jogador jogador = new Jogador(
                        rs.getInt("Jogador.idJogador"),
                        rs.getString("Jogador.nome"),
                        rs.getString("nome_fazenda"),
                        rs.getString("animal_favorito"),
                        rs.getString("coisa_favorita"),
                        rs.getString("genero"),
                        rs.getInt("energia"),
                        rs.getInt("saude"),
                        rs.getInt("Inventario_idInventario")
                );

                // Aldeão
                Aldeoes aldeao = new Aldeoes(
                        rs.getInt("Aldeoes.idAldeoes"),
                        rs.getString("Aldeoes.nome"),
                        rs.getString("hobby"),
                        rs.getString("presente_que_ama")
                );

                // Animal
                Animais animal = new Animais(
                        rs.getInt("Animais.idAnimais"),
                        rs.getString("tipo"),
                        rs.getString("Animais.nome")
                );

                // Amizade
                Amizade amizade = new Amizade(
                        rs.getInt("idAmizade"),
                        rs.getString("niveis"),
                        jogador,
                        aldeao,
                        animal
                );

                System.out.println("ID: " + amizade.getIdAmizade());
                System.out.println("Nível: " + amizade.getNiveis());
                System.out.println("Jogador: " + jogador.getNome());
                System.out.println("Aldeão: " + aldeao.getNome());
                System.out.println("Animal: " + animal.getNome() + "\n");

                lista.add(amizade);
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAmizades): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return lista;
    }
}

