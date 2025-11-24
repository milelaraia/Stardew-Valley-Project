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

    // Getters e Setters
    public int getIdItens() {
        return idItens;
    }

    public void setIdItens(int idItens) {
        this.idItens = idItens;
    }

    public String getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(String ferramentas) {
        this.ferramentas = ferramentas;
    }

    public String getArmas() {
        return armas;
    }

    public void setArmas(String armas) {
        this.armas = armas;
    }

    public String getAneis() {
        return aneis;
    }

    public void setAneis(String aneis) {
        this.aneis = aneis;
    }

    public int getInventario() {
        return idInventario;
    }

    public void setInventario(int inventario) {
        this.idInventario = inventario;
    }
}
