package com.eray.veritabaniproject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eray.veritabaniproject.Models.Bagis;
import com.eray.veritabaniproject.Repositories.BagisRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BagisService {
    @Autowired
    private BagisRepository repo;

    public List<Bagis> listAll() {
        return repo.findAll();
    }
    
    @SuppressWarnings("null")
    public void save(Bagis bagis){
        repo.save(bagis);
    }

    public Bagis get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
 
}
