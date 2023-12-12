package com.perfumes.PerfuWeb.repository;
/**
 *
 * @author Fabio
 */
import com.perfumes.PerfuWeb.modal.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
   public List<Cliente> findByNomeContaining(String nomeCli);    
}
