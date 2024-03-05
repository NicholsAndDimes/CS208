package com.mucommander.commons.file.archive.sevenzip.provider.SevenZip.Archive.SevenZip;

import com.mucommander.commons.file.archive.sevenzip.provider.Common.BoolVector;


class ExtractFolderInfo
{
    public int FileIndex;
    public int FolderIndex;
    public BoolVector ExtractStatuses = new BoolVector();
    public long UnPackSize;

    public ExtractFolderInfo(
            int fileIndex, int folderIndex)  // CNum fileIndex, CNum folderIndex
    {
        FileIndex = fileIndex;
        FolderIndex = folderIndex;
        UnPackSize = 0;

        if (fileIndex != InArchive.kNumNoIndex)
        {
            ExtractStatuses.Reserve(1);
            ExtractStatuses.add(true);
        }
    }
}
