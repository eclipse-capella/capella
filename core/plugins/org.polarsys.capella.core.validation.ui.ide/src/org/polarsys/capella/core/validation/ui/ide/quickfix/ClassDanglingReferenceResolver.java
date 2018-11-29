/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.information.Class;

/**
 * A resolver for org.eclipse.emf.ecore.4 style markers (Unresolved proxy (reference) to the super type of a Class).
 * 
 * This fix simply deletes the Generalization link that has no solvable super
 * 
 */

public class ClassDanglingReferenceResolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {

    List<Generalization> generalizationsToDelete = new ArrayList<>();

    if (obj instanceof Generalization) {

      generalizationsToDelete.add((Generalization) obj);

    } else if (obj instanceof Class) {

      Class c = (Class) obj;
      EList<Generalization> genList = c.getOwnedGeneralizations();
      for (Generalization gen : genList) {

        GeneralizableElement sup = gen.getSuper();
        
        // If the super is a Proxy, it means that the resolution has failed
        if (null != sup && sup.eIsProxy()) {
          generalizationsToDelete.add(gen);
        }
      }
    }
    return generalizationsToDelete;
  }
}
