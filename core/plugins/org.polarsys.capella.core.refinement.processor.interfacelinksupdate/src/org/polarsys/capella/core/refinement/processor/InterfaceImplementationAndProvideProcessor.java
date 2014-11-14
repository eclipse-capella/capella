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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 *
 */
public class InterfaceImplementationAndProvideProcessor extends AbstractInterfaceProcessor {

  /**
   * Default constructor
   */
  public InterfaceImplementationAndProvideProcessor() {
    super();
  }

  /**
   * Constructor
   * 
   * @param context_p the Element on which the processing will applied
   */
  public InterfaceImplementationAndProvideProcessor(CapellaElement context_p) {
    super(context_p);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "interface implementation update"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.la.LogicalComponent)
   */
  @Override
  protected void synchronize(LogicalComponent component_p) {
    //
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.pa.PhysicalComponent)
   */
  @Override
  protected void synchronize(PhysicalComponent component_p) {
    List<Interface> implementedItf   = InterfaceProcessorHelper.computeImplementationToAdd(component_p);
    List<Interface> unImplementedItf = InterfaceProcessorHelper.computeImplementationToRemove(component_p, implementedItf, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    List<Interface> providedItf      = InterfaceProcessorHelper.computeProvideToAdd(component_p);
    List<Interface> unprovidedItf    = InterfaceProcessorHelper.computeProvideToRemove(component_p, providedItf, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    
    // Add and Remove Interface Implementation link computed
    InterfaceProcessorHelper.addImplementationInterface(component_p, implementedItf, unImplementedItf);
    InterfaceProcessorHelper.removeImplementationInterface(component_p, unImplementedItf);

    // Add and Remove Interface Provided computed      
    InterfaceProcessorHelper.addProvideInterface(component_p, providedItf, unprovidedItf);
    InterfaceProcessorHelper.removeProvideInterface(component_p, unprovidedItf);
  }

  /**
   * @see org.polarsys.capella.core.refinement.processor.AbstractInterfaceProcessor#synchronize(org.polarsys.capella.core.data.epbs.ConfigurationItem)
   */
  @Override
  protected void synchronize(ConfigurationItem component_p) {
    List<Interface> implementedItf   = InterfaceProcessorHelper.computeImplementationToAdd(component_p);
    List<Interface> unImplementedItf = InterfaceProcessorHelper.computeImplementationToRemove(component_p, implementedItf, EpbsPackage.Literals.EPBS_ARCHITECTURE);
    List<Interface> providedItf      = InterfaceProcessorHelper.computeProvideToAdd(component_p);
    List<Interface> unprovidedItf    = InterfaceProcessorHelper.computeProvideToRemove(component_p, providedItf, EpbsPackage.Literals.EPBS_ARCHITECTURE);
    
    // Add and Remove Interface Implementation link computed between CI and Interface
    InterfaceProcessorHelper.addImplementationInterface(component_p, implementedItf, unImplementedItf);
    InterfaceProcessorHelper.removeImplementationInterface(component_p, unImplementedItf);
    
    // Add and Remove Interface Provided computed between CI and Interface     
    InterfaceProcessorHelper.addProvideInterface(component_p, providedItf, unprovidedItf);
    InterfaceProcessorHelper.removeProvideInterface(component_p, unprovidedItf);
  }
}
