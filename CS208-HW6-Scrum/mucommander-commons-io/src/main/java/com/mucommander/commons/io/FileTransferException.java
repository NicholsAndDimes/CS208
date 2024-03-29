/*
 * This file is part of muCommander, http://www.mucommander.com
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.commons.io;

import java.io.IOException;

/**
 * FileTransferException is an IOException which can be thrown to indicate a file transfer error.
 * {@link #getReason() getReason()} returns the reason why the transfer failed.
 *
 * @author Maxence Bernard
 */
public class FileTransferException extends IOException
{

    protected FileTransferError reason;

    private long bytesWritten;


    public FileTransferException(FileTransferError reason)
    {
        this.reason = reason;
    }

    public FileTransferException(FileTransferError reason, long bytesWritten)
    {
        this.reason = reason;
        this.bytesWritten = bytesWritten;
    }

    public FileTransferError getReason()
    {
        return reason;
    }

    public long getBytesWritten()
    {
        return bytesWritten;
    }

    public String toString()
    {
        return super.toString() + " reason=" + reason;
    }

}
