package DAO;

import Model.Animais;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimaisDAO extends ConnectionDAO {

    boolean sucesso = false;

    // ------------------ INSERT ------------------
    public boolean insertAnimal(Animais animal) {
        connectToDB();
        String sql = "INSERT INTO Animais (tipo, nome) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, animal.getTipo());
            pst.setString(2, animal.getNome());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro (insertAnimal): " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return sucesso;
    }

    // ------------------ UPDATE ------------------
    public boolean updateAnimal(String novoTipo, String novoNome, int idAnimal) {
        connectToDB();
        String sql = "UPDATE Animais SET tipo=?, nome=? WHERE idAnimais=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoTipo);
            pst.setString(2, novoNome);
            pst.setInt(3, idAnimal);
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro (updateAnimal): " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    // ------------------ DELETE ------------------
    public boolean deleteAnimal(int id) {
        connectToDB();
        boolean sucesso = false;

        String sql = "DELETE FROM Animais WHERE idAnimais = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro (deleteAnimal): " + e.getMessage());
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
    public ArrayList<Animais> selectAnimais() {
        ArrayList<Animais> animais = new ArrayList<>();
        connectToDB();

        String sql = "SELECT * FROM Animais";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n-----> Animais Listados:");

            while (rs.next()) {
                Animais a = new Animais(
                        rs.getInt("idAnimais"),
                        rs.getString("tipo"),
                        rs.getString("nome")
                );

                System.out.println("ID: " + a.getIdAnimais());
                System.out.println("Tipo: " + a.getTipo());
                System.out.println("Nome: " + a.getNome() + "\n");

                animais.add(a);
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAnimais): " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return animais;
    }
}
