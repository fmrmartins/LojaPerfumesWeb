
package com.perfumes.PerfuWeb.repository;
/**
 *
 * @author Fabio
 */
import com.perfumes.PerfuWeb.modal.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
   public List<Produto> findByNomeContainingIgnoreCase(String nome);
}
