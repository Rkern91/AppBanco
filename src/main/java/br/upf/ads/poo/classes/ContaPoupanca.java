package br.upf.ads.poo.classes;

/**
 * @author Rkern
 */
public class ContaPoupanca extends Conta {
    /* ATRIBUTOS */
    private int nr_dia_vencimento;

    public ContaPoupanca(int nr_dia_vencimento, int nrConta, float vlSaldo, String dsTitular) {
        super(nrConta, vlSaldo, dsTitular);
        this.nr_dia_vencimento = nr_dia_vencimento;
    }

    /* MÉTODOS */
    /**
     * Realiza uma transferencia entre contas.
     * @param vl_transferencia
     * @param ContaDestino
     * @throws Exception 
     */
    @Override
    public void realizarTransferencia(float vl_transferencia, Conta ContaDestino) throws Exception
    {
        throw new Exception("Operação não suportada para Poupança!");
    }
}
