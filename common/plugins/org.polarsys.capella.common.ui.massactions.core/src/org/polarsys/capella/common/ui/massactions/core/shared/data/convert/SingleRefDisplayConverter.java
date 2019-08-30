/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.data.convert;

import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.kitalpha.massactions.core.data.convert.MADisplayConverter;

/**
 * A display converter handling single references.
 * 
 * @author Sandu Postaru
 *
 */
public class SingleRefDisplayConverter extends MADisplayConverter {

  @Override
  public String canonicalToDisplayValue(Object canonicalValue) {
    return EObjectLabelProviderHelper.getText(canonicalValue);
  }
}
