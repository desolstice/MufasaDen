package world.bentobox.mufasaden;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.managers.RanksManager;
import world.bentobox.mufasaden.Listeners.MobSpawnListener;

public class MufasaDenBentoBoxAddon extends Addon {
    @Override
    public void onEnable() {
        // Check if it is enabled - it might be loaded, but not enabled.
        if (this.getPlugin() == null || !this.getPlugin().isEnabled())
        {
            Bukkit.getLogger().severe("BentoBox is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Check if addon is not disabled before.
        if (this.getState().equals(State.DISABLED))
        {
            Bukkit.getLogger().severe("CauldronWitchery is not available or disabled!");
            return;
        }

        // Init flag.
        MufasaDenBentoBoxAddon.MUFASA_DEN_PLAYER_HOSTILE_MOB_SPAWN_PREVENTION =
            new Flag.Builder("MUFASA_DEN_PLAYER_HOSTILE_MOB_SPAWN_PREVENTION", Material.ZOMBIE_HEAD)
                .type(Flag.Type.SETTING)
                .defaultSetting(true)
                .defaultRank(RanksManager.MEMBER_RANK)
                .mode(Flag.Mode.BASIC)
                .listener(new MobSpawnListener())
                .build();

        MufasaDenBentoBoxAddon.MUFASA_DEN_PLAYER_PASSIVE_MOB_SPAWN_PREVENTION =
            new Flag.Builder("MUFASA_DEN_PLAYER_PASSIVE_MOB_SPAWN_PREVENTION", Material.WHITE_WOOL)
                .type(Flag.Type.SETTING)
                .defaultSetting(true)
                .defaultRank(RanksManager.MEMBER_RANK)
                .mode(Flag.Mode.BASIC)
                .listener(new MobSpawnListener())
                .build();

        this.getPlugin().getAddonsManager().getGameModeAddons().forEach(gameModeAddon -> {
            if (gameModeAddon.isEnabled()) {

                MUFASA_DEN_PLAYER_HOSTILE_MOB_SPAWN_PREVENTION.addGameModeAddon(gameModeAddon);
                MUFASA_DEN_PLAYER_PASSIVE_MOB_SPAWN_PREVENTION.addGameModeAddon(gameModeAddon);
            }
        });

        this.registerFlag(MUFASA_DEN_PLAYER_HOSTILE_MOB_SPAWN_PREVENTION);
        this.registerFlag(MUFASA_DEN_PLAYER_PASSIVE_MOB_SPAWN_PREVENTION);

    }

    @Override
    public void onDisable() {

    }

    // ---------------------------------------------------------------------
// Section: Flags
// ---------------------------------------------------------------------

    public static Flag MUFASA_DEN_PLAYER_HOSTILE_MOB_SPAWN_PREVENTION;
    public static Flag MUFASA_DEN_PLAYER_PASSIVE_MOB_SPAWN_PREVENTION;
}
