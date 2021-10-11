package com.company.controllers;

import com.company.models.WordList;
import com.company.views.CmdLineView;

public class Main {

    public static String[] letters;
    public static int numLetters = 0;
    public static int numGuesses;
    public static String theGuess;
    public static String[] hints;
    public static  CmdLineView view;
    public static boolean correct = false;
    public static boolean underscore = false;

    public static void main(String[] args) {

        numGuesses = 6;

        GetWord getWord = new GetWord();
        String theWord = getWord.getTheWord();

        WordList word = new WordList(theWord);

        //WordList word = new WordList(getWord.getTheWord());

        letters = calculateLetters(word.getTheWord());

        view = new CmdLineView(letters);
        view.startGame();
       // view.cheat(word.getTheWord());
        hints = new String[numLetters];
        for(int i = 0; i < numLetters; i++) {
            hints[i] = "_";
        }
        while(numGuesses > 0){
            underscore = false;
            correct = false;
            hints = guess();
            view.displayHints(hints);
            if (underscore == false){
                view.gameOverCorrect(theWord);
                System.exit(0);
            }
            //display
            //System.out.println(hints);
        }
        if (numGuesses == 0){
            view.gameOverIncorrect(theWord);

        }

    }

    private static String[] calculateLetters(String theWord){
        String[] letters = theWord.split("");
        numLetters = letters.length;
        return letters;
    }

    private static String[] guess(){

        theGuess = view.makeAGuess();

        String msg = "";

        //String[] hints = new String[letters.length];

        for(int i = 0; i < letters.length; i++){
            if(letters[i].equals(theGuess)){
                hints[i] = theGuess;
                correct = true;
            }
            else {
                //hints[i] = "_";
                //view.incorrectGuess(theGuess);
            }
        }

        if (correct == true){
            view.correctGuess(theGuess);
        }
        else if (correct == false){
            view.incorrectGuess(theGuess);
            numGuesses = numGuesses -1;
        }
        for(int i = 0; i < letters.length; i++){
            if (hints[i] == "_"){
                underscore = true;
            }
        }



        return hints;
    }
}
// DONE!!! fix hints to start with underscores, so that it can be made to remember DONE!!!
// bool for corrrect/ incorrect and make it appear once, not 6 times per guess
// make it so game ends after 6 failed guesses.
// make it so you can win
