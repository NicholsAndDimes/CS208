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
package com.mucommander.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Registration tracker for file viewer service.
 *
 * @author Miroslav Hajda
 */
public class FileViewerServiceTracker extends ServiceTracker<FileViewerService, FileViewerService>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileViewerServiceTracker.class);

    private static final List<FileViewerService> SERVICES = new ArrayList<>();

    public FileViewerServiceTracker(BundleContext context)
    {
        super(context, FileViewerService.class, null);
    }

    @Override
    public FileViewerService addingService(ServiceReference<FileViewerService> reference)
    {
<<< This is the medium bug 'quirky_goldberg' (instance 3 of 4) >>>
<<< This is the medium bug 'quirky_goldberg' (instance 4 of 4) >>>
        FileViewerService service = super.addingService(reference);
        FileViewerServiceTracker.addViewerService(service);
        LOGGER.info("FileViewerService is registered: " + service);
        return service;
    }

    @Override
    public void removedService(ServiceReference<FileViewerService> reference, FileViewerService service)
    {
        super.removedService(reference, service);
        SERVICES.remove(service);
        LOGGER.info("FileViewerService is unregistered: " + service);
    }

    private static void addViewerService(FileViewerService service)
    {
        SERVICES.add(service);
        SERVICES.sort(Comparator.comparing(FileViewerService::getOrderPriority).reversed());
    }

    public static List<FileViewerService> getViewerServices()
    {
        return SERVICES;
    }
}
