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
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return all Realizing Class of given Class
 */
public class ClassRealizingClass implements IQuery {

  /**
	 * 
	 */
  public ClassRealizingClass() {
    // do nothing
  }

  /**
   * current.extendedCapabilities
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof org.polarsys.capella.core.data.information.Class) {
      org.polarsys.capella.core.data.information.Class c = (org.polarsys.capella.core.data.information.Class) object;
      EList<AbstractTrace> incomingTraces = c.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        if (abstractTrace instanceof InformationRealization) {
          TraceableElement soruceElement = abstractTrace.getSourceElement();
          if ((null != soruceElement) && (soruceElement instanceof org.polarsys.capella.core.data.information.Class)) {
            result.add(soruceElement);
          }
        }
      }
    }

    return result;
  }
}
