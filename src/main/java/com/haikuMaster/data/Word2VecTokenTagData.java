package com.haikuMaster.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 2/24/2017.
 */
public final class Word2VecTokenTagData {

    private String token;

    private List<String> nouns = new ArrayList<>();

    private List<String> adjectives = new ArrayList<>();

    private List<String> verbs = new ArrayList<>();

    private List<String> adverbs = new ArrayList<>();

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public List<String> getNouns() {
        return nouns;
    }

    public List<String> getAdjectives() {
        return adjectives;
    }

    public List<String> getVerbs() {
        return verbs;
    }

    public List<String> getAdverbs() {
        return adverbs;
    }
}