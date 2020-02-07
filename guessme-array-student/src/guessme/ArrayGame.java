package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;

  //FALSE MEANS NOT ELIMINATED, STILL CANDIDATE
  private boolean[] guessArray ;
  private int[] priorGuessArray;
  private int guessIndex;
  
  private int numCandidates = 9000;

  boolean isOver;



  // ArrayGame constructor method
  public ArrayGame() {
    reset();
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    guessArray = new boolean[9000];
    priorGuessArray = null;
    guessIndex = 0;
    numCandidates = 9000;
    guess = 1000;
    isOver = false;
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    for(int i = 0; i < priorGuessArray.length; i++) 
      if(priorGuessArray[i] == n)
        return true;


    return false;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    if(priorGuessArray == null) {
      return 0;
    }
    return priorGuessArray.length;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    int matches = 0;
    for(int i = 0; i < 4; i++) {
      int modTest = 0;
      while((a - modTest) % 10 != 0) {
        modTest++;
      }
      if((b - modTest) % 10 == 0) {
        matches++;
      }
      a /= 10;
      b /= 10;
    }
    return matches;
  }


  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    if(isOver) {
      return true;
    }
    int falseCounter = 0;
    for(int i = 0; i < guessArray.length; i++) {
      if(guessArray[i] == false) {
        falseCounter++;
      }
      if(falseCounter > 0) {
        return false;
      }
    }
    return true;
  }

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    if(priorGuessArray == null) 
      priorGuessArray = new int[0];

    int[] tempPriorGuessArray = new int[priorGuessArray.length + 1];
    for(int i = 0; i < priorGuessArray.length; i++) 
      tempPriorGuessArray[i] = priorGuessArray[i];//TRANSFER CONTENTS

    tempPriorGuessArray[tempPriorGuessArray.length - 1] = guess;
    priorGuessArray = tempPriorGuessArray;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   *
   *What this method does is to examine all candidate numbers, and eliminate 
   *those that do not have the same number of matches with the player’s current 
   *guess.
   */
  public boolean updateGuess(int nmatches) {
    if(nmatches == 4) {
      isOver = true;
      return isNoError();
    }
    eliminateNums(nmatches);
    guess = guessIndex + 1000;
    return isNoError();
  }
  private void eliminateNums(int nmatches) {
    for(int i = guessArray.length - 1; i >= 0; i--) {//CYCLE THROUGH ALL BOOLEANS IN THE GUESS ARRAY
      if(!guessArray[i] && numMatches(guess, (i + 1000)) != nmatches) {
        guessArray[i] = true;
        numCandidates--;
      }else if(guessArray[i] == false) {
        guessIndex = i;
      }
    }
  }
  private boolean isNoError() {
    return(numCandidates > 0);//THERE ARE NO FALSES IN THE ARRAY MEANING THAT THERE IS AN ERROR
  }

  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    return priorGuessArray;
  }
}
