package com.mucommander.commons.file.archive.sevenzip.provider.SevenZip;

public interface ICompressCoder
{
    int Code(
            java.io.InputStream inStream, // , ISequentialInStream
            java.io.OutputStream outStream, // ISequentialOutStream
            long outSize, ICompressProgressInfo progress) throws java.io.IOException;

}
