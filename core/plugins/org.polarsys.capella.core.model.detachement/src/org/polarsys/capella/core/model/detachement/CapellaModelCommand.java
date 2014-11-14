/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.detachement;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.kitalpha.model.common.commands.action.ModelCommand;
import org.polarsys.kitalpha.model.common.commands.exception.ModelCommandException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.Scrutineer;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;

/**
 *
 */
public class CapellaModelCommand extends ModelCommand {

  /**
   * 
   */
  public CapellaModelCommand() {
    // nothing to do
  }

  /**
   * @see org.polarsys.kitalpha.model.common.commands.action.ModelCommand#exec(org.eclipse.emf.ecore.resource.Resource, org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void exec(Resource resource, IProgressMonitor monitor) throws ModelCommandException {
    try {
      monitor.beginTask("remove library", 1);
      SubMonitor subMonitor = SubMonitor.convert(monitor);

      Collection<IModel> modelLibraries = new HashSet<IModel>();
      RegistryElement regElt = Scrutineer.getRegistryElement(getModelAnalysisID());
      for (IScrutinize<Map<IModel, Boolean>, String> scr : regElt.getFinders()) {
        Map<IModel, Boolean> models = (Map<IModel, Boolean>) scr.getAnalysisResult();
        for (IModel model : models.keySet()) {
          if (models.get(model)) {
            modelLibraries.add(model);
          }
        }
      }

      //TODO to implement : detach libs in 'modelLibraries'

      subMonitor.worked(1);
      subMonitor.done();

    } catch (ModelScrutinyException e) {
      e.printStackTrace();
    }
  }
}
