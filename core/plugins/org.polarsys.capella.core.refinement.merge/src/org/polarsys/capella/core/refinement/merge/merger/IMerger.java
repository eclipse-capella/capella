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
package org.polarsys.capella.core.refinement.merge.merger;

import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;

/**
 * Interface that should be implemented by Merger algorithms.
 *
 */
public interface IMerger {
  
  /** the merge itself */
  public void performMerge() throws MergeException;

  /* result of the merge operation */
  public Scenario getResult();
  
}
