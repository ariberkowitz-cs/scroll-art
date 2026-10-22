import java.util.random.*; // for if a coconut falls

public class Main {
    public static void main(String[] args) {
        char[][] img;
        int width = 20;
        int height = 10;
        img = new char[width][height];
        int num_of_prints = 100;
        
        create_initial_image(img);

        for (int i = 0; i<num_of_prints-1;i++) { // runs for as many times as you choose
            img = birdie(img); // alters the entire image for these 4 methods
            img = wave(img);
            img = sun(img);
            img = coconut(img);
            
            print_img(img); // prints the final
        }



    }

    public static char[][] create_initial_image(char[][] img) {
        return img;
    }

    public static void print_img(char[][] img) {

    }

    public static char[][] birdie(char[][] img) {
        return img;
    }

    public static char[][] wave(char[][] img) {
        return img;
    }
   
    public static char[][] sun(char[][] img) {
        return img;
    }

    public static char[][] coconut(char[][] img) {
        return img;
    }

}
