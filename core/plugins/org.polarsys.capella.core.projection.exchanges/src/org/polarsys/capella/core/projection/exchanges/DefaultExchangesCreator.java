/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.exchanges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;

/**
 * The default <code>IExchangesCreator</code>. Its behavior is to create component exchanges related to functional
 * exchanges.
 */
public class DefaultExchangesCreator implements IExchangesCreator {

  protected static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.DEFAULT);
  /**
   * The component which will be the starting point of the exchanges creation
   */
  protected Component _component;

  /**
   * Constructor
   * 
   * @param component_p
   *          the component which will be the starting point of the exchanges creation
   */
  public DefaultExchangesCreator(Component component_p) {
    _component = component_p;
  }

  /**
   * This implementation create component exchanges.
   * 
   * @see org.polarsys.capella.core.projection.commands.utils.IExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    boolean exchangeCreated = false;
    // Retrieve all functions allocated in component and sub-components
    // > we'll disable creation between internal functions
    List<AbstractFunction> lf = new ArrayList<>();
    Collection<Component> components = ComponentExt.getAllSubUsedComponents(_component);
    components.add(_component);
    for (Component subComponent : components) {
      for (AbstractFunction function : subComponent.getAllocatedFunctions()) {
        lf.add(function);
      }
    }

    // Find functional exchanges of components and proceed creation
    for (Component component : components) {
      EList<AbstractFunction> laf = component.getAllocatedFunctions();
      for (AbstractFunction abstractFunction : laf) {
        // Process outgoing exchanges
        for (FunctionalExchange fe : FunctionExt.getOutGoingExchange(abstractFunction)) {
          if (fe.getIncomingComponentExchangeFunctionalExchangeRealizations().isEmpty()) {
            EObject targetObject = fe.getTarget();
            AbstractFunction targetF;
            if (targetObject instanceof AbstractFunction) {
              targetF = (AbstractFunction) targetObject;
            } else {
              targetF = (AbstractFunction) targetObject.eContainer();
            }

            for (ComponentFunctionalAllocation componentFunctionalAllocation : targetF
                .getComponentFunctionalAllocations()) {
              TraceableElement allocating = componentFunctionalAllocation.getSourceElement();
              if ((allocating instanceof Component) && !lf.contains(targetF)
                  && isValidCreation(fe, component, (Component) allocating)) {
                doCreateExchange(fe, component, (Component) allocating);
                exchangeCreated = true;
              }
            }
          }
        }

        // Process incoming exchanges
        for (FunctionalExchange fe : FunctionExt.getIncomingExchange(abstractFunction)) {
          if (fe.getIncomingComponentExchangeFunctionalExchangeRealizations().isEmpty()) {
            EObject sourceObject = fe.getSource();
            AbstractFunction sourceF;
            if (sourceObject instanceof AbstractFunction) {
              sourceF = (AbstractFunction) sourceObject;
            } else {
              sourceF = (AbstractFunction) sourceObject.eContainer();
            }
            for (ComponentFunctionalAllocation componentFunctionalAllocation : sourceF
                .getComponentFunctionalAllocations()) {
              TraceableElement allocating = componentFunctionalAllocation.getSourceElement();
              if ((allocating instanceof Component) && !lf.contains(sourceF)
                  && isValidCreation(fe, component, (Component) allocating)) {
                doCreateExchange(fe, (Component) allocating, component);
                exchangeCreated = true;
              }
            }
          }
        }
      }
    }
    if (!exchangeCreated) {
      String message = "No component exchange has been created.";
      EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName());
      logger.info(eMessage);
    }
  }

  /**
   * Returns whether a component which will be a bound of a created exchange is valid or not
   * 
   * @param component_p
   * @return
   */
  protected boolean isValidBound(Component component_p) {
    if (component_p instanceof PhysicalComponent) {
      return true;
    }
    if (component_p instanceof LogicalComponent) {
      return true;
    }
    if ((component_p instanceof SystemComponent) || ComponentExt.isComponentRoot(component_p)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether both given component are valid for a creation of a realizing exchange of the given functional
   * exchange
   * 
   * @param fe_p
   * @param component_p
   * @param allocating_p
   * @return
   */
  protected boolean isValidCreation(AbstractEventOperation fe_p, Component component_p, Component allocating_p) {
    return isValidBound(component_p) && isValidBound(allocating_p);
  }

  /**
   * Create an exchange related to the given functional exchange between the given components. For this implementation,
   * creates a component exchange. Subclasses implementations may create different exchanges types.
   * 
   * @param functionalExchange_p
   *          the functional exchange
   * @param exchangeOutput_p
   *          the exchange output component
   * @param exchangeInput_p
   *          the exchange input component
   */
  protected void doCreateExchange(FunctionalExchange functionalExchange_p, Component exchangeOutput_p,
      Component exchangeInput_p) {
    ComponentExchange ce = FaFactory.eINSTANCE.createComponentExchange(functionalExchange_p.getName());

    if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(functionalExchange_p))) {
      ce.setKind(ComponentExchangeKind.ASSEMBLY);
    } else {
      ce.setKind(ComponentExchangeKind.FLOW);
    }

    // Retrieve (or create) ports and set bounds of connections
    ComponentPort outP = getRelatedProvidingPort(exchangeOutput_p, functionalExchange_p.getSource(),
        functionalExchange_p);
    ComponentPort inP = getRelatedRequiringPort(exchangeInput_p, functionalExchange_p.getTarget(),
        functionalExchange_p);
    ce.setSource(outP);
    ce.setTarget(inP);
    ComponentExchangeExt.attachToDefaultContainer(ce);

    // Creates the exchange allocation
    ComponentExchangeFunctionalExchangeAllocation cfea = FaFactory.eINSTANCE
        .createComponentExchangeFunctionalExchangeAllocation();
    cfea.setSourceElement(ce);
    cfea.setTargetElement(functionalExchange_p);
    ce.getOwnedComponentExchangeFunctionalExchangeAllocations().add(cfea);

    String message = "The Component exchange " + ce.getName()
        + " has been succefully created between the exchange source component " + exchangeInput_p.getLabel()
        + " and the exchange target component " + exchangeOutput_p.getLabel();
    EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(),
        Arrays.asList(ce, exchangeInput_p, exchangeOutput_p));
    logger.info(eMessage);
  }

  /**
   * @param exchangeOutput_p
   * @param source_p
   * @param functionalExchange_p
   * @return
   */
  private ComponentPort getRelatedProvidingPort(Component component_p, ActivityNode fport, FunctionalExchange a_p) {
    ComponentPort port = null;

    if ((fport == null) || (component_p == null)) {
      return null;
    }

    Interface relatedInterface = null;
    for (AbstractTrace trace : a_p.getIncomingTraces()) {
      if (trace instanceof TransfoLink) {
        if (trace.getSourceElement() instanceof Interface) {
          relatedInterface = ((Interface) trace.getSourceElement());
          break;
        }
      }
    }

    if (relatedInterface != null) {
      for (Feature f : component_p.getOwnedFeatures()) {
        if (f instanceof ComponentPort) {
          ComponentPort cport = (ComponentPort) f;
          if (cport.getProvidedInterfaces().contains(relatedInterface)) {
            port = cport;
            break;
          }
        }
      }
    }

    if (port == null) {
      port = PortExt.createOutFlowPort(fport.getName());
      component_p.getOwnedFeatures().add(port);
    }

    PortExt.attachPort(port, fport);

    return port;
  }

  /**
   * @param exchangeOutput_p
   * @param source_p
   * @param functionalExchange_p
   * @return
   */
  private ComponentPort getRelatedRequiringPort(Component component_p, ActivityNode fport, FunctionalExchange a_p) {
    ComponentPort port = null;

    if ((fport == null) || (component_p == null)) {
      return null;
    }

    Interface relatedInterface = null;
    for (AbstractTrace trace : a_p.getIncomingTraces()) {
      if (trace instanceof TransfoLink) {
        if (trace.getSourceElement() instanceof Interface) {
          relatedInterface = ((Interface) trace.getSourceElement());
          break;
        }
      }
    }

    if (relatedInterface != null) {
      for (Feature f : component_p.getOwnedFeatures()) {
        if (f instanceof ComponentPort) {
          ComponentPort cport = (ComponentPort) f;
          if (cport.getRequiredInterfaces().contains(relatedInterface)) {
            port = cport;
            break;
          }
        }
      }
    }

    if (port == null) {
      port = PortExt.createInFlowPort(fport.getName());
      component_p.getOwnedFeatures().add(port);
    }

    PortExt.attachPort(port, fport);

    return port;
  }

}
