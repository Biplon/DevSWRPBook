package rpb.java.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rpb.java.RPBook;

public class OnPlayerChat implements Listener
{
    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent e)
    {
        if (RPBook.getInstance().getRpPlayer(e.getPlayer().getUniqueId()) != null)
        {
            e.setFormat(RPBook.getInstance().getRpPlayer(e.getPlayer().getUniqueId()) + ": " + e.getMessage());
        }
    }
}
