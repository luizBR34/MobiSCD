
package mobiSCD.International;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {

   private static final long serialVersionUID = 1L;
   private String locale;

   private static Map<String,Object> countries;
   private static final Locale localAtual = new Locale("pt", "BR");   

   static{
      countries = new LinkedHashMap<String,Object>();
      countries.put("Portugues", localAtual);
      countries.put("English", Locale.ENGLISH);
   }
   

   public Map<String, Object> getCountries() {
      return countries;
   }

   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }



   
   public void alterarIdioma(ActionEvent event) {
       
       if (event.getComponent().getId().equals("Brasil")) {
           
           FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) countries.get("Portugues"));
           
       } else {
           
           FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) countries.get("English"));
       }
   }   
}
