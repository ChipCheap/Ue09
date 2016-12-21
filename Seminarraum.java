
public class Seminarraum {
	private int seats, maxSeats;
	//test only
	public static void main(String[] args) 
	{
		Seminarraum S1=new Seminarraum(6);
		Seminarraum S2=new Seminarraum(10);
		S1.setSeats(5);
		S2.setSeats(-3);
		S1.remSeats(2);
		S2.addSeats(3);
		System.out.println(S1.equals(S1,S2));
	}
	/**
	 * creates a new room with a max amount of seats equal to param size
	 * @param size max amount of seatspace
	 */
	public Seminarraum(int size)
	{
		maxSeats=size;
		seats=0;
	}
	/**
	 * gets amount of seats currently
	 * @return the current amount of seats inside room
	 */
	public int getSeats()
	{
		return seats;
	}
	/**
	 * sets a specific amount of seats n in the room
	 * @param n amount of seats to be in the room
	 * if n is negative, do nothing, if n bigger than max size of room, fill the room
	 */
	public void setSeats(int n)
	{
		if(n<0)
			return;
		if(n<maxSeats)
			seats=n;
		else
		{
			seats=maxSeats;
			System.out.println("Roomsize too small");
		}
	}
	/**
	 * adds in seats as long as there is room for them
	 * @param n amount of seats to add
	 */
	public void addSeats(int n)
	{
		for(int i=0;i<n;i++)
		{
			if (seats+1<=maxSeats)
			{
				seats+=1;
			}
			else
			{
				System.out.println("Too many seats added");
			}
		}
	}
	/**
	 * removes seats from room
	 * @param n amount of seats to be removed
	 */
	public void remSeats(int n)
	{
		for(int i=0;i<n;i++)
		{
			if(seats-1>=0)
			{
				seats-=1;
			}
			else
			{
				System.out.println("No seats left");
			}
		}
	}
	/**
	 * returns the values of the room as a printable String
	 * @param S the room to get the values of
	 * @return String containing values of attributes (current seats and maxSeats)
	 */
	public String toString(Seminarraum S)
	{
		return "Seats: "+S.seats+"\nSize: "+S.maxSeats;
	}
	/**
	 * compares two rooms and returns true if they contain the same amount of seats
	 * @param S1	room 1
	 * @param S2	room 2 that are to be checked on current seats
	 * @return boolean depending on seats in both rooms
	 */
	public boolean equals(Seminarraum S1, Seminarraum S2)
	{
		if(S1.seats==S2.seats)
		{
			return true;
		}
		return false;
	}
}
