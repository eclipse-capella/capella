/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationPreferences;

public class InterfaceInfo {

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((provider == null) ? 0 : provider.hashCode());
    result = prime * result + ((requirer == null) ? 0 : requirer.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    InterfaceInfo other = (InterfaceInfo) obj;
    if (provider == null) {
      if (other.provider != null)
        return false;
    } else if (!provider.equals(other.provider)) {
    	return false;    	
    }
    if (requirer == null) {
      if (other.requirer != null)
        return false;
    } else if (!requirer.equals(other.requirer)) {
    	return false;    	
    }
    return true;
  }

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.REFINEMENT);

 
  public static final String DEFAULT_PROVIDER_NAME = "<provider>"; //$NON-NLS-1$
  public static final String DEFAULT_REQUIRER_NAME = "<requirer>"; //$NON-NLS-1$

  private Collection<FunctionalExchange> exchanges;
  private Interface iface;
  private boolean created;

  private final InterfaceProvider provider;
  private final InterfaceRequirer requirer;
  private TracingStrategy traceStrategy;

  /* optimization flag set when more than one candidate interface between provider/requirer was found before */
  private boolean errMultipleExisting;

  /* optimization flag set when update was called before */
  private boolean updated;

  public InterfaceInfo(InterfaceProvider provider, InterfaceRequirer requirer, TracingStrategy strategy) {
    this.provider = provider;
    this.requirer = requirer;
    this.traceStrategy = strategy;
  }
  
  InterfaceProvider getProvider(){
    return provider;
  }
  
  InterfaceRequirer getRequirer(){
    return requirer;
  }
 
 public Collection<FunctionalExchange> getFunctionalExchanges(){
   if (exchanges == null){
     exchanges = computeFunctionalExchanges();
   }
   return exchanges;
 }
 
 @SuppressWarnings("unchecked")
