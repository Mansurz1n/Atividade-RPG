import Personagens.*;
import java.util.ArrayList;


public class Main {

    private static Personagem jogador;
    private static int inimigosDerrotados = 0;
    private static boolean jogoAtivo = true;


    public static void main(String[] args) {
        try {
            iniciarJogo();
        } catch (Exception e) {
            System.err.println("\n!! ERRO FATAL NO JOGO !!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static void iniciarJogo() throws Exception {
        System.out.println("==========================================");
        System.out.println("  BEM-VINDO AO RPG MALIGNO");
        System.out.println("==========================================");


        System.out.println("\n--- A Lenda do Orbe da OOP ---");
        System.out.println("Você é um herói que esta numa missão de recuperar o Orbe da OOP das mãos da terrível Ordem Maligna.");
        System.out.println("Nessa aventura você enfrentará diversos desafios...");
        System.out.println("O Orbe, que mantém o equilíbrio da Programação Orientada a Objetos, foi roubado.");
        System.out.println("Sem ele, o mundo corre o risco de cair no caos do código de Antas.");
        System.out.println("Sua missão é invadir a fortaleza da Ordem, derrotar seus campeões e enfrentar o líder, Maligno,");
        System.out.println("para restaurar a paz e os bons padrões de projeto.");
        System.out.println("----------------------------------------");
        pressioneEnterParaContinuar();


        jogador = criarPersonagem();

        if (jogador == null) {
            System.out.println("Criação de personagem cancelada. Fim de jogo.");
            return;
        }

        System.out.println("\n--- JOGO INICIADO ---");
        System.out.println("Seu herói foi criado!");
        System.out.println(jogador.toString());


        loopPrincipal();

        System.out.println("\n--- FIM DE JOGO ---");
        System.out.println("Obrigado por jogar!");
    }


    public static Personagem criarPersonagem() throws Exception {
        System.out.println("\n--- CRIAÇÃO DE PERSONAGEM ---");
        System.out.print("Digite o nome do seu herói: ");
        String nome = Teclado.getUmString();

        while (true) {
            System.out.println("\nEscolha sua classe:");
            System.out.println("1: Guerreiro (HP 150, AT 15, DEF 15)");
            System.out.println("2: Mago (HP 80, AT 30, DEF 5)");
            System.out.println("3: Arqueiro (HP 90, AT 28, DEF 8)");
            System.out.print("Opção: ");

            byte escolha = Teclado.getUmByte();

            switch (escolha) {
                case 1:
                    return new Guerreiro(nome);
                case 2:
                    return new Mago(nome);
                case 3:
                    return new Arqueiro(nome);
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }


    public static void loopPrincipal() throws Exception {
        while (jogoAtivo && jogador.isTa_vivo()) {
            System.out.println("\n--- O que você deseja fazer? ---");
            System.out.println("1: Explorar a masmorra");
            System.out.println("2: Usar item do inventário");
            System.out.println("3: Ver status e inventário");
            System.out.println("4: Sair do Jogo");
            System.out.print("Opção: ");

            byte escolha = Teclado.getUmByte();

            switch (escolha) {
                case 1:
                    explorar();
                    break;
                case 2:

                    usarItemMenuPrincipal();
                    break;
                case 3:
                    System.out.println("\n--- STATUS ATUAL ---");
                    System.out.println(jogador.toString());
                    break;
                case 4:
                    System.out.println("Você decide descansar por enquanto...");
                    jogoAtivo = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        if (!jogador.isTa_vivo()) {
            System.out.println("\n=================================");
            System.out.println("    VOCÊ FOI DERROTADO. GAME OVER.");
            System.out.println("=================================");
        }
    }


    public static void explorar() throws Exception {
        if (inimigosDerrotados == 9) {
            encontroChefe();
        } else {
            encontroComum();
        }
    }


    public static void encontroComum() throws Exception {
        System.out.println("\nVocê avança pela fortaleza... Um inimigo aparece!");
        Inimigo inimigo = null;
        Item loot = null;

        int tipoInimigo = Personagem.dado.nextInt(3);

        switch (tipoInimigo) {
            case 0:
                inimigo = new Inimigo("Criatura do Mal", (short) 40, (short) 15, (short) 3, false); //
                loot = new Item("Adaga Enferrujada", "Ataque 3", "arma", (byte) 1, (byte) 3); //
                break;
            case 1:
                inimigo = new Inimigo("Bispo Malevolente", (short) 60, (short) 10, (short) 8, false);
                loot = new Item("Pocao de Cura", "Cura 20HP", "cura", (byte) 1, (byte) 20);
                break;
            case 2:
                inimigo = new Inimigo("Cavaleiro Negro", (short) 80, (short) 12, (short) 15, false);
                loot = new Item("Pocao Grande", "Cura 50HP", "cura", (byte) 1, (byte) 50);
                break;
        }

        if (loot != null) {
            inimigo.getInv().adicionarItem(loot);
        }


        iniciarBatalha(inimigo);


        if (jogador.isTa_vivo() && !inimigo.isTa_vivo()) {
            System.out.println("Você venceu o " + inimigo.getNome() + "!");
            inimigosDerrotados++;
            System.out.println("Inimigos derrotados para chegar ao chefe: " + inimigosDerrotados + "/10");


            jogador.upeiDeNivel();
            jogador.restaurarVida();
            System.out.println(jogador.getNome() + " teve sua vida restaurada após a vitória!");

            saque(inimigo);
        }
    }


    public static void encontroChefe() throws Exception {
        System.out.println("\n========================================");
        System.out.println("  A SALA DO TRONO");
        System.out.println("========================================");

        System.out.println("MALIGNO: 'Você perecerá aqui, maliguininho! Sinta o poder do ditado de Java infinito!!'");
        pressioneEnterParaContinuar();

        Inimigo maligno = new Inimigo("Maligno", (short) 666, (short) 40, (short) 20, true);


        iniciarBatalha(maligno);


        if (jogador.isTa_vivo() && !maligno.isTa_vivo()) {

            System.out.println("\n========================================");
            System.out.println("  VITÓRIA!");
            System.out.println("========================================");
            System.out.println("Maligno cai, seu código se desfazendo em erros de compilação.");
            System.out.println("Você pega o Orbe da OOP!");
            System.out.println("Você recuperou o orbe e restaurou a paz no mundo da programação.");
            System.out.println("Obrigado por jogar!");

            jogoAtivo = false;
        }
    }


    public static void iniciarBatalha(Inimigo inimigo) throws Exception {
        System.out.println("\n============================================");
        System.out.println("           ⚔️ BATALHA INICIADA ⚔️");
        System.out.println("============================================");
        System.out.println(jogador.getNome() + " (HP: " + jogador.getHpAtual() + ") vs " + inimigo.getNome() + " (HP: " + inimigo.getHpAtual() + ")"); //
        System.out.println("--------------------------------------------");

        boolean emBatalha = true;

        while (emBatalha && inimigo.isTa_vivo() && jogador.isTa_vivo()) {


            System.out.println("\n--- Turno do " + jogador.getNome() + " ---");
            System.out.println("HP: " + jogador.getHpAtual() + "/" + jogador.getHpMax());
            System.out.println("O que fazer?");
            System.out.println("1: Atacar");
            System.out.println("2: Usar Item");
            System.out.println("3: Fugir");
            System.out.print("Opção: ");

            byte escolha = Teclado.getUmByte();

            switch (escolha) {
                case 1:

                    short ataqueJogador = jogador.calculoDano(inimigo.getDf());
                    inimigo.dano(ataqueJogador);
                    break;
                case 2:

                    usarItemEmBatalha();
                    break;
                case 3:
                    if (inimigo.isBoss()) {
                        System.out.println("Você não pode fugir de um Chefe!");
                    } else {
                        System.out.println("Você tenta fugir...");
                        int dadoFuga = Personagem.dado.nextInt(20) + 1;
                        if (dadoFuga > 10) {
                            System.out.println("Você escapou com sucesso!");
                            emBatalha = false;
                        } else {
                            System.out.println("A fuga falhou! Você perde o turno.");
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Você hesita e perde o turno.");
            }

            if (!emBatalha) {
                break;
            }

            if (!inimigo.isTa_vivo()) {
                break;
            }


            System.out.println("\n--- Turno do " + inimigo.getNome() + " ---");
            short ataqueInimigo = inimigo.calculoDano(jogador.getDf());
            jogador.dano(ataqueInimigo);

            System.out.println("HP atual do " + jogador.getNome() + ": " + jogador.getHpAtual());

            if (!jogador.isTa_vivo()) {
                break;
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("           ☠️ BATALHA TERMINADA ☠️");
        System.out.println("============================================");
    }



    public static void usarItemMenuPrincipal() throws Exception {
        Inventario inv = jogador.getInv();
        System.out.println("\n--- INVENTÁRIO (Modo Livre) ---");

        if (inv.getItems().isEmpty()) {
            System.out.println("Seu inventário está vazio.");
            return;
        }

        inv.listarItensOrdenados();
        System.out.print("Digite o NOME do item que deseja usar (ou 'cancelar'): ");
        String nomeItem = Teclado.getUmString();

        if (nomeItem.equalsIgnoreCase("cancelar")) {
            System.out.println("Uso de item cancelado.");
            return;
        }

        Item item = inv.buscarItem(nomeItem);

        if (item == null) {
            System.out.println("Item não encontrado no inventário.");
            return;
        }


        switch (item.getEfeito().toLowerCase()) {
            case "cura":
                byte poderCura = item.getPoder();
                jogador.curarVida(poderCura);
                inv.removerItem(item.getNome_item(), (byte) 1);
                break;
            case "arma":
                System.out.println("Você admira sua arma. (Lógica de 'equipar' não implementada).");
                break;
            case "flechas":
                System.out.println("Você conta suas flechas. (Use isso em batalha ou com um Arqueiro).");
                break;
            default:
                System.out.println("Você não sabe como usar este item agora.");
        }
    }


    public static boolean usarItemEmBatalha() throws Exception {
        Inventario inv = jogador.getInv();
        System.out.println("\n--- INVENTÁRIO (Batalha) ---");

        if (inv.getItems().isEmpty()) {
            System.out.println("Seu inventário está vazio. Você perde o turno!");
            return true;
        }

        inv.listarItensOrdenados();
        System.out.print("Digite o NOME do item para usar (ou 'cancelar'): ");
        String nomeItem = Teclado.getUmString();

        if (nomeItem.equalsIgnoreCase("cancelar")) {
            System.out.println("Uso de item cancelado. Você não gasta o turno.");
            return false;
        }

        Item item = inv.buscarItem(nomeItem);

        if (item == null) {
            System.out.println("Item não encontrado. Você se atrapalha e perde o turno!");
            return true;
        }


        switch (item.getEfeito().toLowerCase()) {
            case "cura":
                byte poderCura = item.getPoder();
                jogador.curarVida(poderCura);
                inv.removerItem(item.getNome_item(), (byte) 1);
                return true;

            case "flechas":
                if (jogador instanceof Arqueiro) {
                    ((Arqueiro) jogador).adicionarFlechas(item.getPoder());
                    inv.removerItem(item.getNome_item(), (byte) 1);
                    return true;
                } else {
                    System.out.println("Você não é um Arqueiro! Você perde o turno.");
                    return true;
                }

            case "arma":
                System.out.println("Não pode equipar uma arma no meio da batalha! Você perde o turno.");
                return true;

            default:
                System.out.println("Este item não pode ser usado em batalha! Você perde o turno.");
                return true;
        }
    }



    public static void saque(Inimigo inimigo) throws Exception {
        System.out.println("Você vasculha o corpo do " + inimigo.getNome() + "...");
        Inventario invInimigo = inimigo.getInv();

        if (invInimigo.getItems().isEmpty()) {
            System.out.println("Não havia nada de valor.");
            return;
        }

        Inventario invJogador = jogador.getInv();

        for (Item itemInimigo : new ArrayList<>(invInimigo.getItems())) {
            Item itemParaSaquear = new Item(itemInimigo);
            itemParaSaquear.setQuant(itemInimigo.getQuant());

            System.out.println("Você encontrou: " + itemParaSaquear.getNome_item() + " (x" + itemParaSaquear.getQuant() + ")");
            invJogador.adicionarItem(itemParaSaquear);
        }
    }


    private static void pressioneEnterParaContinuar() {
        System.out.println("\n(Pressione Enter para continuar...)");
        try {
            Teclado.getUmString();
        } catch (Exception e) {

        }
    }
}