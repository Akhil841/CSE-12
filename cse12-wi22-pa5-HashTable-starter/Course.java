/**
 * Contains my implementation of Course.
 * This implementation meets the specifications
 * as outlined in PA5.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Course {
    //instance variables
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Sets various instance variables of
     * the course.
     * @param department The department code the course
     * is connected to.
     * @param number The courses's assigned number.
     * @param description The courses's description.
     * @param capacity The number of students that may
     * enroll in the course.
     */
    public Course(String department, String number, String description, 
int capacity){
        //assign instance variable arguments
        //to respective instance variables
        this.capacity = capacity;
        this.department = department;
        this.number = number;
        this.description = description;
    }

    /**
     * Returns the departmental code of
     * the course.
     * @return The departmental code of
     * the course.
     */
    public String getDepartment(){
        return this.department;
    }

    /**
     * Returns the course's assigned number.
     * @return The course's assigned number.
     */
    public String getNumber(){
        return this.number;
    }

    /**
     * Returns the courses's description.
     * @return The courses's description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the capacity of the course.
     * @return The number of students that may
     * enroll in the course.
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Attempts to enroll a {@link Student} in the course.
     * Will fail if class is full or if the student is 
     * already enrolled in the course.
     * @param student The student to enroll in this course
     * @return True if enrollment is successful, false otherwise
     * @throws IllegalArgumentException if {@code student} is null
     */
    public boolean enroll(Student student) {
        if (student == null) throw new IllegalArgumentException();
        if (!isFull() && !enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        }
        return false;
    }

    /**
     * Attempts to unenroll a {@link Student} from the course.
     * Will fail if the course does not contain the student.
     * @param student The student to unenroll.
     * @return True if unenrollment is successful, false otherwise
     * @throws IllegalArgumentException if {@code student} is null
     */
    public boolean unenroll(Student student) {
        if (student == null) throw new IllegalArgumentException();
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * Cancels the course and deletes all students from it.
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Returns whether the course is at max capacity
     * @return True if the course is full, false otherwise
     */
    public boolean isFull() {
        return enrolled.size() >= getCapacity();
    }

    /**
     * Returns the number of students enrolled in the course.
     * @return The number of students enrolled in the course.
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Returns the number of seats available for enrollment
     * @return The number of seats available for enrollment.
     */
    public int getAvailableSeats() {
        return getCapacity() - getEnrolledCount();
    }

    /**
     * Returns a {@link HashSet}<{@link Student}> of all the {@link Student}s 
     * enrolled in the course.
     * @return A {@link HashSet} of the students enrolled in the course.
     */
    public HashSet<Student> getStudents() {
        return enrolled;
    }

    /**
     * Returns a sorted {@link ArrayList}<{@link Student}> of all the 
     * {@link Student}s enrolled in the course.
     * @return A sorted list of students in the course.
     */
    public ArrayList<Student> getRoster() {
        //initialize output with the HashSet of students
        ArrayList<Student> output = new ArrayList<>(getStudents());
        //sort the output using Student.compareTo(Student)
        Collections.sort(output);
        //return output
        return output;
    }
    /**
     * Returns a textual representation of the course.
     * @returns a string, following the format "Department Number [Capacity]
     * Description".
     */
    public String toString() {
        return getDepartment() + " " + getNumber() + " [" + 
        getCapacity() + "]\n" + getDescription();
    }
}

