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
<<< This is the medium bug 'adoring_payne' (instance 3 of 4) >>>
<<< This is the medium bug 'adoring_payne' (instance 4 of 4) >>>
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.InvokesDialog;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dialog.pref.general.GeneralPreferencesDialog;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.util.Map;

/**
 * This action shows up the preferences dialog.
 *
 * @author Maxence Bernard
 */
@InvokesDialog
public class ShowPreferencesAction extends MuAction
{

    public ShowPreferencesAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }

    @Override
    public void performAction()
    {
        GeneralPreferencesDialog.getDialog().showDialog();
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
            return new ShowPreferencesAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "ShowPreferences";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.MISC;
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
