import java.util.Random;

public class Zavodnik extends Thread {

    private String jmeno;
    private SkladSusenek sklad;
    private Logger logger;
    private Random random = new Random();

    private int kolaKeSplneni = 3;
    private int odbehlaKola = 0;

    public Zavodnik(String jmeno, SkladSusenek sklad, Logger logger) {
        this.jmeno = jmeno;
        this.sklad = sklad;
        this.logger = logger;
    }

    public int getOdbehlaKola() {
        return odbehlaKola;
    }

    public String getJmenoZavodnika() {
        return jmeno;
    }

    @Override
    public void run() {

        while (odbehlaKola < kolaKeSplneni && !sklad.jeKonec()) {

            odbehlaKola++;

            int hod = random.nextInt(6) + 1;

            logger.log(jmeno + " kolo " + odbehlaKola + " hodil: " + hod);

            try {

                switch (hod) {

                    case 1:
                        if (!sklad.snedSusenku(jmeno)) {
                            return;
                        }
                        logger.log(jmeno + " odpočívá 3 sekundy.");
                        Thread.sleep(3000);
                        break;

                    case 2:
                        kolaKeSplneni++;
                        logger.log(jmeno + " musí běžet jedno kolo navíc.");
                        break;

                    case 3:
                        logger.log(jmeno + " čeká 5 sekund.");
                        Thread.sleep(5000);
                        break;

                    case 4:
                        logger.log(jmeno + " čeká 2 sekundy.");
                        Thread.sleep(2000);
                        break;

                    case 5:
                        sklad.pridejSusenku(jmeno);
                        break;

                    case 6:
                        logger.log(jmeno + " pokračuje bez akce.");
                        break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!sklad.jeKonec()) {
            logger.log(jmeno + " dokončil závod.");
        }
    }
}