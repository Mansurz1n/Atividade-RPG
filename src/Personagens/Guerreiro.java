package Personagens;

import java.util.Objects;


public class Guerreiro extends Personagem {


    private short furia;
    private short maxFuria;


    public Guerreiro(String nome) throws Exception {

        super(nome, (short) 150, (short) 15, (short) 15);


        this.maxFuria = 100;
        this.furia = 0;
    }


    public Guerreiro(Guerreiro modelo) throws Exception {

        super(modelo); //

        this.furia = modelo.furia;
        this.maxFuria = modelo.maxFuria;
    }


    @Override
    public Object clone() {
        Guerreiro clone = null;
        try {

            clone = new Guerreiro(this);
        } catch (Exception e) {
            System.err.println("Erro fatal ao clonar Guerreiro: " + e.getMessage());
        }
        return clone;
    }


    @Override
    public void dano(short dano) {
        super.dano(dano);


        if (dano > 0 && this.isTa_vivo()) {
            this.furia += 10;
            if (this.furia > this.maxFuria) {
                this.furia = this.maxFuria;
            }
            System.out.println(this.getNome() + " acumulou fúria! (" + this.furia + ")");
        }
    }


    @Override
    public short calculoDano(short defOponente) {

        short dado = (short) (this.dado.nextInt(20) + 1);
        short danoBase = this.getAt();


        if (this.furia >= 25) {
            System.out.println(this.getNome() + " usa ATAQUE FURIOSO!");
            this.furia -= 25;
            danoBase += 10;
        } else {
            System.out.println(this.getNome() + " ataca normalmente.");
        }

        short dano = (short) ((danoBase + dado) - defOponente);

        System.out.println(this.getNome() + " rolou " + dado + ". Ataque total: " + (danoBase + dado));
        if (dano < 0) dano = 0;
        return dano;
    }

    @Override
    public String toString() {

        String infoBase = super.toString();

        return infoBase +
                "\nFúria: " + this.furia + "/" + this.maxFuria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personagem thatBase = (Personagem) o;
        if (this.getLvl() != thatBase.getLvl()) return false;
        if (!this.getNome().equals(thatBase.getNome())) return false;


        Guerreiro guerreiro = (Guerreiro) o;
        return furia == guerreiro.furia &&
                maxFuria == guerreiro.maxFuria;
    }


    @Override
    public int hashCode() {

        return Objects.hash(this.getNome(), this.getLvl(), furia, maxFuria);
    }
}