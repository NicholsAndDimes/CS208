/*
 * This file is part of muCommander, http://www.mucommander.com
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.ui.action.impl;

import com.mucommander.commons.file.util.FileSet;
import com.mucommander.ui.action.AbstractActionDescriptor;
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
<<< This is the complex bug 'elated_gauss' (instance 1 of 9) >>>
<<< This is the complex bug 'elated_gauss' (instance 2 of 9) >>>
<<< This is the complex bug 'elated_gauss' (instance 3 of 9) >>>
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dnd.ClipboardSupport;
import com.mucommander.ui.dnd.TransferableFileSet;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action copies the file base name(s) (without extension) of the currently selected / marked files(s) to the system clipboard.
 *
 * @author Chen Rozenes
 */
public class CopyFileBaseNamesAction extends SelectedFilesAction
{

    public CopyFileBaseNamesAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction(FileSet files)
    {
        // Create a TransferableFileSet and make DataFlavour.stringFlavor (text) the only DataFlavour supported
        TransferableFileSet tfs = new TransferableFileSet(files);

        // Disable unwanted data flavors
        tfs.setJavaFileListDataFlavorSupported(false);
        tfs.setTextUriFlavorSupported(false);
        // Note: not disabling this flavor would throw an exception because the flavor data is not serializable
        tfs.setFileSetDataFlavorSupported(false);

        // Transfer filenames, not file paths
        tfs.setStringDataFlavourTransfersFilename(true);

        // Transfer base names (filename without its extension)
        tfs.setStringDataFlavourTransfersFileBaseName(true);

        ClipboardSupport.setClipboardContents(tfs);

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
            return new CopyFileBaseNamesAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "CopyFileBaseNames";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.SELECTION;
        }

        public KeyStroke getDefaultAltKeyStroke()
        {
            return null;
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK);
        }
    }

}
