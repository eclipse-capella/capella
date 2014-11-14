/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.customisation.resourcelocator;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Specific resource locator for Sirius.
 * 
 * 
 */
public class DelegatingSiriusResourceLocator extends DelegatingResourceLocator {

    private static final ResourceLocator PRIMARY_RESOURCE_LOCATOR = new SiriusResourceLocator();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.DelegatingResourceLocator#getPrimaryResourceLocator()
     */
    @Override
    protected ResourceLocator getPrimaryResourceLocator() {
        return PRIMARY_RESOURCE_LOCATOR;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.DelegatingResourceLocator#getDelegateResourceLocators()
     */
    @Override
    protected ResourceLocator[] getDelegateResourceLocators() {
        return new ResourceLocator[] { SiriusEditPlugin.INSTANCE.getOriginalResourceLocator() };
    }

}
