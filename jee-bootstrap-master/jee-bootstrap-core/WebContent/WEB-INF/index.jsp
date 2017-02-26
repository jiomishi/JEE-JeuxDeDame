<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
    	<link rel="icon" href="img/white.png">
        <meta charset="utf-8" />
        <title>JeuDeDames index</title>
        <link rel="stylesheet" href="CSS/mycss.css"/>
    </head>

    <body>
        <h1> 
        	Bienvenue sur notre Servlet de jeu de Dames !
        	<br>\\ Par Mathieu et Théophile //
        </h1>
        <br>

<div>
	<div id="boardContainer"> 
	</div>

	
</div>
<div id="chatBox">
<div id="formBox">
<form method="get">
	<input class="hid" id="formLineInit" name="line1" type="number" min="0" max="9" required/>
	<input class="hid" id="formRowInit" name="row1" type="number" min="0" max="9" required/>
	<input class="hid" id="formLineFin" name="line2" type="number" min="0" max="9" required/>
	<input class="hid" id="formRowFin" name="row2" type="number" min="0" max="9" required/>
	<input type="submit" value="move"/>
</form>
</div>
	<%  String messageActuel = (String) request.getAttribute("chatBoxContent");
	if( messageActuel == null) {
		messageActuel = "Tour en cours !";
	}
	out.println(messageActuel);
	%>
</div>
<br>


<script type="text/javascript" src="javascript/myjs.js"></script>
<script type="text/javascript" >


<%  String plateauActuel = (String) request.getAttribute("plateau"); %>
var str = "<%=plateauActuel%>";
displayPawns(str);

function displayPawns(boardString) {
	var nbCases = boardString.length;
	
	for( i = 0 ; i < nbCases ; i++ ){
		var id = i.toString();
		var currentCase = document.getElementById(id);
		var pawnHere = boardString.charAt(i-1);
		var imgContent = document.createElement("img");
		imgContent.classList.add("imageContent");
		
		switch(pawnHere){
		
		case "x":
			currentCase.innerHTML = "";
			imgContent.src = "img/white.png";
			imgContent.addEventListener("click", sendMove);
			currentCase.appendChild(imgContent);
			break;
			
		case "o":
			currentCase.innerHTML = "";
			imgContent.src = "img/black.png";
			imgContent.addEventListener("click", sendMove);
			currentCase.appendChild(imgContent);
			break;
			
		case "X":
			currentCase.innerHTML = "";
			imgContent.src = "img/whitePawn.png";
			imgContent.addEventListener("click", sendMove);
			currentCase.appendChild(imgContent);
			break;
			
		case "O":
			currentCase.innerHTML = "";
			imgContent.src = "img/blackPawn.png";
			imgContent.addEventListener("click", sendMove);
			currentCase.appendChild(imgContent);
			break;
			
		case " ":
			currentCase.innerHTML = "";
			imgContent.src = "img/void.png";
			imgContent.addEventListener("click", sendMove);
			currentCase.appendChild(imgContent);
		
			
		}
	}
}

function sendMove(e){
	
	for(i=1;i<101;i++){
		var caseCleaned = document.getElementById(i);
		if( caseCleaned.childNodes[0] != null ){
			var imgCleaned = caseCleaned.childNodes[0];
			if( imgCleaned.classList.contains("focusCase") )
				imgCleaned.classList.remove("focusCase");
		}
	}
	var focusPawn = e.target;
	focusPawn.classList.add("focusCase");
	var focusCase = focusPawn.parentNode;
	
	if( ((focusCase.id)%10) == 0) 
		var lineValue = 10; 
		else var lineValue = ((focusCase.id)%10); 
	if( ((focusCase.id) - lineValue ) == 0) 
		var rowValue = 1; 
		else var rowValue = 11 - (( ((focusCase.id) - lineValue) / 10 ) + 1); 
	
	
	if( document.getElementById("formRowInit").value == "" ){		
		cleanCases();
		spotCaseSelect(focusCase);
		document.getElementById("formRowInit").value = rowValue; 
		document.getElementById("formLineInit").value = lineValue;}
	else if ( document.getElementById("formRowFin").value == "" ){
		spotCaseDestination(focusCase);
		document.getElementById("formRowFin").value = rowValue; 
		document.getElementById("formLineFin").value = lineValue;
	} else {
		cleanCases();
		spotCaseSelect(focusCase);
		document.getElementById("formRowInit").value = rowValue; 
		document.getElementById("formLineInit").value = lineValue;
		document.getElementById("formRowFin").value = ""; 
		document.getElementById("formLineFin").value = "";
	}
	
	
	
}
function cleanCases(){
	for(i=1;i<101;i++){
		document.getElementById(i).classList.remove("selectedCase");
		document.getElementById(i).classList.remove("destinationCase");
		}
}
function spotCaseSelect( focusCase ) {	
	if ( focusCase != null)
	focusCase.classList.add("selectedCase");
}
function spotCaseDestination( focusCase ) {	
	for(i=1;i<101;i++){
		document.getElementById(i).classList.remove("destinationCase");
		}
	if ( focusCase != null)
	focusCase.classList.add("destinationCase");
}

function verifForm(f){
	if ( verifField(f.line1) && verifField(f.row1) && verifField(f.line2) && verifField(f.row2) ) {
		return true;
	} else { 
		return false;
		document.getElementById()
	}
}

function verifField(a){
	if( a<11 && a>0 && a!= null ){
		return true;
	} else { 
		return false;
	}
}

</script>

    </body>
</html>