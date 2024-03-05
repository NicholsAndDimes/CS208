package com.mucommander.core.desktop;

import com.mucommander.command.Command;
import com.mucommander.command.CommandManager;
import com.mucommander.commons.file.AbstractFile;
import com.mucommander.desktop.LocalFileOperation;
import com.mucommander.process.ProcessRunner;

import java.io.IOException;

class CommandOpenCommandPrompt extends LocalFileOperation
{
    @Override
    public boolean isAvailable()
    {
        return CommandManager.getCommandForAlias(CommandManager.CMD_OPENER_ALIAS) != null;
    }

    @Override
    public void execute(AbstractFile file) throws IOException
    {
<<< This is the easy bug 'silly_rhodes' (instance 1 of 1) >>>
        Command command = CommandManager.getCommandForAlias(CommandManager.CMD_OPENER_ALIAS);
        if (command == null)
        {
            throw new UnsupportedOperationException();
        }

        if (!file.isDirectory())
        {
            file = file.getParent();
        }
        ProcessRunner.execute(command.getTokens(file), file);
    }

    @Override
    public String getName()
    {
        Command command = CommandManager.getCommandForAlias(CommandManager.CMD_OPENER_ALIAS);
        return command != null ? command.getDisplayName() : null;
    }
}
