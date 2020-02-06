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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

public class AttributesParticipantsItemProvider extends AbstractMetaModelParticipantsItemProvider {
  private AbstractCapellaSearchForContainerArea leftArea = null;
	public AttributesParticipantsItemProvider(AbstractCapellaSearchForContainerArea area) {
	  this.leftArea = area;
	}

  public Object[] getElements(Object inputElement) {
    Set<EAttribute> eAttributesList = new HashSet<EAttribute>();
    Set<EClass> eClasses = leftArea.displayedMetaClasses;
    for (EClass eCls : eClasses) {
      EList<EAttribute> attributes = eCls.getEAttributes();
      for (EAttribute newAttr : attributes) {
        boolean add = true;
        for (EAttribute attr : eAttributesList) {
          if (newAttr.getName().equals(attr.getName())) {
            add = false;
            break;
          }
        }
        if(add) {
          eAttributesList.add(newAttr);
        }
      }
    }
    return eAttributesList.toArray();
  }

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof EAttribute) return false ;
		return getChildren(element).length > 0;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return Collections.EMPTY_LIST.toArray();
	}
	

	@Override
	public Object getParent(Object element) {
		return null;
	}
	
}
