package com.apigateway.api.services.sudokumanager.service;

import com.apigateway.api.discoveryclient.assets.table.SudokuRouteTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.api.services.sudokumanager.model.dto.APISudokuLevelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class APISudokuService {

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;

    @Async
    public Object loadSudoku(APISudokuLevelRequest levelRequest) {

        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_load_sudoku;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(levelRequest))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    @Async
    public Mono<Boolean> generateSudoku() {
        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_generate_sudoku;
        return webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
    @Async
    public Object loadSudokuLevelList(String username) {
        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_load_sudoku_level_list+username;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(username))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

}
