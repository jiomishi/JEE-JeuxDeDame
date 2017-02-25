package MathieuPack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JeuxDeDamesMain extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JeuxDeDamesGame currentGame = new JeuxDeDamesGameImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String messageBienvenue =
		 * "Bienvenue sur notre servlet de jeu de Dames !"; String
		 * auteursQuiPesentAMort = "Mathieu et Théophile";
		 * 
		 * request.setAttribute("entete", messageBienvenue);
		 * request.setAttribute("auteurs", auteursQuiPesentAMort);
		 */
		String l1 = (String) request.getParameter("line1");
		String l2 = (String) request.getParameter("line2");
		String r1 = (String) request.getParameter("row1");
		String r2 = (String) request.getParameter("row2");

		if (l1 != null || l2 != null || r1 != null || r2 != null) {
			int lInit = Integer.parseInt(l1) - 1;
			int lFin = Integer.parseInt(l2) - 1;
			int rInit = Integer.parseInt(r1) - 1;
			int rFin = Integer.parseInt(r2) - 1;

			currentGame.move(lInit, rInit, lFin, rFin);
		}

		String currentBoard = currentGame.toString();
		request.setAttribute("plateau", currentBoard);

		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

	}

}
