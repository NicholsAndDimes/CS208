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

package com.mucommander.conf;

import java.io.FileNotFoundException;

/**
 * @author Arik Hadas
 */
public class MuSnapshotFile extends MuConfigurationFile
{

    private static final String DEFAULT_SNAPSHOT_FILE_NAME = "snapshot.xml";

    public static MuSnapshotFile getSnapshotFile()
    {
        try
        {
            return new MuSnapshotFile();
        }
        catch (FileNotFoundException e)
        {
            // Not possible exception??
            return null;
        }
    }

    private MuSnapshotFile() throws FileNotFoundException
    {
        super(null, DEFAULT_SNAPSHOT_FILE_NAME);
    }
}
