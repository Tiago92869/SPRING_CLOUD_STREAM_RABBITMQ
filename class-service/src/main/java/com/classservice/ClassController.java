package com.classservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Class", description = "Manage classes")
@RestController
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/")
    @Operation(summary = "List all classes")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClassDto> findAllClasses(Pageable pageable){

        return this.classService.findAllClasses(pageable);
    }

    @PostMapping("/")
    @Operation(summary = "Create class")
    @ResponseStatus(HttpStatus.OK)
    public ClassDto createClass(@RequestBody ClassDto classDto){

        return this.classService.createClass(classDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete class")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClass(@PathVariable UUID id){

        this.classService.deleteClass(id);
    }
}
