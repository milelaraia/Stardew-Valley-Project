package Model;

import DAO.AmizadeDAO;

public class Amizade {

    private int idAmizade;
    private String nivel;

    private Jogador jogador;
    private Aldeoes aldeao;
    private Animais animal;

    public Amizade(int idAmizade, String nivel, Jogador jogador, Aldeoes aldeao, Animais animal) {
        this.idAmizade = idAmizade;
        this.nivel = nivel;
        this.jogador = jogador;
        this.aldeao = aldeao;
        this.animal = animal;
    }

    // GETTERS
    public int getIdAmizade() {
        return idAmizade;
    }

    public String getNivel() {
        return nivel;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Aldeoes getAldeao() {
        return aldeao;
    }

    public Animais getAnimal() {
        return animal;
    }

    // SETTERS OPCIONAIS
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    // MÉTODO DE CRIAÇÃO (SEM SCANNER)
    public static boolean criarAmizade(Jogador jog, Aldeoes ald, Animais ani, String nivel) {
        Amizade nova = new Amizade(0, nivel, jog, ald, ani);
        AmizadeDAO dao = new AmizadeDAO();
        return dao.insertAmizade(nova);
    }

    // TO STRING
    @Override
    public String toString() {
        return "Amizade {" +
                "nivel='" + nivel + '\'' +
                ", jogador=" + jogador.getNome() +
                ", aldeao=" + aldeao.getNome() +
                ", animal=" + animal.getNome() +
                '}';
    }
}
