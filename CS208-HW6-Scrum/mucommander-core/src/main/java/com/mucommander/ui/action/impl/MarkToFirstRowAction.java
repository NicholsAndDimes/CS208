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

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Marks/unmarks files in the active FileTable, from the currently selected row to the first row (inclusive).
 * The first row will also become the currently selected row.
 *
 * <p>The currently selected row's marked state determines whether the rows will be marked or unmarked : if the selected
 * row is marked, the rows will be unmarked and vice-versa.
 *
 * @author Maxence Bernard
 */
public class MarkToFirstRowAction extends MarkBackwardAction
{

    public MarkToFirstRowAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    protected int getRowDecrement()
    {
        return mainFrame.getActiveTable().getSelectedRow();
    }

    @Override
    public ActionDescriptor getDescriptor()
    {
        return new Descriptor();
    }

    public static class Factory implements ActionFactory
    {
        public MuAction createAction(MainFrame mainFrame, Map<String, Object> properties)
<<< This is the medium bug 'stupefied_hertz' (instance 1 of 4) >>>
<<< This is the medium bug 'stupefied_hertz' (instance 2 of 4) >>>
        {
            return new MarkToFirstRowAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "MarkToFirstRow";

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
            return KeyStroke.getKeyStroke(KeyEvent.VK_HOME, KeyEvent.SHIFT_DOWN_MASK);
        }
    }
}
