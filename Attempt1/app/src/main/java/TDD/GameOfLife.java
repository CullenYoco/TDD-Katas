package TDD;

import java.util.Random;

public class GameOfLife {
    private int currentGeneration[][];
    private int nextGeneration[][];
    private int gameSize;
    private final static int DEFAULT_GAME_SIZE = 20;

    public void advanceToNextGeneration() {
        calculateNextGeneration();

        copy2DGenerationArray(currentGeneration, nextGeneration);
    }
    
    public GameOfLife() {
        this(createRandomArray(DEFAULT_GAME_SIZE));
    }

    public GameOfLife(int gameSize) {
        this(createRandomArray(gameSize));
    }

    public GameOfLife(int[][] seed) {
        if (isSeedStructureInvalid(seed)) {
            throw new IllegalArgumentException();
        }

        initializeGameOfLifeComponents(seed);
    }

    public int[][] getCurrentGeneration() {
        int currentGenerationCopy[][] = newGenerationArray();
        
        copy2DGenerationArray(currentGenerationCopy, currentGeneration);

        return currentGenerationCopy;
    }

    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();

        gol.runGUI();
    }

    public void runGUI() {
        while (true) {
            clearOutputStream();
            int prevGen[][] = getCurrentGeneration();

            System.out.println(this + "\n");
            advanceToNextGeneration();
            
            if (isGameEndedByStagnation(prevGen)) {
                break;
            }

            slowPrinting(250); 
        }
    }   

    @Override
    public String toString() {
        String out = "";

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                out += isCellAlive(i, j) ? "⬛" : "⬜"; 
            }
            out += "\n";
        }

        return out;
    }

    private void calculateNextGeneration() {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (isCellSurviving(i, j)) {
                    nextGeneration[i][j] = 1;
                } else {
                    nextGeneration[i][j] = 0;
                }
            }
        }
    }

    private void clearOutputStream() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void copy2DGenerationArray(int[][] destArray, int[][] srcArray) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                destArray[i][j] = srcArray[i][j];
            }
        }
    }

    private void copyAndTestSeedValues(int[][] seed) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (isSeedValueInvalid(seed, i, j)) {
                    throw new IllegalArgumentException();
                }

                currentGeneration[i][j] = seed[i][j];
            }
        }
    }

    private static int[][] createRandomArray(int gameSize) {
        int randomArray[][] = new int[gameSize][gameSize];
        Random randomGenerator = new Random();

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                randomArray[i][j] = randomGenerator.nextInt(2);
            }
        }

        return  randomArray;
    }

    private void initializeGameOfLifeComponents(int[][] seed) {
        gameSize = seed.length;
        currentGeneration = newGenerationArray();
        nextGeneration = newGenerationArray();

        copyAndTestSeedValues(seed);
    }

    private boolean isCellAlive(int i, int j) {
        return currentGeneration[i][j] == 1;
    }

    private boolean isCellSurviving(int i, int j) {
        int noNeighbours = numberOfNeighbours(i, j);
        return noNeighbours == 3 || (isCellAlive(i, j) && noNeighbours == 2);
    }

    private boolean isGameEndedByStagnation(int[][] prevGen) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (currentGeneration[i][j] != prevGen[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isInValidRange(int i) {
        return i < gameSize && i > -1;
    }

    private boolean isSeedSquare(int[][] seed) {
        for (int i = 0; i < seed.length; i++) {
            if (seed[i].length != seed.length) {
                return false;
            }
        }

        return true;
    }

    private boolean isSeedStructureInvalid(int[][] seed) {
        return seed == null || seed.length < 4 || !isSeedSquare(seed);
    }

    private boolean isSeedValueInvalid(int[][] seed, int i, int j) {
        return seed[i][j] != 0 && seed[i][j] != 1;
    }

    private int[][] newGenerationArray() {
        return new int[gameSize][gameSize];
    }

    private int numberOfNeighbours(int testI, int testJ) {
        int count = isCellAlive(testI, testJ) ? -1 : 0;
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int potentialNeighbourI = testI + i;
                int potentialNeighbourJ = testJ + j;

                if (isInValidRange(potentialNeighbourI) && isInValidRange(potentialNeighbourJ)){
                    if (isCellAlive(potentialNeighbourI, potentialNeighbourJ)) {
                        count += 1;
                    }
                }
            }
        }
        
        return count;
    }

    private void slowPrinting(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
