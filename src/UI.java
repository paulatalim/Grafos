/**
 * Recursos de User Interface
 */
public class UI {
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
        System.out.print("\n\n\t" + "Pressione 'enter' para continuar . . .");
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
