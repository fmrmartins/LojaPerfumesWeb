package com.perfumes.PerfuWeb.controller;

import org.springframework.ui.Model;
import com.perfumes.PerfuWeb.modal.Cliente;
import com.perfumes.PerfuWeb.modal.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Fabio
 */
@Controller
public class PerfumesController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }
    
    @GetMapping("/clientes")
    public String exibirCadCliente(Model model) {
     model.addAttribute("cliente", new Cliente());
     return "clientes";   
    }
    
    @GetMapping("/produtos")
    public String exibirCadProduto(Model model) {
     model.addAttribute("produto", new Produto());
     return "produtos";   
    }
    
    @GetMapping("/vendas")
    public String exibirCadVenda(Model model) {
     model.addAttribute("produto", new Produto());
     model.addAttribute("cliente", new Cliente());
     return "venda";   
    }
    
    @GetMapping("/pesquiCli")
    public String exibirPesquisaCli(Model model) {     
     model.addAttribute("cliente", new Cliente());
     return "pesquisaCli";   
    }
    
    @GetMapping("/pesquiProd")
    public String exibirPesquisaProd(Model model) {     
     model.addAttribute("produto", new Produto());
     return "pesquisaProd";   
    }
    
    @GetMapping("/pesquiVend")
    public String exibirPesquisaVend(Model model) {     
     model.addAttribute("produto", new Produto());
     return "listaVendas";   
    }
}
