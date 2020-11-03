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
package org.polarsys.capella.core.sirius.analysis;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
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
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

public class DragAndDropServices {

  public boolean canBeDropped(EObject semanticObjectToDrop, EObject targetContainerView) {
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(targetContainerView);

    for (DDiagramElement anElement : currentDiagram.getDiagramElements()) {
      if (anElement.getTarget().equals(semanticObjectToDrop)) {
        return false;
      }
    }

    // if element to be dropped belongs from below layer return false
    if (targetContainerView instanceof DSemanticDecorator) {
      DSemanticDecorator element = (DSemanticDecorator) targetContainerView;
      EObject target = element.getTarget();
      if ((target instanceof CapellaElement) && (semanticObjectToDrop instanceof CapellaElement)) {
        CapellaElement capellaElement = (CapellaElement) target;
        if (!CapellaLayerCheckingExt.isElementFromCurrentOrUpperLayer((CapellaElement) semanticObjectToDrop,
            capellaElement)) {
          return false;
        }
      }
    }

    if (targetContainerView instanceof DDiagram) {
      return true;
    }
    return CapellaServices.getService().isChild(((DSemanticDecorator) targetContainerView).getTarget(),
        semanticObjectToDrop);
  }

  public boolean partitionableElementCanBeDropped(Component semanticObjectToDrop, EObject targetContainerView) {

    if (targetContainerView instanceof DDiagramElement) {
      for (DDiagramElement anElement : DiagramServices.getDiagramServices().getDiagramElements(targetContainerView)) {
        if ((anElement.getTarget() != null) && anElement.getTarget().equals(semanticObjectToDrop)) {
          return false;
        }
      }
    }

    DSemanticDecorator decorator = (DSemanticDecorator) targetContainerView;
    if (targetContainerView instanceof DDiagram) {
      return true;
    }
    for (Part part : semanticObjectToDrop.getRepresentingParts()) {
      java.util.Collection<Part> parents = ComponentExt.getPartAncestors(part);
      if (decorator.getTarget() instanceof Part && parents.contains(decorator.getTarget())) {
        return true;
      }
      if (decorator.getTarget() instanceof Component) {
        for (Part partE : ((Component) decorator.getTarget()).getRepresentingParts()) {
          if (parents.contains(partE))
            return true;
        }
      }
    }
    return false;
  }

