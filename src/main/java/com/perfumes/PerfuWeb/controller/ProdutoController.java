package com.perfumes.PerfuWeb.controller;

import com.perfumes.PerfuWeb.modal.Produto;
import com.perfumes.PerfuWeb.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabio
 */
@Controller
public class ProdutoController {
    
    @Autowired
    ProdutoService prodServ;
    
  @GetMapping("/produtos")
    public String exibirCadProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos";
    }
    
    @GetMapping("/pesquiProd")
    public String exibirPesquisaProd(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("lista", prodServ.listarTodos());
        return "pesquisaProd";
    }
    
    @PostMapping("/gravarProduto")
    public String gravarProduto(@ModelAttribute Produto prod, Model model) {
        prodServ.criar(prod);
        return "redirect:/produtos";
    }

    @PostMapping("/alteraProduto")
    public String alterarProduto(@ModelAttribute Produto prod, Model model) {
        prodServ.atualizar(prod.getId(), prod);
        return "redirect:/pesquiProd";
    }
    
    @GetMapping("/alteraProd")
    public String alterarProduto(Model model, @RequestParam String id) {
        Integer idProd = Integer.parseInt(id);
        Produto acheiProd = prodServ.buscarPorId(idProd);
        model.addAttribute("produto", acheiProd);
        return "alterarProd";
    }

    @GetMapping("/pesqProdporNome")
    public String pesquisaProdutoporNome(@RequestParam String nome, Model model) {
        List<Produto> acheiProd = prodServ.buscarPorNome(nome);
        model.addAttribute("produto", new Produto());
        model.addAttribute("lista", acheiProd);
        return "pesquisaProd";
    }

    @GetMapping("/excluiProd")
    public String excluirProduto(Model model, @RequestParam String id) {
        Integer idProd = Integer.parseInt(id);
        prodServ.excluirProduto(idProd);
        return "redirect:/pesquiProd";
    }
}
