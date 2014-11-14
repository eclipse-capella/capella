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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Remove Function's Condition value
 */
public class DWF_DF_09_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    final EObject obj = getModelElements(marker_p).get(0);

    if (null != obj) {
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return getLabel();
        }

        public void run() {
          if (obj instanceof AbstractFunction) {
            AbstractFunction function = (AbstractFunction) obj;
            String condition = function.getCondition();
            if ((null != condition) && !condition.equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
              function.setCondition(ICommonConstants.EMPTY_STRING);
            }
          }
        }
      };

      // execute the command
      TransactionHelper.getExecutionManager(obj).execute(abstrctCommand);
      try {
        marker_p.delete();
      } catch (CoreException exception_p) {
        // Do nothing
      }
    }
  }
}
