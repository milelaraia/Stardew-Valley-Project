package DAO;

import Model.Lavouras;

import java.sql.SQLException;
import java.util.ArrayList;

public class LavourasDAO extends ConnectionDAO {

    boolean sucesso = false;

    // INSERT
    public boolean insertLavoura(Lavouras lavoura) {
        connectToDB();
        String sql = "INSERT INTO Lavouras (flores, frutas, vegetais, graos, hortalicas, tuberculos) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, lavoura.getFlores());
            pst.setString(2, lavoura.getFrutas());
            pst.setString(3, lavoura.getVegetais());
            pst.setString(4, lavoura.getGraos());
            pst.setString(5, lavoura.getHortalicas());
            pst.setString(6, lavoura.getTuberculos());
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
    public boolean updateLavoura(Lavouras lavoura, int idLavouras) {
        connectToDB();
        String sql = "UPDATE Lavouras SET flores=?, frutas=?, vegetais=?, graos=?, hortalicas=?, tuberculos=? WHERE idLavouras=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, lavoura.getFlores());
            pst.setString(2, lavoura.getFrutas());
            pst.setString(3, lavoura.getVegetais());
            pst.setString(4, lavoura.getGraos());
            pst.setString(5, lavoura.getHortalicas());
            pst.setString(6, lavoura.getTuberculos());
            pst.setInt(7, idLavouras);
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
    public boolean deleteLavoura(int idLavouras) {
        connectToDB();
        boolean sucesso = false;

        try {
            con.setAutoCommit(false);

            String sql = "DELETE FROM Lavouras WHERE idLavouras = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idLavouras);
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
    public ArrayList<Lavouras> selectLavouras() {
        connectToDB();
        ArrayList<Lavouras> lista = new ArrayList<>();
        String sql = "SELECT * FROM Lavouras";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Lavouras Listadas: ");

            while (rs.next()) {
                Lavouras lavourasAux = new Lavouras(
                        rs.getInt("idLavouras"),
                        rs.getString("flores"),
                        rs.getString("frutas"),
                        rs.getString("vegetais"),
                        rs.getString("graos"),
                        rs.getString("hortalicas"),
                        rs.getString("tuberculos")
                );

                System.out.println("Id: " + lavourasAux.getIdLavouras());
                System.out.println("Flores: " + lavourasAux.getFlores());
                System.out.println("Frutas: " + lavourasAux.getFrutas());
                System.out.println("Vegetais: " + lavourasAux.getVegetais());
                System.out.println("Graos: " + lavourasAux.getGraos());
                System.out.println("Hortalicas: " + lavourasAux.getHortalicas());
                System.out.println("Tuberculos: " + lavourasAux.getTuberculos() + "\n");

                lista.add(lavourasAux);
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

        return lista;
    }
}

