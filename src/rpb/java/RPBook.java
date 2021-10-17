package rpb.java;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import rpb.java.befehle.SteckbriefBefehl;

public class RPBook extends JavaPlugin
{
    private static RPBook instance;

    public static RPBook getInstance()
    {
        return instance;
    }

    //on enable create all class instances and reg commands events and co
    @Override
    public void onEnable()
    {
        instance = this;

        regCommands();
    }


    @SuppressWarnings("ConstantConditions")
    private void regCommands()
    {
        this.getCommand("rp").setExecutor(new SteckbriefBefehl());
    }


    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll(this);
    }
}