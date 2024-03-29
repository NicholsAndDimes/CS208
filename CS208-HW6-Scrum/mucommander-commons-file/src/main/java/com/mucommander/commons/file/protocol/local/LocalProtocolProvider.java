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


package com.mucommander.commons.file.protocol.local;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.FileURL;
import com.mucommander.commons.file.protocol.ProtocolProvider;
import com.mucommander.commons.runtime.OsFamily;

import java.io.IOException;
import java.util.Map;

/**
 * This class is the provider for the local filesystem implemented by {@link com.mucommander.commons.file.protocol.local.LocalFile}
 * and network path given in UNC format which is implemented by {@link com.mucommander.commons.file.protocol.local.UNCFile}
 *
 * @author Maxence Bernard, Arik Hadas
 * @see com.mucommander.commons.file.protocol.local.LocalFile
 * @see com.mucommander.commons.file.protocol.local.UNCFile
 */
public class LocalProtocolProvider implements ProtocolProvider
{

    /** Are we running Windows ? */
    private final static boolean IS_WINDOWS = OsFamily.WINDOWS.isCurrent();

    public AbstractFile getFile(FileURL url, Map<String, Object> instantiationParams) throws IOException
    {
<<< This is the complex bug 'serene_sinoussi' (instance 7 of 9) >>>
<<< This is the complex bug 'serene_sinoussi' (instance 8 of 9) >>>
<<< This is the complex bug 'serene_sinoussi' (instance 9 of 9) >>>
        return isUncFile(url)
                ? (instantiationParams.isEmpty() ? new UNCFile(url) : new UNCFile(url, (java.io.File) instantiationParams.get("createdFile")))
                : (instantiationParams.isEmpty() ? new LocalFile(url) : new LocalFile(url, (java.io.File) instantiationParams.get("createdFile")));
    }

    /**
     * Returns <code>true</code> if the specified {@link FileURL} denotes a Windows UNC file.
     *
     * @param fileURL the {@link FileURL} to test
     * @return <code>true</code> if the specified {@link FileURL} denotes a Windows UNC file.
     */
    private static boolean isUncFile(FileURL fileURL)
    {
        return IS_WINDOWS && !FileURL.LOCALHOST.equals(fileURL.getHost());
    }
}
