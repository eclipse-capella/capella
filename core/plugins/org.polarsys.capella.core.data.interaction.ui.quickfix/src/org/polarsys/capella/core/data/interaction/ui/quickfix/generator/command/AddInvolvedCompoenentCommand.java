/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.interaction.ui.quickfix.generator.command;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.formatterLabels.FormatterLabels;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.cs.Component;

public class AddInvolvedCompoenentCommand extends AbstractReadWriteCommand {
  private final AbstractCapability capability;
  private final Component component;
  private final List<EObject> modelElements;
  private final String label;

  public AddInvolvedCompoenentCommand(String label, AbstractCapability capability, Component component, List<EObject> modelElements) {
    this.capability = capability;
    this.component = component;
    this.label = label;
    this.modelElements = modelElements;
  }
  
  private void addComponentCapabilityInvolvment() {
    if (!modelElements.isEmpty()) {
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        public void run() {
          AbstractCapabilityExt.addInvolvedComponent(capability, component);
        }
      });
    }
  }

  public AbstractCapability getCapability() {
    return capability;
  }

  public Component getComponent() {
    return component;
  }
  
  public List<EObject> getModelElements() {
    return modelElements;
  }

  public String getLabel() {
    return label;
  }
  
  @Override
  public String getName() {
    return label + " " + capability.getName() + " (" + FormatterLabels.getLevelOfCapability(capability) + ") to "
        + component.getName() + " (" + FormatterLabels.getLevelOfComponents(component) + ")";
  }

  @Override
  public void run() {
    if(capability instanceof AbstractCapability) {
      if(component instanceof Component) {
        if (!AbstractCapabilityExt.getInvolvedComponents(capability).contains(component)) {
          addComponentCapabilityInvolvment();
        }
      }
    }
  }

}
