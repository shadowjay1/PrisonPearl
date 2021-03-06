package com.untamedears.PrisonPearl;

import java.io.IOException;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

class PrisonPearlCommands implements CommandExecutor {
    private final PrisonPearlPlugin plugin;
    private final PrisonPearlStorage pearls;
    private final DamageLogManager damageman;
    private final PrisonPearlManager pearlman;
    private final SummonManager summonman;
    private final BroadcastManager broadcastman;

    public PrisonPearlCommands(PrisonPearlPlugin plugin, DamageLogManager damageman, PrisonPearlStorage pearls, PrisonPearlManager pearlman, SummonManager summonman, BroadcastManager broadcastman) {
        this.plugin = plugin;
        this.pearls = pearls;
        this.damageman = damageman;
        this.pearlman = pearlman;
        this.summonman = summonman;
        this.broadcastman = broadcastman;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("pplocate") || label.equalsIgnoreCase("ppl")) {
            if(sender.hasPermission("prisonpearl.normal.pplocate")) {// sees if the players has the permission.
                return locateCmd(sender, args, false);}
            else {sender.sendMessage("You Do not have Permissions prisonpearl.normal.pplocate");}// if players doesn't have permission, broadcasts message saying what they are missing.

        }else if (label.equalsIgnoreCase("pplocateany")){
            if (sender.hasPermission("prisonpearl.pplocateany")) {// sees if the players has the permission.
                return locateCmd(sender, args, true);}
            else {sender.sendMessage("You Do not have Permissions prisonpearl.pplocateany");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppfree") || label.equalsIgnoreCase("ppf")) {
            if(sender.hasPermission("prisonpearl.normal.ppfree")) {// sees if the players has the permission.
                return freeCmd(sender, args, false);}
            else {sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppfree");} // if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppfreeany")) {
            if(sender.hasPermission("prisonpearl.ppfreeany")) {// sees if the players has the permission.
                return freeCmd(sender, args, true);}
            else {sender.sendMessage("You Do not have Permissions prisonpearl.ppfreeany");} // if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsummon") || label.equalsIgnoreCase("pps")) {
            if(sender.hasPermission("prisonpearl.normal.ppsummon")) {// sees if the players has the permission.
                return summonCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppsummon");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppreturn") || label.equalsIgnoreCase("ppr")) {
            if(sender.hasPermission("prisonpearl.normal.ppreturn")) {// sees if the players has the permission.
                return returnCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppreturn");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppkill") || label.equalsIgnoreCase("ppk")) {
            if(sender.hasPermission("prisonpearl.normal.ppkill")) {// sees if the players has the permission.
                return killCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppkill");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsave")) {
            if(sender.hasPermission("prisonpearl.ppsave")) {// sees if the players has the permission.
                return saveCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.ppsave");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppimprisonany")) {
            if(sender.hasPermission("prisonpearl.ppimprisonany")) {// sees if the players has the permission.
                return imprisonCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.ppimprisonany");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppbroadcast")) {
            if(sender.hasPermission("prisonpearl.normal.ppbroadcast")) {// sees if the players has the permission.
                return broadcastCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppbroadcast");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppconfirm")) {
            if(sender.hasPermission("prisonpearl.normal.ppconfirm")) {// sees if the players has the permission.
                return confirmCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppconfirm");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsilence")) {
            if(sender.hasPermission("prisonpearl.normal.ppsilence")) {// sees if the players has the permission.
                return silenceCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppsilence");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("pploadalts")) {
            if(sender.hasPermission("prisonpearl.pploadalts")) {// sees if the players has the permission.
                return reloadAlts(sender);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.pploadalts");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppcheckall")) {
            if(sender.hasPermission("prisonpearl.ppcheckall")) {// sees if the players has the permission.
                return checkAll(sender);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.ppcheckall");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppcheck")) {
            if(sender.hasPermission("prisonpearl.ppcheck")) {// sees if the players has the permission.
                return check(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.ppcheck");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("kill")) {
            if(sender.hasPermission("prisonpearl.kill")) {// sees if the players has the permission.
                return kill();}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.kill");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsetdist")) {
            if(sender.hasPermission("prisonpearl.normal.ppsetdist")) {// sees if the players has the permission.
                return setDistCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppsetdist");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsetdamage")) {
            if(sender.hasPermission("prisonpearl.normal.ppsetdamage")) {// sees if the players has the permission.
                return setDamageCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppsetdamage");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("pptogglespeech")) {
            if(sender.hasPermission("prisonpearl.normal.pptogglespeech")) {// sees if the players has the permission.
                return toggleSpeechCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.pptogglespeech");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("pptoggledamage")) {
            if(sender.hasPermission("prisonpearl.normal.pptoggledamage")) {// sees if the players has the permission.
                return toggleDamageCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.pptoggledamage");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("pptoggleblocks")) {
            if(sender.hasPermission("prisonpearl.normal.pptoggleblocks")) {// sees if the players has the permission.
                return toggleBlocksCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.pptoggleblocks");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsetmotd")) {
            if(sender.hasPermission("prisonpearl.normal.ppsetmotd")) {// sees if the players has the permission.
                return setMotdCmd(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.normal.ppsetmotd");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppfeed")) {
            return feedCmd(sender, args, false);

        } else if (label.equalsIgnoreCase("pprestore")) {
            return restoreCmd(sender, args, false);
            
        }else if (label.equalsIgnoreCase("ppgetalts")) {
            if(sender.hasPermission("prisonpearl.getalts")) {// sees if the players has the permission.
                return getAlts(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.getalts");}// if players doesn't have permission, broadcasts message saying what they are missing.

        } else if (label.equalsIgnoreCase("ppsetalts")) {
            if(sender.hasPermission("prisonpearl.setalts")) {// sees if the players has the permission.
                return setAlts(sender, args);}
            else{ sender.sendMessage("You Do not have Permissions prisonpearl.setalts");}// if players doesn't have permission, broadcasts message saying what they are missing.

        }return false;
    }

    private boolean setAlts(CommandSender sender, String[] args) {
    	if (args.length < 1)
    	{
    		return false;
    	}
    	String[] confirmedAlts = new String[args.length-1];
    	System.arraycopy(args, 1, confirmedAlts, 0, confirmedAlts.length);
    	try {
			plugin.setAlts(args[0], confirmedAlts);
			return true;
		} catch (IOException e) {
			sender.sendMessage("IOException accured when trying to write to excluded_alts.txt");
			e.printStackTrace();
			return false;
		}
	}

	private boolean getAlts(CommandSender sender, String[] args) {
    	if (args.length != 1)
    	{
    		return false;
    	}
    	String[] alts = plugin.getAltsList().getAltsArray(args[0]);
    	if (alts.length == 0)
    	{
    		sender.sendMessage("No information about " + args[0]);
    		return false;
    	}
    	else
    	{
    		String message = "";
    		for (int x = 0; x < alts.length; x++)
    		{
    			message = message + alts[x] + ", ";
    		}
    		sender.sendMessage(message);
    		return true;
    	}
	}

	private boolean restoreCmd(CommandSender sender, String args[], boolean any){
        if ((sender instanceof Player)) {
            sender.sendMessage("Must use [[restore at the console");
            return true;
        }
        if (!args[0].isEmpty()){
            sender.sendMessage("Restoring from " + args[0]);
            sender.sendMessage(pearls.restorePearls(pearlman, args[0]));
        }else{
            sender.sendMessage("Restoring from most recent record...");
            sender.sendMessage(pearls.restorePearls(pearlman, null));
        }
        return true;
    }

    private boolean feedCmd(CommandSender sender, String args[], boolean any) {
        if ((sender instanceof Player)) {
            sender.sendMessage("Must use ppfeed at the console");
            return true;
        }
        sender.sendMessage("Feeding all pearls: " + pearls.getPearlCount());
        sender.sendMessage(pearls.feedPearls(pearlman));
        return true;
    }

    private PrisonPearl setCmd(CommandSender sender, String[] args) {
        PrisonPearl pp;
        if (!(sender instanceof Player)) {
            sender.sendMessage("ppset cannot be used at the console");
            return null;
        }
        String[] anArray = {};
        Player player = (Player)sender;
        pp = getCommandPearl(player, anArray, 1);
        if (pp == null){
            return null;
        }
        if (args.length > 1)
            return null;
        if (pp.getImprisonedPlayer().isDead()) {
            sender.sendMessage(pp.getImprisonedName() + " is dead. Bring him back to try again.");
            return null;
        } else if (pp.getImprisonedPlayer() == player) {
            sender.sendMessage("You cannot alter your own pearl!");
            return null;
        } else if (!(summonman.isSummoned(pp))) {
            sender.sendMessage(pp.getImprisonedName() + " is not summoned.");
            return null;
        }
        return pp;
    }

    private boolean setDistCmd(CommandSender sender, String args[]) {
        PrisonPearl pp = setCmd(sender, args);
        if (pp == null) {
            return false;
        }
        summonman.getSummon(pp.getImprisonedName()).setAllowedDistance(Integer.parseInt(args[0]));
        sender.sendMessage(pp.getImprisonedName() + "'s allowed distance set to " + args[0]);
        return true;
    }

    private boolean setDamageCmd(CommandSender sender, String args[]) {
        PrisonPearl pp = setCmd(sender, args);
        if (pp == null) {
            return false;
        }
        summonman.getSummon(pp.getImprisonedName()).setDamageAmount(Integer.parseInt(args[0]));
        sender.sendMessage(pp.getImprisonedName() + "'s damage amount set to " + args[0]);
        return true;
    }

    private boolean toggleSpeechCmd(CommandSender sender, String args[]) {
        PrisonPearl pp = setCmd(sender, args);
        if (pp == null) {
            return false;
        }
        boolean speak = summonman.getSummon(pp.getImprisonedName()).isCanSpeak();
        summonman.getSummon(pp.getImprisonedName()).setCanSpeak(!speak);
        sender.sendMessage(pp.getImprisonedName() + " ability to speak set to " + !speak);
        return true;
    }

    private boolean toggleDamageCmd(CommandSender sender, String args[]) {
        PrisonPearl pp = setCmd(sender, args);
        if (pp == null) {
            return false;
        }
        boolean damage = summonman.getSummon(pp.getImprisonedName()).isCanDealDamage();
        summonman.getSummon(pp.getImprisonedName()).setCanDealDamage(!damage);
        sender.sendMessage(pp.getImprisonedName() + " ability to deal damage set to " + !damage);
        return true;
    }

    private boolean toggleBlocksCmd(CommandSender sender, String args[]) {
        PrisonPearl pp = setCmd(sender, args);
        if (pp == null) {
            return false;
        }
        boolean block = summonman.getSummon(pp.getImprisonedName()).isCanBreakBlocks();
        summonman.getSummon(pp.getImprisonedName()).setCanBreakBlocks(!block);
        sender.sendMessage(pp.getImprisonedName() + " ability to break blocks set to " + !block);
        return true;
    }

    private boolean setMotdCmd(CommandSender sender, String args[]) {
        PrisonPearl pp;
        if (!(sender instanceof Player)) {
            sender.sendMessage("ppset cannot be used at the console");
            return true;
        }
        String[] anArray = {};
        Player player = (Player)sender;
        pp = getCommandPearl(player, anArray, 1);
        if (pp == null) {
            return false;
        }
        String s = "";
        for (String arg : args) {
            s = s.concat(arg + " ");
        }
        pp.setMotd(s);
        sender.sendMessage(pp.getImprisonedName() + "'s Message of the Day set to " + s);
        return true;
    }   

    private boolean locateCmd(CommandSender sender, String args[], boolean any) {
        String name_is;
        String name_possesive;
        PrisonPearl pp;
        if (!any) {
            if (args.length != 0)
                return false;
            if (!(sender instanceof Player)) {
                sender.sendMessage("Must use pplocateany at the console");
                return true;
            }
            name_is = "You are";
            name_possesive = "Your";
            pp = pearls.getByImprisoned((Player)sender);
        } else {
            if (args.length != 1)
                return false;
            name_is = args[0] + " is";
            name_possesive = args[0] + "'s";
            pp = pearls.getByImprisoned(args[0]);
        }
        if (pp != null) {
            if (!pp.verifyLocation()) {
                System.err.println("PrisonPearl for " + pp.getImprisonedName() + " didn't validate, so is now set free");
                pearlman.freePearl(pp);
                plugin.getLogger().info(pp.getImprisonedName() + " is being freed. Reason: Locate command returned a invalid pearl.");
            } else {
                sender.sendMessage(ChatColor.GREEN + name_possesive + " prison pearl is " + pp.describeLocation());
                if (sender instanceof Player && !any)
                    broadcastman.broadcast((Player)sender, ChatColor.GREEN + pp.getImprisonedName() + ": " + pp.describeLocation());
            }
        } else {
            sender.sendMessage(name_is + " not imprisoned");
        }
        return true;
    }

    private boolean freeCmd(CommandSender sender, String args[], boolean any) {
        PrisonPearl pp;
        if (!any) {
            if (args.length > 1)
                return false;
            if (!(sender instanceof Player)) {
                sender.sendMessage("Must use freeany at console");
                return true;
            }
            Player player = (Player)sender;
            int slot = getCommandPearlSlot(player, args, 0);
            if (slot == -1)
                return true;
            pp = pearls.getByItemStack(player.getInventory().getItem(slot));
            player.getInventory().setItem(slot, null);
        } else {
            if (args.length != 1)
                return false;
            pp = pearls.getByImprisoned(args[0]);
            if (pp == null) {
                sender.sendMessage(args[0] + " is not imprisoned");
                return true;
            }
        }
        if (pearlman.freePearl(pp)) {
        	plugin.getLogger().info(pp.getImprisonedName() + " is being freed. Reason: Freed via command by " + sender.getName());
            if (pp.getImprisonedPlayer() != sender) // when freeing yourself, you're already going to get a message
                sender.sendMessage("You've freed " + pp.getImprisonedName());
        } else {
            sender.sendMessage("You failed to free " + pp.getImprisonedName());
        }
        return true;
    }

    private boolean imprisonCmd(CommandSender sender, String args[]) {
        if (args.length != 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("imprison cannot be used at the console");
            return true;
        }
        if (pearlman.imprisonPlayer(args[0], (Player)sender)) {
            sender.sendMessage("You imprisoned " + args[0]);
            Player player = Bukkit.getPlayerExact(args[0]);
            if (player != null) {
                player.setHealth(0);
            }
        } else {
            sender.sendMessage("You failed to imprison " + args[0]);
        }
        return true;
    }

    private boolean summonCmd(CommandSender sender, String args[]) {
        if (args.length > 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        PrisonPearl pp;
        if (args.length == 1) {
            try {
                pp = getCommandPearl(player, args, 0);
            } catch (NumberFormatException e) {
                pp = getCommandPearl(player, args, 1);
            }
        } else {
            pp = getCommandPearl(player, args, 0);
        }
        if (pp == null)
            return true;
        //check if the pearled player is combat tagged
        if (plugin.isCombatTagged(pp.getImprisonedName())) {
            sender.sendMessage(ChatColor.RED+"[PrisonPearl]"+ChatColor.WHITE+" You cannot summon a CombatTagged player.");
            return true;
        }
        if (pp.getImprisonedPlayer() == null || pp.getImprisonedPlayer().isDead()) {
            sender.sendMessage(pp.getImprisonedName() + " cannot be summoned");
            return true;
        } else if (pp.getImprisonedPlayer() == player) {
            sender.sendMessage("You cannot summon yourself!");
            return true;
        } else if (summonman.isSummoned(pp)) {
            sender.sendMessage(pp.getImprisonedName() + " is already summoned");
            return true;
        }
        if (summonman.summonPearl(pp))
            sender.sendMessage("You've summoned " + pp.getImprisonedName());
        else
            sender.sendMessage("You failed to summon " + pp.getImprisonedName());
        return true;
    }

    private boolean returnCmd(CommandSender sender, String args[]) {
        if (args.length > 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        PrisonPearl pp = getCommandPearl(player, args, 0);
        if (pp == null)
            return true;
        //check if the pearled player is combat tagged
        if (plugin.isCombatTagged(pp.getImprisonedName())) {
            sender.sendMessage(ChatColor.RED+"[PrisonPearl]"+ChatColor.WHITE+" You cannot return a CombatTagged player.");
            return true;
        }
        if (pp.getImprisonedName().equals(player.getName())) {
            sender.sendMessage("You cannot return yourself!");
            return true;
        } else if (!summonman.isSummoned(pp)) {
            sender.sendMessage(pp.getImprisonedName() + " has not been summoned!");
            return true;
        } else if (damageman.hasDamageLog(player)) {
            sender.sendMessage(pp.getImprisonedName() + " is in combat and cannot be returned!");
            return true;
        }
        if (summonman.returnPearl(pp))
            sender.sendMessage("You've returned " + pp.getImprisonedName());
        else
            sender.sendMessage("You failed to return " + pp.getImprisonedName());
        return true;
    }

    private boolean killCmd(CommandSender sender, String args[]) {
        if (args.length > 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        PrisonPearl pp = getCommandPearl(player, args, 0);
        if (pp == null)
            return true;
        if (!summonman.isSummoned(pp)) {
            sender.sendMessage(pp.getImprisonedName() + " has not been summoned!");
            return true;
        }
        if (summonman.killPearl(pp))
            sender.sendMessage("You've killed " + pp.getImprisonedName());
        else
            sender.sendMessage("You failed to kill " + pp.getImprisonedName());
        return true;
    }

    private boolean saveCmd(CommandSender sender, String args[]) {
        if (args.length > 0)
            return false;
        try {
            plugin.saveAll(true);
            sender.sendMessage("PrisonPearl data saved!");
            return true;
        } catch (RuntimeException e) {
            if (!(sender instanceof ConsoleCommandSender))
                sender.sendMessage("PrisonPearl failed to save data! Check server logs!");
            throw e;
        }
    }

    private boolean broadcastCmd(CommandSender sender, String args[]) {
        if (args.length != 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        Player receiver = Bukkit.getPlayerExact(args[0]);
        if (receiver == null) {
            sender.sendMessage("No such player " + args[0]);
            return true;
        } else if (receiver == player) {
            sender.sendMessage("You cannot broadcast to yourself!");
            return true;
        } else if (!pearls.isImprisoned(player)) {
            sender.sendMessage("You are not imprisoned!");
            return true;
        }
        if (broadcastman.addBroadcast(player, receiver)) {
            sender.sendMessage("You will broadcast pplocate information to " + receiver.getDisplayName());
            receiver.sendMessage("Type /ppconfirm to receive pplocate broadcasts from " + player.getDisplayName());
        } else {
            sender.sendMessage("You are already broadcasting to " + receiver.getDisplayName());
        }
        return true;
    }

    private boolean confirmCmd(CommandSender sender, String args[]) {
        if (args.length > 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        Player broadcaster;
        if (args.length == 1) {
            broadcaster = Bukkit.getPlayerExact(args[0]);
            if (broadcaster == null) {
                sender.sendMessage("No such player " + args[0]);
                return true;
            }
        } else {
            broadcaster = broadcastman.getQuickConfirmPlayer(player);
            if (broadcaster == null) {
                sender.sendMessage("Nobody has requested to broadcast to you");
                return true;
            }
        }
        if (broadcastman.confirmBroadcast(broadcaster, player)) {
            player.sendMessage("You will now receive broadcasts from " + broadcaster.getDisplayName());
        } else {
            player.sendMessage(broadcaster.getDisplayName() + " does not wish to broadcast to you");
        }
        return true;
    }

    private boolean silenceCmd(CommandSender sender, String args[]) {
        if (args.length != 1)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command cannot be used at console");
            return true;
        }
        Player player = (Player)sender;
        Player broadcaster = Bukkit.getPlayerExact(args[0]);
        if (broadcaster == null) {
            sender.sendMessage("No such player " + args[0]);
            return true;
        }
        if (broadcastman.silenceBroadcast(player, broadcaster)) {
            player.sendMessage("You will no longer receive broadcasts from " + broadcaster.getDisplayName());
        } else {
            player.sendMessage(broadcaster.getDisplayName() + " is not broadcasting to you");
        }
        return true;
    }

    private PrisonPearl getCommandPearl(Player player, String args[], int pos) {
        int slot = getCommandPearlSlot(player, args, pos);
        if (slot != -1)
            return pearls.getByItemStack(player.getInventory().getItem(slot));
        else
            return null;
    }

    private int getCommandPearlSlot(Player player, String args[], int pos) {
        if (args.length <= pos) {
            ItemStack item = player.getItemInHand();
            if (item.getType() != Material.ENDER_PEARL) {
                player.sendMessage("You must hold a pearl or supply the player's name to use this command");
                return -1;
            }
            if (pearls.getByItemStack(item) == null) {
                player.sendMessage("This is an ordinary ender pearl");
                return -1;
            }
            return player.getInventory().getHeldItemSlot();
        } else {
            PrisonPearl pp = pearls.getByImprisoned(args[pos]);
            if (pp != null) {
                Inventory inv = player.getInventory();
                for (Entry<Integer, ? extends ItemStack> entry : inv.all(Material.ENDER_PEARL).entrySet()) {
                    if (entry.getValue().getDurability() == pp.getID())
                        return entry.getKey();
                }
            }
            player.sendMessage("You don't possess " + args[0] + "'s prison pearl");
            return -1;
        }
    }

    private boolean reloadAlts(CommandSender sender) {
        if (!(sender instanceof Player)) {
            plugin.loadAlts();
            plugin.checkBanAllAlts();
            return true;
        }
        return false;
    }

    private boolean checkAll(CommandSender sender) {
        if (!(sender instanceof Player)) {
            plugin.checkBanAllAlts();
            return true;
        }
        return false;
    }

    private boolean check(CommandSender sender, String[] args) {
        if (args.length != 1)
            return false;
        if (!(sender instanceof Player)) {
            boolean isBanned = plugin.isTempBanned(args[0]);
            if (isBanned) {
                sender.sendMessage(args[0]+" is temp banned for having "+plugin.getImprisonedCount(args[0])+" imprisoned accounts: "+plugin.getImprisonedAltsString(args[0]));
            } else {
                sender.sendMessage(args[0]+" is not temp banned");
            }
            return true;
        }
        return false;
    }

    private boolean kill() {
        return false;
    }
}
