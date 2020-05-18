/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.massactions.core.shared.data.convert;

import java.util.Collection;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.kitalpha.massactions.core.data.convert.MADisplayConverter;

/**
 * A display converter handling semantic browser values.
 * 
 * @author Sandu Postaru
 *
 */
public class SemanticBrowserDisplayConverter extends MADisplayConverter {

  @SuppressWarnings("unchecked")
  @Override
  public String canonicalToDisplayValue(Object canonicalValue) {

    if (canonicalValue instanceof Collection<?>) {
      Collection<Object> valueList = (Collection<Object>) canonicalValue;
      return valueList.stream().map(EObjectLabelProviderHelper::getText).collect(Collectors.joining(", "));
    }

    // default behavior
    return EObjectLabelProviderHelper.getText(canonicalValue);
  }

}
