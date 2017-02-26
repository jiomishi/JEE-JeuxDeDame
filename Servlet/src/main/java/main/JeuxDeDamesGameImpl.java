package main;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;

public class JeuxDeDamesGameImpl implements JeuxDeDamesGame  {

	


    public static final int COLUMNS_NUMBER = 10;
    public static final int ROWS_NUMBER = 10;
    public int statement=0;
    public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible to play outside of the board";

    PawnColor[][] checkboard = new PawnColor[COLUMNS_NUMBER][ROWS_NUMBER];
    
    public JeuxDeDamesGameImpl() {
        initBoard();
        
    }
    
    public JeuxDeDamesGameImpl(int i){
    	boardWin();
    }
    
    public int getStatement(){
    	return statement;
    }
    
    private void initBoard() {
    for(int i=0; i<ROWS_NUMBER; i++){
    	if(i%2==0){
    		checkboard[i][0]=PawnColor.BLACK;
    		checkboard[i][2]=PawnColor.BLACK;
    		checkboard[i][6]=PawnColor.WHITE;
    		checkboard[i][8]=PawnColor.WHITE;
    	}
    	else{
    		checkboard[i][1]=PawnColor.BLACK;
    		checkboard[i][3]=PawnColor.BLACK;
    		checkboard[i][7]=PawnColor.WHITE;
    		checkboard[i][9]=PawnColor.WHITE;
    	}
    }
    }
    
   private void boardWin(){
	   for(int i=0; i<ROWS_NUMBER; i++){
		   if(i==4){
			   checkboard[i][0]=PawnColor.BLACK; 
			   checkboard[5][1]=PawnColor.WHITE; 
		   }
	   }
   }
    
   public PawnColor getWinner(){
	   int nbWhite=countWhite();
	   int nbBlack=countBlack();
	   if(nbWhite==0){
		   return PawnColor.BLACK;
	   }
	   else{
		   if(nbBlack==0){
			   return PawnColor.WHITE;
		   }
		   else{return null;}
	   }
   }
   
  
   
    public int countWhite(){
    	int nb=0;
    	for(int i = 0; i < checkboard.length; i++) 
    	{
    		for(int j = 0; j < checkboard[i].length; j++) 
    		{ 
    			if(checkboard[i][j]==PawnColor.WHITE || checkboard[i][j]==PawnColor.DAME_WHITE)
    			{
    				nb=nb+1;
    			}
    		}
    	}
    	return nb;
    }
    
    public int countBlack(){
    	int nb=0;
    	for(int i = 0; i < checkboard.length; i++) 
    	{
    		for(int j = 0; j < checkboard[i].length; j++) 
    		{ 
    			if(checkboard[i][j]==PawnColor.BLACK || checkboard[i][j]==PawnColor.DAME_BLACK)
    			{
    				nb=nb+1;
    			}
    		}
    	}
    	return nb;
    }
    
    public void dame(){
    	int i;
    	for(i=0;i<10;i++){
    		if(checkboard[i][0]==PawnColor.WHITE)
    		{
    			checkboard[i][0]=PawnColor.DAME_WHITE;
    		}
    		else{
    				if(checkboard[i][9]==PawnColor.BLACK)
    				{
    					checkboard[i][9]=PawnColor.DAME_BLACK;
    				}
    			}
    	}
    }
    
    public int getColumnsNumber() {
        return COLUMNS_NUMBER;
    }


    public int getRowsNumber() {
    	
    	return ROWS_NUMBER;
    }
    
    public void turn(){
    	
    	System.out.println("debut du tour");
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	System.out.println("au pion blanc de jouer, choisissez un pion blanc en indicant la ligne: ");
    	int j = sc.nextInt();
    	System.out.println("la colonne: ");
    	int h = sc.nextInt();
    	if(select(j,h)==PawnColor.WHITE){
    		
    		System.out.println("indiquer la ligne ou il doit se deplacer: ");
	    	int i = sc.nextInt();
	    	System.out.println("la colonne: ");
	    	int k = sc.nextInt();
	    	move(j,h,i,k);

	    	System.out.println("au pion noir de jouer, choisissez un pion blanc en indicant la ligne: ");
	    	j = sc.nextInt();
	    	System.out.println("la colonne: ");
	    	h = sc.nextInt();
	    	
	    	if(select(j,h)==PawnColor.BLACK){
	    		
	    		System.out.println("indiquer la ligne ou il doit se deplacer: ");
		    	i = sc.nextInt();
		    	System.out.println("la colonne: ");
		    	k = sc.nextInt();
		    	move(j,h,i,k);
		    
	    	}
	    	else
	    	{
	    		System.out.println("selectionnez un noir, les ronds quoi !");
	    	}
	    	
    	}
    	else 
    	{
    		System.out.println("selectionnez un blanc, les croix quoi !");
    	}
    		
    	
    	
    }
    
