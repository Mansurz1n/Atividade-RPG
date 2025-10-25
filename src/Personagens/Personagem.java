package Personagens;

import java.util.Random;

public abstract class Personagem {

    private String nome;
    private short hpAtual;
    private short hpMax;
    private short at;
    private short df;
    private short lvl;
    private Inventario inv;

    protected static final Random dado = new Random();

    public Personagem (String nome, short hp,short at,short df)throws Exception{
        if(nome == null) throw new Exception("Nome não pode ser nulo");
        this.lvl = 1;
        this.nome = nome;
        this.hpMax = hp;
        this.hpAtual = hp;
        this.at = at;
        this.df = df;
        this.inv = new Inventario();
    }


    public void setLevel (short lvl){
        this.lvl = lvl;
    }


    public void setNome (String nome)throws Exception{
        if(nome == null) throw new Exception("Nome não pode ser nulo");
        this.nome = nome;
    }

    public void setAt(short at) {
        this.at = at;
    }

    public void setDf(short df) {
        this.df = df;
    }

    public void setHpAtual(short hpAtual)throws Exception {
        if(hpAtual>this.hpMax)hpAtual =hpMax;
        else if (hpAtual < 0) this.hpAtual = 0;
        else this.hpAtual = hpAtual;
    }

    public void setHpMax(short hpMax) {
        this.hpMax = hpMax;
    }

    public void setInv(Inventario inv) {
        this.inv = inv;
    }

    public short getAt() {
        return this.at;
    }

    public short getLvl() {
        return this.lvl;
    }

    public String getNome() {
        return this.nome;
    }

    public short getHpAtual() {
        return this.hpAtual;
    }

    public Inventario getInv() {
        return this.inv;
    }

    public  void upeiDeNivel() throws Exception {
        this.lvl+=1;
        System.out.println("O nivel do " + this.nome +" Aumentou");
        System.out.println("Escolha uma das estatistica para aumentar");

        byte escolha = 0;
        boolean escolhaCerta = false;

        while(!escolhaCerta){
            System.out.println("1: Vida" + this.hpMax + " + 10 " + "\n 2: Ataque "+this.at +" +1" +  "\n 3: Defesa " + this.df + " +1.");
            System.out.println("Escolha: ");
            try {
                escolha = Teclado.getUmByte();

                switch (escolha) {
                    case 1: {
                        this.hpMax += 10;
                        System.out.println("Agora o Hp do personagem é de :" + this.hpMax);
                        escolhaCerta = true;
                        break;
                    }
                    case 2: {
                        this.at += 1;
                        System.out.println("Agora o ataque é igual a :" + this.at);
                        escolhaCerta = true;
                        break;
                    }
                    case 3: {
                        this.df += 1;
                        System.out.println("Agora a defesa é igual a :" + this.df);
                        escolhaCerta = true;
                        break;
                    }
                    default: {
                        System.out.println("Precisa Ser digitado os numeros : 1, 2 ou 3. Tente Novamente.");
                    }
                }
            } catch (Exception e) {
                throw new Exception("Erro " + e.getMessage() + "\nTente Novamente");
            }
        }
    }

    public boolean isTa_vivo() {
        return this.hpAtual > 0;
    }


    public void dano (short dano){
        if(dano>0){
            this.hpAtual -=dano;
            System.out.println("O " + this.nome + "levou " + dano + "de dano!");
            if(!isTa_vivo()){
                this.hpAtual = 0;
                System.out.println("O " + this.nome + " foi derrotado!" );
            }
        }else {
            System.out.println("O " +this.nome + " defendeu o Ataque!");
        }
    }

    public short calculoDano(short defOponente){

        short dado = (short)( this.dado.nextInt(20) + 1);

        short dano = (short) ((this.at + dado) - defOponente);

        System.out.println(this.nome + "rolou " + dado + " O  ataque dele é de " + (this.at+dado));
        if(dano<0) dano = 0;
        return dano ;
    }


    public void batalha( Personagem i ) throws Exception{
        System.out.println("\n============================================");
        System.out.println("           ⚔️ BATALHA INICIADA ⚔️");
        System.out.println("============================================");
        System.out.println(this.getNome() + " (HP: " + this.getHpAtual() + ") vs " + i.getNome() + " (HP: " + i.getHpAtual() + ")");
        System.out.println("--------------------------------------------");

        while(i.isTa_vivo() && this.isTa_vivo()){
            short ataque = calculoDano(i.df);
            i.dano(ataque);

            if(!i.isTa_vivo()){
                this.upeiDeNivel();
                break;
            }
            System.out.println("Vida Atual do " + i.getNome() + " é de " + i.getHpAtual()+".");

            ataque = calculoDano(this.df);
            this.dano(ataque);

            System.out.println("Vida Atual do " + this.getNome() + " é de " + this.getHpAtual()+".");
        }
        System.out.println("\n============================================");
        System.out.println("           ☠️ BATALHA TERMINADA ☠️");
        System.out.println("============================================");
    }



    @Override
    public String toString(){
        return "Nome: " + this.nome+
                " Level: " + this.lvl +
                "\nVida: " + this.hpAtual +"/" + this.hpMax +
                "\nAtaque: " + this.at +
                "\nDefesa: " + this.df +
                "\nInventario: " + this.inv.toString();

    }

    public Personagem (Personagem modelo)throws Exception{
        if(modelo == null)throw new Exception("O modelo não pode ser nulo");

        this.nome = modelo.nome;
        this.hpAtual = modelo.hpAtual;
        this.hpMax = modelo.hpMax;
        this.at = modelo.at;
        this.lvl = modelo.lvl;
        this.df = modelo.df;
        this.inv = new Inventario(modelo.inv);
    }

    @Override
    public abstract Object clone ();

}