import java.util.Scanner;

import lista.ListaManage;
import matriz.MatrizManage;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner (System.in);
        ListaManage grafoLista = new ListaManage();
        MatrizManage grafoMatriz = new MatrizManage();

        boolean opcao_invalida;
        String aresta;
        int opcao;
        char resposta;
        int cont;

        // Valida a resposta do usuario
        do {
            // Pergunta se o grafo é ou não direcionado
            UI.print("\n\t\t\t\t\t*** GRAFOS ***\n"
                        + "\t\t\t\t\tSeja bem vindo!\n\n\n"
                        + "\tVamos iniciar criando um grafo ...\n\n"
                        + "\tEsse grafo é direcionado? (s/n)\n\n"
                        + "\tResposta: ");
            resposta = scanner.next().charAt(0);

            UI.limpar_console();

            if (resposta == 's') {
                grafoLista.setIsDirecionado(true);
                grafoMatriz.setIsDirecionado(true);
                opcao_invalida = false;
            } else if (resposta == 'n') {
                grafoLista.setIsDirecionado(false);
                grafoMatriz.setIsDirecionado(false);
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
                    UI.print("\n\t\t\t\t\t*** LISTA DE ADJACÊNCIA ***\n\n\n");

                    if(!grafoLista.isGrafosEmpty()) {
                        // Exibe o grafo
                        grafoLista.exibir_lista();
                    } else {
                        // Caso o grafo estiver vazio
                        UI.print("\tO grafo está vazio, adicione vértices e arestas");
                    }
                    break;
                case 2:
                    // exibe o grafo em forma de matriz
                    UI.print("\n\t\t\t\t\t*** MATRIZ DE ADJACÊNCIA ***\n\n\n");

                    if(!grafoMatriz.isGrafosEmpty()) {
                        // Exibe o grafo
                        grafoMatriz.exibir_matriz();
                    } else {
                        // Caso o grafo estiver vazio
                        UI.print("\tO grafo está vazio, adicione vértices e arestas");
                    }
                    break;
                case 3:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print("\n\t\t\t\t\t*** ADICIONANDO VÉRTICES ***\n\n\n");
                    
                    if(grafoLista.isGrafosEmpty()) {
                        UI.println("\tInforme letras diferente para indentificar os vértices\n"
                                + "\tDigite \"0\" para interromper a leitura\n");
                        
                        cont = 1;

                        do {
                            // Leitura do vértice
                            UI.print("\tVértice " + cont + ": ");
                            resposta = scanner.next().charAt(0);
                            UI.println("");
                            
                            if(resposta != '0') {
                                // Adiciona vértice na lista
                                grafoLista.inserir_vertice(resposta);
                            
                            }
                            
                            grafoMatriz.inserir_vertice(resposta);

                            // Atualiza contador
                            cont ++;
                        } while (resposta != '0');
                    } else {
                        UI.println("\tO grafo já possui vértices");
                    }
                    
                    break;
                case 4:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print("\n\t\t\t\t\t*** ADICIONANDO ARESTAS ***\n\n\n");
                    
                    if(!grafoLista.isGrafosEmpty()) {
                        UI.println("\tInforme os vértices adjacentes da aresta\n"
                                    + "\tExemplo: AB, CD, EF\n\n"
                                    + "\tDigite \"0\" para interromper a leitura\n");

                        cont = 1;
                        do {
                            // Leitura da aresta
                            UI.print("\tAresta " + cont + ": ");
                            aresta = scanner.next();
                            UI.println("");
                        
                            if(!aresta.equals("0")) {
                                // Adiciona aresta na lista e na matriz
                                grafoLista.inserir_aresta(aresta);
                                grafoMatriz.inserir_aresta(aresta);
                            }

                            // Atualiza contador
                            cont ++;
                        } while (!aresta.equals("0"));
                    } else {
                        UI.println("\tSeu grafo não possui vértices, adicione vértices para habilitar está função");
                    }
                    break;

                case 5:
                    // Remove uma aresta

                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.print("\n\t\t\t\t\t*** DELETAR ARESTA ***\n\n\n");

                    if (!grafoLista.isGrafosEmpty() && grafoLista.temAresta()) {

                        UI.println("\tInforme os vértices adjacentes da aresta que deseja retirar\n"
                                    + "\tExemplo: AB, CD, EF\n\n");
                        
                        opcao_invalida = true;
                        
                        // Leitura da aresta
                        UI.print("\tAresta: ");
                        aresta = scanner.next();
                        UI.println("");
                        
                        // Remove aresta na lista e na matriz
                        opcao_invalida = !grafoLista.remover_aresta(aresta) || !grafoMatriz.remover_aresta(aresta);

                        // Valida o vértice
                        if(opcao_invalida) {
                            UI.println("\t" + "Um dos vértices não foi encontrado. Tente novavemente." + "\n");
                            UI.exibir_fim_tela();
                        } else {
                            UI.println("\tAresta removida com sucesso !!!");
                        }
                    } else if(!grafoLista.temAresta()) {
                        // Caso o grafo nao possua aresta
                        UI.println("\tSeu grafo não tem aresta, adicione arestas para habilitar está função");
                    
                    } else {
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices e arestas para habilitar está função");
                    }
                    break;
                case 6:
                    opcao_invalida = true;
                    
                    if(grafoLista.isDirecionado() && !grafoLista.isGrafosEmpty()) {
                        // Identifica sucessores e predecessores de um vértice
                        
                        // Exibe o cabecalho da pagina
                        UI.println("\n\t\t\t\t\t*** SUCESORES E PREDECESSORES ***\n\n");
                        UI.println("\tInsira o vértice que deseja saber seus predecessores e sucessores\n");

                        // Entrada do vértice
                        UI.print("\tVértice: ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        // Valida o vértice
                        if(grafoLista.isVerticeExist(resposta)) {

                            // TODO Busca os predecessores e sucessores

                        } else {
                            UI.println("\t" + "Vértice inválido." + "\n");
                        }
                    } else if(!grafoLista.isGrafosEmpty()) {
                        // Identifica vizinhaça de um vértice
                        // Exibe o cabecalho da pagina
                        UI.println("\n\t\t\t\t\t*** VIZINHAÇA DO VÉRTICE ***\n\n\n"
                                +"\tInsira o vértice que deseja saber sua vizinhaça\n");

                        // Entrada do vértice
                        UI.print("\tVértice: ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        // Valida o vértice
                        if(grafoLista.isVerticeExist(resposta)) {
                        
                            // TODO Busca a vizinhaça do vértice
                        
                        } else {
                            UI.println("\t" + "Vértice inválido." + "\n");
                        }
                    } else {
                        if (grafoLista.isDirecionado()) {
                            UI.println("\n\t\t\t\t\t*** SUCESORES E PREDECESSORES ***\n\n");
                        } else {
                            UI.print("\n\t\t\t\t\t*** VIZINHAÇA DO VÉRTICE ***\n\n\n");
                        }
                        // Caso o grafo esteja vazio
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }

                    break;
                case 7:
                    //Identifica grau de um vértice

                    if(!grafoLista.isGrafosEmpty()) {
                        
                        // Exibe o cabecalho da pagina
                        UI.print("\n\t\t\t\t\t*** GRAU DO VÉRTICE ***\n\n\n");
                        UI.println("\tInsira o vértice que deseja saber o grau");
                        
                        // Entrada do vértice
                        UI.print("\tVértice: ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");

                        // Valida o vértice
                        if(grafoLista.isVerticeExist(resposta)) {

                            // TODO Calcula o grau do vértice

                        } else {
                            UI.println("\t" + "Vértice inválido." + "\n");
                        }
                    } else {
                        // Caso o grafo esteja vazio
                        UI.print("\n\t\t\t\t\t*** GRAU DO VÉRTICE ***\n\n\n");
                        UI.println("\tSeu grafo está vazio, adicione vértices para habilitar está função");
                    }

                    break;
                case 8:
                    // Analisa e classifica o grafo

                    // Exibe cabecalho da pagina
                    UI.print("\n\t\t\t\t\t*** CLASSIFICAÇÃO DO GRAFO ***\n\n\n");

                    // (dizer se o grafo eh simples, regular, completo ou bipartido)
                    if(!grafoLista.isGrafosEmpty()) {
                        if(grafoLista.isBipartido()) {
                            UI.print("e bipartido");
                        } else {
                            UI.print("nao eh bipartido");
                        }
                    }   
                    break;
                case 9:
                    // Cria novo grafo

                    opcao_invalida = true;
                    grafoLista = new ListaManage();

                    do {
                        // Pergunta se o grafo é ou não direcionado
                        UI.print("\n\t\t\t\t\t*** NOVO GRAFO ***\n\n\n"
                                    + "\tO novo grafo é direcionado? (s/n)\n\n\tResposta: ");
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
