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

    @Override
    public void interagir() {
        System.out.println(nome + " acena de forma amigável.");
    }

    @Override
    public void conversar() {
        System.out.println(nome + " diz: 'Você trouxe algo que eu gosto?'");
    }

    // ----------------------------
    // GETTERS NECESSÁRIOS PARA O DAO
    // ----------------------------

    public int getIdAldeoes() {
        return idAldeoes;
    }

    public String getHobby() {
        return hobby;
    }

    public String getPresente_que_ama() {
        return presente_que_ama;
    }

    // SETTERS OPCIONAIS
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setPresente_que_ama(String presente_que_ama) {
        this.presente_que_ama = presente_que_ama;
    }
}