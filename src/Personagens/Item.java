package Personagens;

public class Item implements Comparable<Item>,Cloneable {

    private String nome_item;
    private String desc;
    private String efeito;
    private byte quant;
    private byte poder;


    public Item(String nome,String desc, String efeito, byte quantidade, byte p) throws Exception {
        if(nome==null || efeito==null)throw new Exception("O item precisa conter todos os atributos");
        if(quantidade<=0) throw new Exception("Precisa ter pelo menos 1 desse item");
        this.nome_item = nome;
        this.desc = desc;
        this.efeito = efeito;
        this.quant = quantidade;
        this.poder = p;
    }

    public String getNome_item() {
        return this.nome_item;
    }

    public String getDesc() {
        return this.desc;
    }

    public byte getQuant() {
        return this.quant;
    }

    public String getEfeito() {
        return this.efeito;
    }

    public byte getPoder() { return poder; }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEfeito(String efeito)throws Exception {
        if(efeito == null) throw new Exception("Ta faltando um efeito no seu item");
        this.efeito = efeito;
    }

    public void setNome_item(String nome_item)throws Exception {
        if(nome_item==null)throw new Exception("Precisa de um nome");
        this.nome_item = nome_item;
    }
    public void setQuant(byte quant)throws Exception {
        if(quant<0) throw new Exception("Precisa ter pelo menos 1 desse item");
        this.quant = quant;
    }

    public void setPoder(byte poder) { this.poder = poder; }

    public void PegarItems(byte add)
    {
        if (add>0)this.quant+=add;
    }


    public void usar(){
        if (this.quant>0){
            this.quant --;
            System.out.println("Usou " + this.nome_item + " Restam:" + this.quant);
            return;
        }else {
            System.out.println("Não possui mais esse item");
        }
    }

    @Override
    public String toString(){
        return this.nome_item + " " +this.quant + "\n"
                + this.efeito + " " + this.poder + "\n" +
                this.desc + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null)return false;
        if(this.getClass()!=obj.getClass())return false;

        Item i = (Item) obj;

        if(!i.nome_item.equals(this.nome_item) ||!i.desc.equals(this.desc) || !i.efeito.equals(this.efeito) || i.poder!=this.poder)return false;

        return true;

    }


    @Override
    public int hashCode() {
        int ret = 1;

        ret = ret *2 + ((Byte)this.quant).hashCode();
        ret = ret * 2 + this.nome_item.hashCode();
        ret = ret * 2 + this.efeito.hashCode();
        ret = ret * 2 + this.desc.hashCode();
        ret = ret* 2 + ((Byte)this.poder).hashCode();
        if(ret<0) ret = -ret;

        return ret;
    }

    @Override
    public int compareTo(Item o) {
        if (this.poder<o.poder) return  -666;
        if (this.poder>o.poder) return 666;
        return 0;
    }

    public Item(Item i)throws Exception
    {
        if(i==null) throw new Exception("Ta faltando o item");

        this.nome_item=i.nome_item;
        this.desc=i.desc;
        this.efeito=i.efeito;
        this.quant=i.quant;
        this.poder = i.poder;
    }

    @Override
    public Object clone()
    {
        Item clone = null;
        try{
            clone=new Item(this);
        }catch (Exception e){}

        return clone;
    }


}
