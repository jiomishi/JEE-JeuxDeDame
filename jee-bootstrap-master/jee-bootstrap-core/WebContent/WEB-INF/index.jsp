<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>JeuDeDames index</title>
        <link rel="stylesheet" href="CSS/mycss.css"/>
    </head>

    <body>
        <div> 
        	Bienvenue sur notre Servlet de jeu de Dames !
        	<br>\\ Par Mathieu et Théophile //
        </div>
        <br>

<div>
	<div id="boardContainer"> 
	</div>
	<button id="displayBoard"> Display board </button>
	<button id="pawns"> Display pawns</button>
</div>
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
	<input type="submit" value="move"/>
</form>
<script type="text/javascript" >

<%  String plateauActuel = (String) request.getAttribute("plateau"); %>
var str = "<%=plateauActuel%>";


function displayBoard() {
	document.getElementById("boardContainer").innerHTML = "Board container : <br>";
	var i = 0;
	var j = 0;
	var board = document.createElement("ul");
	
	for(j=1; j<11; j++){
		var line = document.createElement("li")
		line.classList.add("line");
		var lineList = document.createElement("ul")
			for (i = 1; i < 11; i++) {
				var square = document.createElement("li");
				var content = (j-1)*10 + i;
				square.id = content;
				
				if( (j+i)%2 == 0){
					square.classList.add("caseB");	
				} else {
					square.classList.add("caseN");	
				}
				
				lineList.appendChild(square);
			}
		line.appendChild(lineList);
		board.appendChild(line);
	}
		document.getElementById("boardContainer").appendChild(board);
}
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
			currentCase.appendChild(imgContent);
			break;
			
		case "o":
			currentCase.innerHTML = "";
			imgContent.src = "img/black.png";
			currentCase.appendChild(imgContent);
			break;
			
		case "X":
			currentCase.innerHTML = "";
			imgContent.src = "img/whiteDame.png";
			currentCase.appendChild(imgContent);
			break;
			
		case "O":
			currentCase.innerHTML = "";
			imgContent.src = "img/blackDame.png";
			currentCase.appendChild(imgContent);
			break;
			
		default:
			
		}
	}
}
displayBoard();
document.getElementById("pawns").addEventListener("click", displayPawns(str));</script>

    </body>
</html>