package Model;

public class Aldeoes extends Personagem implements Interagivel {

    private int idAldeoes;
    private String hobby;
    private String presente_que_ama;

    public Aldeoes(int idAldeoes, String nome, String hobby, String presente_que_ama) {
        super(nome);
        this.idAldeoes = idAldeoes;
        this.hobby = hobby;
        this.presente_que_ama = presente_que_ama;
    }

    // Métodos das classes Personagem e Interagivel
    @Override
    public void interagir() {
        System.out.println(nome + " acena de forma amigável.");
    }

    @Override
    public void conversar() {
        System.out.println(nome + " diz: 'Você trouxe algo que eu gosto?'");
    }


    // Getters necessários para o DAO
    public int getIdAldeoes() {
        return idAldeoes;
    }

    public String getHobby() {
        return hobby;
    }

    public String getPresente_que_ama() {
        return presente_que_ama;
    }

}