package Model;

public class Itens {
    private int idItens;
    private String ferramentas;
    private String armas;
    private String aneis;
    private Inventario inventario;

    public Itens(int idItens, String ferramentas, String armas, String aneis, Inventario inventario) {
        this.idItens = idItens;
        this.ferramentas = ferramentas;
        this.armas = armas;
        this.aneis = aneis;
        this.inventario = inventario;
    }

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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
