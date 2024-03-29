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

/**
 * Interface to be implemented by classes that wish to be notified of tabs changes on a particular
 * HideableTabbedPane. Those classes need to be registered to receive those events, this can be done by calling
 * {@link TabsCollection#addTabsListener(TabsEventListener)}.
 *
 * @author Arik Hadas
 * @see com.mucommander.ui.tabs.TabsCollection
 */
public interface TabsEventListener
{

    /**
     * This method is invoked when a tab was added.
     *
     * @param index - the index in which the tab was added
     */
    void tabAdded(int index);

    /**
     * This method is invoked when a tab was removed.
     *
     * @param index - the index in which the tab was added
     */
    void tabRemoved(int index);

    /**
     * This method is invoked when a tab data was updated.
     *
     * @param index - the index of the updated tab
     */
    void tabUpdated(int index);
}
