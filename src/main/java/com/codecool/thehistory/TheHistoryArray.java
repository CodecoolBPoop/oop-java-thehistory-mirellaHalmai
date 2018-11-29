package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] wordsToAdd = text.split("\\s+");
        int oldLength = wordsArray.length;
        String[] oldWordsArray = wordsArray;
        int newLength = wordsToAdd.length + oldLength;
        wordsArray = new String[newLength];
        System.arraycopy(oldWordsArray, 0, wordsArray, 0, oldLength);
        System.arraycopy(wordsToAdd, 0, wordsArray, oldLength, wordsToAdd.length);
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        String[] newWordsArray = new String[wordsArray.length];
        int resultIndex = 0;
        for (String word : wordsArray) {
            if (!word.equals(wordToBeRemoved)) {
                newWordsArray[resultIndex++] = word;
            }
        }
        wordsArray = Arrays.copyOfRange(newWordsArray, 0, resultIndex);
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < wordsArray.length; i++) {
            String word = wordsArray[i];
            if (word.equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int oldLength = wordsArray.length;
        int oldArrayIndex = 0;
        int newArrayIndex = 0;
        int fromLength = fromWords.length;
        int toLength = toWords.length;
        int newLength = oldLength;
        if (toLength > fromLength) {
            newLength += Math.floorDiv(oldLength, toLength) * (toLength - fromLength);
        }
        String[] newWordsArray = new String[newLength];
        while (oldArrayIndex < oldLength) {
            if (oldArrayIndex > oldLength - fromLength) {
                int remainingElementNumber = oldLength - oldArrayIndex;
                System.arraycopy(wordsArray, oldArrayIndex, newWordsArray, newArrayIndex, remainingElementNumber);
                newArrayIndex += remainingElementNumber;
                break;
            } else if (!wordsArray[oldArrayIndex].equals(fromWords[0]) ||
                    !Arrays.equals(fromWords, Arrays.copyOfRange(wordsArray, oldArrayIndex, oldArrayIndex + fromLength))) {
                newWordsArray[newArrayIndex++] = wordsArray[oldArrayIndex++];
            } else {
                System.arraycopy(toWords, 0, newWordsArray, newArrayIndex, toLength);
                oldArrayIndex += fromLength;
                newArrayIndex += toLength;
            }
        }
        wordsArray = Arrays.copyOfRange(newWordsArray, 0, newArrayIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
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
        history.removeWord("this");
        System.out.println(history.toString());
        history.clear();
        System.out.println(history.toString());
    }
}
