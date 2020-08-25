/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.UIJob;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

/**
 * @author Joao Barata
 */
public class CapellaDescriptionPropertySection extends DescriptionPropertySection {
  /**
   * Because of the description property section is used in both the view properties and the wizard dialog (when user
   * double clicks on an capella element). We use here a static map to keep track of the instances of this class. The
   * idea is: when a section in a wizard is disposed, the current opening section in the view properties could be
   * notified to be refresh. (This is the bug we have with richtext editor)
   */
  private static Map<CapellaDescriptionPropertySection, EObject> mapDescriptionSectionToEObject = new HashMap<>();

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObj = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(toTest);
    return eObj instanceof CapellaElement || (eObj instanceof DRepresentationDescriptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();
    // On disposing, remove the instance from the map
    EObject element = mapDescriptionSectionToEObject.remove(this);
    // If the disposing section is displayed in the wizard, then notify the section (if there is) in the view properties
    // to be refreshed.
    if (element != null && isDisplayedInWizard()) {
      Set<CapellaDescriptionPropertySection> availableDescriptionSections = mapDescriptionSectionToEObject.keySet();
      for (CapellaDescriptionPropertySection descriptionSection : availableDescriptionSections) {
        if (descriptionSection != null && !descriptionSection.isDisplayedInWizard()) {
          if (mapDescriptionSectionToEObject.get(descriptionSection) == element) {
            descriptionSection.refresh();
            descriptionSection.aboutToBeShown();
          }
        }
      }
    }
  }

  DelayedSetDescription job = new DelayedSetDescription("Load Description");

  /**
   * Avoid consecutive loads if the selection is quickly changed
   */
  private class DelayedSetDescription extends UIJob {

    EObject current = null;

    public DelayedSetDescription(String name) {
      super(name);
      setSystem(true);
    }

    @Override
    public boolean belongsTo(Object family) {
      return DelayedSetDescription.class.getSimpleName().equals(family);
    }

    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
      EObject element = current;

      // On loading data, add the instance to the map.
      if (null != descriptionGroup) {
        if (element.eResource() != null) {
          // If the element is no longer in a resource (for example in case of a
          // connection lost with the server in Team4Capella usage) there is no reason to
          // load (except to have some exception).
          descriptionGroup.loadData(element);
        }
      } else if (descriptionFallbackGroup != null) {
        if (element.eResource() != null) {
          // If the element is no longer in a resource (for example in case of a
          // connection lost with the server in Team4Capella usage) there is no reason to
          // load (except to have some exception).
          descriptionFallbackGroup.loadData(element);
        }
      }
      return Status.OK_STATUS;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject descriptorOrCapellaElement) {
    super.loadData(descriptorOrCapellaElement);
    mapDescriptionSectionToEObject.put(CapellaDescriptionPropertySection.this, descriptorOrCapellaElement);

    job.current = descriptorOrCapellaElement;
    job.schedule(100);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);
    if (selection instanceof StructuredSelection) {
      EObject elt = CapellaAdapterHelper
          .resolveDescriptorOrBusinessObject(((StructuredSelection) selection).getFirstElement());

      if (elt instanceof CapellaElement || elt instanceof DRepresentationDescriptor) {
        loadData(elt);
      }
    }
  }

}
