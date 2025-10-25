package Personagens;

public class Arqueiro extends Personagem{
    public Arqueiro(String nome) throws Exception {
        super(nome, (short) 100, (short) 25,(short) 10);
    }


    @Override
    public Object clone() {
        return null;
    }
}
