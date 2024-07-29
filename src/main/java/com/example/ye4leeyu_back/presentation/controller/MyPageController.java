package com.example.ye4leeyu_back.presentation.controller;

import com.example.ye4leeyu_back.application.usecase.HistoryUseCase;
import com.example.ye4leeyu_back.presentation.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final HistoryUseCase historyUseCase;

    @GetMapping("/mypage/exp")
    public ResponseEntity<HistoryResponse> getHistory() {
        return ResponseEntity.ok(historyUseCase.getHistory());
    }
}
