/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.tabbed;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.FixedTabDescriptorProvider;


/**
 * A TabDescriptorProvider for semantic elements
 */
public class CapellaTabDescriptorProvider extends FixedTabDescriptorProvider {

  /**
   *
   */
  public CapellaTabDescriptorProvider() {
    super("org.polarsys.capella.core.data.capellamodeller.properties");
    addDescriptor(new SubPropertiesTabDescriptorProvider());
  }

  @Override
  protected boolean isExtensionsPrior() {
    return false;
  }

  @Override
  public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
      Object firstElement = ((IStructuredSelection) selection).getFirstElement();
      
      if (firstElement instanceof DRepresentationDescriptor) {
        DRepresentationDescriptor descriptor = (DRepresentationDescriptor) firstElement;
        boolean isValidRepresentation = new DRepresentationDescriptorQuery(descriptor).isRepresentationValid();
        
        if (!isValidRepresentation) {
          return new ITabDescriptor[] {};
        }
        
      }
    }
    
    return super.getTabDescriptors(part, selection);
  }
}
