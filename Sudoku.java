import java.util.Random;
public class Sudoku {
	private int[][] sudoku;
	private Random r=new Random();			//object of class Random to use methods for randomizing 
	private boolean permutated=false;		//boolean for random permutations (see further below at randomPermutation( (n) )
	public static void main(String[] args)
	{
		//test
		Sudoku s=new Sudoku(10);
//		s.permutateBand(2,1);
//		s.permutateRows();
//		s.permutateBand();
//		s.permutateColumns();
//		s.permutateStack();
//		s.transpose();
//		s.randomRow();
//		s.hide(50);
		System.out.println(s.toString());
	}
	/*
	 * 
| 1 2 3 | 4 5 6 | 7 8 9 | 
| 4 5 6 | 7 8 9 | 1 2 3 | 
| 7 8 9 | 1 2 3 | 4 5 6 | 
-------------------------
| 2 3 4 | 5 6 7 | 8 9 1 | 
| 5 6 7 | 8 9 1 | 2 3 4 | 
| 8 9 1 | 2 3 4 | 5 6 7 | 
-------------------------
| 3 4 5 | 6 7 8 | 9 1 2 | 
| 6 7 8 | 9 1 2 | 3 4 5 | 
| 9 1 2 | 3 4 5 | 6 7 8 | 
-------------------------

	 */
//g)
	/** constructor that generates new Sudoku with n permutations
	 *	h) assigns new values to old ones, see other constructor
	 * @param n amount of permutations to be done
	 */
	public Sudoku(int n)
	{
		int[] randomizer=randomRow();
		boolean rearranged=false;
		sudoku=new int[9][9];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				sudoku[i][j]=(3*i+i/3+j)%9+1;
//h)
				for(int k=1;k<10;k++)
				{
					if(!rearranged)
					if(sudoku[i][j]==k)
					{
						sudoku[i][j]=randomizer[k-1];
						rearranged=true;
					}
				} 
				rearranged=false;
			}
		}
		randomPermutation(n);
	}
//a)
	/**
	 * creates a predefined filled Sudoku (without //h))
	 * with h), all the same old values get replaced with new values e.g. 1 becomes a 7 in the entire sudoku
	 */
	public Sudoku()
	{
		int[] randomizer=randomRow();
		boolean rearranged=false;
		sudoku=new int[9][9];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				sudoku[i][j]=(3*i+i/3+j)%9+1;
//h)
				for(int k=1;k<10;k++)
				{
					if(!rearranged)
					if(sudoku[i][j]==k)
					{
						sudoku[i][j]=randomizer[k-1];
						rearranged=true;
					}
				} 
				rearranged=false;
			}
		}
	}
//b)
	/**
	 * returns a printable String containing the sudoku puzzle
	 */
	public String toString()
	{
		String S="-------------------------";
		for(int i=0;i<9;i++)
		{
			S+="\n| ";
			for(int j=0;j<9;j++)
			{
				if(sudoku[i][j]!=0)S+=sudoku[i][j]+" ";
				else S+="  ";
				if((j+1)%3==0)S+="| ";
			}
			if((i+1)%3==0)S+="\n-------------------------";
		}
		return S;
	}
//c)
	/**
	 * swaps columns a and b under certain conditions
	 * @param a col(umn) 1
	 * @param b	col 2
	 */
	public void permutateColumns(int a, int b)
	{
		int temp;		//temporary save for a value
		if(a<0||b<0||a>8||b>8||a==b)return;		//check if a, b inside 9x9 sudoku
		if(a>=0&&a<3&&b>=3)return;				//check if a, b are in one block
		if(a>=3&&a<6&&(b>=6||b<=2))return;
		if(a>=6&&b<6)return;
		for(int i=0;i<9;i++)
		{
			temp=sudoku[i][a];
			sudoku[i][a]=sudoku[i][b];
			sudoku[i][b]=temp;
		}
		permutated=true;
	}
	/**
	 * swaps rows a and b
	 * @param a row 1
	 * @param b row 2
	 */
	public void permutateRows(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>8||b>8||a==b)return;		//checks for valid operations 
		if(a>=0&&a<3&&b>=3)return;			
		if(a>=3&&a<6&&(b>=6||b<=2))return;
		if(a>=6&&b<6)return;
		for(int i=0;i<9;i++)
		{
			temp=sudoku[a][i];
			sudoku[a][i]=sudoku[b][i];
			sudoku[b][i]=temp;
		}
		permutated=true;
	}
