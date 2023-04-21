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
        for (int idx = evenElements.size(); idx > 0; idx--) {
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
        for (int idx = descendingElements.size(); idx > 0; idx--) {
            bfr = descendingElements.pollLast();
            sb.append(bfr);
        }
        return sb.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
