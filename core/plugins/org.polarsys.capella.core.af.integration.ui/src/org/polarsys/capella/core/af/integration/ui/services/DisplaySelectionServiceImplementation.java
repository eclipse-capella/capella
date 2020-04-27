/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.af.integration.ui.services;

import java.util.Arrays;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.af.integration.services.CapellaServiceImplementation;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Service;
import org.polarsys.kitalpha.ad.viewpoint.utils.ModelAccessor;

/**
 * 
 */
public class DisplaySelectionServiceImplementation extends CapellaServiceImplementation {

  public void run(Service service, ModelAccessor properties, Object[] selection) {
    String string = service.getId() + " -> " + Arrays.toString(selection);
    MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Capella Selection Result", string);
    properties.getProperties();
  }

  public boolean canRun(Service action, ModelAccessor properties, Object[] selection) {
    return true;
  }
}
