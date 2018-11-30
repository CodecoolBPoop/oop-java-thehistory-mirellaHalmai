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
        for (int i = 0; i < size(); i++) {
            if (wordsArrayList.get(i).equals(from)) {
                wordsArrayList.set(i, to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        ArrayList<String> newWordsArrayList = new ArrayList<String>();
        ArrayList<String> fromWordsList = new ArrayList<>(Arrays.asList(fromWords));
        ArrayList<String> toWordsList = new ArrayList<>(Arrays.asList(toWords));
        int fromLength = fromWords.length;
        int toLength = toWords.length;
        int originalSize = size();
        int originalIndex = 0;
        int newIndex = 0;
        while (originalIndex < originalSize) {
            if (originalIndex > originalSize - fromLength) {
                newWordsArrayList.addAll(wordsArrayList.subList(originalIndex, originalSize));
            } else if (!wordsArrayList.get(originalIndex).equals(fromWords[0]) ||
                    !wordsArrayList.subList(originalIndex, originalIndex + fromLength).equals(fromWordsList)) {
                newWordsArrayList.add(wordsArrayList.get(originalIndex));
                newIndex++;
                originalIndex++;
            } else {
                newWordsArrayList.addAll(toWordsList);
                newIndex += toLength;
                originalIndex += fromLength;
            }
        }
        wordsArrayList = newWordsArrayList;
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
        history.replaceOneWord("this", "that");
        System.out.println(history.toString());
        history.replaceMoreWords(from, to);
        System.out.println(history.toString());
        history.removeWord("that");
        System.out.println(history.toString());
        history.clear();
        System.out.println(history.toString());
    }

}
