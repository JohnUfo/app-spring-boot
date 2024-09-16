package uz.muydinov.app_spring_boot.controller;

import org.springframework.web.bind.annotation.*;
import uz.muydinov.app_spring_boot.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {
    static List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Umidjon", "Muydinov", new Date(), "17"),
            new Student(2, "Sevara", "Muydinova", new Date(), "79"),
            new Student(3, "Soqqi", "Boqqi", new Date(), "1"),
            new Student(4, "Botir", "Shotir", new Date(), "12")
    ));

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return students;
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") Integer id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student) {
        student.setId(students.get(students.size()-1).getId()+1);
        students.add(student);
        return "Success";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("id") Integer id) {
        boolean deleted = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                deleted = students.remove(student);
            }
        }
        return deleted ? "Success" : "Fail";
    }


    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@RequestBody Student student, @PathVariable("id") Integer id) {
        boolean updated = false;
        for (Student currentStudent : students) {
            if (currentStudent.getId().equals(id)) {
                currentStudent.setFirstName(student.getFirstName());
                currentStudent.setLastName(student.getLastName());
                currentStudent.setBirthDate(student.getBirthDate());
                currentStudent.setPhoneNumber(student.getPhoneNumber());
                updated = true;
            }
        }
        return updated ? "Success" : "Fail";
    }
}
