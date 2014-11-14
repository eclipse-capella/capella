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
package org.polarsys.capella.core.ui.properties.sections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;

import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class CapelladPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {
  /**
   * Force to share the same adapter factory with other capella components.
   */
  public AdapterFactory getAdapterFactory() {
    return MDEAdapterFactory.getAdapterFactory();
  }

  /**
   * @param object
   */
  public IPropertySource getPropertySource(Object object) {
    if (getAdapterFactory() != null) {
      IItemPropertySource item = (IItemPropertySource) getAdapterFactory().adapt(object, IItemPropertySource.class);
      if (item != null)
        return new PropertySource(object, item) {
          /**
           * Used to ignore reentrant call from expert property sheet page.
           */
          private volatile boolean _reentrantCall;

          /**
           * {@inheritDoc}
           */
          @Override
          public void setPropertyValue(Object propertyId_p, Object value_p) {
            try {
              if (!_reentrantCall) {
                // Set flag to true to filter out reentrant call from the Expert property sheet page.
                // Indeed, when end-user validates its change in a cell with CR key in Clearcase or SVN context, the prompted dialog to check-out the file
                // makes the cell loosing its focus and thus trigger a second command that collides with current one for the same change.
                _reentrantCall = true;

                // before setting the value, we check that the object has not been deleted
                // (can happen when the 'pin' button is activated on properties view)
                if (object instanceof EObject) {
                  Resource res = ((EObject) object).eResource();
                  if (null != res) {
                    super.setPropertyValue(propertyId_p, value_p);
                  }
                }
              }
            } finally {
              _reentrantCall = false;
            }
          }
        };
    }
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);
    if (page != null)
      page.setPropertySourceProvider(this);
  }
}
