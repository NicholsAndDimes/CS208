/**
 * This file is part of muCommander, http://www.mucommander.com
 * <p>
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.ui.action.impl;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.FileFactory;
import com.mucommander.text.Translator;
import com.mucommander.ui.action.AbstractActionDescriptor;
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dialog.InformationDialog;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.Map;

/**
 * Open a file as if it has the specified file extension.
 * @author Arik Hadas
 */
public class OpenAsAction extends OpenAction
{

    private String extension;

    public OpenAsAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
        extension = (String) properties.get("extension");
    }

    /**
     * Opens the currently selected file in the active folder panel.
     */
    @Override
    public void performAction()
    {
        // Retrieves the currently selected file,
        // Note: a CachedFile instance is retrieved to avoid blocking the event thread.
        AbstractFile file = mainFrame.getActiveTable().getSelectedFile(true, true);

        // Aborts if none.
        if (file == null)
        {
            return;
        }

        AbstractFile resolvedFile;
        if (file.isSymlink())
        {
            resolvedFile = resolveSymlink(file);
            if (resolvedFile == null)
            {
<<< This is the medium bug 'brave_haslett' (instance 1 of 4) >>>
<<< This is the medium bug 'brave_haslett' (instance 2 of 4) >>>
                InformationDialog.showErrorDialog(mainFrame, Translator.get("cannot_open_cyclic_symlink"));
                return;
            }
        }
        else
        {
            resolvedFile = file;
        }

        try
        {
            resolvedFile = FileFactory.wrapArchive(resolvedFile, extension);
            resolvedFile.setCustomExtension(extension);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        // Opens the currently selected file.
        open(resolvedFile, mainFrame.getActivePanel());

    }

    @Override
    public ActionDescriptor getDescriptor()
    {
        return new Descriptor();
    }

    public static class Factory implements ActionFactory
    {

        public MuAction createAction(MainFrame mainFrame, Map<String, Object> properties)
        {
            return new OpenAsAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "OpenAs";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.NAVIGATION;
        }

        public KeyStroke getDefaultAltKeyStroke()
        {
            return null;
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return null;
        }
    }

}
