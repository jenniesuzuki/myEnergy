package br.com.fiap.bo;

import br.com.fiap.dao.ContasDAO;
import br.com.fiap.to.ContasTO;

import java.util.ArrayList;

public class ContasBO {
    private ContasDAO contasDAO;

    public ArrayList<ContasTO> findAllContas() {
        contasDAO = new ContasDAO();
        return contasDAO.findAllContas();
    }

    public ContasTO findContaById(Long id) {
        contasDAO = new ContasDAO();
        return contasDAO.findContaById(id);
    }

    public ArrayList<ContasTO> findContasByCpf(String usuarioCpf) {
        contasDAO = new ContasDAO();
        return contasDAO.findContasByCpf(usuarioCpf);
    }

    public ContasTO saveConta(ContasTO conta) {
        contasDAO = new ContasDAO();
        return contasDAO.saveConta(conta);
    }

    public boolean deleteConta(Long id) {
        contasDAO = new ContasDAO();
        return contasDAO.deleteConta(id);
    }

    public ContasTO updateConta(ContasTO conta) {
        contasDAO = new ContasDAO();
        return contasDAO.updateConta(conta);
    }

}