package com.sms.controller;

import com.sms.dto.InstructorGetDTO;
import com.sms.dto.InstructorPostDTO;
import com.sms.model.Instructor;
import com.sms.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/instructor")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     *
     * @param instructorPostDTO
     * @return the instructor added to the database
     */
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody @Valid InstructorPostDTO instructorPostDTO){
        Optional<InstructorGetDTO> myOptionalResult = instructorService.save(instructorPostDTO);
        if(myOptionalResult.isPresent()){
            return new ResponseEntity<>(myOptionalResult.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return the instructors found in the database
     */
    @GetMapping(value = "")
    public ResponseEntity<List<InstructorGetDTO>> getAllInstructors(){
        return new ResponseEntity<>(instructorService.findAll().get(),HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return the instructor with the desired id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<InstructorGetDTO> getInstructor(@PathVariable Long id){
        return new ResponseEntity<>(instructorService.findById(id).get(),HttpStatus.OK);
    }

    /**
     * Updates and returns the instructor with the requested ID
     * @param instructorPostDTO
     * @return the instructor with the requested ID
     */
    @PutMapping(value = "")
    public ResponseEntity<InstructorGetDTO> updateInstructor(@RequestBody @Valid InstructorPostDTO instructorPostDTO){
        return new ResponseEntity<>(instructorService.update(instructorPostDTO).get(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return Deletes the instructor with the desired ID and returns a string value
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id){
        String name = instructorService.findById(id).get().getName();
        instructorService.deleteById(id);
        return new ResponseEntity<>(name + " is deleted", HttpStatus.OK);
    }

    /**
     * That method adds instructor in course.
     * @param instructorId
     * @param courseId
     * @return the instructor found in the database
     */
    @PostMapping("/{instructorId}/{courseId}")
    public ResponseEntity<List<InstructorGetDTO>> addInstructorCourseRelationship(@PathVariable Long instructorId, @PathVariable Long courseId){
        return new ResponseEntity<>(instructorService.setInstructorCourseRelationship(instructorId,courseId).get(),HttpStatus.OK);
    }
}
