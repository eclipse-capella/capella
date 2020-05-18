/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.model.settings;

import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;

public class OA_IDProjectSettings extends IDProjectSettings {
  public OA_IDProjectSettings() {
    DATAPKG = EmptyProject.OA__DATA;
    INTERFACEPKG = EmptyProject.OA__INTERFACES;
  }

}
