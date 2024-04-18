package fr.nkri.wizapi.cmds;

public abstract class ICommand {

    public ICommand() {}
    public abstract boolean onCommand(final CommandArguments commandArguments);
}