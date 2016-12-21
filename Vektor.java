
public class Vektor {
	private Integer[] coords;
	//test only
	public static void main(String[] args)
	{
		Integer[] i1={1,4,2,6};
		Integer[] i2={3,1,4,5,7};
		Integer[] i3={2,3,1,2};
		Integer[] i4={1,4,4,6};
		Vektor v1= new Vektor(i1);
		Vektor v2= new Vektor(i2);
		Vektor v3= new Vektor(i3);
		Vektor v4= new Vektor(5);
		Vektor v5= new Vektor(i4);
		v1.addiere(null);
		System.out.println(v1.toString()+"\n"+v2.toString());
		System.out.println(v1.addiere(v2).toString());
		System.out.println(v1.skalarprodukt(v3));
		v1.setCoords(2, 4);
		System.out.println(v1.enthaelt(3));
		System.out.println(v1.enthaelt(4));
		System.out.println(v1.toString());
		
	}
//a)	
	/**
	 * creates a null-vector
	 * @param dim dimensions of the vector
	 */	
	Vektor(int dim)
	{
		coords=new Integer[dim];
		for(int i=0;i<dim;i++)
		{
			coords[i]=0;
		}
	}
//b)	
	/**
	 * creates a new vector filled with coordinates of input array
	 * @param coordsInit values the vector shall have
	 * also sets the dimensions of the new vector
	 */
	Vektor(Integer[] coordsInit)
	{
		coords=new Integer[coordsInit.length];
		for(int i=0;i<coordsInit.length;i++)
		{
			coords[i]=coordsInit[i];
		}
	}
//c)	
	/**
	 * gets the value of the coordinate with index i
	 * @param i	index of coordinate's value to get
	 * @return value of given index
	 */
	public int getCoord(int i)
	{
		try
		{
			return coords[i];
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	/**
	 * sets coordinate i to have value n
	 * @param i	index
	 * @param n	new value
	 */
	public void setCoords(int i, int n)
	{
		try
		{
			coords[i]=n;
		}
		catch(Exception e)
		{
			System.out.println("Index not inside array");
		}
	}
//d)	
	/**
	 * gets the dimension of a vector
	 * @return dimension
	 */
	public int getDim()
	{
		return coords.length+1;
	}
	/**
	 * checks whether every single coordinate of two vectors are at the same position and have the same value
	 * @param other vector to compare with
	 * @return boolean 
	 */
	public boolean istGleich(Vektor other)
	{
		if(other==null)return false;
		if(other.getDim()==this.getDim())
		{
			for(int i=0;i<coords.length;i++)
			{
				if(other.coords[i]!=this.coords[i])
					return false;
			}
		}
		return true;
	}
//e)	
	/**
	 * if a vector contains coordinate value k, this returns its index 
	 * @param k number to check inside a vector
	 * @return index
	 */
	public Integer enthaelt(int k)
	{
		for(int i=0;i<coords.length;i++)
		{
			if(coords[i]==k)
			return i;
		}
		return null;
	}
//f)	
	/**
	 * returns a printable String of a vector
	 */
	public String toString()
	{
		String S="[ ";
			try
			{
				for (int i=0;i<coords.length;i++)
				{
					S+=coords[i]+" ";
				}
				return S+"]";
			}
			catch(Exception e)
			{
				return null;
			}
	}
//g)	
	/**
	 * addition of two vectors
	 * @param other second vector to add to the first one
	 * @return new vector
	 */
	public Vektor addiere(Vektor other)
	{
		if(other==null){System.out.println("insert vector");return null;}
		if(other.getDim()!=this.getDim())
		{
			Vektor v=new Vektor(1);
			System.out.println("different dimensions");
			return v;
		} //not returning null for testing reasons; otherwise just:			return null;
		Vektor v=new Vektor(this.coords.length);
		for(int i=0;i<coords.length;i++)
		{
			v.coords[i]=other.coords[i]+this.coords[i];
		}
		return v;
	}
//h)	
	/**
	 * multiplies all coordinates of the same index of 2 vectors and sums them up 
	 * @param other second vector
	 * @return s-product 
	 */
	public Integer skalarprodukt(Vektor other)
	{
		if(other==null)return null;
		Integer sp=0;
		if(other.getDim()!=this.getDim())return null;
		for(int i=0;i<coords.length;i++)
		{
			sp+=other.coords[i]*this.coords[i];
		}
		return sp;
	}
}
