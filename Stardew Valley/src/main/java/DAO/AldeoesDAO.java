package DAO;

import Model.Aldeoes;
import java.sql.SQLException;
import java.util.ArrayList;

public class AldeoesDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertAldeoes(Aldeoes a) {
        connectToDB();
        String sql = "INSERT INTO Aldeoes (nome, hobby, presente_que_ama) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, a.getNome());
            pst.setString(2, a.getHobby());
            pst.setString(3, a.getPresente_que_ama());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertAldeoes): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); pst.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // SELECT SIMPLES
    public ArrayList<Aldeoes> selectAldeoes() {
        connectToDB();
        ArrayList<Aldeoes> lista = new ArrayList<>();

        String sql = "SELECT * FROM Aldeoes";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Aldeoes a = new Aldeoes(
                        rs.getInt("idAldeoes"),
                        rs.getString("nome"),
                        rs.getString("hobby"),
                        rs.getString("presente_que_ama")
                );
                lista.add(a);

                System.out.println("ID: " + a.getIdAldeoes());
                System.out.println("Nome: " + a.getNome());
                System.out.println("Hobby: " + a.getHobby());
                System.out.println("Presente que ama: " + a.getPresente_que_ama());
                System.out.println("----------------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAldeoes): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); st.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return lista;
    }

    // SELECT COM JOIN (Aldeão + Amizade + Jogador)
    public void selectAldeoesComAmizades() {
        connectToDB();

        String sql =
                "SELECT a.nome AS aldeao, am.nivel, j.nome AS jogador " +
                        "FROM Aldeoes a " +
                        "JOIN Amizade am ON am.Aldeoes_idAldeoes = a.idAldeoes " +
                        "JOIN Jogador j ON j.idJogador = am.Jogador_idJogador";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Aldeões e suas Amizades ---");

            while (rs.next()) {
                System.out.println("Aldeão: " + rs.getString("aldeao"));
                System.out.println("Nível de amizade: " + rs.getString("nivel"));
                System.out.println("Jogador amigo: " + rs.getString("jogador"));
                System.out.println("----------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Aldeoes): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); st.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public boolean updateAldeoes(Aldeoes a) {
        connectToDB();

        String sql = "UPDATE Aldeoes SET nome=?, hobby=?, presente_que_ama=? WHERE idAldeoes=?";

        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, a.getNome());
            pst.setString(2, a.getHobby());
            pst.setString(3, a.getPresente_que_ama());
            pst.setInt(4, a.getIdAldeoes());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateAldeoes): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); } catch (SQLException ignored) {}
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteAldeoes(int id) {
        connectToDB();

        String sql = "DELETE FROM Aldeoes WHERE idAldeoes=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteAldeoes): " + e.getMessage());
            sucesso = false;

        } finally {
            try { con.close(); pst.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }
}
