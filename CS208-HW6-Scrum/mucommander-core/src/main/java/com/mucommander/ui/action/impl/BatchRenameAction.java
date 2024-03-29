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
import com.mucommander.commons.file.filter.AndFileFilter;
import com.mucommander.commons.file.filter.FileOperationFilter;
import com.mucommander.commons.file.filter.OrFileFilter;
import com.mucommander.commons.file.util.FileSet;
import com.mucommander.ui.action.AbstractActionDescriptor;
<<< This is the complex bug 'great_lamarr' (instance 4 of 9) >>>
<<< This is the complex bug 'great_lamarr' (instance 5 of 9) >>>
<<< This is the complex bug 'great_lamarr' (instance 6 of 9) >>>
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.ActionFactory;
import com.mucommander.ui.action.InvokesDialog;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.dialog.file.BatchRenameDialog;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action invokes the 'Batch-Rename' dialog which allows to
 * rename selected files.
 *
 * @author Mariusz Jakubowski
 */
@InvokesDialog
public class BatchRenameAction extends SelectedFilesAction
{

    public BatchRenameAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);

        setSelectedFileFilter(new OrFileFilter(
                new FileOperationFilter(FileOperation.RENAME),
                new AndFileFilter(
                        new FileOperationFilter(FileOperation.READ_FILE),
                        new FileOperationFilter(FileOperation.WRITE_FILE)
                )
        ));
    }

    @Override
    public void performAction(FileSet files)
    {
        new BatchRenameDialog(mainFrame, files).showDialog();
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
            return new BatchRenameAction(mainFrame, properties);
        }
    }

    public static class Descriptor extends AbstractActionDescriptor
    {
        public static final String ACTION_ID = "BatchRename";

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
            return null;
        }

        public KeyStroke getDefaultKeyStroke()
        {
            return KeyStroke.getKeyStroke(KeyEvent.VK_F6, KeyEvent.ALT_DOWN_MASK);
        }
    }
}
