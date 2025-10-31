package Personagens;

import java.util.Objects;


public class Mago extends Personagem {


    private short pontosDeMagia;
    private short maxPontosDeMagia;


    public Mago(String nome) throws Exception {

        super(nome, (short) 80, (short) 30, (short) 5);


        this.maxPontosDeMagia = 50;
        this.pontosDeMagia = 50;
    }


    public Mago(Mago modelo) throws Exception {

        super(modelo);


        this.pontosDeMagia = modelo.pontosDeMagia;
        this.maxPontosDeMagia = modelo.maxPontosDeMagia;
    }


    @Override
    public Object clone() {
        Mago clone = null;
        try {

            clone = new Mago(this);
        } catch (Exception e) {

            System.err.println("Erro fatal ao clonar Mago: " + e.getMessage());

        }
        return clone;
    }


    @Override
    public short calculoDano(short defOponente) {

        short dado = (short) (this.dado.nextInt(20) + 1);
        short dano;


        if (this.pontosDeMagia >= 10) {
            this.pontosDeMagia -= 10;
            System.out.println(this.getNome() + " lança BOLA DE FOGO!");


            short danoMagico = (short) (this.getAt() * 1.5);
            dano = (short) ((danoMagico + dado) - (defOponente / 2));
            System.out.println(this.getNome() + " rolou " + dado + ". Ataque total: " + (danoMagico + dado));

        } else {

            System.out.println(this.getNome() + " ataca com o cajado...");
            short danoFisico = (short) (this.getAt() / 2);
            dano = (short) ((danoFisico + dado) - defOponente);
            System.out.println(this.getNome() + " rolou " + dado + ". Ataque total: " + (danoFisico + dado));
        }

        if (dano < 0) dano = 0;
        return dano;
    }


    public void regenerarMagia() {
        if (this.pontosDeMagia < this.maxPontosDeMagia) {
            this.pontosDeMagia += 5;
            if (this.pontosDeMagia > this.maxPontosDeMagia) {
                this.pontosDeMagia = this.maxPontosDeMagia;
            }
        }
    }



    @Override
    public String toString() {

        String infoBase = super.toString();

        return infoBase +
                "\nMagia: " + this.pontosDeMagia + "/" + this.maxPontosDeMagia;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Personagem thatBase = (Personagem) o;
        if (this.getLvl() != thatBase.getLvl()) return false;
        if (!this.getNome().equals(thatBase.getNome())) return false;

        Mago mago = (Mago) o;
        return pontosDeMagia == mago.pontosDeMagia &&
                maxPontosDeMagia == mago.maxPontosDeMagia;
    }


    @Override
    public int hashCode() {

        int resultado = Objects.hash(this.getNome(), this.getLvl(), pontosDeMagia, maxPontosDeMagia);
        return resultado;
    }



    public short getPontosDeMagia() {
        return pontosDeMagia;
    }

    public void setPontosDeMagia(short pontosDeMagia) {
        if (pontosDeMagia < 0) {
            this.pontosDeMagia = 0;
        } else if (pontosDeMagia > this.maxPontosDeMagia) {
            this.pontosDeMagia = this.maxPontosDeMagia;
        } else {
            this.pontosDeMagia = pontosDeMagia;
        }
    }

    public short getMaxPontosDeMagia() {
        return maxPontosDeMagia;
    }
}