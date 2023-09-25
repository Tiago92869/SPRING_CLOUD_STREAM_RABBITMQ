package com.schoolservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Page<SchoolDto> findAllClasses(Pageable pageable) {

        return this.schoolRepository.findAll(pageable).map(SchoolMapper.INSTANCE::schoolToDto);
    }

    @PostConstruct
    public void createClass() {

        School school = new School(UUID.randomUUID(), "Harvard", 0);

        this.schoolRepository.save(school);
    }

    public void addClass(){

        List<School> schoolList = this.schoolRepository.findAll();

        Integer currentNumber = schoolList.get(0).getClasses();

        schoolList.get(0).setClasses(currentNumber+1);
    }

    public void subtractClass(){

        List<School> schoolList = this.schoolRepository.findAll();

        Integer currentNumber = schoolList.get(0).getClasses();

        if(currentNumber > 0){

            schoolList.get(0).setClasses(currentNumber-1);
        }
    }
}
