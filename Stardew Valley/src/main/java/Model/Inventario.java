package Model;

public class Inventario {

    //atributos
    private int idInventario;
    private int capacidade;

    //constructor
    public Inventario(int idInventario, int capacidade) {
        this.idInventario = idInventario;
        this.capacidade = capacidade;
    }


    //getters e setters
    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
