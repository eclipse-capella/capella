/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return physical paths in which current physical link is involved
 */
public class PhysicalLinksInvolvedInPhysicalPaths implements IQuery {
  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof PhysicalLink) {
      // retrieve all inverse references elements
      Collection<Setting> invRefs = CapellaElementExt.getInverseReferencesOfEObject((PhysicalLink) object);
      for (Setting setting : invRefs) {
        EObject eObject = setting.getEObject();
        // add to result if physical path
        if (null != eObject && eObject instanceof PhysicalPathInvolvement) {
          PhysicalPathInvolvement inv = (PhysicalPathInvolvement) eObject;
          EObject container = inv.eContainer();
          if (null != container && container instanceof PhysicalPath) {
            result.add(container);
          }
        }
      }
    }
    return result;
  }
}
