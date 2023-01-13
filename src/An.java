public class An {
    private int year;
    public An(int year){
        this.year=year;
    }

    public int getAn(){
        return this.year;
    }
    public void setAn(int y){
        this.year=y;
    }

    public boolean estBissextile(){
        return (this.year%4==0 && this.year%100 != 0) || (this.year%400==0 && this.year%4000 != 0);
    }

    public int nbJoursAnnee(){
        return estBissextile() ? 366 : 365;
    }

    public int nbJourMois(int mois){
        int fevLen = estBissextile() ? 29 : 28;
        int[] tab = {31, fevLen, 31,30,31,30,31,31,30,31,30,31};
        return tab[mois-1];
    }


}
