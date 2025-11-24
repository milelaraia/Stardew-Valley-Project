package DAO;

import Model.Inventario;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventarioDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertInventario(Inventario inventario) {
        connectToDB();
        String sql = "INSERT INTO Inventario (capacidade) VALUES(?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, inventario.getCapacidade());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }

        return sucesso;
    }

    // UPDATE
    public boolean updateInventario(int novaCapacidade, int idInventario) {
        connectToDB();
        String sql = "UPDATE Inventario SET capacidade=? WHERE idInventario=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, novaCapacidade);
            pst.setInt(2, idInventario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteInventario(int idInventario) {
        connectToDB();
        boolean sucesso = false;

        try {
            con.setAutoCommit(false);

            String sql = "DELETE FROM Inventario WHERE idInventario = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idInventario);
            pst.executeUpdate();
            pst.close();

            con.commit();
            sucesso = true;

        } catch (SQLException ex) {
            try {
                con.rollback();
                System.out.println("Erro = " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Erro no rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                con.setAutoCommit(true);
                if (pst != null) pst.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }

        return sucesso;
    }

    // SELECT
    public ArrayList<Inventario> selectInventario() {
        connectToDB();
        ArrayList<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM Inventario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Capacidades de inventário: ");

            while (rs.next()) {
                Inventario inventarioAux = new Inventario(
                        rs.getInt("idInventario"),
                        rs.getInt("capacidade")
                );

                System.out.println("IdIventario: " + inventarioAux.getIdInventario());
                System.out.println("Capacidade: " + inventarioAux.getCapacidade() + "\n");

                inventarios.add(inventarioAux);
            }

            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return inventarios;
    }
}
