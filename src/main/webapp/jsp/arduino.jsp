<%-- 
    Document   : arduino
    Created on : 04/11/2016, 12:24:08
    Author     : Luiz Fernando
    Descrição  : Pagina usada como elo de comunicação entre o dispositivo Arduino e o Sistema MobiSCD
--%>

<%@page import="mobiSCD.Beans.Aparelho"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Map"%>
<%@page import="mobiSCD.Model.DataManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <title>Pagina de comunicacao do Sistema.</title>
    </head>
    <body>



        <jsp:useBean id="dados" class="mobiSCD.Beans.Cliente"/>

        <%

            //Atributos passados pelo método GET da solicitação HTTP Request enviada pelo Arduino.
            String param_ITR_ID = request.getParameter("p_ITR_ID");
            Map<String,String[]> param_Consumo_Canais = request.getParameterMap(); // Map<K,V> - Nome do parâmetro são a chave (K) e o valor é (V).
            
            /* MÉTODO PARA IMPLEMENTAR O HISTÓRICO DE DATA/CONSUMO 
            
            Para inserir os dados de Consumo e data na tabela Aparelho será necessário criar uma nova Tabela Historico 
            com os atributos Nome, Consumo e Data. Essa tabela terá que ter também uma chave primaria qualquer com 
            uma numeração que será incrementada pela querie:
            SELECT COUNT(DISTINCT CANAL_ID) FROM Aparelho;
            
            e uma chave estrangeira com o ID do Canal.
            */
            

            String queryQuantCanais = "SELECT qtd_canais FROM Interruptor WHERE ITR_ID = '" + param_ITR_ID + "'";

            DataManager model = new DataManager();
            ResultSet resultadoQuantCanais = model.executaQuery(model.statement, queryQuantCanais);
            int quantidade = 0;

                //A busca pela quantidade de canais do ITR_ID foi realizada com SUCESSO!
                if (resultadoQuantCanais != null) {

                    quantidade = model.getIntColumn(resultadoQuantCanais, "qtd_canais");
                    dados.setInterruptor_Quant_Canais(quantidade);

                } else {

                    out.println("ERROR!");
                }

            String query_Aparelhos = "SELECT CANAL_ID, apr_nome, apr_consumo, apr_data, apr_estado FROM Aparelho WHERE apr_ITR_ID = '" + param_ITR_ID + "'";
            ResultSet resultadoAparelhos = model.executaQuery(model.statement, query_Aparelhos);

                //Foi encontrado os dados dos aparelhos referente ao ITR_ID passado.
                if ((resultadoAparelhos != null) & (quantidade > 0)) {

                    String[][] colunas_Aparelhos = model.getStringColumnsAparelho(resultadoAparelhos, quantidade);

                    Aparelho[] aparelhos = new Aparelho[quantidade];

                        for (int i = 0; i < aparelhos.length; i++) {

                            aparelhos[i] = new Aparelho();
                            aparelhos[i].canal_ID = colunas_Aparelhos[i][0];
                            aparelhos[i].estado = colunas_Aparelhos[i][4];
                        }

                    dados.setDispositivos(aparelhos);

                } else {

                    dados.setDispositivos(null);
                    out.println("ERROR!");
                }
                
            
            // INSERINDO A INFORMAÇÃO DE CONSUMO e DATA ATUAL ENVIADO PELO ARDUINO 

                //Intera sob o número de atributos de consumo passados pelo HTTP Request. 0 é o atributo do ID do Interruptor.
                for (int i = 1; i < param_Consumo_Canais.size(); i++) {
                    
                    String aparelho_CANAL_ID = (param_ITR_ID.substring(0, 5) + "_C0" + i);
                    String consumo = param_Consumo_Canais.get("p_consumo_C0" + i)[0];
                    String queryConsumo = "UPDATE Aparelho SET apr_consumo = " + consumo + ", apr_data = DATE_FORMAT(NOW(), '%Y/%m/%d - %H:%i') WHERE CANAL_ID ='" + aparelho_CANAL_ID + "'";

                    int resultado_Update_Consumo = model.executaUpdate(model.statement, queryConsumo);

                        if (resultado_Update_Consumo == 0) {

                            dados.setDispositivos(null);
                            out.println("ERROR!");
                        }
                }

        %>

        <br/>
        <br/>

        <c:if test="${not empty dados.dispositivos}"> 

            <table>
                <c:forEach var="aparelhos" items="${dados.dispositivos}" varStatus="id">
                    <tr>
                        <td>Canal ${id.count}:</td><td>${aparelhos.canal_ID}</td><td>${aparelhos.estado}</td>
                    </tr>
                </c:forEach>
            </table>

        </c:if>
        
        
        <c:if test="${empty dados.dispositivos}">
            
            <h1>ERROR!</h1>

        </c:if>
            
</body>
</html>