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
<<< This is the easy bug 'confident_germain' (instance 1 of 1) >>>
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dnd.ClipboardSupport;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action copies the selected / marked files to the system clipboard, allowing to paste
 * them to muCommander or another application.
 *
 * @author Maxence Bernard
 */
public class CopyFilesToClipboardAction extends SelectedFilesAction
{

    public CopyFilesToClipboardAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction(FileSet files)
    {
        ClipboardSupport.setClipboardFiles(files);
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
            return new CopyFilesToClipboardAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "CopyFilesToClipboard";

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
            return KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK);
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        }
    }
}
