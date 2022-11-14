import java.util.*;

/** Creates ArrayList that keeps Array items in order**/

//declares SortedArrayList as subclass of ArrayList
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{
//Insert objects in arrayList
    public void insert(E o)
    {
        //If empty insert array value at 0
        if (size() == 0)
        {
            add(o);
            return;
        }

        for (int i = 0; i < size(); i++)
        {
            //Add object if smaller than current value
            if (o.compareTo(get(i)) < 0)
            {
                add(i, o);
                return;
            }
        }
        //add after current value if not smaller
        add(o);
    }
}
