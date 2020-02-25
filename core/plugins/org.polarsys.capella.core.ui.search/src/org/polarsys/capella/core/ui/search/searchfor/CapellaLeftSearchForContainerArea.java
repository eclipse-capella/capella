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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PatternFilter;
import org.polarsys.capella.core.ui.search.CapellaSearchSettings;

public class CapellaLeftSearchForContainerArea extends AbstractCapellaSearchForContainerArea {

  public CapellaLeftSearchForContainerArea(Group parent, CapellaSearchSettings settings) {
    super(parent, null, settings);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    if (partictipantsItemProvider == null)
      return new MetaClassesParticipantsItemProvider(this);
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
            for (Object element : result) {
              updateDisplayedElements(filter(viewer, element, partictipantsItemProvider.getChildren(element)));
            }
          }
        }
        return result;
      }

      private void updateDisplayedElements(Object[] elements) {
        for (Object displayedElement : elements) {
          if (displayedElement instanceof EClass) {
            displayedElements.add(displayedElement);
          }
        }
      }
    };
  }

  @Override
  protected void setCheckSubtree() {
    // for partictipantsItemProviders
    ((CheckboxTreeViewer) filteredTree.getViewer()).addCheckStateListener(getCheckStateListener());
  }

  private ICheckStateListener getCheckStateListener() {
    return new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent event) {
        CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredTree.getViewer();
        boolean state = event.getChecked();
        Object parent = event.getElement();
        if(state == true)
          checkedElements.add(parent);
        else
          checkedElements.remove(parent);
        
        // handle the inheritance check propagation
        Object[] changedObjects = partictipantsItemProvider.getChildren(parent);
        for (Object obj : changedObjects) {
          viewer.setChecked(obj, state);
          if(state == true)
            checkedElements.add(obj);
          else
            checkedElements.remove(obj);
        }
        
        capellaSearchSettings.setSearchMetaClasses(checkedElements);
        // refresh the attributes in right panel
        if(otherSideArea != null) {
          otherSideArea.filteredTree.getViewer().refresh();
        }
      }
    };
  }
}
