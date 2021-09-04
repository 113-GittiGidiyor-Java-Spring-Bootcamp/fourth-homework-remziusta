package com.sms.service;

import com.sms.dto.InstructorGetDTO;
import com.sms.dto.InstructorPostDTO;
import com.sms.mappers.MapStructMapper;
import com.sms.model.Course;
import com.sms.model.Instructor;
import com.sms.repository.CourseRepository;
import com.sms.repository.InstructorRepository;
import com.sms.utils.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService{

    InstructorRepository instructorRepository;

    MapStructMapper mapStructMapper;

    CourseRepository courseRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository, MapStructMapper mapStructMapper, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.mapStructMapper = mapStructMapper;
        this.courseRepository = courseRepository;
    }


    public Optional<List<InstructorGetDTO>> findAll() {
        List<InstructorGetDTO> instructorGetList = new ArrayList<>();
        for (Instructor i : instructorRepository.findAll())
            instructorGetList.add(mapStructMapper.instructorToInstructorGetDto(i));
        return Optional.of(instructorGetList);
    }

    public Optional<InstructorGetDTO> findById(Long id) {
        return Optional.of(mapStructMapper.instructorToInstructorGetDto(instructorRepository.findById(id).get()));
    }

    @Transactional
    public Optional<InstructorGetDTO> save(InstructorPostDTO instructorPostDTO) {
        int k = instructorRepository.existsByPhoneNumber(instructorPostDTO.getPhoneNumber());
        ValidatorUtil.phoneNumberValidator(k);
        Instructor instructor = mapStructMapper.instructorPostDtoToInstructor(instructorPostDTO);
        InstructorGetDTO instructorGetDTO = mapStructMapper.instructorToInstructorGetDto(instructorRepository.save(instructor));
        return Optional.of(instructorGetDTO);
    }

    @Transactional
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    @Transactional
    public Optional<InstructorGetDTO> update(InstructorPostDTO instructorPostDTO) {
        Instructor i = findByInstructorId(instructorPostDTO.getId());

        i.setName(instructorPostDTO.getName());
        i.setAddress(instructorPostDTO.getAddress());
        i.setPhoneNumber(instructorPostDTO.getPhoneNumber());
        i.setSalary(instructorPostDTO.getSalary());

        InstructorGetDTO instructorGetDTO= mapStructMapper.instructorToInstructorGetDto(instructorRepository.save(i));
        return Optional.of(instructorGetDTO);
    }

    public Instructor findByInstructorId(Long id){
        return instructorRepository.findById(id).get();
    }

    public Optional<List<InstructorGetDTO>> setInstructorCourseRelationship(Long instructorId, Long courseId){
        Instructor i = instructorRepository.getById(instructorId);
        Course c = courseRepository.getById(courseId);

        i.getCourses().add(c);
        c.setInstructor(i);

        instructorRepository.save(i);
        courseRepository.save(c);

        return findAll();
    }
}
