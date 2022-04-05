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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 */
public abstract class AbstractModelElementRelatedRepresentationQuery implements IQuery {
  /**
   * {@inheritDoc}
   */
public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>(0);
    if (!(object instanceof EObject)) {
      return result;
    }
    result.addAll(RepresentationHelper.getAllRepresentationDescriptorsWhereSemanticElementIsDisplayed((EObject) object, filterRepresentationDescription()));
    Collections.sort(result, (o1, o2) -> {
        DRepresentationDescriptor desc1 = (DRepresentationDescriptor) o1;
        DRepresentationDescriptor desc2 = (DRepresentationDescriptor) o2;
        return desc1.getName().compareTo(desc2.getName());
    });
    return result;
  }

  /**
   * Allow to filter out processed representation description by {@link #compute(Object)} method.
   * @return <code>true</code> means specified representation description is kept, <code>false</code> it is filtered out.
   */
  protected abstract RunnableWithBooleanResult filterRepresentationDescription();
}
