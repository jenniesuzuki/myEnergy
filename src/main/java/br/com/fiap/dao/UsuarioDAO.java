package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends Repository {
    public ArrayList<UsuarioTO> findAllUsuarios() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
        String sql = "select * from usuario order by nome";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setNome(rs.getString("nome"));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findByCpf(String cpf) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from usuario where cpf = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return usuario;
    }

    public UsuarioTO saveUsuario(UsuarioTO usuario) {
        String sql = "insert into usuario(cpf, nome, senha) values(?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean deleteUsuario(String cpf) {
        String sql = "delete from usuario where cpf = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cpf);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public UsuarioTO updateUsuario(UsuarioTO usuario) {
        String sql = "update usuario set nome=?, senha=? where cpf=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getCpf());
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: "  + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean autenticarUsuario(String cpf, String senha) {
        String sql = "select * from usuario where cpf = ? and senha = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
}
