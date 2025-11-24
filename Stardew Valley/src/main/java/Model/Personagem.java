package Model;

public abstract class Personagem implements Interagivel {
    protected String nome;

    public Personagem(String nome){
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public abstract void interagir();
}
