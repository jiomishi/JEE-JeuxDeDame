package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet
 */

public class servlet extends HttpServlet {
	
	  public JeuxDeDamesGame game ;
  	public JeuxDeDamesGame gameForEasyWin; 
  	boolean WhiteTurn;
  	public PawnColor white=PawnColor.WHITE;
  	public PawnColor dameWhite=PawnColor.DAME_WHITE;
  	public PawnColor black=PawnColor.BLACK;
  	public PawnColor dameBlack=PawnColor.DAME_BLACK;
  	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
    	 
    	super();
    	game = new JeuxDeDamesGameImpl();
    	gameForEasyWin = new JeuxDeDamesGameImpl(1);
    	WhiteTurn=true;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute( "test", game.toHtmlString() );
		if(WhiteTurn==true)
		{request.setAttribute( "color", "Blanc" );}
		else{
			request.setAttribute( "color", "Noir" );
		}
		this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		
		game.dame();
		if(game.getWinner()==null){
			if(request.getParameter("ligne")==null || request.getParameter("column")==null || request.getParameter("ligneDeplacement")==null || request.getParameter("columnDeplacement")==null)
			{
				request.setAttribute( "textError", "vous n'avez pas remplie tous les champs" );
			}
			else{
				int ligneSelect = Integer.parseInt(request.getParameter("ligne"));
				int ColumnSelect = Integer.parseInt(request.getParameter("column"));
				int ligneDeplacement=Integer.parseInt(request.getParameter("ligneDeplacement"));
				int columnDeplacement=Integer.parseInt(request.getParameter("columnDeplacement"));
				
				if(game.select(ColumnSelect, ligneSelect)!=null){
				
					if(WhiteTurn==true &&(game.select(ColumnSelect, ligneSelect)==white||game.select(ColumnSelect, ligneSelect)==dameWhite)){
						game.move(ColumnSelect,ligneSelect,columnDeplacement,ligneDeplacement);
						switch(game.getStatement()){
						case 0:
							WhiteTurn=false;
							request.setAttribute( "textError", "Au Noirs de jouer" );
							request.setAttribute( "color", "Noir" );
							break;
						case 1:
							request.setAttribute( "textError", "N'essayez pas de vous echapper" );
							break;
						case 2:
							request.setAttribute( "textError", "Vous ne selectionnez pas de pion" );
							break;
						case 3:
							request.setAttribute( "textError", "ce pion est protegé" );
							break;
						case 4:
							request.setAttribute( "textError", "Vous avez deja un pion sur cette case" );
							break;
						case 5:
							request.setAttribute( "textError", "Deplacement non autorisé" );
							break;
							
						}
						
					}
					else if(WhiteTurn==false &&(game.select(ColumnSelect, ligneSelect)==black||game.select(ColumnSelect, ligneSelect)==dameBlack)){
						game.move(ColumnSelect,ligneSelect,columnDeplacement,ligneDeplacement);
						switch(game.getStatement()){
						case 0:
							WhiteTurn=true;
							if(WhiteTurn)
							request.setAttribute( "color", "Blanc" );
							request.setAttribute( "textError", "Au blancs de jouer" );
							break;
						case 1:
							request.setAttribute( "textError", "N'essayez pas de vous echapper" );
							break;
						case 2:
							request.setAttribute( "textError", "Vous ne selectionnez pas de pion" );
							break;
						case 3:
							request.setAttribute( "textError", "ce pion est protegé" );
							break;
						case 4:
							request.setAttribute( "textError", "Vous avez deja un pion sur cette case" );
							break;
						case 5:
							request.setAttribute( "textError", "Deplacement non autorisé" );
							break;
							
						}
						
					}
					else{
						request.setAttribute( "textError", "Ce n'est pas à vous de jouer" );
					}
				}
				else{
					request.setAttribute( "textError", "Vous ne selectionnez pas de pion" );
				}
			}}
		else{
			if(game.getWinner()==white){
				request.setAttribute( "textError", "Les blancs gagnes !!" );
			}
			else{
				request.setAttribute( "textError", "Les noirs gagnes !!" );
			}
			
		}
			
			request.setAttribute( "test", game.toHtmlString() );
			this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
	     
	

}
}