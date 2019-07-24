package app.test;

import app.rafAndReady.Word;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        Word w= new Word("Word{eng='inflate', uk='раздувать', createionTime=Mon Jun 17 15:55:39 EEST 2019}");
        Word w1= new Word("Word{eng='inflate', uk='раздувать', createionTime=Mon Jun 17 15:55:39 EEST 2019}");
        System.out.println(w);
        System.out.println(w1);
        System.out.println(w.equals(w1));
    }
}
