/**
 * Recursos de User Interface
 */
public class UI {
    // Cores para texto do terminal
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Limpa a tela do console de windows e MacOS
     */
    public static void limpar_console() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    /**
     * Exibe mensagem no fim da tela
     */
    public static void exibir_fim_tela() throws Exception {
        System.out.print(UI.BLACK_BACKGROUND + UI.GREEN + "\n\n\t" + "Pressione 'enter' para continuar . . .");
        System.in.read();
        System.in.read(new byte[System.in.available()]);
        limpar_console();
    }

    /**
     * Imprime um texto no console
     * 
     * @param text - texto a ser imprimido
     */
    public static void print(String text) {
        System.out.print(text);
    }

    /**
     * Imprime um texto no console com quebra de linha
     * @param text - texto a ser imprimido
     */
    public static void println(String text) {
        System.out.println(text);
    }
}
