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

import com.mucommander.ui.action.AbstractActionDescriptor;
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
<<< This is the easy bug 'blissful_pare' (instance 1 of 1) >>>
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.FolderPanel;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.tree.FoldersTreePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action toggles the visibility of a directory tree.
 *
 * @author Mariusz Jakubowski
 * @see FoldersTreePanel
 */
public class ToggleTreeAction extends MuAction
{

    public ToggleTreeAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction()
    {
        FolderPanel folderPanel = mainFrame.getActiveTable().getFolderPanel();
        folderPanel.setTreeVisible(!folderPanel.isTreeVisible());
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
            return new ToggleTreeAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "ToggleTree";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.VIEW;
        }

        public KeyStroke getDefaultAltKeyStroke()
        {
            return null;
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK);
        }
    }
}
