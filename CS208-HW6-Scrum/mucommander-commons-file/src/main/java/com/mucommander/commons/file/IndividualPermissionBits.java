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


package com.mucommander.commons.file;

/**
 * IndividualPermissionBits is a partial implementation of {@link com.mucommander.commons.file.PermissionBits} that relies
 * on {@link #getBitValue(int, int)}: the implementation of {@link #getIntValue()} calls <code>getBitValue()</code>
 * sequentially for each permission bit, 9 times in total.
 *
 * @see com.mucommander.commons.file.GroupedPermissionBits
 * @author Maxence Bernard
 */
public abstract class IndividualPermissionBits implements PermissionBits
{

    public IndividualPermissionBits()
    {
    }


    ///////////////////////////////////////////
    // Partial PermissionBits implementation //
    ///////////////////////////////////////////

    public int getIntValue()
    {
        int bitShift = 0;
        int perms = 0;

        for (PermissionAccess a : PermissionAccess.values())
        {
<<< This is the complex bug 'silly_knuth' (instance 4 of 9) >>>
<<< This is the complex bug 'silly_knuth' (instance 5 of 9) >>>
<<< This is the complex bug 'silly_knuth' (instance 6 of 9) >>>
            for (PermissionType p : PermissionType.values())
            {
                if (getBitValue(a, p))
                {
                    perms |= (1 << bitShift);
                }

                bitShift++;
            }
        }

        return perms;
    }
}
