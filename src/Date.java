import java.util.Scanner;

import static java.lang.Math.abs;

public class Date {
    private int jour=-1;
    private int mois=-1;
    private An an;
    public Date(){
        this.jour=-1;
        this.mois=-1;
        this.an.setAn(-1);
    }
    public Date(int jour, int year){
        this.an = new An(year);
        int[] input = numJourAnnee();
        this.mois = input[1];
        this.jour = input[0];
        if(!verifier()){
            System.out.println("Les valeurs entrées sont mauvaise.");
        }else{
            System.out.println("L'objet Date a été crée avec succés.");
        }

    }
    public Date(int inputAn, int mois, int jour){
        this.an=new An(inputAn);
        this.mois=mois;
        this.jour=jour;
    }
    public Date jourDeLAn(){
        return new Date(this.an.getAn(),1,1);
    }
    public boolean egale(int inp_year){
        return inp_year == this.an.getAn();
    }
    public void afficher(){
        if(this.jourDeLAn().equals(this)){System.out.println("Bonne année ! ");}
        System.out.format("%s %d %s %d", numJourSemaine(),numJourAnnee()[0], nomMois(), getYears());
        System.out.println();
    }

    public int getDay(){return this.jour;}
    public int getMonth(){return this.mois;}
    public int getYears(){return this.an.getAn();}

    public void setDay(){
    Scanner input = new Scanner(System.in);
    int d = 0;
    do{
        if(this.mois==-1){
            System.out.println("Veuillez mettre un mois avant de choisir un jour.");
            this.setMonth();
        }
        System.out.println("Insérez un jour : ");
        d = Integer.parseInt(input.nextLine());
    }while(d >= an.nbJourMois(this.mois) || d < 0);
    this.jour=d;
    }
    public void setMonth(){
        Scanner input = new Scanner(System.in);
        int m = 0;
        do{
            System.out.println("Insérez un mois : ");
            m = Integer.parseInt(input.nextLine());
        }while(!(m <=12 && m > 0));
        this.mois=m;
    }
    public void setYears(int an_Input){an.setAn(an_Input);}


    public boolean verifier(){
        return (this.mois > 0 && this.mois <= 12) && this.an.nbJourMois(this.mois) >= this.jour;
    }

    public int[] numJourAnnee(){
        int mois = 0;
        int lastDays = this.jour;
        int[] stat = {31,(this.an.estBissextile() ? 29 : 28),31,30,31,30,31,31,30,31,30,31};
        for(int m = 1; m < stat.length;m++){
            if( stat[m] == -1){
                stat[m] = this.an.estBissextile() ? 29 : 28;
            }
            mois=m+1;
            if(stat[m] >= lastDays){
                break;
            }
            lastDays = lastDays-stat[m];
        }
        System.out.println("------------"+jour);
        return new int[]{lastDays,mois};
    }

    public String numJourSemaine(){
        int d = this.jour;
        int m = this.mois;
        int y = this.an.getAn();
        return new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"}[((23*m/9) + d + (this.mois < 3 ? 4 : 2) + y + (y/4) - (y/100) + (y/400))%7];
    }
    public int compare(Date toCompare){
        if(toCompare.equals(this)) {
            return 0;
        }
        else if(this.an.getAn() > toCompare.getYears() && this.mois > toCompare.getMonth() && this.jour > toCompare.getDay()) {
            return 1;
        }
        else{
            return -1;
        }
    }

    public boolean avant(Date d){
        return compare(d) == -1;
    }
    public boolean apres(Date d){
        return compare(d) == 1;
    }
    public boolean egale(Date d){
        return compare(d) == 0;
    }

    public Date min(Date d){
        return apres(d) ? d : this;
    }

    public Date max(Date d){
        return avant(d) ? d : this;
    }


    public int difference(Date d){
        int diffY=0;
        if(getYears() > d.getYears() || getYears() < d.getYears()){
            diffY = abs(getYears()-d.getYears());
        }
    return 1;
    }

    public String nomMois(){
        return new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Decembre"}[this.mois];
    }

}
