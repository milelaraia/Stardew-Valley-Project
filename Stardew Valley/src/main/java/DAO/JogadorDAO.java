package DAO;

import Model.Jogador;

import java.sql.SQLException;
import java.util.ArrayList;

public class JogadorDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou


    //INSERT
    public boolean insertJogador(Jogador jogador) {

        String sql = "INSERT INTO Jogador (nome, nome_fazenda, animal_favorito, coisa_favorita, genero, energia, saude) values(?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogador.getNome());
            pst.setString(2,jogador.getNome_fazenda());
            pst.setString(3, jogador.getAnimal_favorito());
            pst.setString(4, jogador.getCoisa_favorita());
            pst.setString(5, jogador.getGenero());
            pst.setInt(6, jogador.getEnergia());
            pst.setInt(7, jogador.getSaude());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateBatalha(String novoNome, String novaFazenda, String novoAnimal_favorito, String novaCoisa_favorita, String novoGenero, int novoEnergia, int novoSaude, int idJogador) {

        String sql = "UPDATE Jogador SET nome=?, nome_fazenda=?, animal_favorito=?, coisa_favorita=?, genero=?, energia=?, saude=? where idJogador=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setString(2, novaFazenda);
            pst.setString(3, novoAnimal_favorito);
            pst.setString(4, novaCoisa_favorita);
            pst.setString(5, novoGenero);
            pst.setInt(6, novoEnergia);
            pst.setInt(7, novoSaude);
            pst.setInt(8, idJogador);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteBatalha(int id) {
        boolean sucesso = false;

        try {
            // Desabilitar auto-commit para iniciar uma transação
            con.setAutoCommit(false);

            // Primeira instrução DELETE
            String sqlJogador = "DELETE FROM Jogador WHERE idJogador = ?";
            pst = con.prepareStatement(sqlJogador);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();

            // Segunda instrução DELETE caso precise apagar algo junto do jogador
            /*
            String sqlInventario = "DELETE FROM Inventario WHERE idInventario = ?";
            pst = con.prepareStatement(sqlInventario);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            */

            // Commit da transação se ambas as operações forem bem-sucedidas
            con.commit();
            sucesso = true;
        } catch (SQLException ex) {
            // Rollback em caso de erro
            try {
                con.rollback();
                System.out.println("Erro = " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Erro no rollback: " + rollbackEx.getMessage());
            }
            sucesso = false;
        } finally {
            // Restaurar o auto-commit e fechar os recursos
            try {
                con.setAutoCommit(true);
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //SELECT
    public ArrayList<Jogador> selectJogador() {
        ArrayList<Jogador> jogador = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Jogador";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Jogadores Listados: ");

            while (rs.next()) {

                Jogador jogadorAux = new Jogador(rs.getInt("idJogador"), rs.getString("nome"), rs.getString("nome_fazenda"), rs.getString("animal_favorito"), rs.getString("coisa_favorita"), rs.getString("genero"), rs.getInt("energia"), rs.getInt("saude"), rs.getInt("Inventario_idInventario"));

                System.out.println("Nome: " + jogadorAux.getNome());
                System.out.println("Id: " + jogadorAux.getIdJogador());
                System.out.println("Nome da fazenda: " + jogadorAux.getNome_fazenda());
                System.out.println("Animal favorito: " + jogadorAux.getAnimal_favorito());
                System.out.println("Coisa favorita: " + jogadorAux.getCoisa_favorita());
                System.out.println("Genero: " + jogadorAux.getGenero());
                System.out.println("Energia: " + jogadorAux.getEnergia());
                System.out.println("Saude: " + jogadorAux.getSaude());
                System.out.println("Id de inventário: " + jogadorAux.getInventario() + "\n");

                jogador.add(jogadorAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return jogador;
    }

}

