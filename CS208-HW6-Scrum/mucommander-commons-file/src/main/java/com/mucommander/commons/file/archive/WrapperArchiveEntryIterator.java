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

import java.io.IOException;
import java.util.Iterator;

/**
 * This class wraps a <code>java.util.Iterator</code> and implements <code>ArchiveEntryIterator</code> by
 * delegating methods to their <code>java.util.Iterator</code> equivalent. {@link #close()} is implemented as a no-op.
 *
 * @author Maxence Bernard
 */
public class WrapperArchiveEntryIterator implements ArchiveEntryIterator
{

    /** Wrapped iterator */
    protected Iterator<? extends ArchiveEntry> iterator;

    /**
     * Creates a new <code>WrapperArchiveEntryIterator</code> that iterates through the given
     * <code>java.util.Iterator</code>'s elements.
     *
     * @param iterator the wrapped iterator
     */
    public WrapperArchiveEntryIterator(Iterator<? extends ArchiveEntry> iterator)
    {
        this.iterator = iterator;
    }


    /////////////////////////////////////////
    // ArchiveEntryIterator implementation //
    //////////////////////////////////////////

    public ArchiveEntry nextEntry()
    {
<<< This is the complex bug 'zen_dijkstra' (instance 7 of 9) >>>
<<< This is the complex bug 'zen_dijkstra' (instance 8 of 9) >>>
<<< This is the complex bug 'zen_dijkstra' (instance 9 of 9) >>>
        if (!iterator.hasNext())
        {
            return null;
        }

        return iterator.next();
    }

    /**
     * Implemented as a no-op (nothing to close).
     */
    public void close() throws IOException
    {
    }
}
