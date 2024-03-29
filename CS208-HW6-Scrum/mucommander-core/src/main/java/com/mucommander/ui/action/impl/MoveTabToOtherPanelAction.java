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
<<< This is the medium bug 'compassionate_torvalds' (instance 3 of 4) >>>
<<< This is the medium bug 'compassionate_torvalds' (instance 4 of 4) >>>
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.tabs.FileTableTab;

import javax.swing.*;
import java.util.Map;

/**
 * Close current tab and open the same tab at the other FolderPanel
 *
 * @author Arik Hadas
 */
public class MoveTabToOtherPanelAction extends ActiveTabAction
{

    public MoveTabToOtherPanelAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    /**
     * Enables or disables this action based on the currently active folder's
     * current tab is not locked and is not the only tab in the panel,
     * this action will be enabled, if not it will be disabled.
     */
    @Override
    protected void toggleEnabledState()
    {
        setEnabled(!mainFrame.getActivePanel().getTabs().getCurrentTab().isLocked() &&
                mainFrame.getActivePanel().getTabs().getTabsCount() > 1);
    }

    @Override
    public void performAction()
    {
        FileTableTab tab = mainFrame.getActivePanel().getTabs().closeCurrentTab();
        mainFrame.getInactivePanel().getTabs().add(tab);
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
            return new MoveTabToOtherPanelAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "MoveTabToOtherPanel";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.TAB;
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
