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


package com.mucommander.commons.file.archive.iso;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.UnsupportedFileOperationException;
import com.mucommander.commons.file.archive.AbstractROArchiveFile;
import com.mucommander.commons.file.archive.ArchiveEntry;
import com.mucommander.commons.file.archive.ArchiveEntryIterator;
import com.mucommander.commons.io.FilterRandomAccessInputStream;
import com.mucommander.commons.io.RandomAccessInputStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * IsoArchiveFile provides read-only access to archives in the ISO and NRG formats.
 *
 * @author Maxence Bernard
 * @see com.mucommander.commons.file.archive.iso.IsoFormatProvider
 */
public class IsoArchiveFile extends AbstractROArchiveFile
{

    public IsoArchiveFile(AbstractFile file)
    {
        super(file);
    }

    //////////////////////////////////////////
    // AbstractROArchiveFile implementation //
    //////////////////////////////////////////

    @Override
    public ArchiveEntryIterator getEntryIterator() throws IOException, UnsupportedFileOperationException
    {
        RandomAccessInputStream rais = getRandomAccessInputStream();

        return new IsoEntryIterator(IsoParser.getEntries(this, rais).iterator(), rais);
    }

    @Override
    public InputStream getEntryInputStream(ArchiveEntry entry, ArchiveEntryIterator entryIterator) throws IOException, UnsupportedFileOperationException
    {
        // Cast the entry before creating the stream, in case it fails
        IsoArchiveEntry isoEntry = (IsoArchiveEntry) entry;

        RandomAccessInputStream rais;
        // If a IsoEntryIterator is specified, reuse the iterator's stream
        if (entryIterator != null && entryIterator instanceof IsoEntryIterator)
        {
            // Override close() as a no-op so that the stream is re-used from one entry to another -- the stream will
            // be closed when the iterator is closed.
            rais = new FilterRandomAccessInputStream(((IsoEntryIterator) entryIterator).getRandomAccessInputStream())
            {
                @Override
                public void close() throws IOException
                {
                    // No-op
                }
            };
        }
        else
        {
            rais = getRandomAccessInputStream();
        }

        return new IsoEntryInputStream(rais, isoEntry);
    }
}
