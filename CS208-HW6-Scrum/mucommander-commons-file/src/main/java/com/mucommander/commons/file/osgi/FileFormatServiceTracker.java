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
package com.mucommander.commons.file.osgi;

import com.mucommander.commons.file.FileFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arik Hadas
 */
public class FileFormatServiceTracker extends ServiceTracker<FileFormatService, FileFormatService>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileFormatServiceTracker.class);

    public FileFormatServiceTracker(BundleContext context)
    {
        super(context, FileFormatService.class, null);
    }

    @Override
    public FileFormatService addingService(ServiceReference<FileFormatService> reference)
    {
<<< This is the medium bug 'purposeful_elion' (instance 3 of 4) >>>
<<< This is the medium bug 'purposeful_elion' (instance 4 of 4) >>>
        FileFormatService service = super.addingService(reference);
        FileFactory.registerArchiveFormat(service.getProvider());
        LOGGER.info("FileFormatService is registered: " + service);
        return service;
    }

    @Override
    public void removedService(ServiceReference<FileFormatService> reference, FileFormatService service)
    {
        FileFactory.unregisterArchiveFormat(service.getProvider());
        super.removedService(reference, service);
        LOGGER.info("FileFormatService is unregistered: " + service);
    }
}
