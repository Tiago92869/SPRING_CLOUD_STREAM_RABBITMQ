package com.classservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    private final SenderService senderService;

    @Autowired
    public ClassService(ClassRepository classRepository, SenderService senderService) {
        this.classRepository = classRepository;
        this.senderService = senderService;
    }

    public Page<ClassDto> findAllClasses(Pageable pageable) {

        return this.classRepository.findAll(pageable).map(ClassMapper.INSTANCE::classToDto);
    }

    public ClassDto createClass(ClassDto classDto) {

        classDto.setId(UUID.randomUUID());

        Class c = ClassMapper.INSTANCE.dtoToClass(classDto);

        this.senderService.sendSchool("add");
        return ClassMapper.INSTANCE.classToDto(this.classRepository.save(c));
    }

    public void deleteClass(UUID id) {

        Optional<Class> optionalClass = this.classRepository.findById(id);

        if(optionalClass.isEmpty()){

            throw new NotFoundException("There is no class with that id");
        }

        this.senderService.sendSchool("remove");
        this.classRepository.deleteById(id);
    }
}
