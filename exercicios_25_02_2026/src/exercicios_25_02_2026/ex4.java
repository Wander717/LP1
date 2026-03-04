package exercicios_25_02_2026;

public class ex4 {

    public static void executar () {
        
        double notaFinal = calcularMedia(6.0, 7.0, 8.0, 1.0, 5.0, 7.5, 6.0);
        System.out.println("A nota final calculada é: " + notaFinal);
    }

    public static double calcularMedia(double p1, double e1, double e2, double x, double sub, double api, double exf) {
        
        double m = (p1 * 0.5) + (e1 * 0.2) + (e2 * 0.3) + x + (sub * 0.15);
        double bonusAPI = 0;
        if (m > 5.9) {
            bonusAPI = api * 0.5;
        }        
        
        double notaSemestre = (m * 0.5) + bonusAPI;
        return Math.max(notaSemestre, exf);
    }
}