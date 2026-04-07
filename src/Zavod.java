import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zavod {

    private static final int POCET_ZAVODNIKU = 4;
    private static final Logger logger = LoggerFactory.getLogger(Zavod.class);

    public static void main(String[] args) {

        SkladSusenek sklad = new SkladSusenek(2);

        Zavodnik[] zavodnici = new Zavodnik[POCET_ZAVODNIKU];

        for (int i = 0; i < POCET_ZAVODNIKU; i++) {

            zavodnici[i] = new Zavodnik(
                    "Závodník " + (i + 1),
                    sklad
            );

            zavodnici[i].start();
        }

        for (Zavodnik z : zavodnici) {
            try {
                z.join();
            } catch (InterruptedException e) {
                logger.error("Chyba při join()", e);
            }
        }

        if (!sklad.jeKonec()) {

            logger.info("\nZávod skončil.");
            logger.info("Ve skladu zůstalo sušenek: {}", sklad.getSusenky());

            for (Zavodnik z : zavodnici) {
                logger.info("{} uběhl kol: {}", z.getJmenoZavodnika(), z.getOdbehlaKola());
            }
        }
    }
}