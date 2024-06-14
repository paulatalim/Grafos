import java.util.Scanner;

import system.Hospital;

// import graph.AGM.ArvoreGeradoraMinima;
// import graph.busca.DepthFirstSearch;
// import graph.representacao.lista.ListaManage;
// import graph.representacao.matriz.MatrizManage;

/**
 * PONTÍFICIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS
 * Trabalho acadêmico de Algoritmos em Grafos
 * 
 * @author Gabriel Luís Pinto Cecconello
 * @author Paula Cristina Talim Gonçalves
 * @author Rafael Vicente Souza E Paula
 * 
 * DATA DE CRIAÇÃO: 12 de jun. de 2024
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();
        boolean opcao_invalida;
        int opcao;

        // Repete o programa
        do {

            // Reinicia a variavel
            opcao_invalida = false;

            // Validacao da entrada do usuario
            do {
                // Exibe o menu das opcoes
                UI.print(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t" + "  *** DOCTORS WITHOUT WEEKENDS ***  " + "\n\n\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "O que deseja fazer" + "\n\n");

                UI.println("\t" + "1 - Cadastrar feriado");
                if(hospital.getC() == -1) UI.println("\t" + "2 - Cadastrar disponibilidade de dias dos médicos plantonistas");
                else UI.println("\t" + "2 - Cadastrar médico");
                if(hospital.HolidayListToString() != null) UI.println("\t" + "3 - Exibir feriados");
                if(hospital.DoctorsListToString() != null) UI.println("\t" + "4 - Exibir lista de médico plantonistas");
                if(hospital.HolidayListToString() != null && hospital.DoctorsListToString() != null) {
                    UI.println("\t" + "5 - Inserir disponibilidade para plantão");
                    UI.println("\t" + "6 - Gerar Relatorio de Plantão");
                }
                UI.println("\t" + "0 - SAIR" + "\n");

                if (opcao_invalida) {
                    UI.println(UI.RED + "\t" + "Opcao inválida. Tente novavemente." + "\n");
                }
                UI.print(UI.CYAN + "\t" + "Insira o número da operação: " + UI.WHITE);
                opcao = scanner.nextInt();
                UI.limpar_console();

                if((hospital.getC() == -1 && opcao >= 5)
                || (hospital.DoctorsListToString() == null && (opcao == 4)) 
                || (hospital.HolidayListToString() == null && (opcao == 3 || opcao >= 6))) {
                    opcao_invalida = true;
                }

                // Valida a resposta do usuario
            } while (opcao < 0 || opcao > 6 || opcao_invalida);

            switch (opcao) {
                case 1:
                    // Cadastrar feriado
                    hospital.addHoliday("Carnaval", 4);
                    break;
                
                case 2:
                    if(hospital.getC() == -1) {
                        // Cadastrar disponibilidade de dias dos médicos plantonistas
                        hospital.setC(5);
                    } else {
                        // Cadastrar médico
                        hospital.addDoctor("Natalia");
                    }

                    break;
                
                case 3:
                    // Exibir feriados
                    UI.print(hospital.HolidayListToString());
                    break;
                
                case 4:
                    // Exibir lista de médico plantonistas
                    UI.print(hospital.DoctorsListToString());
                    break;
                
                case 5:
                    break;
                
                case 6:
                    break;
                
                case 0:
                    UI.println(UI.YELLOW + "Desligando sistema ...\n\n\n"
                            + UI.PURPLE_BACKGROUND + UI.WHITE + "\t\t\t\t\t   Obrigado e volte sempre   \n\n"
                            + UI.RESET);
                    break;
            }

            // Preparo para reiniciar programa

            if (opcao != 0) {
                UI.exibir_fim_tela();
            }

        } while (opcao != 0);

        scanner.close();
    }
}
