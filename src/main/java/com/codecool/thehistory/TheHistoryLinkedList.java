package com.codecool.thehistory;

import java.util.*;
import java.util.LinkedList;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private LinkedList<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        List<String> toAdd = new LinkedList<>(Arrays.asList(text.split("\\s")));
        for (String word : toAdd) {
            wordsLinkedList.add(word);
        }
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
        LinkedList<String> fromLinkedList = new LinkedList<>(Arrays.asList(fromWords));
        LinkedList<String> toLinkedList = new LinkedList<>(Arrays.asList(toWords));
        ListIterator<String> words = wordsLinkedList.listIterator();
        String firstFromWord = fromLinkedList.removeFirst();
        int fromLength = fromWords.length;

        while (words.nextIndex() <= size() - fromLength) {
            int nextElementIndex = words.nextIndex();
            String nextWord = words.next();
            if (nextWord.equals(firstFromWord)) {
                boolean allWordsEqual = true;
                for (String fromWord : fromLinkedList) {
                    String followingWordToCheck = words.next();
                    if (!followingWordToCheck.equals(fromWord)) {
                        allWordsEqual = false;
                        break;
                    }
                }
                if (allWordsEqual) {
                    while (words.previousIndex() > nextElementIndex - 1) {
                        words.previous();
                        words.remove();
                    }
                    for (String toWord : toLinkedList) {
                        words.add(toWord);
                    }
                } else {
                    while (words.previousIndex() > nextElementIndex) {
                        words.previous();
                    }
                }
            }
        }
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
}
