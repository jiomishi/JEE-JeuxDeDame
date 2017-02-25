<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>JeuDeDames index</title>
    </head>

    <body>
        <%-- Commentaire JSP ma gueule, visible que pour le dév --%>
        <!-- Commentaire HTML standard -->
        <div> 
        	Bienvenue sur notre Servlet de jeu de Dames !
        	<br>\\ Par Mathieu et Théophile //
        </div>
        <br>
     
<%
  String plateauActuel = (String) request.getAttribute("plateau");
%>

<h4> 
	<%= plateauActuel %>
</h4>
 <br>
<form method="get">
line 1 :
<input name="line1" type="number" min="0" max="9"/>
row 1 :
<input name="row1" type="number" min="0" max="9"/>
<br>line 2 :
<input name="line2" type="number" min="0" max="9"/>
row 2 :
<input name="row2" type="number" min="0" max="9"/>
<input type="submit" value="moveData"/>
</form>

    </body>
</html>