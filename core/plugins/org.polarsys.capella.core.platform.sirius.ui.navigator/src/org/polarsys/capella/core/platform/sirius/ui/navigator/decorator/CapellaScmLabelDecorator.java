/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.decorator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.team.core.RepositoryProvider;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;

public class CapellaScmLabelDecorator implements ILightweightLabelDecorator {
  /**
   * Decorator id declared in plugin.xml file.
   */
  public static final String ID = "org.polarsys.capella.common.menu.dynamic.CapellaScmDecorator"; //$NON-NLS-1$

  /**
   * Constructor.
   */
  public CapellaScmLabelDecorator() {
    super();
  }

  /**
   * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
   *      org.eclipse.jface.viewers.IDecoration)
   */
  @Override
  public void decorate(Object element, IDecoration decoration) {
    // The plugin.xml (via decorator extension) defines the enable status.
    // Only the NamedElement model element is decorated.
    // Ensure that in checking given object type.
    
    if (element instanceof NamedElement) {
      handleNamedElement(decoration, (NamedElement) element);
      
    } else if (element instanceof DRepresentationDescriptor) {
      handleRepresentation(decoration, (DRepresentationDescriptor) element);
    }
  }

  /**
   * Handle Representation.
   * 
   * @param decoration
   * @param element
   */
  protected void handleRepresentation(IDecoration decoration, DRepresentationDescriptor element) {
    addScmOverlay(decoration, element);
  }

  /**
   * @param decoration
   * @param element
   */
  protected void handleNamedElement(IDecoration decoration, NamedElement element) {
    // Add an overlay if given element is the Capella Project or a fragment root object.
    boolean addOverlay = false;
    if (element instanceof Project) {
      addOverlay = true;
    } else {
      // Check if given element is controlled in a fragment
      try {
        addOverlay = AdapterFactoryEditingDomain.isControlled(element);
      } catch (IllegalStateException exception) {
        // may not be able to check if the element is controlled in a collaborative context for example.
      }
    }
    if (addOverlay) {
      addScmOverlay(decoration, element);
    }
  }

  /**
   * Add scm overlay if needed.
   * 
   * @param decoration
   * @param element
   */
  protected void addScmOverlay(IDecoration decoration, EObject element) {
    Resource resource = element.eResource();
    IFile file = EcoreUtil2.getFile(resource);
    if (null != file) {
      RepositoryProvider scmProvider = RepositoryProvider.getProvider(file.getProject());
      // No provider means a project not controlled by a SCM tool.
      if (null != scmProvider) {
        // Select the SCM image depending on resource read only property.
        boolean isReadOnly = EcoreUtil2.isReadOnly(resource);
        ImageDescriptor imageDescriptor = null;
        if (isReadOnly) {
          imageDescriptor = CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SCM_CI);
        } else {
          imageDescriptor = CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_SCM_CO);
        }
        decoration.addOverlay(imageDescriptor);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void addListener(ILabelProviderListener listener) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void removeListener(ILabelProviderListener listener) {
    // Do nothing.
  }
}
