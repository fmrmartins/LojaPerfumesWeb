package com.perfumes.PerfuWeb.controller;

import org.springframework.ui.Model;
import com.perfumes.PerfuWeb.modal.Cliente;
import com.perfumes.PerfuWeb.modal.Item;
import com.perfumes.PerfuWeb.modal.Produto;
import com.perfumes.PerfuWeb.modal.Venda;
import com.perfumes.PerfuWeb.service.ProdutoService;
import com.perfumes.PerfuWeb.service.ClienteService;
import com.perfumes.PerfuWeb.service.ItemService;
import com.perfumes.PerfuWeb.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;

/**
 *
 * @author Fabio
 */
@Controller
public class PerfumesController {

    @Autowired
    ClienteService cliServ;

    @Autowired
    ProdutoService prodServ;
    
    @Autowired
    VendaService vendServ;
    
    @Autowired
    ItemService itemServ;
    
    static List<Produto> carrinho = new ArrayList();
    static double total;
    Venda venda = new Venda();
    
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
        model.addAttribute("cli", new Cliente());
        model.addAttribute("produto", new Produto());
        model.addAttribute("listaCli", cliServ.listarTodos());
        model.addAttribute("listaProd", prodServ.listarTodos());
        return "venda";
    }

    @GetMapping("/pesquiCli")
    public String exibirPesquisaCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("lista", cliServ.listarTodos());
        return "pesquisaCli";
    }

    @GetMapping("/pesquiProd")
    public String exibirPesquisaProd(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("lista", prodServ.listarTodos());
        return "pesquisaProd";
    }

    @GetMapping("/pesquiVend")
    public String exibirPesquisaVend(Model model) {
        model.addAttribute("produto", new Produto());
        return "listaVendas";
    }

    @PostMapping("/gravarCliente")
    public String gravarCliente(@ModelAttribute Cliente cli, Model model) {
        cliServ.criar(cli);
        return "redirect:/clientes";
    }

    @PostMapping("/alteraCliente")
    public String alterarCliente(@ModelAttribute Cliente cli, Model model) {
        cliServ.atualizar(cli.getId(), cli);
        return "redirect:/pesquiCli";
    }

    @GetMapping("/pesqCliporNome")
    public String pesquisaClienteporNome(@RequestParam String nome, Model model) {
        List<Cliente> acheiCli = acheiCli = cliServ.buscarPorNome(nome);  
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("lista", acheiCli);
        return "pesquisaCli";
    }

    @GetMapping("/excluiCli")
    public String excluirCliente(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        cliServ.excluir(idCliente);
        return "redirect:/pesquiCli";
    }

    @GetMapping("/alteraCli")
    public String alterarCliente(Model model, @RequestParam String id) {
        Integer idCli = Integer.parseInt(id);
        Cliente acheiCli = cliServ.buscarPorId(idCli);
        model.addAttribute("cliente", acheiCli);
        return "alterarCli";
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

    @GetMapping("/alteraProd")
    public String alterarProduto(Model model, @RequestParam String id) {
        Integer idProd = Integer.parseInt(id);
        Produto acheiProd = prodServ.buscarPorId(idProd);
        model.addAttribute("produto", acheiProd);
        return "alterarProd";
    }
    
    @PostMapping("/adicionarProd")
    public String encheCarrinho(Model model, @RequestParam String produtoId,
            @RequestParam Integer clienteId) {
        Integer idProd = Integer.parseInt(produtoId); 
        Produto acheiprod = prodServ.buscarPorId(idProd);
        carrinho.add(acheiprod);
        total = total - acheiprod.getPreco();        
        model.addAttribute("lista", carrinho);
        model.addAttribute("listaCli", cliServ.listarTodos());
        model.addAttribute("listaProd", prodServ.listarTodos());
        model.addAttribute("cliIdSelecionado", clienteId);
        return "venda"; 
    }
    @GetMapping("/excluirProduto")
    public String excluirProdutoDoCarrinho(Model model, @RequestParam String id) {
        Integer idProd = Integer.parseInt(id);
        Produto acheiprod = prodServ.buscarPorId(idProd);
        carrinho.remove(acheiprod);
        total = total - acheiprod.getPreco();
        model.addAttribute("lista", carrinho);
        model.addAttribute("listaCli", cliServ.listarTodos());
        model.addAttribute("listaProd", prodServ.listarTodos());
        return "venda";
        
    }
    @GetMapping("/limpaCarrinho")
    public String excluirTodosProdutosDoCarrinho(Model model) {
              carrinho.clear();
              total = 0;
        return "index";
    }
    @PostMapping("/comprei")
    public String comprafinalizada(@RequestParam Integer clienteId){
        LocalDateTime diaHora = LocalDateTime.now();
        venda.setCliente(cliServ.buscarPorId(clienteId));
        venda.setTotal(String.valueOf(total));
        venda.setDt_venda(diaHora.toString());
        vendServ.criar(venda);
        Venda vd = vendServ.pegaUltimaVenda();
        for (Produto pd : carrinho) {
            Item itv = new Item();
            itv.setId_prod(pd.getId());
            //itv.setQuantidade();
            itv.setVenda(vd);
        }
       return"venda";  
    }
}
