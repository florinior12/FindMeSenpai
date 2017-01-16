package find;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by Florin on 1/8/2017.
 */
public class FindMeSenpai {
    private final String name;
    private final String region;
    FindMeSenpai(String name, String region) {
        this.name = name;
        this.region = region;
        Summoner summoner = new Summoner(name,region);
        System.out.println("ID: " + summoner.getID());
        System.out.println("Name: " + summoner.getSummonerName());
        String match = "";
        if (!summoner.isInMatch()) {
            match = " not ";
        }
        System.out.println(summoner.getSummonerName() + " is" + match + "in match");
        //System.out.println("Last match: " + summoner.getLastMatchID());
        System.out.println(summoner.getLastMatchDetails());
    }
    /*public static void main(String[] args) {
        FindMeSenpai findMeSenpai = new FindMeSenpai("whee");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.print("Region('eune' for Europe Nord-East): ");
        String region = scanner.next();
        Summoner summoner = new Summoner(name,region);
        System.out.println("ID: " + summoner.getID());
        System.out.println("Name: " + summoner.getSummonerName());
        String match = "";
        if (!summoner.isInMatch()) {
            match = " not ";
        }
        System.out.println(summoner.getSummonerName() + " is" + match + "in match");
        //System.out.println("Last match: " + summoner.getLastMatchID());
        System.out.println(summoner.getLastMatchDetails());
    }*/
}

