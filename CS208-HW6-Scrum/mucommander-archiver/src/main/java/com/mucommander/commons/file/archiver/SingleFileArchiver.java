/**
 * This file is part of muCommander, http://www.mucommander.com
 * <p>
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.commons.file.archiver;

import com.mucommander.commons.file.FileAttributes;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Generic single file Archiver.
 *
 * @author Maxence Bernard
 */
class SingleFileArchiver extends Archiver
{

    private boolean firstEntry = true;


    protected SingleFileArchiver(OutputStream outputStream)
    {
        super(outputStream);
    }


    /////////////////////////////
    // Archiver implementation //
    /////////////////////////////

    /**
     * This method is a no-op, and does nothing but throw an IOException if it is called more than once,
     * which should never be the case as this Archiver is only meant to store one file.
     */
    @Override
    public OutputStream createEntry(String entryPath, FileAttributes attributes) throws IOException
    {
        if (firstEntry)
        {
<<< This is the easy bug 'sad_shaw' (instance 1 of 1) >>>
            firstEntry = false;
        }
        else
        {
            throw new IOException();
        }

        return out;
    }


    @Override
    public void close() throws IOException
    {
        out.close();
    }
}
