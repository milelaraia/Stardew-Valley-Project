package Model;

public class Habilidades {
    private int idHabilidades;
    private int cultivo;
    private int mineracao;
    private int coleta;
    private int pesca;
    private int combate;

    // Fk's

    private int idInventario;
    private int idJogador;

    //construtor
    public Habilidades(int idHabilidades, int cultivo, int mineracao, int coleta, int pesca, int combate, int idInventario, int idJogador) {
        this.idHabilidades = idHabilidades;
        this.cultivo = cultivo;
        this.mineracao = mineracao;
        this.coleta = coleta;
        this.pesca = pesca;
        this.combate = combate;
        this.idInventario = idInventario;
        this.idJogador = idJogador;
    }

    //getters e setters
    public int getIdHabilidades() {
        return idHabilidades;
    }

    public void setIdHabilidades(int idHabilidades) {
        this.idHabilidades = idHabilidades;
    }

    public int getCultivo() {
        return cultivo;
    }

    public void setCultivo(int cultivo) {
        this.cultivo = cultivo;
    }

    public int getMineracao() {
        return mineracao;
    }

    public void setMineracao(int mineracao) {
        this.mineracao = mineracao;
    }

    public int getColeta() {
        return coleta;
    }

    public void setColeta(int coleta) {
        this.coleta = coleta;
    }

    public int getPesca() {
        return pesca;
    }

    public void setPesca(int pesca) {
        this.pesca = pesca;
    }

    public int getCombate() {
        return combate;
    }

    public void setCombate(int combate) {
        this.combate = combate;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }
}
