package com.online.flowers.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.flowers.model.FlowersModel;

import com.online.flowers.repo.FlowersRepo;

@Service
@Transactional
public class ImageService {
	
	@Autowired
	private FlowersRepo flowersRepo;
	
	public List<FlowersModel> list(){
        return flowersRepo.findByOrderById();
    }

    public Optional<FlowersModel> getOne(int id){
        return flowersRepo.findById(id);
    }

    public void save(FlowersModel imagen){
    	flowersRepo.save(imagen);
    }

    public void delete(int id){
    	flowersRepo.deleteById(id);
    }

    public boolean exists(int id){
        return flowersRepo.existsById(id);
    }
	

}
