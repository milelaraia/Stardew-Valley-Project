package Model;

public class Itens {

    // Atributos
    private int idItens;
    private String ferramentas;
    private String armas;
    private String aneis;
    private int idInventario; // FK (depende de um inventário que já existe)

    // Contructor
    public Itens(int idItens, String ferramentas, String armas, String aneis, int inventario) {
        this.idItens = idItens;
        this.ferramentas = ferramentas;
        this.armas = armas;
        this.aneis = aneis;
        this.idInventario = inventario;
    }

    public Itens(int idItens, String ferramentas, String armas, String aneis) {
        this.idItens = idItens;
        this.ferramentas = ferramentas;
        this.armas = armas;
        this.aneis = aneis;
    }

    // Getters e Setters
    public int getIdItens() {
        return idItens;
    }

    public String getFerramentas() {
        return ferramentas;
    }

    public String getArmas() {
        return armas;
    }

    public String getAneis() {
        return aneis;
    }

    public int getInventario() {
        return idInventario;
    }
}
