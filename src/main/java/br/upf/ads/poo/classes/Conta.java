package br.upf.ads.poo.classes;

/**
 * @author Rkern
 */
public abstract class Conta {
    /* ATRIBUTOS */
    private int    nr_conta;
    private float  vl_saldo;
    private String ds_titular;

    public Conta (int nrConta, float vlSaldo, String dsTitular)
    {
        this.ds_titular = dsTitular;
        this.nr_conta   = nrConta;
        this.vl_saldo   = vlSaldo;
    }
    
    /* GETTERS AND SETTERS */
    public int get_nr_conta() 
    {
        return nr_conta;
    }
    
    public void set_nr_conta(int nr_conta) 
    {
        this.nr_conta = nr_conta;
    }
    
    public float get_vl_saldo() 
    {
        return vl_saldo;
    }
    
    public void set_vl_saldo(float vl_saldo) 
    {
        this.vl_saldo = vl_saldo;
    }
    
    public String get_ds_titular() 
    {
        return ds_titular;
    }
    
    public void set_ds_titular(String ds_titular) 
    {
        this.ds_titular = ds_titular;
    }
    
    /* METODOS DA CLASSE */
    
    /**
     * Realiza um deposito na conta atual.
     * @param vl_deposito 
     */
    public void realizarDeposito(float vl_deposito)
    {
        this.set_vl_saldo(this.get_vl_saldo() + vl_deposito);
    }
    
    /**
     * Realiza um deposito na conta atual com envelope.
     * @param vl_deposito 
     * @param nr_envelope
     */
    public void realizarDeposito(float vl_deposito, int nr_envelope)
    {
        this.set_vl_saldo(this.get_vl_saldo() + vl_deposito);
    }
    
    /**
     * Realiza um saque da conta atual.
     * @param vl_saque 
     * @throws java.lang.Exception 
     */
    public void realizarSaque(float vl_saque) throws Exception
    {
        if (this.get_vl_saldo() < vl_saque)
            throw new Exception("Saldo insuficiente!");
        else
        {
            this.set_vl_saldo(this.get_vl_saldo() - vl_saque);

            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo Atual: R$ " + this.get_vl_saldo());
        }
    }
    
    public abstract void realizarTransferencia(float vl_transferencia, Conta ContaDestino) throws Exception;
}
