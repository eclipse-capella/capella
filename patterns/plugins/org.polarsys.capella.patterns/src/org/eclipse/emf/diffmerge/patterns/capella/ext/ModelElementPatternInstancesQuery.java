/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.diffmerge.patterns.core.CorePatternsPlugin;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IPatternSupport;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * A query for customizing the Semantic Browser with "related pattern instances".
 */
public class ModelElementPatternInstancesQuery implements IQuery {
  
  /**
   * Constructor
   */
  public ModelElementPatternInstancesQuery() {
    // Nothing specific
  }
  
  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<Object> compute(Object object_p) {
    List<Object> result = Collections.emptyList();
    if (object_p instanceof EObject) {
      EObject element = (EObject)object_p;
      IPatternSupport support =
        CorePatternsPlugin.getDefault().getPatternSupportFor(element);
      if (support != null)
        result = (List)support.getRelatedInstances(element);
    }
    return result;
  }
  
}
