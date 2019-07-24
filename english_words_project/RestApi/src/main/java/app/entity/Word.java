package app.entity;


public class Word {

    String word;
    String transletion;

    public Word(String word, String transletion) {
        this.word = word;
        this.transletion = transletion;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTransletion() {
        return transletion;
    }

    public void setTransletion(String transletion) {
        this.transletion = transletion;
    }
}
