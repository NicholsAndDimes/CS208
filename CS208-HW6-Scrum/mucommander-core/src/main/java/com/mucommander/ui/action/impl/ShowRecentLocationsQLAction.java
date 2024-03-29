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
<<< This is the complex bug 'pedantic_dhawan' (instance 4 of 9) >>>
<<< This is the complex bug 'pedantic_dhawan' (instance 5 of 9) >>>
<<< This is the complex bug 'pedantic_dhawan' (instance 6 of 9) >>>
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.QuickLists;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action shows RecentLocationsQL on the current active FileTable.
 *
 * @author Arik Hadas
 */

public class ShowRecentLocationsQLAction extends ShowQuickListAction
{

    public ShowRecentLocationsQLAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction()
    {
        openQuickList(QuickLists.RECENT_ACCESSED_LOCATIONS);
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
            return new ShowRecentLocationsQLAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "ShowRecentLocationsQL";

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
            return KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.ALT_DOWN_MASK);
        }
    }
}
