package br.upf.ads.poo.classes;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Rkern
 */
public class ContaEspecial extends Conta{
    /* ATRIBUTOS */
    private float vl_limite;    

    public ContaEspecial(float vl_limite, int nrConta, float vlSaldo, String dsTitular) {
        super(nrConta, vlSaldo, dsTitular);
        this.vl_limite = vlSaldo + (vl_limite * Float.parseFloat("0.5"));
    }

    /* GETTERS AND SETTERS */
    public float getVl_limite() {
        return vl_limite;
    }

    public void setVl_limite(float vl_limite) {
        this.vl_limite = vl_limite;
    }
    
    /* MÉTODOS */
    /**
     * Realiza um saque da conta atual.
     * @param vl_saque 
     */
    @Override
    public void realizarSaque(float vl_saque) throws Exception
    {
        if (this.get_vl_saldo() < 0)
        {
            float vlSaqueNegativo = vl_saque * -1;
            vlSaqueNegativo       = this.get_vl_saldo() + vlSaqueNegativo;

            if ((vlSaqueNegativo * -1) <= this.getVl_limite())
                this.set_vl_saldo(vlSaqueNegativo);
            else
                throw new Exception("Limite Insuficiente para novos Saques!");
        }
        else if (vl_saque > this.get_vl_saldo())
        {
            float vlTemporarioSaque = (this.get_vl_saldo() - vl_saque) * -1;

            if (vlTemporarioSaque <= this.getVl_limite())
                this.set_vl_saldo(vlTemporarioSaque * -1);
            else
                throw new Exception("Limite Insuficiente para novos Saques!");
        }
        else
            this.set_vl_saldo(this.get_vl_saldo() - vl_saque);
        
        NumberFormat nf = NumberFormat.getInstance(new Locale("br", "PT"));
            
        System.out.println("Saque realizado com sucesso!");
        System.out.println("Saldo Atual: R$ " + nf.format(this.get_vl_saldo()));
    }
    
    /**
     * Realiza uma transferencia entre contas.
     * @param vl_transferencia
     * @param ContaDestino
     * @throws Exception 
     */
    @Override
    public void realizarTransferencia(float vl_transferencia, Conta ContaDestino) throws Exception
    {
        if (this.get_vl_saldo() < 0)
        {
            float vlTransfNegativo = vl_transferencia * -1;
            vlTransfNegativo       = this.get_vl_saldo() + vlTransfNegativo;

            if ((vlTransfNegativo * -1) <= this.getVl_limite())
                this.set_vl_saldo(vlTransfNegativo);
            else
                throw new Exception("Limite Insuficiente para Transferencias!");
        }
        else if (vl_transferencia > this.get_vl_saldo())
        {
            float vlTemporarioTransf = (this.get_vl_saldo() - vl_transferencia) * -1;

            if (vlTemporarioTransf <= this.getVl_limite())
                this.set_vl_saldo(vlTemporarioTransf * -1);
            else
                throw new Exception("Limite Insuficiente para Transferencias!");
        }
        else
            this.set_vl_saldo(this.get_vl_saldo() - vl_transferencia);

        ContaDestino.set_vl_saldo(vl_transferencia + ContaDestino.get_vl_saldo());

        System.out.println("Transferencia realizada com Sucesso!");
        System.out.println("FAVORECIDO: " + ContaDestino.get_ds_titular());
    }
}
