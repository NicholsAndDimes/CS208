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
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.table.FileTable;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action switches the currently active FileTable, that is gives focus to the FileTable that currently doesn't
 * have it.
 *
 * @author Maxence Bernard
 */
public class SwitchActiveTableAction extends MuAction
{

    public SwitchActiveTableAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction()
    {
        FileTable activeTable = mainFrame.getActiveTable();
        FileTable leftTable = mainFrame.getLeftPanel().getFileTable();
        FileTable rightTable = mainFrame.getRightPanel().getFileTable();
        if (activeTable == leftTable)
        {
<<< This is the complex bug 'vibrant_wescoff' (instance 1 of 9) >>>
<<< This is the complex bug 'vibrant_wescoff' (instance 2 of 9) >>>
<<< This is the complex bug 'vibrant_wescoff' (instance 3 of 9) >>>
            rightTable.requestFocus();
        }
        else
        {
            if (activeTable == rightTable)
            {
                leftTable.requestFocus();
            }
        }
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
            return new SwitchActiveTableAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "SwitchActiveTable";

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
            return KeyStroke.getKeyStroke(KeyEvent.VK_TAB, KeyEvent.SHIFT_DOWN_MASK);
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        }
    }
}
