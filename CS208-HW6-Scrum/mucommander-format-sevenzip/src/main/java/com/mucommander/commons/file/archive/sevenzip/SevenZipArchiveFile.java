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

package com.mucommander.commons.file.archive.sevenzip;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.file.UnsupportedFileOperationException;
import com.mucommander.commons.file.archive.AbstractROArchiveFile;
import com.mucommander.commons.file.archive.ArchiveEntry;
import com.mucommander.commons.file.archive.ArchiveEntryIterator;
import com.mucommander.commons.file.archive.WrapperArchiveEntryIterator;
import com.mucommander.commons.file.archive.sevenzip.provider.SevenZip.Archive.IInArchive;
import com.mucommander.commons.file.archive.sevenzip.provider.SevenZip.Archive.SevenZip.Handler;
import com.mucommander.commons.file.archive.sevenzip.provider.SevenZip.Archive.SevenZipEntry;
import com.mucommander.commons.util.CircularByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;


/**
 * SevenZipArchiveFile provides read access to archives in the 7zip format.
 *
 * @author Arik Hadas, Maxence Bernard
 */
public class SevenZipArchiveFile extends AbstractROArchiveFile
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SevenZipArchiveFile.class);

    private IInArchive sevenZipFile;

    public SevenZipArchiveFile(AbstractFile file) throws IOException
    {
        super(file);
    }

    private IInArchive openSevenZipFile() throws IOException
    {
        if (sevenZipFile == null)
        {
            MuRandomAccessFile in = new MuRandomAccessFile(file);
            sevenZipFile = new Handler();
            if (sevenZipFile.Open(in) != 0)
            {
                throw new IOException("Error while opening 7zip archive " + file.getAbsolutePath());
            }
        }
        return sevenZipFile;
    }

    /**
     * Creates and return an {@link ArchiveEntry()} whose attributes are fetched from the given {@link SevenZipEntry}
     *
     * @param entry the object that serves to initialize the attributes of the returned ArchiveEntry
     * @return an ArchiveEntry whose attributes are fetched from the given SevenZipEntry
     */
    private ArchiveEntry createArchiveEntry(SevenZipEntry entry)
    {
        return new ArchiveEntry(entry.getName(), entry.isDirectory(), entry.getTime(), entry.getSize(), true);
    }


    //////////////////////////////////////////
    // AbstractROArchiveFile implementation //
    //////////////////////////////////////////

    @Override
    public InputStream getEntryInputStream(final ArchiveEntry entry, ArchiveEntryIterator entryIterator) throws IOException, UnsupportedFileOperationException
    {
        final IInArchive sevenZipFile = openSevenZipFile();

        final int arrays[] = new int[1];
        for (int i = 0; i < sevenZipFile.size(); i++)
        {
            if (entry.getPath().equals(sevenZipFile.getEntry(i).getName()))
            {
                System.out.println("entry.getPath = " + entry.getPath() + ", sevenZipFile.getEntry(i).getName() " + sevenZipFile.getEntry(i).getName());
                arrays[0] = i;
                break;
            }
        }

        final CircularByteBuffer cbb = new CircularByteBuffer(CircularByteBuffer.INFINITE_SIZE);
        new Thread(
                new Runnable()
                {
                    public void run()
                    {

                        MuArchiveExtractCallback extractCallbackSpec = new MuArchiveExtractCallback(cbb.getOutputStream(), entry.getPath());
                        extractCallbackSpec.Init(sevenZipFile);
                        try
                        {
                            sevenZipFile.Extract(arrays, 1, IInArchive.NExtract_NAskMode_kExtract, extractCallbackSpec);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

        return cbb.getInputStream();
    }

    @Override
    public ArchiveEntryIterator getEntryIterator() throws IOException
    {
        final IInArchive sevenZipFile = openSevenZipFile();

        try
        {
            int nbEntries = sevenZipFile.size();
            Vector<ArchiveEntry> entries = new Vector<ArchiveEntry>();
            for (int i = 0; i < nbEntries; i++)
            {
                entries.add(createArchiveEntry(sevenZipFile.getEntry(i)));
            }

            return new WrapperArchiveEntryIterator(entries.iterator());
        }
        finally
        {
        }
    }
}
