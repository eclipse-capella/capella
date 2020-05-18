/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;

/**
 * Return association related to the Property
 *
 */
public class PropertyAssociation implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Property) {
      Property property = (Property) object;
      if (property.getAssociation() != null) {
        result.add(property.getAssociation());
      } else {
        EObject container = property.eContainer();
        if (container instanceof Association) {
          result.add(container);
        }
      }
    }
    return result;
  }
}
