public class Programme {
    public static void main(String[] args) {
        Date date1 = new Date(2021, 21);
        Date date4 = new Date(2021, 225);
        date1.afficher();
        date4.afficher();
        System.out.println(date4.difference(date1));
    }
    public static void showTable(int[] tab){
        for(int i : tab) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
    }
}