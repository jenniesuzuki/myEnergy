package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UsuarioTO {
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    private String cpf;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, message = "A senha deve ter pelo menos 8 dígitos.")
    private String senha;

    // Construtores
    public UsuarioTO() {
    }

    public UsuarioTO(@Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") String cpf, @NotBlank String nome, @NotBlank @Size(min = 8, message = "A senha deve ter pelo menos 8 dígitos.") String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank @Size(min = 8, message = "A senha deve ter pelo menos 8 dígitos.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank @Size(min = 8, message = "A senha deve ter pelo menos 8 dígitos.") String senha) {
        this.senha = senha;
    }
}
