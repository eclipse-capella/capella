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
package org.polarsys.capella.core.model.obfuscator;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellacore.AbstractAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class ObfuscatorHelper {
  /**
   * Obfuscates a capella element if apply. Do nothing instead.<br>
   * 
   * @deprecated should not be used. use ObfuscateSemanticResourceCommand instead.
   */
  @Deprecated
  public static void obfuscateCapellaElement(EObject currentObject) {
    if (currentObject instanceof AbstractNamedElement) {
      // The new name is a generated Id. It shouldn't be digest.
      AbstractNamedElement ane = (AbstractNamedElement) currentObject;
      ane.setName(generateUnreadableString(ane.getName()));
    }
    if (currentObject instanceof CapellaElement) {
      CapellaElement capellaElement = (CapellaElement) currentObject;
      capellaElement.setDescription(generateUnreadableString(capellaElement.getDescription()));
      capellaElement.setSummary(generateUnreadableString(capellaElement.getSummary()));
    }
    if (currentObject instanceof AbstractAnnotation) {
      AbstractAnnotation ann = (AbstractAnnotation) currentObject;
      ann.setContent(generateUnreadableString(ann.getContent()));
    }
  }

  /**
   * generate Unreadable string.
   * @return
   */
  public static String generateUnreadableString(String uncrypted) {
    if (uncrypted == null) {
      return null;
    }
    if ("".equals(uncrypted)) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    String createId = IdGenerator.createId();
    for (int i = 0, j = 0; i < uncrypted.length(); i++, j++) {
      if (j >= createId.length()) {
        j = 0;
      }
      builder.append(createId.charAt(j));
    }
    return builder.toString();
  }
}
