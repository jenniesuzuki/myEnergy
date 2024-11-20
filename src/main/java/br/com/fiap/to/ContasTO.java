package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ContasTO {
    private Long id;
    @NotBlank
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    private String usuarioCpf;
    @PastOrPresent
    private LocalDate data;
    @PositiveOrZero
    private float valor;
    @PositiveOrZero
    private float kwh;
    private float co2;
    private boolean consumoExcedente;

    public ContasTO() {
    }

    public ContasTO(Long id, @NotBlank @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.") String usuarioCpf, @PastOrPresent LocalDate data, @PositiveOrZero float valor, @PositiveOrZero float kwh, float co2, boolean consumoExcedente) {
        this.id = id;
        this.usuarioCpf = usuarioCpf;
        this.data = data;
        this.valor = valor;
        this.kwh = kwh;
        this.co2 = co2;
        this.consumoExcedente = consumoExcedente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public void setUsuarioCpf(String usuarioCpf) {
        this.usuarioCpf = usuarioCpf;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getKwh() {
        return kwh;
    }

    public void setKwh(float kwh) {
        this.kwh = kwh;
        this.co2 = calcularEmissoesCO2(kwh);
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public void setConsumoExcedente(boolean consumoExcedente) {
        this.consumoExcedente = consumoExcedente;
    }

    public boolean getConsumoExcedente() {
        return consumoExcedente;
    }

    public float calcularEmissoesCO2(float consumoKWh) {
        final float FATOR_EMISSAO = 0.124f;
        return consumoKWh * FATOR_EMISSAO;
    }

    public boolean isConsumoExcedente(float limite) {
        return this.kwh > limite;
    }
}
