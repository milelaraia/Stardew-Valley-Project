package DAO;

import Model.Habilidades;

import java.sql.SQLException;
import java.util.ArrayList;

public class HabilidadesDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertHabilidade(Habilidades h) {
        connectToDB();
        String sql = "INSERT INTO Habilidades (cultivo, mineracao, coleta, pesca, combate, Jogador_idJogador, Inventario_idInventario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, h.getCultivo());
            pst.setInt(2, h.getMineracao());
            pst.setInt(3, h.getColeta());
            pst.setInt(4, h.getPesca());
            pst.setInt(5, h.getCombate());
            pst.setInt(6, h.getIdJogador());
            pst.setInt(7, h.getIdInventario());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertHabilidade): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // SELECT SIMPLES
    public ArrayList<Habilidades> selectHabilidades() {
        connectToDB();
        ArrayList<Habilidades> lista = new ArrayList<>();
        String sql = "SELECT * FROM Habilidades";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Habilidades h = new Habilidades(
                        rs.getInt("idHabilidades"),
                        rs.getInt("cultivo"),
                        rs.getInt("mineracao"),
                        rs.getInt("coleta"),
                        rs.getInt("pesca"),
                        rs.getInt("combate"),
                        rs.getInt("Jogador_idJogador"),
                        rs.getInt("Inventario_idInventario")
                );
                lista.add(h);

                // <<< IMPRIME AQUI >>>
                System.out.println("ID: " + h.getIdHabilidades());
                System.out.println("Nível de cultivo: " + h.getCultivo());
                System.out.println("Nível de mineração: " + h.getMineracao());
                System.out.println("Nível de coleta: " + h.getColeta());
                System.out.println("Nível de pesca: " + h.getPesca());
                System.out.println("Nível de combate: " + h.getCombate());
                System.out.println("Id do jogador: " + h.getIdJogador());
                System.out.println("Id do inventário do jogador: " + h.getIdInventario());
                System.out.println("------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectHabilidades): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return lista;
    }

    // SELECT COM JOIN SIMPLES (Habilidades + Jogador (JOIN direto))
    public void selectHabilidadesComJogador() {
        connectToDB();

        String sql =
                "SELECT h.*, j.nome AS jogador " +
                        "FROM Habilidades h " +
                        "JOIN Jogador j ON j.idJogador = h.Jogador_idJogador";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Habilidades + Jogador ---");

            while (rs.next()) {
                System.out.println("Jogador: " + rs.getString("jogador"));
                System.out.println("Cultivo: " + rs.getInt("cultivo"));
                System.out.println("Mineração: " + rs.getInt("mineracao"));
                System.out.println("Coleta: " + rs.getInt("coleta"));
                System.out.println("Pesca: " + rs.getInt("pesca"));
                System.out.println("Combate: " + rs.getInt("combate"));
                System.out.println("-------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Habilidades+Jogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // UPDATE
    public boolean updateHabilidade(Habilidades h) {
        connectToDB();

        String sql =
                "UPDATE Habilidades SET cultivo=?, mineracao=?, coleta=?, pesca=?, combate=?, Jogador_idJogador=?, Inventario_idInventario=? " +
                        "WHERE idHabilidades=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, h.getCultivo());
            pst.setInt(2, h.getMineracao());
            pst.setInt(3, h.getColeta());
            pst.setInt(4, h.getPesca());
            pst.setInt(5, h.getCombate());
            pst.setInt(6, h.getIdJogador());
            pst.setInt(7, h.getIdInventario());
            pst.setInt(8, h.getIdHabilidades());

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateHabilidade): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteHabilidade(int id) {
        connectToDB();

        String sql = "DELETE FROM Habilidades WHERE idHabilidades=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteHabilidade): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }
}
