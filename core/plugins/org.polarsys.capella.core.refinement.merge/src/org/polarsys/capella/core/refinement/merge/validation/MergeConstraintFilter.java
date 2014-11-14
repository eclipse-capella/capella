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
package org.polarsys.capella.core.refinement.merge.validation;

import org.polarsys.capella.core.validation.filter.group.AbstractGroupConstraintFilter;
import org.polarsys.capella.core.validation.filter.group.ConstraintGroupEnum;

/**
 * Rules filtering for merge validation
 */
public class MergeConstraintFilter extends AbstractGroupConstraintFilter {

  /**
   * @see org.polarsys.capella.core.validation.filter.group.AbstractGroupConstraintFilter#getGroup()
   */
  @Override
  public ConstraintGroupEnum getGroup() {
    return ConstraintGroupEnum.MERGE;
  }
  
}
