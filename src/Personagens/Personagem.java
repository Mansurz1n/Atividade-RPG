package Personagens;

public class Personagem {

    private String nome;
    private short hp;
    private short at;
    private short df;
    private short lvl;
    private Inventario inventario;
    private boolean ta_vivo;

    public Personagem (String nome, short hp,short at,short df){
        this.lvl = 1;
        this.nome = nome;
        this.hp = hp;
        this.at = at;
        this.df = df;
    }


    public void setLevel (short lvl){
        this.lvl = lvl;
    }


    public void setNome (String nome){
        this.nome = nome;
    }

    public void setAt(short at) {
        this.at = at;
    }

    public void setDf(short df) {
        this.df = df;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public short getAt() {
        return at;
    }

    public short getLvl() {
        return lvl;
    }

    public String getNome() {
        return nome;
    }

    public  void upeiDeNivel(){
        this.lvl+=1;
    }

    public boolean isTa_vivo() {
        return ta_vivo;
    }

    @Override
    public String toString(){
        return "Nome: " + this.nome+
                "\nLevel: " + this.lvl +
                "\nAtaque: " + this.at +
                "\nDefesa: " + this.df +
                "\nVida: " + this.hp;
    }


    @Override
    public Object


}
