package Model;

public class Jogador extends Personagem implements Interagivel {

    private int idJogador;
    private String nome_fazenda;
    private String animal_favorito;
    private String coisa_favorita;
    private String genero;
    private int energia;
    private int saude;
    private int inventario;  // ID do inventário (FK)

    public Jogador(int idJogador, String nome, String nome_fazenda, String animal_favorito,
                   String coisa_favorita, String genero, int energia, int saude, int inventario) {

        super(nome);
        this.idJogador = idJogador;
        this.nome_fazenda = nome_fazenda;
        this.animal_favorito = animal_favorito;
        this.coisa_favorita = coisa_favorita;
        this.genero = genero;
        this.energia = energia;
        this.saude = saude;
        this.inventario = inventario;
    }

    @Override
    public void interagir() {
        System.out.println(nome + " observa a fazenda com orgulho.");
    }

    @Override
    public void conversar() {
        System.out.println(nome + " diz: 'É um ótimo dia para plantar umas sementes!'");
    }

    // ----------------------------
    // GETTERS NECESSÁRIOS PARA O DAO
    // ----------------------------

    public int getIdJogador() {
        return idJogador;
    }

    public String getNome_fazenda() {
        return nome_fazenda;
    }

    public String getAnimal_favorito() {
        return animal_favorito;
    }

    public String getCoisa_favorita() {
        return coisa_favorita;
    }

    public String getGenero() {
        return genero;
    }

    public int getEnergia() {
        return energia;
    }

    public int getSaude() {
        return saude;
    }

    public int getInventario() {
        return inventario;
    }

    // Se quiser, pode adicionar SETTERS também:
    public void setNome_fazenda(String nome_fazenda) {
        this.nome_fazenda = nome_fazenda;
    }

    public void setAnimal_favorito(String animal_favorito) {
        this.animal_favorito = animal_favorito;
    }

    public void setCoisa_favorita(String coisa_favorita) {
        this.coisa_favorita = coisa_favorita;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
}
