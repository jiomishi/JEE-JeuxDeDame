package MathieuPack;

import java.util.Scanner;

/**
 * Created by dmetzler on 03/09/2014.
 */

public class JeuxDeDamesGameImpl implements JeuxDeDamesGame {

	public static final int COLUMNS_NUMBER = 10;
	public static final int ROWS_NUMBER = 10;
	public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible to play outside of the board";

	PawnColor[][] checkboard = new PawnColor[COLUMNS_NUMBER][ROWS_NUMBER];

	public JeuxDeDamesGameImpl() {
		initBoard();

	}

	public JeuxDeDamesGameImpl(int i) {
		boardWin();
	}

	private void initBoard() {
		for (int i = 0; i < ROWS_NUMBER; i++) {
			if (i % 2 == 0) {
				checkboard[i][0] = PawnColor.BLACK;
				checkboard[i][2] = PawnColor.BLACK;
				checkboard[i][6] = PawnColor.WHITE;
				checkboard[i][8] = PawnColor.WHITE;
			} else {
				checkboard[i][1] = PawnColor.BLACK;
				checkboard[i][3] = PawnColor.BLACK;
				checkboard[i][7] = PawnColor.WHITE;
				checkboard[i][9] = PawnColor.WHITE;
			}
		}
	}

	private void boardWin() {
		for (int i = 0; i < ROWS_NUMBER; i++) {
			if (i == 4) {
				checkboard[i][0] = PawnColor.BLACK;
				checkboard[5][1] = PawnColor.WHITE;
			}
		}
	}

	public String getWinner() {
		int nbWhite = countWhite();
		int nbBlack = countBlack();
		if (nbWhite == 0) {
			return "black";
		} else {
			if (nbBlack == 0) {
				return "white";
			} else {
				return null;
			}
		}
	}

	public int countWhite() {
		int nb = 0;
		for (int i = 0; i < checkboard.length; i++) {
			for (int j = 0; j < checkboard[i].length; j++) {
				if (checkboard[i][j] == PawnColor.WHITE || checkboard[i][j] == PawnColor.DAME_WHITE) {
					nb = nb + 1;
				}
			}
		}
		return nb;
	}

	public int countBlack() {
		int nb = 0;
		for (int i = 0; i < checkboard.length; i++) {
			for (int j = 0; j < checkboard[i].length; j++) {
				if (checkboard[i][j] == PawnColor.BLACK || checkboard[i][j] == PawnColor.DAME_BLACK) {
					nb = nb + 1;
				}
			}
		}
		return nb;
	}

	public void dame() {
		int i;
		for (i = 0; i < 10; i++) {
			if (checkboard[i][0] == PawnColor.WHITE) {
				checkboard[i][0] = PawnColor.DAME_WHITE;
			} else {
				if (checkboard[i][9] == PawnColor.BLACK) {
					checkboard[i][9] = PawnColor.DAME_BLACK;
				}
			}
		}
	}

	@Override
	public int getColumnsNumber() {
		return COLUMNS_NUMBER;
	}

	@Override
	public int getRowsNumber() {

		return ROWS_NUMBER;
	}

	public void turn() {

		System.out.println("debut du tour");
		Scanner sc = new Scanner(System.in);
		System.out.println("au pion blanc de jouer, choisissez un pion blanc en indicant la ligne: ");
		int j = sc.nextInt();
		System.out.println("la colonne: ");
		int h = sc.nextInt();
		if (select(j, h) == PawnColor.WHITE) {

			System.out.println("indiquer la ligne ou il doit se deplacer: ");
			int i = sc.nextInt();
			System.out.println("la colonne: ");
			int k = sc.nextInt();
			move(j, h, i, k);

			System.out.println("au pion noir de jouer, choisissez un pion blanc en indicant la ligne: ");
			j = sc.nextInt();
			System.out.println("la colonne: ");
			h = sc.nextInt();

			if (select(j, h) == PawnColor.BLACK) {

				System.out.println("indiquer la ligne ou il doit se deplacer: ");
				i = sc.nextInt();
				System.out.println("la colonne: ");
				k = sc.nextInt();
				move(j, h, i, k);

			} else {
				System.out.println("selectionnez un noir, les ronds quoi !");
			}

		} else {
			System.out.println("selectionnez un blanc, les croix quoi !");
		}

	}

