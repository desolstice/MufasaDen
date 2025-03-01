package world.bentobox.mufasaden;

import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.addons.Pladdon;

public class MufasaDenBentoBoxPladdon  extends Pladdon {
    private Addon addon;

    @Override
    public Addon getAddon() {
        if (addon == null) {
            addon = new MufasaDenBentoBoxAddon();
        }
        return addon;
    }
}