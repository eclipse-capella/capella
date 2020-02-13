/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PatternFilter;

public class CapellaRightSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  public CapellaRightSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea leftArea) {
    super(parent, leftArea);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    if(partictipantsItemProvider == null)
      return new AttributesParticipantsItemProvider(otherSideArea);
    return partictipantsItemProvider;
  }

  protected PatternFilter createPatternFilter() {
    return new PatternFilter() {
      @Override
      public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
        Object[] result = super.filter(viewer, parent, elements);
        if (parent != null) {
          if (parent.equals("")) {
            displayedElements.clear();
            updateDisplayedElements(result);
          }
        }
        return result;
      }

      private void updateDisplayedElements(Object[] elements) {
        for (Object displayedElement : elements) {
          displayedElements.add( displayedElement);
        }
      }
    };
  }

}
