public class game_of_life {
    public static void main(String[] args) throws InterruptedException {
        int rows = 25;
        int cols = 80;
        char[][] conwaygamegrid = new char[rows][cols];
        int printnum = 100;

        for (int i = 0; i < rows; i++) { // creates grid of O cells
            for (int j = 0; j < cols; j++) {
                conwaygamegrid[i][j] = ' ';
            }
        }

        // STARTING PATTERN
        conwaygamegrid[7][13] = 'O';
        conwaygamegrid[7][14] = 'O';
        conwaygamegrid[5][14] = 'O';
        conwaygamegrid[6][16] = 'O';
        conwaygamegrid[7][17] = 'O';
        conwaygamegrid[7][18] = 'O';
        conwaygamegrid[7][19] = 'O';

        for (int i = 0; i < printnum; i++) { // goes printnum amount of times
            print_grid(conwaygamegrid, rows, cols);

            conwaygamegrid = next_gen(conwaygamegrid, rows, cols);

            Thread.sleep(200); // sleeps for 500 milliseconds
        }
    }

    // this one prints the grid in the terminal
    public static void print_grid(char[][] grid, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < 5; i++) // change this to change space between gens
        {
            System.out.println("");
        }
    }

    // makes the next gen in a new char array so it doesn't mess up the current one
    // not this method calls the friends method
    public static char[][] next_gen(char[][] current_grid, int rows, int cols) {
        char[][] next_gen = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int friends = num_of_friends(current_grid, i, j, rows, cols); // a new method

                if (current_grid[i][j] == 'O') {
                    // SURVIVE IF 2 OR 3 NEIGHBORS
                    if (friends == 2 || friends == 3) {
                        next_gen[i][j] = 'O';
                    } else {
                        next_gen[i][j] = ' '; // too many or few neighbors = die
                    }
                } else {
                    if (friends == 3) { // rebirth if 3 neighbors
                        next_gen[i][j] = 'O';

                    } else {
                        next_gen[i][j] = ' ';
                    }
                }
            }
        }

        return next_gen;
    }

    public static int num_of_friends(char[][] grid, int a, int b, int rows, int cols) {
        int friends = 0; // this will get returned

        // up
        if (a > 0 && grid[a - 1][b] == 'O') {
            friends++;
        }

        // down
        if (a < rows - 1 && grid[a + 1][b] == 'O') {
            friends++;
        }

        // left
        if (b > 0 && grid[a][b - 1] == 'O') {
            friends++;
        }

        // right
        if (b < cols - 1 && grid[a][b + 1] == 'O') {
            friends++;
        }

        // up+left
        if (a > 0 && b > 0 && grid[a - 1][b - 1] == 'O') {
            friends++;
        }

        // down+left
        if (a < rows - 1 && b > 0 && grid[a + 1][b - 1] == 'O') {
            friends++;
        }

        // up+right
        if (a > 0 && b < cols - 1 && grid[a - 1][b + 1] == 'O') {
            friends++;
        }

        // down+right
        if (a < rows - 1 && b < cols - 1 && grid[a + 1][b + 1] == 'O') {
            friends++;
        }

        return friends;
    }
}