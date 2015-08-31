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
package org.polarsys.capella.core.refinement.merge.helpers;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * Helper class for InstanceRole
 *
 */
public class InstanceRoleHelper {

  /**
   * Check name...
   * @param ir
   * @return
   * @throws MergeToolException
   */
  public static boolean isIRrepresentASysyemComponent(InstanceRole ir) throws MergeToolException {
    
    boolean result = false;

    try {
    
      AbstractType ae = getAbstractType(ir);
    
      result = ( CsPackage.Literals.SYSTEM_COMPONENT.isSuperTypeOf(ae.eClass()) );
    
    } catch (Exception exception) {
      //TODO Log an error and/or change message
      throw new MergeToolException(MergeMessages.genericInternalError, exception);
    }
    
    return result;
  }
  
  /**
   * return the given {@link AbstractType}.
   * @param ir
   * @param expected the expected EClass, 
   * @return
   * @throws MergeToolException
   */
  static public AbstractType getAbstractType(InstanceRole ir) throws MergeToolException {
    
    AbstractType result = null;
    
    try {
      result = ir.getRepresentedInstance().getAbstractType();
    } catch (Exception exceptionP) {
      //TODO Log an error and/or change message
      throw new MergeToolException(MergeMessages.genericInternalError, exceptionP);
    }
    
    return result;
  }
}
