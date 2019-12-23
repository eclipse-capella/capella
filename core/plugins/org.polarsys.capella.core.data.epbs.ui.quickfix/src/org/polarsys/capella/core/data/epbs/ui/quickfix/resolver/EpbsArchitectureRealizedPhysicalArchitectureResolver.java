/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.epbs.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class EpbsArchitectureRealizedPhysicalArchitectureResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      // execute a read write command
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          PhysicalArchitecture physicalArchitecture = null;
          EPBSArchitecture epbsArchitecture = null;
          Project project = ProjectExt.getProject(modelElements.get(0));
          BlockArchitecture architecture = BlockArchitectureExt
              .getBlockArchitecture(PaPackage.Literals.PHYSICAL_ARCHITECTURE, project);
          physicalArchitecture = (PhysicalArchitecture) architecture;
          for (EObject obj : modelElements) {
            if (obj instanceof EPBSArchitecture) {
              epbsArchitecture = (EPBSArchitecture) obj;
            }
          }
          if (epbsArchitecture != null) {
            PhysicalArchitectureRealization lar = EpbsFactory.eINSTANCE.createPhysicalArchitectureRealization();
            lar.setSourceElement(epbsArchitecture);
            lar.setTargetElement(physicalArchitecture);
            epbsArchitecture.getOwnedPhysicalArchitectureRealizations().add(lar);
          }
        }
      });
    }
    deleteMarker(marker);
  }
}
