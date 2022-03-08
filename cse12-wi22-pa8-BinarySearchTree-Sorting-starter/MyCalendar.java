import java.util.ArrayList;
/**
 * Implementation of a binary search tree.
 * Name: Akhil Pillai
 * PID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: MyTreeMap
 * 
 * Contains an implementation of a calendar using MyTreeMap,
 * graciously provided by Jianwei Gong. The calendar is capable
 * of adding bookings or returning itself.
 */

/**
 * A calendar that stores bookings using MyTreeMap.
 * Contains a smart booking method that prevents double bookings.
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Constructs an empty calendar.
     */
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }
    
    /**
     * Attempts to book an event. Returns true if booking was successful,
     * false if the booking conflicts with existing bookings.
     * @param start The start of the event.
     * @param end The end of the event.
     * @return whether the booking was successful
     * @throws IllegalStateException if start is negative
     * or after end
     */
    public boolean book(int start, int end) {
        //exception handling
        if (start < 0 || start >= end) throw new IllegalStateException();
        //get all the bookings in order
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> preStarts = calendar.inorder();
        //store the start times of all the bookings
        ArrayList<Integer> starts = new ArrayList<>();
        //store the end times of all the bookings
        ArrayList<Integer> ends = new ArrayList<>();
        //for each existing booking
        for (int i = 0; i < preStarts.size(); i++) {
            //add the start time to the start list
            starts.add(preStarts.get(i).getKey());
            //add the end time to the end list
            ends.add(preStarts.get(i).getValue());
        }
        //store whether insertion is successful here
        boolean insertable = true;
        //for each start time
        for (int i = 0; i < starts.size(); i++) {
            //if the new booking's start is between the start and the end
            //of an existing booking
            if (starts.get(i) <= start && start < ends.get(i)) {
                //return false since these would be conflicting times
                insertable = false;
                //break out of the for loop for efficiency
                break;
            }
            //if the new booking's end is between the start and the end
            //of an existing booking
            if (starts.get(i) < end && end <= ends.get(i)) {
                //return false since these would be conflicting times
                insertable = false;
                //break out of the for loop for efficiency
                break;
            }
        }
        //insert booking into the calendar if possible
        if (insertable) calendar.insert(start, end);
        //return whether insertion was successful
        return insertable;
    }

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}