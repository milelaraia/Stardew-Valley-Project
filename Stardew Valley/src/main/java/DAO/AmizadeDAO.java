package DAO;

import Model.Amizade;
import Model.Jogador;
import Model.Aldeoes;
import Model.Animais;

import java.sql.SQLException;
import java.util.ArrayList;

public class AmizadeDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertAmizade(Amizade am) {
        connectToDB();

        String sql = "INSERT INTO Amizade (nivel, Jogador_idJogador, Aldeoes_idAldeoes, Animais_idAnimais) VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, am.getNivel());
            pst.setInt(2, am.getJogador().getIdJogador());
            pst.setInt(3, am.getAldeao().getIdAldeoes());
            pst.setInt(4, am.getAnimal().getIdAnimais());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertAmizade): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
        return sucesso;
    }

    // SELECT SIMPLES
    public ArrayList<Amizade> selectAmizades() {
        connectToDB();
        ArrayList<Amizade> lista = new ArrayList<>();

        String sql = "SELECT * FROM Amizade";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Amizade am = new Amizade(
                        rs.getInt("idAmizade"),
                        rs.getString("nivel"),
                        null,
                        null,
                        null
                );
                lista.add(am);

                System.out.println("ID: " + am.getIdAmizade());
                System.out.println("Nível: " + am.getNivel());
                System.out.println("------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAmizades): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
        return lista;
    }

    // SELECT COM JOIN (COMPLETO)
    public void selectAmizadesComDetalhes() {
        connectToDB();

        String sql =
                "SELECT a.idAmizade, a.nivel, " +
                        "j.nome AS jogador, al.nome AS aldeao, an.nome AS animal " +
                        "FROM Amizade a " +
                        "JOIN Jogador j ON j.idJogador = a.Jogador_idJogador " +
                        "JOIN Aldeoes al ON al.idAldeoes = a.Aldeoes_idAldeoes " +
                        "JOIN Animais an ON an.idAnimais = a.Animais_idAnimais";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n==== LISTA COMPLETA DE AMIZADES ====\n");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idAmizade"));
                System.out.println("Nível: " + rs.getString("nivel"));
                System.out.println("Jogador: " + rs.getString("jogador"));
                System.out.println("Aldeão: " + rs.getString("aldeao"));
                System.out.println("Animal: " + rs.getString("animal"));
                System.out.println("-------------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Amizades): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // SELECT COM JOIN (TABELA INTERMEDIÁRIA)
    // Ex: jogadores e sua contagem de amizades (view-like)
    public void selectResumoAmizadesPorJogador() {
        connectToDB();

        String sql =
                "SELECT j.nome AS jogador, COUNT(a.idAmizade) AS total_amizades " +
                        "FROM Jogador j " +
                        "LEFT JOIN Amizade a ON a.Jogador_idJogador = j.idJogador " +
                        "GROUP BY j.idJogador, j.nome";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n==== RESUMO AMIZADES POR JOGADOR ====\n");

            while (rs.next()) {
                System.out.println("Jogador: " + rs.getString("jogador"));
                System.out.println("Total de amizades: " + rs.getInt("total_amizades"));
                System.out.println("--------------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Intermediária Resumo): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // UPDATE
    public boolean updateAmizade(int id, String novoNivel) {
        connectToDB();

        String sql = "UPDATE Amizade SET nivel=? WHERE idAmizade=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNivel);
            pst.setInt(2, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateAmizade): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteAmizade(int id) {
        connectToDB();

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
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

}
