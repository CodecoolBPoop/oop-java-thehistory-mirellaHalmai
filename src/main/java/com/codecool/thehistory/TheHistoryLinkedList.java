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
        wordsLinkedList.addAll(toAdd);
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsLinkedList.removeIf(word -> (word.equals(wordToBeRemoved)));
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList = new LinkedList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> words = wordsLinkedList.listIterator();
        while (words.hasNext()) {
            if (words.next().equals(from)) {
                words.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        LinkedList<String> newList = new LinkedList<>();
        LinkedList<String> fromLinkedList = new LinkedList<>(Arrays.asList(fromWords));
        LinkedList<String> toLinkedList = new LinkedList<>(Arrays.asList(toWords));
        String firstFromWord = fromLinkedList.removeFirst();
        int fromLength = fromWords.length;

        while (size() > 0) {
            if (size() < fromLength) {
                newList.addAll(wordsLinkedList);
                break;
            } else {
                String nextWord = wordsLinkedList.removeFirst();
                if (!nextWord.equals(firstFromWord)) {
                    newList.add(nextWord);
                } else {
                    LinkedList<String> temporary = new LinkedList<>();
                    temporary.add(nextWord);
                    boolean allWordsEqual = true;
                    for (String fromWord : fromLinkedList) {
                        nextWord = wordsLinkedList.removeFirst();
                        if (!nextWord.equals(fromWord)) {
                            if (nextWord.equals(firstFromWord)) {
                                wordsLinkedList.addFirst(nextWord);
                            } else {
                                temporary.add(nextWord);
                            }
                            newList.addAll(temporary);
                            allWordsEqual = false;
                            break;
                        }
                        temporary.add(nextWord);
                    }
                    if (allWordsEqual) {
                        newList.addAll(toLinkedList);
                    }
                }
            }
        }
        wordsLinkedList = newList;
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
