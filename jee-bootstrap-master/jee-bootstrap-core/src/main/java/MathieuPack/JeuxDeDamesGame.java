package MathieuPack;

public interface JeuxDeDamesGame {

	/**
	 * Returns the colour of the chip in a given cell, null if no chip is
	 * present.
	 * 
	 * @param column
	 * @param row
	 * @return
	 */

	void turn();

	int countWhite();

	int countBlack();

	void dame();

	void move(int ligneInitiaL, int columnInitial, int ligneFinal, int columnFinal);

	PawnColor select(int ligneInitial, int columnInitial);

	/**
	 * Returns the number of columns.
	 * 
	 * @return
	 */
	int getColumnsNumber();

	/**
	 * Returns the number of rows.
	 * 
	 * @return
	 */
	int getRowsNumber();

	PawnColor getWinner();

}
