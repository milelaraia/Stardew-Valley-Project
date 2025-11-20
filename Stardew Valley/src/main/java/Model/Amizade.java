package Model;

public class Amizade {
    private int idAmizade;
    private String niveis;
    private Jogador jogador;
    private Aldeoes aldeao;
    private Animais animal;

    public Amizade(int idAmizade, String niveis, Jogador jogador, Aldeoes aldeao, Animais animal) {
        this.idAmizade = idAmizade;
        this.niveis = niveis;
        this.jogador = jogador;
        this.aldeao = aldeao;
        this.animal = animal;
    }

    public int getIdAmizade() {
        return idAmizade;
    }

    public void setIdAmizade(int idAmizade) {
        this.idAmizade = idAmizade;
    }

    public String getNiveis() {
        return niveis;
    }

    public void setNiveis(String niveis) {
        this.niveis = niveis;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Aldeoes getAldeao() {
        return aldeao;
    }

    public void setAldeao(Aldeoes aldeao) {
        this.aldeao = aldeao;
    }

    public Animais getAnimal() {
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }
}
