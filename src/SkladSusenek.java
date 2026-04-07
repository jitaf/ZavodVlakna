import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkladSusenek {

    private static final Logger logger = LoggerFactory.getLogger(SkladSusenek.class);

    private int susenky;
    private boolean konec = false;

    public SkladSusenek(int susenky) {
        this.susenky = susenky;
    }

    public synchronized boolean snedSusenku(String zavodnik) {

        if (susenky <= 0) {
            logger.error("Nejsme na závodě jedlíků, běžte běhat do lesa.");
            konec = true;
            return false;
        }

        susenky--;
        logger.info("{} snědl sušenku. Ve skladu zbývá {}", zavodnik, susenky);
        return true;
    }

    public synchronized void pridejSusenku(String zavodnik) {
        susenky++;
        logger.info("{} přidal sušenku. Ve skladu je {}", zavodnik, susenky);
    }

    public synchronized boolean jeKonec() {
        return konec;
    }

    public synchronized int getSusenky() {
        return susenky;
    }
}