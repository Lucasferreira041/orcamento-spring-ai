package com.example.orcamento.service;

import com.example.orcamento.model.Transacao;
import com.example.orcamento.repository.TransacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;
import java.util.function.Function;

@Configuration
public class TransacaoTools {

    public record RequisicaoTransacao(String descricao, BigDecimal valor, String tipo) {}
    public record RespostaTransacao(String mensagem) {}

    @Bean
    @Description("Salva uma nova transacao financeira no banco de dados quando o usuario informa um gasto ou ganho")
    public Function<RequisicaoTransacao, RespostaTransacao> salvarTransacao(TransacaoRepository repository) {
        return req -> {
            // Regra simples: Validação de limite para gastos de saída
            if ("SAIDA".equalsIgnoreCase(req.tipo()) && req.valor().compareTo(new BigDecimal("500")) > 0) {
                return new RespostaTransacao("Atenção: A transação foi salva, mas ultrapassou o limite de R$ 500 para gastos isolados!");
            }

            Transacao novaTransacao = new Transacao(req.descricao(), req.valor(), req.tipo().toUpperCase());
            repository.save(novaTransacao);

            return new RespostaTransacao("Transação '" + req.descricao() + "' no valor de R$ " + req.valor() + " cadastrada com sucesso!");
        };
    }
}