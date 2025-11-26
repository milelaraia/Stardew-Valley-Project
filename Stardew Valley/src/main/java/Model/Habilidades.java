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

    public int getCultivo() {
        return cultivo;
    }

    public int getMineracao() {
        return mineracao;
    }

    public int getColeta() {
        return coleta;
    }

    public int getPesca() {
        return pesca;
    }

    public int getCombate() {
        return combate;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public int getIdJogador() {
        return idJogador;
    }
}
