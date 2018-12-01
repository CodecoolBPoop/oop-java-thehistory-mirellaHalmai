package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        wordsArrayList.addAll(Arrays.asList(text.split("\\s")));
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
        wordsArrayList = new ArrayList<>();
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
        ArrayList<String> newWordsArrayList = new ArrayList<>();
        ArrayList<String> fromWordsList = new ArrayList<>(Arrays.asList(fromWords));
        ArrayList<String> toWordsList = new ArrayList<>(Arrays.asList(toWords));
        int fromLength = fromWords.length;
        int oldSize = size();
        int oldIndex = 0;
        while (oldIndex < oldSize) {
            if (oldIndex > oldSize - fromLength) {
                newWordsArrayList.addAll(wordsArrayList.subList(oldIndex, oldSize));
                oldIndex = oldSize;
            } else if (!wordsArrayList.get(oldIndex).equals(fromWords[0]) ||
                    !wordsArrayList.subList(oldIndex, oldIndex + fromLength).equals(fromWordsList)) {
                newWordsArrayList.add(wordsArrayList.get(oldIndex));
                oldIndex++;
            } else {
                newWordsArrayList.addAll(toWordsList);
                oldIndex += fromLength;
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
}
