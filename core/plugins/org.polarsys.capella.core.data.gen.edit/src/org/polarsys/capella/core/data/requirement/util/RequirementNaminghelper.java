/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.requirement.util;

import org.polarsys.capella.core.data.requirement.Requirement;

public class RequirementNaminghelper {

  public static String getRequirementLabel(Requirement requirement_p) {
    String id = requirement_p.getRequirementId();
    String name = requirement_p.getName();

    String label = ((null == id) || (id.length() == 0)) ? 
      (((null == name) || (name.length() == 0)) ? "" : name) : //$NON-NLS-1$
      (((null == name) || (name.length() == 0)) ? id : id + " - " + name); //$NON-NLS-1$

    return label;
  }
}
