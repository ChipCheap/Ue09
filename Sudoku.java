import java.util.Random;
public class Sudoku {
	private int[][] sudoku;
	private Random r=new Random();
	private boolean permutated=false;
	public static void main(String[] args)
	{
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
	 * creates a predefined filled Sudoku
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
	public void permutateColumns(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>8||b>8||a==b)return;
		if(a>=0&&a<3&&b>=3)return;
		if(a>=3&&a<6&&(b>=6||b<=4))return;
		if(a>=6&&b<6)return;
		for(int i=0;i<9;i++)
		{
			temp=sudoku[i][a];
			sudoku[i][a]=sudoku[i][b];
			sudoku[i][b]=temp;
		}
		permutated=true;
	}
	public void permutateRows(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>8||b>8||a==b)return;		//check if a, b inside 9x9 sudoku
		if(a>=0&&a<3&&b>=3)return;			//check if a, b are in one block
		if(a>=3&&a<6&&(b>=6||b<=4))return;
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
	public void permutateStack(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>2||b>2||a==b)return;
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
	public void permutateBand(int a, int b)
	{
		int temp;
		if(a<0||b<0||a>2||b>2||a==b)return;
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
	public void permutateRows()
	{
		int a=r.nextInt(9);
		int b=r.nextInt(9);
		while(!permutated)
		{
			System.out.println("Rows err");
			System.out.println("a: "+a);
			System.out.println("b: "+b);
			permutateRows(a,b);
			b=r.nextInt(9);
		}
		permutated=false;
		
	}
	public void permutateColumns()
	{
		int a=r.nextInt(9);
		int b=r.nextInt(9);
		while(!permutated)
		{
			System.out.println("Coll err");
			permutateColumns(a,b);
			b=r.nextInt(9);
		}
		permutated=false;
	}
	public void permutateStack()
	{
		int a=r.nextInt(3);
		int b=r.nextInt(3);
		while(!permutated)
		{
			System.out.println("Stack err");
			permutateStack(a,b);
			b=r.nextInt(3);
		}
		permutated=false;
	}
	public void permutateBand()
	{
		System.out.println("Band err");
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
	public void randomPermutation()
	{
		System.out.println("works");
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
			System.out.println("test");
			randomPermutation();
		}
	}
//h)
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
//t-d: permutations are erroring at times, fix, probably if(..) return; wrong somewhere
