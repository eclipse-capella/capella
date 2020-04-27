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
package org.polarsys.capella.core.semantic.queries.sirius.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;

import org.polarsys.capella.common.utils.RunnableWithBooleanResult;

/**
 */
public class ModelElementRelatedDiagramsQuery extends AbstractModelElementRelatedRepresentationQuery {
  /**
   * {@inheritDoc}
   */
  @Override
  protected RunnableWithBooleanResult filterRepresentationDescription() {
    return new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject representationDescription = getObject();
        setResult(Boolean.valueOf(!(representationDescription instanceof TableDescription)));
      }
    };
  }
}
