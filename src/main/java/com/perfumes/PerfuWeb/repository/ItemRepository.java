package com.perfumes.PerfuWeb.repository;

/**
 *
 * @author Fabio
 */
import com.perfumes.PerfuWeb.modal.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByVendaId(Integer id);
}