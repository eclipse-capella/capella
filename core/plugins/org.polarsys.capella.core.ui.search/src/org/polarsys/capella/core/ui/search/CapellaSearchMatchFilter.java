
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.search.ui.text.Match;
import org.eclipse.search.ui.text.MatchFilter;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;

public class CapellaSearchMatchFilter extends MatchFilter {
  private final String id;
  private final String name;
  private final String description;
  private final String label;

  public static final CapellaSearchMatchFilter REPRESENTATION = new CapellaSearchMatchFilter(//
      Messages.CapellaSearchMatchFilter_Representation_Id, //
      Messages.CapellaSearchMatchFilter_Representation_Name, //
      Messages.CapellaSearchMatchFilter_Representation_Description, //
      Messages.CapellaSearchMatchFilter_Representation_Label) {
    @Override
    public boolean filters(Match match) {
      return match.getElement() instanceof DRepresentationDescriptor;
    }
  };

  public static final CapellaSearchMatchFilter NOT_MODIFIABLE = new CapellaSearchMatchFilter( //
      Messages.CapellaSearchMatchFilter_NotModifiable_Id, //
      Messages.CapellaSearchMatchFilter_NotModifiable_Name, //
      Messages.CapellaSearchMatchFilter_NotModifiable_Description, //
      Messages.CapellaSearchMatchFilter_NotModifiable_Label) {
    @Override
    public boolean filters(Match match) {
      Object element = match.getElement();
      if (element instanceof EObject) {
        boolean isLockedByOther = CapellaReadOnlyHelper.getReadOnlySectionHandler().isLockedByOthers((EObject) element);
        return isLockedByOther;
      }
      return false;
    }
  };

  public static final CapellaSearchMatchFilter CAPELLA_ELEMENT = new CapellaSearchMatchFilter(//
      Messages.CapellaSearchMatchFilter_CapellaElement_Id, //
      Messages.CapellaSearchMatchFilter_CapellaElement_Name, //
      Messages.CapellaSearchMatchFilter_CapellaElement_Description, //
      Messages.CapellaSearchMatchFilter_CapellaElement_Label) {
    @Override
    public boolean filters(Match match) {
      return match.getElement() instanceof CapellaElement;
    }
  };

  private CapellaSearchMatchFilter(String id, String name, String description, String label) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.label = label;
  }

  @Override
  public boolean filters(Match match) {
    return false;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getActionLabel() {
    return label;
  }

  @Override
  public String getID() {
    return id;
  }

}
