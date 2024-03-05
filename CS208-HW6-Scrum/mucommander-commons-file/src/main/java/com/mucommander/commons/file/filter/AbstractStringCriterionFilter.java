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
 * <code>AbstractCriterionFilter</code> implements the bulk of the {@link StringCriterionFilter} interface, matching
 * files based on the criteria values generated by a given {@link CriterionValueGenerator}. The only method left for
 * subclasses to implement is {@link #accept(Object)}.
 *
 * @see AbstractPathFilter
 * @see AbstractFilenameFilter
 * @author Maxence Bernard
 */
public abstract class AbstractStringCriterionFilter extends AbstractCriterionFilter<String> implements StringCriterionFilter
{

    /** True if this StringCriterionFilter is case-sensitive. */
    private boolean caseSensitive;

    /**
     * Creates a new case-insensitive <code>AbstractStringCriterionFilter</code> operating in non-inverted mode.
     *
     * @param generator generates criterion values for files as requested
     */
    public AbstractStringCriterionFilter(CriterionValueGenerator<String> generator)
    {
        this(generator, false, false);
    }

    /**
     * Creates a new <code>AbstractStringCriterionFilter</code> operating in non-inverted mode.
     *
     * @param generator generates criterion values for files as requested
     * @param caseSensitive if true, this FilePathFilter will be case-sensitive
     */
    public AbstractStringCriterionFilter(CriterionValueGenerator<String> generator, boolean caseSensitive)
    {
        this(generator, caseSensitive, false);
    }

    /**
     * Creates a new <code>AbstractStringCriterionFilter</code> that operates in the specified mode.
     *
     * @param generator generates criterion values for files as requested
     * @param caseSensitive if true, this FilePathFilter will be case-sensitive
     * @param inverted if true, this filter will operate in inverted mode.
     */
    public AbstractStringCriterionFilter(CriterionValueGenerator<String> generator, boolean caseSensitive, boolean inverted)
    {
        super(generator, inverted);
        setCaseSensitive(caseSensitive);
    }


    ///////////////////////////////////
    // FilePathFilter implementation //
    ///////////////////////////////////

    public boolean isCaseSensitive()
    {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive)
    {
        this.caseSensitive = caseSensitive;
    }
}
