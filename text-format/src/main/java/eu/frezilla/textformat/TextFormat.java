package eu.frezilla.textformat;

public class TextFormat {

    public static void main(String[] args) {
        System.out.println("\033[1;3;4;5;37mhttps://en.wikipedia.org/wiki/ANSI_escape_code#In_shell_scripting\033[0m");
        
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                int n = 10 * i + j;
                if (n > 108) break;
                System.console().printf("\033[%dm %3d\033[m", n, n);
            }
            System.console().printf("\n");
        }
    }
}
