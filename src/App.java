import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

class App {

    public static int menu() {

        int op;

        do {
            Scanner teclado = new Scanner(System.in);

            System.out.println("---------------------------------------");
            System.out.println("Informe a tarefa: ");
            System.out.println("\t 1 - Caminho mínimo");
            System.out.println("\t 2 - Labirinto");
            System.out.println("\t 3 - Sair");

            op = teclado.nextInt();

            if(op < 1 || op > 3)
                System.out.println("Opção inválida!");
        } while(op < 1 || op > 3);

        return op;
    }

    public static int menuCaminhoMinimo() {

        int op;

        do {

            Scanner teclado = new Scanner(System.in);

            System.out.println("---------------------------------------");
            System.out.println("Arquivo de entrada: ");
            System.out.println("\t 1 - facebook_combined");
            System.out.println("\t 2 - rg300_4730");
            System.out.println("\t 3 - rome99c");
            System.out.println("\t 4 - toy");
            System.out.println("\t 5 - USA-road-dt.DC");
            System.out.println("\t 6 - USA-road-dt.NY");

            op = teclado.nextInt();

            if(op < 1 || op > 6)
                System.out.println("Opção inválida!");
        } while(op < 1 || op > 6);

        return op;
    }

    public static int menuAlgoritmosCaminhoMinimo() {

        int op;

        do {

            Scanner teclado = new Scanner(System.in);

            System.out.println("---------------------------------------");
            System.out.println("Arquivo de entrada: ");
            System.out.println("\t 1 - Dijkstra");
            System.out.println("\t 2 - Bellman-Ford");
            System.out.println("\t 3 - Bellman-Ford Melhorado");
            System.out.println("\t 4 - Floyd-Warshall");

            op = teclado.nextInt();

            if(op < 1 || op > 4)
                System.out.println("Opção inválida!");
        } while(op < 1 || op > 6);

        return op;
    }

    public static int[] menuNosOrigemEDestino() {

        int[] origemDestino = new int[2];
        Scanner teclado = new Scanner(System.in);

        System.out.println("---------------------------------------");
        System.out.println("Digite o no de origem do caminho: ");
        origemDestino[0] = teclado.nextInt();
        System.out.println("Digite o no de destino do caminho: ");
        origemDestino[1] = teclado.nextInt();

        return origemDestino;
    }

    public static void execDijkstra(String nomeArquivo) throws IOException {
        GraphList g = new GraphList("files/cm/" + nomeArquivo);
        int[] origemDestino = menuNosOrigemEDestino();

        System.out.println("Arquivo: <" + nomeArquivo + ">");
        System.out.println("Origem: <" + origemDestino[0] + ">");
        System.out.println("Destino: <" + origemDestino[1] + ">");
        System.out.println("Processando...");

        Instant startTime = Instant.now();

        Dijkstra dijkstra = new Dijkstra(g.getCountNodes());
        dijkstra.dijkstra(g.getAdjList(), origemDestino[0]);

        String caminho = calculaCaminho(origemDestino[0], origemDestino[1], dijkstra.getPred());

        Instant stopTime = Instant.now();

        System.out.println("Caminho: " + caminho);
        System.out.println("Custo: " + dijkstra.getDist()[origemDestino[1]]);
        System.out.println("Tempo: " + Duration.between(stopTime, startTime).toString());
    }

    private static void execCaminhoMinimo() throws IOException {
        int op = menuCaminhoMinimo();
        String nomeArquivo = getNomeArquivo(op);
        int algoritmo = menuAlgoritmosCaminhoMinimo();

        switch (algoritmo) {
            case 1 -> execDijkstra(nomeArquivo);
            //case 2 -> execBellmanFord();
            //case 3 -> execBellmanFordMelhorado();
            //case 4 -> execFloydWarshall();
        }

    }

    private static String getNomeArquivo(int op) {
        switch (op) {
            case 1:
                return "facebook_combined.txt";
            case 2:
                return "rg300_4730.txt";
            case 3:
                return "rome99c.txt";
            case 4:
                return "toy.txt";
            case 5:
                return "USA-road-dt.DC.txt";
            case 6:
                return "USA-road-dt.NY.txt";
            default:
                return "";
        }
    }

    private static String calculaCaminho(int origem, int destino, int[] pred) {

        String caminho = "";
        int cont;
        for ( cont = destino; cont != origem; cont = pred[cont]) {
            caminho += cont;
            caminho += ",";
        }
        caminho += cont;

        String[] reverte = caminho.split(",");
        String retorno = "[";

        for (int i = reverte.length - 1; i > 0 ; i--) {
            retorno += reverte[i];
            retorno += ",";
        }
        retorno += reverte[0] + "]";

        return retorno;
    }


    public static void main(String[] args) throws IOException {
        GraphList g = new GraphList("files/cm/toy.txt");
//        GraphMatrix h = new GraphMatrix("files/cm/toy.txt");
        System.out.println(g);

        int op;
        do {

            op = menu();

            switch (op) {
                case 1:
                    execCaminhoMinimo();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
            }

        }while(op != 3);

    }
}