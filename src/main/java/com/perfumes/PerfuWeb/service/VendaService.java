package com.perfumes.PerfuWeb.service;

import com.perfumes.PerfuWeb.modal.Venda;
import com.perfumes.PerfuWeb.repository.VendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Fabio
 */

@Service
public class VendaService {
@Autowired
    VendaRepository vendRepo;

    public Venda criar(Venda vda) {
        vda.setId(null);
        vendRepo.save(vda);
        return vda;
    }

    public List<Venda> listarTodos() {
        return vendRepo.findAll();
    }

    public Venda buscarPorId(Integer id) {
        return vendRepo.findById(id).orElseThrow();
    }

    public void excluirVenda(Integer id) {
        Venda objEncontrado = buscarPorId(id);
        vendRepo.deleteById(objEncontrado.getId());
    }
    
    public Venda pegaUltimaVenda(){
        return vendRepo.findFirstByOrderByIdDesc();
    }
}
