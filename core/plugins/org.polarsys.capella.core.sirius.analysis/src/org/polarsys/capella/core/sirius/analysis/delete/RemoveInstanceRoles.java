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
package org.polarsys.capella.core.sirius.analysis.delete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 *
 */
public class RemoveInstanceRoles implements IExternalJavaAction {

  private static final String RESULT = "result"; //$NON-NLS-1$
  private static final String CURRENT = "current"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#canExecute(java.util.Collection)
   */
  public boolean canExecute(Collection<? extends EObject> selections_p) {
    return true;
  }

  /** 
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @SuppressWarnings("unchecked")
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    if (parameters_p.get(RESULT) instanceof String) {
      return; // canceled
    }
    List<EObject> result = (List<EObject>) parameters_p.get(RESULT);
    List<EObject> current = (List<EObject>) parameters_p.get(CURRENT);

    if (result == null) {
      result = new ArrayList<EObject>();
    }
    if (current == null) {
      current = new ArrayList<EObject>();
    }

    Set<InstanceRole> irToRemove = new HashSet<InstanceRole>();
    for (EObject obj : current) {
      if (obj instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) obj;
        if (!result.contains(ir.getRepresentedInstance())) {
          irToRemove.add(ir);
        }
      }
    }

    CapellaDeleteCommand mdc = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(selections_p), irToRemove, false, false, true);
    if (mdc.canExecute()) {
      // Do execute the command !
      mdc.execute();
    }
  }
}
