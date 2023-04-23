package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder sb = new StringBuilder();
        Character bfr;
        int size = evenElements.size();
        for (int idx = 0; idx < size; idx++) {
            bfr = evenElements.pollFirst();
            if (idx % 2 == 0) {
                sb.append(bfr);
            }
        }
        return sb.toString();
    }

    private String getDescendingElements() {
        StringBuilder sb = new StringBuilder();
        Character bfr;
        int size = descendingElements.size();
        for (int idx = 0; idx < size; idx++) {
            bfr = descendingElements.pollLast();
            sb.append(bfr);
        }
        return sb.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