    public PawnColor select(int ligneInitial, int columnInitial)
    {
    	if (ligneInitial < 0 || ligneInitial >= getColumnsNumber() || columnInitial < 0 || columnInitial >= getColumnsNumber()) {
    		return null;
        }
    	
    	else{
    		if(checkboard[ligneInitial][columnInitial]!=null ){
    			PawnColor pawnSelect = checkboard[ligneInitial][columnInitial];
    			return pawnSelect;
    		}
    		else{return null;}
    	}
    }
    
    public void move( int ligneInitial, int columnInitial, int ligneFinal, int columnFinal)
    {
    	
    	if (ligneFinal < 0 || ligneFinal >= getColumnsNumber() || columnFinal < 0 || columnFinal >= getColumnsNumber())
    	{
    		statement=1;
    		System.out.println("vous ne vous echapperez pas du plateau !!");
    	}
    	
    	else
    	{	
    		
    		if(checkboard[ligneInitial][columnInitial]==null)
			{	
    			statement=2;
    			System.out.println("vous ne selectionnez pas de pion");
			}
    		else{
			
    		switch(checkboard[ligneInitial][columnInitial])
    		{
    		case WHITE:
    			if((ligneFinal==ligneInitial+1 && columnFinal==columnInitial-1) ||(ligneFinal==ligneInitial-1 && columnFinal==columnInitial-1))
    			{	
    				if(checkboard[ligneFinal][columnFinal]==null)
    				{
    					checkboard[ligneFinal][columnFinal]=checkboard[ligneInitial][columnInitial];
    					checkboard[ligneInitial][columnInitial]=null;
    				}
    				else
    				{
    					switch(checkboard[ligneFinal][columnFinal])
    					{
    						case BLACK:
    						
    							if(ligneFinal+1<10 && columnFinal-1>=0 && ligneFinal==ligneInitial+1 && columnFinal==columnInitial-1 && checkboard[ligneFinal+1][columnFinal-1]==null )
    							{
    								
    									if(ligneFinal+2<10 && columnFinal-2>=0 && ligneFinal+3<10 && columnFinal-3>=0 && checkboard[ligneFinal+2][columnFinal-2]==PawnColor.BLACK && checkboard[ligneFinal+3][columnFinal-3]==null )
    									{
    										checkboard[ligneFinal+3][columnFinal-3]=checkboard[ligneInitial][columnInitial];
    										checkboard[ligneFinal][columnFinal]=null;
    										checkboard[ligneFinal+2][columnFinal-2]=null;
    										checkboard[ligneInitial][columnInitial]=null;
    									}
    								
    								else
    								{
    										
    									if(ligneFinal-1>=0 && columnFinal-2>=0 && columnFinal-3>=0 &&checkboard[ligneFinal][columnFinal-2]==PawnColor.BLACK && checkboard[ligneFinal-1][columnFinal-3]==null)
    										{
    											checkboard[ligneFinal-1][columnFinal-3]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneFinal][columnFinal-2]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    										else
    										{
    											checkboard[ligneFinal+1][columnFinal-1]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    									}
    								
    								}
    							else 
    							{
    								if(ligneFinal-1>=0 && columnFinal-1>=0 && ligneFinal==ligneInitial-1 && columnFinal==columnInitial-1 && checkboard[ligneFinal-1][columnFinal-1]==null)
    								{
    										if(ligneFinal-2>=0 && columnFinal-2>=0 && ligneFinal-3>=0 && columnFinal-3>=0 && checkboard[ligneFinal-2][columnFinal-2]==PawnColor.BLACK && checkboard[ligneFinal-3][columnFinal-3]==null )
    										{
    											checkboard[ligneFinal-3][columnFinal-3]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneFinal-2][columnFinal-2]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    									
    									else
    									{
    										if(columnFinal-3>=0 && columnFinal-2>=0 && checkboard[ligneFinal][columnFinal-2]==PawnColor.BLACK && checkboard[ligneInitial][columnFinal-3]==null)
    										{
    											checkboard[ligneFinal+1][columnFinal-3]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneFinal][columnFinal-2]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    										else
    										{
    											checkboard[ligneFinal-1][columnFinal-1]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    									}
    								}
    								else
    								{	
    									statement=3;
    									System.out.println("il y a un pion adverse qui protege ce pion");
    								}
    							}
    						
    						break;
    					
    						case WHITE:
    							statement=4;
    							System.out.println("vous avez deja un pion sur cette case");
    							break;
    					}
    				}
    			}
    			else
    			{
    				statement=5;
    				System.out.println("ce deplacement n'est pas autorisé");
    			}
    		break;
    		case BLACK:
    		
    				if((ligneFinal==ligneInitial+1 && columnFinal==columnInitial+1) ||(ligneFinal==ligneInitial-1 && columnFinal==columnInitial+1))
    				{
    					if(checkboard[ligneFinal][columnFinal]==null)
    					{
    						checkboard[ligneFinal][columnFinal]=checkboard[ligneInitial][columnInitial];
    						checkboard[ligneInitial][columnInitial]=null;
    					}
    					else
    					{
    						switch(checkboard[ligneFinal][columnFinal])
    						{
    				
    							case BLACK:
    								statement=4;
    								System.out.println("vous avez deja un pion sur cette case");
    							break;
    							case WHITE:
    								if(columnFinal+1<10 && ligneFinal+1<10 && ligneFinal==ligneInitial+1 && columnFinal==columnInitial+1 && checkboard[ligneFinal+1][columnFinal+1]==null )
    								{
    									if(columnFinal+2<10 && ligneFinal+2<10 && columnFinal+3<10 && ligneFinal+3<10 &&checkboard[ligneFinal+2][columnFinal+2]==PawnColor.WHITE && checkboard[ligneFinal+3][columnFinal+3]==null )
    									{
    										checkboard[ligneFinal+3][columnFinal+3]=checkboard[ligneInitial][columnInitial];
    										checkboard[ligneFinal][columnFinal]=null;
    										checkboard[ligneFinal+2][columnFinal+2]=null;
    										checkboard[ligneInitial][columnInitial]=null;
    									}
    									else
    									{
    										if(columnFinal+2<10 && ligneFinal+3<10 && ligneFinal-1>=0 && checkboard[ligneFinal][columnFinal+2]==PawnColor.WHITE && checkboard[ligneFinal-1][columnFinal+3]==null)
    										{
    											checkboard[ligneFinal-1][columnFinal+3]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneFinal][columnFinal+2]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    										else
    										{
    											checkboard[ligneFinal+1][columnFinal+1]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
	    						
    									}
	    					
    								}
    								if(ligneFinal==ligneInitial-1 && columnFinal==columnInitial+1 && checkboard[ligneFinal-1][columnFinal+1]==null )
    								{
    									
    									if(ligneFinal-3>=0 && columnFinal+3<10 && ligneFinal-2>=0 && columnFinal+2<10 && checkboard[ligneFinal-2][columnFinal+2]==PawnColor.WHITE && checkboard[ligneFinal-3][columnFinal+3]==null )
    									{
    										checkboard[ligneFinal-3][columnFinal+3]=checkboard[ligneInitial][columnInitial];
    										checkboard[ligneFinal][columnFinal]=null;
    										checkboard[ligneFinal-2][columnFinal+2]=null;
    										checkboard[ligneInitial][columnInitial]=null;
    									}
    									else
    									{
    										if(columnFinal+3<10 && columnFinal+2<10 && checkboard[ligneFinal][columnFinal+2]==PawnColor.WHITE && checkboard[ligneInitial][columnFinal+3]==null)
    										{
    											checkboard[ligneFinal+1][columnFinal+3]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneFinal][columnFinal+2]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    										else
    										{
    											checkboard[ligneFinal-1][columnFinal+1]=checkboard[ligneInitial][columnInitial];
    											checkboard[ligneFinal][columnFinal]=null;
    											checkboard[ligneInitial][columnInitial]=null;
    										}
    									}
    								}
    								else
    								{
    									statement=3;
    									System.out.println("il y a un pion adverse qui protege ce pion");}
    								}
	    					
    							break;
    						}
    					}
    					else
    					{	statement=5;
    						System.out.println("ce deplacement n'est pas autorisé");
    					}
    				break;
    		case DAME_WHITE:
				
				if(Math.abs(ligneInitial-ligneFinal)==Math.abs(columnInitial-columnFinal))
				{	
					int i;
					if( ligneFinal>ligneInitial && columnFinal>columnInitial)
					{
						boolean move=false;
						for(i=1;i<=(columnFinal-columnInitial+1);i++)
						{	
							if(ligneInitial+i<=8 && columnInitial+i<=8){
							if(checkboard[ligneInitial+i][columnInitial+i]!=null)
							{
							switch(checkboard[ligneInitial+i][columnInitial+i])
							{
							case WHITE:
								statement=4;
								System.out.println("vous avez deja un pion sur cette case");
								move=true;
								break;
							case BLACK:
								if(checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_WHITE)
								{
									statement=3;
									System.out.println("il y a un pion adverse qui protege ce pion");
									move=true;
								}
								else
								{	
									checkboard[ligneInitial][columnInitial]=null;
									checkboard[ligneInitial+i][columnInitial+i]=null;
									checkboard[ligneInitial+i+1][columnInitial+i+1]=PawnColor.DAME_WHITE;
									move=true;
								}
								break;
							case DAME_WHITE:
								statement=4;
								System.out.println("vous avez deja un pion sur cette case");
								move=true;
								break;
							case DAME_BLACK:
								if(checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_WHITE)
								{
									statement=3;
									System.out.println("il y a un pion adverse qui protege ce pion");
									move=true;
								}
								else
								{
									checkboard[ligneInitial][columnInitial]=null;
									checkboard[ligneInitial+i][columnInitial+i]=null;
									checkboard[ligneInitial+i+1][columnInitial+i+1]=PawnColor.DAME_WHITE;
									move=true;
								}
								break;
								}
							}
							}
						}
						if(move==false)
							{
								checkboard[ligneInitial][columnInitial]=null;
							checkboard[ligneFinal][columnFinal]=PawnColor.DAME_WHITE;
							}
					}
					
					if( ligneFinal<ligneInitial && columnFinal<columnInitial)
					{	
						
						boolean move=false;
						for(i=1;i<(columnInitial-columnFinal+1);i++)
						{	
							if(ligneInitial-i<=8 && columnInitial-i<=8){
							if(checkboard[ligneInitial-i][columnInitial-i]!=null)
							{
								switch(checkboard[ligneInitial-i][columnInitial-i])
								{
								case WHITE:
									statement=4;
									System.out.println("vous avez deja un pion sur cette case");
									move=true;
									break;
								case BLACK:
									if(checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_WHITE)
									{
										statement=3;
										System.out.println("il y a un pion adverse qui protege ce pion");
										move=true;
									}
									else
									{	
										checkboard[ligneInitial][columnInitial]=null;
										checkboard[ligneInitial-i][columnInitial-i]=null;
										checkboard[ligneInitial-i-1][columnInitial-i-1]=PawnColor.DAME_WHITE;
										move=true;
									}
									break;
								case DAME_WHITE:
									statement=4;
									System.out.println("vous avez deja un pion sur cette case");
									move=true;
									break;
								case DAME_BLACK:
									if(checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_WHITE)
									{
										statement=3;
										System.out.println("il y a un pion adverse qui protege ce pion");
										move=true;
									}
									else
									{
										checkboard[ligneInitial][columnInitial]=null;
										checkboard[ligneInitial-i][columnInitial-i]=null;
										checkboard[ligneInitial-i-1][columnInitial-i-1]=PawnColor.DAME_WHITE;
										move=true;
									}
									break;
								}
							}
						}
						}
						if(move==false)
						{
							checkboard[ligneInitial][columnInitial]=null;
							checkboard[ligneFinal][columnFinal]=PawnColor.DAME_WHITE;
						}
							
					}
					
					if( ligneFinal<ligneInitial && columnFinal>columnInitial)
					{	
						boolean move=false;
						for(i=1;i<=(columnFinal-columnInitial+1);i++)
						{
							if(ligneInitial-i<=8 && columnInitial+i<=8){
							if(checkboard[ligneInitial-i][columnInitial+i]!=null)
							{
							switch(checkboard[ligneInitial-i][columnInitial+i])
							{
							case WHITE:
								statement=4;
								System.out.println("vous avez deja un pion sur cette case");
								move=true;
								break;
							case BLACK:
								if(checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_WHITE)
								{
									statement=3;
									System.out.println("il y a un pion adverse qui protege ce pion");
									move=true;
								}
								else
								{	
									checkboard[ligneInitial][columnInitial]=null;
									checkboard[ligneInitial-i][columnInitial+i]=null;
									checkboard[ligneInitial-i-1][columnInitial+i+1]=PawnColor.DAME_WHITE;
									move=true;
								}
								break;
							case DAME_WHITE:
								statement=4;
								System.out.println("vous avez deja un pion sur cette case");
								move=true;
								break;
							case DAME_BLACK:
								if(checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_WHITE)
								{
									statement=3;
									System.out.println("il y a un pion adverse qui protege ce pion");
									move=true;
								}
								else
								{
									checkboard[ligneInitial][columnInitial]=null;
									checkboard[ligneInitial-i][columnInitial+i]=null;
									checkboard[ligneInitial-i-1][columnInitial+i+1]=PawnColor.DAME_WHITE;
									move=true;
								}
								break;
							}
						}
					}
						}
						if(move==false)
						{
						checkboard[ligneInitial][columnInitial]=null;
						checkboard[ligneFinal][columnFinal]=PawnColor.DAME_WHITE;
						}
					}
					
					if( ligneFinal>ligneInitial && columnFinal<columnInitial)
					{
						boolean move=false;
						for(i=1;i<=(ligneFinal-ligneInitial+1);i++)
						{
							if(ligneInitial+i<=8 && columnInitial-i<=8){		
							if(checkboard[ligneInitial+i][columnInitial-i]!=null)
							{
								switch(checkboard[ligneInitial+i][columnInitial-i])
								{
								case WHITE:
									statement=4;
									System.out.println("vous avez deja un pion sur cette case");
									move=true;
									break;
								case BLACK:
									
									if(checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_WHITE)
									{
										statement=3;
										System.out.println("il y a un pion adverse qui protege ce pion");
										move=true;
									}
									else
									{	
										
										checkboard[ligneInitial][columnInitial]=null;
										checkboard[ligneInitial+i][columnInitial-i]=null;
										checkboard[ligneInitial+i+1][columnInitial-i-1]=PawnColor.DAME_WHITE;
										move=true;
									}
								break;
								case DAME_WHITE:
									statement=4;
									System.out.println("vous avez deja un pion sur cette case");
									move=true;
								break;
								case DAME_BLACK:
									if(checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_WHITE)
									{
										statement=3;
										System.out.println("il y a un pion adverse qui protege ce pion");
										move=true;
									}
									else
									{
										checkboard[ligneInitial][columnInitial]=null;
										checkboard[ligneInitial+i][columnInitial-i]=null;
										checkboard[ligneInitial+i+1][columnInitial-i-1]=PawnColor.DAME_WHITE;
										move=true;
									}
								break;
								}
							}
						}
						
					}
						if(move==false)
						{
							checkboard[ligneInitial][columnInitial]=null;
							checkboard[ligneFinal][columnFinal]=PawnColor.DAME_WHITE;
						}
					}
					}	
    				break;
    				case DAME_BLACK:
    					
    					if(Math.abs(ligneInitial-ligneFinal)==Math.abs(columnInitial-columnFinal))
    					{	
    						int i;
    						if( ligneFinal>ligneInitial && columnFinal>columnInitial)
    						{
    							boolean move=false;
    							for(i=1;i<=(columnFinal-columnInitial+1);i++)
    							{	
    								if(ligneInitial+i<=8 && columnInitial+i<=8){
    								if(checkboard[ligneInitial+i][columnInitial+i]!=null)
    								{
    								switch(checkboard[ligneInitial+i][columnInitial+i])
    								{
    								case BLACK:
    									statement=4;
    									System.out.println("vous avez deja un pion sur cette case");
    									move=true;
    									break;
    								case WHITE:
    									if(checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_WHITE)
    									{
    										statement=3;
    										System.out.println("il y a un pion adverse qui protege ce pion");
    										move=true;
    									}
    									else
    									{	
    										checkboard[ligneInitial][columnInitial]=null;
    										checkboard[ligneInitial+i][columnInitial+i]=null;
    										checkboard[ligneInitial+i+1][columnInitial+i+1]=PawnColor.DAME_BLACK;
    										move=true;
    									}
    									break;
    								case DAME_BLACK:
    									statement=4;
    									System.out.println("vous avez deja un pion sur cette case");
    									move=true;
    									break;
    								case DAME_WHITE:
    									if(checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial+i+1]==PawnColor.DAME_WHITE)
    									{
    										statement=3;
    										System.out.println("il y a un pion adverse qui protege ce pion");
    										move=true;
    									}
    									else
    									{
    										checkboard[ligneInitial][columnInitial]=null;
    										checkboard[ligneInitial+i][columnInitial+i]=null;
    										checkboard[ligneInitial+i+1][columnInitial+i+1]=PawnColor.DAME_BLACK;
    										move=true;
    									}
    									break;
    									}
    								}
    								}
    							}
    							if(move==false)
									{
										checkboard[ligneInitial][columnInitial]=null;
									checkboard[ligneFinal][columnFinal]=PawnColor.DAME_BLACK;
									}
    						}
    						
    						if( ligneFinal<ligneInitial && columnFinal<columnInitial)
    						{	
    							
    							boolean move=false;
    							for(i=1;i<(columnInitial-columnFinal+1);i++)
    							{	
    								if(ligneInitial-i<=8 && columnInitial-i<=8){
    								if(checkboard[ligneInitial-i][columnInitial-i]!=null)
    								{
    									switch(checkboard[ligneInitial-i][columnInitial-i])
    									{
    									case BLACK:
    										statement=4;
    										System.out.println("vous avez deja un pion sur cette case");
    										move=true;
    										break;
    									case WHITE:
    										if(checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_WHITE)
    										{
    											statement=3;
    											System.out.println("il y a un pion adverse qui protege ce pion");
    											move=true;
    										}
    										else
    										{	
    											checkboard[ligneInitial][columnInitial]=null;
    											checkboard[ligneInitial-i][columnInitial-i]=null;
    											checkboard[ligneInitial-i-1][columnInitial-i-1]=PawnColor.DAME_BLACK;
    											move=true;
    										}
    										break;
    									case DAME_BLACK:
    										statement=4;
    										System.out.println("vous avez deja un pion sur cette case");
    										move=true;
    										break;
    									case DAME_WHITE:
    										if(checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial-i-1]==PawnColor.DAME_WHITE)
    										{
    											statement=3;
    											System.out.println("il y a un pion adverse qui protege ce pion");
    											move=true;
    										}
    										else
    										{
    											checkboard[ligneInitial][columnInitial]=null;
    											checkboard[ligneInitial-i][columnInitial-i]=null;
    											checkboard[ligneInitial-i-1][columnInitial-i-1]=PawnColor.DAME_BLACK;
    											move=true;
    										}
    										break;
    									}
    								}
    							}
    							}
    							if(move==false)
    							{
    								checkboard[ligneInitial][columnInitial]=null;
    								checkboard[ligneFinal][columnFinal]=PawnColor.DAME_BLACK;
    							}
    								
    						}
    						
    						if( ligneFinal<ligneInitial && columnFinal>columnInitial)
    						{	
    							boolean move=false;
    							for(i=1;i<=(columnFinal-columnInitial+1);i++)
    							{
    								if(ligneInitial-i<=8 && columnInitial+i<=8){
    								if(checkboard[ligneInitial-i][columnInitial+i]!=null)
    								{
    								switch(checkboard[ligneInitial-i][columnInitial+i])
    								{
    								case BLACK:
    									statement=4;
    									System.out.println("vous avez deja un pion sur cette case");
    									move=true;
    									break;
    								case WHITE:
    									if(checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_WHITE)
    									{
    										statement=3;
    										System.out.println("il y a un pion adverse qui protege ce pion");
    										move=true;
    									}
    									else
    									{	
    										checkboard[ligneInitial][columnInitial]=null;
    										checkboard[ligneInitial-i][columnInitial+i]=null;
    										checkboard[ligneInitial-i-1][columnInitial+i+1]=PawnColor.DAME_BLACK;
    										move=true;
    									}
    									break;
    								case DAME_BLACK:
    									statement=4;
    									System.out.println("vous avez deja un pion sur cette case");
    									move=true;
    									break;
    								case DAME_WHITE:
    									if(checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_BLACK || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.WHITE || checkboard[ligneInitial-i-1][columnInitial+i+1]==PawnColor.DAME_WHITE)
    									{
    										statement=3;
    										System.out.println("il y a un pion adverse qui protege ce pion");
    										move=true;
    									}
    									else
    									{
    										checkboard[ligneInitial][columnInitial]=null;
    										checkboard[ligneInitial-i][columnInitial+i]=null;
    										checkboard[ligneInitial-i-1][columnInitial+i+1]=PawnColor.DAME_BLACK;
    										move=true;
    									}
    									break;
    								}
    							}
    						}
    							}
    							if(move==false)
    							{
    							checkboard[ligneInitial][columnInitial]=null;
								checkboard[ligneFinal][columnFinal]=PawnColor.DAME_BLACK;
    							}
    						}
    						
    						if( ligneFinal>ligneInitial && columnFinal<columnInitial)
    						{
    							boolean move=false;
    							for(i=1;i<=(ligneFinal-ligneInitial+1);i++)
    							{
    								if(ligneInitial+i<=8 && columnInitial-i<=8){		
    								if(checkboard[ligneInitial+i][columnInitial-i]!=null)
    								{
    									switch(checkboard[ligneInitial+i][columnInitial-i])
    									{
    									case BLACK:
    										statement=4;
    										System.out.println("vous avez deja un pion sur cette case");
    										move=true;
    										break;
    									case WHITE:
    										
    										if(checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_WHITE)
    										{
    											statement=3;
    											System.out.println("il y a un pion adverse qui protege ce pion");
    											move=true;
    										}
    										else
    										{	
    											
    											checkboard[ligneInitial][columnInitial]=null;
    											checkboard[ligneInitial+i][columnInitial-i]=null;
    											checkboard[ligneInitial+i+1][columnInitial-i-1]=PawnColor.DAME_BLACK;
    											move=true;
    										}
    									break;
    									case DAME_BLACK:
    										statement=4;
    										System.out.println("vous avez deja un pion sur cette case");
    										move=true;
    									break;
    									case DAME_WHITE:
    										if(checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_BLACK || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.WHITE || checkboard[ligneInitial+i+1][columnInitial-i-1]==PawnColor.DAME_WHITE)
    										{
    											statement=3;
    											System.out.println("il y a un pion adverse qui protege ce pion");
    											move=true;
    										}
    										else
    										{
    											checkboard[ligneInitial][columnInitial]=null;
    											checkboard[ligneInitial+i][columnInitial-i]=null;
    											checkboard[ligneInitial+i+1][columnInitial-i-1]=PawnColor.DAME_BLACK;
    											move=true;
    										}
    									break;
    									}
    								}
    							}
    							
    						}
    							if(move==false)
    							{
    								checkboard[ligneInitial][columnInitial]=null;
    								checkboard[ligneFinal][columnFinal]=PawnColor.DAME_BLACK;
    							}
    					}
    				
    			}	
    					break;
    		}
    		
    	}
    }
    }
    		
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = getRowsNumber() - 1; i >= 0; i--) {
            sb.append("|");
            for (int j = 0; j < getColumnsNumber(); j++) {
                if (select(j, i) == PawnColor.BLACK) {
                    sb.append("O ");
                } else if (select(j, i) == PawnColor.WHITE) {
                    sb.append("X ");
                } else if (select(j, i) == PawnColor.DAME_WHITE) {
                    sb.append("XX");
                }else if (select(j, i) == PawnColor.DAME_BLACK) {
                    sb.append("OO");
                }else {
                    sb.append("  ");
                }
                sb.append("|");
            }
            sb.append("\n");
        }
        for (int i = 0; i < getRowsNumber() * 2 + 3; i++) {
            sb.append("-");
        }
        return sb.toString();
    }
    
    
    public String toHtmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
        sb.append("<td> </td>");
        for(int h =0; h<=getColumnsNumber()-1; h++){
        	sb.append("<td>"+h+"</td>");
        }
        sb.append("</tr>");
        
        for (int i = getRowsNumber() - 1; i >= 0; i--) {
            sb.append("<tr>");
            sb.append("<td>"+i+"</td>");
            for (int j = 0; j < getColumnsNumber(); j++) {
                if (select(j, i) == PawnColor.BLACK) {
                    sb.append("<td class='circle-Black'>");
                } else if (select(j, i) == PawnColor.WHITE) {
                    sb.append("<td class='circle-White'>");
                } else if (select(j, i) == PawnColor.DAME_WHITE) {
                    sb.append("<td class='circle-White'>W");
                }else if (select(j, i) == PawnColor.DAME_BLACK) {
                    sb.append("<td class='circle-Black'>B");
                }else {
                    sb.append("<td> ");
                }
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        
        return sb.toString();
    }
    	

}
