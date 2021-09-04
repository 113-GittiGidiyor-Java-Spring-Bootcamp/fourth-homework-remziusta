package com.sms.mappers;

import com.sms.dto.*;
import com.sms.model.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Mapper
@Component
public class MapStructMapperImp implements MapStructMapper{
    @Override
    public Student studentPostDtoToStudent(StudentPostDTO studentPostDTO) {
        if(studentPostDTO == null) return null;

        Student s = new Student();
        s.setName(studentPostDTO.getName());
        s.setBirthDate(studentPostDTO.getBirthDate());
        s.setAddress(studentPostDTO.getAddress());
        s.setGender(studentPostDTO.getGender());

        return s;
    }

    @Override
    public StudentGetDTO studentToStudentGetDto(Student student) {
        if (student == null) return null;

        StudentGetDTO studentGetDTO = new StudentGetDTO();
        studentGetDTO.setId(student.getId());
        studentGetDTO.setName(student.getName());
        studentGetDTO.setBirthDate(student.getBirthDate());
        studentGetDTO.setAddress(student.getAddress());
        studentGetDTO.setGender(student.getGender());
        studentGetDTO.setCourses(studentSetToStudentGetDtoSet(student.getCourses()));
        return studentGetDTO;
    }



    @Override
    public StudentSlimDTO studentToStudentSlimDto(Student student) {
        if(student == null) return null;

        StudentSlimDTO studentSlimDTO = new StudentSlimDTO();
        studentSlimDTO.setId(student.getId());
        studentSlimDTO.setName(student.getName());
        studentSlimDTO.setBirthDate(student.getBirthDate());
        return studentSlimDTO;
    }

    @Override
    public CourseSlimDTO courseToCourseSlimDto(Course course) {
        if(course == null) return null;

        CourseSlimDTO courseSlimDTO = new CourseSlimDTO();
        courseSlimDTO.setId(course.getId());
        courseSlimDTO.setCourseName(course.getCourseName());
        return courseSlimDTO;
    }

    @Override
    public Course coursePostDtoToCourse(CoursePostDTO coursePostDTO) {
        if(coursePostDTO == null) return null;

        Course course = new Course();
        course.setCourseName(coursePostDTO.getCourseName());
        course.setCourseCode(coursePostDTO.getCourseCode());
        course.setCourseCredit(coursePostDTO.getCourseCredit());
        return course;
    }

    @Override
    public CourseGetDTO courseToCourseGetDto(Course course) {
        if(course == null) return null;

        CourseGetDTO courseGetDTO = new CourseGetDTO();

        courseGetDTO.setId(course.getId());
        courseGetDTO.setCourseName(course.getCourseName());
        courseGetDTO.setCourseCode(course.getCourseCode());
        courseGetDTO.setCourseCredit(course.getCourseCredit());
        if (course.getInstructor() != null)
            courseGetDTO.setInstructor_id(course.getInstructor().getId());
        courseGetDTO.setStudents(courseSetToCourseGetDtoSet(course.getStudents()));

        return courseGetDTO;
    }

    /**
     *
     * @param instructorPostDTO 's type is InstructorDTO
     * @return Maps the instructorPostDTO object to the Instructor object
     */
    @Override
    public Instructor instructorPostDtoToInstructor(InstructorPostDTO instructorPostDTO) {
        if (instructorPostDTO == null) return  null;

        Instructor instructor;

        if(instructorPostDTO.getType().equals("Permanent")) {
            instructor = new PermanentInstructor(instructorPostDTO.getSalary());
        }else {
            instructor = new VisitingInstructor(instructorPostDTO.getSalary());
        }
        instructor.setName(instructorPostDTO.getName());
        instructor.setAddress(instructorPostDTO.getAddress());
        instructor.setPhoneNumber(instructorPostDTO.getPhoneNumber());

        return instructor;
    }

    /**
     *
     * @param instructor 's type is Instructor
     * @return Maps the Instructor object to the InstructorGetDTO object
     */
    @Override
    public InstructorGetDTO instructorToInstructorGetDto(Instructor instructor) {
        if (instructor == null) return null;

        InstructorGetDTO instructorGetDTO = new InstructorGetDTO();

        instructorGetDTO.setId(instructor.getId());
        instructorGetDTO.setName(instructor.getName());
        instructorGetDTO.setAddress(instructor.getAddress());
        instructorGetDTO.setPhoneNumber(instructor.getPhoneNumber());
        instructorGetDTO.setSalary(instructor.getSalary());
        instructorGetDTO.setCourses(studentSetToStudentGetDtoSet(instructor.getCourses()));

        return instructorGetDTO;
    }

    private Set<CourseSlimDTO> studentSetToStudentGetDtoSet(Set<Course> courses) {
        if(courses == null) return null;

        Set<CourseSlimDTO> courseSlimSet = new HashSet<>();
        for (Course c : courses)
            courseSlimSet.add(courseToCourseSlimDto(c));

        return courseSlimSet;
    }

    private Set<StudentSlimDTO> courseSetToCourseGetDtoSet(Set<Student> students){
        if (students == null) return null;

        Set<StudentSlimDTO> studentSlimSet = new HashSet<>();
        for (Student s : students)
            studentSlimSet.add(studentToStudentSlimDto(s));

        return studentSlimSet;
    }
}
