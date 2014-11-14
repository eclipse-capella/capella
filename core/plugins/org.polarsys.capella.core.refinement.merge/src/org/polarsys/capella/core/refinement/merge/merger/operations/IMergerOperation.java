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
package org.polarsys.capella.core.refinement.merge.merger.operations;

import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;

/**
 * Interface for 'atomic' operation for Merge
 *
 */
public interface IMergerOperation {
 
  /**
   * Prepare the operation
   */
  public void preOperation()  throws MergeToolException;
  
  /**
   * The job itself
   */
  public void perform() throws MergeToolException;
  
  /**
   * post treatment;
   */
  public void postOperation()  throws MergeToolException;
   
  
}
