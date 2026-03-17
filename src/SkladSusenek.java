public class SkladSusenek {

    private int susenky;
    private boolean konec = false;
    private Logger logger;

    public SkladSusenek(int susenky, Logger logger) {
        this.susenky = susenky;
        this.logger = logger;
    }

    public synchronized boolean snedSusenku(String zavodnik) {

        if (susenky <= 0) {
            logger.log("Nejsme na závodě jedlíků, běžte běhat do lesa.");
            konec = true;
            return false;
        }

        susenky--;
        logger.log(zavodnik + " snědl sušenku. Ve skladu zbývá " + susenky);
        return true;
    }

    public synchronized void pridejSusenku(String zavodnik) {
        susenky++;
        logger.log(zavodnik + " přidal sušenku do skladu. Ve skladu je " + susenky);
    }

    public synchronized boolean jeKonec() {
        return konec;
    }

    public synchronized int getSusenky() {
        return susenky;
    }
}