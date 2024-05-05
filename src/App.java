import java.util.Scanner;

import graph.representacao.lista.ListaManage;
import graph.representacao.matriz.MatrizManage;

/**
 * PONTÍFICIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS
 * Trabalho acadêmico de Algoritmos em Grafos
 * 
 * @author Gabriel Luís Pinto Cecconello
 * @author Paula Cristina Talim Gonçalves
 * @author Rafael Vicente Souza E Paula
 * 
 * DATA DE CRIAÇÃO: 27 de fev. de 2024
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ListaManage grafoLista = new ListaManage();
        MatrizManage grafoMatriz = new MatrizManage();

        boolean opcao_invalida;
        String aresta;
        int opcao;
        char resposta;
        char resposta1;
        int cont;
        boolean useAnaliseMatriz = true;;

        // Valida a resposta do usuario
        do {
            // Pergunta se o grafo é ou não direcionado
            UI.println(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t  *** " + "grafos".toUpperCase() + " ***  ");
            UI.print(UI.BLACK_BACKGROUND + UI.PURPLE + "\t\t\t\t\t  Seja bem vindo!\n\n\n");
            UI.print(UI.YELLOW + UI.BLACK_BACKGROUND + "\tVamos iniciar criando um grafo ...\n\n"
                            + "\tEsse grafo é direcionado? (s/n)\n\n");
            UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
            resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta == 's') {
                grafoLista.setIsDirecionado(true);
                grafoMatriz.setIsDirecionado(true);
                opcao_invalida = false;
            } else if (resposta == 'n') {
                grafoLista.setIsDirecionado(false);
                grafoMatriz.setIsDirecionado(false);
                opcao_invalida = false;
            } else {
                UI.print(UI.RED + "\n\tResposta inválida. Tente novamente.\n");
                opcao_invalida = true;
                UI.exibir_fim_tela();
            }
        } while (opcao_invalida);

        UI.limpar_console();

        // Configura se o grafo eh ponderado
        do {
            // Pergunta se o grafo é ou não direcionado
            UI.println(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t  *** " + "grafos".toUpperCase() + " ***  ");
            UI.print(UI.BLACK_BACKGROUND + UI.PURPLE + "\t\t\t\t\t  Seja bem vindo!\n\n\n");
            
            UI.println(UI.GREEN + "\tInformações do grafo:");

            if(grafoLista.isDirecionado()) {
                UI.print("\t - Grafo Direcionado");
            } else {
                UI.print("\t - Grafo Não Direcionado");
            }

            UI.println("\n\n");

            UI.print(UI.YELLOW + UI.BLACK_BACKGROUND + "\tO grafo é ponderado? (s/n)\n\n");
            UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
            resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta == 's') {
                grafoLista.setIsPonderado(true);
                grafoMatriz.setIsPonderado(true);
                opcao_invalida = false;
            } else if (resposta == 'n') {
                grafoLista.setIsPonderado(false);
                grafoMatriz.setIsPonderado(false);
                opcao_invalida = false;
            } else {
                UI.print(UI.RED + "\n\tResposta inválida. Tente novamente.\n");
                opcao_invalida = true;
                UI.exibir_fim_tela();
            }
        } while (opcao_invalida);

        UI.limpar_console();

        // Selecao do metodo de analise
        do {
            UI.println(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t  *** " + "grafos".toUpperCase() + " ***  ");
            UI.print(UI.BLACK_BACKGROUND + UI.PURPLE + "\t\t\t\t\t  Seja bem vindo!\n\n\n");

            UI.println(UI.GREEN + "\tInformações do grafo:");

            if (grafoLista.isDirecionado()) {
                UI.println("\t - Grafo Direcionado");
            } else {
                UI.println("\t - Grafo Não Direcionado");
            }

            if (grafoLista.isPonderado()) {
                UI.print("\t - Grafo ponderado");
            } else {
                UI.print("\t - Grafo não ponderado");
            }

            UI.println("\n\n");

            UI.print(UI.YELLOW + "\tPara a análise do grafo, você deseja utilizar os métodos de qual classe?\n\n");
            UI.print("\t1 - Matriz de Adjacência\n"
                    + "\t2 - Lista de Adjacência\n\n");

            UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
            resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta == '1') {
                useAnaliseMatriz = true;
                opcao_invalida = false;
            } else if (resposta == '2') {
                useAnaliseMatriz = false;
                opcao_invalida = false;
            } else {
                UI.print(UI.RED + "\n\tResposta inválida. Tente novamente.\n");
                opcao_invalida = true;
                UI.exibir_fim_tela();
            }
        } while (opcao_invalida);

        UI.exibir_fim_tela();

        // Repete o programa
        do {

            // Reinicia a variavel
            opcao_invalida = false;

            // Validacao da entrada do usuario
            do {
                //Exibe o menu das opcoes
                UI.print(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t" + "  *** GRAFOS ***  " + "\n\n\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "O que deseja fazer no grafo" + "\n\n");
                
                UI.print(UI.RED_BACKGROUND + UI.WHITE + "\t" + " REPRESENTAÇÕES   " + "\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "1 - Exibir em forma de lista de adjacência" + "\n"
                            + "\t" + "2 - Exibir em forma de matriz de adjacência" + "\n\n");
                
                UI.print(UI.YELLOW_BACKGROUND + UI.BLACK + "\t" + " MANIPULAÇÃO   " + "\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "3 - Adicionar vértices" + "\n"
                            + "\t" + "4 - Adicionar arestas" + "\n"
                            + "\t" + "5 - Remover aresta" + "\n\n");

                UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\t" + " ANÁLISE   ");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW);

                if (grafoLista.isDirecionado()) {
                    UI.print("\t" + "6 - Identificar sucessores e predecessores de um vértice\n");
                } else {
                    UI.print("\t" + "6 - Identificar vizinhaça de um vértice\n");
                }

                UI.print("\t" + "7 - Identificar grau de um vértice" + "\n");
                UI.print("\t" + "8 - Calcular distancia entre dois vértices" + "\n");
                UI.print("\t" + "9 - Analisar e classificar grafo" + "\n\n");
                
                UI.println(UI.BLUE_BACKGROUND + UI.BLACK + "\t" + " ALGORITMOS DE BUSCA E ÁRVORE   ");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW);
                UI.println("\t" + "10 - Realizar Busca em Largura");
                UI.print("\t" + "11 - Realizar Busca em Profundidade");
                
                if(grafoLista.isPonderado()) {
                    UI.print("\n\t" + "12 - Gerar Árvore Geradora Mínima");
                }

                UI.print(UI.PURPLE_BACKGROUND + UI.BLACK + "\n\n\t" + " OUTROS   " + "\n");
                UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "13 - Criar novo grafo" + "\n"
                                    + "\t" + "0 - SAIR" + "\n");
                
                if (opcao_invalida) {
                    UI.println(UI.RED + "\t" + "Opcao inválida. Tente novavemente." + "\n");
                }
                UI.print(UI.CYAN + "\t" + "Insira o número da operação: " + UI.WHITE);
                opcao = scanner.nextInt();
                UI.limpar_console();

                opcao_invalida = true;

                // Valida a resposta do usuario
            } while (opcao < 0 || opcao > 13 || (!grafoLista.isPonderado() && opcao == 12));

            switch (opcao) {
                case 1:
                    // exibir o grafo em forma de lista
                    UI.print(UI.RED_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** LISTA DE ADJACÊNCIA ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    if (!grafoLista.isGrafosEmpty()) {
                        // Exibe o grafo
                        grafoLista.exibir_lista();
                    } else {
                        // Caso o grafo estiver vazio
                        UI.print("\tO grafo está vazio, adicione vértices e arestas");
                    }
                    break;
                case 2:
                    // exibe o grafo em forma de matriz
                    UI.print(UI.RED_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** MATRIZ DE ADJACÊNCIA ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    if (!grafoMatriz.isGrafosEmpty()) {
                        // Exibe o grafo
                        grafoMatriz.exibir_matriz();
                    } else {
                        // Caso o grafo estiver vazio
                        UI.print("\tO grafo está vazio, adicione vértices e arestas");
                    }
                    break;
                case 3:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print(UI.YELLOW_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** ADICIONANDO VÉRTICES ***   \n\n\n");
                    
                    if(grafoLista.isGrafosEmpty()) {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInforme letras diferente para indentificar os vértices\n"
                                + UI.YELLOW + "\tDigite \"0\" para interromper a leitura\n");
                        
                        cont = 1;

                        do {
                            // Leitura do vértice
                            UI.print(UI.CYAN + "\tVértice " + cont + ": " + UI.WHITE);
                            resposta = scanner.next().charAt(0);
                            UI.println("");

                            Character.isDigit(resposta);

                            if (resposta != '0' && (Character.isDigit(resposta) || Character.isLetter(resposta))) {
                                // Adiciona vértice na lista
                                grafoLista.inserir_vertice(resposta);
                            }

                            grafoMatriz.inserir_vertice(resposta);

                            // Atualiza contador
                            cont++;
                        } while (resposta != '0');
                    } else {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tO grafo já possui vértices");
                    }

                    break;
                case 4:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print(UI.YELLOW_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** ADICIONANDO ARESTAS ***   \n\n\n");
                    
                    if(!grafoLista.isGrafosEmpty()) {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInforme os vértices adjacentes da aresta\n"
                                    + "\tExemplo: AB, CD, EF\n\n"
                                    + "\tDigite \"0\" para interromper a leitura\n");

                        cont = 1;
                        do {
                            // Leitura da aresta
                            UI.print(UI.CYAN + "\tAresta " + cont + ": " + UI.WHITE);
                            aresta = scanner.next();

                            // Atualiza contador
                            cont++;

                            // Valida a aresta
                            if (!aresta.equals("0") && aresta.length() > 1) {
                                if(grafoMatriz.isPonderado()) {
                                    int peso;

                                    if(grafoLista.isArestaeExist(aresta)) {
                                        UI.print(UI.CYAN +"\tNovo peso: " + UI.WHITE);
                                        peso = scanner.nextInt();

                                        grafoLista.atualizarPeso(aresta, peso);
                                        grafoMatriz.atualizarPeso(aresta, peso);
                                    
                                    } else {
                                        UI.print(UI.CYAN +"\tPeso: " + UI.WHITE);
                                        peso = scanner.nextInt();

                                        grafoLista.inserir_aresta(aresta, peso);
                                        grafoMatriz.inserir_aresta(aresta, peso);
                                    }

                                } else {
                                    // Adiciona aresta na lista e na matriz
                                    grafoLista.inserir_aresta(aresta);
                                    grafoMatriz.inserir_aresta(aresta);
                                }
                            }

                            UI.println("");

                        } while (!aresta.equals("0"));
                    } else {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo não possui vértices, adicione vértices para habilitar está função");
                    }
                    break;

                case 5:
                    // Remove uma aresta

                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print(UI.YELLOW_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** DELETAR ARESTA ***   \n\n\n");

                    if (!grafoLista.isGrafosEmpty() && grafoLista.temAresta()) {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInforme os vértices adjacentes da aresta que deseja retirar\n"
                                    + "\tExemplo: AB, CD, EF\n\n");
                        
                        opcao_invalida = true;

                        // Leitura da aresta
                        UI.print(UI.CYAN + "\tAresta: " + UI.WHITE);
                        aresta = scanner.next();
                        UI.println("");

                        // Remove aresta na lista e na matriz
                        if(aresta.length() > 1) {
                            opcao_invalida = !grafoLista.remover_aresta(aresta) || !grafoMatriz.remover_aresta(aresta);
                        }

                        // Valida o vértice
                        if(opcao_invalida) {
                            UI.println(UI.RED + "\t" + "Aresta inválida" + "\n");
                        } else {
                            UI.println(UI.YELLOW + "\tAresta removida com sucesso !!!");
                        }
                    } else if (!grafoLista.temAresta()) {
                        // Caso o grafo nao possua aresta
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo não tem aresta, adicione arestas para habilitar está função");
                    
                    } else {
                        // Caso o grafo esteja vazio
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo está vazio, adicione vértices e arestas para habilitar está função");
                    }
                    break;
                case 6:
                    opcao_invalida = true;

                    if (grafoLista.isDirecionado() && !grafoLista.isGrafosEmpty()) {
                        // Identifica sucessores e predecessores de um vértice

                        // Exibe o cabecalho da pagina
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** SUCESSORES E PREDECESSORES ***   \n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW +"\tInsira o vértice que deseja saber seus predecessores e sucessores\n");

                        // Entrada do vértice
                        UI.print(UI.CYAN + "\tVértice: " + UI.WHITE);
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        String separacao = "";
                        char[] array;

                        if(useAnaliseMatriz && grafoMatriz.isVerticeExist(resposta)) {
                            array = grafoMatriz.verifica_sucessores(resposta);

                            UI.print(UI.YELLOW + "\tOs sucessores do vértice " + resposta + " é composto por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < array.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(array[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]\n\n");

                            separacao = "";
                            array = grafoMatriz.verifica_predecessores(resposta);

                            UI.print(UI.YELLOW + "\tOs predecessores do vértice " + resposta + " é composto por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < array.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(array[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]");
                        
                        } else if (grafoLista.isVerticeExist(resposta)) {
                            array = grafoLista.encontrarSucessores(resposta);

                            UI.print(UI.YELLOW + "\tOs sucessores do vértice " + resposta + " é composto por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < array.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(array[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]\n\n");

                            separacao = "";
                            array = grafoLista.encontrarPredecessores(resposta);

                            UI.print(UI.YELLOW + "\tOs predecessores do vértice " + resposta + " é composto por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < array.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(array[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]");

                        } else {
                            UI.println(UI.RED + "\t" + "Vértice inválido." + "\n");
                        }
                    } else if (!grafoLista.isGrafosEmpty()) {
                        // Identifica vizinhaça de um vértice
                        // Exibe o cabecalho da pagina
                        UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** VIZINHAÇA DO VÉRTICE ***   \n\n\n"
                                 + UI.BLACK_BACKGROUND + UI.YELLOW +"\tInsira o vértice que deseja saber sua vizinhaça\n");

                        // Entrada do vértice
                        UI.print(UI.CYAN + "\tVértice: " + UI.WHITE);
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        String separacao;
                        char[] arrayVizinhos;

                        if(useAnaliseMatriz && grafoMatriz.isVerticeExist(resposta)) {
                            separacao = "";
                            arrayVizinhos = grafoMatriz.verfica_vizinhos(resposta);
                            UI.print(UI.YELLOW + "\tA vizinhança do vértice " + resposta + " é composta por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < arrayVizinhos.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(arrayVizinhos[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]");
                        
                        } else if (grafoLista.isVerticeExist(resposta)) {
                            separacao = "";
                            arrayVizinhos = grafoLista.vizinhaca(resposta);
                            UI.print(UI.YELLOW + "\tA vizinhança do vértice " + resposta + " é composta por:" + UI.CYAN + "\n\t[ ");
                            for (int i = 0; i < arrayVizinhos.length; i++) {
                                UI.print(separacao);
                                UI.print(String.valueOf(arrayVizinhos[i]));
                                separacao = ", ";
                            }
                            UI.print(" ]");

                        } else {
                            UI.println(UI.RED + "\t" + "Vértice inválido." + "\n");
                        }
                    } else {
                        if (grafoLista.isDirecionado() && grafoMatriz.isDirecionado()) {
                            UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** SUCESSORES E PREDECESSORES ***   \n\n");
                        } else {
                            UI.print(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t  *** VIZINHANÇA DO VÉRTICE ***   \n\n\n");
                        }
                        // Caso o grafo esteja vazio
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }

                    break;
                case 7:
                    // Identifica grau de um vértice

                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {

                        // Exibe o cabecalho da pagina
                        UI.print(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** GRAU DO VÉRTICE ***   \n\n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInsira o vértice que deseja saber o grau\n");
                        
                        // Entrada do vértice
                        UI.print(UI.CYAN + "\tVértice: " + UI.WHITE);
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        if(useAnaliseMatriz && grafoMatriz.isVerticeExist(resposta)) {
                            int[] graus = grafoMatriz.grau_vertice(resposta);

                            if(grafoMatriz.isDirecionado()) {
                                graus = grafoMatriz.grau_vertice(resposta);
                                UI.println(UI.YELLOW + "\t" + " - Grau de Saída: " + UI.CYAN + graus[0]);
                                UI.println(UI.YELLOW + "\t" + " - Grau de Entrada: " + UI.CYAN + graus[1]);
                            } else {
                                UI.println(UI.YELLOW + "\t" + " - Grau: " + UI.CYAN + graus[0] + "\n");
                            }

                        } else if(grafoLista.isVerticeExist(resposta)) {
                            if(grafoLista.isDirecionado()) {
                                UI.println(UI.YELLOW + "\t" + " - Grau de Saída: " + UI.CYAN + grafoLista.calcularGrauSaida(resposta));
                                UI.println(UI.YELLOW + "\t" + " - Grau de Entrada: " + UI.CYAN + grafoLista.calcularGrauEntrada(resposta));
                            } else {
                                UI.println(UI.YELLOW + "\t" + " - Grau: " + UI.CYAN + grafoLista.calcularGrau(resposta) + "\n");
                            }

                        } else {
                            UI.println(UI.RED + "\t" + "Vértice inválido." + "\n");
                        }
                    } else {
                        // Caso o grafo esteja vazio
                        UI.print(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** GRAU DO VÉRTICE ***   \n\n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }

                    break;
                   
                case 8:
                    // Distancia entre vertices

                    // Exibe cabecalho da pagina
                    UI.print(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** DISTÂNCIA ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);
                    
                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tInforme os vertices que desja calcular a distância\n\n");

                        // Entrada do primeiro vertice
                        UI.print(UI.CYAN + "\tVértice 1: " + UI.WHITE);
                        resposta = scanner.next().charAt(0);

                        // Entrada do segundo vertice
                        UI.print(UI.CYAN + "\n\tVértice 2: " + UI.WHITE);
                        resposta1 = scanner.next().charAt(0);
                        
                        // Calcula a distancia entre os vertices inseridos
                        if(useAnaliseMatriz) {
                            // TODO Calcular distancia entre dois vértices na matriz
                        } else {
                            // TODO Calcular distancia entre dois vértices na lista
                        }

                        // Exibe o resultado da distancia
                        UI.println(UI.YELLOW + "\n\n\t- Distância: " + UI.YELLOW);

                    } else {
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }
                    break;

                case 9:
                    // Analisa e classifica o grafo

                    // Exibe cabecalho da pagina
                    UI.print(UI.GREEN_BACKGROUND + UI.BLACK + "\n\t\t\t\t\t   *** CLASSIFICAÇÃO DO GRAFO ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {
                        if(useAnaliseMatriz) {
                            UI.println(UI.YELLOW + "\t" + " - Grafo Simples: " + UI.CYAN + (grafoMatriz.isGrafosSimples() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Regular: " + UI.CYAN + (grafoMatriz.isGrafosRegular() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Completo: " + UI.CYAN + (grafoMatriz.isGrafosCompleto() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Bipartido: " + UI.CYAN + (grafoMatriz.isGrafosBipartido() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Conexo: " + UI.CYAN + (grafoMatriz.isGrafosConexo() ? "Sim" : "Não"));
                        } else {
                            UI.println(UI.YELLOW + "\t" + " - Grafo Simples: " + UI.CYAN + (grafoLista.isSimples() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Regular: " + UI.CYAN + (grafoLista.isRegular() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Completo: " + UI.CYAN + (grafoLista.isCompleto() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Bipartido: " + UI.CYAN + (grafoLista.isBipartido() ? "Sim" : "Não"));
                            UI.println(UI.YELLOW + "\t" + " - Grafo Conexo: " + UI.CYAN + (grafoLista.isConexo() ? "Sim" : "Não"));
                        }
                    } else {
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }

                    break;

                case 10:
                    // Busca em Largura
                    UI.print(UI.BLUE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** BUSCA EM LARGURA ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);
                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {
                        if(useAnaliseMatriz) {
                            // TODO Busca em Largura em matriz
                            UI.print("\t" + String.valueOf(grafoMatriz.realizarBuscaLargura()));
                        } else {    
                            // TODO Busca em Largura em lista
                            UI.print("\t" + String.valueOf(grafoLista.realizarBuscaLargura()));
                        }
                    } else {
                        // Caso o grafo esteja vazio
                        UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }
                    break;
                
                case 11:
                    // Busca em Profundidade
                    UI.print(UI.BLUE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** BUSCA EM PROFUNDIDADE ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {
                        if(useAnaliseMatriz) {
                            // TODO Busca em Profundidade na matriz
                        } else {
                            // TODO Busca em Profundidade na lista
                        }

                    } else {
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }
                    break;
                
                case 12:
                    // Arvore geradora minima
                    UI.print(UI.BLUE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** ÁRVORE GERADORA MÍNIMA ***   \n\n\n" + UI.BLACK_BACKGROUND + UI.YELLOW);

                    if (!grafoLista.isGrafosEmpty() && !grafoMatriz.isGrafosEmpty()) {
                        if(useAnaliseMatriz) {
                            // TODO AGM em matriz
                        } else {
                            // TODO AGM em lista
                        }
                    } else {
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }
                    break;
                
                case 13:
                    // Cria novo grafo

                    opcao_invalida = true;

                    do {
                        // Pergunta se o grafo é ou não direcionado
                        UI.print(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** NOVO GRAFO ***   \n\n\n"
                                + UI.BLACK_BACKGROUND + UI.YELLOW + "\tO novo grafo é direcionado? (s/n)" + UI.CYAN +"\n\n\tResposta: " + UI.WHITE);
                        resposta = scanner.next().toLowerCase().charAt(0);
            
                        if (resposta == 's') {
                            grafoLista.setIsDirecionado(true);
                            grafoMatriz.setIsDirecionado(true);
                            opcao_invalida = false;
                        } else if (resposta == 'n') {
                            grafoLista.setIsDirecionado(false);
                            grafoMatriz.setIsDirecionado(false);
                            opcao_invalida = false;
                        }  else {
                            UI.println(UI.RED + "\t" + "Resposta inválida. Tente novavemente." + "\n");
                            UI.exibir_fim_tela();
                        }
                    } while (opcao_invalida);

                    UI.limpar_console();

                    // Configura se o grafo eh ponderado
                    do {
                        // Pergunta se o grafo é ou não direcionado
                        UI.print(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t   *** NOVO GRAFO ***   \n\n\n");
                        UI.println(UI.BLACK_BACKGROUND + UI.GREEN + "\tInformações do grafo:");

                        if(grafoLista.isDirecionado()) {
                            UI.print("\t - Grafo Direcionado");
                        } else {
                            UI.print("\t - Grafo Não Direcionado");
                        }

                        UI.println("\n\n");

                        UI.print(UI.YELLOW + UI.BLACK_BACKGROUND + "\tO grafo é ponderado? (s/n)\n\n");
                        UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
                        resposta = scanner.next().toLowerCase().charAt(0);

                        if (resposta == 's') {
                            // Configura o grafo ponderado
                            grafoLista.setIsPonderado(true);
                            grafoMatriz.setIsPonderado(true);
                            opcao_invalida = false;
                        } else if (resposta == 'n') {
                            // Configura o grafo nao ponderado
                            grafoLista.setIsPonderado(false);
                            grafoMatriz.setIsPonderado(true);
                            opcao_invalida = false;
                        } else {
                            // Exibe mensagem de erro
                            UI.print(UI.RED + "\n\tResposta inválida. Tente novamente.\n");
                            opcao_invalida = true;
                            UI.exibir_fim_tela();
                        }
                    } while (opcao_invalida);

                    UI.limpar_console();

                    do {
                        UI.println(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t  *** NOVO GRAFO ***  ");
                        UI.println(UI.BLACK_BACKGROUND + UI.GREEN + "\tInformações do grafo:");
            
                        if(grafoLista.isDirecionado()) {
                            UI.print("\t - Grafo Direcionado");
                        } else {
                            UI.print("\t - Grafo Não Direcionado");
                        }

                        if (grafoLista.isPonderado()) {
                            UI.print("\t - Grafo ponderado");
                        } else {
                            UI.print("\t - Grafo não ponderado");
                        }
            
                        UI.println("\n\n");
            
                        UI.print(UI.YELLOW + "\tPara a análise do grafo, você deseja utilizar os métodos de qual classe?\n\n");
                        UI.print("\t1 - Matriz de Adjacência\n"
                                + "\t2 - Lista de Adjacência\n\n");
            
                        UI.print(UI.CYAN + "\tResposta: " + UI.WHITE);
                        resposta = scanner.next().toLowerCase().charAt(0);
            
                        if (resposta == '1') {
                            useAnaliseMatriz = true;
                            opcao_invalida = false;
                        } else if (resposta == '2') {
                            useAnaliseMatriz = false;
                            opcao_invalida = false;
                        } else {
                            UI.print(UI.RED + "\n\tResposta inválida. Tente novamente.\n");
                            opcao_invalida = true;
                            UI.exibir_fim_tela();
                        }
                    } while (opcao_invalida);
                    break;
                case 0:
                    UI.println(UI.YELLOW + "Desligando sistema ...\n\n\n"
                               + UI.PURPLE_BACKGROUND + UI.WHITE +"\t\t\t\t\t   Obrigado e volte sempre   \n\n" + UI.RESET);
            }

            // Preparo para reiniciar programa
            if (opcao != 0) {
                UI.exibir_fim_tela();
            }

        } while (opcao != 0);

        scanner.close();
    }
}
