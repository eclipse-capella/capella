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
package org.polarsys.capella.core.diagram.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 */
public class ContextualDiagramHelper {

  public static ContextualDiagramHelper instance;

  protected ContextualDiagramHelper() {
    // Nothing here
  }

  public static ContextualDiagramHelper getService() {
    if (instance == null) {
      instance = new ContextualDiagramHelper();
    }
    return instance;
  }

  /**
   * Retrieve whether the representation can be a contextualizedElements based diagram.
   * 
   * @param representation
   * @return
   */
  public boolean isContextualRepresentation(DRepresentationDescriptor representation) {
    if (representation instanceof DRepresentationDescriptor) {
      RepresentationDescription description = representation.getDescription();
      if (description != null) {
        if (description.getName() != null) {
          return description.getName().endsWith(DiagramDescriptionConstants.INTERACTION_BLANK_DIAGRAM_NAME)
              || description.getName().endsWith(DiagramDescriptionConstants.ARCHITECTURE_BLANK_DIAGRAM_NAME)
              || description.getName().endsWith(DiagramDescriptionConstants.ENTITY_BLANK_DIAGRAM_NAME)
              || description.getName().endsWith(DiagramDescriptionConstants.DATA_FLOW_BLANK_DIAGRAM_NAME)
              || description.getName().endsWith(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME);
        }
      }
    }
    return false;
  }

