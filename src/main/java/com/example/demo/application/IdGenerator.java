package com.example.demo.application;

import com.example.demo.infrastructure.id.Id;
import com.example.demo.infrastructure.id.IdRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdGenerator {

    private final IdRespository idRespository;

    public Id save(){
        return idRespository.save(new Id());
    }

}
