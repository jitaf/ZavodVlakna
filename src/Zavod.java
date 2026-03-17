public class Zavod {

    private static final int POCET_ZAVODNIKU = 4;

    public static void main(String[] args) {

        Logger logger = new Logger();
        SkladSusenek sklad = new SkladSusenek(2, logger);

        Zavodnik[] zavodnici = new Zavodnik[POCET_ZAVODNIKU];

        for (int i = 0; i < POCET_ZAVODNIKU; i++) {

            zavodnici[i] = new Zavodnik(
                    "Závodník " + (i + 1),
                    sklad,
                    logger
            );

            zavodnici[i].start();
        }

        for (Zavodnik z : zavodnici) {
            try {
                z.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!sklad.jeKonec()) {

            logger.log("\nZávod skončil.");
            logger.log("Ve skladu zůstalo sušenek: " + sklad.getSusenky());

            for (Zavodnik z : zavodnici) {
                logger.log(
                        z.getJmenoZavodnika()
                                + " uběhl kol: "
                                + z.getOdbehlaKola()
                );
            }
        }
    }
}