package mobiSCD.Beans;

import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import mobiSCD.Mail.TransmissorEMail;
import mobiSCD.Model.DataManager;

@ManagedBean(name="cliente")
@SessionScoped
public class Cliente implements Serializable {
    
    
        private String nome;
        
        private String estado;
        private final String[] estados_Nomes = new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", 
                                                "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", 
                                                "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", 
                                                "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" };
        
        private String cidade;
                
        private String endereco;
        private String tel_DDD;
        private String telefone;
        private String email;
        private String senha;
        private String repetir_Senha;
        private String interruptor_ID;
        private String TEMP_interruptor_ID; //usado apenas para controle de exibição dos componentes da GUI
        
        private int interruptor_Quant_Canais;

        private boolean flag_Chave_Interruptor = false; //Flag referente a Chave do Interruptor
        private boolean flag_Nomes_Aparelhos = false; //Flag referente aos nomes dos Aparelhos.
        private boolean flag_Tabela = false; //Flag que exibe a tabela de informações
        
        private boolean flag_Requerido = false; //Flag do input text de usuario e senha pra evitar de logar com os campos em branco.

        private Aparelho[] dispositivos;
        
        
        public enum Navegacao {

            principal, index, cadastro, recuperacao, resultado
        };
        
        
        public boolean login_Correto = false;
        
        
        public DataManager gerenciadorBanco;
        public FacesMessage mensagem;
        
        
        
    public Cliente() {
        
        mensagem = new FacesMessage("");

        gerenciadorBanco = new DataManager(mensagem);
    }
        

        
        
    public boolean isFlag_Chave_Interruptor() {
        return flag_Chave_Interruptor;
    }

    public void setFlag_Chave_Interruptor(boolean flag_Chave_Interruptor) {
        this.flag_Chave_Interruptor = flag_Chave_Interruptor;
    }

    public boolean isFlag_Nomes_Aparelhos() {
        return flag_Nomes_Aparelhos;
    }

    public void setFlag_Nomes_Aparelhos(boolean flag_Nomes_Aparelhos) {
        this.flag_Nomes_Aparelhos = flag_Nomes_Aparelhos;
    }

    public boolean isFlag_Tabela() {
        return flag_Tabela;
    }

    public void setFlag_Tabela(boolean flag_Tabela) {
        this.flag_Tabela = flag_Tabela;
    }
    

    public boolean isFlag_Requerido() {
        return flag_Requerido;
    }

    public void setFlag_Requerido(boolean flag_Requerido) {
        this.flag_Requerido = flag_Requerido;
    }
    
    
    
    public String getInterruptor_ID() {
        return interruptor_ID;
    }

    public void setInterruptor_ID(String interruptor_ID) {
        this.interruptor_ID = interruptor_ID;
    }
    


    public String getTEMP_interruptor_ID() {
        return TEMP_interruptor_ID;
    }

    public void setTEMP_interruptor_ID(String TEMP_interruptor_ID) {
        this.TEMP_interruptor_ID = TEMP_interruptor_ID;
    }

    
    public int getInterruptor_Quant_Canais() {
        return interruptor_Quant_Canais;
    }

    public void setInterruptor_Quant_Canais(int interruptor_Quant_Canais) {
        this.interruptor_Quant_Canais = interruptor_Quant_Canais;
    }

    
    

    
    
    public Aparelho[] getDispositivos() {
        
        return dispositivos;
    }

    public void setDispositivos(Aparelho[] dispositivos) {
        
        this.dispositivos = dispositivos; 
    }
    
    
    
    


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String[] getEstados_Nomes() {
        return estados_Nomes;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel_DDD() {
        return tel_DDD;
    }

    public void setTel_DDD(String tel_DDD) {
        this.tel_DDD = tel_DDD;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepetir_Senha() {
        return repetir_Senha;
    }

    public void setRepetir_Senha(String repetir_Senha) {
        this.repetir_Senha = repetir_Senha;
    }
    
    
    
    
    
    
    
    
    public Object sentidoNavegacao() {
        
        if (login_Correto)
            return Navegacao.principal;
        else 
            return Navegacao.index;
    }
    
    
    public Object sentidoNavegacaoCadastro() {
        
        if (login_Correto)
            return Navegacao.principal;
        else 
            return Navegacao.cadastro;
    }
    
    
    public Object sentidoNavegacaoSenha() {
        
        if (login_Correto)
            return Navegacao.resultado;
        else 
            return Navegacao.recuperacao;
    }
    
    
    
    
    //Método usado para verificar se os dados fornecidos do usuário são legítmos.
    public void validar(FacesContext context, UIComponent component, Object value) {

        String valor = (String) value;

            //Aqui é identificado o componente que gerou o evento.
            //O componente que gerou o evento de validação foi o input text do e-mail.
            if (component.getId().equals("usuario_Email")) {
  
                //Endereço de e-mail inválido. Todo e-mail deve conter os símbolos de @ e .
                if (!(valor.contains("@")) || !(valor.contains("."))) {
                    
                FacesMessage msg = new FacesMessage("Endereço de e-mail inválido!");
                context.addMessage(component.getClientId(context), msg);   
                
                } else { }

            } 
            
            
            if (component.getId().equals("usuario_Senha")) {
                
                //A senha deve ter no mínimo 5 caracteres
                if (valor.length() < 5) {
                    
                FacesMessage msg = new FacesMessage("A senha deve ter no mínimo 5 caracteres.");
                context.addMessage(component.getClientId(context), msg);   
                
                } else { }

            }
            

            if (component.getId().equals("cadastro_Senha")) {
                
                //A senha deve ter no mínimo 5 caracteres
                if (valor.length() < 5) {
                    
                FacesMessage msg = new FacesMessage("A senha deve ter no mínimo 5 caracteres.");
                context.addMessage(component.getClientId(context), msg);   
                
                } else { }
            }
            

            if (component.getId().equals("cadastro_Senha02")) {

                //A senha deve ter no mínimo 5 caracteres
                if (valor.length() < 5) {
                    
                FacesMessage msg = new FacesMessage("A senha deve ter no mínimo 5 caracteres.");
                context.addMessage(component.getClientId(context), msg);   

                } else { }

            }
    }
    
    
    
    
    
    
    //Método ouvinte dos aventos de ação.
    public void mostrarGUIInterruptor(ActionEvent event) {
        
        //Aqui é identificado qual componente gerou o evento.
        if (event.getComponent().getId().equals("mostrarCamposInterruptor")) {
            
            flag_Chave_Interruptor = true;
        
            
            
        //O botão BUSCAR na tela principal foi clicado 
        //Aqui deve ser feita uma busca na Tabela Interruptor com ID do aparelho fornecido
        } else if (event.getComponent().getId().equals("mostrarCampoAparelho")) {

            String query = "SELECT qtd_canais FROM Interruptor WHERE ITR_ID = '" + TEMP_interruptor_ID + "'";
            ResultSet resultado = gerenciadorBanco.executaQuery(gerenciadorBanco.statement, query);
            
                if (resultado == null) {
                    
                    mensagem.setSummary("Ocorreu um erro de acesso ao banco!");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                     
  
                } else {
                    
                    int quantCanais = gerenciadorBanco.getIntColumn(resultado, "qtd_canais");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
            
                        //O ID do Interruptor foi encontrado
                        //DEVE EXISTIR PELO MENOS 1 CANAL
                        if (quantCanais > 0) {
 
                            flag_Nomes_Aparelhos = true;
                            
                            interruptor_Quant_Canais = quantCanais;
                            
                            dispositivos = new Aparelho[interruptor_Quant_Canais];
                            
                                for (int i = 0; i < interruptor_Quant_Canais; i++) {
                                    
                                    dispositivos[i] = new Aparelho();
                                }                     
                            
                            mensagem.setSummary("Informe o nome do(s) aparelho(s) que serão monitorados. Clique em GRAVAR para começar o monitoramento.");
                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);

                        //ID DE INTERRUPTOR NÃO ENCONTRADO
                        } else {
                    
                            flag_Nomes_Aparelhos = false;
                            
                            mensagem.setSummary("Chave não encontrada!");
                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                            
                            TEMP_interruptor_ID = "";
                            interruptor_Quant_Canais = 0;
                        }
            
                }
                    
                    
                    
                    
            
        //O botão GRAVAR da tela principal foi pressionado
        //Aqui deve ser implementado a inclusão do ID do Interruptor na Tabela Cliente e Populada a tabela Aperelho de acordo com o nº de canais daquele ID do Interruptor.
        } else if (event.getComponent().getId().equals("mostrarTabela")) {

            //Atualiza a tabela cliente com o ID do Interruptor fornecido pelo usuário.
            String query = "UPDATE Cliente SET clt_ITR_ID = '" + TEMP_interruptor_ID + "' WHERE CLIENTE_ID = '" + email + "'";
            int resultado_Cliente = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query);
            int resultado_Aparelho = 0;

                for (int i = 0; i < interruptor_Quant_Canais; i++) { 
  
                    String aparelho_CANAL_ID = (TEMP_interruptor_ID.substring(0, 5) + "_C0" + (i+1));
                    dispositivos[i].canal_ID = aparelho_CANAL_ID;
                    String query02 = "INSERT INTO Aparelho(CANAL_ID, apr_nome, apr_estado, apr_consumo, apr_ITR_ID) VALUES ('" + dispositivos[i].canal_ID + "', '" + dispositivos[i].apr_Nome + "', '" + dispositivos[i].estado + "', '" + dispositivos[i].consumo + "', '" + TEMP_interruptor_ID + "')";
                    resultado_Aparelho = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query02); 
                }
                
            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
            
                //As queries foram executadas no banco com SUCESSO!
                if ((resultado_Cliente > 0) & (resultado_Aparelho > 0)) {
                    
                    flag_Tabela = true;
                    interruptor_ID = TEMP_interruptor_ID;

                //Houve algum erro ao executar as queries no banco.
                } else {
                    
                    mensagem.setSummary("Ocorreu um erro ao gravar os dados!");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                    
                }
            


            
        //Ocorreu um clique no botão Logar da tela inicial
        //Aqui deve ser estabelecida uma conexão com o banco para verificar o login ***
        } else if (event.getComponent().getId().equals("logar")) {
            
            flag_Requerido = true;

            String query = "SELECT CLIENTE_ID, clt_nome, clt_endereco, clt_senha, clt_ITR_ID FROM Cliente WHERE CLIENTE_ID = '" + email + "'" + " AND clt_senha = '" + senha + "'";
            ResultSet resultado = gerenciadorBanco.executaQuery(gerenciadorBanco.statement, query);
            
                if (resultado == null) {
                    
                    mensagem.setSummary("Ocorreu um erro de acesso ao banco!");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                     
  
                } else {
                    
                    String[] colunas = gerenciadorBanco.getStringColumns(resultado);
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
            
                        //Login CORRETO
                        if (colunas[0] != null) {
 
                            login_Correto = true; //Ir para a página principal.

                            email = colunas[0];
                            nome = colunas[1];
                            endereco = colunas[2];
                            senha = colunas[3];
                            interruptor_ID = colunas[4];
                            
                                //Verifica se o usuário cadastrado já cadastrou um Interruptor Remoto para monitorar seus dispositivos.
                                if (interruptor_ID != null) {
                                    
                                    //Primeiramente pega a quantidade de canais do ID do Interruptor
                                    String query_Interruptor = "SELECT qtd_canais FROM Interruptor WHERE ITR_ID = '" + interruptor_ID + "'";
                                    ResultSet resultado03 = gerenciadorBanco.executaQuery(gerenciadorBanco.statement, query_Interruptor);
                                    
                                        if (resultado03 != null) {
                                            
                                            interruptor_Quant_Canais = gerenciadorBanco.getIntColumn(resultado03, "qtd_canais");
                                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
                                        }

                                    String query_Aparelhos = "SELECT CANAL_ID, apr_nome, apr_consumo, DATE_FORMAT(apr_data, '%d/%m/%Y - %H:%i') AS apr_data, apr_estado FROM Aparelho WHERE apr_ITR_ID = '" + interruptor_ID +  "'";
                                    ResultSet resultado02 = gerenciadorBanco.executaQuery(gerenciadorBanco.statement, query_Aparelhos);
  
                                        if ((resultado02 != null) & (interruptor_Quant_Canais > 0)) {
                                            
                                            String[][] colunas_Aparelhos = gerenciadorBanco.getStringColumnsAparelho(resultado02, interruptor_Quant_Canais);
                                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
                                            
                                                //Se a busca pelos aparelhos cadastrados foi realizada com sucesso, atribui-se ao Bean.
                                                if (colunas_Aparelhos[0][0] != null) {
                                                    
                                                    flag_Tabela = true;
                                                    
                                                    dispositivos = new Aparelho[interruptor_Quant_Canais];
                                                    
                                                        for (int i = 0; i < dispositivos.length; i++) {

                                                            dispositivos[i] = new Aparelho(colunas_Aparelhos[i][0], colunas_Aparelhos[i][1], Integer.valueOf(colunas_Aparelhos[i][2]).intValue(), colunas_Aparelhos[i][3], colunas_Aparelhos[i][4]);
                                                        }
                                                }
                                        } 

                                } else {
                                    flag_Tabela = false;
                                }
                                    
                    
                        //Login INCORRETO
                        } else {
                    
                            login_Correto = false;
                            
                            mensagem.setSummary("Usuário não encontrado!");
                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                            
                            email = "";
                            senha = "";
                        }
            
                    //gerenciadorBanco.fecharConexao(gerenciadorBanco.statement);
                }
            

        } else if (event.getComponent().getId().equals("cadastrar")) {
            
            flag_Requerido = false;

            email = "";
            senha = "";

            
        //O usuário preencheu o formulário de cadastro e clicou no botão Cadastrar da página Cadastro.jspx.
        //Aqui deve ser implementado o código que popula a tabela referente aos dados do cliente
        //e mandar o e-mail com as informações de Login e Senha cadastradas.
        } else if (event.getComponent().getId().equals("gravar_Cadastro")) {
            
            //As senhas foram digitadas de forma diferente.
            if ( !(senha.equals(repetir_Senha.toString())) ) {
                
                login_Correto = false;
                
                senha = "";
                repetir_Senha = "";
                
                mensagem.setSummary("Senhas digitadas diferente.");
                gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);

                
            } else {
                 
                String query = "INSERT INTO Cliente(CLIENTE_ID, clt_nome, clt_estado, clt_cidade, clt_endereco, clt_DDD, clt_telefone, clt_senha) values('" + email + "', '" + nome + "', '" + estado + "', '" + cidade + "', '" + endereco + "', '" + tel_DDD + "', '" + telefone + "', '" + senha + "')";
                int resultado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query);

                    //Dado inserido com sucesso!
                    if (resultado > 0) {

                        login_Correto = true;
                        
                        //Envia o E-Mail para o usuário.
                        TransmissorEMail transmissor = new TransmissorEMail(nome, email, senha);
                        transmissor.enviar();

                    } else {

                        login_Correto = false;

                        mensagem.setSummary("Usuário já existente!");
                        gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                    }
                
            }
            


        //O botão resetar da tela de Cadaastro foi clicado.
        } else if (event.getComponent().getId().equals("resetar")) { 
 
        nome = "";
        estado = estados_Nomes[0];
        cidade = "";
        endereco = "";
        tel_DDD = "";
        telefone = "";
        email = "";
        senha = "";
        repetir_Senha = "";
        
        
        //O usuário solicitou a recuperação da senha cadastrada.
        } else if (event.getComponent().getId().equals("recuperarSenha")) {
            
            String query = "SELECT clt_senha FROM Cliente WHERE CLIENTE_ID = '" + email + "'";
            ResultSet resultado = gerenciadorBanco.executaQuery(gerenciadorBanco.statement, query);
            
                if (resultado == null) {
                    
                    mensagem.setSummary("Ocorreu um erro de acesso ao banco!");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                     
  
                } else {
                    
                    String senhaRecuperada = gerenciadorBanco.getStringColumn(resultado, "clt_senha");
                    gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, gerenciadorBanco.msg);
            
                        //A senha foi encontrada
                        if (!(senhaRecuperada.equals(""))) {
                            
                            login_Correto = true; //Ir para a página resultado.jspx
                            
                            //Envia o E-Mail para o usuário.
                            TransmissorEMail transmissor = new TransmissorEMail("", email, senhaRecuperada);
                            transmissor.enviar();

                            
                        } else {
                            
                            login_Correto = false; //Continuar na página
                            
                            mensagem.setSummary("Usuário não encontrado!");
                            gerenciadorBanco.mostrarMSG(FacesContext.getCurrentInstance(), event, mensagem);
                            
                            email = "";
                        }
                }
            
        
        
        //O botão interruptor da Tela Principal referente ao 1º aparelho foi clicado.
        } else if (event.getComponent().getId().equals("interruptor01")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[0].setEstado_Aparelho(!(dispositivos[0].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[0].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[0].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[0].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[0].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[0].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }

                }
        
        } else if (event.getComponent().getId().equals("interruptor02")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[1].setEstado_Aparelho(!(dispositivos[1].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[1].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[1].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[1].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[1].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[1].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }

                }
        
        } else if (event.getComponent().getId().equals("interruptor03")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[2].setEstado_Aparelho(!(dispositivos[2].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[2].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[2].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[2].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[2].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[2].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }
                }
        
        } else if (event.getComponent().getId().equals("interruptor04")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[3].setEstado_Aparelho(!(dispositivos[3].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[3].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[3].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[3].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[3].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[3].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }
                }
        
        } else if (event.getComponent().getId().equals("interruptor05")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[4].setEstado_Aparelho(!(dispositivos[4].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[4].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[4].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[4].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[4].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[4].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }

                }
        
        } else if (event.getComponent().getId().equals("interruptor06")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[5].setEstado_Aparelho(!(dispositivos[5].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[5].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[5].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[5].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[5].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[5].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }
                }
        
        } else if (event.getComponent().getId().equals("interruptor07")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[6].setEstado_Aparelho(!(dispositivos[6].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[6].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[6].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[6].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[6].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[6].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }

                }
        
        } else if (event.getComponent().getId().equals("interruptor08")) {
            
            HtmlCommandButton butao = (HtmlCommandButton) event.getComponent();
            dispositivos[7].setEstado_Aparelho(!(dispositivos[7].estado_Aparelho)); //Alterna o estado do botão.

                //O botão foi acionado para ligar o aparelho.
                if (dispositivos[7].estado_Aparelho) {

                    String query_Mudar_Estado = "UPDATE Aparelho SET apr_estado = 'Ligado' WHERE CANAL_ID = '" + dispositivos[7].canal_ID + "'";
                    int resultado_Mudar_Estado = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado > 0) {
                            
                            dispositivos[7].setEstado("Ligado");
                            butao.setStyle("font-size:32px; color:cyan; background-color: dimgray; border: 2px inset black; padding:4px 4px;");
                        }

                //O botão foi acionado para Desligar o aparelho.
                } else {

                    String query_Mudar_Estado2 = "UPDATE Aparelho SET apr_estado = 'Desligado' WHERE CANAL_ID = '" + dispositivos[7].canal_ID + "'";
                    int resultado_Mudar_Estado2 = gerenciadorBanco.executaUpdate(gerenciadorBanco.statement, query_Mudar_Estado2);
                                    
                        //Se o atributo da base foi atualizado, atualiza-se o bean e a interface.
                        if (resultado_Mudar_Estado2 > 0) {
                            
                            dispositivos[7].setEstado("Desligado");
                            butao.setStyle("font-size:32px; color:darkblue; padding:4px 4px;");
                        }
                }
        
        } else { }
        
        
        
    }
    

}
