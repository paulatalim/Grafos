import java.util.Scanner;

import lista.ListaManage;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner (System.in);
        ListaManage grafoLista = new ListaManage();

        boolean opcao_invalida;
        int opcao;
        char resposta;
        int cont;

        // Valida a resposta do usuario
        do {
            // Pergunta se o grafo é ou não direcionado
            UI.print("O grafo eh direcionado? (s/n)\n\nresposta: ");
            resposta = scanner.next().charAt(0);

            UI.limpar_console();

            if (resposta == 's') {
                grafoLista.setIsDirecionado(true);;
                opcao_invalida = false;
            } else if (resposta == 'n') {
                grafoLista.setIsDirecionado(false);
                opcao_invalida = false;
            } else {
                opcao_invalida = true;
            }   
        } while (opcao_invalida);

        //Repete o programa
        do {

            //Reinicia a variavel
            opcao_invalida = false;

            //Validacao da entrada do usuario
            do {
                //Exibe o menu das opcoes
                UI.print ("\n\t\t\t\t\t" + "*** GRAFOS ***" + "\n\n\n"
                            + "\t" + "O que deseja no seu grafo" + "\n\n"
                            + "\t" + "REPRESENTAÇÕES" + "\n"
                            + "\t" + "1 - Exibir em forma de lista de adjacência" + "\n"
                            + "\t" + "2 - Exibir em forma de matriz de adjacência" + "\n\n"
                            + "\t" + "MANIPULAÇÃO" + "\n"
                            + "\t" + "3 - Adicionar vértices" + "\n"
                            + "\t" + "4 - Adicionar arestas" + "\n"
                            + "\t" + "5 - Remover aresta" + "\n\n"
                            + "\t" + "ANALISE" + "\n");

                if (grafoLista.isDirecionado()){
                    UI.print("\t" + "6 - Identificar sucessores e predecessores de um vértice\n");
                } else {
                    UI.print("\t" + "6 - Identificar vizinhaça de um vértice\n");
                }

                UI.println("\t" + "7 - Identificar grau de um vértice" + "\n"
                            + "\t" + "8 - Analisar e classificar grafo" + "\n\n"
                            + "\t" + "OUTROS" + "\n"
                            + "\t" + "9 - Criar novo grafo" + "\n"
                            + "\t" + "0 - SAIR" + "\n");
                
                if (opcao_invalida) {
                    UI.println("\t" + "Opcao inválida. Tente novavemente." + "\n");
                }

                UI.print("\t" + "Insira o número da operação: ");
                opcao = scanner.nextInt();
                UI.limpar_console();

                opcao_invalida = true;

                // Valida a resposta do usuario
            } while (opcao < 0 || opcao > 9);

            switch (opcao) {
                case 1:
                    // exibir o grafo em forma de lista
                    grafoLista.exibir_lista();
                    break;
                case 2:
                    // exibe o grafo em forma de matriz
                    break;
                case 3:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.println("Informe letras para indentificar os vértices\n"
                            + "Digite \"0\" para interromper a leitura\n");
                    
                    cont = 1;

                    do {
                        // Leitura do vértice
                        UI.print("Vértice " + cont + ": ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");
                        
                        if(resposta != '0') {
                            // Adiciona vértice na lista
                            grafoLista.inserir_vertice(resposta);

                            // TODO Adiciona vértice na matriz
                        }

                        // Atualiza contador
                        cont ++;
                    } while (resposta != '0');
                    
                    break;
                case 4:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.println("Informe os vértices adjacente\n"
                                + "Exemplo: AB, CD, EF\n\n"
                                + "Digite \"0\" para interromper a leitura\n");

                    cont = 1;
                    String aresta;

                    do {
                        // Leitura da aresta
                        UI.print("Aresta " + cont + ": ");
                        aresta = scanner.next();
                        UI.println("");
                        
                        if(!aresta.equals("0")) {
                            // Adiciona aresta na lista
                            grafoLista.inserir_aresta(aresta);
                    
                            // TODO Adiciona aresta na matriz
                        }

                        // Atualiza contador
                        cont ++;
                    } while (!aresta.equals("0"));
                    
                    break;

                case 5:
                    // Remove uma aresta

                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.println("Informe os vértices adjacentes da aresta que deseja retirar\n"
                                + "Exemplo: AB, CD, EF\n\n");
                    
                    opcao_invalida = true;

                    do {
                        // Leitura da aresta
                        UI.print("Aresta: ");
                        aresta = scanner.next();
                        UI.println("");
                        
                        
                        // Remove aresta na lista
                        opcao_invalida = !grafoLista.remover_aresta(aresta);
                
                        // TODO Adiociona aresta na matriz

                        // Valida o vértice
                        if(opcao_invalida) {
                            UI.println("\t" + "Um dos vértices não foi encontrado. Tente novavemente." + "\n");
                            UI.exibir_fim_tela();
                        } else {
                            UI.println("Aresta removida com sucesso !!!");
                        }
                    } while (opcao_invalida);
                    break;
                case 6:
                    // Entrada do vértice
                    opcao_invalida = true;

                    if(grafoLista.isDirecionado()) {
                        // Identifica sucessores e predecessores de um vértice
                        
                        // Entrada do vértice
                        UI.println("Insira o vértice que deseja saber seus predecessores e sucessores");
                        do {
                            UI.print("vértice: ");
                            resposta = scanner.next().charAt(0);
                            UI.println("");

                            // Valida o vértice
                            if(grafoLista.verificar_vertice(resposta)) {
                                opcao_invalida = false;
                            } else {
                                UI.println("\t" + "Opcao invalida. Tente novavemente." + "\n");
                                UI.exibir_fim_tela();
                            }

                        } while (opcao_invalida);

                        // Busca os predecessores e sucessores

                    } else {
                        // Identifica vizinhaça de um vértice

                        // Entrada do vértice
                        UI.println("Insira o vértice que deseja saber sua vizinhaça");
                        do {
                            UI.print("vértice: ");
                            resposta = scanner.next().charAt(0);
                            UI.println("");

                            // Valida o vértice
                            if(grafoLista.verificar_vertice(resposta)) {
                                opcao_invalida = false;
                            } else {
                                UI.println("\t" + "Opcao invalida. Tente novavemente." + "\n");
                                UI.exibir_fim_tela();
                            }
                        } while (opcao_invalida);

                        // Busca a vizinhaça do vértice
                    }

                    break;
                case 7:
                    //Identifica grau de um vértice

                    // Entrada do vértice
                    UI.println("Insira o vértice que deseja saber o grau");
                    do {
                        UI.print("Vértice: ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        // Valida o vértice
                        if(grafoLista.verificar_vertice(resposta)) {
                            opcao_invalida = false;
                        } else {
                            UI.println("\t" + "Vértice invalido. Tente novavemente." + "\n");
                            UI.exibir_fim_tela();
                        }
                    } while(opcao_invalida);

                    // Calcula o grau do vértice

                    break;
                case 8:
                    // Analisa e classifica o grafo 
                    // (dizer se o grafo eh simples, regular, completo ou bipartido)

                    break;
                case 9:
                    // Cria novo grafo

                    opcao_invalida = true;
                    grafoLista = new ListaManage();

                    do {
                        // Pergunta se o grafo é ou não direcionado
                        UI.print("\tO novo grafo é direcionado? (s/n)\n\n\tResposta: ");
                        resposta = scanner.next().charAt(0);
            
                        if (resposta == 's') {
                            grafoLista.setIsDirecionado(true);;
                            opcao_invalida = false;
                        } else if (resposta == 'n') {
                            grafoLista.setIsDirecionado(false);
                            opcao_invalida = false;
                        }  else {
                            UI.println("\t" + "Resposta inválida. Tente novavemente." + "\n");
                            UI.exibir_fim_tela();
                        }
                    } while (opcao_invalida);
                    break;
                case 0:
                    UI.println("Desligando sistema ...\n\n\n"
                                +"\t\t\t\t\tObrigado e volte sempre\n\n");
            }

            //Preparo para reiniciar programa
            if (opcao != 0) {
                UI.exibir_fim_tela();
            }

        } while (opcao != 0);

        scanner.close();

    }
}
