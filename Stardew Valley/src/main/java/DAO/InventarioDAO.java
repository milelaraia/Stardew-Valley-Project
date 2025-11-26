package DAO;

import Model.Inventario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class InventarioDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertInventario(Inventario inv) {
        connectToDB();

        String sql = "INSERT INTO Inventario (capacidade) VALUES (?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, inv.getCapacidade());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertInventario): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
        return sucesso;
    }

    public int criarInventarioAutomatico() {
        connectToDB();
        int idGerado = -1;

        String sql = "INSERT INTO Inventario (capacidade) VALUES (20)"; // capacidade padrão

        try {
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.execute();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1); // pega o ID do inventário recém criado
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar inventário automático: " + e.getMessage());
        } finally {
            try { pst.close(); con.close(); } catch (Exception ignored) {}
        }

        return idGerado;
    }

    // SELECT SIMPLES
    public ArrayList<Inventario> selectInventario() {
        connectToDB();
        ArrayList<Inventario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Inventario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Inventario inv = new Inventario(
                        rs.getInt("idInventario"),
                        rs.getInt("capacidade")
                );
                lista.add(inv);

                // <<< IMPRIME AQUI >>>
                System.out.println("ID: " + inv.getIdInventario());
                System.out.println("Capacidade: " + inv.getCapacidade());
                System.out.println("------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectInventario): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return lista;
    }

    // SELECT COM JOIN (Inventário + Jogador (quem usa aquele inventário))
    public void selectInventarioComJogadores() {
        connectToDB();

        String sql =
                "SELECT inv.idInventario, inv.capacidade, j.nome AS jogador " +
                        "FROM Inventario inv " +
                        "LEFT JOIN Jogador j ON j.Inventario_idInventario = inv.idInventario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Inventários e seus Jogadores ---");

            while (rs.next()) {
                System.out.println("Inventário: " + rs.getInt("idInventario"));
                System.out.println("Capacidade: " + rs.getInt("capacidade"));
                System.out.println("Jogador usando: " + rs.getString("jogador"));
                System.out.println("----------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Inventario+Jogador): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // UPDATE
    public boolean updateInventario(Inventario inv) {
        connectToDB();

        String sql = "UPDATE Inventario SET capacidade=? WHERE idInventario=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, inv.getCapacidade());
            pst.setInt(2, inv.getIdInventario());

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateInventario): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteInventario(int id) {
        connectToDB();

        String sql = "DELETE FROM Inventario WHERE idInventario=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteInventario): " + e.getMessage());
            sucesso = false;

        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }
}
