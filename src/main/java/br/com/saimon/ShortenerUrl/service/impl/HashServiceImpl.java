package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.service.HashService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HashServiceImpl implements HashService {

    private static final Integer NUMBER_CHAR = 5;

    public String createHash() {
        StringBuffer HASH = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <= NUMBER_CHAR; i++) {
            CHOICE choice = this.changeNewChoice();
            if (choice.equals(CHOICE.CHARACTER)) {
                char randomizedCharacter = (char) (random.nextInt(26) + 'a');
                String choiceCharacter = String.valueOf(randomizedCharacter);
                int camelCase = random.nextInt(20);
                if (camelCase >= 10) {
                    choiceCharacter = choiceCharacter.toUpperCase();
                }
                HASH.append(choiceCharacter);
            } else {
                int choiceNumber = random.nextInt(10);
                HASH.append(choiceNumber);
            }
        }
        return HASH.toString();
    }

    private CHOICE changeNewChoice() {
        Random random = new Random();
        int i = random.nextInt(1000);
//        Have 52 Character with camelCase, and have 10 numbers...
//        total 62 / 10 numbers, 6.2% to choice a number;
        if (i < 62) {
            return CHOICE.NUMBER;
        }
        return CHOICE.CHARACTER;
    }

    private enum CHOICE {
        NUMBER,
        CHARACTER;
    }

}
