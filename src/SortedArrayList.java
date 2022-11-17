import java.util.*;

/** Creates ArrayList that keeps Array items in order**/

//declares SortedArrayList as subclass of ArrayList
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{
//Insert objects in arrayList
        public boolean add(E o) {
            E newArrayItem = o;

            for (int i = super.size(); i >= 0; i--) {
                //Add object if smaller than current value
                if (i == 0) {
                    super.add(0, newArrayItem);
                    break;
                }

            E compareItem = super.get(i - 1);
            if (compareItem.compareTo(newArrayItem) < 0) {
                super.add(i, newArrayItem);
                break;
            }
        }
            return true;
}
}
