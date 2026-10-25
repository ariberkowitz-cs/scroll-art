import java.util.random.*; // for if a coconut falls

public class Main {
    public static void main(String[] args) throws InterruptedException {
        char[][] img;
        int width = 100;
        int height = 25;
        img = new char[height][width];
        int num_of_prints = 200; // switch to a larger number for a longer change
        
        int wave_pos = 0; 

        char[][] sun = new char[10][20]; // creates arrays for moving objects
        for (int j=0; j<10;j++) {
            for (int k=0; k<20;k++) {
                sun[j][k] = ' ';}
        }

        char[][] coconut = new char[14][5];
        for (int j=0; j<14;j++) {
            for (int k=0; k<5;k++) {
                coconut[j][k] = ' ';}
        }

        char[][] wave = new char[10][17];
        for (int j=0; j<10;j++) {
            for (int k=0; k<17;k++) {
                wave[j][k] = ' ';}
        }

        char[][] birdie = new char[50][8];
        
        img = create_initial_image(img, width, height, sun, coconut, wave, birdie); // makes the initial image

        for (int i = 0; i<num_of_prints;i++) { // runs for as many times as you choose
            birdie = birdie(birdie); // alters the entire image for these 4 methods
            wave = wave(wave);
            sun = sun(sun);
            coconut = coconut(coconut);
            
            for (int j=0; j<10;j++) { // maps sun onto image
                for (int k=0; k<20;k++) {
                    img[j][k] = sun[j][k];}
                }
            
            for (int j=0; j<14;j++) { // maps coconut onto image
                for (int k=0; k<5;k++) {
                    img[10+j][70+k] = coconut[j][k];}
                }
            
            for (int j=0; j<10;j++) {
                for (int k=0; k<17;k++) {
                    if (k+wave_pos>60 || k+wave_pos<56) {
                    img[14+j][k+wave_pos] = wave[j][k];}

                }
            }
            wave_pos++;
            if (wave_pos>width-17) {wave_pos=0;}

            print_img(img, width, height); // prints the final image

            for (int k=0;k<2;k++) {System.out.println("");}
            Thread.sleep(50); // make this number smaller for quicker prints and vice versa

             
        }



    }

    public static char[][] create_initial_image(char[][] img, int width, int height, char[][] sun, char[][] coconut, char[][] wave,char[][] birdie) {
        for (int i=0; i<height;i++) { // fills in a blank grid of whatever character you choose first
            for (int j=0; j<width; j++) {
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

        coconut[0][0]='.'; // coconut
        coconut[0][1]='.';
        coconut[0][2]='.';
        coconut[0][3]='.';
        coconut[0][4]='.';
        coconut[1][0]='.';
        coconut[1][1]='.';
        coconut[1][2]='.';
        coconut[1][3]='.';
        coconut[1][4]='.';
        coconut[2][0]='.';
        coconut[2][1]='.';
        coconut[2][2]='.';
        coconut[2][3]='.';
        coconut[2][4]='.';
        
        wave[9][0] = 'O';
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
        
        
        
        

        return img; // returns the completed image
    }

    public static void print_img(char[][] img, int width, int height) {
        for (int i=0; i<height;i++) {
            for (int j=0; j<width; j++) {
                System.out.print(img[i][j]);
            }
            System.out.println("");
        }

        
    }

    public static char[][] birdie(char[][] birdie) {
        return birdie;
    }

    public static char[][] wave(char[][] wave) {
        return wave;
    }
   
    public static char[][] sun(char[][] sun) {
        return sun;
    }

    public static char[][] coconut(char[][] coconut) {
        return coconut;
    }

}
