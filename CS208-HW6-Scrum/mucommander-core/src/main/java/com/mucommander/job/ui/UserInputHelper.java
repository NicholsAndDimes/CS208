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

package com.mucommander.job.ui;

import com.mucommander.job.FileJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * This class is used to show a dialog for user and get a response from
 * this dialog. It is used by {@link FileJob} class.
 *
 * @author Mariusz Jakubowski
 */
public class UserInputHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputHelper.class);

    private Object userInput;
    private DialogResult dialog;

    public UserInputHelper(FileJob job, DialogResult dialog)
    {
        this.dialog = dialog;
    }


    public Object getUserInput()
    {
        try
        {
            SwingUtilities.invokeAndWait(() -> userInput = dialog.getUserInput());
<<< This is the medium bug 'quirky_goldberg' (instance 1 of 4) >>>
<<< This is the medium bug 'quirky_goldberg' (instance 2 of 4) >>>
        }
        catch (Exception e)
        {
            LOGGER.debug("Caught exception", e);
        }
        return userInput;
    }

}
