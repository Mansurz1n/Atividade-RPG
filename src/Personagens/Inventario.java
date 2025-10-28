package Personagens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario implements Cloneable {
    private List<Item> itens;




    public Inventario() {
        this.itens = new ArrayList<Item>();
    }
    public List<Item> getItems() {
        return this.itens;
    }




    public void adicionarItem(Item addI)
    {
        if(addI == null) return;

        for(Item Existente:this.itens){
            if(Existente.equals(addI)){
                Existente.PegarItems(addI.getQuant());
                return;
            }
        }
        this.itens.add(addI);
    }

    public void listarItensOrdenados()throws Exception{
        if(this.itens.isEmpty())throw new Exception("Não tem item nenhum nesse exato momento");
        Collections.sort(this.itens);
        for(Item i : this.itens){
            System.out.println(i.toString());
        }
    }


    public Item buscarItem(String nome)throws Exception{
        if(this.itens.isEmpty())throw new Exception("Não tem nenhum item no Inventario para-se buscar");
        for (Item item: this.itens){
            //equalsIgnoreCase() compara se é igual ignorando letras maisculas/minusculas
            if(item.getNome_item().equalsIgnoreCase(nome)) return item;
        }
        return null;
    }

    //Função que remove o item do inventario
    public void removerItem(String nome, byte quantRemover)throws Exception{
        Item i = this.buscarItem(nome);
        if(i!=null) {
            try {
                if (i.getQuant() > quantRemover) {
                    i.setQuant((byte) (i.getQuant() - quantRemover));
                    // sobrou item ainda
                } else if (i.getQuant() == quantRemover) {
                    this.itens.remove(i);
                    //remove a o item do negocio pois ele quis remover a quantidade certa que ele tinha
                } else {
                    throw new Exception("Não possui" + quantRemover + " de " + i.getNome_item());
                }
            } catch (Exception e) {
                throw new Exception("Erro ao utilizar o remover Item: " + e.getMessage());
            }
        }else throw new Exception("Item " + nome + " não encontrado no inventario");
    }




    public Inventario(Inventario modelo)throws Exception{
        if(modelo == null)throw new Exception("Não tem Modelo de Inventario");


        this.itens = new ArrayList<Item>();

        for(Item itemModelo : modelo.itens){
            this.itens.add((Item) itemModelo.clone());
        }
    }

    @Override
    protected Object clone() {
        Inventario i = null;
        try {
            i = (Inventario) super.clone();
            i.itens = new ArrayList<Item>();
            for(Item itemO : this.itens){
                Item itemClonado = (Item) itemO.clone();
                i.itens.add(itemClonado);
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if(o==this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Inventario that = (Inventario) o;
        if(!that.itens.equals(this.itens))return false;
        return true;
    }

    @Override
    public String toString(){
        if(this.itens.isEmpty()) return "Iventario Vazio";
        return "Inventario " + this.itens.toString();
    }

    @Override
    public int hashCode() {
        int ret = 1;
        for (Item i: this.itens){
            ret =ret * 2 + i.hashCode();
        }
        return ret;
    }
}