//d)	
	/**
	 * swaps triplets of columns (stacks) a and b 
	 * @param a stack 1
	 * @param b stack 2
	 */
	public void permutateStack(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>2||b>2||a==b)return;		//i think you should have gotten the gist by now ;^)
		for(int c=0;c<3;c++)
		{
			for(int i=0;i<9;i++)
			{
				temp=sudoku[i][a*3+c];
				sudoku[i][a*3+c]=sudoku[i][b*3+c];
				sudoku[i][b*3+c]=temp;
			}
		}
		permutated=true;
	}
	/**
	 * swaps triplets of rows a and b
	 * @param a band 1
	 * @param b band 2
	 */
	public void permutateBand(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>2||b>2||a==b)return;		//Rockband a must not equal rockband b to not get sued and returned to jail
		for(int c=0;c<3;c++)
		{
			for(int i=0;i<9;i++)
			{
				temp=sudoku[a*3+c][i];
				sudoku[a*3+c][i]=sudoku[b*3+c][i];
				sudoku[b*3+c][i]=temp;
			}
		}
		permutated=true;
	}
//e)
	/**
	 * will definitely permutate 2 rows (by swapping them) once
	 */
	public void permutateRows()
	{
		int a=r.nextInt(9);
		int b=r.nextInt(9);
		while(!permutated)			//unless the permutating operation has been applied once, it shall be retried with another ranodm number for b
		{
			permutateRows(a,b);
			b=r.nextInt(9);
		}
		permutated=false;
	}
	/**
	 * random columnswap 
	 */
	public void permutateColumns()
	{
		int a=r.nextInt(9);
		int b=r.nextInt(9);
		while(!permutated)
		{
			permutateColumns(a,b);
			b=r.nextInt(9);
		}
		permutated=false;
	}
	/**
	 * stacks
	 */
	public void permutateStack()
	{
		int a=r.nextInt(3);
		int b=r.nextInt(3);
		while(!permutated)
		{
			permutateStack(a,b);
			b=r.nextInt(3);
		}
		permutated=false;
	}
	/**
	 * Trolling Stones
	 */
	public void permutateBand()
	{
		int a=r.nextInt(3);
		int b=r.nextInt(3);
		while(!permutated)
		{
			permutateBand(a,b);
			b=r.nextInt(3);
		}
		permutated=false;
	}
//f)
	/**
	 * mirrors the puzzle on the topleft to botright diagonal
	 */
	public void transpose()
	{
		int temp=0;
		for(int i=0;i<9;i++)
		{
			for(int j=0+i;j<9;j++)
			{
				temp=sudoku[i][j];
				sudoku[i][j]=sudoku[j][i];
				sudoku[j][i]=temp;
			}
		}
	}
//g)
	/**
	 * will do one of the 4 base mutations
	 * and afterwards there is a chance that the puzzle will get transposed as well(transposing twice is like not transposing at all, wastes time)
	 */
	public void randomPermutation()
	{
		int i=r.nextInt(4);
		switch(i)
		{
			case 0:permutateRows(); break;
			case 1:permutateColumns(); break;
			case 2:permutateStack(); break;
			case 3:permutateBand(); break;
		}
		if(r.nextInt(100)>80)transpose();
	}
	public void randomPermutation(int n)
	{
		for(int i=0;i<n;i++)
		{
			randomPermutation();
		}
	}
//h)
	/**
	 * randomizes a new order for the numbers 1-9 to create more puzzles in the constructor
	 * @return int array with the randomized order
	 */
	public int[] randomRow()
	{
		boolean reset=false;
		int nr;
		int[] randomized=new int[9];
		for(int i=0;i<9;i++)
		{
			reset=false;
			nr=r.nextInt(9)+1;
//			System.out.println("nr: "+nr);
			for(int j=0;j<i;j++)
				{
					if(randomized[j]==nr){i--;reset=true;break;}
				}
			if(reset){continue;}
			randomized[i]=nr;
			//test
//			for(int k=0;k<i+1;k++)
//			{
//				System.out.print(randomized[k]);
//			}
//			System.out.println("\ni: "+i);
		}
		return randomized;
	}
//i)
	/**
	 * hides up to n spaces in the sudoku, hiding the same square twice has absolutely no effect
	 * @param n amount of tries to hide numbers
	 */
	public void hide(int n)
	{
		int a=r.nextInt(9);
		int b=r.nextInt(9);
		for(int i=0;i<n;i++)
		{
			sudoku[a][b]=0;
			a=r.nextInt(9);
			b=r.nextInt(9);
		}
	}
}
