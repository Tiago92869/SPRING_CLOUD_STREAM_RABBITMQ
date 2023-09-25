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

        if(this.schoolRepository.count()==0){

            School school = new School(UUID.randomUUID(), "Harvard", 0);

            this.schoolRepository.save(school);
        }
    }

    public void addClass(){

        Optional<School> optionalSchool = this.schoolRepository.findByName("Harvard");

        if(optionalSchool.isEmpty()){

            throw new NotFoundException("Could not find Harvard school");
        }

        Integer currentNumber = optionalSchool.get().getClasses();

        optionalSchool.get().setClasses(currentNumber+1);

        this.schoolRepository.save(optionalSchool.get());
    }

    public void subtractClass(){

        Optional<School> optionalSchool = this.schoolRepository.findByName("Harvard");

        if(optionalSchool.isEmpty()){

            throw new NotFoundException("Could not find Harvard school");
        }

        Integer currentNumber = optionalSchool.get().getClasses();

        if(currentNumber > 0){

            optionalSchool.get().setClasses(currentNumber-1);
        }

        this.schoolRepository.save(optionalSchool.get());
    }
}
