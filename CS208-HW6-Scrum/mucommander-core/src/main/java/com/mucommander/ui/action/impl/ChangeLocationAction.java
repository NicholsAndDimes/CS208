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
<<< This is the medium bug 'pensive_elgamal' (instance 3 of 4) >>>
<<< This is the medium bug 'pensive_elgamal' (instance 4 of 4) >>>
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action transfers focus to the location field of the currently active FolderPanel to edit or type in
 * a new folder location.
 *
 * @author Maxence Bernard
 */
public class ChangeLocationAction extends ActiveTabAction
{

    public ChangeLocationAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    /**
     * Enables or disables this action based on the currently active folder's
     * current tab is not locked, this action will be enabled,
     * if not it will be disabled.
     */
    @Override
    protected void toggleEnabledState()
    {
        setEnabled(!mainFrame.getActivePanel().getTabs().getCurrentTab().isLocked());
    }

    @Override
    public void performAction()
    {
        mainFrame.getActivePanel().getLocationTextField().requestFocus();
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
            return new ChangeLocationAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "ChangeLocation";

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
            return KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
        }
    }
}
