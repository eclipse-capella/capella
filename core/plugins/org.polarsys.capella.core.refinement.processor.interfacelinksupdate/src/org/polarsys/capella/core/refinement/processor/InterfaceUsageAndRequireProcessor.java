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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 */
public class InterfaceUsageAndRequireProcessor extends AbstractInterfaceProcessor {

  /**
   * Default constructor
   */
  public InterfaceUsageAndRequireProcessor() {
    super();
  }

  /**
   * Constructor
   * 
   * @param context the Element on which the processing will applied
   */
  public InterfaceUsageAndRequireProcessor(CapellaElement context) {
    super(context);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "interface usage update"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.la.LogicalComponent)
   */
  @Override
  protected void synchronize(LogicalComponent component) {
    //
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.pa.PhysicalComponent)
   */
  @Override
  protected void synchronize(PhysicalComponent component) {
    List<Interface> usedItf       = InterfaceProcessorHelper.computeUsageToAdd(component);
    List<Interface> unUsedItf     = InterfaceProcessorHelper.computeUsageToRemove(component, usedItf, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    List<Interface> requiredItf   = InterfaceProcessorHelper.computeRequireToAdd(component);
    List<Interface> unrequiredItf = InterfaceProcessorHelper.computeRequireToRemove(component, requiredItf, PaPackage.Literals.PHYSICAL_ARCHITECTURE);

    // Add and Remove Interface Use link computed
    InterfaceProcessorHelper.addUseInterface(component, usedItf, unUsedItf);
    InterfaceProcessorHelper.removeUseInterface(component, unUsedItf);
    
    // Add and Remove Interface Required computed
    InterfaceProcessorHelper.addRequireInterface(component, requiredItf, unrequiredItf);
    InterfaceProcessorHelper.removeRequireInterface(component, unrequiredItf);
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.epbs.ConfigurationItem)
   */
  @Override
  protected void synchronize(ConfigurationItem component) {
    List<Interface> usedItf       = InterfaceProcessorHelper.computeUsageToAdd(component);
    List<Interface> unUsedItf     = InterfaceProcessorHelper.computeUsageToRemove(component, usedItf, EpbsPackage.Literals.EPBS_ARCHITECTURE);
    List<Interface> requiredItf   = InterfaceProcessorHelper.computeRequireToAdd(component);
    List<Interface> unrequiredItf = InterfaceProcessorHelper.computeRequireToRemove(component, requiredItf, EpbsPackage.Literals.EPBS_ARCHITECTURE);
    
    // Add and Remove Interface Use link computed between CI and Interface
    InterfaceProcessorHelper.addUseInterface(component, usedItf, unUsedItf);
    InterfaceProcessorHelper.removeUseInterface(component, unUsedItf);
    
    // Add and Remove Interface Required computed between CI and Interface
    InterfaceProcessorHelper.addRequireInterface(component, requiredItf, unrequiredItf);
    InterfaceProcessorHelper.removeRequireInterface(component, unrequiredItf);
  }
}
