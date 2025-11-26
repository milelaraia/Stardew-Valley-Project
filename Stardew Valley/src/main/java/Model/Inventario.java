package Model;

public class Inventario {

    // Atributos
    private int idInventario;
    private int capacidade;

    // Constructor
    public Inventario(int idInventario, int capacidade) {
        this.idInventario = idInventario;
        this.capacidade = capacidade;
    }


    // Getters e setters
    public int getIdInventario() {
        return idInventario;
    }

    public int getCapacidade() {
        return capacidade;
    }

}
