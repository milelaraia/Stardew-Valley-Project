package DAO;

import Model.Itens;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItensDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertItens(Itens i) {
        connectToDB();

        String sql = "INSERT INTO Itens (ferramentas, armas, aneis, Inventario_idInventario) " +
                "VALUES (?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, i.getFerramentas());
            pst.setString(2, i.getArmas());
            pst.setString(3, i.getAneis());
            pst.setInt(4, i.getInventario());

            pst.execute();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (insertItens): " + e.getMessage());
            sucesso = false;
        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // SELECT SIMPLES
    public ArrayList<Itens> selectItens() {
        connectToDB();
        ArrayList<Itens> lista = new ArrayList<>();

        String sql = "SELECT * FROM Itens";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n------ LISTA DE ITENS ------");

            while (rs.next()) {
                Itens i = new Itens(
                        rs.getInt("idItens"),
                        rs.getString("ferramentas"),
                        rs.getString("armas"),
                        rs.getString("aneis"),
                        rs.getInt("Inventario_idInventario")
                );
                lista.add(i);


                System.out.println("ID: " + i.getIdItens());
                System.out.println("Ferramentas: " + i.getFerramentas());
                System.out.println("Armas: " + i.getArmas());
                System.out.println("Anéis: " + i.getAneis());
                System.out.println("Inventário: " + i.getInventario());
                System.out.println("------------------------------");
            }

            if (lista.isEmpty()) {
                System.out.println("Nenhum item encontrado!");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectItens): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return lista;
    }


    // SELECT COM JOIN SIMPLES
    public void selectItensComInventario() {
        connectToDB();

        String sql =
                "SELECT i.*, inv.capacidade AS cap " +
                        "FROM Itens i " +
                        "JOIN Inventario inv ON inv.idInventario = i.Inventario_idInventario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n--- Itens + Inventário ---");

            while (rs.next()) {
                System.out.println("Ferramenta: " + rs.getString("ferramentas"));
                System.out.println("Arma: " + rs.getString("armas"));
                System.out.println("Anel: " + rs.getString("aneis"));
                System.out.println("Inventário capacidade: " + rs.getInt("cap"));
                System.out.println("-----------------------------------");
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (JOIN Itens+Inventario): " + e.getMessage());
            sucesso = false;

        } finally {
            try { st.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    // UPDATE

    public boolean updateItens(Itens i) {
        connectToDB();

        String sql = "UPDATE Itens SET ferramentas=?, armas=?, aneis=?" +
                "WHERE idItens=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, i.getFerramentas());
            pst.setString(2, i.getArmas());
            pst.setString(3, i.getAneis());
            pst.setInt(4, i.getIdItens());

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (updateItens): " + e.getMessage());
            sucesso = false;
        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }

    // DELETE
    public boolean deleteItens(int id) {
        connectToDB();

        String sql = "DELETE FROM Itens WHERE idItens=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (deleteItens): " + e.getMessage());
            sucesso = false;
        } finally {
            try { pst.close(); con.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar conexão: " + e.getMessage()); }
        }

        return sucesso;
    }
}