import java.util.*;

/** Creates ArrayList that keeps Array items in order**/

//declares SortedArrayList as subclass of ArrayList
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{
    public boolean add(E e)
    {
        E Item= e;
        for (int i = super.size(); i >= 0; i--) {
            if (i == 0) {
                super.add(0, Item);
                break;
            }

            E compareItem = super.get(i-1);
            if (compareItem.compareTo(Item) < 0) {
                super.add(i, Item);
                break;
            }
        }
        return true;
    }
}
