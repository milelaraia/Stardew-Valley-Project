package br.inatel.dev;

import DAO.*;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JogadorDAO jogador = new JogadorDAO();
        InventarioDAO inventario = new InventarioDAO();
        ItensDAO itens = new ItensDAO();
        LavourasDAO lavouras = new LavourasDAO();
        // testando a conexão com o banco de dados
        ConnectionDAO dao = new ConnectionDAO() {};
        dao.connectToDB();

        //fazendo select de teste
        jogador.selectJogador();
        inventario.selectInventario();
        itens.selectItens();
        lavouras.selectLavouras();

    }
}