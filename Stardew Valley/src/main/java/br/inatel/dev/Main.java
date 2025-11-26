package br.inatel.dev;

import DAO.*;
import Model.*;
import Util.RelogioJogo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        RelogioJogo relogio = new RelogioJogo();
        relogio.setDaemon(true);
        relogio.start();

        System.out.println("\n Bem-vindo(a) à Pelican Town — Stardew Java Edition!");

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=========== MENU PRINCIPAL ===========");
            System.out.println("1 - Jogadores");
            System.out.println("2 - Aldeões");
            System.out.println("3 - Animais");
            System.out.println("4 - Inventário");
            System.out.println("5 - Itens");
            System.out.println("6 - Habilidades");
            System.out.println("7 - Amizades");
            System.out.println("8 - Testar Interações");
            System.out.println("0 - Sair");
            System.out.print("> ");

            opcao = lerOpcao(sc);

            switch (opcao) {

                case 1 -> menuJogadores(sc);
                case 2 -> menuAldeoes(sc);
                case 3 -> menuAnimais(sc);
                case 4 -> menuInventario(sc);
                case 5 -> menuItens(sc);
                case 6 -> menuHabilidades(sc);
                case 7 -> menuAmizades(sc);
                case 8 -> interacoes();
                case 0 -> System.out.println("\nSaindo da vila... Até a próxima!");
                default -> System.out.println("Opção inexistente!");
            }
        }
    }

    // SUBMENUS

    // JOGADORES
    private static void menuJogadores(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU JOGADORES ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Jogadores + Inventário (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            JogadorDAO dao = new JogadorDAO();

            switch (op) {
                case 1 -> dao.selectJogador();
                case 2 -> inserirJogador(sc);
                case 3 -> atualizarJogador(sc);
                case 4 -> deletarJogador(sc);
                case 5 -> dao.selectJogadoresComInventario();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ALDEÕES
    private static void menuAldeoes(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU ALDEÕES ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Aldeões + Amizades (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            AldeoesDAO dao = new AldeoesDAO();

            switch (op) {
                case 1 -> dao.selectAldeoes();
                case 2 -> inserirAldeao(sc);
                case 3 -> atualizarAldeao(sc);
                case 4 -> deletarAldeao(sc);
                case 5 -> dao.selectAldeoesComAmizades();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ANIMAIS
    private static void menuAnimais(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU ANIMAIS ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Animais + Amizades (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            AnimaisDAO dao = new AnimaisDAO();

            switch (op) {
                case 1 -> dao.selectAnimais();
                case 2 -> inserirAnimal(sc);
                case 3 -> atualizarAnimal(sc);
                case 4 -> deletarAnimal(sc);
                case 5 -> dao.selectAnimaisComAmizades();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    // INVENTÁRIO
    private static void menuInventario(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU INVENTÁRIO ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Inventário + Jogadores (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            InventarioDAO dao = new InventarioDAO();

            switch (op) {
                case 1 -> dao.selectInventario();
                case 2 -> inserirInventario(sc);
                case 3 -> atualizarInventario(sc);
                case 4 -> deletarInventario(sc);
                case 5 -> dao.selectInventarioComJogadores();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    // ITENS
    private static void menuItens(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU ITENS ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Itens + Inventário (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            ItensDAO dao = new ItensDAO();

            switch (op) {
                case 1 -> dao.selectItens();
                case 2 -> inserirItens(sc);
                case 3 -> atualizarItem(sc);
                case 4 -> deletarItens(sc);
                case 5 -> dao.selectItensComInventario();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    // HABILIDADES
    private static void menuHabilidades(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU HABILIDADES ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Habilidades + Jogador (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            HabilidadesDAO dao = new HabilidadesDAO();

            switch (op) {
                case 1 -> dao.selectHabilidades();
                case 2 -> inserirHabilidade(sc);
                case 3 -> atualizarHabilidade(sc);
                case 4 -> deletarHabilidade(sc);
                case 5 -> dao.selectHabilidadesComJogador();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    // AMIZADES
    private static void menuAmizades(Scanner sc) {

        int op = -1;
        while (op != 0) {

            System.out.println("\n---- MENU AMIZADES ----");
            System.out.println("1 - Listar");
            System.out.println("2 - Criar Amizade");
            System.out.println("3 - Atualizar nível");
            System.out.println("4 - Deletar amizade");
            System.out.println("5 - Amizades COMPLETAS (JOIN 3 tabelas)");
            System.out.println("6 - Resumo de amizades por jogador");
            System.out.println("0 - Voltar");
            System.out.print("> ");

            op = lerOpcao(sc);
            AmizadeDAO dao = new AmizadeDAO();

            switch (op) {
                case 1 -> dao.selectAmizades();
                case 2 -> criarNovaAmizade(sc);
                case 3 -> atualizarAmizade(sc);
                case 4 -> deletarAmizade(sc);
                case 5 -> dao.selectAmizadesComDetalhes();
                case 6 -> dao.selectResumoAmizadesPorJogador();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // CRUD HELPERS

    // ---- Jogador ----
    private static void inserirJogador(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Fazenda: ");
        String faz = sc.nextLine();

        System.out.print("Animal favorito: ");
        String animal = sc.nextLine();

        System.out.print("Coisa favorita: ");
        String coisa = sc.nextLine();

        System.out.print("Genero: ");
        String gen = sc.nextLine();

        System.out.print("Energia: ");
        int ener = lerOpcao(sc);

        System.out.print("Saude: ");
        int saude = lerOpcao(sc);

        Jogador j = new Jogador(0, nome, faz, animal, coisa, gen, ener, saude, 0);

        if (new JogadorDAO().insertJogador(j))
            System.out.println("Jogador criado com inventário próprio!");
        else
            System.out.println("Erro ao criar jogador!");
    }


    private static void atualizarJogador(Scanner sc) {

        System.out.print("ID do jogador a atualizar: ");
        int id = lerOpcao(sc);
        sc.nextLine(); // limpar buffer

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Nova fazenda: ");
        String fazenda = sc.nextLine();

        System.out.print("Novo animal favorito: ");
        String animal = sc.nextLine();

        System.out.print("Nova coisa favorita: ");
        String coisa = sc.nextLine();

        System.out.print("Novo gênero: ");
        String genero = sc.nextLine();

        System.out.print("Nova energia: ");
        int energia = lerOpcao(sc);

        System.out.print("Nova saúde: ");
        int saude = lerOpcao(sc);

        // inventário não muda no update (então passa 0 só para preencher o construtor)
        Jogador j = new Jogador(id, nome, fazenda, animal, coisa, genero, energia, saude, 0);

        if (new JogadorDAO().updateJogador(j))
            System.out.println("Atualizado!");
        else
            System.out.println("Erro!");
    }


    private static void deletarJogador(Scanner sc) {
        System.out.print("ID para deletar: ");
        int id = lerOpcao(sc);

        if (new JogadorDAO().deleteJogador(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }

    // ---- Aldeões ----
    private static void inserirAldeao(Scanner sc) {

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Hobby: ");
        String hobby = sc.nextLine();
        System.out.print("Presente favorito: ");
        String presente = sc.nextLine();

        Aldeoes a = new Aldeoes(0, nome, hobby, presente);

        if (new AldeoesDAO().insertAldeoes(a))
            System.out.println("Aldeão criado!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarAldeao(Scanner sc) {

        System.out.print("ID do aldeão: ");
        int id = lerOpcao(sc);

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Novo hobby: ");
        String hobby = sc.nextLine();

        System.out.print("Novo presente favorito: ");
        String presente = sc.nextLine();

        Aldeoes a = new Aldeoes(id, nome, hobby, presente);

        if (new AldeoesDAO().updateAldeoes(a))
            System.out.println("Aldeão atualizado!");
        else
            System.out.println("Erro ao atualizar aldeão!");
    }


    private static void deletarAldeao(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new AldeoesDAO().deleteAldeoes(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }

    // ---- Animais ----
    private static void inserirAnimal(Scanner sc) {
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        Animais a = new Animais(0, tipo, nome);

        if (new AnimaisDAO().insertAnimais(a))
            System.out.println("Animal criado!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarAnimal(Scanner sc) {
        System.out.print("ID do animal: ");
        int id = lerOpcao(sc);

        System.out.print("Novo tipo: ");
        String tipo = sc.nextLine();

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        Animais a = new Animais(id, tipo, nome);

        if (new AnimaisDAO().updateAnimais(a))
            System.out.println("Atualizado!");
        else
            System.out.println("Erro!");
    }


    private static void deletarAnimal(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new AnimaisDAO().deleteAnimais(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }


    // ---- Inventário ----
    private static void inserirInventario(Scanner sc) {
        System.out.print("Capacidade: ");
        int cap = lerOpcao(sc);

        Inventario inv = new Inventario(0, cap);

        if (new InventarioDAO().insertInventario(inv))
            System.out.println("Inventário criado!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarInventario(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        System.out.print("Nova capacidade: ");
        int cap = lerOpcao(sc);

        Inventario inv = new Inventario(id, cap);

        if (new InventarioDAO().updateInventario(inv))
            System.out.println("Atualizado!");
        else
            System.out.println("Erro!");
    }

    private static void deletarInventario(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new InventarioDAO().deleteInventario(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }


    // Itens
    private static void inserirItens(Scanner sc) {
        System.out.print("Ferramentas: ");
        String fer = sc.nextLine();
        System.out.print("Armas: ");
        String arm = sc.nextLine();
        System.out.print("Anéis: ");
        String ane = sc.nextLine();
        System.out.print("ID Inventário: ");
        int inv = lerOpcao(sc);

        Itens i = new Itens(0, fer, arm, ane, inv);

        if (new ItensDAO().insertItens(i))
            System.out.println("Itens criados!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarItem(Scanner sc) {

        ItensDAO dao = new ItensDAO();

        System.out.print("ID do item: ");
        int idItem = Integer.parseInt(sc.nextLine());

        System.out.print("Nova ferramenta: ");
        String novaFerramenta = sc.nextLine();

        System.out.print("Nova arma: ");
        String novaArma = sc.nextLine();

        System.out.print("Novo anel: ");
        String novoAnel = sc.nextLine();

        // cria o objeto completo para enviar ao DAO
        Itens it = new Itens(idItem, novaFerramenta, novaArma, novoAnel);
        dao.updateItens(it);

        if (dao.updateItens(it)) {
            System.out.println("Item atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar item!");
        }
    }


    private static void deletarItens(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new ItensDAO().deleteItens(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }


    // Habilidades
    private static void inserirHabilidade(Scanner sc) {
        System.out.print("Cultivo: ");
        int c = lerOpcao(sc);
        System.out.print("Mineracao: ");
        int m = lerOpcao(sc);
        System.out.print("Coleta: ");
        int co = lerOpcao(sc);
        System.out.print("Pesca: ");
        int p = lerOpcao(sc);
        System.out.print("Combate: ");
        int cb = lerOpcao(sc);
        System.out.print("ID Jogador: ");
        int j = lerOpcao(sc);
        System.out.print("ID Inventário: ");
        int inv = lerOpcao(sc);

        Habilidades h = new Habilidades(0, c, m, co, p, cb, j, inv);

        if (new HabilidadesDAO().insertHabilidade(h))
            System.out.println("Criado!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarHabilidade(Scanner sc) {
        System.out.print("ID da habilidade: ");
        int id = lerOpcao(sc);
        sc.nextLine();

        System.out.print("Novo valor pesca: ");
        int novo = lerOpcao(sc);

        Habilidades h = new Habilidades(id, 0,0,0, novo,0, 0,0);

        if (new HabilidadesDAO().updateHabilidade(h))
            System.out.println("Atualizado!");
        else
            System.out.println("Erro!");
    }

    private static void deletarHabilidade(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new HabilidadesDAO().deleteHabilidade(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }


    // Amizades
    private static void criarNovaAmizade(Scanner sc) {

        System.out.print("ID jogador: ");
        int idJ = lerOpcao(sc);

        System.out.print("ID aldeão: ");
        int idA = lerOpcao(sc);

        System.out.print("ID animal: ");
        int idN = lerOpcao(sc);

        System.out.print("Nível (Baixo/Médio/Alto): ");
        String nivel = sc.nextLine();

        Jogador jog = new JogadorDAO().selectJogador().stream()
                .filter(j -> j.getIdJogador() == idJ).findFirst().orElse(null);

        Aldeoes ald = new AldeoesDAO().selectAldeoes().stream()
                .filter(a -> a.getIdAldeoes() == idA).findFirst().orElse(null);

        Animais ani = new AnimaisDAO().selectAnimais().stream()
                .filter(an -> an.getIdAnimais() == idN).findFirst().orElse(null);

        if (jog == null || ald == null || ani == null) {
            System.out.println("IDs inválidos.");
            return;
        }

        if (Amizade.criarAmizade(jog, ald, ani, nivel))
            System.out.println("Amizade criada!");
        else
            System.out.println("Erro!");
    }

    private static void atualizarAmizade(Scanner sc) {
        System.out.print("ID da amizade: ");
        int id = lerOpcao(sc);
        sc.nextLine();

        System.out.print("Novo nível: ");
        String nivel = sc.nextLine();

        if (new AmizadeDAO().updateAmizade(id, nivel))
            System.out.println("Atualizado!");
        else
            System.out.println("Erro!");
    }

    private static void deletarAmizade(Scanner sc) {
        System.out.print("ID: ");
        int id = lerOpcao(sc);

        if (new AmizadeDAO().deleteAmizade(id))
            System.out.println("Deletado!");
        else
            System.out.println("Erro!");
    }

    private static int lerOpcao(Scanner sc) {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }


    // Interações (Polimorfismo)
    private static void interacoes() {

        System.out.println("\nTestando interações...");

        Personagem p1 = new Jogador(1, "Mile", "Mar Azul", "Gato", "Peixe", "Fem", 200, 150, 1);
        Personagem p2 = new Aldeoes(1, "Abigail", "Explorar minas", "Ametista");

        p1.interagir();
        p2.interagir();

        System.out.println("\nConversas:");
        ((Interagivel) p1).conversar();
        ((Interagivel) p2).conversar();
    }
}