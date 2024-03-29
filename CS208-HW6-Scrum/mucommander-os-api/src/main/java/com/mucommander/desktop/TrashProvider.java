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

package com.mucommander.desktop;

/**
 * TrashProvider provides a way to instantiate {@link AbstractTrash} implementations.
 *
 * <p>Trash providers can be registered with {@link com.mucommander.core.desktop.DesktopManager#setTrashProvider(TrashProvider)}
 * for them to become the default trash one.</p>
 *
 * @author Nicolas Rinaudo
 * @see com.mucommander.desktop.AbstractTrash
 * @see com.mucommander.core.desktop.DesktopManager#setTrashProvider(TrashProvider)
 */
public interface TrashProvider
{

    /**
     * Returns a trash instance.
     *
     * @return a trash instance
     */
    public AbstractTrash getTrash();

}
