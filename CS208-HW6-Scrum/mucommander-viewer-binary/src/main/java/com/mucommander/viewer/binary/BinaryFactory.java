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
package com.mucommander.viewer.binary;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.ui.viewer.FileViewer;
import com.mucommander.ui.viewer.ViewerFactory;

/**
 * <code>BinaryFactory</code> implementation for creating binary viewers.
 *
 * @author Miroslav Hajda
 */
public class BinaryFactory implements ViewerFactory
{

    public BinaryFactory()
    {
    }

    @Override
    public boolean canViewFile(AbstractFile file)
    {
        // Do not allow directories
        if (file.isDirectory())
        {
            return false;
        }

        return true;
    }

    @Override
    public FileViewer createFileViewer()
    {
        return new BinaryViewer();
    }
}
