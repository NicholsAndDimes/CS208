package com.mucommander.commons.file.archive.sevenzip.provider.SevenZip.Compression.RangeCoder;


public class BitModel
{

    public static final int kTopMask = ~((1 << 24) - 1);
    public static final int kNumBitModelTotalBits = 11;
    public static final int kBitModelTotal = (1 << kNumBitModelTotalBits);

    int numMoveBits;

    int Prob;

    public BitModel(int num)
    {
        numMoveBits = num;
    }

    public void Init()
    {
        Prob = kBitModelTotal / 2;
    }

}
