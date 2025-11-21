package br.inatel.dev;

import DAO.ConnectionDAO;
import DAO.JogadorDAO;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JogadorDAO jogador = new JogadorDAO();
        // testando a conexão com o banco de dados
        ConnectionDAO dao = new ConnectionDAO() {};
        dao.connectToDB();

        //fazendo select de teste
        jogador.selectJogador();

    }
}