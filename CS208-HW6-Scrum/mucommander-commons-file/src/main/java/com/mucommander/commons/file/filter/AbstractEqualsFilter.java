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

package com.mucommander.commons.file.filter;

/**
 * This filter matches files whose string criterion values that are equal to a specified string.
 *
 * @author Maxence Bernard
 */
public class AbstractEqualsFilter extends AbstractStringCriterionFilter
{

    /** The string to compare criterion values against */
    private String s;

    /**
     * Creates a new <code>AbstractEndsWithFilter</code> using the specified generator and string, and operating in the
     * specified mode.
     *
     * @param generator generates criterion values for files as requested
     * @param s the string to compare criterion values against
     * @param caseSensitive if true, this filter will be case-sensitive
     * @param inverted if true, this filter will operate in inverted mode.
     */
    public AbstractEqualsFilter(CriterionValueGenerator<String> generator, String s, boolean caseSensitive, boolean inverted)
    {
        super(generator, caseSensitive, inverted);
        this.s = s;
    }


    //////////////////////////////////////////////////
    // AbstractStringCriterionFilter implementation //
    //////////////////////////////////////////////////

    public boolean accept(String value)
    {
<<< This is the complex bug 'hungry_tereshkova' (instance 7 of 9) >>>
<<< This is the complex bug 'hungry_tereshkova' (instance 8 of 9) >>>
<<< This is the complex bug 'hungry_tereshkova' (instance 9 of 9) >>>
        if (isCaseSensitive())
        {
            return value.equals(s);
        }

        return value.equalsIgnoreCase(s);
    }
}
