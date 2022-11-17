import java.util.*;

/** Uses insertion method to create an arrayList that is sorted in
 * lexicographical order.  Used by the main program to sort activities
 * and customers in order.
 *
 * Code inspired by GeeksforGeeks articles found below. Edited to fit my
 * program by Gregor Gray (myself)
 * original source :<a href="https://www.geeksforgeeks.org/insertion-sort/"></a>
 **/

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{
//Insert array using insertion sort
        public boolean add(E o)
        {
            E newArrayItem = o;
            for (int i = super.size(); i >= 0; i--) //Adds element if less than the key
            {
                if (i == 0) //add element at the start of array if none found
                {
                    super.add(0, newArrayItem);
                    break;
                }

            E compareItem = super.get(i - 1);// get previous element in array
            if (compareItem.compareTo(newArrayItem) < 0)
                {
                    super.add(i, newArrayItem);// adds if index matches element
                    break;
                }
            }
            return true;
        }
}
