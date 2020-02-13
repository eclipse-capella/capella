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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;

@SuppressWarnings("deprecation")
public class ParticipantsViewerSorter extends ViewerSorter {
  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {

    if (e1 instanceof ENamedElement && e2 instanceof ENamedElement) {
      ENamedElement eClass1 = (ENamedElement) e1;
      ENamedElement eClass2 = (ENamedElement) e2;

      String eClass1Name = "";
      if (e1 instanceof EClass) {
        eClass1Name += "ETypeKind_1_";
      } else if (e1 instanceof EDataType) {
        eClass1Name += "ETypeKind_2_";
      }
      eClass1Name += eClass1.getName() == null ? "" : eClass1.getName();

      String eClass2Name = "";
      if (e2 instanceof EClass) {
        eClass2Name += "ETypeKind_1_";
      } else if (e2 instanceof EDataType) {
        eClass2Name += "ETypeKind_2_";
      }
      eClass2Name += eClass2.getName() == null ? "" : eClass2.getName();

      return eClass1Name.compareTo(eClass2Name);
    }
    else if(e1 instanceof String && e2 instanceof String) {
      String cat1 = (String) e1;
      if(cat1.equals(CapellaSearchConstants.ModelElements_Key))
        return -1;
      return 1;
    }
    return 0;
  }
}
