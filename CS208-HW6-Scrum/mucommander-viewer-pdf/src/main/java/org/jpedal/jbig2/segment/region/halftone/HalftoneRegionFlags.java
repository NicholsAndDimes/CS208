/**
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 * <p>
 * Project Info:  http://www.jpedal.org
 * (C) Copyright 1997-2008, IDRsolutions and Contributors.
 * Main Developer: Simon Barnett
 * <p>
 * This file is part of JPedal
 * <p>
 * Copyright (c) 2008, IDRsolutions
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the IDRsolutions nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY IDRsolutions ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL IDRsolutions BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * Other JBIG2 image decoding implementations include
 * jbig2dec (http://jbig2dec.sourceforge.net/)
 * xpdf (http://www.foolabs.com/xpdf/)
 * <p>
 * The final draft JBIG2 specification can be found at http://www.jpeg.org/public/fcd14492.pdf
 * <p>
 * All three of the above resources were used in the writing of this software, with methodologies,
 * processes and inspiration taken from all three.
 * <p>
 * ---------------
 * HalftoneRegionFlags.java
 * ---------------
 */
package org.jpedal.jbig2.segment.region.halftone;

import org.jpedal.jbig2.decoders.JBIG2StreamDecoder;
import org.jpedal.jbig2.segment.Flags;

public class HalftoneRegionFlags extends Flags
{

    public static String H_MMR = "H_MMR";
    public static String H_TEMPLATE = "H_TEMPLATE";
    public static String H_ENABLE_SKIP = "H_ENABLE_SKIP";
    public static String H_COMB_OP = "H_COMB_OP";
    public static String H_DEF_PIXEL = "H_DEF_PIXEL";

    public void setFlags(int flagsAsInt)
    {
        this.flagsAsInt = flagsAsInt;

        /** extract H_MMR */
        flags.put(H_MMR, new Integer(flagsAsInt & 1));

        /** extract H_TEMPLATE */
        flags.put(H_TEMPLATE, new Integer((flagsAsInt >> 1) & 3));

        /** extract H_ENABLE_SKIP */
        flags.put(H_ENABLE_SKIP, new Integer((flagsAsInt >> 3) & 1));

        /** extract H_COMB_OP */
        flags.put(H_COMB_OP, new Integer((flagsAsInt >> 4) & 7));

        /** extract H_DEF_PIXEL */
        flags.put(H_DEF_PIXEL, new Integer((flagsAsInt >> 7) & 1));


        if (JBIG2StreamDecoder.debug)
        {
            System.out.println(flags);
        }
    }
}
