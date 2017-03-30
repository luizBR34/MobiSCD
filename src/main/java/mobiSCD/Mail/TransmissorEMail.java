
package mobiSCD.Mail;

import javax.faces.application.FacesMessage;


public class TransmissorEMail {
    
    String nome_Usuario;
    String email_Usuario;
    String senha_Usuario;
    

    public TransmissorEMail(String nome_Usuario, String email_Usuario, String senha_Usuario) {
        
        this.nome_Usuario = nome_Usuario;
        this.email_Usuario = email_Usuario;
        this.senha_Usuario = senha_Usuario;
    }
    
    

    public String montarMensagem() {
        
        String message = "<head>";
        message += "<link type=text/css href=http://mobiscd-mobitronics.rhcloud.com/css/email.css rel=stylesheet>";
        message += "</head>";

        message += "<body>";

        message += "<table>";
        message += "<tbody>";
        message += "<tr>";
        message += "<td colspan=4><img src=http://mobiscd-mobitronics.rhcloud.com/images/logo.png width=237 height=56/></td>";
        message += "</tr>";
        message += "<tr>";
        message += "<td colspan=2><br/><br/>";
        message += "<h2><span>Sistema de Controle</span></h2>";
        message += "<h2><span>de Dispositivos</span></h2>";
        message += "</td>";
        message += "<td colspan=2>";
        message += "<h1><span><strong>Bem Vindo!</strong></span></h1>";
        message += "</td>";
        message += "</tr>";
        message += "<tr>";
        message += "<td colspan=4 rowspan=2>";
        message += "<h3>Ol&aacute; " + nome_Usuario + "!</h3>";
        message += "<p>Seu cadastro foi realizado com sucesso! <br> Abaixo seguem suas informa&ccedil;&otilde;es para logar no sistema: </p>";
        message += "<h3>E-Mail: " + email_Usuario + "</h3>";
        message += "<h3>Senha: " + senha_Usuario + "</h3>";
        message += "<p>Lembramos que a chave do dispositivo deve ser cadastrada para o uso completo de todas as funcionalidades dispon&iacute;veis.</p>";
        message += "<p>Atenciosamente <br> Equipe Mobitronics </p>";
        message += "<p class=copyright><span>Copyright &copy; 2016 Mobitronics. - Todos os direitos reservados</span></p>";
        message += "</td>";
        message += "</tr>";
        message += "</tbody>";
        message += "</table>";

        message += "</body>";
        
        return message;
    }
    
    
    public String mensagemRecuperacaoSenha() {
        
        String message = "<head>";
        message += "<style>";
        message += "body {";
        message += "background-image: url(\"http://mobiscd-mobitronics.rhcloud.com/images/cidade.jpg\");";
        message += "background-repeat: no-repeat;  background-position: right top; background-attachment: fixed;";
        message += "}";
        message += "</style>";
        message += "</head>";
        
        message += "<body>";

        message += "<table style=background-color: #f2f2f2; width: 50vw; height: 50vh; margin-left: auto; margin-right: auto; padding: 15px;>";
        message += "<tbody>";
        message += "<tr>";
        message += "<td colspan=4><img src=http://mobiscd-mobitronics.rhcloud.com/images/logo.png width=237 height=56/></td>";
        message += "</tr>";
        message += "<tr>";
        message += "<td colspan=2><br /><br/>";

        message += "<h2 style=font-family: Times, serif; font-size: 1.125em; font-weight: bold; color: darkblue; text-align: center; text-transform: uppercase; text-shadow: 0.1em 0.1em 0.2em rgba(83,83,110,0.4);>";
        message += "<span>Sistema de Controle</span></h2>";
        message += "<h2 style=font-family: Times, serif; font-size: 1.125em; font-weight: bold; color: darkblue; text-align: center; text-transform: uppercase; text-shadow: 0.1em 0.1em 0.2em rgba(83,83,110,0.4);>";
        message += "<span>de Dispositivos</span></h2>";
        message += "</td>";
        message += "<td colspan=2>";
        message += "<h2 style=font-size: 1.75em; font-weight: bold; text-align: center; color: #339966;>";
        message += "<span>Recupera&ccedil;&atilde;o de senha</span></h1>";
        message += "</td>";
        message += "</tr>";
        message += "<tr>";
        message += "<td colspan=4 rowspan=2>";
        message += "<h3 style=font-family: Arial, Helvetica, sans-serif; font-size: 0.9375em; font-weight: normal;>Ol&aacute;!</h3>";
        message += "<p>Abaixo segue a senha cadastrada para acesso ao sistema. </p>";
        message += "<h3 style=font-family: Arial, Helvetica, sans-serif; font-size: 0.9375em; font-weight: bold;>Senha: " + senha_Usuario + "</h3>";
        message += "<br>";
        message += "<p class=copyright><span>Copyright &copy; 2016 Mobitronix. - Todos os direitos reservados</span></p>";
        message += "</td>";
        message += "</tr>";
        message += "</tbody>";
        message += "</table>";
        
        message += "</body>";
        
        return message;
    }
    
    
    
    
    
    
    
    public void enviar() {
        
        String host = "smtp.mail.yahoo.com";
        String port = "587";
        String mailFrom = "mobitronix@yahoo.com";
        String password = "shouryuken83";
        
        String mailTo = email_Usuario;
        String subject, message;
        
            //Mandar e-mail de cadastro
            if (!(nome_Usuario.equals(""))) {
                
            subject = "Mobitronics - Bem Vindo!";
            message = montarMensagem();
                
            //Mandar e-mail de recuperação de senha
            } else {
                
            subject = "Mobitronics - Senha de acesso";
            message = mensagemRecuperacaoSenha();
            }
        
        UtilitarioEMail mailer = new UtilitarioEMail();
 
        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo, subject, message);
        } catch (Exception ex) {
            //msgERROR.setSummary("Não foi possível enviar o E-MAIL!");
            ex.printStackTrace();
        }       
    }

}
