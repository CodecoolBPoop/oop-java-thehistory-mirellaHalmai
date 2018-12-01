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
        LinkedList<String> newList = new LinkedList<>();
        ListIterator<String> words = wordsLinkedList.listIterator();
        LinkedList<String> fromLinkedList = new LinkedList<>(Arrays.asList(fromWords));
        String firstFromWord = fromLinkedList.removeFirst();
        int fromLength = fromWords.length;
        LinkedList<String> toLinkedList = new LinkedList<>(Arrays.asList(toWords));

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
                            newList.addAll(temporary);
                            wordsLinkedList.addFirst(nextWord);
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

        //while (words.hasNext()) {
        //    if (words.nextIndex() > size() - fromLength) {
        //        words.forEachRemaining(word -> newList.add(word));
        //    } else if (!words.next().equals(firstFromWord)) {

        //    }
        //}
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
        history.replaceMoreWords(from, to);
        System.out.println(history.toString());
        history.removeWord("that");
        System.out.println(history.toString());
        history.clear();
        System.out.println(history.toString());
    }
}
