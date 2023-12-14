package com.perfumes.PerfuWeb.controller;

import com.perfumes.PerfuWeb.modal.Cliente;
import com.perfumes.PerfuWeb.service.ClienteService;
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
public class ClienteController {

    @Autowired
    ClienteService cliServ;

    @GetMapping("/clientes")
    public String exibirCadCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }

    @GetMapping("/pesquiCli")
    public String exibirPesquisaCli(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("lista", cliServ.listarTodos());
        return "pesquisaCli";
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
        List<Cliente> acheiCli = cliServ.buscarPorNome(nome);
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

}
