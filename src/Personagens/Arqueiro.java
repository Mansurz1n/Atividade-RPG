package Personagens;

import java.util.Objects;


public class Arqueiro extends Personagem {


    private short flechas;
    private short maxFlechas;


    public Arqueiro(String nome) throws Exception {

        super(nome, (short) 90, (short) 28, (short) 8);


        this.maxFlechas = 30;
        this.flechas = 30;
    }


    public Arqueiro(Arqueiro modelo) throws Exception {

        super(modelo);


        this.flechas = modelo.flechas;
        this.maxFlechas = modelo.maxFlechas;
    }


    @Override
    public Object clone() {
        Arqueiro clone = null;
        try {

            clone = new Arqueiro(this);
        } catch (Exception e) {
            System.err.println("Erro fatal ao clonar Arqueiro: " + e.getMessage());
        }
        return clone;
    }


    @Override
    public short calculoDano(short defOponente) {

        short dado = (short) (this.dado.nextInt(20) + 1);
        short dano;


        if (this.flechas > 0) {
            this.flechas--;
            System.out.println(this.getNome() + " dispara uma FLECHA PRECISA! (Restam: " + this.flechas + ")");


            dano = (short) ((this.getAt() + dado) - defOponente);

        } else {

            System.out.println(this.getNome() + " ataca com uma adaga...");
            short danoCorpoACorpo = (short) (this.getAt() / 3);
            dano = (short) ((danoCorpoACorpo + dado) - defOponente);
        }

        System.out.println(this.getNome() + " rolou " + dado + ". Ataque total: " + (this.getAt() + dado));
        if (dano < 0) dano = 0;
        return dano;
    }


    public void adicionarFlechas(short quantidade) {
        this.flechas += quantidade;
        if (this.flechas > this.maxFlechas) {
            this.flechas = this.maxFlechas;
        }
        System.out.println(this.getNome() + " reabasteceu flechas. Total: " + this.flechas);
    }


    @Override
    public String toString() {

        String infoBase = super.toString();

        return infoBase +
                "\nFlechas: " + this.flechas + "/" + this.maxFlechas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Personagem thatBase = (Personagem) o;
        if (this.getLvl() != thatBase.getLvl()) return false;
        if (!this.getNome().equals(thatBase.getNome())) return false;


        Arqueiro arqueiro = (Arqueiro) o;
        return flechas == arqueiro.flechas &&
                maxFlechas == arqueiro.maxFlechas;
    }


    @Override
    public int hashCode() {

        return Objects.hash(this.getNome(), this.getLvl(), flechas, maxFlechas);
    }



    public short getFlechas() {
        return flechas;
    }

    public short getMaxFlechas() {
        return maxFlechas;
    }
}