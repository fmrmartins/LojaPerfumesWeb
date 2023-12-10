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
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_Cliente")
    private Cliente cliente;
    private String dt_venda;
    private String total;    
}
