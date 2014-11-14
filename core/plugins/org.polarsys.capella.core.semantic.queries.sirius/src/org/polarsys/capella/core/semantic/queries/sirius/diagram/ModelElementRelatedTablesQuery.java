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
package org.polarsys.capella.core.semantic.queries.sirius.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;

import org.polarsys.capella.common.utils.RunnableWithBooleanResult;

/**
 * Query to retrieve all table representations that contain current model element.
 */
public class ModelElementRelatedTablesQuery extends AbstractModelElementRelatedRepresentationQuery {
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
        setResult(Boolean.valueOf(representationDescription instanceof TableDescription));
      }
    };
  }
}
