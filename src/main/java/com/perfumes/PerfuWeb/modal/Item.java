package com.perfumes.PerfuWeb.modal;
/**
 *
 * @author Fabio
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_Venda")
    private Venda venda;
    private Integer Id_prod;
    private Integer quantidade;
}
