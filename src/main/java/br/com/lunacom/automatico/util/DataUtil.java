package br.com.lunacom.automatico.util;

import br.com.lunacom.automatico.domain.entity.DiaUtil;
import br.com.lunacom.automatico.enumeration.TipoDiaEnum;
import br.com.lunacom.automatico.repository.DiaUtilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DataUtil {
    private static String ANO_MES_DIA = "yyyyMMdd";
    private static String ANO_MES_DIA_HIFEN = "yyyy-MM-dd";
    private static String DIA_MES_ANO_BARRA = "dd/MM/yyyy";

    private final DiaUtilRepository diaUtilRepository;

    public static String localDateParaDataBr(LocalDate origem) {
        if (Objects.isNull(origem)) {
            return "";
        }
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DIA_MES_ANO_BARRA);
        return formatter.format(origem);
    }

    public static LocalDate dataBrParaLocalDate(String origem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DIA_MES_ANO_BARRA);
        return LocalDate.parse(origem, formatter);
    }

    public LocalDate proximoDiaUtil(LocalDate diaReferencia) {
        Boolean cond = false;
        DiaUtil diaUtil = null;
        do {
            LocalDate proximoDia = Objects.isNull(diaUtil) ? diaReferencia : diaUtil.getDia();
            diaUtil = proximoDia(proximoDia);
            cond = diaUtil.getTipo().equals(TipoDiaEnum.NORMAL);
        } while (!cond);
        return diaUtil.getDia();
    }

    private DiaUtil proximoDia(LocalDate diaReferencia) {
        Optional<DiaUtil> optional = diaUtilRepository.findByDia(diaReferencia.plusDays(1l));
        return optional
                .orElseThrow(() -> new IllegalArgumentException(("Agenda não existe")));
    }

    public static LocalDate dataAgora() {
        return LocalDate.now();
    }

    public static LocalDateTime dataHoraAgora() {
        return LocalDateTime.now();
    }

    public LocalDateTime dataHoraAora() {
        return LocalDateTime.now();
    }
}
