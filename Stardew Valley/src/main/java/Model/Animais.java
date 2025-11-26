package Model;

public class Animais {
    private int idAnimais;
    private String tipo;
    private String nome;

    public Animais(int idAnimais, String tipo, String nome) {
        this.idAnimais = idAnimais;
        this.tipo = tipo;
        this.nome = nome;
    }

    public int getIdAnimais() {
        return idAnimais;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }
}
