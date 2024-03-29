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
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Abstract class for components that display tabs.
 *
 * @author Arik Hadas
 */
public abstract class TabsViewer<T extends Tab> extends JComponent
{

    /**
     * Collection of the displayed tabs
     */
    private TabsCollection<T> tabs;

    public TabsViewer(JComponent component, TabsCollection<T> tabs)
    {
        this.tabs = tabs;

        setLayout(new GridLayout(1, 1));
        add(component);
    }

    public void addChangeListener(ChangeListener listener)
    {
    }

    public void removeChangeListener(ChangeListener listener)
    {
    }

    /***************
     * Tabs Actions
     ***************/

    public abstract void add(T tab);

    public abstract void add(T tab, int index);

    public abstract void update(T tab, int index);

    public abstract int getSelectedTabIndex();

    public abstract void setSelectedTabIndex(int index);

    public abstract T removeCurrentTab();

    public abstract void removeDuplicateTabs();

    public abstract void removeOtherTabs();

    public abstract void removeTab(Component header);

    public abstract void removeTab(int index);

    public void nextTab()
    {
        setSelectedTabIndex((getSelectedTabIndex() + 1) % tabs.count());
    }

    public void previousTab()
    {
        int numOfTabs = tabs.count();
        setSelectedTabIndex((getSelectedTabIndex() - 1 + numOfTabs) % numOfTabs);
    }
}
