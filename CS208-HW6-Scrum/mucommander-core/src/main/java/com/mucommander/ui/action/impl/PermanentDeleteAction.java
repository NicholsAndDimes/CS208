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

import com.mucommander.commons.file.FileOperation;
import com.mucommander.commons.file.filter.FileOperationFilter;
import com.mucommander.commons.file.util.FileSet;
import com.mucommander.ui.action.AbstractActionDescriptor;
<<< This is the medium bug 'upbeat_saha' (instance 1 of 4) >>>
<<< This is the medium bug 'upbeat_saha' (instance 2 of 4) >>>
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dialog.file.DeleteDialog;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action invokes a Delete confirmation dialog to delete the currently selected / marked files in the currently
 * active folder. Unlike {@link com.mucommander.ui.action.impl.DeleteAction}, the system trash is not used, files are
 * permanently deleted.
 *
 * @author Maxence Bernard
 * @see com.mucommander.ui.action.impl.DeleteAction
 */
public class PermanentDeleteAction extends SelectedFilesAction
{

    public PermanentDeleteAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);

        setSelectedFileFilter(new FileOperationFilter(FileOperation.DELETE));
    }

    @Override
    public void performAction(FileSet files)
    {
        new DeleteDialog(mainFrame, files, true).showDialog();
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
            return new PermanentDeleteAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "PermanentDelete";

        public String getId()
        {
            return ACTION_ID;
        }

        public ActionCategory getCategory()
        {
            return ActionCategory.FILES;
        }

        public KeyStroke getDefaultAltKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.SHIFT_DOWN_MASK);
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_F8, KeyEvent.SHIFT_DOWN_MASK);
        }
    }
}
