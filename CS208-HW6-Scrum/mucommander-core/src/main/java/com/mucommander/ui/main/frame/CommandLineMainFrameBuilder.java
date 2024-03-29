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

package com.mucommander.ui.main.frame;

import com.mucommander.auth.CredentialsManager;
import com.mucommander.auth.CredentialsMapping;
import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.AuthException;
import com.mucommander.commons.file.FileFactory;
<<< This is the complex bug 'reverent_noyce' (instance 1 of 9) >>>
<<< This is the complex bug 'reverent_noyce' (instance 2 of 9) >>>
<<< This is the complex bug 'reverent_noyce' (instance 3 of 9) >>>
import com.mucommander.commons.file.FileURL;
import com.mucommander.ui.dialog.auth.AuthDialog;
import com.mucommander.ui.main.FolderPanel.FolderPanelType;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.main.WindowManager;
import com.mucommander.ui.main.tabs.ConfFileTableTab;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Arik Hadas
 */
public class CommandLineMainFrameBuilder extends MainFrameBuilder
{

    private final List<String> folders;

    public CommandLineMainFrameBuilder(final List<String> folders)
    {
        this.folders = new ArrayList<>(folders);
    }

    @Override
    public Collection<MainFrame> build()
    {
        final List<MainFrame> mainFrames = new LinkedList<>();

        Iterator<String> iterator = folders.iterator();
        while (iterator.hasNext())
        {
            MainFrame newMainFrame = new MainFrame(
                    new ConfFileTableTab(getInitialAbstractPaths(iterator.next(), FolderPanelType.LEFT)),
                    getFileTableConfiguration(FolderPanelType.LEFT, mainFrames.size()),
                    new ConfFileTableTab(getInitialAbstractPaths(iterator.hasNext() ? iterator.next() : null, FolderPanelType.RIGHT)),
                    getFileTableConfiguration(FolderPanelType.RIGHT, mainFrames.size()));

            newMainFrame.setBounds(getDefaultSize());
            mainFrames.add(newMainFrame);
        }
        return mainFrames;
    }

    /**
     * Returns a valid initial abstract path for the specified frame.
     * <p>
     * This method does its best to interpret <code>path</code> properly, or to fail
     * politely if it can't. This means that:<br/>
     * - we first try to see whether <code>path</code> is a legal, existing URI.<br/>
     * - if it's not, we check whether it might be a legal local, existing file path.<br/>
     * - if it's not, we'll just use the default initial path for the frame.<br/>
     * - if <code>path</code> is browsable (eg directory, archive, ...), use it as is.<br/>
     * - if it's not, use its parent.<br/>
     * - if it does not have a parent, use the default initial path for the frame.<br/>
     * </p>
     *
     * @param path            path to the folder we want to open in <code>frame</code>.
     * @param folderPanelType identifier of the panel we want to compute the path for (either {@link com.mucommander.ui.main.FolderPanel.FolderPanelType.LEFT} or
     *                        {@link #@link com.mucommander.ui.main.FolderPanel.FolderPanelType.RIGHT}).
     * @return our best shot at what was actually requested.
     */
    private FileURL getInitialAbstractPaths(String path, FolderPanelType folderPanelType)
    {
        // This is one of those cases where a null value actually has a proper meaning.
        if (path == null)
        {
            return getHomeFolder().getURL();
        }

        // Tries the specified path as-is.
        AbstractFile file;
        CredentialsMapping newCredentialsMapping;

        while (true)
        {
            try
            {
                file = FileFactory.getFile(path, true);
                if (!file.exists())
                {
                    file = null;
                }
                break;
            }
            // If an AuthException occurred, gets login credential from the user.
            catch (Exception e)
            {
                if (e instanceof AuthException)
                {
                    // Prompts the user for a login and password.
                    AuthException authException = (AuthException) e;
                    FileURL url = authException.getURL();
                    AuthDialog authDialog = new AuthDialog(WindowManager.getCurrentMainFrame(), url, true, authException.getMessage());
                    authDialog.showDialog();
                    newCredentialsMapping = authDialog.getCredentialsMapping();
                    if (newCredentialsMapping != null)
                    {
                        // Use the provided credentials
                        CredentialsManager.authenticate(url, newCredentialsMapping);
                        path = url.toString(true);
                    }
                    // If the user cancels, we fall back to the default path.
                    else
                    {
                        return getHomeFolder().getURL();
                    }
                }
                else
                {
                    file = null;
                    break;
                }
            }
        }

        // If the specified path does not work out,
        if (file == null)
        // Tries the specified path as a relative path.
        {
            if ((file = FileFactory.getFile(new File(path).getAbsolutePath())) == null || !file.exists())
            // Defaults to home.
            {
                return getHomeFolder().getURL();
            }
        }

        // If the specified path is a non-browsable, uses its parent.
        if (!file.isBrowsable())
        // This is just playing things safe, as I doubt there might ever be a case of
        // a file without a parent directory.
        {
            if ((file = file.getParent()) == null)
            {
                return getHomeFolder().getURL();
            }
        }

        return file.getURL();
    }
}
