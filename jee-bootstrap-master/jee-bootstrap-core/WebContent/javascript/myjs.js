/* 
My js file form the webApp JeuxDeDames in JEE course
 Author : Mathieu Dumont & Théophile Becquet
 2017
*/


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
				
				if( (j+i)%2 == 1){
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
		var pawnHere = boardString.charAt(i);
		var imgContent = document.createElement("img");
		
		switch(pawnHere){
		
		case "x":
			currentCase.innerHTML = "";
			imgContent.href = "img/white.png";
			currentCase.appendChild(imgContent);
			break;
			
		case "o":
			currentCase.classList.add("blackPawn")
			break;
			
		case "X":
			currentCase.classList.add("whiteDame")
			break;
			
		case "O":
			currentCase.classList.add("blackDame")
			break;
			
		default:
			
		}
	}
}










