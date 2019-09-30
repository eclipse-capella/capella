/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
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
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();
    // On disposing, remove the instance from the map
    mapDescriptionSectionToEObject.remove(this);
    // If the disposing section is displayed in the wizard, then notify the section (if there is) in the view properties
    // to be refreshed.
    if (isDisplayedInWizard()) {
      Set<CapellaDescriptionPropertySection> availableDescriptionSections = mapDescriptionSectionToEObject.keySet();
      for (CapellaDescriptionPropertySection descriptionSection : availableDescriptionSections) {
        if (descriptionSection != null && !descriptionSection.isDisplayedInWizard()) {
          descriptionSection.refresh();
          descriptionSection.aboutToBeShown();
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    // On loading data, add the instance to the map.
    mapDescriptionSectionToEObject.put(this, capellaElement);
    if (null != descriptionGroup) {
      descriptionGroup.loadData(capellaElement);
    } else if (descriptionFallbackGroup != null) {
      descriptionFallbackGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_Description());
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject elt = CapellaAdapterHelper.resolveBusinessObject(((StructuredSelection) selection).getFirstElement());
      if (elt instanceof CapellaElement) {
        loadData((CapellaElement) elt);
      }
    }
    super.setInput(part, selection);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObj = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(toTest);
    return eObj instanceof CapellaElement;
  }
}
