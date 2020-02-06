/**
 * Tel Hai College
 * 
 * @author rsivan (ronny.sivan@gmail.com)
 * @date 25 May 2014 23:35:15
 */



import java.util.Random;

/**
 * RandomSleep - implement a method which suspends its caller for a 
 *               random period of time between 0 and 8 milliseconds
 *
 */
public class RandomSleep
{
   private int[] millis;
   private int[] nanos;

   private int   randlen;
   private int   randix;

   /**
    * Constructor - 
    *
    * @param seed - an integer used to both initialize the random number
    *               generator, so that instantiating the class with 
    *               different seeds produces different delays.
    *               It also determines the number of saved random values,
    *               so that distinct instantiations have different cycles,
    *               further delaying the coincidence values from diffrent
    *               instances of the class. 
    */
   public RandomSleep( int seed )
   {
      this.randlen = 100000 - seed;
      this.randix = 0;

      Random rand = new Random( seed );
      this.millis = new int[this.randlen];
      for( int i = 0; i < this.randlen; i++ )
      {
         int r = rand.nextInt() % 7;
         this.millis[i] = r > 0 ? r : -r;
      }

      this.nanos = new int[this.randlen];
      for( int i = 0; i < this.randlen; i++ )
      {
         int r = rand.nextInt() % 1000000;
         this.nanos[i] = r > 0 ? r : -r;
      }
   }

   public void sleep()
   {
      try
      {
         Thread.sleep( millis[randix], nanos[randix] );
         randix = ( randix + 1 ) % randlen;
      }
      catch( InterruptedException e )
      {
         e.printStackTrace();
      }
   }

   /**
    * main - test the class
    * 
    * @param args - not used
    */
   public static void main(String[] args)
   {
      RandomSleep rs = new RandomSleep( 1 );
      
      long last = System.currentTimeMillis();
      for( int i = 0; i < 1000; i++ )
      {
         rs.sleep();
         long curr = System.currentTimeMillis();
         System.out.println( curr - last );
         last = curr;
      }

   }

}
