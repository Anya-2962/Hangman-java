import java.util.*;

class Hangman 
{
    private static final String[] WORDS = { "java", "program", "computer", "hangman", "developer", "algorithm", "keyboard"};
    private static final int MAX_TRIES = 6;
    private String selectedWord;
    private char[] guessedWord;
    private Set<Character> guessedLetters;
    private int remainingTries;
    
    Hangman() 
    {
        Random rand = new Random();
        selectedWord = WORDS[rand.nextInt(WORDS.length)];
        guessedWord = new char[selectedWord.length()];
        Arrays.fill(guessedWord, '_');
        guessedLetters = new HashSet<>();
        remainingTries = MAX_TRIES;
    }
    
    void play() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("🎮 Welcome to Hangman Game!");
        while (remainingTries > 0 && !isWordGuessed()) 
        {
            displayHangman(); // Draw the hangman
            displayWord();
            System.out.println("Guessed Letters: " + guessedLetters);
            System.out.println("Remaining tries: " + remainingTries);
            System.out.print("Guess a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);
            if (!Character.isLetter(guess)) {
                System.out.println("❗ Invalid input. Please enter a letter.");
                continue;
            }
            if (guessedLetters.contains(guess)) {
                System.out.println("⚠️ You already guessed that letter.");
                continue;
            }
            guessedLetters.add(guess);
            if (updateGuessedWord(guess))
                System.out.println("✅ Good guess!");
            else
            {
                System.out.println("❌ Wrong guess!");
                remainingTries--;
            }
        }

        if (isWordGuessed()) 
        {
            System.out.println("\n🎉 Congratulations! You guessed the word: " + selectedWord);
        } else {
            displayHangman(); // Final drawing
            System.out.println("\n💀 Game Over! The word was: " + selectedWord);
        }
        
        sc.close();
    }

    private void displayWord() 
    {
        System.out.print("Word: ");
        for (char c : guessedWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private boolean updateGuessedWord(char guess) 
    {
        boolean found = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                found = true;
            }
        }
        return found;
    }

    private boolean isWordGuessed() 
    {
        return selectedWord.equals(new String(guessedWord));
    }

    private void displayHangman()
    {
        int stage = MAX_TRIES - remainingTries;
        System.out.println(" _______");
        System.out.println(" |     |");

        switch (stage) 
        {
            case 0:
                System.out.println(" |     ");
                System.out.println(" |     ");
                System.out.println(" |     ");
                System.out.println(" |     ");
                break;
            case 1:
                System.out.println(" |     O");
                System.out.println(" |     ");
                System.out.println(" |     ");
                System.out.println(" |     ");
                break;
            case 2:
                System.out.println(" |     O");
                System.out.println(" |     |");
                System.out.println(" |     ");
                System.out.println(" |     ");
                break;
            case 3:
                System.out.println(" |     O");
                System.out.println(" |    /|");
                System.out.println(" |     ");
                System.out.println(" |     ");
                break;
            case 4:
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |     ");
                System.out.println(" |     ");
                break;
            case 5:
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |    / ");
                System.out.println(" |     ");
                break;
            case 6:
                System.out.println(" |     O");
                System.out.println(" |    /|\\");
                System.out.println(" |    / \\");
                System.out.println(" |     ");
                break;
        }
        System.out.println("_|_");
        System.out.println();
    }

    void main() 
    {
        Hangman game = new Hangman();
        game.play();
    }
}
