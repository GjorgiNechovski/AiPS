import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        HashMap<String,String[]> everything = new HashMap<>(2*n);

        for (int i=0; i<n; i++){
            String ip = bf.readLine();
            String[] routes = bf.readLine().split(",");

            everything.put(ip,routes);
        }

        int tries = Integer.parseInt(bf.readLine());

        for (int i=0; i<tries; i++){
            String ip = bf.readLine();
            String route = bf.readLine();

            String[] routes = everything.get(ip);
            boolean exists = false;

            if(routes!=null){
               // String[] parts = route.split("\\.");
               // route = parts[0] + parts[1] + parts[2];
                route = route.substring(0,route.lastIndexOf("."));

                for (int j=0; j<routes.length; j++) {
                    //parts = routes[j].split("\\.");
                    //routes[j] = parts[0] + parts[1] + parts[2];
                    String idk = routes[j].substring(0,routes[j].lastIndexOf("."));

                    if (route.equals(idk)) {
                        exists = true;
                        break;
                    }
                }
            }
            if(exists)
                System.out.println("postoi");
            else
                System.out.println("ne postoi");
        }
    }
}
