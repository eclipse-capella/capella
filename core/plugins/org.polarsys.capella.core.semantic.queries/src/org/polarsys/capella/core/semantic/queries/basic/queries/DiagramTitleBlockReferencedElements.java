/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * This query returns the elements referenced by the Title Block
 *
 */
public class DiagramTitleBlockReferencedElements implements IQuery {

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    
    DAnnotation titleBlock = (DAnnotation) object;
    if (TitleBlockHelper.isDiagramTitleBlock(titleBlock)) {
      result.add(TitleBlockHelper.getReferencedElement(titleBlock));
    }    
    return result;
  }
}