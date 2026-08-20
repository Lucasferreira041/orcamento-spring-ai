package com.example.orcamento.controller;

import com.example.orcamento.service.ProcessadorAudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final ProcessadorAudioService audioService;

    public TransacaoController(ProcessadorAudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping("/audio")
    public ResponseEntity<String> enviarAudio(@RequestParam("file") MultipartFile file) {
        String resposta = audioService.processarAudio(file.getResource());
        return ResponseEntity.ok(resposta);
    }
}