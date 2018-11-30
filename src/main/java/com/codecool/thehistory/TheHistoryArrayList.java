package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        wordsArrayList.addAll(Arrays.asList(text.split("\\s+")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsArrayList.removeIf(word -> (word.equals(wordToBeRemoved)));
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList = new ArrayList<String>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information

    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }


    public static void main(String[] args) {
        String string = "Test this and this and this also this if you can";
        String[] from = {"that", "and"};
        String[] to = {"not", "this"};
        TheHistoryArray history = new TheHistoryArray();
        history.add(string);
        System.out.println(history.toString());
        //history.replaceOneWord("this", "that");
        //System.out.println(history.toString());
        //history.replaceMoreWords(from, to);
        //System.out.println(history.toString());
        history.removeWord("this");
        System.out.println(history.toString());
        //history.clear();
        //System.out.println(history.toString());
    }

}
