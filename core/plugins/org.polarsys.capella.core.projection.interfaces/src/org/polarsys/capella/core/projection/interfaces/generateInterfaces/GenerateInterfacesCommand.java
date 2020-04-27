/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.AbstractTransitionCommand;
import org.polarsys.capella.core.projection.interfaces.InterfaceGeneration;

/**
 * 
 */
public class GenerateInterfacesCommand extends AbstractTransitionCommand {

  public GenerateInterfacesCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public GenerateInterfacesCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return Messages.generateInterfaces_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {
    EObject modelElement = modelElement_p;
    if (modelElement instanceof Part) {
      Part part = (Part) modelElement;
      if (part.getAbstractType() instanceof Component) {
        modelElement = part.getAbstractType();
      }
    }
    return Collections.singleton(modelElement);
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new InterfaceGeneration();
  }
  
}
