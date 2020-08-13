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
package org.polarsys.capella.core.projection.common;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 */
public class TransitionHelper {

  /** A shared instance. */
  private static TransitionHelper service;

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static TransitionHelper getService() {
    if (service == null) {
      service = new TransitionHelper();
    }
    return service;
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
   * 
   * @param object
   * @return
   */
  public boolean isES2ESTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into another architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should not be in the same architecture
          if ((architectureTarget != null) && architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be a dataflow scenario
          if ((s.getKind() == ScenarioKind.DATA_FLOW)
              || ((s.getKind() == ScenarioKind.INTERACTION) && !isFunctionalScenario(s))) {
            return false;
          }
        }
      }
      return (scenario.getKind() == ScenarioKind.DATA_FLOW)
          || ((scenario.getKind() == ScenarioKind.INTERACTION) && !isFunctionalScenario(scenario));
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));

  }

  /**
   * 
   * @param object
   * @return
   */
  public boolean isES2ISTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned interface scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if (s.getKind() == ScenarioKind.INTERFACE) {
            return false;
          }
        }
      }
      if (scenario.getKind() != ScenarioKind.DATA_FLOW) {
        return false;
      }
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof Scenario)));
  }

  /**
   * @param object
   * @return
   */
  public boolean isESF2ESBTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if (s.getKind() == ScenarioKind.DATA_FLOW) {
            return false;
          }
        }
      }

      // should be a dataflow scenario functional (messages carrying functional exchanges)
      if (scenario.getKind() == ScenarioKind.DATA_FLOW) {
        for (SequenceMessage message : scenario.getOwnedMessages()) {
          AbstractEventOperation operation = message.getInvokedOperation();
          if ((operation != null) && !(operation instanceof FunctionalExchange)) {
            return false;
          }
        }

      } else {
        return false;
      }
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof Scenario)));
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2ESForOASATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) object)
        && isFS2ESTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2ESForSALAPATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) object))
        && isFS2ESTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2ESTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(scenario);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if ((s.getKind() == ScenarioKind.DATA_FLOW) || (s.getKind() == ScenarioKind.INTERACTION)) {
            return false;
          }
        }
      }

      // should be a dataflow scenario functional (messages carrying functional exchanges)
      return isFunctionalScenario(scenario);
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param object
   * @return
   */
  public boolean isES2ESForOASATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) object)
        && isES2ESTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isES2ESForSALATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) object)
        && isES2ESTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isES2ESForLAPATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) object)
        && isES2ESTransitionAvailable(object);
  }


  /**
   * @param element
   * @return
   */
  public boolean isInterfaceGenerationAvailable(EObject object) {
    if (null != object) {
      Component comp = getComponent(object);
      if ((null != comp) && (comp instanceof PhysicalComponent)) {
        PhysicalComponent pc = (PhysicalComponent) comp;
        if (pc.getNature() == PhysicalComponentNature.NODE) {
          return false;
        }
      }
    }
    return ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
        && ((object instanceof Component) || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
            && (((Part) object).getAbstractType() instanceof Component))));
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
        if (abstractType instanceof Component) {
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
  public boolean isComponentExchangesGenerationAvailable(EObject object) {
    EObject component = getComponent(object);
    if (component instanceof SystemComponent || component instanceof LogicalComponent) {
      return true;
    }
    if (component instanceof PhysicalComponent) {
      return ((PhysicalComponent) component).getNature() != PhysicalComponentNature.NODE
          || (((PhysicalComponent) component).isActor());
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isCommunicationMeansGenerationAvailable(EObject object) {
    EObject component = getComponent(object);
    return (component instanceof Entity);
  }

  /**
   * @param element
   * @return
   */
  public boolean isPhysicalLinksGenerationAvailable(EObject object) {
    EObject component = object;
    if (object instanceof Part) {
      component = ((Part) object).getAbstractType();
    }

    if ((component instanceof Component)) {
      if (component instanceof PhysicalComponent) {
        return (((PhysicalComponent) component).getNature() != PhysicalComponentNature.BEHAVIOR);
      }
      if (canHavePhysicalPort(component)) {
        return true;
      }
      return (component instanceof PhysicalComponent) && !(((Component) component).isActor());
    }
    return false;
  }

  public boolean canHavePhysicalPort(EObject source) {
    return (source instanceof PhysicalComponent) || (source instanceof Component && ((Component) source).isActor())
        || (source instanceof SystemComponent) || (source instanceof LogicalComponent
            && source == ComponentExt.getRootBlockArchitecture((LogicalComponent) source).getSystem());
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2FSTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into another architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should not be in the same architecture
          if ((architectureTarget != null) && architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be a functional scenario
          if (isFunctionalScenario(s)) {
            return false;
          }
        }
      }
      return isFunctionalScenario(scenario);
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2FSForOASATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) object)
        && isFS2FSTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2FSForSALATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) object)
        && isFS2FSTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isFS2FSForLAPATransitionAvailable(EObject object) {
    return (object instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) object)
        && isFS2FSTransitionAvailable(object);
  }

  /**
   * @param object
   * @return
   */
  public boolean isPropertyValueTransitionAvailable(EObject object) {
    return ((object instanceof CapellaElement)
        && ((object instanceof PropertyValueGroup) || (object instanceof PropertyValuePkg)
            || (object instanceof AbstractPropertyValue) || (object instanceof EnumerationPropertyType))
        && EcoreUtil2.isContainedBy(object, CsPackage.Literals.BLOCK_ARCHITECTURE)
        && !(CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) object)));
  }

  public boolean isIS2ISSALATransitionAvailable(EObject element) {
    return element instanceof CapellaElement && CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element)
        && isIS2ISTransitionAvailable(element, "SALA");
  }

  public boolean isIS2ISLAPATransitionAvailable(EObject element) {
    return element instanceof CapellaElement && CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) element)
        && isIS2ISTransitionAvailable(element, "LAPA");
  }

  public boolean isIS2ISPAEPBSTransitionAvailable(EObject element) {
    return element instanceof CapellaElement && CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) element)
        && isIS2ISTransitionAvailable(element, "PAEPBS");
  }
  
  private boolean isIS2ISTransitionAvailable(EObject object, String transition) {
    if (object instanceof Scenario && ((Scenario) object).getKind() == ScenarioKind.INTERFACE) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // Disable command if there is a transitioned IS
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario existingScenario = (Scenario) src;
          // Should be an Interface Scenario
          if (existingScenario.getKind() == ScenarioKind.INTERFACE) {
            BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(existingScenario);
            if ("SALA".equals(transition)) {
              if (architectureSource instanceof SystemAnalysis && architectureTarget instanceof LogicalArchitecture) {
                return false;
              }
            } else if ("LAPA".equals(transition)) {
              if (architectureSource instanceof LogicalArchitecture
                  && architectureTarget instanceof PhysicalArchitecture) {
                return false;
              }
            } else if ("PAEPBS".equals(transition)) {
              if (architectureSource instanceof PhysicalArchitecture
                  && architectureTarget instanceof EPBSArchitecture) {
                return false;
              }
            }

            continue;
          }
        }
      }
      return true;
    }
    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }
}
