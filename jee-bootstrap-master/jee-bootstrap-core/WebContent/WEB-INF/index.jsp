<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>JeuDeDames index</title>
    </head>

    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <%-- Commentaire JSP ma gueule, visible que pour le dév --%>
        <!-- Commentaire HTML standard -->
        <div> 
        	<%
        	String attribut = (String) request.getAttribute("test");
        	out.println( attribut );
        	%>
        </div>
        <p> 
        	Récupération du bean :
        	<%
        		test.beans.MyBeans monBean = (test.beans.MyBeans) request.getAttribute("PremierBean");
        		out.println(monBean.getPrenom());
        		out.println(monBean.getNom());
        	%>	
        	
        </p>
        
        <%= "Impression par balise d'expression " %>
    </body>
</html>