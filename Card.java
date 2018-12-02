package Pianica;

public class Card {
    private int rank;
    private int mast;

    public Card(int rank, int mast) {
        this.rank = rank;
        this.mast = mast;
    }

    public void vivod(){

    }

    public String getRankString() {
        return Enum.Rank.values()[rank].toString();
    }

    public String getMastString() {
        return Enum.Mast.values()[mast].toString();
    }

    public int getRank() {
        return rank;
    }
}
