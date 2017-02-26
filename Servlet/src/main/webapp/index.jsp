
<%@page contentType="text/html" pageEncoding="UTF-8"%> 


<style>
table, tr, td {
    border: 1px solid black;
    table-layout: fixed;
    width: 25px;
    height: 25px;
}

tr:nth-child(odd) td:nth-child(even), tr:nth-child(even) td:nth-child(odd) {
	background: #DE9816;
}

tr:nth-child(even) td:nth-child(even), tr:nth-child(odd) td:nth-child(odd) {
	background: #FFDE75;
}

td.circle-Black:before {
  content: attr(data-char);
  display: block;
  background: #000;
  width: 20px;
  height: 20px;
  line-height:20px;
  text-align:center;
  vertical-align: middle;
  border-radius: 50%;
  margin:0 auto;
}

td.circle-White:before {
  content: attr(data-char);
  display: block;
  background: #fff;
  width: 20px;
  height: 20px;
  line-height:20px;
  text-align:center;
  vertical-align: middle;
  border-radius: 50%;
  margin:0 auto;
}



#selection{
    position: absolute;
    left: 350px;
    top: 0px;
}

#Deplacement{
	position: absolute;
    left: 350px;
    top: 15%;
}

#valider{
position: absolute;
    left: 600px;
     top: 40%;
}

</style>

<html>

<body>
<table>
 <% 
        String attribut = (String) request.getAttribute("test");
        out.println( attribut );
  %>
</table>


<form action="servlet" method="POST">
<div id="selection">
<p>Vous etes les <% String color = (String) request.getAttribute("color"); out.println( color ); %>  Selectionnez votre pion</p>
<input id="columnSelect" name="column" placeholder="column"type="number" min="0" max="10"> </input>
<input id="ligneSelect" name="ligne" placeholder="Ligne"type="number" min="0" max="10"> </input>
 </div>
 
 <div id="Deplacement">
<p>Choisissez votre destination</p>
<input id="ligneDeplacement" name="columnDeplacement" placeholder="Column"type="number" min="0" max="10"> </input>
<input id="ColumnDeplacement" name="ligneDeplacement" placeholder="Ligne"type="number" min="0" max="10"> </input>
 </div>
 
<button type="submit" id="valider" name="button" value="Submit"> Valider</button>
</form>
<p>
<% 
        String error = (String) request.getAttribute("textError");
        out.println( error );
  %>
</p>
</body>
</html>


	 <!--game.move(Integer.parseInt(request.getParameter("ligne")),Integer.parseInt(request.getParameter("column")),Integer.parseInt(request.getParameter("ligneDeplacement")),Integer.parseInt(request.getParameter("ColumnDeplacement")));  -->

