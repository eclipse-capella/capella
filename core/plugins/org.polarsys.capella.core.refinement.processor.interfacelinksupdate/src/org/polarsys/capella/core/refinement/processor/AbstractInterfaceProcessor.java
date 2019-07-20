/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.processor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class AbstractInterfaceProcessor implements IProcessor {

  private CapellaElement context = null;
  private CapellaElement target = null;

  /**
   * Default constructor
   */
  public AbstractInterfaceProcessor() {
    /** do nothing */
  }

  /**
   * Constructor
   * 
   * @param context the Element on which the processing will applied
   */
  public AbstractInterfaceProcessor(CapellaElement context) {
    setContext(context);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return null;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   */
  public void setContext(List<ModelElement> context) {
    if ((context != null) && (context.size()>0)) {
      setContext(context.get(0));
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public void setContext(ModelElement context) {
    if (context instanceof Component) {
      context = (Component) context;
    } else if (context instanceof ComponentArchitecture) {
      context = (ComponentArchitecture) context;
    } else if (context instanceof Scenario) {
      context = ScenarioExt.getContainer((Scenario) context); 
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
   */
  public void setTarget(NamedElement target) {
    this.target = target;
  }

  /**
   * if ('target' is NULL)<br>
   *  - synchronizes all the components implementing 'context'<br>
   * else<br>
   *  - synchronizes 'context' implemented components<br>
   * 
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute()
   * @throws ProcessorException
   */
  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
    if (target == null) {
      if (context instanceof LogicalComponent) {
        if (!ComponentExt.isComposite((LogicalComponent) context)) {
          execute((LogicalComponent) context);
        }
      } else if (context instanceof LogicalArchitecture) {
        for (LogicalComponent lc : SystemEngineeringExt.getAllLogicalComponents(context)) {
          if (!ComponentExt.isComposite(lc)) {
            execute(lc);
          }
        }
      } else if (context instanceof PhysicalComponent) {
        execute((PhysicalComponent) context);
      } else if (context instanceof PhysicalArchitecture) {
        for (PhysicalComponent pc : SystemEngineeringExt.getAllPhysicalComponents(context)) {
          execute(pc);
        }
      }
    } else {
      if (target instanceof PhysicalComponent) {
        synchronize((PhysicalComponent) target);
      } else if (target instanceof PhysicalArchitecture) {
        for (PhysicalComponent pc : SystemEngineeringExt.getAllPhysicalComponents(target)) {
          synchronize(pc);
        }
      } else if (target instanceof ConfigurationItem) {
        synchronize((ConfigurationItem) target);
      } else if (target instanceof EPBSArchitecture) {
        for (ConfigurationItem epbs : SystemEngineeringExt.getAllConfigurationItems(target)) {
          synchronize(epbs);
        }
      }
    }
  }

  protected void execute(System currentElt) {
    for (Component implementorLC : currentElt.getAllocatingComponents()) {
      if (implementorLC instanceof LogicalComponent) {
        synchronize((LogicalComponent) implementorLC);
      }
    }
  }

  protected void execute(LogicalComponent currentElt) {
    for (PhysicalComponent implementorPC : LogicalComponentExt.getImplementors(currentElt)) {
      synchronize(implementorPC);
    }
  }

  protected void execute(PhysicalComponent currentElt) {
    for (ConfigurationItem implementorEPBS : PhysicalComponentExt.getImplementors(currentElt)) {
      synchronize(implementorEPBS);
    }
  }

  protected abstract void synchronize(LogicalComponent component);
  protected abstract void synchronize(PhysicalComponent component);
  protected abstract void synchronize(ConfigurationItem component);
}
