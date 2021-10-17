package rpb.java;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import rpb.java.befehle.SteckbriefBefehl;
import rpb.java.listener.OnPlayerChat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RPBook extends JavaPlugin
{
    private static RPBook instance;

    public static RPBook getInstance()
    {
        return instance;
    }

    private final Map<UUID, String> rpPlayerList = new HashMap<>();

    public String getRpPlayer(UUID player)
    {
        return rpPlayerList.get(player);
    }

    public void setRpPlayer(UUID player, String name)
    {
        if (rpPlayerList.get(player) != null)
        {
            rpPlayerList.remove(player);
        }
        rpPlayerList.put(player, name);
    }

    //on enable create all class instances and reg commands events and co
    @Override
    public void onEnable()
    {
        instance = this;

        regCommands();
        regEvents();
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

    private void regEvents()
    {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new OnPlayerChat(), this);
    }
}