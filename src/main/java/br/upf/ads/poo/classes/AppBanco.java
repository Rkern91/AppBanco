package br.upf.ads.poo.classes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Rkern
 */
public class AppBanco {
    /* Array para armazenamento de novas contas */
    public static ArrayList<Conta> ListaContas = new ArrayList();
    
    /**
     * Metodo principal da aplicacao.
     * @param args 
     */
    public static void main(String[] args)
    {
        while(true)
        {
            mostrarMenus("P");
            
            Scanner teclado = new Scanner(System.in);
            String  idOpcao = teclado.next().toUpperCase();
            
            switch (idOpcao)
            {
                case "N" -> criarNovasContas();
                case "A" -> acessarContas();
                case "L" -> listarContas();
                case "D" -> deletarContas();
                case "S" -> System.exit(0);
                default  -> System.out.println("Opcao invalida!");
            }
        }
    }
    
    /**
     * Cria uma nova Conta e guarda em uma lista de Contas.
     */
    public static void criarNovasContas()
    {
        boolean  idRepetir = true;
        
        do
        {
            Scanner  teclado       = new Scanner(System.in);
            String[] idOptionsMenu = {"C","E","P"};
            int      nrConta       = obtemNumeroConta();
            String   dsTitular     = "";
            float    vlSaldo       = 0;
            String   idOpcao;
            
            mostrarMenus("C");
            
            idOpcao = teclado.next().toUpperCase();
            
            if (idOpcao.equals("I"))
                idRepetir = false;
            else
            {
                try
                {
                    //Só executa uma vez o menu caso informado uma ação válida
                    //Se existir o valor, devolve um FALSO e não repete o laço
                    //Se não existir, devolve TRUE e roda mais uma vez as opções
                    idRepetir = !Arrays.stream(idOptionsMenu).anyMatch(idOpcao::equals);

                    System.out.println("INFORME OS DADOS");
                    teclado.nextLine();
                    
                    System.out.print("Nome Titular: ");
                    dsTitular = teclado.nextLine().toUpperCase();
                    dsTitular = Normalizer.normalize(dsTitular, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

                    System.out.print("Valor Deposito: ");
                    vlSaldo = teclado.nextFloat();
                }
                catch(Exception e)
                {
                     System.out.println("\n"); 
                     System.out.println("Por favor, insira corretamente os dados!");
                     System.out.println("NOMES: Apenas letras | VALORES: Numeros separados por virgula");
                     System.out.println("\n");
                }
                
                switch (idOpcao)
                {
                    case "C" -> {
                        System.out.println("Criando Conta Comum...");
                        ContaComum newContaComum = new ContaComum(nrConta, vlSaldo, dsTitular);
                        ListaContas.add(newContaComum);
                        System.out.println("Conta Comum Nr. " + nrConta + " criada com SUCESSO!");
                    }
                    case "E" -> {
                        System.out.println("Criando Conta Especial...");
                        ContaEspecial newContaEspecial = new ContaEspecial(vlSaldo, nrConta, vlSaldo, dsTitular);
                        ListaContas.add(newContaEspecial);
                        System.out.println("Conta Especial Nr. " + nrConta + " criada com SUCESSO!");
                        System.out.println("LIMITE ATUAL: R$ " + newContaEspecial.getVl_limite());
                    }
                    case "P" -> {
                        System.out.print("Informe o dia de vencimento: ");
                        int nrDiaVencimento = teclado.nextInt();
                        
                        System.out.println("Criando Conta Poupança...");
                        ContaPoupanca newContaPoupanca = new ContaPoupanca(nrDiaVencimento, nrConta, vlSaldo, dsTitular);
                        ListaContas.add(newContaPoupanca);
                        System.out.println("Conta Poupanca Nr. " + nrConta + " criada com SUCESSO!");
                    }
                    default -> System.out.println("Opcao invalida!");
                }
            }
        }
        while(idRepetir);
    }
    
    /**
     * Acessa uma conta criada e realiza uma operação.
     */
    public static void acessarContas()
    {
        if (!ListaContas.isEmpty())
        {
            try
            {
                System.out.println("ACESSO DE CONTA BANCÁRIA");
                
                String idAcao;
                int    nrContaAcesso;

                Scanner teclado = new Scanner(System.in);

                System.out.print("Informe o Numero da Conta: ");
                nrContaAcesso = teclado.nextInt();

                mostrarMenus("A");
                idAcao = teclado.next().toUpperCase();

                for (Conta accConta : ListaContas)
                {
                    if (accConta.get_nr_conta() == nrContaAcesso)
                    {
                        executarAcao(accConta, idAcao);
                        break;
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println("Nenhuma Conta Criada!");
    }
    
    /**
     * Lista todas as contas criadas.
     */
    public static void listarContas()
    {
        if (!ListaContas.isEmpty())
        {
            System.out.println("LISTAGEM DE CONTAS");
            
            for (Conta accCriada : ListaContas)
            {
                System.out.println("Nome: "      + accCriada.get_ds_titular() + "\n" +
                                   "Nr. Conta: " + accCriada.get_nr_conta()   + "\n" +
                                   "Saldo: R$ "  + accCriada.get_vl_saldo());
                System.out.println("--------------------");
            }
        }
        else
            System.out.println("Nenhuma Conta Criada!");
    }
    
    /**
     * Executa a acao informada pelo usuario.
     * @param ContaAcesso
     * @param idAcao
     * @throws Exception 
     */
    public static void executarAcao(Conta ContaAcesso, String idAcao) throws Exception
    {
        float   vlValor;
        int     nrContaDestino;
        Scanner teclado = new Scanner(System.in);
        
        switch(idAcao)
        {
            case "S" -> { 
                System.out.print("Valor Saque: ");
                vlValor = teclado.nextFloat();

                ContaAcesso.realizarSaque(vlValor);
            }
            case "D" -> {
                System.out.print("Valor Depósito: ");
                vlValor = teclado.nextFloat();

                ContaAcesso.realizarDeposito(vlValor);
            }
            case "T" -> {
                System.out.print("Valor a transferir: ");
                vlValor = teclado.nextFloat();

                System.out.print("Número Conta Destino: ");
                nrContaDestino = teclado.nextInt();
                
                if (ContaAcesso.get_nr_conta() != nrContaDestino)//Só para outra conta
                {
                    for (Conta accDestino : ListaContas)
                    {
                        if (accDestino.get_nr_conta() == nrContaDestino)
                        {
                            //Realiza transferencia e deduz o saldo da conta
                            ContaAcesso.realizarTransferencia(vlValor, accDestino);
                            break;
                        }
                    }
                }
                else
                    throw new Exception("Transferências somente entre contas distintas!");
            }
            default -> System.out.println("Ação Inválida!");
        }
    }
    
    /**
     * Remove uma conta informada atraves do Número da Conta.
     */
    public static void deletarContas()
    {
        //Verifica se a lista de contas esta vazia.
        if (!ListaContas.isEmpty())
        {
            int     nrContaRemover;
            boolean idEncontrado = false;
            Scanner teclado      = new Scanner(System.in);
            
            System.out.println("EXCLUSÃO DE CONTA BANCÁRIA");
            System.out.print("Informe o Nr. da Conta: ");
            nrContaRemover = teclado.nextInt();
            
            for (Conta contaRemover : ListaContas)
            {
                if (contaRemover.get_nr_conta() == nrContaRemover)
                {
                    ListaContas.remove(contaRemover);
                    System.out.println("Conta removida com SUCESSO!");
                    idEncontrado = true;
                    break;
                }
            }
            
            if (!idEncontrado)
                System.out.println("Conta não ENCONTRADA!");
        }
        else
            System.out.println("Nenhuma Conta Criada!");
    }
    
    /**
     * Exibe os menu de acordo com a opção informada.
     * @param idMenu 
     */
    public static void mostrarMenus(String idMenu)
    {
        switch(idMenu)
        {
            case "P" -> {
                System.out.println("MENU INICIAL");
                System.out.println("[N] - Nova Conta");
                System.out.println("[A] - Acessar Conta");
                System.out.println("[L] - Listar Contas");
                System.out.println("[D] - Deletar Conta");
                System.out.println("[S] - Sair");
                System.out.print("Opção: ");
            }
            case "C" -> {
                System.out.println("TIPOS DE CONTAS");
                System.out.println("[C] - Conta Comum");
                System.out.println("[E] - Conta Especial");
                System.out.println("[P] - Conta Poupança");
                System.out.println("[I] - Voltar p/ Inicio");
                System.out.print("Opção: ");
            }
            case "A" -> {
                System.out.println("AÇÕES DISPONíVEIS");
                System.out.println("[S] - SACAR");
                System.out.println("[D] - DEPOSITAR");
                System.out.println("[T] - TRANSFERIR");
                System.out.print("Opção: ");
            }
        }
    }
    
    /**
     * Obtem um novo numero para conta sendo criada.
     * @return int
     */
    public static int obtemNumeroConta()
    {
        if (!ListaContas.isEmpty())
            return geradorRandomico();
        
        int     nrContaNovo;
        boolean repeteBusca = false;
        
        do
        {
             nrContaNovo = geradorRandomico();
             
             for (Conta accCriada : ListaContas)
             {
                 if (accCriada.get_nr_conta() == nrContaNovo)
                 {
                     repeteBusca = true;
                     break;
                 }
             }
                 
        }
        while(repeteBusca);
        
        return nrContaNovo;
    }
    
    /**
     * Gera um numero de conta aleatorio.
     * @return int
     */
    public static int geradorRandomico()
    {
      int min = 1;
      int max = 100;
        
      //Valores Randomicos entre 1 e 100 para a numeração das contas
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
