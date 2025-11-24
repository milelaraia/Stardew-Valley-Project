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

        System.out.println("\n🌾✨ Bem-vindo(a) a Pelican Town — Stardew Java Edition ✨🌾");

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Listar Jogadores");
            System.out.println("2 - Listar Aldeões");
            System.out.println("3 - Listar Animais");
            System.out.println("4 - Criar Nova Amizade");
            System.out.println("5 - Testar Interações (Herança & Polimorfismo)");
            System.out.println("0 - Sair");
            System.out.print("> ");

            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("⚠ Você não digitou nada! Tente novamente.");
                continue;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Opção inválida! Digite apenas números.");
                continue;
            }

            switch (opcao) {

                case 1 -> {
                    System.out.println("\n📜 Listando jogadores...");
                    new JogadorDAO().selectJogador();
                }

                case 2 -> {
                    System.out.println("\n🏘 Listando aldeões...");
                    new AldeoesDAO().selectAldeoes();
                }

                case 3 -> {
                    System.out.println("\n🐄 Listando animais...");
                    new AnimaisDAO().selectAnimais();
                }

                case 4 -> criarNovaAmizade(sc);

                case 5 -> testarPolimorfismo();

                case 0 -> System.out.println("\n👋 Encerrando sua jornada em Pelican Town...");

                default -> System.out.println("⚠ Opção inexistente!");
            }
        }
    }

    // ============================================================
    // FUNÇÃO 1: CRIAR AMIZADE
    // ============================================================
    private static void criarNovaAmizade(Scanner sc) {

        JogadorDAO jdao = new JogadorDAO();
        AldeoesDAO adao = new AldeoesDAO();
        AnimaisDAO anmDAO = new AnimaisDAO();

        ArrayList<Jogador> jogadores = jdao.selectJogador();
        ArrayList<Aldeoes> aldeoes = adao.selectAldeoes();
        ArrayList<Animais> animais = anmDAO.selectAnimais();

        System.out.println("\n💛 Criando nova amizade...");

        int idJogador = lerID(sc, "ID do jogador: ");
        int idAldeao = lerID(sc, "ID do aldeão: ");
        int idAnimal = lerID(sc, "ID do animal: ");

        System.out.print("Nível da amizade (Baixo/Médio/Alto): ");
        String nivel = sc.nextLine().trim();

        // Validar IDs
        Jogador jog = jogadores.stream().filter(j -> j.getIdJogador() == idJogador).findFirst().orElse(null);
        Aldeoes ald = aldeoes.stream().filter(a -> a.getIdAldeoes() == idAldeao).findFirst().orElse(null);
        Animais ani = animais.stream().filter(a -> a.getIdAnimais() == idAnimal).findFirst().orElse(null);

        if (jog == null || ald == null || ani == null) {
            System.out.println("❌ Um ou mais IDs informados não existem.");
            return;
        }

        Amizade amizade = new Amizade(0, nivel, jog, ald, ani);

        AmizadeDAO amDAO = new AmizadeDAO();
        if (amDAO.insertAmizade(amizade)) {
            System.out.println("💛 Amizade criada com sucesso!");
        } else {
            System.out.println("❌ Não foi possível criar amizade.");
        }
    }

    // ============================================================
    // FUNÇÃO 2: TESTE DE HERANÇA + POLIMORFISMO
    // ============================================================
    private static void testarPolimorfismo() {

        System.out.println("\n🔮 Testando interações polimórficas...");

        Personagem p1 = new Jogador(1, "Mile", "Mar Azul", "Gato", "Peixe", "Fem", 200, 150, 1);
        Personagem p2 = new Aldeoes(1, "Abigail", "Explorar minas", "Ametista");

        p1.interagir();  // comportamento do jogador
        p2.interagir();  // comportamento do aldeão

        System.out.println("\n💬 Testando interface Interagivel:");
        ((Interagivel) p1).conversar();
        ((Interagivel) p2).conversar();
    }

    // ============================================================
    // FUNÇÃO EXTRA: LEITURA SEGURA DE ID
    // ============================================================
    private static int lerID(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("⚠ Entrada vazia! Tente novamente.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Digite apenas números!");
            }
        }
    }
}