package ru.pivovardi.teodoro.telebuff.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleConversion {
    private final List<Character> badCharList = "._~:/?#[]@!$&'()*+,;=} "
            .chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

    public String convertTitleToIdForm(String title) {
        StringBuilder idBase = new StringBuilder();
        for (int i = 0; i < title.length(); i++) {
            if (badCharList.contains(title.charAt(i)) && i != title.length() - 1) {
                idBase.append('_');
            } else {
                idBase.append(title.charAt(i));
            }
        }
        return idBase.toString().toLowerCase();
    }

    public String concatWithTimestamp(String title) {
        LocalDate localDate = LocalDate.now();
        return title + '_' + localDate;
    }
}
