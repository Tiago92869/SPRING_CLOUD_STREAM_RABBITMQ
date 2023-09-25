package com.schoolservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "School", description = "Manage schools")
@RestController
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/")
    @Operation(summary = "List all classes")
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolDto> findAllClasses(Pageable pageable){

        return this.schoolService.findAllClasses(pageable);
    }
}
