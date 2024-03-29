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


package com.mucommander.commons.file.protocol.hadoop;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.FileURL;
import com.mucommander.commons.file.protocol.ProtocolProvider;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.util.Map;

/**
 * A file protocol provider for the Amazon S3 protocol, provided by the Hadoop virtual filesystem.
 *
 * <p>Even though it is working for the most part, it is flawed in several ways and should not be used.
 * See the {@link com.mucommander.commons.file.protocol.s3} package for a better implementation of the Amazon S3 protocol.</p>
 *
 * @deprecated
 * @author Maxence Bernard
 */
public class S3ProtocolProvider implements ProtocolProvider
{

    public AbstractFile getFile(FileURL url, Map<String, Object> instantiationParams) throws IOException
    {
        return instantiationParams.isEmpty()
                ? new S3File(url)
                : new S3File(url, (FileSystem) instantiationParams.get("file-system"), (FileStatus) instantiationParams.get("file-status"));
    }
}
