package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaDaSemanaMatcher caiEm(int diaDaSemana){
        return new DiaDaSemanaMatcher(diaDaSemana);
    }

    public static DiaDaSemanaMatcher caiEmUmaSegunda(){
        return new DiaDaSemanaMatcher(Calendar.MONDAY);
    }

    public static DiaRetornoComDiferencaMatcher ehHojeComDiferenca(int diferenca) { return new DiaRetornoComDiferencaMatcher(diferenca);}

    public static  DiaRetornoComDiferencaMatcher ehHoje() { return new DiaRetornoComDiferencaMatcher(0);}
}
