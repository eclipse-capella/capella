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
package org.polarsys.capella.core.transition.system.topdown.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

public class TransitionCommandHelper {

  public static TransitionCommandHelper getInstance() {
    return new TransitionCommandHelper();
  }

  public ICommand getActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_ACTOR;
      }
    };
  }

  public ICommand getDataTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_DATA;
      }
    };
  }

  public ICommand getExchangeItemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM;
      }
    };
  }

  public ICommand getFunctionalTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL;
      }
    };
  }

  public ICommand getOCtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OC2SM;
      }
    };
  }

  public ICommand getOAtoSCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SC;
      }
    };

  }

  public ICommand getOAtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SM;
      }
    };

  }

  public ICommand getInterfaceTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE;
      }
    };
  }

  public ICommand getLC2PCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_LC2PC;
      }
    };
  }

  public ICommand getStateMachineTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE;
      }
    };
  }

  public ICommand getCapabilityTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY;
      }
    };
  }

  public ICommand getOE2SystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getOE2ActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getPropertyValueTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getSystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM;
      }
    };
  }

  public ICommand getPC2CITransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {
      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PC2CI;
      }
    };
  }

  public boolean isFunctionalTransitionAvailable(EObject object) {
    return ((object instanceof OperationalAnalysis) || (object instanceof SystemAnalysis)
        || (object instanceof LogicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && ((object instanceof AbstractFunction) || (object instanceof FunctionPkg)
                || (object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof FunctionalExchange) || (object instanceof FunctionalChain)));
  }

  public boolean isInterfaceTransitionAvailable(EObject object) {
    return (object instanceof Interface || object instanceof InterfacePkg)
        && (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object));
  }

  public boolean isExchangeItemTransitionAvailable(EObject object) {
    return ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
        && ((object instanceof ExchangeItem) || (object instanceof ExchangeItemAllocation)));
  }

  public boolean isStateMachineTransitionAvailable(EObject object) {
    return ((object instanceof BlockArchitecture)
        || ((object instanceof Component) && !(((Component) object).getOwnedStateMachines().isEmpty()))
        || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
            && (((Part) object).getAbstractType() instanceof Component)
            && !(((Component) ((Part) object).getAbstractType()).getOwnedStateMachines().isEmpty()))
        || (object instanceof StateMachine) || (object instanceof AbstractState) || (object instanceof StateTransition))
        && ((object instanceof CapellaElement) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) object)
            && !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) object));

  }

  public boolean isDataTransitionAvailable(EObject object) {
    return ((object instanceof BlockArchitecture && (((BlockArchitecture) object).getOwnedDataPkg() != null))
        || ((object instanceof Component) && (((Component) object).getOwnedDataPkg() != null))
        || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
            && (((Part) object).getAbstractType() instanceof Component)
            && (((Component) ((Part) object).getAbstractType()).getOwnedDataPkg() != null))
        || (object instanceof DataPkg) || (object instanceof DataValueContainer) || (object instanceof Collection)
        || (object instanceof CommunicationItem) || (object instanceof Class))
        && ((object instanceof CapellaElement) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) object)
            && !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) object));

  }

  /**
   * An INTERACTION scenario can be a FUNCTIONAL scenario or an DATA_FLOW scenario
   */
  public boolean isFunctionalScenario(Scenario scenario) {

    if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
      return true;

    } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
      for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
        if ((role.getRepresentedInstance() != null) && (role.getRepresentedInstance() instanceof AbstractFunction)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isActorTransitionAvailable(EObject object) {
    if (object instanceof SystemAnalysis || object instanceof LogicalArchitecture) {
      ComponentPkg componentPkg = BlockArchitectureExt.getComponentPkg((BlockArchitecture) object);
      if (componentPkg != null && !ComponentPkgExt.getExternalActors(componentPkg).isEmpty()) {
        return true;
      }
    }
    if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)) {
      Component component = getComponent(object);
      if ((component instanceof SystemComponent && (ComponentExt.isActor(object))) || //
          ((object instanceof SystemComponentPkg && !ComponentPkgExt.getAllActors((ComponentPkg) object).isEmpty())) || //
          ((object instanceof ComponentExchange) && ComponentExchangeExt.isLinkToAnActor((ComponentExchange) object)) || //
          (object instanceof PhysicalLink)) {
        return true;
      }
    }
    if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)) {
      Component component = getComponent(object);
      if ((component instanceof LogicalComponent && (ComponentExt.isExternalActor(component))) || //
          ((object instanceof LogicalComponentPkg
              && !ComponentPkgExt.getExternalActors((ComponentPkg) object).isEmpty()))
          || //
          ((object instanceof ComponentExchange) && ComponentExchangeExt.isLinkToAnActor((ComponentExchange) object)) || //
          (object instanceof PhysicalLink)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isSystemTransitionAvailable(EObject object) {
    if (object instanceof SystemAnalysis) {
      return true;

    } else if (object instanceof SystemComponentPkg && object.eContainer() instanceof BlockArchitecture) {
      return true;

    } else if (object instanceof SystemComponent) {
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
      return (object.equals(architecture.getSystem()));

    } else if (object instanceof Part && CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)) {
      Component component = getComponent(object);
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
      return (component != null && component.equals(architecture.getSystem()));
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isLC2PCTransitionAvailable(EObject object) {
    if (object instanceof LogicalArchitecture) {
      return true;
    }
    if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)) {
      Component component = getComponent(object);
      if (component instanceof LogicalComponent || //
          object instanceof LogicalComponentPkg || //
          (object instanceof ComponentExchange)
              && ComponentExchangeExt.isNotLinkToAnActor((ComponentExchange) object)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isOE2ActorTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof Entity) || (object instanceof EntityPkg) || (object instanceof CommunicationMean)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOE2SystemTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof Entity && !((Entity) object).isHuman()) || (object instanceof EntityPkg)
            || (object instanceof CommunicationMean)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOA2SCTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalActivity) || (object instanceof OperationalActivityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOA2SMTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalActivity) || (object instanceof OperationalActivityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isCapabilityTransitionAvailable(EObject object) {
    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOC2SMTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalCapability) || (object instanceof OperationalCapabilityPkg)));
  }

  /**
   * @param object
   * @return
   */
  private Component getComponent(EObject object) {
    if (null != object) {
      if (object instanceof Component) {
        return (Component) object;
      } else if (object instanceof Part) {
        Part part = (Part) object;
        AbstractType abstractType = part.getAbstractType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    return null;
  }

  /**
   * @param element
   * @return
   */
  public boolean isPropertyValueTransitionAvailable(EObject element) {
    return ((element instanceof CapellaElement)
        && ((element instanceof PropertyValueGroup) || (element instanceof PropertyValuePkg)
            || (element instanceof AbstractPropertyValue) || (element instanceof EnumerationPropertyType))
        && EcoreUtil2.isContainedBy(element, CsPackage.Literals.BLOCK_ARCHITECTURE)
        && !(CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) element)));
  }

  public boolean isPC2CITransitionAvailable(EObject element) {

    if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) element)) {
      Component component = getComponent(element);
      if (component instanceof PhysicalComponent || //
          element instanceof PhysicalComponentPkg || //
          (element instanceof ComponentExchange)
              && ComponentExchangeExt.isNotLinkToAnActor((ComponentExchange) element)) {
        return true;
      }
    }
    return false;
  }
}
