/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public enum CapellaSearchField {

  NAME(Messages.CapellaSearchField_Name) {
    @Override
    public EAttribute getEAttribute(Object element) {
      if (element instanceof AbstractNamedElement) {
        return ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME;
      }
      if (element instanceof DRepresentationDescriptor) {
        return ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME;
      }
      return null;
    }

    @Override
    public String getText(Object element) {
      if (element instanceof DRepresentationDescriptor) {
        return ((DRepresentationDescriptor) element).getName();
      }
      if (element instanceof AbstractNamedElement) {
        return ((AbstractNamedElement) element).getName();
      }
      return null;
    }
  },

  SUMMARY(Messages.CapellaSearchField_Summary) {
    @Override
    public EAttribute getEAttribute(Object element) {
      if (element instanceof CapellaElement) {
        return CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY;
      }
      return null;
    }

    @Override
    public String getText(Object element) {
      if (element instanceof CapellaElement) {
        return ((CapellaElement) element).getSummary();
      }
      return null;
    }
  },

  DESCRIPTION(Messages.CapellaSearchField_Description) {
    @Override
    public EAttribute getEAttribute(Object element) {
      if (element instanceof CapellaElement) {
        return CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION;
      }
      if (element instanceof DRepresentationDescriptor) {
        return DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION;
      }
      return null;
    }

    @Override
    public String getText(Object element) {
      if (element instanceof CapellaElement) {
        return ((CapellaElement) element).getDescription();

      }
      if (element instanceof DRepresentationDescriptor) {
        return ((DRepresentationDescriptor) element).getDocumentation();
      }
      return null;
    }
  };

  private final String label;

  private CapellaSearchField(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  /**
   * Get the EAttribute of element corresponding to the search field
   * 
   * @param element
   * @return
   */
  public abstract EAttribute getEAttribute(Object element);

  /**
   * Get the text of element corresponding to the search field
   * 
   * @param element
   * @return
   */
  public abstract String getText(Object element);
}
