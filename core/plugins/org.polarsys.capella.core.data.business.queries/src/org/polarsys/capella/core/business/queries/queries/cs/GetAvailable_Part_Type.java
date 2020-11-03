/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Part_Type extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    EObject container = element.eContainer();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
    Set<EObject> setOfAvailableElements = new HashSet<EObject>();
    if (element instanceof Part) {
      if (arch instanceof OperationalAnalysis) {
        setOfAvailableElements = EObjectExt.getAll(arch, OaPackage.Literals.ENTITY);
        if (setOfAvailableElements.contains(container)) {
          setOfAvailableElements.remove(container);
        }
      } else {
        java.util.Collection<EObject> availableComponentsByNamespaceOfParts = ComponentExt
            .getAvailableComponentsByNamespaceOfParts((Part) element);
        for (EObject anObject : availableComponentsByNamespaceOfParts) {
          if (!EcoreUtil.isAncestor(anObject, element)) {
            setOfAvailableElements.add(anObject);
          }
        }
      }
    }
    for (EObject anObject : setOfAvailableElements) {
      if (anObject instanceof CapellaElement) {
        availableElements.add((CapellaElement) anObject);
      }
    }
    return availableElements;
  }

}
