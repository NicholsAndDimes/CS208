/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id: ProcessingInstructionSelector.java,v 1.2 1999/09/25 12:32:36 plehegar Exp $
 */
package org.w3c.css.sac;

/**
 * This simple matches a
 * <a href="http://www.w3.org/TR/REC-xml#sec-pi">processing instruction</a>.
 *
 * @author Philippe Le Hegaret
 * @version $Revision: 1.2 $
 * @see Selector#SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR
 */
public interface ProcessingInstructionSelector extends SimpleSelector
{

    /**
     * Returns the <a href="http://www.w3.org/TR/REC-xml#NT-PITarget">target</a>
     * of the processing instruction.
     */
    public String getTarget();

    /**
     * Returns the character data.
     */
    public String getData();
}
