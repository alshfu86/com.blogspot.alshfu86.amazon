import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


    public class Menu {
        int var=0;

        public void menu(){
            boolean a=false;
            while (a!=true){
                a=firstStep();
            }


        }


        public int getVar() {
            return var;
        }

        public void setVar(int var) {
            this.var = var;
        }

        public boolean firstStep() {
            boolean respond = false;
            System.out.println("Press 1 to create AISN-list from url");
            System.out.println("Press 2 to create product-list from ASIN-list");
            //System.out.println("Press 3 for debug");
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();
                if (s.equals("1") || s.equals("2")) {
                    setVar(Integer.parseInt(s));
                    respond = true;
                } else { System.out.println("You Can Chose Only 1 or 2");}

            } catch (IOException e) {
                e.printStackTrace();
            }
            return respond;

        }

    }
