package br.upf.ads.poo.classes;

/**
 * @author Rkern
 */
public class ContaComum extends Conta {

    public ContaComum(int nrConta, float vlSaldo, String dsTitular)
    {
        super(nrConta, vlSaldo, dsTitular);
    }

    /**
     * Realiza uma transferencia entre contas.
     * @param vl_transferencia
     * @param ContaDestino 
     * @throws java.lang.Exception 
     */
    @Override
    public void realizarTransferencia(float vl_transferencia, Conta ContaDestino) throws Exception
    {
        if (this.get_vl_saldo() >= vl_transferencia)
        {
            this.set_vl_saldo(this.get_vl_saldo() - vl_transferencia);
            
            ContaDestino.set_vl_saldo(vl_transferencia + ContaDestino.get_vl_saldo());
            
            System.out.println("Transferencia realizada com Sucesso!");
            System.out.println("FAVORECIDO: " + ContaDestino.get_ds_titular());
        }
        else
            throw new Exception("Saldo Insuficiente!");
    }
}
