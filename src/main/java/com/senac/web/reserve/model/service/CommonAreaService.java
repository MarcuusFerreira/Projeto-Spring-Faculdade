package com.senac.web.reserve.model.service;

import com.senac.web.reserve.model.entities.CommonArea;
import com.senac.web.reserve.model.repository.CommonAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonAreaService {

    @Autowired
    private CommonAreaRepository commonAreaRepository;

    public CommonArea save(CommonArea commonArea) {
        return commonAreaRepository.save(commonArea);
    }
    public CommonArea findCommonArea(Long id) {
        return commonAreaRepository.findById(id).get();
    }

    public List<CommonArea> listAll() {
        return commonAreaRepository.findAll();
    }

    public void delete(Long id) {
        commonAreaRepository.deleteById(id);
    }
}
