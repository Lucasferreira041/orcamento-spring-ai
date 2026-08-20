# Controle Financeiro por Voz com Spring AI 🎙️💰

Projeto desenvolvido como desafio prático na plataforma DIO para a criação de uma API inteligente com Spring Boot e Spring AI.

## 🚀 O que o projeto faz
A aplicação recebe um arquivo de áudio via API REST com um comando financeiro (ex: *"Gastei 600 reais no mercado"*), faz a transcrição do áudio para texto e utiliza a funcionalidade de **Tool Calling** para identificar a intenção e salvar o registro no banco de dados.

## 🛠️ Tecnologias
- Java 17
- Spring Boot 3
- Spring AI (OpenAI Transcription + ChatClient)
- Spring Data JPA
- Banco de Dados H2

## 📌 Melhoria Implementada
Foi adicionada uma validação de limite na classe `TransacaoTools`: caso o valor do gasto ultrapasse R$ 500,00, a aplicação registra a transação e devolve um alerta informando que o valor superou o limite recomendado para compras individuais.

## 🧪 Como Executar
1. Clone o repositório.
2. Defina sua chave de API da OpenAI nas variáveis do ambiente (`OPENAI_API_KEY`).
3. Execute o projeto pela IDE ou via terminal com `./mvnw spring-boot:run`.
4. Envie uma requisição `POST` com um arquivo de áudio para `http://localhost:8080/api/transacoes/audio`.
