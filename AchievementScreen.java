import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Write a description of class AchievementScreen here.
 * 
 * @author Megan Lee 
 * @version (a version number or a date)
 */
public class AchievementScreen extends World
{
    Scanner s;
    ArrayList scoreBoard = new ArrayList();
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
    }
    public void act(){
        boolean moreLines = true;
        int numLines = 0;
        while (moreLines){
            try{
                String temp = s.nextLine();
                System.out.println(temp);
                scoreBoard.add(temp);
                numLines++;
            }catch(NoSuchElementException e){
                moreLines = false;
            }
        }
        
    }
    public static int[] insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
