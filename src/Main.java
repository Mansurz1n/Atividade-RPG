import Personagens.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            TesteInventetarioEItem();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void TesteInventetarioEItem() throws Exception {


        Item adaga = new Item("A","Ataque 5","arma", (byte) 1,(byte)5);
        Item pocao = new Item("P","c5","cura", (byte) 5,(byte)5);
        Item superpotion = new Item("superpotion","cura 10","cura", (byte) 2,(byte)10);


        Inventario i = new Inventario();

        i.adicionarItem(pocao);

        i.adicionarItem(superpotion);

        i.adicionarItem(adaga);

        i.listarItensOrdenados();
        System.out.println("/");
        Item pocaoMais = new Item("P","c5","cura", (byte) 2,(byte)5);
        i.adicionarItem(pocaoMais);

        i.listarItensOrdenados();
        System.out.println("/");

        i.removerItem("p",(byte) 7);


        i.removerItem("A",(byte) 1);

        i.listarItensOrdenados();


    }

    public static void TestePersonagem()
    {

    }


}