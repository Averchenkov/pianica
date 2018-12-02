package Pianica;

import java.util.ArrayDeque;

public class Player {
    private ArrayDeque<Card> cards = new ArrayDeque<>();
    private int amount;

    public Player() {
        amount = 0;
    }

    void add(int rank, int mast) {
        cards.addLast(new Card(rank, mast));
        amount++;
    }

    public int getAmount() {
        return cards.size();
    }

    public boolean EstKarti() {
        if (getAmount() > 0)
            return true;
        else
            return false;
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public Card poolFirst (){
        return cards.pollFirst();
    }

    public void Dobavlenie(Card one, Card two) {
        cards.remove();
        cards.addLast(one);
        cards.addLast(two);
        amount++;
    }

    public void DobavlenieOne(Card card) {
        cards.remove();
        cards.addLast(card);
    }

    public void DobavlenieTwo(Card card) {
        cards.addLast(card);
        amount++;
    }

    public void Ubavlenie() {
        cards.remove();
        amount--;
    }
}
