package TDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void emptySeedTest() {
        GameOfLife gol = new GameOfLife(new int[20][20]);
        assertArrayEquals(new int[20][20], gol.getCurrentGeneration());

        gol = new GameOfLife(new int[10][10]);
        assertArrayEquals(new int[10][10], gol.getCurrentGeneration());
    }

    @Test
    void invalidSeedTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(new int[5][20]);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(new int[20][5]);
        });

        int seed[][] = {
                { 0, 2, 1, 1 },
                { 0, -1, 0, 1 },
                { 1, 1, -5, 1 },
                { 0, 0, 0, 0 }
        };

        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(seed);
        });

        int seed1[][] = {
                { 0, 1, 1, 1 },
                { 0, 1, 0 },
                { 1, 1, 1 },
                {}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(seed1);
        });

        int seed2[][] = {
                { 0, 1, 1 },
                { 0, 1, 0 },
                { 1, 1, 1 }
        };

        assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(seed2);
        });
    }
    
    @Test
    void randomSeedTest() {
        GameOfLife gol = new GameOfLife();
        generationSizeTest(gol, 20);

        gol = new GameOfLife(50);
        generationSizeTest(gol, 50);
    }

    @Test
    void toStringTest() {
        GameOfLife gol = new GameOfLife(new int[5][5]);
        String expectedString = "⬜⬜⬜⬜⬜\n" +
                                "⬜⬜⬜⬜⬜\n" +
                                "⬜⬜⬜⬜⬜\n" +
                                "⬜⬜⬜⬜⬜\n" +
                                "⬜⬜⬜⬜⬜\n";

        assertEquals(expectedString, gol.toString());

        int seed[][] = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        gol = new GameOfLife(seed);
        expectedString = "⬜⬜⬜⬜\n" +
                         "⬜⬛⬛⬜\n" +
                         "⬜⬛⬛⬜\n" +
                         "⬜⬜⬜⬜\n";

        assertEquals(expectedString, gol.toString());
    }

    @Test
    void beeHiveNextGeneration() {
        int seed[][] = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 0 },
                { 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };

        stillLifesTest(seed);
    }

    @Test
    void blockNextGeneration() {
        int seed[][] = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        stillLifesTest(seed);
    }

    @Test
    void boatNextGeneration() {
        int seed[][] = {
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 1, 0, 1, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        stillLifesTest(seed);
    }

    @Test
    void loafNextGeneration() {
        int seed[][] = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 1, 0 },
                { 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };

        stillLifesTest(seed);
    }

    @Test
    void tubNextGeneration() {
        int seed[][] = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 1, 0, 1, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        stillLifesTest(seed);
    }

    @Test
    void beaconNextGeneration() {
        int positionOne[][] = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        int positionTwo[][] = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };

        oscillatorsTest(positionOne, positionTwo);
    }

    @Test
    void blinkerNextGeneration() {
        int positionOne[][] = {   
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
        };
        int postionTwo[][] =     
        {   
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
        };

        oscillatorsTest(positionOne, postionTwo);
    }

    @Test
    void toadNextGeneration() {
        int positionOne[][] = {
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 1, 0 },
            { 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 }
        };
        int postionTwo[][] = {
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 1, 0 },
            { 0, 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 }
        };

        oscillatorsTest(positionOne, postionTwo);
    }

    @Test
    void gliderNextGenerationTest() {
        int positions[][][] = {
            {
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 1, 1, 1, 0 },
                { 0, 0, 0, 0 }
            },
            {
                { 0, 0, 0, 0 },
                { 1, 0, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 0, 0 }
            },
            {
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 },
                { 1, 0, 1, 0 },
                { 0, 1, 1, 0 }
            },
            {
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 1, 1 },
                { 0, 1, 1, 0 }
            },
            {
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 1 },
                { 0, 1, 1, 1 }
            }
        };

        generationTest(positions);
    }

    @Test
    void LWSSTest() {
        int positions[][][] = {
            {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 1 },
                { 0, 0, 0, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
            },
            {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 1, 1, 0 },
                { 0, 0, 0, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
            },
            {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
            },
            {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 1, 0, 0 },
                { 0, 1, 1, 0, 1, 1, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
            },
            {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 1, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
            }
        };

        generationTest(positions);
    }

    private void generationSizeTest(GameOfLife gol, int expectedSize) {
        int currentGeneration[][] = gol.getCurrentGeneration();
        
        assertNotNull(currentGeneration);
        assertEquals(expectedSize, currentGeneration.length);
        
        for (int[] generationArray: currentGeneration) {
            assertEquals(expectedSize, generationArray.length);
        }
    }

    private void generationTest(int[][][] positions) {
        GameOfLife gol = new GameOfLife(positions[0]);

        for (int i = 0; i < positions.length - 1; i++) {
            assertArrayEquals(positions[i], gol.getCurrentGeneration());
            gol.advanceToNextGeneration();
        }

        assertArrayEquals(positions[positions.length - 1], gol.getCurrentGeneration());
    }

    private void oscillatorsTest(int[][] positionOne, int[][] postionTwo) {
        generationTest(new int[][][]{positionOne, postionTwo, positionOne});
    }

    private void stillLifesTest(int[][] seed) {
        generationTest(new int[][][]{seed, seed});
    }
}
