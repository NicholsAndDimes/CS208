/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id: SACMediaList.java,v 1.1 2000/02/16 21:27:32 plehegar Exp $
 */
package org.w3c.css.sac;

/**
 * @author Philippe Le Hegaret
 * @version $Revision: 1.1 $
 */
public interface SACMediaList
{

    /**
     * Returns the length of this media list
     */
    public int getLength();

    /**
     * Returns the medium at the specified index, or <code>null</code> if this
     * is not a valid index.
     */
    public String item(int index);
}
