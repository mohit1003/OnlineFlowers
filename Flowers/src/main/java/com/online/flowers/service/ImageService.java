package com.online.flowers.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.model.ShopModel;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.repo.ShopRepo;

@Service
@Transactional
public class ImageService {
	
	@Autowired
	private FlowersRepo flowersRepo;
	
	@Autowired
	private ShopRepo shopRepo;
	
	
	public List<FlowersModel> list(){
        return flowersRepo.findByOrderById();
    }

    public Optional<FlowersModel> getOne(int id){
        return flowersRepo.findById(id);
    }

    public void save(FlowersModel imagen){
    	flowersRepo.save(imagen);
    }

    public void delete(String id){
    	flowersRepo.deleteById(Integer.parseInt(id));
    }

    public boolean exists(int id){
        return flowersRepo.existsById(id);
    }
    
    
    
    public List<ShopModel> listShopImages(){
        return shopRepo.findAll();
    }

    public Optional<ShopModel> getOneShopImage(int id){
        return shopRepo.findById(id);
    }

    public void saveShopImage(ShopModel shop){
    	shopRepo.save(shop);
    }

    public void deleteShopImage(String id){
    	shopRepo.deleteById(Integer.parseInt(id));
    }

    public boolean existsShopImage(int id){
        return shopRepo.existsById(id);
    }
    
    
	

}
