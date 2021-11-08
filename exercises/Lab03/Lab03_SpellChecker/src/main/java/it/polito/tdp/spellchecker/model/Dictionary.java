package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionary {

    private List<String> parole;

    public Dictionary() {
        this.parole = new ArrayList<>();
    }

    public void loadDictionary(String language) {
        language = "src/main/resources/" + language + ".txt";
        try {
            FileReader fr = new FileReader(language);
            BufferedReader br = new BufferedReader(fr);
            String word;
            while ((word = br.readLine()) != null) {
                parole.add(word);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file");
        }
    }

    public List<RichWord> splellCheckText(List<String> inputTextList, boolean dicotomica) {

        if (dicotomica) {
            return this.ricercaDicotomica(inputTextList);
        } else {
            return this.ricercaLineare(inputTextList);
        }
    }

    private List<RichWord> ricercaLineare(List<String> inputTextList) {
        List<RichWord> controllato = new ArrayList<>();
        Iterator<String> it = parole.iterator();
        for (String s : inputTextList) {
            boolean trovato = false;
            while (it.hasNext() && !trovato) {
                if (it.next().equals(s)) {
                    trovato = true;
                }
            }

            controllato.add(new RichWord(s, trovato));

        }

        return controllato;
    }

    private List<RichWord> ricercaDicotomica(List<String> inputTextList) {
        List<RichWord> controllato = new ArrayList<>();
        parole.sort(null);
        for (String s : inputTextList) {
            controllato.add(this.cerca(parole, s, 0, parole.size()));
        }
        return controllato;
    }

    private RichWord cerca(List<String> dizionario, String s, int da, int a) {
        List<String> dizionarioDicotomico = dizionario.subList(da, a);
        int index = dizionarioDicotomico.size() / 2;
        int confronto = s.compareTo(dizionarioDicotomico.get(index));
        if ((dizionarioDicotomico.size() == 1 || dizionarioDicotomico.size() == 2)
                && (confronto != 0 || s.compareTo(dizionarioDicotomico.get(0)) != 0)) {
            return new RichWord(s, false);
        } else if (confronto == 0) {
            return new RichWord(s, true);
        } else if (confronto < 0) {
            return this.cerca(dizionarioDicotomico, s, 0, index);
        } else if (confronto > 0) {
            return this.cerca(dizionarioDicotomico, s, index + 1, dizionarioDicotomico.size());
        }
        return null;
    }

}
