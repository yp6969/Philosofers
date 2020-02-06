/**
 * Tel Hai College
 *
 * @author Ron Sivan (ronny.sivan@gmail.com)
 * @date   19 May 2014 07:03:37
 *
 */
package philosophers;

import java.util.ArrayList;
import java.util.List;

public class Main
{
   /**
    * main -
    * 
    * @param args
    */
   public static void main( String[] args )
   {
      /*
       * Create an array of Fork
       */
      List<Fork> forks = new ArrayList<Fork>();
      for( int i = 0; i < Philosopher.PHIL_CNT; i++ )
         forks.add( new Fork() );

      /*
       * create the philosophers and start them running
       */
      List<Philosopher> philos = new ArrayList<Philosopher>();
      for( int i = 1; i <= Philosopher.PHIL_CNT; i++ )
         philos.add( new Bad( i, forks ) );

      for( Philosopher ph : philos )
         new Thread( ph ).start();

      /*
       * start the monitor thread to display the system state
       */
      new Thread( new Monitor( philos, forks, "Bad" ) ).start();   
   }
}
