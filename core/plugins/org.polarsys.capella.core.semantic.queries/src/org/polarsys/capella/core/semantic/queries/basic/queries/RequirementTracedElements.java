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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return all the elements associated to current requirement
 */
public class RequirementTracedElements implements IQuery {

  /**
   * Default Constructor 
   */
  public RequirementTracedElements() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Requirement) {
      // Type the element
      Requirement req = (Requirement) object_p;
      // Retrieve list of inComing traces 
      EList<AbstractTrace> incomingTraces = req.getIncomingTraces();
      for (AbstractTrace abstractTrace : incomingTraces) {
        if (abstractTrace instanceof RequirementsTrace) {
          // Type the element
          RequirementsTrace requirementsTrace = (RequirementsTrace) abstractTrace;
          // retrieve the source of the requirementsTrace 
          TraceableElement sourceElement = requirementsTrace.getSourceElement();
          // if source not null add to result
          if (null != sourceElement)
            result.add(sourceElement);
        }
      }
    }
    return result;
  }
}
