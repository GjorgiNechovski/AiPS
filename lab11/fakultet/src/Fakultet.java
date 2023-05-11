import java.util.*;
//import java.util.Random;
class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    float weight;

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }





}
class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }


    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }


    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+=neighbors.get(i).info+" ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }



}
class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    /************************TOPOLOGICAL SORT*******************************************************************/

    void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
        if(!visited[i]){
            visited[i] = true;
            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
            System.out.println("dfsVisit: "+i+" Stack="+s);
            while(it.hasNext()){
                dfsVisit(s, it.next().getIndex(), visited);
            }
            s.push(i);
            System.out.println("--dfsVisit: "+i+" Stack="+s);
        }
    }

    void topological_sort_dfs(){
        boolean visited[] = new boolean[num_nodes];
        for(int i=0;i<num_nodes;i++){
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();

        for(int i=0;i<num_nodes;i++){
            dfsVisit(s,i,visited);
        }
        System.out.println("----Stack="+s);
        while(!s.isEmpty()){
            System.out.println(adjList[s.pop()]);
        }
    }

    /***********************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }


    int getIndex(E value){
        return Arrays.stream(adjList)
                .filter(x->x.getInfo().equals(value))
                .mapToInt(GraphNode::getIndex)
                .sum();
    }


}
class Edge{
    private final int fromVertex;
    private final int toVertex;
    private final float weight;
    public Edge(int from, int to, float weight){
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom(){
        return this.fromVertex;
    }
    public int getTo(){
        return this.toVertex;
    }
    public float getWeight(){
        return this.weight;
    }
}

public class Fakultet{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        String[] subjects = new String[n];
        Map<String, Integer> subjectsWithIndexes = new HashMap<>();

        for (int i=0; i<n; i++){
            String subject = in.nextLine();
            subjects[i] = subject;
            subjectsWithIndexes.put(subject, i);
        }

        Graph<String> graph = new Graph<>(n,subjects);

        int preuduslovi = Integer.parseInt(in.nextLine());

        for (int i=0; i<preuduslovi; i++) {

            String[] parts = in.nextLine().split("\\s+");

            String glavenPredmet = parts[0];

            String preduslovenPredmet = parts[parts.length-1];
            graph.addEdge(subjectsWithIndexes.get(preduslovenPredmet), subjectsWithIndexes.get(glavenPredmet));

        }

        String baranPredmet = in.nextLine();
        System.out.println(
                graph.adjList[subjectsWithIndexes
                        .get(baranPredmet)]
                        .getNeighbors()
                        .get(0)
                        .getInfo()
        );
    }

}
