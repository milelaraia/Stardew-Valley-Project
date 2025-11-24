package br.inatel.dev;

import DAO.*;
import Model.*;
import Util.RelogioJogo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Inicia a thread do relógio do jogo
        RelogioJogo relogio = new RelogioJogo();
        relogio.setDaemon(true); // Permite fechar sem travar
        relogio.start();

        System.out.println("\n Bem-vindo(a) a Pelican Town — Stardew Java Edition");

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Listar Jogadores");
            System.out.println("2 - Listar Aldeões");
            System.out.println("3 - Listar Animais");
            System.out.println("4 - Listar Itens");
            System.out.println("5 - Listar Inventario");
            System.out.println("6 - Listar Habilidades");
            System.out.println("7 - Criar Nova Amizade");
            System.out.println("8 - Testar Interações");
            System.out.println("0 - Sair");
            System.out.print("> ");

            String entrada = sc.nextLine().trim(); // garante que a entrada nao tenha um espaço antes e "quebre" o codigo

            // trata os erros de entrada do menu
            if (entrada.isEmpty()) {
                System.out.println("Você não digitou nada! Tente novamente.");
                continue;
            }

            try {
                opcao = Integer.parseInt(entrada); // metodo estatico do java que trata se há um numero válido
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite apenas números.");
                continue;
            }

            switch (opcao) {

                case 1 -> {
                    System.out.println("\nListando jogadores...");
                    new JogadorDAO().selectJogador();
                }

                case 2 -> {
                    System.out.println("\nListando aldeões...");
                    new AldeoesDAO().selectAldeoes();
                }

                case 3 -> {
                    System.out.println("\nListando animais...");
                    new AnimaisDAO().selectAnimais();
                }

                case 4 -> {
                    System.out.println("\nListando itens...");
                    new ItensDAO().selectItens();
                }

                case 5 -> {
                    System.out.println("\nListando inventário...");
                    new InventarioDAO().selectInventario();
                }

                case 6 -> {
                    System.out.println("\nListando habilidades...");
                    new HabilidadesDAO().selectHabilidades();
                }

                case 7 -> criarNovaAmizade(sc);

                case 8 -> interacoes();

                case 0 -> System.out.println("\nEncerrando sua jornada em Pelican Town...");

                default -> System.out.println("Opção inexistente!");
            }
        }
    }

    // funçao 1 - criar amizade
    private static void criarNovaAmizade(Scanner sc) {

        JogadorDAO jdao = new JogadorDAO();
        AldeoesDAO adao = new AldeoesDAO();
        AnimaisDAO anmDAO = new AnimaisDAO();

        ArrayList<Jogador> jogadores = jdao.selectJogador();
        ArrayList<Aldeoes> aldeoes = adao.selectAldeoes();
        ArrayList<Animais> animais = anmDAO.selectAnimais();

        System.out.println("\nCriando nova amizade...");

        int idJogador = lerID(sc, "ID do jogador: ");
        int idAldeao = lerID(sc, "ID do aldeão: ");
        int idAnimal = lerID(sc, "ID do animal: ");

        System.out.print("Nível da amizade (Baixo/Médio/Alto): ");
        String nivel = sc.nextLine().trim();

        // Validar os ids
        Jogador jog = jogadores.stream().filter(j -> j.getIdJogador() == idJogador).findFirst().orElse(null);
        Aldeoes ald = aldeoes.stream().filter(a -> a.getIdAldeoes() == idAldeao).findFirst().orElse(null);
        Animais ani = animais.stream().filter(a -> a.getIdAnimais() == idAnimal).findFirst().orElse(null);

        if (jog == null || ald == null || ani == null) {
            System.out.println("Um ou mais IDs informados não existem.");
            return;
        }

        Amizade amizade = new Amizade(0, nivel, jog, ald, ani);

        AmizadeDAO amDAO = new AmizadeDAO();
        if (amDAO.insertAmizade(amizade)) {
            System.out.println("Amizade criada com sucesso!");
        } else {
            System.out.println("Não foi possível criar amizade.");
        }
    }

    //  funçao 2 - interaçoes
    private static void interacoes() {

        System.out.println("\nTestando interações na vila...");

        Personagem p1 = new Jogador(1, "Mile", "Mar Azul", "Gato", "Peixe", "Fem", 200, 150, 1);
        Personagem p2 = new Aldeoes(1, "Abigail", "Explorar minas", "Ametista");

        p1.interagir();  // comportamento do jogador
        p2.interagir();  // comportamento do aldeão

        System.out.println("\nDiálogos:");
        ((Interagivel) p1).conversar();
        ((Interagivel) p2).conversar();
    }

    // funçao extra - leitura segura de id
    private static int lerID(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Entrada vazia! Tente novamente.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            }
        }
    }
}