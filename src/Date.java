import java.util.Scanner;

import static java.lang.Math.abs;

public class Date {
    private int jour=-1;
    private int mois=-1;
    private An an;
    //Constructeur par input utilisateur complet via les mutateurs
    public Date(){
        this.setYears();
        this.setDay();
    }
    //Constructeur avec attributs par input utilisateur en indiquant le numero du jours d'un année. Le systeme devra ensuite les convertirs en date réel.
    public Date(int an, int jourtotaux){
        this.an = new An(an);

        if(jourtotaux > 367){
            System.out.println("Veuillez entrez une quantité de jour inclus dans l'année correspondante.");
        }

        this.numJourAnnee(jourtotaux);

        if(!verifier()){
            System.out.println("Les valeurs entrées sont mauvaise.");
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
        //if(this.jourDeLAn().equals(this)){System.out.println("Bonne année ! ");}
        System.out.format("%s %d %s %d", numJourSemaine(),this.jour, nomMois(), getYears());
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
    }while(d >= an.nbJourMois(this.mois, this.an) && d < 0);
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
    public void setYears(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insérez une année : ");
        this.an = new An(Integer.parseInt(input.nextLine()));
    }

    public boolean verifier(){
        return (this.mois > 0 && this.mois <= 12) && this.an.nbJourMois(this.mois, this.an) >= this.jour;
    }

    public void numJourAnnee(int jourtotaux){
        int mois = 0;
        int lastDays = jourtotaux;
        int[] stat = {31,(this.an.estBissextile(this.an) ? 29 : 28),31,30,31,30,31,31,30,31,30,31};
        for(int m = 1; m < stat.length;m++){
            if( stat[m] == -1){
                stat[m] = this.an.estBissextile(this.an) ? 29 : 28;
            }
            mois=m+1;
            if(stat[m] >= lastDays){
                break;
            }
            lastDays = lastDays-stat[m];
        }
        this.jour=lastDays;
        this.mois=mois;
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
        int cpyears = calculYearsDiff(this.an, d.an);
        int cpmois=0; int cpJours=0;
        for(int i=Math.min(d.getMonth(), this.getMonth());i <= Math.max(d.getMonth(), this.getMonth()); i++){
            if(i == Math.max(d.getMonth(), this.getMonth())){
                cpJours = Math.max(d.getDay(), this.getDay()) - Math.min(d.getDay(), this.getDay());
            }else{
                cpmois+=this.an.nbJourMois(i,this.an);
            }
        }
        return cpmois+cpJours+cpyears;

    }

    public int calculYearsDiff(An a1, An a2){
        int cp=0;
        if(a1.getAn() != a2.getAn()){
            for(int i = Math.min(a1.getAn(), a2.getAn());  i < Math.max(a1.getAn(), a2.getAn()); i++){
                System.out.println(i);
                cp+= a1.estBissextile(new An(i)) ? 366 : 355;
            }
        }
        else{
            return cp;
        }
        return cp;
    }

    public String nomMois(){
        return new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre","Novembre","Decembre"}[this.mois-1];
    }


}
