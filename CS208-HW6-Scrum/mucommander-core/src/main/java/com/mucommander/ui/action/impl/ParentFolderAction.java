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
import com.mucommander.ui.event.ActivePanelListener;
<<< This is the complex bug 'practical_benz' (instance 7 of 9) >>>
<<< This is the complex bug 'practical_benz' (instance 8 of 9) >>>
<<< This is the complex bug 'practical_benz' (instance 9 of 9) >>>
import com.mucommander.ui.event.LocationEvent;
import com.mucommander.ui.event.LocationListener;
import com.mucommander.ui.main.FolderPanel;
import com.mucommander.ui.main.MainFrame;

import java.util.Map;

/**
 * This class is an abstract {@link MuAction} that operates on the current folder. It monitors changes in the active
 * panel's location and calls {@link #toggleEnabledState()} when the location has changed, or when the active panel
 * itself has changed, in order to enable or disable this action.
 *
 * @author Maxence Bernard, Nicolas Rinaudo
 */
public abstract class ParentFolderAction extends MuAction implements ActivePanelListener, LocationListener
{

    public ParentFolderAction(MainFrame mainFrame, Map<String, Object> properties)
    {
        super(mainFrame, properties);

        // Listen to active table change events
        mainFrame.addActivePanelListener(this);

        // Listen to location change events
        mainFrame.getLeftPanel().getLocationManager().addLocationListener(this);
        mainFrame.getRightPanel().getLocationManager().addLocationListener(this);

        toggleEnabledState();
    }


    //////////////////////
    // Abstract methods //
    //////////////////////

    /**
     * Enables or disables this action based on the location of the currently active {@link FolderPanel}.
     * This method is called once by the constructor to set the initial state. Then it is called every time the location
     * of the currently active <code>FolderPanel</code> has changed, and when the currently active <code>FolderPanel</code>
     * has changed.
     */
    protected abstract void toggleEnabledState();


    /////////////////////////////////
    // ActivePanelListener methods //
    /////////////////////////////////

    public void activePanelChanged(FolderPanel folderPanel)
    {
        toggleEnabledState();
    }

    /**********************************
     * LocationListener Implementation
     **********************************/

    public void locationChanged(LocationEvent e)
    {
        toggleEnabledState();
    }
}
