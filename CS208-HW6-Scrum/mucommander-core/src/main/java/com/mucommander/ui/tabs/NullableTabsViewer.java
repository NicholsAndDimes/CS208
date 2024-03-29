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

package com.mucommander.ui.tabs;

import javax.swing.*;
import java.awt.*;

/**
 * Initial value for TabsDisplay object.
 *
 * @author Arik Hadas
 */
class NullableTabsViewer<T extends Tab> extends TabsViewer<T>
{

    public NullableTabsViewer()
    {
        super(new JLabel(), null);
    }

    @Override
    public void add(T tab)
    {
    }

    @Override
    public void add(T tab, int index)
    {
    }

    @Override
    public void update(T tab, int index)
    {
    }

    @Override
    public void remove(int index)
    {
    }

    @Override
    public int getSelectedTabIndex()
    {
        return 0;
    }

    @Override
    public void setSelectedTabIndex(int index)
    {
    }

    @Override
    public T removeCurrentTab()
    {
        return null;
    }

    @Override
    public void removeOtherTabs()
    {
    }

    @Override
    public void removeTab(Component header)
    {
    }

    @Override
    public void removeDuplicateTabs()
    {
    }

    @Override
    public void removeTab(int index)
    {
    }
}
