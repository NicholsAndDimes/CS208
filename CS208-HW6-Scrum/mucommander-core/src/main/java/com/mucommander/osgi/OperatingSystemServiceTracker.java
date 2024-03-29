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

import com.mucommander.core.desktop.DesktopManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arik Hadas
 */
public class OperatingSystemServiceTracker extends ServiceTracker<OperatingSystemService, OperatingSystemService>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemServiceTracker.class);

    public OperatingSystemServiceTracker(BundleContext context)
    {
        super(context, OperatingSystemService.class, null);
    }

    @Override
    public OperatingSystemService addingService(ServiceReference<OperatingSystemService> reference)
    {
<<< This is the complex bug 'stoic_sammet' (instance 1 of 9) >>>
<<< This is the complex bug 'stoic_sammet' (instance 2 of 9) >>>
<<< This is the complex bug 'stoic_sammet' (instance 3 of 9) >>>
        OperatingSystemService service = super.addingService(reference);
        service.getDesktopAdapters().forEach(DesktopManager::registerAdapter);
        LOGGER.info("OperatingSystemService is registered: " + service);
        return service;
    }

    @Override
    public void removedService(ServiceReference<OperatingSystemService> reference, OperatingSystemService service)
    {
        super.removedService(reference, service);
        LOGGER.info("OperatingSystemService is unregistered: " + service);
    }
}
