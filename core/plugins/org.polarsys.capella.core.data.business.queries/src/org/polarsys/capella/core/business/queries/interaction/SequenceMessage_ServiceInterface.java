/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;

/**
 *
 */
public class SequenceMessage_ServiceInterface implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    BlockArchitecture currentArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    result.addAll(InterfaceExt.getAllInterfaces(currentArchitecture)); 
    return result;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return getAvailableElements(element_p);
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InteractionPackage.Literals.SEQUENCE_MESSAGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
      //FIXME incorrect
      return Collections.singletonList(InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END);
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_SequenceMessage_ServiceInterface__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_SequenceMessage_ServiceInterface", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}

}