  public boolean hasContextualElements(DRepresentationDescriptor representation) {
    DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ContextualElements,
        representation, false);
    if (annotation != null) {
      return (annotation.getReferences() != null) && !annotation.getReferences().isEmpty();
    }
    return false;
  }

  /**
   * Retrieve contextualElements according to the string-based annotation of the given representation
   * 
   * @param representation
   * @param elements
   */
  public List<EObject> getContextualElements(DRepresentationDescriptor representation) {
    ArrayList<EObject> result = new ArrayList<EObject>();
    if (representation != null) {
      DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ContextualElements,
          representation, false);
      if (annotation != null) {
        return new ArrayList<EObject>(annotation.getReferences());
      }
    }
    return result;
  }

  /**
   * Set the contextualElement string-based annotation of the given representation for given elements
   * 
   * @param representation
   * @param elements
   */
  public void setContextualElements(DRepresentationDescriptor representation, Collection<EObject> elements) {
    if (representation != null) {
      if ((elements == null) || (elements.size() == 0)) {
        DAnnotationHelper.deleteAnnotation(IRepresentationAnnotationConstants.ContextualElements, representation);

      } else {
        DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ContextualElements,
            representation, true);
        annotation.getReferences().clear();
        annotation.getReferences().addAll(elements);
      }
    }
  }

  /**
   * Retrieve all available elements which can be used as a contextual element into the given representation
   */
  public Collection<EObject> getAvailableContextualElements(DRepresentationDescriptor representation) {
    if (representation != null) {

      // Check for invalid diagrams
      EObject target = representation.getTarget();
      if ((target == null) || (representation.getDescription() == null)) {
        return Collections.emptyList();
      }

      String name = representation.getDescription().getName();
      if (name.endsWith(DiagramDescriptionConstants.ARCHITECTURE_BLANK_DIAGRAM_NAME)
          || name.endsWith(DiagramDescriptionConstants.ENTITY_BLANK_DIAGRAM_NAME)) {
        return getABAvailableContextualElements(representation);

      } else if (name.endsWith(DiagramDescriptionConstants.DATA_FLOW_BLANK_DIAGRAM_NAME)
          || name.endsWith(DiagramDescriptionConstants.INTERACTION_BLANK_DIAGRAM_NAME)) {
        return getDFAvailableContextualElements(representation);

      } else if (name.endsWith(DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
        return getCDBAvailableContextualElements(representation);
      }
    }

    return Collections.emptyList();
  }

  /**
   * Retrieve all available elements which can be used as a contextual element into the given architecture blank diagram
   */
  protected Collection<EObject> getABAvailableContextualElements(DRepresentationDescriptor diagram) {
    Collection<EObject> result = new HashSet<EObject>();
    EObject target = diagram.getTarget();

    BlockArchitecture architecture = (BlockArchitecture) EcoreUtil2.getFirstContainer(target,
        CsPackage.Literals.BLOCK_ARCHITECTURE);
    if (architecture != null) {

      // Retrieve all functional chains and available state/modes
      FunctionPkg fpkg = BlockArchitectureExt.getFunctionPkg(architecture, false);
      if (fpkg != null) {
        for (AbstractFunction function : FunctionPkgExt.getAllAbstractFunctions(fpkg)) {
          result.addAll(function.getOwnedFunctionalChains());
          result.addAll(function.getAvailableInStates());
        }
      }

      AbstractCapabilityPkg cpkg = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
      if (cpkg != null) {
        // Retrieve all functional chains
        for (AbstractCapability capability : AbstractCapabilityPkgExt.getAllAbstractCapabilities(cpkg)) {
          result.addAll(capability.getOwnedFunctionalChains());
        }
        // Retrieve all ab scenarios
        for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(cpkg)) {
          if (isABContextualScenario(scenario, architecture)) {
            result.add(scenario);
          }
        }

        // Retrieve all components and actors (parts if multiparts)
        boolean multiPart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(architecture));
        boolean isOA = (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(architecture));

        for (Component component : BlockArchitectureExt.getAllComponents(architecture)) {
          if (multiPart && !isOA) {
            result.addAll(getCache(ComponentExt::getRepresentingParts, component));
          } else {
            result.add(component);
          }
        }
      }
    }

    return result;
  }

  /**
   * @param scenario
   * @return
   */
  public boolean isABContextualScenario(Scenario scenario, BlockArchitecture architecture) {
    boolean addElement = false;
    for (EObject element : getInsertScenariosRelatedElements(scenario, architecture)) {
      if (element instanceof AbstractFunction) {
        addElement = true;

      } else if (element instanceof FunctionalExchange) {
        addElement = true;

      } else if (element instanceof Part) {
        addElement = true;

      } else if (element instanceof Role) {
        addElement = true;

      } else if (element instanceof Entity) {
        addElement = true;

      } else if (element instanceof ComponentExchange) {
        addElement = true;
      }

      if (addElement) {
        break;
      }
    }
    if (addElement) {
      if (ScenarioExt.isFunctionalScenario(scenario)) {
        return true;
      } else if (ScenarioExt.isDataFlowFunctionalScenario(scenario)) {
        return true;
      } else if (ScenarioExt.isDataFlowBehaviouralScenario(scenario)) {
        return true;
      } else if (ScenarioExt.isInterfaceScenario(scenario)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieve all available elements which can be used as a contextual element into the given data flow blank diagram
   */
  protected Collection<EObject> getDFAvailableContextualElements(DRepresentationDescriptor diagram) {
    Collection<EObject> result = new HashSet<EObject>();
    EObject target = diagram.getTarget();

    BlockArchitecture architecture = (BlockArchitecture) EcoreUtil2.getFirstContainer(target,
        CsPackage.Literals.BLOCK_ARCHITECTURE);
    if (architecture != null) {

      // Retrieve all functions / functional chains and available state/modes
      FunctionPkg fpkg = BlockArchitectureExt.getFunctionPkg(architecture, false);
      if (fpkg != null) {
        for (AbstractFunction function : FunctionPkgExt.getAllAbstractFunctions(fpkg)) {
          result.add(function);
          result.addAll(function.getOwnedFunctionalChains());
          result.addAll(function.getAvailableInStates());
        }
      }

      AbstractCapabilityPkg cpkg = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
      if (cpkg != null) {
        // Retrieve all functional chains
        for (AbstractCapability capability : AbstractCapabilityPkgExt.getAllAbstractCapabilities(cpkg)) {
          result.addAll(capability.getOwnedFunctionalChains());
        }
        // Retrieve all functional scenarios
        for (Scenario scenario : AbstractCapabilityPkgExt.getAllScenarios(cpkg)) {
          if (isDFContextualScenario(scenario, architecture)) {
            result.add(scenario);
          }
        }
      }
    }
    return result;
  }

  /**
   * @param scenario
   * @param architecture
   * @return
   */
  public boolean isDFContextualScenario(Scenario scenario, BlockArchitecture architecture) {
    boolean addElement = false;
    for (EObject element : getInsertScenariosRelatedElements(scenario, architecture)) {
      if (element instanceof AbstractFunction) {
        addElement = true;
      } else if (element instanceof FunctionalExchange) {
        addElement = true;
      }

      if (addElement) {
        break;
      }
    }
    if (addElement) {
      if (ScenarioExt.isFunctionalScenario(scenario)) {
        return true;
      } else if (ScenarioExt.isDataFlowFunctionalScenario(scenario)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieve related elements for a scenario and a given architecture (functions and functional exchanges)
   * 
   * @param scenario
   * @param sourceArchitecture
   * @return
   */
  public Collection<? extends EObject> getInsertScenariosRelatedElements(Scenario scenario,
      BlockArchitecture sourceArchitecture) {
    Collection<EObject> result = new HashSet<EObject>();

    for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
      AbstractInstance instance = role.getRepresentedInstance();
      if ((instance != null)) {
        BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(instance);
        if (sourceArchitecture.equals(targetArchitecture)) {

          if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(sourceArchitecture)
              && (instance instanceof Part)) {

            result.add(instance.getAbstractType());
          } else {
            result.add(instance);
          }
        }
      }
    }

    for (SequenceMessage message : scenario.getOwnedMessages()) {
      AbstractEventOperation operation = message.getInvokedOperation();
      if ((operation != null)) {
        BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(operation);
        if (sourceArchitecture.equals(targetArchitecture)) {
          result.add(operation);
        }
      }
    }

    for (TimeLapse timeLapse : scenario.getOwnedTimeLapses()) {
      if (timeLapse instanceof StateFragment) {
        StateFragment fragment = (StateFragment) timeLapse;

        if ((fragment.getRelatedAbstractFunction() != null)) {
          BlockArchitecture targetArchitecture = BlockArchitectureExt
              .getRootBlockArchitecture(fragment.getRelatedAbstractFunction());
          if (sourceArchitecture.equals(targetArchitecture)) {
            result.add(fragment.getRelatedAbstractFunction());
          }
        }
      }
    }

    return result;
  }

  /**
   * Retrieve all available elements which can be used as a contextual element into the given class diagram blank
   */
  protected Collection<EObject> getCDBAvailableContextualElements(DRepresentationDescriptor diagram) {
    Collection<EObject> result = new HashSet<EObject>();
    EObject target = diagram.getTarget();

    BlockArchitecture architecture = (BlockArchitecture) EcoreUtil2.getFirstContainer(target,
        CsPackage.Literals.BLOCK_ARCHITECTURE);
    if (architecture != null) {
      TreeIterator<EObject> objects = architecture.eAllContents();
      while (objects.hasNext()) {
        boolean valid = false;
        EObject object = objects.next();

        if (object instanceof ExchangeItem) {
          valid = true;

        } else if (object instanceof org.polarsys.capella.core.data.information.Collection) {
          valid = true;

        } else if (object instanceof DataType) {
          valid = true;

        } else if (object instanceof org.polarsys.capella.core.data.information.Class) {
          valid = true;

        } else if (object instanceof Interface) {
          valid = true;

        } else if (object instanceof FunctionPkg) {
          objects.prune();
        }

        if (valid) {
          result.add(object);
        }
      }

    }

    return result;
  }

}
