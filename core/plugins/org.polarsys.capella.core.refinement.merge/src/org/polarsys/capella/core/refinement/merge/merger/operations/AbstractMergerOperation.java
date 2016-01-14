/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;

/**
 *
 */
public abstract class AbstractMergerOperation implements IMergerOperation {

  final public boolean apply() throws MergeToolException {
    
    boolean b = true;
    
    try {
      preOperation();
      perform();
      postOperation();
    } catch (MergeToolException exception) {  
            
      b = false;
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError, exception);
    }
    
    return b;
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#perform()
   */
  abstract public void perform() throws MergeToolException;
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#postOperation()
   */
  abstract public void postOperation() throws MergeToolException;
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#preOperation()
   */
  abstract public void preOperation() throws MergeToolException;

}
