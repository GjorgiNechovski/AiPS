import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

class Graph {

    int num_nodes; // broj na jazli
    int[][] adjMat;  // matrica na sosednost

    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }

    public Graph(int num_nodes, int[][] adjMat) {
        this.num_nodes = num_nodes;
        this.adjMat = adjMat;
    }


    int adjacent(int x,int y)
    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }

    void addEdge(int x,int y)
    {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }

    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }



    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder("  ");
        for(int i=0;i<num_nodes;i++)
            ret.append(i).append(" ");
        ret.append("\n");
        for(int i=0;i<num_nodes;i++){
            ret.append(i).append(" ");
            for(int j=0;j<num_nodes;j++)
                ret.append(adjMat[i][j]).append(" ");
            ret.append("\n");
        }
        return ret.toString();
    }

}
public class Maze {

    //Kje se koristi nenasocen graf implementiran so martica na sosednost
    //za pretstavuvanje na lavirintot
    //Teminjata vo grafot se samo indeksi
    //Iminjata na teminjata se preveduvaat vo broevi preku hash tabela
    //T.e. na pr. 1,1 kje se prevede vo 1 (1,1 kje se zacuva kako string)

    Graph g;
    int start_node; //indeks temeto koe e vlez
    int end_node; //indeks na temeto koe e izlez

    Hashtable<String,Integer> h;
    HashMap<Integer,String> reversed = new HashMap<>();
    public Maze() {
        h = new Hashtable<String,Integer>();
    }

    void generateGraph(int rows, int columns, String[] in){

        int num_nodes = 0;

        String key;

        for(int i=1; i<rows; i++){
            for(int j=1; j<columns; j++){
                if(in[i].charAt(j)!='#'){

                    key = i+","+j;
                    h.put(key, num_nodes);
                    reversed.put(num_nodes,key);
                    if(in[i].charAt(j)=='S')
                        start_node = num_nodes;
                    if(in[i].charAt(j)=='E')
                        end_node = num_nodes;
                    num_nodes++;
                }
            }
        }

        g = new Graph(num_nodes);

        int x;
        int y;
        //generiranje na matrica na sosednost
        for(int i=1; i<rows; i++){
            for(int j=1; j<columns; j++){
                if(in[i].charAt(j)!='#'){
                    //dali ima teme pred nego
                    if(in[i].charAt(j-1)!='#'){
                        x = h.get(i+","+j);
                        y = h.get(i+","+(j-1));
                        g.addEdge(x, y);
                    }
                    //dali ima teme posle nego
                    if(in[i].charAt(j+1)!='#'){
                        x = h.get(i+","+j);
                        y = h.get(i+","+(j+1));
                        g.addEdge(x, y);
                    }
                    //dali ima teme nad nego
                    if(in[i-1].charAt(j)!='#'){
                        x = h.get(i+","+j);
                        y = h.get((i-1)+","+j);
                        g.addEdge(x, y);
                    }
                    //dali ima teme pod nego
                    if(in[i+1].charAt(j)!='#'){
                        x = h.get(i+","+j);
                        y = h.get((i+1)+","+j);
                        g.addEdge(x, y);
                    }
                }
            }
        }
    }

    void findPath(){
        boolean visited[] = new boolean[g.getNum_nodes()];
        for (int i = 0; i < g.getNum_nodes(); i++)
            visited[i] = false;
        visited[start_node] = true;
        Stack<Integer> s = new Stack<Integer>();
        s.push(start_node);

        int pom;
        while (!s.isEmpty() && s.peek()!=end_node) {
            pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < g.getNum_nodes(); i++) {
                if(g.adjacent(pom,i)==1){
                    pom1 = i;
                    if(!visited[i])
                        break;
                }
            }
            if(!visited[pom1]){
                visited[pom1] = true;
                //System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(pom1);
            }
            else
                s.pop();
        }
        if(s.isEmpty())
            System.out.println("Nema reshenie");
        else{
            Stack<Integer> os = new Stack<Integer>();
            while(!s.isEmpty())
                os.push(s.pop());
            while(!os.isEmpty())
                System.out.println(reversed.get(os.pop()));
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] sizes = bf.readLine().split(",");
        int rows = Integer.parseInt(sizes[0]);
        int columns = Integer.parseInt(sizes[1]);

        String[] temp = new String[rows*columns];

        for (int i=0; i<rows; i++){
            temp[i] = bf.readLine();
        }

        Maze maze = new Maze();
        maze.generateGraph(rows,columns,temp);

        maze.findPath();

    }

}
