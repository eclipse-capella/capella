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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

public class DragAndDropServices {

  public boolean canBeDropped(EObject semanticObjectToDrop_p, EObject targetContainerView_p) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(targetContainerView_p);

    if (currentDiagram.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)
        || currentDiagram.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)
        || currentDiagram.getDescription().getName().equals(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      if (semanticObjectToDrop_p instanceof Component) {
        return false;
      }
    }

    for (DDiagramElement anElement : currentDiagram.getDiagramElements()) {
      if (anElement.getTarget().equals(semanticObjectToDrop_p)) {
        return false;
      }
    }

    // if element to be dropped belongs from below layer return false
    if (targetContainerView_p instanceof DSemanticDecorator) {
      DSemanticDecorator element = (DSemanticDecorator) targetContainerView_p;
      EObject target = element.getTarget();
      if ((null != target) && (target instanceof CapellaElement) && (semanticObjectToDrop_p instanceof CapellaElement)) {
        CapellaElement capellaElement = (CapellaElement) target;
        if (!CapellaLayerCheckingExt.isElementFromCurrentOrUpperLayer((CapellaElement) semanticObjectToDrop_p, capellaElement)) {
          return false;
        }
      }
    }

    if (targetContainerView_p instanceof DDiagram) {
      return true;
    }
    return CapellaServices.getService().isChild(((DSemanticDecorator) targetContainerView_p).getTarget(), semanticObjectToDrop_p);
  }

  public boolean partitionableElementCanBeDropped(PartitionableElement semanticObjectToDrop_p, EObject targetContainerView_p) {
    DSemanticDiagram currentDiagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(targetContainerView_p);

    if ((currentDiagram.getTarget() != null) && (BlockArchitectureExt.getRootBlockArchitecture(currentDiagram.getTarget()) instanceof OperationalAnalysis)) {

      for (DDiagramElement anElement : currentDiagram.getDiagramElements()) {
        if (anElement.getTarget().equals(semanticObjectToDrop_p)) {
          return false;
        }
      }
      if (targetContainerView_p instanceof DDiagram) {
        return true;
      }
      if (currentDiagram.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        if (CapellaServices.getService().getAllAncestors((PartitionableElement) currentDiagram.getTarget()).contains(semanticObjectToDrop_p)) {
          return false;
        }
      }
      EObject target = ((DSemanticDecorator) targetContainerView_p).getTarget();
      if (target instanceof Part) {
        target = CsServices.getService().getComponentType((Part) target);
      }
      return CapellaServices.getService().getAllAncestors(semanticObjectToDrop_p).contains(target);

    }

    return false;
  }

  public boolean partitionableElementCanBeDropped(Part semanticObjectToDrop_p, EObject targetContainerView_p) {
    EObject target = ((DSemanticDecorator) targetContainerView_p).getTarget();
    PartitionableElement type = null;
    PartitionableElement typeTarget = null;
    DSemanticDiagram currentDiagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(targetContainerView_p);

    if (targetContainerView_p instanceof DDiagramElement) {
      for (DDiagramElement anElement : DiagramServices.getDiagramServices().getDiagramElements((DDiagramElement) targetContainerView_p)) {
        if ((anElement.getTarget() != null) && anElement.getTarget().equals(semanticObjectToDrop_p)) {
          return false;
        }
      }
    }

    if (target instanceof Part) {
      typeTarget = (PartitionableElement) CsServices.getService().getComponentType((Part) target);
    } else {
      typeTarget = (PartitionableElement) target;
    }

    if (typeTarget != null) {
      type = (PartitionableElement) CsServices.getService().getParentContainer(semanticObjectToDrop_p);

      List<PartitionableElement> el = new ArrayList<PartitionableElement>();
      el.add(type);
      if (!CsServices.getService().isMultipartMode((ModelElement) target)) {
        el.addAll(CapellaServices.getService().getAllAncestors(semanticObjectToDrop_p));
      }
      return el.contains(typeTarget);
    }
    return false;
  }

  /**
   * Returns whether the given element from model can be dropped as an interface in the given newViewContainer_p used in common.odesign
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndInterfaceFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    if (element_p instanceof Interface) {

      DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, element_p)) {
        if (newViewContainer_p instanceof DDiagram) {
          return true;
        }
        EObject target = newViewContainer_p.getTarget();
        if ((target != null) && EcoreUtil2.isContainedBy(element_p, target)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * Performs drop as interface of the given element_p from model into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDInterfaceFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    // Nothing here. for future enhancements
  }

  /**
   * Returns whether the given element from diagram can be dropped as an interface in the given newViewContainer_p used in common.odesign, interface diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndInterfaceFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p) {
    if (newViewContainer_p instanceof DDiagramElement) {
      if (newViewContainer_p.getTarget() != null) {
        return !(newViewContainer_p.getTarget() instanceof AbstractActor);
      }
      return false;
    }
    return true;
  }

  /**
   * Performs drop as interface of the given element_p from diagram into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDInterfaceFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    EObject targetContainer = CsServices.getService().getIBTarget(newViewContainer_p);
    if (element_p instanceof Interface) {
      Interface itf = (Interface) element_p;
      EObject container = CsServices.getService().getParentContainer(itf);
      if ((targetContainer != null) && !targetContainer.equals(container)) {

        if (targetContainer instanceof BlockArchitecture) {
          BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(targetContainer);
          BlockArchitectureExt.getInterfacePkg(architecture).getOwnedInterfaces().add(itf);

        } else if (targetContainer instanceof Component) {
          Component component = (Component) targetContainer;
          ComponentExt.getInterfacePkg(component).getOwnedInterfaces().add(itf);
        }
      }
    }

  }

  /**
   * Performs drop of classes and collections and update linked associations
   */

  @SuppressWarnings("unchecked")
  public void dndCDClassOrCollectionFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    if (!((element_p instanceof Class) || (element_p instanceof Collection))) {
      return;
    }
    EObject target = newViewContainer_p.getTarget();

    if (target instanceof DataPkg) {
      DataPkg dataPkg = (DataPkg) target;
      EStructuralFeature feature = InformationPackage.Literals.DATA_PKG__OWNED_CLASSES;
      Classifier classif = (Classifier) element_p;
      if (classif instanceof Collection) {
        feature = InformationPackage.Literals.DATA_PKG__OWNED_COLLECTIONS;
      }
      ((java.util.Collection<EObject>) dataPkg.eGet(feature)).add(classif);
      // move unidirectional associations to class container pkg
      // and move bidirectional & non-directional associations to source and target common ancestor
      List<Association> allAssociations = ClassExt.getAllAssociationsButIncoming(classif);
      for (Association assoc : allAssociations) {
        AssociationExt.moveToCorrectContainer(assoc);
      }
    }
  }

  public void dndCDDataPkgFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    if (!(element_p instanceof DataPkg)) {
      return;
    }
    EObject target = newViewContainer_p.getTarget();
    if (target instanceof DataPkg) {
      DataPkg dataPkg = (DataPkg) element_p;
      ((DataPkg) target).getOwnedDataPkgs().add(dataPkg);
      Set<Association> allAssociations = DataPkgExt.getAllInvolvedAssociations(dataPkg);
      for (Association assoc : allAssociations) {
        AssociationExt.moveToCorrectContainer(assoc);
      }
    }
  }

  /**
   * Returns whether the given element from model can be dropped as an exchange item allocation in the given newViewContainer_p used in common.odesign,
   * interface diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndAllocationExchangeItemFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    if ((element_p != null) && (newViewContainer_p.getTarget() != null)) {
      if (element_p instanceof ExchangeItem) {

        if (newViewContainer_p.getTarget() instanceof Interface) {
          return true;
        }

        DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);
        if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, element_p)) {
          if (newViewContainer_p instanceof DDiagram) {
            return true;
          }
          EObject target = newViewContainer_p.getTarget();
          if ((target != null) && EcoreUtil2.isContainedBy(element_p, target)) {
            return true;
          }
        }
        return false;

      } else if (element_p instanceof ExchangeItemAllocation) {
        return (newViewContainer_p.getTarget() != null) && (newViewContainer_p.getTarget() instanceof Interface);
      }
    }
    return false;
  }

  /**
   * Returns whether the given element from diagram can be dropped as an exchange item allocation in the given newViewContainer_p used in common.odesign,
   * interface diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndAllocationExchangeItemFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    // we disable exchange item from exchange item to interface, sirius destroy always the source view.
    // we disable exchange item allocation dnd between 2 interfaces. a warning is generated by sirius.
    // we disable dnd from EIA to diagram. a dnd always dragndrop the source of the drag. (we can't create a view for the EI starting from the EIA)

    if (element_p != null) {
      if (element_p instanceof ExchangeItem) {

        // Allow dnd of ExchangeItem to Component
        if (newViewContainer_p instanceof DDiagramElement) {
          if (newViewContainer_p.getTarget() instanceof Component) {
            if (newViewContainer_p.getTarget() != null) {
              return !(newViewContainer_p.getTarget() instanceof AbstractActor);
            }
          }
          if (newViewContainer_p.getTarget() instanceof Interface) {
            return true;
          }
          return false;
        }
        return true;

      } else if (element_p instanceof ExchangeItemAllocation) {
        return false;
      }
    }
    return false;
  }

  /**
   * Performs drop as exchange item allocation of the given element_p from model into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDAllocationExchangeItemFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    EObject target = newViewContainer_p.getTarget();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);

    if (target instanceof Interface) {
      // Drag something into an interface : retrieve an EIA and attach it
      Interface itf = (Interface) target;
      if (element_p instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element_p;
        ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem(itf, item);
        InformationServices.getService().createOperationView(newViewContainer_p, allocation, diagram);

      } else if (element_p instanceof ExchangeItemAllocation) {
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) element_p;
        if (allocation.eContainer() != itf) {
          itf.getOwnedExchangeItemAllocations().add(allocation);
        }
        InformationServices.getService().createOperationView(newViewContainer_p, allocation, diagram);
      }

    }
  }

  /**
   * Performs drop as exchange item allocation of the given element_p from diagram into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDAllocationExchangeItemFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    EObject target = newViewContainer_p.getTarget();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);

    if (target instanceof Interface) {
      // Drag something into an interface : retrieve an EIA and attach it
      Interface itf = (Interface) target;
      if (element_p instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element_p;
        ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem(itf, item);
        InformationServices.getService().createOperationView(newViewContainer_p, allocation, diagram);

      } else if (element_p instanceof ExchangeItemAllocation) {
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) element_p;
        if (allocation.eContainer() != itf) {
          itf.getOwnedExchangeItemAllocations().add(allocation);
        }
        InformationServices.getService().createOperationView(newViewContainer_p, allocation, diagram);
      }

    } else if (target instanceof Component) {

      // Drag something into a component, just move it
      EObject targetContainer = CsServices.getService().getIBTarget(newViewContainer_p);
      if (element_p instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element_p;
        EObject container = CsServices.getService().getParentContainer(item);
        if ((targetContainer != null) && !targetContainer.equals(container)) {

          if (targetContainer instanceof BlockArchitecture) {
            BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
            BlockArchitectureExt.getInterfacePkg(architecture).getOwnedExchangeItems().add(item);

          } else if (targetContainer instanceof Component) {
            Component component = (Component) targetContainer;
            ComponentExt.getInterfacePkg(component).getOwnedExchangeItems().add(item);
          }
        }
      }
    }

  }

  /**
   * Returns whether the given element from model can be dropped as a component port in the given newViewContainer_p used in common.odesign, interface diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndComponentPortFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    if ((element_p instanceof ComponentPort) && (newViewContainer_p instanceof DDiagramElement)) {
      EObject target = newViewContainer_p.getTarget();
      if ((target != null) && (target instanceof Component) && (element_p.eContainer() != null) && element_p.eContainer().equals(target)) {
        DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);
        return !DiagramServices.getDiagramServices().isOnDiagram(diagram, element_p);
      }
    }
    return false;
  }

  /**
   * Performs drop as component port of the given element_p from model into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public EObject dndIDComponentPortFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    IInterpreter interpreter = InterpreterUtil.getInterpreter(element_p);
    Object result = interpreter.getVariable(IInterpreterSiriusVariables.VIEW);
    // here result is null, view is not created yet. A workaround can be FutureTask ??
    forceHideLabelAfterDndComponentPortFromModel(element_p, newViewContainer_p);
    return element_p;

  }

  public boolean forceHideLabelAfterDndComponentPortFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);
    EObject portView = DiagramServices.getDiagramServices().getDiagramElement(diagram, element_p);

    if (portView instanceof DDiagramElement) {
      HideLabelFilter filter = DiagramFactory.eINSTANCE.createHideLabelFilter();
      ((DDiagramElement) portView).getGraphicalFilters().add(filter);
      return true;
    }
    return false;
  }

  /**
   * Performs drop as component port of the given element_p from diagram into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDComponentPortFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    ComponentPort port = (ComponentPort) element_p;
    if ((newViewContainer_p.getTarget() != null) && (newViewContainer_p.getTarget() instanceof Component)) {
      Component cs = (Component) newViewContainer_p.getTarget();
      if (!cs.equals(port.eContainer())) {
        FaServices.getFaServices().removeUselessPortRealizations(port, true, true, false, false);
        FaServices.getFaServices().moveComponentExchanges(port);
        cs.getOwnedFeatures().add(port);
      }
    }
  }

  /**
   * Returns whether the given element from diagram can be dropped as a component port in the given newViewContainer_p used in common.odesign, interface
   * diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndComponentPortFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    if (element_p instanceof ComponentPort) {
      if ((newViewContainer_p.getTarget() != null) && (newViewContainer_p.getTarget() instanceof Component)) {
        return CsServices.getService().linkReconnectSource(oldContainer_p, oldContainer_p, newViewContainer_p.getTarget());
      }
    }
    return false;
  }

  /**
   * Returns whether the given element from model can be dropped as a component in the given newViewContainer_p used in common.odesign, interface diagrams
   * @param item_p
   * @param itf_p
   */
  public boolean isValidIDDndComponentFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    if (element_p instanceof Component) {

      DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer_p);
      if (newViewContainer_p instanceof DDiagram) {
        return !DiagramServices.getDiagramServices().isOnDiagram(diagram, element_p);
      }
      if (newViewContainer_p instanceof DNodeContainer) {
        EObject target = newViewContainer_p.getTarget();
        if ((target != null) && CsServices.getService().getParentContainersByParts((Component) element_p).contains(target)) {
          return !DiagramServices.getDiagramServices().isOnDiagram((DNodeContainer) newViewContainer_p, element_p);
        }
      }
    }
    return false;
  }

  /**
   * Performs drop as component of the given element_p from model into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDComponentFromModel(EObject element_p, DSemanticDecorator newViewContainer_p) {
    return;
  }

  /**
   * Returns whether the given element from diagram can be dropped as a component in the given newViewContainer_p used in common.odesign, interface diagrams
   * @param item_p
   * @param itf_p
   */

  public boolean isValidIDDndComponentFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    EObject newContainer = CsServices.getService().getIBTarget(newViewContainer_p);
    if (element_p instanceof AbstractActor) {
      return false;
    }
    if (newContainer instanceof BlockArchitecture) {
      return element_p instanceof AbstractActor;
    }
    if ((newContainer instanceof Part) && (element_p instanceof Part)) {
      return ABServices.getService().isValidDndComponent((Part) element_p, (Part) newContainer);
    }
    if ((newContainer instanceof Component) && (element_p instanceof Component)) {
      return ABServices.getService().isValidDndComponent((Component) element_p, (Component) newContainer);
    }
    return false;
  }

  /**
   * Performs drop as component of the given element_p from diagram into the given newViewContainer_p used in common.odesign, interface diagrams
   * @param element_p
   * @param newViewContainer_p
   */
  public void dndIDComponentFromDiagram(EObject element_p, DSemanticDecorator newViewContainer_p, EObject oldContainer_p) {
    ABServices.getService().dndABComponent((NamedElement) element_p, (NamedElement) oldContainer_p, (NamedElement) newViewContainer_p.getTarget());
  }

}
