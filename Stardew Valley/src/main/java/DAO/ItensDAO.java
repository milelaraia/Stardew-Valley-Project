package DAO;

import Model.Itens;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItensDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertItens(Itens itens) {
        connectToDB();
        String sql = "INSERT INTO Itens (ferramentas, armas, aneis, Inventario_idInventario) VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, itens.getFerramentas());
            pst.setString(2, itens.getArmas());
            pst.setString(3, itens.getAneis());
            pst.setInt(4, itens.getInventario());
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
    public boolean updateItens(String novaFerramenta, String novaArma, String novoAnel, int novoInventario, int idItens) {
        connectToDB();
        String sql = "UPDATE Itens SET ferramentas=?, armas=?, aneis=?, Inventario_idInventario=? WHERE idItens=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novaFerramenta);
            pst.setString(2, novaArma);
            pst.setString(3, novoAnel);
            pst.setInt(4, novoInventario);
            pst.setInt(5, idItens);
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
    public boolean deleteItens(int id) {
        connectToDB();
        boolean sucesso = false;

        try {
            con.setAutoCommit(false);

            String sqlItens = "DELETE FROM Itens WHERE idItens = ?";
            pst = con.prepareStatement(sqlItens);
            pst.setInt(1, id);
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
    public ArrayList<Itens> selectItens() {
        connectToDB();
        ArrayList<Itens> itens = new ArrayList<>();
        String sql = "SELECT * FROM Itens";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n-----> Itens Listados: ");

            while (rs.next()) {
                Itens itensAux = new Itens(
                        rs.getInt("idItens"),
                        rs.getString("ferramentas"),
                        rs.getString("armas"),
                        rs.getString("aneis"),
                        rs.getInt("Inventario_idInventario")
                );

                System.out.println("ID: " + itensAux.getIdItens());
                System.out.println("Ferramentas: " + itensAux.getFerramentas());
                System.out.println("Armas: " + itensAux.getArmas());
                System.out.println("Aneis: " + itensAux.getAneis());
                System.out.println("Inventário FK: " + itensAux.getInventario());
                System.out.println();

                itens.add(itensAux);
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

        return itens;
    }
}
