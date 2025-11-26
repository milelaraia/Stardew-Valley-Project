package DAO;

import Model.Animais;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimaisDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertAnimais(Animais a) {
        connectToDB();
        String sql = "INSERT INTO Animais (tipo, nome) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, a.getTipo());
            pst.setString(2, a.getNome());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertAnimais): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); pst.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // SELECT SIMPLES
    public ArrayList<Animais> selectAnimais() {
        connectToDB();
        ArrayList<Animais> lista = new ArrayList<>();

        String sql = "SELECT * FROM Animais";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Animais a = new Animais(
                        rs.getInt("idAnimais"),
                        rs.getString("tipo"),
                        rs.getString("nome")
                );
                lista.add(a);

                // <<< IMPRIME AQUI >>>
                System.out.println("ID: " + a.getIdAnimais());
                System.out.println("Espécie: " + a.getTipo());
                System.out.println("Nome: " + a.getNome());
                System.out.println("------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAnimais): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); st.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return lista;
    }

    // SELECT COM JOIN (Animais + Amizade + Jogador)
    public void selectAnimaisComAmizades() {
        connectToDB();

        String sql =
                "SELECT an.nome AS animal, an.tipo, am.nivel, j.nome AS jogador " +
                        "FROM Animais an " +
                        "JOIN Amizade am ON am.Animais_idAnimais = an.idAnimais " +
                        "JOIN Jogador j ON j.idJogador = am.Jogador_idJogador";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Animais e suas Amizades ---");

            while (rs.next()) {
                System.out.println("Animal: " + rs.getString("animal"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("Amigo: " + rs.getString("jogador"));
                System.out.println("Nível da Amizade: " + rs.getString("nivel"));
                System.out.println("---------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Animais): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); st.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // UPDATE
    public boolean updateAnimais(Animais a) {
        connectToDB();
        String sql = "UPDATE Animais SET tipo=?, nome=? WHERE idAnimais=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, a.getTipo());
            pst.setString(2, a.getNome());
            pst.setInt(3, a.getIdAnimais());

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateAnimais): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); } catch (SQLException ignored) {}
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteAnimais(int id) {
        connectToDB();

        String sql = "DELETE FROM Animais WHERE idAnimais=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteAnimais): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); pst.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }
}