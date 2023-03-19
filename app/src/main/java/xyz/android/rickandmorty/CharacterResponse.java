package xyz.android.rickandmorty;

import java.util.ArrayList;

public class CharacterResponse {
    private int cout;
    private ArrayList<Character> results;

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }
}
