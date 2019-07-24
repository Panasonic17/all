package app.rafAndReady;

import app.google.document.DocsAPI;
import app.google.spreadsheets.SheetAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GetAllWords {

    public static ArrayList<Word> getAllWords() {
        ArrayList<String> sheetIds = new ArrayList<>();
        sheetIds.add("15dLBragCYHUw2o_mnNMVzblsklKcKMC7IMXokXP6K7M");

        ArrayList<String> docsIds = new ArrayList<>();
        docsIds.add("Pmysd2O9x1gt3dNgrAr0pvOOn2ScLC0qleht4E8Zis");
        docsIds.add("14OT6ZbLMYlpecgbX-_vk0WvIwPY9AzjYGJmesQjAzNk");

        String allWords = DocsAPI.getAllWords("17Pmysd2O9x1gt3dNgrAr0pvOOn2ScLC0qleht4E8Zis");
        allWords += DocsAPI.getAllWords("14OT6ZbLMYlpecgbX-_vk0WvIwPY9AzjYGJmesQjAzNk");
        allWords += SheetAPI.getAllWords("15dLBragCYHUw2o_mnNMVzblsklKcKMC7IMXokXP6K7M");

        return Arrays.stream(allWords.split("\n")).map(Word::new).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void saveAllWords(ArrayList<Word> allWords) throws IOException {
        // File (or directory) with old name
        File oldWordsFile = new File("/home/sawa/Education/All_projects/english_words_project/allWords.txt");
        if (!oldWordsFile.exists()) {
            oldWordsFile.createNewFile();
        }


        BufferedReader br = new BufferedReader(new FileReader(oldWordsFile));
        StringBuilder rawOldWords = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null)
            rawOldWords.append(st).append("NEW_LINE_OFC");

        ArrayList<String> oldWords = (ArrayList<String>)
                Arrays.stream(rawOldWords.toString()
                        .split("NEW_LINE_OFC"))
                        .map(str -> new Word(str))
                        .map(w->w.eng+ "->"+w.uk)
                        .collect(Collectors.toList());

        ArrayList<String> allWordsInString=(ArrayList<String>)
              allWords.stream()
                        .map(w->w.eng+ "->"+w.uk)
                        .collect(Collectors.toList());


// remake delete conversion Word in STRING ^^^
        allWordsInString.removeAll(oldWords);
        java.io.FileWriter out = new java.io.FileWriter(oldWordsFile, true /*append=yes*/);
        allWordsInString.stream().map(s->new Word(s)).filter(w->!w.eng.equals("''")).forEach(s -> {
            try {
                out.append(s.toString() + "\n");
                System.out.println("NEW WORD IS " + s.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        out.flush();
        out.close();
    }


//        ArrayList<Word> oldWords= allWordsFile
//        java.io.FileWriter out= new java.io.FileWriter(allWordsFile, true /*append=yes*/);
// File (or directory) with new name
//        File file2 = new File("newname");
//
//        if (file2.exists())
//            throw new java.io.IOException("file exists");
//
// Rename file (or directory)
//        boolean success = file.renameTo(file2);
//
//        if (!success) {
//             File was not successfully renamed
//        }


    public static void main(String[] args) throws IOException {

        saveAllWords(getAllWords());
    }
}
