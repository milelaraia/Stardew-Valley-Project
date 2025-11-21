package Model;

public class Jogador {

    //atributos
    private int idJogador;
    private String nome;
    private String nome_fazenda;
    private String animal_favorito;
    private String coisa_favorita;
    private String genero;
    private int energia;
    private int saude;

    //Fk
    private int idInventario;

    public Jogador(int idJogador, String nome, String nome_fazenda, String animal_favorito, String coisa_favorita, String genero, int energia, int saude, int idInventario) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.nome_fazenda = nome_fazenda;
        this.animal_favorito = animal_favorito;
        this.coisa_favorita = coisa_favorita;
        this.genero = genero;
        this.energia = energia;
        this.saude = saude;
        this.idInventario = idInventario;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_fazenda() {
        return nome_fazenda;
    }

    public void setNome_fazenda(String nome_fazenda) {
        this.nome_fazenda = nome_fazenda;
    }

    public String getAnimal_favorito() {
        return animal_favorito;
    }

    public void setAnimal_favorito(String animal_favorito) {
        this.animal_favorito = animal_favorito;
    }

    public String getCoisa_favorita() {
        return coisa_favorita;
    }

    public void setCoisa_favorita(String coisa_favorita) {
        this.coisa_favorita = coisa_favorita;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public int getInventario() {
        return idInventario;
    }

    public void setInventario(int inventario) {
        this.idInventario = inventario;
    }
}
