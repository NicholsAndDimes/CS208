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
 * This {@link PathFilter} matches paths that contain a specified string that can be located anywhere in the
 * path.
 *
 * @author Maxence Bernard
 */
public class ContainsPathFilter extends AbstractContainsFilter implements PathFilter
{

    /**
     * Creates a new case-insensitive <code>ContainsPathFilter</code> operating in non-inverted mode.
     *
     * @param s the string to compare paths against
     */
    public ContainsPathFilter(String s)
    {
        this(s, false, false);
    }

    /**
     * Creates a new <code>ContainsPathFilter</code> operating in non-inverted mode.
     *
     * @param s the string to compare paths against
     * @param caseSensitive if true, this PathFilter will be case-sensitive
     */
    public ContainsPathFilter(String s, boolean caseSensitive)
    {
        this(s, caseSensitive, false);
    }

    /**
     * Creates a new <code>ContainsPathFilter</code> operating in the specified mode.
     *
     * @param s the string to compare paths against
     * @param caseSensitive if true, this PathFilter will be case-sensitive
     * @param inverted if true, this filter will operate in inverted mode.
     */
    public ContainsPathFilter(String s, boolean caseSensitive, boolean inverted)
    {
        super(new PathGenerator(), s, caseSensitive, inverted);
    }
}
