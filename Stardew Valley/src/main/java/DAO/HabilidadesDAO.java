package DAO;

import Model.Habilidades;

import java.sql.SQLException;
import java.util.ArrayList;

public class HabilidadesDAO extends ConnectionDAO {

    boolean sucesso = false;

    // ------------------ INSERT ------------------
    public boolean insertHabilidade(Habilidades h) {
        connectToDB();
        String sql = "INSERT INTO Habilidades (cultivo, mineracao, coleta, pesca, combate, Inventario_idInventario, Jogador_idJogador) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, h.getCultivo());
            pst.setInt(2, h.getMineracao());
            pst.setInt(3, h.getColeta());
            pst.setInt(4, h.getPesca());
            pst.setInt(5, h.getCombate());
            pst.setInt(6, h.getIdInventario());
            pst.setInt(7, h.getIdJogador());
            pst.execute();
            sucesso = true;

        } catch (SQLException exc) {
            System.out.println("Erro (insertHabilidade): " + exc.getMessage());
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

    // ------------------ UPDATE ------------------
    public boolean updateHabilidade(int cultivo, int mineracao, int coleta, int pesca, int combate, int idInventario, int idJogador, int idHabilidade) {
        connectToDB();
        String sql = "UPDATE Habilidades SET cultivo=?, mineracao=?, coleta=?, pesca=?, combate=?, Inventario_idInventario=?, Jogador_idJogador=? WHERE idHabilidades=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, cultivo);
            pst.setInt(2, mineracao);
            pst.setInt(3, coleta);
            pst.setInt(4, pesca);
            pst.setInt(5, combate);
            pst.setInt(6, idInventario);
            pst.setInt(7, idJogador);
            pst.setInt(8, idHabilidade);

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateHabilidade): " + e.getMessage());
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

    // ------------------ DELETE ------------------
    public boolean deleteHabilidade(int id) {
        connectToDB();
        boolean sucesso = false;

        String sql = "DELETE FROM Habilidades WHERE idHabilidades = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteHabilidade): " + e.getMessage());
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

    // ------------------ SELECT ------------------
    public ArrayList<Habilidades> selectHabilidades() {
        ArrayList<Habilidades> habilidades = new ArrayList<>();
        connectToDB();

        String sql = "SELECT * FROM Habilidades";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n-----> Habilidades Listadas:");

            while (rs.next()) {

                Habilidades h = new Habilidades(
                        rs.getInt("idHabilidades"),
                        rs.getInt("cultivo"),
                        rs.getInt("mineracao"),
                        rs.getInt("coleta"),
                        rs.getInt("pesca"),
                        rs.getInt("combate"),
                        rs.getInt("Inventario_idInventario"),
                        rs.getInt("Jogador_idJogador")
                );

                System.out.println("ID: " + h.getIdHabilidades());
                System.out.println("Cultivo: " + h.getCultivo());
                System.out.println("Mineração: " + h.getMineracao());
                System.out.println("Coleta: " + h.getColeta());
                System.out.println("Pesca: " + h.getPesca());
                System.out.println("Combate: " + h.getCombate());
                System.out.println("Inventário (FK): " + h.getIdInventario());
                System.out.println("Jogador (FK): " + h.getIdJogador() + "\n");

                habilidades.add(h);
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectHabilidades): " + e.getMessage());
            sucesso = false;

        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return habilidades;
    }
}