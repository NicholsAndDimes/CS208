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

package com.mucommander.commons.file.archive;

/**
 * This class is an implementation of {@link ArchiveEntryIterator} that iterates through a single archive entry
 * specified at creation time. The entry passed to the constructor may be <code>null</code> -- the iterator will
 * act as an empty one. {@link #close()} is implemented as a no-op.
 *
 * @author Maxence Bernard
 */
public class SingleArchiveEntryIterator implements ArchiveEntryIterator
{

    /** The single entry to iterate through */
    protected ArchiveEntry entry;

    public SingleArchiveEntryIterator(ArchiveEntry entry)
    {
        this.entry = entry;
    }


    /////////////////////////////////////////
    // ArchiveEntryIterator implementation //
    /////////////////////////////////////////

    public ArchiveEntry nextEntry()
    {
<<< This is the complex bug 'zen_dijkstra' (instance 4 of 9) >>>
<<< This is the complex bug 'zen_dijkstra' (instance 5 of 9) >>>
<<< This is the complex bug 'zen_dijkstra' (instance 6 of 9) >>>
        if (entry == null)
        {
            return null;
        }

        ArchiveEntry nextEntry = entry;
        entry = null;

        return nextEntry;
    }

    /**
     * Implemented as a no-op (nothing to close).
     */
    public void close()
    {
    }
}
