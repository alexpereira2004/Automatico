package br.com.lunacom.automatico.task;

import br.com.lunacom.automatico.client.LeitorDeIndicesClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final LeitorDeIndicesClient leitorDeIndicesClient;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
        List<String> ativos = Arrays.asList("ABCB4");
        String dataReferencia = "19/11/2019";
        boolean invisivel = false;
        final ResponseEntity<Void> responseEntity = leitorDeIndicesClient.scrapingHistoricoAtivos(ativos, dataReferencia, invisivel);
        log.info("Scraping retornou código {} as {}",
                responseEntity.getStatusCode().value(), dateFormat.format(new Date()));
    }
}
