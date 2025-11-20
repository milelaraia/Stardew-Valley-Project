package Model;

public class Aldeoes {
    // atributos
    private int idAldeoes;
    private String nome;
    private String hobby;
    private String presente_que_ama;

    //constructor
    public Aldeoes(int idAldeoes, String nome, String hobby, String presente_que_ama) {
        this.idAldeoes = idAldeoes;
        this.nome = nome;
        this.hobby = hobby;
        this.presente_que_ama = presente_que_ama;
    }

    // getter e setter
    public int getIdAldeoes() {
        return idAldeoes;
    }

    public void setIdAldeoes(int idAldeoes) {
        this.idAldeoes = idAldeoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPresente_que_ama() {
        return presente_que_ama;
    }

    public void setPresente_que_ama(String presente_que_ama) {
        this.presente_que_ama = presente_que_ama;
    }
}
