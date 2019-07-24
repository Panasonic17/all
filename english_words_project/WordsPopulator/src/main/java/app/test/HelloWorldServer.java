package app.test;

import app.google.spreadsheets.SheetAPI;

public class HelloWorldServer {

    public static void main(String[] args) {
        String a = SheetAPI.getAllWords("15dLBragCYHUw2o_mnNMVzblsklKcKMC7IMXokXP6K7M");
        System.out.println(a);
    }
}
