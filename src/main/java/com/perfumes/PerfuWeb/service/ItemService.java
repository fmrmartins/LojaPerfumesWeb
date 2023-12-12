package com.perfumes.PerfuWeb.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Fabio
 */
import com.perfumes.PerfuWeb.modal.Item;
import com.perfumes.PerfuWeb.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepo;

    public Item criar(Item ite) {
        ite.setId(null);
        itemRepo.save(ite);
        return ite;
    }

    public List<Item> listarTodos() {
        return itemRepo.findAll();
    }

    public Item buscarPorId(Integer id) {
        return itemRepo.findById(id).orElseThrow();
    }

    public void excluirProduto(Integer id) {
        Item objEncontrado = buscarPorId(id);
        itemRepo.deleteById(objEncontrado.getId());
    }    
}
