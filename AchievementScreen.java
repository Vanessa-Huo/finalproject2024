import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Write a description of class AchievementScreen here.
 * 
 * @author Megan Lee, Rick Li
 * @version (a version number or a date)
 */
public class AchievementScreen extends World
{
    Scanner s;
    ArrayList<Integer> scoreBoard = new ArrayList<Integer>();
    ArrayList<Integer> sortedScores;
    int numLines;
    public AchievementScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1);
        setBackground("achievementScreen.png");
        
        HomeButton homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        try{
            s = new Scanner(new File("Scores.txt"));
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
        boolean moreLines = true;
        numLines = 0;
        while (moreLines){
            try{
                String temp = s.nextLine();
                if(!temp.equals(null)){
                    scoreBoard.add(Integer.valueOf(temp));
                    numLines++;
                }
            }catch(NoSuchElementException e){
                moreLines = false;
            }
        }
        sortedScores = insertionSort(scoreBoard);
        int xA = getWidth()/2 - 50;
        int xB = getWidth()/2 + 50;
        int Y = 100;
        //Displays high score
        Label score = new Label(sortedScores.get(sortedScores.size() - 1), 200);
        score.setFillColor(Color.BLACK);
        addObject(score, getWidth()/2, getHeight()/2 - 40);
        
        WatermelonDisplay booster1 = new WatermelonDisplay();
        PaintbrushDisplay booster2 = new PaintbrushDisplay();
        Label bst1 = new Label("x" + MainScreen.boostersUsed1, 80);
        Label bst2 = new Label("x" + MainScreen.boostersUsed2, 80);
        addObject(booster1, getWidth()/2 + 95, getHeight()/2 + 126);
        addObject(booster2, getWidth()/2 + 294, getHeight()/2 + 140);
        addObject(bst1, getWidth()/2 + 140, getHeight()/2 + 170);
        addObject(bst2, getWidth()/2 + 335, getHeight()/2 + 170);
    }
    
    public static ArrayList<Integer> insertionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            int key = arr.get(i);
            int j = i - 1;
    
            // Move elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
        return arr;
    }
}
