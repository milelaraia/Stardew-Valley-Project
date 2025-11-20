package Model;

public class Lavouras {
    private int idLavouras;
    private String flores;
    private String frutas;
    private String vegetais;
    private String graos;
    private String hortalicas;
    private String tuberculos;

    public Lavouras(int idLavouras, String flores, String frutas, String vegetais, String graos, String hortalicas, String tuberculos) {
        this.idLavouras = idLavouras;
        this.flores = flores;
        this.frutas = frutas;
        this.vegetais = vegetais;
        this.graos = graos;
        this.hortalicas = hortalicas;
        this.tuberculos = tuberculos;
    }

    public int getIdLavouras() {
        return idLavouras;
    }

    public void setIdLavouras(int idLavouras) {
        this.idLavouras = idLavouras;
    }

    public String getFlores() {
        return flores;
    }

    public void setFlores(String flores) {
        this.flores = flores;
    }

    public String getFrutas() {
        return frutas;
    }

    public void setFrutas(String frutas) {
        this.frutas = frutas;
    }

    public String getVegetais() {
        return vegetais;
    }

    public void setVegetais(String vegetais) {
        this.vegetais = vegetais;
    }

    public String getGraos() {
        return graos;
    }

    public void setGraos(String graos) {
        this.graos = graos;
    }

    public String getHortalicas() {
        return hortalicas;
    }

    public void setHortalicas(String hortalicas) {
        this.hortalicas = hortalicas;
    }

    public String getTuberculos() {
        return tuberculos;
    }

    public void setTuberculos(String tuberculos) {
        this.tuberculos = tuberculos;
    }
}