private Collection<FunctionalExchange> computeFunctionalExchanges(){
   if (provider == null || requirer == null){
     return Collections.emptyList();
   }

   Collection<FunctionalExchange> providerExchanges = new LinkedHashSet<>();
   for (FunctionInputPort fip : provider.getFunctionInputPorts()){
     providerExchanges.addAll((Collection<? extends FunctionalExchange>) EObjectExt.getReferencers(fip, FaPackage.Literals.FUNCTIONAL_EXCHANGE, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET));
   }

   Collection<FunctionalExchange> requirerExchanges = new ArrayList<>();
   for (FunctionOutputPort fop : requirer.getFunctionOutputPorts()){
     requirerExchanges.addAll((Collection<? extends FunctionalExchange>)EObjectExt.getReferencers(fop, FaPackage.Literals.FUNCTIONAL_EXCHANGE, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE));
   }

   providerExchanges.retainAll(requirerExchanges);
   return providerExchanges;
 }

  public Interface getInterface(boolean create) {
    if (errMultipleExisting) {
      return null;
    }
    if (iface == null){
      Collection<Interface> existing = traceStrategy.getTracingInterfaces(this);
      if (existing.size() > 1){
        errMultipleExisting = true;
        errorMultipleExistingInterfacesFound(existing);
      } else if (existing.size() == 1){
        iface = existing.iterator().next();
      } else if (create) {
        created = true;
        iface = CsFactory.eINSTANCE.createInterface(initializeInterfaceName());
        // rule.attach() broke something, don't remember what it was, so attach here..
        BlockArchitecture ctx = BlockArchitectureExt.getRootBlockArchitecture(provider == null ? requirer.getEObject() : provider.getEObject());
        InterfacePkg pkg = ctx.getOwnedInterfacePkg();
        if (pkg == null) {
          ctx.setOwnedInterfacePkg(CsFactory.eINSTANCE.createInterfacePkg());
          pkg = ctx.getOwnedInterfacePkg();
        }
        pkg.getOwnedInterfaces().add(iface);
        if (logger.isInfoEnabled()) {
          logger.info(new EmbeddedMessage(NLS.bind("Created Interface ''{0}''", EObjectLabelProviderHelper.getText(iface)), logger.getName(), //$NON-NLS-1$
              new Object[] { iface }));
        }
      }
    }
    return iface;
  }



  /**
   * Set the name of the generated interface according to specification rules.
   */
  private String initializeInterfaceName() {
    String name = null;
    Collection<ComponentExchange> commonExchanges = getComponentExchanges();
    if (provider == null) {
      name = EObjectLabelProviderHelper.getText(requirer.getEObject()) + "( REQUIRER )";
    } else if (requirer == null) {
      name = EObjectLabelProviderHelper.getText(provider.getEObject()) + "( PROVIDER )";
    } else if (commonExchanges.size() > 1) {
      name = getProviderRequirerName();
    } else if (commonExchanges.size() == 1) {
      name = commonExchanges.iterator().next().getName();
    } else if (getFunctionalExchanges().size() > 1) {
      name = getProviderRequirerName();
    } else if (getFunctionalExchanges().size() == 1) {
      name = getFunctionalExchanges().iterator().next().getName();
    } 
    return name;
  }

  private String getProviderRequirerName() {
    return String.format("%s-%s", EObjectLabelProviderHelper.getText(provider.getEObject()), EObjectLabelProviderHelper.getText(requirer.getEObject())); //$NON-NLS-1$
  }

  /**
   * If no component exchange exists between the provider/requirer port, create one and allocate all related functional
   * exchanges on it.
   */
  private void createMissingComponentExchange() {
    if (getComponentExchanges().isEmpty() && provider instanceof ComponentPortInterfaceAdapter && requirer instanceof ComponentPortInterfaceAdapter) {

      ComponentExchange exchange = FaFactory.eINSTANCE.createComponentExchange();
      exchange.setTarget((ComponentPort)provider.getEObject());
      exchange.setSource((ComponentPort)requirer.getEObject());
      exchange.setName(getProviderRequirerName());
      ComponentExchangeExt.attachToDefaultContainer(exchange);

      if (logger.isInfoEnabled()) {
        logger.info(new EmbeddedMessage(
            NLS.bind("Creating component exchange ''{0}'' between ''{1}'' and ''{2}''", //$NON-NLS-1$
                new Object[] { EObjectLabelProviderHelper.getText(exchange),
                    EObjectLabelProviderHelper.getText(provider.getEObject()),
                    EObjectLabelProviderHelper.getText(requirer.getEObject())}),
            logger.getName(),
            new Object[] { exchange, provider.getEObject(), requirer.getEObject() }));
      }

      for (FunctionalExchange fe : getFunctionalExchanges()) {
        ComponentExchangeFunctionalExchangeAllocation alloc = FaFactory.eINSTANCE
            .createComponentExchangeFunctionalExchangeAllocation();
        alloc.setSourceElement(exchange);
        alloc.setTargetElement(fe);
        exchange.getOwnedComponentExchangeFunctionalExchangeAllocations().add(alloc);
        if (logger.isInfoEnabled()) {
          logger.info(new EmbeddedMessage(
              NLS.bind("Allocating functional exchange ''{0}'' on ''{1}''", //$NON-NLS-1$
                  new Object[] { EObjectLabelProviderHelper.getText(fe),
                      EObjectLabelProviderHelper.getText(exchange), }),
              logger.getName(), new Object[] { fe, exchange }));
        }
      }
    }
  }

  /**
   * Find exchanges that the two ports have in common.
   */
  public Collection<ComponentExchange> getComponentExchanges() {
    Collection<ComponentExchange> result = new ArrayList<>();
    if (provider != null && requirer != null){
      for (ComponentExchange e : provider.getComponentExchanges()) {
        if (requirer.getComponentExchanges().contains(e)) {
          result.add(e);
        }
      }
    }
    return result;
  }

  private void errorMultipleExistingInterfacesFound(Collection<Interface> existing){
    Collection<Object> elems = new ArrayList<>(existing);
    if (provider != null){
      elems.add(provider.getEObject());
    }
    if (requirer != null){
      elems.add(requirer.getEObject());      
    }
    
    String providerTxt = provider == null ? null : "provider " + provider.getText();
    String requirerTxt = requirer == null ? null : "requirer " + requirer.getText();
    
    StringBuilder builder = new StringBuilder("Skipping generation for ");
    if (providerTxt != null) {
      builder.append(providerTxt);
      if (requirerTxt != null) {
        builder.append(" / ");
        builder.append(requirerTxt);
      }
    } else {
      builder.append(requirerTxt);
    }

    builder.append(" : Multiple candidate interfaces found");
    logger.error(new EmbeddedMessage(builder.toString(), logger.getName(), elems));
  }

  public Collection<ExchangeItem> getExchangeItems(InterfaceGenerationPreferences prefs){
    Collection<ExchangeItem> result = new LinkedHashSet<>();
    if (prefs.includeExchangeItemsFromFunctionalExchanges()){
      for (FunctionalExchange exchange : getFunctionalExchanges()){
        result.addAll(exchange.getExchangedItems());
      }
    }
    if (prefs.includeExchangeItemsFromComponentExchanges()){
      for (ComponentExchange ce : getComponentExchanges()){
        for (AbstractExchangeItem ei : ce.getConvoyedInformations()) {
          if (ei instanceof ExchangeItem){          
            result.add((ExchangeItem) ei);
          }
        }
      }
    }
    
    if (provider == null && requirer != null) {
      for (FunctionOutputPort p : requirer.getFunctionOutputPorts()) {
        result.addAll(p.getOutgoingExchangeItems());
      }
    }
    
    if (requirer == null && provider != null){
      for (FunctionInputPort p : provider.getFunctionInputPorts()) {
        result.addAll(p.getIncomingExchangeItems());
      }
    }

    return result;
  }

  void update(InterfaceGenerationPreferences prefs){
    if (iface != null && !updated){
        updated = true;
        traceStrategy.traceInterface(iface, this);
        ExchangeItemUpdater.updateAddMissing(iface, getExchangeItems(prefs));
        new PortReferenceUpdater(this).updatePortReferences();
        if (prefs.isGenerateComponentExchanges()){
          createMissingComponentExchange();
        }
        if (prefs.isPropagateExchangeItemsToFunctionPorts()){
          for (FunctionalExchange e : getFunctionalExchanges()){
            ExchangeItemUpdater.propagateExchangeItemsToFunctionPorts(e);
          }
        }
    }
  }

  /**
   * 
   * @return
   */
  public boolean isNew() {
    return created;
  }
}
