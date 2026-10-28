import java.util.Random; // for if a coconut falls

public class Main {
    public static void main(String[] args) throws InterruptedException {
        char[][] img;
        int width = 100;
        int height = 25;
        img = new char[height][width];
        int num_of_prints = 520; // switch to a larger number for a longer change

        int wave_pos = 0;
        int bird_dir = 1;
        int bird_pos = 10;
        Random rand = new Random();
        boolean falling_coco = false; // this will become true 1/100 of the time
        int coco_y = 10;

        // IMPORTED STRINGS
        String turtleString = "   ___\n" +
                           "  /@ @\\\n" +
                           "  \\   /\n" +
                           " __//|\\__\n" +
                           "/ /\\||//\\ \\\n" +
                           "\\/\\//|\\/\\/\n" +
                           "   \\|//\n" +
                           "    //\\\n" +
                           "   -  -   VC";
        AsciiArt turtle = new AsciiArt(turtleString);
        char[][] turtleGrid = turtle.getImg();

        String treeString = "          'Xx  xX*,\n" +
                    "        ,*xXXx_xXx\n" +
                    "          _xXXXXXxx*,\n" +
                    "        ,*XXx@x@Xx\n" +
                    "          X @|@@ `x\n" +
                    "           '  ||    '\n" +
                    "              ||\n" +
                    "              ||\n" +
                    "              ||\n" +
                    "              ||\n" +
                    "          /ssssssss.\n" +
                    "    /sssssssSSSSssssssssss.\n" +
                    "/sssssSSSSSSSSSSSSSSSssssssssssss. Dani";
        AsciiArt tree = new AsciiArt(treeString);
        char[][] treeGrid = tree.getImg();


        String eagleString = " _\\|             |/_\n" +
                        "_-  \\_   _/\"=_   _/  -_\n" +
                        "_-_    `-'(   )`-'    _-\n" +
                        " `=.__.=-(   )-=.__.='\n" +
                        "         |/-\\|\n" +
                        "cjr      Y   Y";
        AsciiArt eagle = new AsciiArt(eagleString);
        char[][] eagleGrid = eagle.getImg();        

        

        char[][] sun = new char[10][20]; // creates arrays for moving objects
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 20; k++) {
                sun[j][k] = ' ';
            }
        }

        char[][] coconut = new char[3][5];
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 5; k++) { // empty array for coconut
                coconut[j][k] = ' ';
            }
        }

        char[][] wave = new char[10][17];
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 17; k++) { // empty array for wave
                wave[j][k] = ' ';
            }
        }

        char[][] birdie = new char[4][15];
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 15; k++) { // empty array for birdie
                birdie[j][k] = ' ';
            }
        }

        img = create_initial_image(img, width, height, sun, coconut, wave, birdie); // makes the initial image

        for (int i = 0; i < num_of_prints; i++) { // runs for as many times as you choose

            for (int j = 0; j < treeGrid.length; j++) {         // tree
                for (int k = 0; k < treeGrid[j].length; k++) { 
                img[10+j][k] = treeGrid[j][k];
                }
            } // NOTE IM DOING TREE FIRST SO ITS IN THE BACKGROUND

            if (!falling_coco && rand.nextInt(100) == 67) { // must not alr be falling + 1/100 chance
                falling_coco = true;
                coco_y = 13; // puts it back in place

            }

            for (int j = 0; j < 10; j++) { // maps sun onto image
                for (int k = 0; k < 20; k++) { // here are the beam points
                    boolean beam_pixel = ((j == 2 && k >= 15 && k <= 18) || (j == 5 && k >= 15 && k <= 18)
                            || ((j == 7 || j == 8) && (k == 4 || k == 10)));

                    if (beam_pixel) {
                        if ((i / 4) % 2 == 0) { // every 4 frames it flickers
                            img[j][k] = sun[j][k];
                        } else {
                            img[j][k] = ' ';
                        }
                    } else {
                        img[j][k] = sun[j][k];
                    }
                } // the rest of the sun stays the same
            }

            for (int j = 0; j < 10; j++) { // maps wave
                for (int k = 0; k < 17; k++) {
                    if (k + wave_pos < 56 || k + wave_pos > 60) { // doesn't erase the tree
                        img[14 + j][k + wave_pos] = wave[j][k];
                    }

                }
            }

            for (int j = 0; j < 3; j++) { // maps coconut onto image
                for (int k = 0; k < 5; k++) {
                    img[10 + j][70 + k] = coconut[j][k];
                }
            }

            if (falling_coco) {
                for (int j = 0; j < 3; j++) { // now the coconut falls randomly
                    for (int k = 0; k < 5; k++) {
                        int old_row = coco_y + j;
                        int old_col = 70 + k;

                        if (old_row >= 0 && old_row < height && old_col >= 0 && old_col < width) {
                            img[old_row][old_col] = ' ';
                        }
                    }
                }

                coco_y++; // height decreases

                if (coco_y + 3 > height) { // stops falling at the bottom
                    falling_coco = false;
                } else {

                    for (int j = 0; j < 3; j++) { // now the coconut falls randomly
                        for (int k = 0; k < 5; k++) {
                            int row = coco_y + j;
                            int col = 70 + k;

                            if (row >= 0 && row < height && col >= 0 && col < width) {
                                if (coconut[j][k] != ' ') {
                                    img[row][col] = coconut[j][k];
                                }
                            }
                        }
                    }
                }

            }

            for (int j = 0; j < 5; j++) {
                for (int k = 20; k < width; k++) {
                    img[j][k] = ' ';
                }
            }

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 15; k++) {
                    img[1 + j][25 + k + bird_pos] = birdie[j][k];
                }
            }

            wave_pos++;
            bird_pos += bird_dir;
            if (bird_pos > 55 || bird_pos < 1) { // bird turns around when too left/right
                bird_dir *= -1;
            }
            if (wave_pos > width - 17) { // wave goes all the way to the end
                wave_pos = 0;
            }

            

            for (int k = 0; k < 2; k++) {
                System.out.println("");
            }  

            // inserting the foreground images (cited in readme)
            for (int j = 0; j < turtleGrid.length; j++) {         // turtle
                for (int k = 0; k < turtleGrid[j].length; k++) { 
                img[15+j][40+k] = turtleGrid[j][k];
                }
            }

            for (int j = 0; j < eagleGrid.length; j++) {         // eagle
                for (int k = 0; k < eagleGrid[j].length; k++) { 
                img[4+j][70+k] = eagleGrid[j][k];
                }
            }

            img = add_initials(img, width, height);
            print_img(img, width, height, turtleGrid); // prints the final image

            Thread.sleep(50); // make this number smaller for quicker prints and vice versa

        }

    }

    public static char[][] create_initial_image(char[][] img, int width, int height, char[][] sun, char[][] coconut,
            char[][] wave, char[][] birdie) {
        for (int i = 0; i < height; i++) { // fills in a blank grid of whatever character you choose first
            for (int j = 0; j < width; j++) {
                img[i][j] = ' ';
            }
        }
        // alters one pixel at a time of sun
        sun[1][2] = '/'; // this section is the sun circle
        sun[1][3] = '-';
        sun[1][4] = '-';
        sun[1][5] = '-';
        sun[1][6] = '-';
        sun[1][7] = '-';
        sun[1][8] = '-';
        sun[1][9] = '-';
        sun[1][10] = '-';
        sun[1][11] = '-';
        sun[1][12] = '\\';
        sun[2][1] = '/';
        sun[2][13] = '\\';
        sun[3][1] = '|';
        sun[3][13] = '|';
        sun[4][1] = '|';
        sun[4][13] = '|';
        sun[5][1] = '\\';
        sun[5][13] = '/';
        sun[6][2] = '\\';
        sun[6][3] = '-';
        sun[6][4] = '-';
        sun[6][5] = '-';
        sun[6][6] = '-';
        sun[6][7] = '-';
        sun[6][8] = '-';
        sun[6][9] = '-';
        sun[6][10] = '-';
        sun[6][11] = '-';
        sun[6][12] = '/';

        sun[2][15] = '-'; // this section is the rotating beams of the sun
        sun[2][16] = '-';
        sun[2][17] = '-';
        sun[2][18] = '-';
        sun[5][15] = '-';
        sun[5][16] = '-';
        sun[5][17] = '-';
        sun[5][18] = '-';
        sun[7][4] = '|';
        sun[8][4] = '|';
        sun[7][10] = '|';
        sun[8][10] = '|';

        coconut[0][0] = '.'; // coconut
        coconut[0][1] = '.';
        coconut[0][2] = '.';
        coconut[0][3] = '.';
        coconut[0][4] = '.';
        coconut[1][0] = '.';
        coconut[1][1] = '.';
        coconut[1][2] = '.';
        coconut[1][3] = '.';
        coconut[1][4] = '.';
        coconut[2][0] = '.';
        coconut[2][1] = '.';
        coconut[2][2] = '.';
        coconut[2][3] = '.';
        coconut[2][4] = '.';

        wave[9][0] = 'O'; // wave
        wave[9][1] = 'O';
        wave[9][2] = 'O';
        wave[9][3] = 'O';
        wave[9][4] = 'O';
        wave[9][5] = 'O';
        wave[9][6] = 'O';
        wave[9][7] = 'O';
        wave[9][8] = 'O';
        wave[9][9] = 'O';
        wave[9][10] = 'O';
        wave[9][11] = 'O';
        wave[9][12] = 'O';
        wave[9][13] = 'O';
        wave[9][14] = 'O';
        wave[9][15] = 'O';
        wave[9][16] = 'O';
        wave[8][2] = 'O';
        wave[8][3] = 'O';
        wave[8][4] = 'O';
        wave[8][5] = 'O';
        wave[8][6] = 'O';
        wave[8][7] = 'O';
        wave[8][8] = 'O';
        wave[8][9] = 'O';
        wave[8][10] = 'O';
        wave[8][11] = 'O';
        wave[8][12] = 'O';
        wave[8][13] = 'O';
        wave[8][14] = 'O';
        wave[8][15] = 'O';
        wave[7][5] = 'O';
        wave[7][6] = 'O';
        wave[7][7] = 'O';
        wave[7][8] = 'O';
        wave[7][9] = 'O';
        wave[7][10] = 'O';
        wave[7][11] = 'O';
        wave[7][12] = 'O';
        wave[7][13] = 'O';
        wave[7][14] = 'O';
        wave[6][1] = 'O';
        wave[6][7] = 'O';
        wave[6][8] = 'O';
        wave[6][9] = 'O';
        wave[6][10] = 'O';
        wave[6][11] = 'O';
        wave[6][12] = 'O';
        wave[6][13] = 'O';
        wave[6][14] = 'O';
        wave[5][1] = 'O';
        wave[5][2] = 'O';
        wave[5][7] = 'O';
        wave[5][8] = 'O';
        wave[5][9] = 'O';
        wave[5][10] = 'O';
        wave[5][11] = 'O';
        wave[5][12] = 'O';
        wave[5][13] = 'O';
        wave[4][2] = 'O';
        wave[4][3] = 'O';
        wave[4][6] = 'O';
        wave[4][7] = 'O';
        wave[4][8] = 'O';
        wave[4][9] = 'O';
        wave[4][10] = 'O';
        wave[4][11] = 'O';
        wave[4][12] = 'O';
        wave[3][3] = 'O';
        wave[3][4] = 'O';
        wave[3][5] = 'O';
        wave[3][6] = 'O';
        wave[3][7] = 'O';
        wave[3][8] = 'O';
        wave[3][9] = 'O';
        wave[3][10] = 'O';
        wave[3][11] = 'O';
        wave[2][4] = 'O';
        wave[2][5] = 'O';
        wave[2][6] = 'O';
        wave[2][7] = 'O';
        wave[2][8] = 'O';
        wave[2][9] = 'O';
        wave[2][10] = 'O';
        wave[1][6] = 'O';
        wave[1][7] = 'O';
        wave[1][8] = 'O';

        img[7][52] = '~'; // TREE
        img[7][53] = '~';
        img[7][54] = '~';
        img[7][55] = '~';
        img[7][56] = '~';
        img[7][57] = '~';
        img[7][58] = '~';
        img[7][59] = '~';
        img[7][60] = '~';
        img[7][61] = '~';
        img[7][62] = '~';
        img[7][63] = '~';
        img[7][64] = '~';
        img[7][65] = '~';
        img[7][47] = '~';
        img[7][48] = '~';
        img[7][49] = '~';
        img[7][50] = '~';
        img[7][51] = '~';
        img[7][52] = '~';
        img[7][53] = '~';
        img[7][54] = '~';
        img[7][55] = '~';
        img[7][56] = '~';
        img[7][57] = '~';
        img[7][58] = '~';
        img[7][59] = '~';
        img[7][60] = '~';
        img[7][61] = '~';
        img[7][62] = '~';
        img[7][63] = '~';
        img[7][64] = '~';
        img[7][65] = '~';
        img[7][66] = '~';
        img[7][67] = '~';
        img[7][68] = '~';
        img[7][69] = '~';
        img[8][45] = '~';
        img[8][46] = '~';
        img[8][47] = '~';
        img[8][48] = '~';
        img[8][49] = '~';
        img[8][50] = '~';
        img[8][51] = '~';
        img[8][52] = '~';
        img[8][53] = '~';
        img[8][54] = '~';
        img[8][55] = '~';
        img[8][56] = '~';
        img[8][57] = '~';
        img[8][58] = '~';
        img[8][59] = '~';
        img[8][60] = '~';
        img[8][61] = '~';
        img[8][62] = '~';
        img[8][63] = '~';
        img[8][64] = '~';
        img[8][65] = '~';
        img[8][66] = '~';
        img[8][67] = '~';
        img[8][68] = '~';
        img[8][69] = '~';
        img[8][70] = '~';
        img[8][71] = '~';
        img[9][43] = '~';
        img[9][44] = '~';
        img[9][45] = '~';
        img[9][46] = '~';
        img[9][47] = '~';
        img[9][48] = '~';
        img[9][49] = '~';
        img[9][50] = '~';
        img[9][51] = '~';
        img[9][52] = '~';
        img[9][53] = '~';
        img[9][54] = '~';
        img[9][55] = '~';
        img[9][56] = '~';
        img[9][57] = '~';
        img[9][58] = '~';
        img[9][59] = '~';
        img[9][60] = '~';
        img[9][61] = '~';
        img[9][62] = '~';
        img[9][63] = '~';
        img[9][64] = '~';
        img[9][65] = '~';
        img[9][66] = '~';
        img[9][67] = '~';
        img[9][68] = '~';
        img[9][69] = '~';
        img[9][70] = '~';
        img[9][71] = '~';
        img[9][72] = '~';
        img[9][73] = '~';
        img[10][56] = 'x';
        img[10][57] = 'x';
        img[10][58] = 'x';
        img[10][59] = 'x';
        img[10][60] = 'x';
        img[11][56] = 'x';
        img[11][57] = 'x';
        img[11][58] = 'x';
        img[11][59] = 'x';
        img[11][60] = 'x';
        img[12][56] = 'x';
        img[12][57] = 'x';
        img[12][58] = 'x';
        img[12][59] = 'x';
        img[12][60] = 'x';
        img[13][56] = 'x';
        img[13][57] = 'x';
        img[13][58] = 'x';
        img[13][59] = 'x';
        img[13][60] = 'x';
        img[14][56] = 'x';
        img[14][57] = 'x';
        img[14][58] = 'x';
        img[14][59] = 'x';
        img[14][60] = 'x';
        img[15][56] = 'x';
        img[15][57] = 'x';
        img[15][58] = 'x';
        img[15][59] = 'x';
        img[15][60] = 'x';
        img[16][56] = 'x';
        img[16][57] = 'x';
        img[16][58] = 'x';
        img[16][59] = 'x';
        img[16][60] = 'x';
        img[17][56] = 'x';
        img[17][57] = 'x';
        img[17][58] = 'x';
        img[17][59] = 'x';
        img[17][60] = 'x';
        img[18][56] = 'x';
        img[18][57] = 'x';
        img[18][58] = 'x';
        img[18][59] = 'x';
        img[18][60] = 'x';
        img[19][56] = 'x';
        img[19][57] = 'x';
        img[19][58] = 'x';
        img[19][59] = 'x';
        img[19][60] = 'x';
        img[20][56] = 'x';
        img[20][57] = 'x';
        img[20][58] = 'x';
        img[20][59] = 'x';
        img[20][60] = 'x';
        img[21][56] = 'x';
        img[21][57] = 'x';
        img[21][58] = 'x';
        img[21][59] = 'x';
        img[21][60] = 'x';
        img[22][56] = 'x';
        img[22][57] = 'x';
        img[22][58] = 'x';
        img[22][59] = 'x';
        img[22][60] = 'x';
        img[23][56] = 'x';
        img[23][57] = 'x';
        img[23][58] = 'x';
        img[23][59] = 'x';
        img[23][60] = 'x';

        birdie[2][0] = '/'; // bird
        birdie[1][1] = '_';
        birdie[2][2] = '\\';
        birdie[2][3] = '/';
        birdie[1][4] = '_';
        birdie[2][5] = '\\';

        return img; // returns the completed image
    }

    public static void print_img(char[][] img, int width, int height, char[][] turtle) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(img[i][j]); // nested for loop to print the whole image
            }
            System.out.println(""); // extra line for spacing
        }

    }

    public static char[][] add_initials(char[][] img, int width, int height) { // adding initials ;)
        img[height - 1][width - 2] = 'A';
        img[height - 1][width - 1] = 'B';

        return img;
    }
}

 class AsciiArt {
    char[][] img;
    int height;
    int width;

    // constructor
    public AsciiArt(char[][] img) {
        this.img = img;
        this.height = img.length;
        this.width = img[0].length;
    }

    public AsciiArt(String art) {
        this.processArt(art);
    }

    // generate new copy from original already processed
    public AsciiArt(AsciiArt original) {
        this.img = original.img;
        this.height = original.height;
        this.width = original.width;
    }

    private void processArt(String art) {
        String[] lines = art.split("\n");
        this.height = lines.length;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > this.width) {
                this.width = lines[i].length(); // max width!
            }
        }
        this.img = new char[height][width];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                img[i][j] = lines[i].charAt(j);
            }
        }
        // fill all zeroes with empty space
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (img[i][j] == 0) {
                    img[i][j] = ' ';
                } // will not allow overlap.
            }
        }
    }

    public void resize(int width2) {
        // only resizing upwards for now
        // add some columns, only worth repeating the pattern n times if possible.
        if (width2 / width > 1) {
            char[][] newImg = new char[height][width2];
            for (int i = 0; i < height; i++) {
                // copy existing pattern only if it fits
                for (int j = 0; j < width2 && j / width < (width2 / width); j++) {
                    newImg[i][j] = img[i][j % width]; // next to itself
                }
                // #3. fill the rest with empty space if the image is narrower than IMAGE_WIDTH
                for (int j = (width2 / width) * width; j < width2; j++) {
                    newImg[i][j] = ' ';
                }
            }
            this.img = newImg;
            this.width = width2;
        }

    }

    char[][] getImg() {
        return this.img;
    }

    AsciiArt switchRowsAndCols() {
        char[][] newImg = new char[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newImg[j][i] = img[i][j];
            }
        }
        this.img = newImg;
        int temp = this.width;
        this.width = this.height;
        this.height = temp;
        return this;
    }

    static AsciiArt getWidestArt(AsciiArt[] asciis) {
        if (asciis.length == 0) {
            return null;
        }
        AsciiArt widest = asciis[0];
        for (AsciiArt art : asciis) {
            if (art.width > widest.width) {
                widest = art;
            }
        }
        return widest;
    }

    // really only for debugging
    private void printArt() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.img[i][j]);
            }
            System.out.println();
        }
    }

}

