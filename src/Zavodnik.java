import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class Zavodnik extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Zavodnik.class);

    private String jmeno;
    private SkladSusenek sklad;
    private Random random = new Random();

    private int kolaKeSplneni = 3;
    private int odbehlaKola = 0;

    public Zavodnik(String jmeno, SkladSusenek sklad) {
        this.jmeno = jmeno;
        this.sklad = sklad;
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

            logger.info("{} kolo {} hodil: {}", jmeno, odbehlaKola, hod);

            try {

                switch (hod) {

                    case 1:
                        if (!sklad.snedSusenku(jmeno)) {
                            return;
                        }
                        logger.info("{} odpočívá 3 sekundy.", jmeno);
                        Thread.sleep(3000);
                        break;

                    case 2:
                        kolaKeSplneni++;
                        logger.info("{} musí běžet jedno kolo navíc.", jmeno);
                        break;

                    case 3:
                        logger.info("{} čeká 5 sekund.", jmeno);
                        Thread.sleep(5000);
                        break;

                    case 4:
                        logger.info("{} čeká 2 sekundy.", jmeno);
                        Thread.sleep(2000);
                        break;

                    case 5:
                        sklad.pridejSusenku(jmeno);
                        break;

                    case 6:
                        logger.info("{} pokračuje bez akce.", jmeno);
                        break;
                }

            } catch (InterruptedException e) {
                logger.error("Chyba ve vlákně {}", jmeno, e);
            }
        }

        if (!sklad.jeKonec()) {
            logger.info("{} dokončil závod.", jmeno);
        }
    }
}