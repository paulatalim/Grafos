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
                UI.println ("\n\t\t\t\t\t" + "*** GRAFOS ***" + "\n\n\n"
                            + "\t" + "O que deseja no seu grafo" + "\n\n"
                            + "\t" + "1 - Exibir em forma de lista de adjacencia" + "\n"
                            + "\t" + "2 - Exibir em forma de matriz de adjacencia" + "\n"
                            + "\t" + "3 - Adicionar vertices" + "\n"
                            + "\t" + "4 - Adicionar arestas" + "\n"
                            + "\t" + "0 - SAIR" + "\n");
                
                if (opcao_invalida) {
                    UI.println("\t" + "Opcao invalida. Tente novavemente." + "\n");
                }

                UI.print("\t" + "Insira o numero da operacao: ");
                opcao = scanner.nextInt();
                UI.limpar_console();

                opcao_invalida = true;

                // Valida a resposta do usuario
            } while (opcao < 0 || opcao > 4);

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
                    UI.println("Informe letras para indentificar os vertices\n"
                            + "Digite \"0\" para interromper a leitura\n");
                    
                    cont = 1;

                    do {
                        // Leitura do vertice
                        UI.print("vertice " + cont + ": ");
                        resposta = scanner.next().charAt(0);
                        UI.println("");
                        
                        if(resposta != '0') {
                            // Adiciona vertice na lista
                            grafoLista.inserir_vertice(resposta);

                            // Adiciona vertice na matriz
                        }

                        // Atualiza contador
                        cont ++;
                    } while (resposta != '0');
                    
                    break;
                case 4:
                    // Informa como deve ser feita a entrada de dados para o usuario
                    UI.println("Informe os vertices adjacente\n"
                                + "Exemplo: AB, CD, EF\n\n"
                                + "Digite \"0\" para interromper a leitura\n");

                    cont = 1;
                    String aresta;

                    do {
                        // Leitura do vertice
                        UI.print("aresta " + cont + ": ");
                        aresta = scanner.next();
                        UI.println("");
                        
                        if(!aresta.equals("0")) {
                            // Adiciona aresta na lista
                            grafoLista.inserir_aresta(aresta);
                    
                            // Adiociona aresta na matriz
                        }

                        // Atualiza contador
                        cont ++;
                    } while (!aresta.equals("0"));
                    
                    break;
            
                default:
                    break;
            }

            //Preparo para reiniciar programa
            if (opcao != 0) {
                UI.exibir_fim_tela();
            }

        } while (opcao != 0);

        scanner.close();

    }
}
