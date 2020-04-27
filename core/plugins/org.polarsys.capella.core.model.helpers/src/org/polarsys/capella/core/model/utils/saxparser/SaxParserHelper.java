/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.utils.saxparser;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 */
public class SaxParserHelper {
  /**
   * @param result
   * @return
   */
  public static String escapeSpecialCharacter(String input) {
    String result = input;
    result = result.replace(IConstantValidation.NON_BREAKING_SPACE_NAME_CODE, IConstantValidation.NON_BREAKING_SPACE_NUMBER_CODE);
    result = result.replace(IConstantValidation.EURO_NAME_CODE, IConstantValidation.EURO_NUMBER_CODE);
    result = result.replace(IConstantValidation.TRADE_NAME_CODE, IConstantValidation.TRADE_NUMBER_CODE);
    result = result.replace(IConstantValidation.AMP_NAME_CODE, IConstantValidation.AMP_NUMBER_CODE);

    // if the 5 predefined entities of XML1.0 are present => replace them by their unicode code
    result = result.replace(IConstantValidation.DOUBLE_QUOTES, IConstantValidation.DOUBLE_QUOTES);
    result = result.replace(IConstantValidation.AMP, IConstantValidation.AMP_NUMBER_CODE);
    result = result.replace(IConstantValidation.APOSTROPHE, IConstantValidation.APOSTROPHE_CODE);

    return result;
  }

  public static EObject getEObjectFromHrefAttribute(final EObject context, String attValue) {
    String[] split = attValue.split("hlink://"); //$NON-NLS-1$
    EObject eObject = null;
    if (split.length == 2) {
      String id = split[1].replace("/", ""); //$NON-NLS-1$ //$NON-NLS-2$
      eObject = IdManager.getInstance().getEObject(id, new IScope() {
        @Override
        public List<Resource> getResources() {
          return TransactionHelper.getEditingDomain(context).getResourceSet().getResources();
        }
      });
    }

    return eObject;
  }
}
