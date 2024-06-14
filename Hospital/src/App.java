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
        boolean opcao_invalida;
        int opcao;
        int resposta_int;
        String resposta_str_1;
        // String resposta_str_2;
        
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

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
                if(hospital.DoctorsListToString() != null) UI.println("\t" + "4 - Exibir lista de médicos cadastrados");
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

                    UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** FERIADOS ***   \n\n");
                    UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o nome e a duração do feriado\n");

                    // Entrada do nome do feriado
                    UI.print(UI.CYAN + "\tNome: " + UI.WHITE);
                    resposta_str_1 = scanner.next();
                    UI.println("");

                    // Entrada da duracao do feriado
                    UI.print(UI.CYAN + "\tDuração: " + UI.WHITE);
                    resposta_int = scanner.nextInt();
                    UI.println("");

                    // Adiciona o feriado
                    hospital.addHoliday(resposta_str_1, resposta_int);
                    break;
                
                case 2:
                    if(hospital.getC() == -1) {
                        // Cadastrar disponibilidade de dias dos médicos plantonistas
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** FERIADOS ***   \n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira a quantidade de dias máximos de plantão que um médico pode fazer\n");
                        
                        // Entrada da quantidade maxima de dias de plantao
                        UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
                        resposta_int = scanner.nextInt();

                        // Configura a quantidade maxima de dias de plantao
                        hospital.setC(resposta_int);
                    } else {
                        // Cadastrar médico
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** CADASTRO DE MÉDICO ***   \n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o nome do médico a seguir\n");

                        // Entrada do nome do feriado
                        UI.print(UI.CYAN + "\tNome: " + UI.WHITE);
                        resposta_str_1 = scanner.next();

                        hospital.addDoctor(resposta_str_1);
                    }

                    break;
                
                case 3:
                    // Exibir feriados
                    UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** LISTA DE FERIADOS ***   \n\n");
                    UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + hospital.HolidayListToString());
                    break;
                
                case 4:
                    // Exibir lista de médico plantonistas
                    UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** LISTA DE MÉDICOS ***   \n\n");
                    UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + hospital.DoctorsListToString());
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
