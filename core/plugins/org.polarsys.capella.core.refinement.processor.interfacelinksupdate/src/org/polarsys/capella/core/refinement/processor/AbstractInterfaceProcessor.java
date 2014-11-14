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
import org.polarsys.capella.core.model.helpers.ComponentExt;
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

  private CapellaElement _context = null;
  private CapellaElement _target = null;

  /**
   * Default constructor
   */
  public AbstractInterfaceProcessor() {
    /** do nothing */
  }

  /**
   * Constructor
   * 
   * @param context_p the Element on which the processing will applied
   */
  public AbstractInterfaceProcessor(CapellaElement context_p) {
    setContext(context_p);
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
  public void setContext(List<ModelElement> context_p) {
    if ((context_p != null) && (context_p.size()>0)) {
      setContext(context_p.get(0));
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public void setContext(ModelElement context_p) {
    if (context_p instanceof Component) {
      _context = (Component) context_p;
    } else if (context_p instanceof ComponentArchitecture) {
      _context = (ComponentArchitecture) context_p;
    } else if (context_p instanceof Scenario) {
      _context = ScenarioExt.getContainer((Scenario) context_p); 
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
   */
  public void setTarget(NamedElement target_p) {
    _target = target_p;
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
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    if (_target == null) {
      if (_context instanceof LogicalComponent) {
        if (!ComponentExt.isComposite((LogicalComponent) _context)) {
          execute((LogicalComponent) _context);
        }
      } else if (_context instanceof LogicalArchitecture) {
        for (LogicalComponent lc : SystemEngineeringExt.getAllLogicalComponents(_context)) {
          if (!ComponentExt.isComposite(lc)) {
            execute(lc);
          }
        }
      } else if (_context instanceof PhysicalComponent) {
        execute((PhysicalComponent) _context);
      } else if (_context instanceof PhysicalArchitecture) {
        for (PhysicalComponent pc : SystemEngineeringExt.getAllPhysicalComponents(_context)) {
          execute(pc);
        }
      }
    } else {
      if (_target instanceof PhysicalComponent) {
        synchronize((PhysicalComponent) _target);
      } else if (_target instanceof PhysicalArchitecture) {
        for (PhysicalComponent pc : SystemEngineeringExt.getAllPhysicalComponents(_target)) {
          synchronize(pc);
        }
      } else if (_target instanceof ConfigurationItem) {
        synchronize((ConfigurationItem) _target);
      } else if (_target instanceof EPBSArchitecture) {
        for (ConfigurationItem epbs : SystemEngineeringExt.getAllConfigurationItems(_target)) {
          synchronize(epbs);
        }
      }
    }
  }

  protected void execute(System currentElt_p) {
    for (Component implementorLC : currentElt_p.getAllocatingComponents()) {
      if (implementorLC instanceof LogicalComponent) {
        synchronize((LogicalComponent) implementorLC);
      }
    }
  }

  protected void execute(LogicalComponent currentElt_p) {
    for (PhysicalComponent implementorPC : LogicalComponentExt.getImplementors(currentElt_p)) {
      synchronize(implementorPC);
    }
  }

  protected void execute(PhysicalComponent currentElt_p) {
    for (ConfigurationItem implementorEPBS : PhysicalComponentExt.getImplementors(currentElt_p)) {
      synchronize(implementorEPBS);
    }
  }

  protected abstract void synchronize(LogicalComponent component_p);
  protected abstract void synchronize(PhysicalComponent component_p);
  protected abstract void synchronize(ConfigurationItem component_p);
}
