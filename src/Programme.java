public class Programme {
    public static void main(String[] args) {
       //Date date1 = new Date();
       //Date date2 = new Date(2024, 12, 11);
       //Date date3 = new Date(2004, 4, 5);
        Date date4 = new Date(222, 2023);
        //date1.setDay();
        //date1.afficher();
        //date2.afficher();
        date4.afficher();


    }
    public static void showTable(int[] tab){
        for(int i : tab) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
    }
}