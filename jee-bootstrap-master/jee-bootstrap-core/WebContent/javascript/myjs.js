/* 
My js file form the webApp JeuxDeDames in JEE course
 Author : Mathieu Dumont & Théophile Becquet
 2017
*/

displayBoard();


function displayBoard() {
	document.getElementById("boardContainer").innerHTML = "<br>";
	var i = 0;
	var j = 0;
	var board = document.createElement("ul");
	
	for(j=1; j<11; j++){
		var line = document.createElement("li")
		line.classList.add("line");
		line.id = "line " + (11 - j);
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