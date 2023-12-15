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

    static List<Item> carrinho = new ArrayList();
    static double total;
    static Integer lead;

    Venda venda = new Venda();

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/vendas")
    public String exibirCadVenda(Model model) {
        venda.setTotal(total);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("itv", new Item());
        model.addAttribute("item", new Item());
        model.addAttribute("venda", venda);
        model.addAttribute("produto", new Produto());
        model.addAttribute("listaProd", prodServ.listarTodos());
        return "venda";
    }
    
    @GetMapping("/vendasComId")
    public String exibirCadVendaSelecionado(@RequestParam Integer id, Model model) {
        lead = id;
        venda.setTotal(total);
        model.addAttribute("cliente", cliServ.buscarPorId(id));
        model.addAttribute("itv", new Item());
        model.addAttribute("item", new Item());
        model.addAttribute("venda", venda);
        model.addAttribute("lista", carrinho);
        model.addAttribute("produto", new Produto());
        model.addAttribute("listaProd", prodServ.listarTodos());
        return "venda";
    }

    @GetMapping("/pesquiVend")
    public String exibirPesquisaVend(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("lista", vendServ.listarTodos());
        return "listaVendas";
    }

    @GetMapping("/pequisaVenda")
    public String pesquisaVendaCliente(@RequestParam String nome, Model model) {
        List<Venda> acheiVend = new ArrayList();
        List<Cliente> acheiCli = cliServ.buscarPorNome(nome);
        
        if (!acheiCli.isEmpty()) {
            for (Cliente c : acheiCli) {
                acheiVend = vendServ.buscarPorNomeCliente(c.getId());
            }
        } else {
            acheiVend = vendServ.listarTodos();
        }

        model.addAttribute("cliente", new Cliente());
        model.addAttribute("lista", acheiVend);
        return "listaVendas";
    }

    @PostMapping("/adicionarProd")
    public String encheCarrinho(Model model, @RequestParam String produtoId,
            @RequestParam String quantidade) {
        Integer idP = Integer.parseInt(produtoId);
        Produto acheiprod = prodServ.buscarPorId(idP);
        Item itc = new Item();
        itc.setQuantidade(Integer.parseInt(quantidade));
        itc.setProduto(acheiprod);
        itemServ.criar(itc);
        carrinho.add(itc);
        total = total + acheiprod.getPreco() * itc.getQuantidade();
        venda.setTotal(total);
        model.addAttribute("cliente", cliServ.buscarPorId(lead));
        model.addAttribute("itv", new Item());
        model.addAttribute("item", new Item());
        model.addAttribute("venda", venda);
        model.addAttribute("lista", carrinho);        
        model.addAttribute("listaProd", prodServ.listarTodos());      
        return "venda";
    }

    @GetMapping("/excluirProduto")
    public String excluirProdutoDoCarrinho(Model model, @RequestParam String id) {
        Integer idItem = Integer.parseInt(id);
        Item acheItv = itemServ.buscarPorId(idItem);
        carrinho.remove(acheItv);
        itemServ.excluir(acheItv.getId());
        total = total - acheItv.getProduto().getPreco();
        venda.setTotal(total);
        model.addAttribute("cliente", cliServ.buscarPorId(lead));
        model.addAttribute("itv", new Item());
        model.addAttribute("item", new Item());
        model.addAttribute("venda", venda);
        model.addAttribute("lista", carrinho);
        model.addAttribute("listaCli", cliServ.listarTodos());
        model.addAttribute("listaProd", prodServ.listarTodos());    
        return "venda";
    }

    @GetMapping("/limpaCarrinho")
    public String excluirTodosProdutosDoCarrinho(Model model) {
        carrinho.clear();
        total = 0.00;
        return "index";
    }

    @GetMapping("/comprei")
    public String comprafinalizada() {
        LocalDateTime diaHora = LocalDateTime.now();
        venda.setCliente(cliServ.buscarPorId(lead));
        venda.setTotal(total);
        venda.setDt_venda(diaHora);
        vendServ.criar(venda);
        Venda vd = vendServ.pegaUltimaVenda();
        for (Item it : carrinho) {
            it.setVenda(vd);
            itemServ.atualizar(it.getId(),it);
        }
        carrinho.clear();
        total = 0.00;
        return "redirect:/vendas";
    }

    @GetMapping("/exibir")
    public String mostraDetalhesVenda(Model model, @RequestParam String id) {
        Integer idVenda = Integer.parseInt(id);
        Venda encontrado = vendServ.buscarPorId(idVenda);
        Item itc = new Item();
        List<Item> itens = new ArrayList<>();
        itens = itemServ.buscarPorIdVenda(idVenda);

        model.addAttribute("venda", encontrado);
        model.addAttribute("item", itc);
        model.addAttribute("itens", itens);
        return "exibirDetalhes";
    }

}