	public PawnColor select(int ligneInitial, int columnInitial) {
		if (ligneInitial < 0 || ligneInitial >= getColumnsNumber() || columnInitial < 0
				|| columnInitial >= getColumnsNumber()) {
			return null;
		}

		else {
			if (checkboard[ligneInitial][columnInitial] != null) {
				PawnColor pawnSelect = checkboard[ligneInitial][columnInitial];
				return pawnSelect;
			} else {
				return null;
			}
		}
	}

	public String move(int ligneInitial, int columnInitial, int ligneFinal, int columnFinal) {

		if (ligneFinal < 0 || ligneFinal >= getColumnsNumber() || columnFinal < 0
				|| columnFinal >= getColumnsNumber()) {
			// System.out.println("vous ne vous echapperez pas du plateau !!");
			return ("vous ne vous echapperez pas du plateau !!");
		}

		else {

			if (checkboard[ligneInitial][columnInitial] == null) {
				// System.out.println("vous ne selectionnez pas de pion");
				return ("vous ne selectionnez pas de pion");
			} else {

				switch (checkboard[ligneInitial][columnInitial]) {
				case WHITE:
					if ((ligneFinal == ligneInitial + 1 && columnFinal == columnInitial - 1)
							|| (ligneFinal == ligneInitial - 1 && columnFinal == columnInitial - 1)) {
						if (checkboard[ligneFinal][columnFinal] == null) {
							checkboard[ligneFinal][columnFinal] = checkboard[ligneInitial][columnInitial];
							checkboard[ligneInitial][columnInitial] = null;
						} else {
							switch (checkboard[ligneFinal][columnFinal]) {
							case BLACK:
								if (ligneFinal == ligneInitial + 1 && columnFinal == columnInitial - 1
										&& checkboard[ligneFinal + 1][columnFinal - 1] == null) {
									if (checkboard[ligneFinal + 2][columnFinal - 2] == PawnColor.BLACK
											&& checkboard[ligneFinal + 3][columnFinal - 3] == null) {
										checkboard[ligneFinal + 3][columnFinal
												- 3] = checkboard[ligneInitial][columnInitial];
										checkboard[ligneFinal][columnFinal] = null;
										checkboard[ligneFinal + 2][columnFinal - 2] = null;
										checkboard[ligneInitial][columnInitial] = null;
									} else {
										if (checkboard[ligneFinal][columnFinal - 2] == PawnColor.BLACK
												&& checkboard[ligneFinal - 1][columnFinal - 3] == null) {
											checkboard[ligneFinal - 1][columnFinal
													- 3] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneFinal][columnFinal - 2] = null;
											checkboard[ligneInitial][columnInitial] = null;
										} else {
											checkboard[ligneFinal + 1][columnFinal
													- 1] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneInitial][columnInitial] = null;
										}
									}
								} else {
									if (ligneFinal == ligneInitial - 1 && columnFinal == columnInitial - 1
											&& checkboard[ligneFinal - 1][columnFinal - 1] == null) {
										if (checkboard[ligneFinal - 2][columnFinal - 2] == PawnColor.BLACK
												&& checkboard[ligneFinal - 3][columnFinal - 3] == null) {
											checkboard[ligneFinal - 3][columnFinal
													- 3] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneFinal - 2][columnFinal - 2] = null;
											checkboard[ligneInitial][columnInitial] = null;
										} else {
											if (checkboard[ligneFinal][columnFinal - 2] == PawnColor.BLACK
													&& checkboard[ligneInitial][columnFinal - 3] == null) {
												checkboard[ligneFinal + 1][columnFinal
														- 3] = checkboard[ligneInitial][columnInitial];
												checkboard[ligneFinal][columnFinal] = null;
												checkboard[ligneFinal][columnFinal - 2] = null;
												checkboard[ligneInitial][columnInitial] = null;
											} else {
												checkboard[ligneFinal - 1][columnFinal
														- 1] = checkboard[ligneInitial][columnInitial];
												checkboard[ligneFinal][columnFinal] = null;
												checkboard[ligneInitial][columnInitial] = null;
											}
										}
									} else {
										// System.out.println("il y a un pion
										// adverse qui protege ce pion");
										return ("il y a un pion adverse qui protege ce pion");
									}
								}
								break;

							case WHITE:
								// System.out.println("vous avez deja un pion
								// sur cette case");
								return ("vous avez deja un pion sur cette case");
							// break;
							}
						}
					} else {
						// System.out.println("ce deplacement n'est pas
						// autorisé");
						return ("ce deplacement n'est pas autorisé");
					}
					break;
				case BLACK:

					if ((ligneFinal == ligneInitial + 1 && columnFinal == columnInitial + 1)
							|| (ligneFinal == ligneInitial - 1 && columnFinal == columnInitial + 1)) {
						if (checkboard[ligneFinal][columnFinal] == null) {
							checkboard[ligneFinal][columnFinal] = checkboard[ligneInitial][columnInitial];
							checkboard[ligneInitial][columnInitial] = null;
						} else {
							switch (checkboard[ligneFinal][columnFinal]) {

							case BLACK:
								// System.out.println("vous avez deja un pion
								// sur cette case");
								return ("vous avez deja un pion sur cette case");
							// break;
							case WHITE:
								if (ligneFinal == ligneInitial + 1 && columnFinal == columnInitial + 1
										&& checkboard[ligneFinal + 1][columnFinal + 1] == null) {
									if (checkboard[ligneFinal + 2][columnFinal + 2] == PawnColor.WHITE
											&& checkboard[ligneFinal + 3][columnFinal + 3] == null) {
										checkboard[ligneFinal + 3][columnFinal
												+ 3] = checkboard[ligneInitial][columnInitial];
										checkboard[ligneFinal][columnFinal] = null;
										checkboard[ligneFinal + 2][columnFinal + 2] = null;
										checkboard[ligneInitial][columnInitial] = null;
									} else {
										if (checkboard[ligneFinal][columnFinal + 2] == PawnColor.WHITE
												&& checkboard[ligneFinal - 1][columnFinal + 3] == null) {
											checkboard[ligneFinal - 1][columnFinal
													+ 3] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneFinal][columnFinal + 2] = null;
											checkboard[ligneInitial][columnInitial] = null;
										} else {
											checkboard[ligneFinal + 1][columnFinal
													+ 1] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneInitial][columnInitial] = null;
										}

									}

								}
								if (ligneFinal == ligneInitial - 1 && columnFinal == columnInitial + 1
										&& checkboard[ligneFinal - 1][columnFinal + 1] == null) {
									if (checkboard[ligneFinal - 2][columnFinal + 2] == PawnColor.WHITE
											&& checkboard[ligneFinal - 3][columnFinal + 3] == null) {
										checkboard[ligneFinal - 3][columnFinal
												+ 3] = checkboard[ligneInitial][columnInitial];
										checkboard[ligneFinal][columnFinal] = null;
										checkboard[ligneFinal - 2][columnFinal + 2] = null;
										checkboard[ligneInitial][columnInitial] = null;
									} else {
										if (checkboard[ligneFinal][columnFinal + 2] == PawnColor.WHITE
												&& checkboard[ligneInitial][columnFinal + 3] == null) {
											checkboard[ligneFinal + 1][columnFinal
													+ 3] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneFinal][columnFinal + 2] = null;
											checkboard[ligneInitial][columnInitial] = null;
										} else {
											checkboard[ligneFinal - 1][columnFinal
													+ 1] = checkboard[ligneInitial][columnInitial];
											checkboard[ligneFinal][columnFinal] = null;
											checkboard[ligneInitial][columnInitial] = null;
										}
									}
								} else {
									// System.out.println("il y a un pion
									// adverse qui protege ce pion");
									return ("il y a un pion adverse qui protege ce pion");
								}
							}

