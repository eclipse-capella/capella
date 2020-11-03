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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;

public class CapellaTransfertViewerLabelProviderIDB extends CapellaTransfertViewerLabelProvider {
	 
  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object_p) {
	  String text = super.getText(object_p);
	  if (object_p instanceof Interface) {
		String providedText = ICommonConstants.EMPTY_STRING; 
		String requiredText = ICommonConstants.EMPTY_STRING;
		if (InterfaceExt.isProvidedByComponentPorts((Interface) object_p)) {
			providedText = "Provided"; //$NON-NLS-1$
		}
		if (InterfaceExt.isRequiredByComponentPorts((Interface) object_p)) {
			if (providedText.equals(ICommonConstants.EMPTY_STRING)) {
				requiredText = "Required"; //$NON-NLS-1$
			}else requiredText = ", Required"; //$NON-NLS-1$
			
		}
		if(providedText.equals(ICommonConstants.EMPTY_STRING) 
				&& requiredText.equals(ICommonConstants.EMPTY_STRING))
		{
			return text;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("["); //$NON-NLS-1$
		stringBuilder.append(providedText);
		stringBuilder.append(requiredText);
		stringBuilder.append("] "); //$NON-NLS-1$
		stringBuilder.append(text);
		return stringBuilder.toString(); 	
	  }
	  
	  return text;
  }
}
