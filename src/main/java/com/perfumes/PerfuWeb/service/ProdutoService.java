package com.perfumes.PerfuWeb.service;

/**
 *
 * @author Fabio
 */
import com.perfumes.PerfuWeb.modal.Produto;
import com.perfumes.PerfuWeb.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository prodRepo;

    public Produto criar(Produto pod) {
        pod.setId(null);
        prodRepo.save(pod);
        return pod;
    }

    public List<Produto> listarTodos() {
        return prodRepo.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return prodRepo.findById(id).orElseThrow();
    }
    
    public List<Produto> buscarPorNome(String nome) {
        return prodRepo.findByNomeContaining(nome);
                
    }

    public void excluirProduto(Integer id) {
        Produto objEncontrado = buscarPorId(id);
        prodRepo.deleteById(objEncontrado.getId());
    }

    public Produto atualizar(Integer id, Produto prodVeio) {
        Produto acheiProd = buscarPorId(id);
        acheiProd.setNome(prodVeio.getNome());
        acheiProd.setDescricao(prodVeio.getDescricao());
        acheiProd.setPreco(prodVeio.getPreco());
        acheiProd.setMarca(prodVeio.getMarca());        
        acheiProd.setEstoque(prodVeio.getEstoque());
        prodRepo.save(acheiProd);
        return acheiProd;
    }
}
