/*
 * This file is part of muCommander, http://www.mucommander.com
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.ui.main.tree;

/**
 * Monitors thread that reads children and icons for the tree.
 *
 * @author Mariusz Jakubowski
 */
public class TreeIOThreadManager extends AbstractIOThreadManager
{

    public final static TreeIOThreadManager instance = new TreeIOThreadManager();

    private TreeIOThreadManager()
    {
        super("TreeIOThreadManager", 5000);
    }

    public static TreeIOThreadManager getInstance()
    {
        return instance;
    }


}
