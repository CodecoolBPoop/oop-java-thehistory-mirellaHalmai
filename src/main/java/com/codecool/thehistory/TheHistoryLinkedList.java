package com.codecool.thehistory;

import java.util.*;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        LinkedList<String> toAdd = new LinkedList<>(Arrays.asList(text.split("\\s")));
        wordsLinkedList.addAll(toAdd);
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information
        wordsLinkedList.removeIf(word -> (word.equals(wordToBeRemoved)));
    }

    @Override
    public int size() {
        //TODO: check the TheHistory interface for more information
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
        wordsLinkedList = new LinkedList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
        ListIterator<String> words = wordsLinkedList.listIterator();
        while (words.hasNext()) {
            if (words.next().equals(from)) {
                words.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
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
        //history.replaceMoreWords(from, to);
        //System.out.println(history.toString());
        history.removeWord("that");
        System.out.println(history.toString());
        history.clear();
        System.out.println(history.toString());
    }
}
