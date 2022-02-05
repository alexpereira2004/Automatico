package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.domain.entity.DiaUtil;
import br.com.lunacom.automatico.enumeration.TipoDiaEnum;
import br.com.lunacom.automatico.repository.DiaUtilRepository;
import br.com.lunacom.automatico.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class DiaUtilService {

    private final DiaUtilRepository repository;
//    public static final String REGEX = "<div id=\"(\\d)\" class=\"cellcalendarYearStats (red|gray|white)\"> \\s\\n.*\\n.*\\n.*<div class=\"info\">(\\d)<\\/div>\\n(:?\\t\\t<div class=\"info\"><\\/div>\\s){3}\\t\\t<div class=\"info\">(\\d+\\/\\d+\\/\\d+)<\\/div>\\n\\t\\t<div class=\"info\">(.*)<\\/div>";
    public static final String REGEX = "<div id=\"(\\d)\" class=\"cellcalendarYearStats (red|gray|white)\"> \\s\\r\\n.*\\r\\n.*\\r\\n.*<div class=\"info\">(\\d)<\\/div>\\r\\n(:?\\t\\t<div class=\"info\"><\\/div>\\r\\s){3}\\t\\t<div class=\"info\">(\\d+\\/\\d+\\/\\d+)<\\/div>\\r\\n\\t\\t<div class=\"info\">(.*)<\\/div>";

    public List<DiaUtil> parseFromDiasUteisCom(String request) {
        List<DiaUtil> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(request);
        while (matcher.find()) {
            final DiaUtil entity = DiaUtil.builder()
                    .dia(DataUtil.dataBrParaLocalDate(matcher.group(5)))
                    .dia_ano(Integer.valueOf(matcher.group(1)))
                    .tipo(TipoDiaEnum.fromFlag(matcher.group(2)))
                    .feriado(matcher.group(6))
                    .build();
            list.add(entity);
        }
        return Arrays.asList(new DiaUtil());
    }
}
