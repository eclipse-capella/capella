/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.sections;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 *
 */
public class CapellaAdvancedPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {

  /**
   * Force to share the same adapter factory with other Capella components.
   */
  public AdapterFactory getAdapterFactory() {
    return CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
  }

  /**
   * @param object
   */
  @Override
  public IPropertySource getPropertySource(Object object) {
    if (object instanceof IResource) {
      return Adapters.adapt(object, IPropertySource.class);
    }
    if (getAdapterFactory() != null) {
      IItemPropertySource item = (IItemPropertySource) getAdapterFactory().adapt(object, IItemPropertySource.class);
      if (item != null) {
        return new PropertySource(object, item) {
          /**
           * Used to ignore reentrant call from expert property sheet page.
           */
          private volatile boolean _reentrantCall;

          /**
           * {@inheritDoc}
           */
          @Override
          public void setPropertyValue(Object propertyId, Object value) {
            try {
              if (!_reentrantCall) {
                // Set flag to true to filter out reentrant call from the Expert property sheet page.
                // Indeed, when end-user validates its change in a cell with CR key in Clearcase or SVN context, the
                // prompted dialog to check-out the file
                // makes the cell loosing its focus and thus trigger a second command that collides with current one for
                // the same change.
                _reentrantCall = true;

                // before setting the value, we check that the object has not been deleted
                // (can happen when the 'pin' button is activated on properties view)
                if (object instanceof EObject) {
                  Resource res = ((EObject) object).eResource();
                  if (null != res) {
                    super.setPropertyValue(propertyId, value);
                  }
                }
              }
            } finally {
              _reentrantCall = false;
            }
          }
        };
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    CommonViewer viewer = getPart().getAdapter(CommonViewer.class);
    if (viewer != null) {
      ITreeSelection currentSelection = viewer.getStructuredSelection();
      if (getSelection().equals(currentSelection)) {
        super.refresh();
      } else {
        setInput(getPart(), currentSelection);
      }
    } else {
      super.refresh();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);
    if (null != page) {
      page.setPropertySourceProvider(this);
    }
  }
}
