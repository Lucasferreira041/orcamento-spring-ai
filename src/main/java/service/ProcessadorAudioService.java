package com.example.orcamento.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorAudioService {

    private final ChatClient chatClient;
    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public ProcessadorAudioService(ChatClient.Builder chatClientBuilder, OpenAiAudioTranscriptionModel transcriptionModel) {
        this.chatClient = chatClientBuilder.build();
        this.transcriptionModel = transcriptionModel;
    }

    public String processarAudio(Resource arquivoAudio) {
        var respostaTranscricao = transcriptionModel.call(new AudioTranscriptionPrompt(arquivoAudio));
        String textoTranscritor = respostaTranscricao.getResult().getOutput();

        return chatClient.prompt()
                .user("Interprete o seguinte comando de voz e execute a ação correspondente: " + textoTranscritor)
                .functions("salvarTransacao")
                .call()
                .content();
    }
}