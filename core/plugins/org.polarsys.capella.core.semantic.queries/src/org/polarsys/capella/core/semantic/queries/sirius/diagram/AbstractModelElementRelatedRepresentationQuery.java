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
package org.polarsys.capella.core.semantic.queries.sirius.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public abstract class AbstractModelElementRelatedRepresentationQuery implements IQuery {
  /**
   * {@inheritDoc}
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>(0);
    if (!(object_p instanceof EObject)) {
      return result;
    }
    result.addAll(RepresentationHelper.getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed((EObject) object_p, filterRepresentationDescription()));
    return result;
  }

  /**
   * Allow to filter out processed representation description by {@link #compute(Object)} method.
   * @return <code>true</code> means specified representation description is kept, <code>false</code> it is filtered out.
   */
  protected abstract RunnableWithBooleanResult filterRepresentationDescription();
}
