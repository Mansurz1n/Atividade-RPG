package Personagens;

public class Inimigo extends Personagem  {


    public Inimigo(String nome) throws Exception {
        super(nome,(short) 100, (short) 5, (short)10);
    }


    @Override
    public Object clone() {
        return null;
    }
}
