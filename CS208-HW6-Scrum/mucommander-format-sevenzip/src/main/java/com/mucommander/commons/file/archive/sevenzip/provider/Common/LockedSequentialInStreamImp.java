package com.mucommander.commons.file.archive.sevenzip.provider.Common;

public class LockedSequentialInStreamImp extends java.io.InputStream
{
    LockedInStream _lockedInStream;
    long _pos;

    public LockedSequentialInStreamImp()
    {
    }

    public void Init(LockedInStream lockedInStream, long startPos)
    {
        _lockedInStream = lockedInStream;
        _pos = startPos;
    }

    public int read() throws java.io.IOException
    {
        throw new java.io.IOException("LockedSequentialInStreamImp : read() not implemented");
    }

    public int read(byte[] data, int off, int size) throws java.io.IOException
    {
        int realProcessedSize = _lockedInStream.read(_pos, data, off, size);
        if (realProcessedSize == -1)
        {
            return -1; // EOF
        }

        _pos += realProcessedSize;

        return realProcessedSize;
    }

}
