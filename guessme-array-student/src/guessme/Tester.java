package guessme;

import java.util.Arrays;
import org.junit.Test;


public class Tester {
  public static void main(String[] args) {
    
  }
  //@Tcest
  public void play(int gt) {
    ArrayGame ag = new ArrayGame();
    int groundtruth = gt;
    int guess = 1000;
    while(ArrayGame.numMatches(groundtruth, guess) != 4) {
      guess = ag.getGuess();
      ag.updateGuess(ArrayGame.numMatches(guess, groundtruth));
    }
    System.out.println(guess);
    System.out.println(Arrays.toString(ag.priorGuesses()));
  }
  @Test
  public void playAll() {
    Tester t = new Tester();
    for(int i = 1000; i <= 9999; i++) {
      t.play(i);
    }
  }
}
