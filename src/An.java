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

    public boolean estBissextile(An an){
        return (an.getAn()%4==0 && an.getAn()%100 != 0) || (an.getAn()%400==0 && an.getAn()%4000 != 0);
    }

    public int nbJoursAnnee(An an){
        return estBissextile(an) ? 366 : 365;
    }

    public int nbJourMois(int mois, An an){
        int fevLen = estBissextile(an) ? 29 : 28;
        int[] tab = {31, fevLen, 31,30,31,30,31,31,30,31,30,31};
        return tab[mois-1];
    }


}
