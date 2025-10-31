package Personagens;

import java.util.Objects;


public class Inimigo extends Personagem {


    private boolean isBoss;


    public Inimigo(String nome) throws Exception {

        super(nome, (short) 50, (short) 10, (short) 5);
        this.isBoss = false;
    }


    public Inimigo(String nome, short hp, short at, short df, boolean isBoss) throws Exception {
        super(nome, hp, at, df);
        this.isBoss = isBoss;


        if (isBoss) {
            this.setLevel((short) 5);
            System.out.println(this.getNome() + " é um Chefe!");
        }
    }


    public Inimigo(Inimigo modelo) throws Exception {

        super(modelo); //

        this.isBoss = modelo.isBoss;
    }

    @Override
    public Object clone() {
        Inimigo clone = null;
        try {

            clone = new Inimigo(this);
        } catch (Exception e) {
            System.err.println("Erro fatal ao clonar Inimigo: " + e.getMessage());
        }
        return clone;
    }


    @Override
    public String toString() {
        String status = this.isBoss ? " (Chefe)" : " (Normal)";

        String infoBase = super.toString();

        return "[INIMIGO]" + status + "\n" + infoBase;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Personagem thatBase = (Personagem) o;
        if (this.getLvl() != thatBase.getLvl()) return false;
        if (!this.getNome().equals(thatBase.getNome())) return false;


        Inimigo inimigo = (Inimigo) o;
        return isBoss == inimigo.isBoss;
    }


    @Override
    public int hashCode() {

        return Objects.hash(this.getNome(), this.getLvl(), isBoss);
    }


    public boolean isBoss() {
        return this.isBoss;
    }
}