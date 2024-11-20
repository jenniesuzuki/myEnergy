package br.com.fiap.dao;

import br.com.fiap.to.ContasTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContasDAO extends Repository{
    public ArrayList<ContasTO> findAllContas() {
        ArrayList<ContasTO> contas = new ArrayList<ContasTO>();
        String sql = "SELECT * FROM contas_luz ORDER BY id";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ContasTO conta = new ContasTO();
                    conta.setId(rs.getLong("id"));
                    conta.setUsuarioCpf(rs.getString("usuario_cpf"));
                    conta.setData(rs.getDate("data").toLocalDate());
                    conta.setValor(rs.getFloat("valor"));
                    conta.setKwh(rs.getFloat("kwh"));
                    conta.setCo2(rs.getFloat("co2"));
                    contas.add(conta);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return contas;
    }

    public ContasTO findContaById(Long id) {
        ContasTO conta = new ContasTO();
        String sql = "SELECT * FROM contas_luz WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conta.setId(rs.getLong("id"));
                conta.setUsuarioCpf(rs.getString("usuario_cpf"));
                conta.setData(rs.getDate("data").toLocalDate());
                conta.setValor(rs.getFloat("valor"));
                conta.setKwh(rs.getFloat("kwh"));
                conta.setCo2(rs.getFloat("co2"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return conta;
    }

    public ArrayList<ContasTO> findContasByCpf(String usuarioCpf) {
        ArrayList<ContasTO> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas_luz WHERE usuario_cpf = ? ORDER BY id";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuarioCpf);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ContasTO conta = new ContasTO();
                conta.setId(rs.getLong("id"));
                conta.setUsuarioCpf(rs.getString("usuario_cpf"));
                conta.setData(rs.getDate("data").toLocalDate());
                conta.setValor(rs.getFloat("valor"));
                conta.setKwh(rs.getFloat("kwh"));
                conta.setCo2(rs.getFloat("co2"));
                contas.add(conta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return contas;
    }

    public ContasTO saveConta(ContasTO conta) {
        conta.setCo2(conta.calcularEmissoesCO2(conta.getKwh()));
        String sql = "INSERT INTO contas_Luz(usuario_cpf, data, valor, kwh, co2) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, conta.getUsuarioCpf());
            ps.setDate(2, Date.valueOf(conta.getData()));
            ps.setFloat(3, conta.getValor());
            ps.setFloat(4, conta.getKwh());
            ps.setFloat(5, conta.getCo2());
            if (ps.executeUpdate() > 0) {
                return conta;
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

    public boolean deleteConta(Long id) {
        String sql = "DELETE FROM contas_luz WHERE id = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ContasTO updateConta(ContasTO conta) {
        conta.setCo2(conta.calcularEmissoesCO2(conta.getKwh()));
        String sql = "UPDATE contas_luz SET usuario_cpf=?, data=?, valor =?, kwh=?, co2=? where id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, conta.getUsuarioCpf());
            ps.setDate(2, Date.valueOf(conta.getData()));
            ps.setFloat(3, conta.getValor());
            ps.setFloat(4, conta.getKwh());
            ps.setFloat(5, conta.getCo2());
            ps.setLong(6, conta.getId());
            if (ps.executeUpdate() > 0) {
                return conta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

}
