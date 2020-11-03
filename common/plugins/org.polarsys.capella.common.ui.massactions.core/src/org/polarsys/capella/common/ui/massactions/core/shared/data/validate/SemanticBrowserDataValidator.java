/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.data.validate;

import org.eclipse.nebula.widgets.nattable.data.validate.ValidationFailedException;
import org.polarsys.kitalpha.massactions.core.data.validate.MADataValidator;

/**
 * A data validator handling semantic browser queries.
 * 
 * @author Sandu Postaru
 *
 */
public class SemanticBrowserDataValidator extends MADataValidator {

  @Override
  public boolean validate(int columnIndex, int rowIndex, Object newValue) {

    boolean isValid = (newValue != null);

    if (!isValid) {
      throw new ValidationFailedException("The new value " + newValue + " is invalid!");
    }
    return isValid;
  }
}
