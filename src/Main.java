import java.util.*;
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        students.put("S1", new Student("Ali", 3.5, 20));
        students.put("S2", new Student("Bek", 3.8, 21));
        students.put("S3", new Student("Aida", 3.5, 19));
        students.put("S4", new Student("Nurs", 3.9, 22));
        students.put("S5", new Student("Dana", 3.2, 20));

        System.out.println("All students:");
        for (String id : students.keySet()) {
            System.out.println(id + " -> " + students.get(id));
        }

        System.out.println("\nFind S2:");
        System.out.println(students.get("S2"));

        students.remove("S5");

        students.get("S1").setGpa(3.7);

        List<Student> list = new ArrayList<>(students.values());

        Collections.sort(list);
        System.out.println("\nSorted by GPA:");
        for (Student s : list) {
            System.out.println(s);
        }

        list.sort(Comparator.comparing(Student::getName));
        System.out.println("\nSorted by Name:");
        for (Student s : list) {
            System.out.println(s);
        }

        System.out.println("\n=== Top 3 by GPA ===");
        list.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        for (int i = 0; i < 3 && i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("\n=== Students with same GPA ===");
        HashMap<Double, List<String>> map = new HashMap<>();

        for (Student s : students.values()) {
            map.putIfAbsent(s.getGpa(), new ArrayList<>());
            map.get(s.getGpa()).add(s.getName());
        }

        for (Double gpa : map.keySet()) {
            if (map.get(gpa).size() > 1) {
                System.out.println("GPA " + gpa + " -> " + map.get(gpa));
            }
        }

        System.out.println("\n=== Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();

        Course math = new Course("Math");
        Course cs = new Course("CS");

        courseMap.put(math, new ArrayList<>());
        courseMap.put(cs, new ArrayList<>());

        courseMap.get(math).add(students.get("S1"));
        courseMap.get(math).add(students.get("S2"));
        courseMap.get(cs).add(students.get("S3"));
        courseMap.get(cs).add(students.get("S4"));

        for (Course c : courseMap.keySet()) {
            System.out.println(c);
            for (Student s : courseMap.get(c)) {
                System.out.println("  " + s);
            }
        }

        System.out.println("\n=== GPA desc + Name ===");
        list.sort((a, b) -> {
            int cmp = Double.compare(b.getGpa(), a.getGpa());
            if (cmp == 0) {
                return a.getName().compareTo(b.getName());
            }
            return cmp;
        });

        for (Student s : list) {
            System.out.println(s);
        }
    }
}