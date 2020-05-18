/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.kitalpha.massactions.core.data.convert.MADisplayConverter;

/**
 * A display converter handling many (multiple) references.
 * 
 * @author Sandu Postaru
 *
 */
public class ManyRefDisplayConverter extends MADisplayConverter {

  @Override
  @SuppressWarnings("unchecked")
  public String canonicalToDisplayValue(Object canonicalValue) {

    if (canonicalValue instanceof Collection<?>) {
      Collection<EObject> valueList = (Collection<EObject>) canonicalValue;
      return valueList.stream().map(EObjectLabelProviderHelper::getText).collect(Collectors.joining(", "));
    }

    // default behavior
    return EObjectLabelProviderHelper.getText(canonicalValue);
  }
}
