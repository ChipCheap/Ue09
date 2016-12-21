
public class Xmastree {
	private int lvl;
	public String topper=" * ";
//c)	
	public static void main(String[] args)
	{
		int height;
		try
		{
			height=Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Enter a number");
			return;
		}
		
		Xmastree x = new Xmastree(height);
//		System.out.println(x.toString());
	}
//a)	
	/**
	 * creates a tree with given amount of levels
	 * @param level height of the tree
	 */
	Xmastree(int level)
	{
		lvl=level;
		System.out.println(this.toString());
	}
//b)	
	/**
	 * returns a printable String representation of the tree
	 */
	public String toString()
	{
		String S="";			//combined stages String
		String Stemp="";		//for leftside spacing
		String trunk="[_]";	
		String lvlS1="/.";		//every odd tree stage
		String lvlS2="/,.,";	//even tree stages
		int currentLvl=lvl;		//for spacings on every stage
		for(int i=0;i<lvl;i++)
		{
			//leftside spacings differ per level
			//reset spacing for next stage
			Stemp="";
			for(int j=0;j<currentLvl;j++)
			{
				Stemp+=" ";
			}
			//combine odd stages with current String
			S+="\n"+Stemp+lvlS1+"\\";
			currentLvl-=1;
			//reset again
			Stemp="";
			for(int h=0;h<currentLvl;h++)
			{
				Stemp+=" ";
			}
			//combine even stages with current String
			S+="\n"+Stemp+lvlS2+"\\";
			lvlS1+=",.";
			lvlS2+=".,";
			topper=" "+topper;		//position of the star
			//trunk changes per level
			trunk="^"+trunk+"^";
		}
		//combine trunk and topper with the rest of the tree
		return topper+S+"\n"+trunk;
	}
}
