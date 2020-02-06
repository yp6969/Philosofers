/**
 * Tel Hai College
 * 
 * @author Ron Sivan (ronny.sivan@gmail.com)
 * @date 25 May 2014 20:50:44
 */


import java.util.List;
import java.util.concurrent.locks.Lock;

public class Good implements Philosopher
{

   private int         place;
   private List<Fork>  forks;
   private boolean     eating;
   private int         count;
   private Lock       lock;
   private RandomSleep rs;

   public Good( int place, List<Fork> forks, Lock lock )
   {
      this.place = place;
      this.forks = forks;
      this.eating = false;
      this.count = 0;
      this.lock = lock;
      this.rs = new RandomSleep( place );
   }

   /*
    * (non-Javadoc) run -
    * 
    * @see java.lang.Runnable#run()
    */
   @Override
   public void run()
   {
      while( true )
	 {
	    rs.sleep(); // Thinking

	    Fork left = forks.get( place % forks.size() );
	    Fork right = forks.get( ( place + 1 ) % forks.size() );

	    lock.lock();

	    synchronized( left )
	    {
	       left.setOwner( place );
	       synchronized( right )
	       {
	          lock.unlock();

	          right.setOwner( place );
	          eating = true;
	          count++;
	          rs.sleep();
	          eating = false;
	          right.setOwner( 0 );
	          left.setOwner( 0 );
	       }
	    }

	 }

   }

   /**
    * @return the place
    */
   public int getPlace()
   {
      return place;
   }

   /**
    * @return the eating
    */
   public boolean isEating()
   {
      return eating;
   }

   @Override
   public int getCount()
   {
      return( count );
   }

}
