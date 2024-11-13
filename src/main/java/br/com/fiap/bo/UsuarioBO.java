package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAllUsuarios() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAllUsuarios();
    }

    public UsuarioTO findByCpf(String cpf) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByCpf(cpf);
    }

    public UsuarioTO saveUsuario(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.saveUsuario(usuario);
    }

    public boolean deleteUsuario(String cpf) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.deleteUsuario(cpf);
    }

    public UsuarioTO updateUsuario(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.updateUsuario(usuario);
    }

    public boolean autenticarUsuario(String cpf, String senha) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.autenticarUsuario(cpf, senha);
    }
}
