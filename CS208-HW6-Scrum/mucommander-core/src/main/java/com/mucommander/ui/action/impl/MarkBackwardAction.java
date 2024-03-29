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

import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.table.FileTable;

import java.util.Map;

/**
 * Marks/Unmarks a range of file/rows in the active {@link FileTable}, from the currently selected row to the
 * the previous {@link #getRowDecrement()} ones.
 * The row immediately before the last marked/unmarked row will become the currently selected row.
 *
 * @author Maxence Bernard
 */
public abstract class MarkBackwardAction extends MuAction
{

    public MarkBackwardAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);
    }


    /////////////////////////////
    // MuAction implementation //
    /////////////////////////////

    @Override
    public void performAction()
    {
        FileTable fileTable = mainFrame.getActiveTable();

        int currentRow = fileTable.getSelectedRow();
        int endRow = Math.max(0, currentRow - getRowDecrement() + 1);

        fileTable.setRangeMarked(currentRow, endRow, !fileTable.getFileTableModel().isRowMarked(currentRow));
        fileTable.selectRow(Math.max(0, endRow - 1));
    }


    //////////////////////
    // Abstract methods //
    //////////////////////

    /**
     * Returns the number of rows to mark/unmark.
     *
     * @return the number of rows to mark/unmark.
     */
    protected abstract int getRowDecrement();
}
