package com.perfumes.PerfuWeb.service;

import com.perfumes.PerfuWeb.modal.Cliente;
import com.perfumes.PerfuWeb.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Fabio
 */
@Service
public class ClienteService {

    @Autowired
    ClienteRepository cliRepo;

    public Cliente criar(Cliente cli) {
        cli.setId(null);
        cliRepo.save(cli);
        return cli;
    }

    public List<Cliente> listarTodos() {
        return cliRepo.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return cliRepo.findById(id).orElseThrow();
    }

    public List<Cliente> buscarPorNome(String nomeC) {
        return cliRepo.findByNomeContaining(nomeC);                
    }

    public void excluir(Integer id) {
        Cliente livroEncontrado = buscarPorId(id);
        cliRepo.deleteById(livroEncontrado.getId());
    }

    public Cliente atualizar(Integer id, Cliente cliVeio) {
        Cliente acheiCli = buscarPorId(id);
        acheiCli.setNome(cliVeio.getNome());
        acheiCli.setCpf(cliVeio.getCpf());
        acheiCli.setDtNascimento(cliVeio.getDtNascimento());
        acheiCli.setTelefone(cliVeio.getTelefone());
        acheiCli.setEmail(cliVeio.getEmail());
        cliRepo.save(acheiCli);
        return acheiCli;
    }
}
