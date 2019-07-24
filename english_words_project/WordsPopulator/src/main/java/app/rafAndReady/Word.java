package app.rafAndReady;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Word {

    public String eng = "";
    public String uk = "";
    public Date createionTime;


    public Word(String eng, String uk) {
        this.eng = eng;
        this.uk = uk;
        createionTime = new Date();

    }

    public Word(String tuple) {
        if (tuple.startsWith("Word")) {
//            Word{eng='pick up', uk='підібрати', createionTime=Mon Jun 17 14:49:56 EEST 2019}
            String[] split = tuple.split("eng='|', uk='|', createionTime=|}");
            eng = split[1];
            uk = split[2];
            if (!split[3].equals("null")) {
                try {
                    createionTime = new SimpleDateFormat("E MMM dd HH:mm:ss zzzz yyyy").parse(split[3]);
                } catch (ParseException e) {
                    e.printStackTrace();

                }
            }

        } else {
            String[] words = tuple.trim().split("->");
            if (words.length <= 1) {
                System.err.println(tuple);
                return;
            }
            this.eng = words[0].trim();
            this.uk = words[1].trim();
            createionTime = new Date();
        }

    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    @Override
    public String toString() {
        return "Word{" +
                "eng='" + eng + '\'' +
                ", uk='" + uk + '\'' +
                ", createionTime=" + createionTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(eng, word.eng) &&
                Objects.equals(uk, word.uk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eng, uk);
    }
}