  public boolean partitionableElementCanBeDropped(Part part, EObject targetContainerView) {

    DSemanticDecorator decorator = (DSemanticDecorator) targetContainerView;
    if (targetContainerView instanceof DDiagram) {
      return true;
    }

    java.util.Collection<Part> parents = ComponentExt.getPartAncestors(part);
    if (decorator.getTarget() instanceof Part && parents.contains(decorator.getTarget())) {
      return true;
    }

    if (decorator.getTarget() instanceof Component) {
      for (Part partE : ((Component) decorator.getTarget()).getRepresentingParts()) {
        if (parents.contains(partE)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the given element from model can be dropped as an interface in the given newViewContainer used in
   * common.odesign
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndInterfaceFromModel(EObject element, DSemanticDecorator newViewContainer) {
    if (element instanceof Interface) {

      DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);
      if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, element)) {
        if (newViewContainer instanceof DDiagram) {
          return true;
        }
        EObject target = newViewContainer.getTarget();
        if ((target != null) && EcoreUtil2.isContainedBy(element, target)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * Performs drop as interface of the given element from model into the given newViewContainer used in common.odesign,
   * interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDInterfaceFromModel(EObject element, DSemanticDecorator newViewContainer) {
    // Nothing here. for future enhancements
  }

  /**
   * Returns whether the given element from diagram can be dropped as an interface in the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndInterfaceFromDiagram(EObject element, DSemanticDecorator newViewContainer) {
    if (newViewContainer instanceof DDiagramElement) {
      if (newViewContainer.getTarget() != null) {
        return !(ComponentExt.isActor(newViewContainer.getTarget()));
      }
      return false;
    }
    return true;
  }

  /**
   * Performs drop as interface of the given element from diagram into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDInterfaceFromDiagram(EObject element, DSemanticDecorator newViewContainer, EObject oldContainer) {
    EObject targetContainer = CsServices.getService().getIBTarget(newViewContainer);
    if (element instanceof Interface) {
      Interface itf = (Interface) element;
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
  public void dndCDClassOrCollectionFromDiagram(EObject element, DSemanticDecorator newViewContainer,
      EObject oldContainer) {
    if (!((element instanceof Class) || (element instanceof Collection))) {
      return;
    }
    EObject target = newViewContainer.getTarget();

    if (target instanceof DataPkg) {
      DataPkg dataPkg = (DataPkg) target;
      EStructuralFeature feature = InformationPackage.Literals.DATA_PKG__OWNED_CLASSES;
      Classifier classif = (Classifier) element;
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

  public void dndCDDataPkgFromDiagram(EObject element, DSemanticDecorator newViewContainer, EObject oldContainer) {
    if (!(element instanceof DataPkg)) {
      return;
    }
    EObject target = newViewContainer.getTarget();
    if (target instanceof DataPkg) {
      DataPkg dataPkg = (DataPkg) element;
      ((DataPkg) target).getOwnedDataPkgs().add(dataPkg);
      Set<Association> allAssociations = DataPkgExt.getAllInvolvedAssociations(dataPkg);
      for (Association assoc : allAssociations) {
        AssociationExt.moveToCorrectContainer(assoc);
      }
    }
  }

  /**
   * Returns whether the given element from model can be dropped as an exchange item allocation in the given
   * newViewContainer used in common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndAllocationExchangeItemFromModel(EObject element, DSemanticDecorator newViewContainer) {
    if ((element != null) && (newViewContainer.getTarget() != null)) {
      if (element instanceof ExchangeItem) {

        if (newViewContainer.getTarget() instanceof Interface) {
          return true;
        }

        DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);
        if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, element)) {
          if (newViewContainer instanceof DDiagram) {
            return true;
          }
          EObject target = newViewContainer.getTarget();
          if ((target != null) && EcoreUtil2.isContainedBy(element, target)) {
            return true;
          }
        }
        return false;

      } else if (element instanceof ExchangeItemAllocation) {
        return (newViewContainer.getTarget() != null) && (newViewContainer.getTarget() instanceof Interface);
      }
    }
    return false;
  }

  /**
   * Returns whether the given element from diagram can be dropped as an exchange item allocation in the given
   * newViewContainer used in common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndAllocationExchangeItemFromDiagram(EObject element, DSemanticDecorator newViewContainer,
      EObject oldContainer) {
    // we disable exchange item from exchange item to interface, sirius destroy always the source view.
    // we disable exchange item allocation dnd between 2 interfaces. a warning is generated by sirius.
    // we disable dnd from EIA to diagram. a dnd always dragndrop the source of the drag. (we can't create a view for
    // the EI starting from the EIA)

    if (element != null) {
      if (element instanceof ExchangeItem) {

        // Allow dnd of ExchangeItem to Component
        if (newViewContainer instanceof DDiagramElement) {
          if (newViewContainer.getTarget() instanceof Component) {
            if (newViewContainer.getTarget() != null) {
              return !(ComponentExt.isActor(newViewContainer.getTarget()));
            }
          }
          if (newViewContainer.getTarget() instanceof Interface) {
            return true;
          }
          return false;
        }
        return true;

      } else if (element instanceof ExchangeItemAllocation) {
        return false;
      }
    }
    return false;
  }

  /**
   * Performs drop as exchange item allocation of the given element from model into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDAllocationExchangeItemFromModel(EObject element, DSemanticDecorator newViewContainer) {
    EObject target = newViewContainer.getTarget();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);

    if (target instanceof Interface) {
      // Drag something into an interface : retrieve an EIA and attach it
      Interface itf = (Interface) target;
      if (element instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element;
        ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem(itf, item);
        InformationServices.getService().createOperationView(newViewContainer, allocation, diagram);

      } else if (element instanceof ExchangeItemAllocation) {
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) element;
        if (allocation.eContainer() != itf) {
          itf.getOwnedExchangeItemAllocations().add(allocation);
        }
        InformationServices.getService().createOperationView(newViewContainer, allocation, diagram);
      }

    }
  }

  /**
   * Performs drop as exchange item allocation of the given element from diagram into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDAllocationExchangeItemFromDiagram(EObject element, DSemanticDecorator newViewContainer,
      EObject oldContainer) {
    EObject target = newViewContainer.getTarget();
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);

    if (target instanceof Interface) {
      // Drag something into an interface : retrieve an EIA and attach it
      Interface itf = (Interface) target;
      if (element instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element;
        ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem(itf, item);
        InformationServices.getService().createOperationView(newViewContainer, allocation, diagram);

      } else if (element instanceof ExchangeItemAllocation) {
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) element;
        if (allocation.eContainer() != itf) {
          itf.getOwnedExchangeItemAllocations().add(allocation);
        }
        InformationServices.getService().createOperationView(newViewContainer, allocation, diagram);
      }

    } else if (target instanceof Component) {

      // Drag something into a component, just move it
      EObject targetContainer = CsServices.getService().getIBTarget(newViewContainer);
      if (element instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) element;
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
   * Returns whether the given element from model can be dropped as a component port in the given newViewContainer used
   * in common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndComponentPortFromModel(EObject element, DSemanticDecorator newViewContainer) {
    if ((element instanceof ComponentPort) && (newViewContainer instanceof DDiagramElement)) {
      EObject target = newViewContainer.getTarget();
      if ((target != null) && (target instanceof Component) && (element.eContainer() != null)
          && element.eContainer().equals(target)) {
        DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);
        return !DiagramServices.getDiagramServices().isOnDiagram(diagram, element);
      }
    }
    return false;
  }

  /**
   * Performs drop as component port of the given element from model into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public EObject dndIDComponentPortFromModel(EObject element, DSemanticDecorator newViewContainer) {
    // here result is null, view is not created yet. A workaround can be FutureTask ??
    forceHideLabelAfterDndComponentPortFromModel(element, newViewContainer);
    return element;

  }

  public boolean forceHideLabelAfterDndComponentPortFromModel(EObject element, DSemanticDecorator newViewContainer) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);
    EObject portView = DiagramServices.getDiagramServices().getDiagramElement(diagram, element);

    if (portView instanceof DDiagramElement) {
      HideLabelFilter filter = DiagramFactory.eINSTANCE.createHideLabelFilter();
      ((DDiagramElement) portView).getGraphicalFilters().add(filter);
      return true;
    }
    return false;
  }

  /**
   * Performs drop as component port of the given element from diagram into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDComponentPortFromDiagram(EObject element, DSemanticDecorator newViewContainer,
      EObject oldContainer) {
    ComponentPort port = (ComponentPort) element;
    if ((newViewContainer.getTarget() != null) && (newViewContainer.getTarget() instanceof Component)) {
      Component cs = (Component) newViewContainer.getTarget();
      if (!cs.equals(port.eContainer())) {
        FaServices.getFaServices().removeUselessPortRealizations(port, true, true, false, false);
        FaServices.getFaServices().moveComponentExchanges(port);
        cs.getOwnedFeatures().add(port);
      }
    }
  }

  /**
   * Returns whether the given element from diagram can be dropped as a component port in the given newViewContainer
   * used in common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndComponentPortFromDiagram(EObject element, DSemanticDecorator newViewContainer,
      EObject oldContainer) {
    if (element instanceof ComponentPort) {
      if ((newViewContainer.getTarget() != null) && (newViewContainer.getTarget() instanceof Component)) {
        return CsServices.getService().linkReconnectSource(oldContainer, oldContainer, newViewContainer.getTarget());
      }
    }
    return false;
  }

  /**
   * Returns whether the given element from model can be dropped as a component in the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */
  public boolean isValidIDDndComponentFromModel(EObject element, DSemanticDecorator newViewContainer) {
    if (element instanceof Component) {

      DDiagram diagram = CapellaServices.getService().getDiagramContainer(newViewContainer);
      if (newViewContainer instanceof DDiagram) {
        return !DiagramServices.getDiagramServices().isOnDiagram(diagram, element);
      }
      if (newViewContainer instanceof DNodeContainer) {
        EObject target = newViewContainer.getTarget();
        if ((target != null)
            && CsServices.getService().getParentContainersByParts((Component) element).contains(target)) {
          return !DiagramServices.getDiagramServices().isOnDiagram((DNodeContainer) newViewContainer, element);
        }
      }
    }
    return false;
  }

  /**
   * Performs drop as component of the given element from model into the given newViewContainer used in common.odesign,
   * interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDComponentFromModel(EObject element, DSemanticDecorator newViewContainer) {
    return;
  }

  /**
   * Returns whether the given element from diagram can be dropped as a component in the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param item
   * @param itf
   */

  public boolean isValidIDDndComponentFromDiagram(Component component, DSemanticDecorator newViewContainer) {
    EObject newContainer = null;

    if (component.isActor()) {
      newContainer = CsServices.getService().getIBTarget(newViewContainer, true);
      return ABServices.getService().isValidCreationABActor(newContainer);
    }

    newContainer = CsServices.getService().getIBTarget(newViewContainer, false);

    if (newContainer instanceof Component) {
      return ABServices.getService().isValidDndComponent(component, (Component) newContainer);
    }
    if (newContainer instanceof ComponentPkg) {
      return ABServices.getService().isValidDndComponent(component, (ComponentPkg) newContainer);
    }

    return false;
  }

  /**
   * Performs drop as component of the given element from diagram into the given newViewContainer used in
   * common.odesign, interface diagrams
   * 
   * @param element
   * @param newViewContainer
   */
  public void dndIDComponentFromDiagram(EObject element, DSemanticDecorator newViewContainer, EObject oldContainer) {
    ABServices.getService().dndABComponent((NamedElement) element, (NamedElement) oldContainer,
        (NamedElement) newViewContainer.getTarget());
  }
}
