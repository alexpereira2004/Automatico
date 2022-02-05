package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.domain.entity.DiasUteis;
import br.com.lunacom.automatico.repository.DiaUtilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaUtilService {

    private final DiaUtilRepository repository;

    public List<DiasUteis> parseFromDiasUteisCom(String request) {

        return Arrays.asList(new DiasUteis());
    }
}
