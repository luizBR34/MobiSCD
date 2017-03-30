package mobiSCD.Beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Aparelho implements Serializable {
    
    public String canal_ID;
    public String apr_Nome;
    public int consumo;
    public String data;
    public String estado;
    public boolean estado_Aparelho = false; //Flag usada pelo botão na tele principal que identifica o estado do aparelho. false = desligado / true = ligado.
    
   
    public Aparelho() {
        
        GregorianCalendar calendario = new GregorianCalendar();
        String dia = String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
        String ano = String.valueOf(calendario.get(Calendar.YEAR));
        
        canal_ID = "";
        apr_Nome = "";
        consumo = 0;
        data = (dia + " / " + mes + " / " + ano);
        estado = "Desligado";
    }
    

    public Aparelho(String canal_ID, String apr_Nome, int consumo, String data, String estado) {
        
        this.canal_ID = canal_ID;
        this.apr_Nome = apr_Nome;
        this.consumo = consumo;
        this.data = data;
        this.estado = estado;
    }
    
    
    

    public String getCanal_ID() {
        return canal_ID;
    }

    public void setCanal_ID(String canal_ID) {
        this.canal_ID = canal_ID;
    }
    
    
    public String getApr_Nome() {
        return apr_Nome;
    }

    public void setApr_Nome(String apr_Nome) {
        this.apr_Nome = apr_Nome;
    }
    
    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEstado_Aparelho() {
        return estado_Aparelho;
    }

    public void setEstado_Aparelho(boolean estado_Aparelho) {
        this.estado_Aparelho = estado_Aparelho;
    }
    
}
