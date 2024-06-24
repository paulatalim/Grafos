import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        String resposta_str_2;
        
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
                // if(hospital.getC() == -1) UI.println("\t" + "2 - Cadastrar disponibilidade de dias dos médicos plantonistas");
                UI.println("\t" + "2 - Cadastrar médico");
                if(hospital.HolidayListToString() != null) UI.println("\n\t" + "3 - Exibir feriados");
                if(hospital.DoctorsListToString() != null) UI.println("\t" + "4 - Exibir médicos cadastrados");
                if(hospital.HolidayListToString() != null && hospital.DoctorsListToString() != null) {
                    UI.println("\n\t" + "5 - Inserir disponibilidade para plantão");
                    UI.println("\t" + "6 - Gerar Relatório de Plantão");
                }
                UI.println("\n\t" + "0 - SAIR" + "\n");

                if (opcao_invalida) {
                    UI.println(UI.RED + "\t" + "Opção inválida. Tente novavemente." + "\n");
                }
                UI.print(UI.CYAN + "\t" + "Insira o número da operação: " + UI.WHITE);
                opcao = scanner.nextInt();
                UI.limpar_console();

                if((hospital.DoctorsListToString() == null && (opcao == 4)) 
                || (hospital.HolidayListToString() == null && (opcao == 3 || opcao >= 6))
                || opcao < 0 || opcao > 6) {
                    opcao_invalida = true;
                }

                // Valida a resposta do usuario
            } while (opcao_invalida);

            switch (opcao) {
                case 1:
                    // Cadastrar feriado
                    do {
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** FERIADOS ***   \n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o nome e a duração, em dias, do feriado\n");
                        UI.println("\tDigite \"0\" para interromper a leitura\n");
    
                        resposta_int = 0;

                        // Exibe medicos cadastrados
                        if(hospital.HolidayListToString() != null) {
                            UI.println(UI.WHITE + "\tFeriados:\n");
                            UI.println(hospital.HolidayListToString() + "\n");
                        }

                        // Entrada do nome do feriado
                        UI.print(UI.CYAN + "\tNome: " + UI.WHITE);
                        scanner.nextLine();
                        resposta_str_1 = scanner.nextLine();
                        
                        if(!resposta_str_1.equals("0")) {
                            // Entrada da duracao do feriado
                            UI.print(UI.CYAN + "\tDuração: " + UI.WHITE);
                            resposta_int = scanner.nextInt();
                            UI.println("");
                        
                            // Adiciona registro de feriado
                            if(hospital.addHoliday(resposta_str_1, resposta_int)) {
                                UI.limpar_console();
                            } else {   
                                UI.println(UI.RED + "\tNome do feriado inválido. Tente novamente");
                                UI.exibir_fim_tela();
                            }
                        }
                    } while (!resposta_str_1.equals("0"));
                    break;
                
                case 2:
                    // Cadastrar médico
                    do {
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** CADASTRO DE MÉDICOS ***   \n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o nome dos médicos e sua disponibilidade, em dias, para plantão, a seguir\n");
                        UI.println("\tDigite \"0\" para interromper a leitura\n");

                        // Exibe medicos cadastrados
                        if(hospital.DoctorsListToString() != null) {
                            UI.println(UI.WHITE + "\tMédicos cadastrados:\n");
                            UI.println(hospital.DoctorsListToString() + "\n");
                        }

                        // Entrada do nome do medico
                        UI.print(UI.CYAN + "\tNome: " + UI.WHITE);
                        scanner.nextLine();
                        resposta_str_1 = scanner.nextLine();

                        if(!resposta_str_1.equals("0")) {
                            // Entrada da disponibilidade do medico
                            UI.print(UI.CYAN + "\tDisponibilidade: " + UI.WHITE);
                            resposta_int = scanner.nextInt();

                            // Adiciona o registro
                            if(hospital.addDoctor(resposta_str_1, resposta_int)) {
                                UI.limpar_console();
                            } else {   
                                UI.println(UI.RED + "\n\tNome inválido. Tente novamente");
                                UI.exibir_fim_tela();
                            }
                        }
                    } while (!resposta_str_1.equals("0"));
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
                    // disponibilidade para plantão
                    UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** DISPONIBILIDADE DE PLANTÃO ***   \n\n");

                    UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o feriado e o médico que irá fazer plantão\n");

                    // Entrada do nome do feriado
                    UI.print(UI.CYAN + "\tFeriado: " + UI.WHITE);
                    scanner.nextLine();
                    resposta_str_1 = scanner.nextLine();

                    // Entrada do nome do medico
                    UI.print(UI.CYAN + "\tMédico: " + UI.WHITE);
                    resposta_str_2 = scanner.nextLine();

                    // Adicao da disponibilidade
                    if(!(hospital.addDoctorAvailability(resposta_str_2, resposta_str_1)))
                        UI.println(UI.RED + "\n\tCampo inválido. Tente novamente.");
                    
                    break;
                
                case 6:
                    UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** RELATÓRIO DE PLANTÃO ***   \n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    resposta_str_1 = hospital.verificarAtribuicaoMedicos();

                    if(resposta_str_1 != null) {
                        UI.println("\tA seguir está a escalação de médicos para cada feriado:");
                        UI.print(resposta_str_1);
                    
                    } else UI.println("\tNão é possível cobrir todos os feriados com os médicos disponíveis.");

                    // if(medicosEmAcordo != null) {
                    //     UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tA dada combinação de médicos satisfaz as restrições: " + medicosEmAcordo.toString() + "\n");
                    // }
                    // else {
                    //     UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tA dada combinação de médicos não satisfaz as restrições.\n");
                    // }
                    
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
