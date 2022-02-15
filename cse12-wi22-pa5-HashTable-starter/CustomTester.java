/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * 
 * This file contains my tests for the Student, Course,
 * and Sanctuary classes. These classes are used to test
 * the various functions of HashMaps and HashSets.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains custom tests for the Student
 * Course, and Sanctuary classes. It covers tests
 * that are not covered in the public tester.
 */
public class CustomTester {

    Student stu0;
    Student stu1;
    @Before
    public void setUp() {
        stu0 = new Student("Whales", "Ocean", "A123");
        stu1 = new Student("Simon", "Belmont", "A1986");
    }

    // ----------------Student class----------------
    /**
     * Test the equals method when the two objects are unequal
     */
    @Test
    public void testEquals() {
        Student student1 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        Student student2 = new Student(new String("Test"), 
            new String("Student"), new String("A12345679"));
        assertFalse("Students should be unequal, they have different PIDs", student1.equals(student2));
    }

    /**
     * Test the compareTo method when the two objects are equal.
     */
    @Test
    public void testCompareTo() {
        Student student1 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        Student student2 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        assertTrue("Equal students should have a compareTo value of 0", student1.compareTo(student2) == 0);
        assertTrue("Equal students should have a compareTo value of 0", student2.compareTo(student1) == 0);
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when class is full
     */
    @Test
    public void testEnroll() {
        Course course = new Course("CSE", "12", "Data Structure", 1);
        course.enrolled = new HashSet<>();
        //try public tests, just to cover those edge cases
        //redundancy in tests is never bad
        assertTrue(course.enroll(stu0));
        assertTrue(course.enrolled.contains(stu0));
        assertFalse("Student should not be able to enroll in full class", course.enroll(stu1));
        assertFalse("Students who cannot enroll should not be in enrolled list", course.enrolled.contains(stu1));
    }

    /**
     * Test the unenroll method when the selected student
     * does not exist
     */
    @Test
    public void testUnenroll() {
        Course course = new Course("CSE", "12", "Data Structure", 1);
        course.enrolled = new HashSet<>();
        course.enroll(stu0);
        assertFalse("Student cannot be unenrolled if they do not exist", course.unenroll(stu1));
    }

    /**
     * Test the getRoster method on a class of students
     */
    @Test
    public void testGetRoster() {
        Course course = new Course("CSE", "12", "Data Structure", 5);
        course.enrolled = new HashSet<>();
        course.enroll(stu0);
        course.enroll(stu1);
        ArrayList<Student> expectedOutput = new ArrayList<>();
        expectedOutput.add(stu0);
        expectedOutput.add(stu1);
        Collections.sort(expectedOutput);
        assertTrue("getRoster should return an ArrayList", course.getRoster() instanceof ArrayList<?>);
        assertEquals("getRoster should return a sorted ArrayList of Students",
        expectedOutput, course.getRoster());
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor for a sanctuary of animals.
     */
    @Test
    public void testSanctuaryConstructor() {
        Sanctuary sanctuary = new Sanctuary(5, 10);
        assertTrue("Sanctuary should have maxAnimals of 5", sanctuary.maxAnimals == 5);
        assertTrue("Sanctuary should have maxSpecies of 10", sanctuary.maxSpecies == 10);
        assertTrue("Sanctuary should not have any entries", 
        sanctuary.sanctuary.keySet().size() == 0 &&
        sanctuary.sanctuary.values().size() == 0);
    }

    /**
     * Test the rescue method when species is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRescueTestOne(){
        Sanctuary sanctuary = new Sanctuary(5, 10);
        sanctuary.rescue(null, 5);
    }

    /**
     * Test the rescue method when not all animals can be rescued.
     */
    @Test
    public void testRescueTestTwo(){
        Sanctuary sanctuary = new Sanctuary(5, 10);
        sanctuary.sanctuary.put("Monkey", 3);
        assertTrue("There should be 3 animals in the sanctuary", sanctuary.getTotalAnimals() == 3);
        int failedRescue = sanctuary.rescue("Monkey", 8);
        assertTrue("Some monkeys should not have rescued", failedRescue == 6);
        assertTrue("The number of animals should be the maximum", sanctuary.getNum("Monkey") == sanctuary.maxAnimals);
    }

    /**
     * Test the release method when num is greater than 
     * the number of species at the sanctuary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReleaseTestOne(){
        Sanctuary sanctuary = new Sanctuary(5, 10);
        sanctuary.sanctuary.put("Monkey", 3);
        sanctuary.release("Monkey", 275);
    }

    /**
     * Test the release method when the species does
     * not exist at the sanctuary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReleaseTestTwo(){
        Sanctuary sanctuary = new Sanctuary(5, 10);
        sanctuary.sanctuary.put("Monkey", 3);
        sanctuary.release("Toucan", 2);
    }
}