							break;
						}
					} else {
						// System.out.println("ce deplacement n'est pas
						// autorisé");
						return ("ce deplacement n'est pas autorisé");
					}
					break;
				case DAME_WHITE:

					if (Math.abs(ligneInitial - ligneFinal) == Math.abs(columnInitial - columnFinal)) {
						int i;
						if (ligneFinal > ligneInitial && columnFinal > columnInitial) {
							boolean move = false;
							for (i = 1; i <= (columnFinal - columnInitial + 1); i++) {
								if (ligneInitial + i <= 8 && columnInitial + i <= 8) {
									if (checkboard[ligneInitial + i][columnInitial + i] != null) {
										switch (checkboard[ligneInitial + i][columnInitial + i]) {
										case WHITE:
											// System.out.println("vous avez
											// deja un pion sur cette case");
											move = true;
											return ("vous avez deja un pion sur cette case");
										// break;
										case BLACK:
											if (checkboard[ligneInitial + i + 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial + i] = null;
												checkboard[ligneInitial + i + 1][columnInitial + i
														+ 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										case DAME_WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_BLACK:
											if (checkboard[ligneInitial + i + 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial + i] = null;
												checkboard[ligneInitial + i + 1][columnInitial + i
														+ 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_WHITE;
							}
						}

						if (ligneFinal < ligneInitial && columnFinal < columnInitial) {

							boolean move = false;
							for (i = 1; i < (columnInitial - columnFinal + 1); i++) {
								if (ligneInitial - i <= 8 && columnInitial - i <= 8) {
									if (checkboard[ligneInitial - i][columnInitial - i] != null) {
										switch (checkboard[ligneInitial - i][columnInitial - i]) {
										case WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case BLACK:
											if (checkboard[ligneInitial - i - 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial - i] = null;
												checkboard[ligneInitial - i - 1][columnInitial - i
														- 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										case DAME_WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_BLACK:
											if (checkboard[ligneInitial - i - 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial - i] = null;
												checkboard[ligneInitial - i - 1][columnInitial - i
														- 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_WHITE;
							}

						}

						if (ligneFinal < ligneInitial && columnFinal > columnInitial) {
							boolean move = false;
							for (i = 1; i <= (columnFinal - columnInitial + 1); i++) {
								if (ligneInitial - i <= 8 && columnInitial + i <= 8) {
									if (checkboard[ligneInitial - i][columnInitial + i] != null) {
										switch (checkboard[ligneInitial - i][columnInitial + i]) {
										case WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case BLACK:
											if (checkboard[ligneInitial - i - 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial + i] = null;
												checkboard[ligneInitial - i - 1][columnInitial + i
														+ 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										case DAME_WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_BLACK:
											if (checkboard[ligneInitial - i - 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial + i] = null;
												checkboard[ligneInitial - i - 1][columnInitial + i
														+ 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_WHITE;
							}
						}

						if (ligneFinal > ligneInitial && columnFinal < columnInitial) {
							boolean move = false;
							for (i = 1; i <= (ligneFinal - ligneInitial + 1); i++) {
								if (ligneInitial + i <= 8 && columnInitial - i <= 8) {
									if (checkboard[ligneInitial + i][columnInitial - i] != null) {
										switch (checkboard[ligneInitial + i][columnInitial - i]) {
										case WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case BLACK:

											if (checkboard[ligneInitial + i + 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {

												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial - i] = null;
												checkboard[ligneInitial + i + 1][columnInitial - i
														- 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										case DAME_WHITE:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_BLACK:
											if (checkboard[ligneInitial + i + 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial - i] = null;
												checkboard[ligneInitial + i + 1][columnInitial - i
														- 1] = PawnColor.DAME_WHITE;
												move = true;
											}
											break;
										}
									}
								}

							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_WHITE;
							}
						}
					}
					break;
				case DAME_BLACK:

					if (Math.abs(ligneInitial - ligneFinal) == Math.abs(columnInitial - columnFinal)) {
						int i;
						if (ligneFinal > ligneInitial && columnFinal > columnInitial) {
							boolean move = false;
							for (i = 1; i <= (columnFinal - columnInitial + 1); i++) {
								if (ligneInitial + i <= 8 && columnInitial + i <= 8) {
									if (checkboard[ligneInitial + i][columnInitial + i] != null) {
										switch (checkboard[ligneInitial + i][columnInitial + i]) {
										case BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case WHITE:
											if (checkboard[ligneInitial + i + 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial + i] = null;
												checkboard[ligneInitial + i + 1][columnInitial + i
														+ 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										case DAME_BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_WHITE:
											if (checkboard[ligneInitial + i + 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial + i] = null;
												checkboard[ligneInitial + i + 1][columnInitial + i
														+ 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_BLACK;
							}
						}

						if (ligneFinal < ligneInitial && columnFinal < columnInitial) {

							boolean move = false;
							for (i = 1; i < (columnInitial - columnFinal + 1); i++) {
								if (ligneInitial - i <= 8 && columnInitial - i <= 8) {
									if (checkboard[ligneInitial - i][columnInitial - i] != null) {
										switch (checkboard[ligneInitial - i][columnInitial - i]) {
										case BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case WHITE:
											if (checkboard[ligneInitial - i - 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial - i] = null;
												checkboard[ligneInitial - i - 1][columnInitial - i
														- 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										case DAME_BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_WHITE:
											if (checkboard[ligneInitial - i - 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial - i] = null;
												checkboard[ligneInitial - i - 1][columnInitial - i
														- 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_BLACK;
							}

						}

						if (ligneFinal < ligneInitial && columnFinal > columnInitial) {
							boolean move = false;
							for (i = 1; i <= (columnFinal - columnInitial + 1); i++) {
								if (ligneInitial - i <= 8 && columnInitial + i <= 8) {
									if (checkboard[ligneInitial - i][columnInitial + i] != null) {
										switch (checkboard[ligneInitial - i][columnInitial + i]) {
										case BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case WHITE:
											if (checkboard[ligneInitial - i - 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial + i] = null;
												checkboard[ligneInitial - i - 1][columnInitial + i
														+ 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										case DAME_BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_WHITE:
											if (checkboard[ligneInitial - i - 1][columnInitial + i
													+ 1] == PawnColor.BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.WHITE
													|| checkboard[ligneInitial - i - 1][columnInitial + i
															+ 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial - i][columnInitial + i] = null;
												checkboard[ligneInitial - i - 1][columnInitial + i
														+ 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										}
									}
								}
							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_BLACK;
							}
						}

						if (ligneFinal > ligneInitial && columnFinal < columnInitial) {
							boolean move = false;
							for (i = 1; i <= (ligneFinal - ligneInitial + 1); i++) {
								if (ligneInitial + i <= 8 && columnInitial - i <= 8) {
									if (checkboard[ligneInitial + i][columnInitial - i] != null) {
										switch (checkboard[ligneInitial + i][columnInitial - i]) {
										case BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case WHITE:

											if (checkboard[ligneInitial + i + 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {

												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial - i] = null;
												checkboard[ligneInitial + i + 1][columnInitial - i
														- 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										case DAME_BLACK:
											move = true;
											// System.out.println("vous avez
											// deja un pion sur cette case");
											return ("vous avez deja un pion sur cette case");
										// break;
										case DAME_WHITE:
											if (checkboard[ligneInitial + i + 1][columnInitial - i
													- 1] == PawnColor.BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_BLACK
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.WHITE
													|| checkboard[ligneInitial + i + 1][columnInitial - i
															- 1] == PawnColor.DAME_WHITE) {
												move = true;
												// System.out.println("il y a un
												// pion adverse qui protege ce
												// pion");
												return ("il y a un pion adverse qui protege ce pion");
											} else {
												checkboard[ligneInitial][columnInitial] = null;
												checkboard[ligneInitial + i][columnInitial - i] = null;
												checkboard[ligneInitial + i + 1][columnInitial - i
														- 1] = PawnColor.DAME_BLACK;
												move = true;
											}
											break;
										}
									}
								}

							}
							if (move == false) {
								checkboard[ligneInitial][columnInitial] = null;
								checkboard[ligneFinal][columnFinal] = PawnColor.DAME_BLACK;
							}
						}

					}
					break;
				}

			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = getRowsNumber() - 1; i >= 0; i--) {
			// sb.append("|");
			for (int j = 0; j < getColumnsNumber(); j++) {
				if (select(j, i) == PawnColor.BLACK) {
					sb.append("o");
				} else if (select(j, i) == PawnColor.WHITE) {
					sb.append("x");
				} else if (select(j, i) == PawnColor.DAME_WHITE) {
					sb.append("X");
				} else if (select(j, i) == PawnColor.DAME_BLACK) {
					sb.append("O");
				} else {
					sb.append(" ");
				}
				// sb.append("|");
			}
			// sb.append("<br>");
		}
		return sb.toString();
	}

}
