package rpb.java.befehle;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import rpb.java.RPBook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SteckbriefBefehl implements CommandExecutor
{

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(@SuppressWarnings("NullableProblems") CommandSender commandSender, @SuppressWarnings("NullableProblems") Command command, @SuppressWarnings("NullableProblems") String s, @SuppressWarnings("NullableProblems") String[] args)
    {
        if (commandSender instanceof Player)
        {
            if (args.length == 4)
            {
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta bookMeta = (BookMeta) book.getItemMeta();

                BaseComponent[] page = new ComponentBuilder("Vorname: " + args[0] + " \n")
                        .append("Nachname: " + args[1] + " \n")
                        .append("Geburtsdatum: " + args[2] + " \n")
                        .append("Geschlecht: " + args[3] + " \n").create();

                bookMeta.spigot().addPage(page);

                bookMeta.setTitle(commandSender.getName());
                bookMeta.setAuthor(commandSender.getName());


                book.setItemMeta(bookMeta);
                ((Player) commandSender).getInventory().addItem(book);

                RPBook.getInstance().setRpPlayer(((Player) commandSender).getUniqueId(), args[0] + " " + args[1]);

                ((Player) commandSender).setDisplayName(" ");
                ((Player) commandSender).setPlayerListName(" ");

                Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                Team team = sb.registerNewTeam("hide");
                team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

                ((Player) commandSender).setScoreboard(sb);
                team.addEntry(commandSender.getName());
                return true;
            }
            else
            {
                commandSender.sendMessage("/rp [Vorname] [Nachname] [Geburtsdatum] [Geschlecht]");
            }
        }
        return false;
    }
}