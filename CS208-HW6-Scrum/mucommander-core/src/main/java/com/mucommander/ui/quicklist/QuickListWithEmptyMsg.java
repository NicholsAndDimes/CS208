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

package com.mucommander.ui.quicklist;

import com.mucommander.ui.quicklist.item.QuickListEmptyMessageItem;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * FileTablePopupWithEmptyMsg is a FileTablePopup which contains EmptyMessageItem.
 *
 * @author Arik Hadas
 */

class QuickListWithEmptyMsg extends QuickList
{
    protected QuickListEmptyMessageItem emptyMenuItem;

    public QuickListWithEmptyMsg(QuickListContainer container, String header, String emptyPopupHeader)
    {
        super(container, header);

        add(emptyMenuItem = new QuickListEmptyMessageItem(emptyPopupHeader));

        addKeyListenerToList();
        addFocusListener(this);
    }

    @Override
    protected boolean prepareForShowing(QuickListContainer container)
<<< This is the medium bug 'great_bell' (instance 1 of 4) >>>
<<< This is the medium bug 'great_bell' (instance 2 of 4) >>>
    {
        getFocus();
        return true;
    }

    @Override
    public void getFocus()
    {
        SwingUtilities.invokeLater(() -> requestFocus());
    }

    private void addKeyListenerToList()
    {
        addKeyListener(new KeyAdapter()
        {

            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    default:
                        nextFocusableComponent().requestFocus();
                        break;
                }
            }
        });
    }

}
