/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.validation.scenario.InstanceRoleHasDifferentNameThanRepresentedInstance.InstanceRoleRenamingData;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class InstanceRoleHasDifferentNameThanRepresentedInstanceResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {

    Optional<InstanceRoleRenamingData> renamingDataOptional = extractRenamingData(marker);

    if (renamingDataOptional.isPresent()) {
      InstanceRoleRenamingData renamingData = renamingDataOptional.get();
      renameInstanceRole(renamingData.instanceRole, renamingData.newName);
      deleteMarker(marker);
    }

  }

  private Optional<InstanceRoleRenamingData> extractRenamingData(IMarker marker) {
    return MarkerViewHelper.getModelElementsFromMarker(marker) //
        .stream() //
        .filter(InstanceRoleRenamingData.class::isInstance) //
        .map(InstanceRoleRenamingData.class::cast) //
        .findAny();
  }

  private void renameInstanceRole(InstanceRole instanceRole, String newName) {
    TransactionHelper.getExecutionManager(instanceRole).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        instanceRole.setName(newName);
      }
    });
  }
}
