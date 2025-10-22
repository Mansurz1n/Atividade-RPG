public class Item {

    private String nome_item;
    private String desc;
    private String efeito;
    private byte quant;


    public Item(String nome,String desc, String efeito, byte quantidade) {
        this.nome_item = nome;
        this.desc = desc;
        this.efeito = efeito;
        this.quant = quantidade;
    }

}
