package DAO;

import Model.Aldeoes;

import java.sql.SQLException;
import java.util.ArrayList;

public class AldeoesDAO extends ConnectionDAO {

    boolean sucesso = false;

    // ------------------ INSERT ------------------
    public boolean insertAldeao(Aldeoes aldeao) {
        connectToDB();
        String sql = "INSERT INTO Aldeoes (nome, hobby, presente_que_ama) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, aldeao.getNome());
            pst.setString(2, aldeao.getHobby());
            pst.setString(3, aldeao.getPresente_que_ama());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro (insertAldeao): " + exc.getMessage());
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
    public boolean updateAldeao(String nome, String hobby, String presente, int idAldeao) {
        connectToDB();
        String sql = "UPDATE Aldeoes SET nome=?, hobby=?, presente_que_ama=? WHERE idAldeoes=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, hobby);
            pst.setString(3, presente);
            pst.setInt(4, idAldeao);
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro (updateAldeao): " + e.getMessage());
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
    public boolean deleteAldeao(int id) {
        connectToDB();
        boolean sucesso = false;

        String sql = "DELETE FROM Aldeoes WHERE idAldeoes = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro (deleteAldeao): " + e.getMessage());
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
    public ArrayList<Aldeoes> selectAldeoes() {
        ArrayList<Aldeoes> aldeoes = new ArrayList<>();
        connectToDB();

        String sql = "SELECT * FROM Aldeoes";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n-----> Aldeões Listados:");

            while (rs.next()) {
                Aldeoes a = new Aldeoes(
                        rs.getInt("idAldeoes"),
                        rs.getString("nome"),
                        rs.getString("hobby"),
                        rs.getString("presente_que_ama")
                );

                System.out.println("ID: " + a.getIdAldeoes());
                System.out.println("Nome: " + a.getNome());
                System.out.println("Hobby: " + a.getHobby());
                System.out.println("Presente que ama: " + a.getPresente_que_ama() + "\n");

                aldeoes.add(a);
            }

            sucesso = true;

        } catch (SQLException e) {
            System.out.println("Erro (selectAldeoes): " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return aldeoes;
    }
}
