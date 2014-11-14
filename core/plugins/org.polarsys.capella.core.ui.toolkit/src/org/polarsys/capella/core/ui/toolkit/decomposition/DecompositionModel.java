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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.resource.ImageRegistry;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;

/**
 * <p>
 * Class <code>DecompositionModel</code> provides the model for Decomposition/Recomposition of Components.
 * </p>
 * <p>
 * This has a source component and a list of decompositions. Each decomposition will have a list of target components.
 * </p>
 * <p>
 * Will have a list of listeners attached with it, which will be triggered whenever an event occurs in the model.
 * </p>
 * <p>
 * For example, to create a model
 * 
 * <pre>
 * LogicalArchitecture logArch = ca.getOwnedSystem().getOwnedLogicalArchitecture();
 * LogicalComponent lc = logArch.getOwnedLogicalComponents(0);
 * 
 * List ll = logicalComponentUsedInterfaces.getAvailableElements(lc);
 * DecompositionComponent sourceComponent = new DecompositionComponent();
 * sourceComponent.setName(lc.getName());
 * sourceComponent.setValue(lc);
 * 
 * List list = getDecompositionItemList(ll);
 * sourceComponent.setItems(list);
 * 
 * DecompositionModel model = new DecompositionModel(sourceComponent);
 * 
 * model.addDecompositionModelListener(new DecompositionModelListener() {
 *   public void decompositionChanged(DecompositionModelEvent event_p) {
 *     //do your code here
 *   }
 * });
 * 
 * LogicalComponent lcc = lc.getSubLogicalComponents(0);
 * 
 * Decomposition decomposition = new Decomposition();
 * decomposition.setName(&quot;First Decomposition&quot;);
 * 
 * DecompositionComponent targetComponent = new DecompositionComponent();
 * targetComponent.setName(lcc.getName());
 * targetComponent.setValue(lcc);
 * List newList = logicalComponentUsedInterfaces.getAvailableElements(lcc);
 * targetComponent.setItems(getDecompositionItemList(newList));
 * 
 * decomp.addTargetComponent(targetComponent);
 * 
 * model.addDecomposition(decomposition);
 * </pre>
 * 
 * <pre>
 * //Just wrap the NamedElement into DecompositionItem
 * List&lt;DecompositionItem&gt; getDecompositionItemList(List&lt;NamedElement&gt; currentList_p) {
 *   List&lt;DecompositionItem&gt; list = new ArrayList&lt;DecompositionItem&gt;(1);
 *   for (NamedElement namedElement : currentList_p) {
 *     DecompositionItem pair = new DecompositionItemImpl(namedElement.getName(), namedElement, DecompositionItem.UNASSIGNED);
 *     list.add(pair);
 *   }
 *   return list;
 * }
 * </pre>
 * 
 * </p>
 */
public class DecompositionModel {

  public enum INTERFACE_KIND {
    External, Internal

  }

  private List<DecompositionItem> _decompositionItemRemoved;

  private List<Decomposition> _decompositions;

//  private String _fieldNameLiteral;

  private ImageRegistry _imgRegistry;

  private List<DecompositionModelListener> _listenersList;

//  private CapellaElementStore _capellaElementStore;

  private List<DecompositionComponent> _reusableComponents;

  private DecompositionComponent _sourceComponent;

  /**
   * Constructor
   */
  protected DecompositionModel() {
    setSourceComponent(null);
    setDecompositions(new ArrayList<Decomposition>(1));
    _listenersList = new ArrayList<DecompositionModelListener>(1);
    setReusableComponents(new ArrayList<DecompositionComponent>(1));
    _decompositionItemRemoved = new ArrayList<DecompositionItem>();
  }

  /**
   * Constructor
   * @param sourceComponent_p the source component for the model
   */
  public DecompositionModel(DecompositionComponent sourceComponent_p) {
    this();
    setSourceComponent(sourceComponent_p);
  }

  /**
   * Adds a new Decomposition
   * @param decomposition_p the Decomposition
   * @return true if the decomposition is successfully added
   */
  public boolean addDecomposition(Decomposition decomposition_p) {
    decomposition_p.setDecompositionModel(this);
    decomposition_p.setValue(Decomposition.DUMMY_VALUE);
    _decompositions.add(decomposition_p);
    fireDecompositionAdded(decomposition_p);
    return true;
  }

  public boolean addDecomposition(Decomposition decomposition_p, boolean alreadyCreated_p) {
    decomposition_p.setDecompositionModel(this);
    _decompositions.add(decomposition_p);
    return true;
  }

  /**
   * Adds a new Decomposition
   * @param name_p the name of the decomposition
   * @return true if the decomposition is successfully added
   */
  public boolean addDecomposition(String name_p) {
    Decomposition decomposition = new Decomposition();
    decomposition.setName(name_p);
    return addDecomposition(decomposition);
  }

  public void addDecompositionItemRemoved(DecompositionItem decItem) {
    _decompositionItemRemoved.add(decItem);

  }

  /**
   * Adds a DecompositionModelListener to the listener list
   * @param listener_p the DecompositionModelListener
   */
  public void addDecompositionModelListener(DecompositionModelListener listener_p) {
    _listenersList.add(listener_p);
  }

  /**
   * Adds a new target component to the decomposition
   * @param decomposition_p the decomposition onto which target component to be added
   * @param targetComp_p the target component
   * @return true if the target component can be added
   */
  public boolean addNewTargetComponent(Decomposition decomposition_p, DecompositionComponent targetComp_p) {

    decomposition_p.addTargetComponent(targetComp_p);
    fireTargetComponentAdded((_decompositions.size() == 1) ? null : decomposition_p, targetComp_p);
    setPathForNewTargetComponent(targetComp_p);
    return true;
  }

  /**
   * @param comp_p
   */
  public void addReusedComponent(DecompositionComponent comp_p) {
    _reusableComponents.add(comp_p);
  }

  /**
   * Attaches an interface to the target component
   * @param component_p the component to which the interface is attached
   * @param item_p the item
   * @return true if the interface is successfully attached
   */
  public boolean attachInterface(DecompositionComponent component_p, DecompositionItem item_p) {
    component_p.addItem(item_p);
    item_p.setParentComponent(component_p);
    fireInterfaceAttached(component_p, item_p);
    refreshStatus(component_p.getParentDecomposition(), item_p.isInterfaceUsage());
    return true;
  }

  /**
   * Attach a service on new target Interface
   */
  public boolean attachService(DecompositionComponent component_p, DecompositionItemService itemSce_p) {
    return attachService(component_p, itemSce_p, null);
  }

  /**
   * Attach a service on new target Interface
   */
  public boolean attachService(DecompositionComponent component_p, DecompositionItemService itemSce_p, DecompositionItem item_p) {

    // Get and Remove External Interface should contains the ItemService delegated
    List<DecompositionItem> itfWithScesFoundList = getItemInterfaceListContainSce(component_p.getItems(), itemSce_p, INTERFACE_KIND.External);
    for (DecompositionItem currentItf : itfWithScesFoundList) {
      detachInterface(component_p, currentItf);
    }

    DecompositionItem decItem = new DecompositionItem(getUniqName(component_p.getItems()), null, DecompositionItem.UNASSIGNED);
    decItem.setInterfaceUsage(itemSce_p.isUsed());
    decItem.addServiceItems(itemSce_p);
    decItem.addOriginInterfaces(item_p.getValue());
    itemSce_p.setParentDecompositionItem(decItem);
    return attachInterface(component_p, decItem);
  }

  /**
   * Attach a service on existing target Interface
   * @param component
   * @param copy
   * @return
   */
  public boolean attachService(DecompositionItem itf_p, DecompositionItemService sce_p) {
    return attachService(itf_p, sce_p, itf_p);
  }

  /**
   * Attach a service on existing target Interface
   * @param component
   * @param copy
   * @return
   */
  public boolean attachService(DecompositionItem itf_p, DecompositionItemService sce_p, DecompositionItem item_p) {

    // Get and Remove External Interface should contains the ItemService delegated
    List<DecompositionItem> itfWithScesFoundList = getItemInterfaceListContainSce(itf_p.getParentComponent().getItems(), sce_p, INTERFACE_KIND.External);
    for (DecompositionItem currentItf : itfWithScesFoundList) {
      detachInterface(itf_p.getParentComponent(), currentItf);
    }

    itf_p.addServiceItems(sce_p);
    itf_p.addOriginInterfaces(item_p.getValue());
    sce_p.setParentDecompositionItem(itf_p);
    fireInterfaceAttached(itf_p.getParentComponent(), itf_p);
    refreshStatus(itf_p.getParentComponent().getParentDecomposition(), itf_p.isInterfaceUsage());
    return true;
  }

  private boolean checkServiceDelegated(DecompositionItemService itemSce_p, List<DecompositionItem> itemListItf_p, boolean isUsed_p) {
    for (DecompositionItem itemItf : itemListItf_p) {
      if (itemItf.isInterfaceUsage() == isUsed_p) {
        for (DecompositionItemService itemSce : itemItf.getServiceItems()) {
          if (itemSce_p.getValue() == itemSce.getValue()) {
            return true;
          }

          // Check equality between Operation by Refinement link
          List<CapellaElement> listMelo =
              RefinementLinkExt.getRefinementRelatedTargetElements((CapellaElement) itemSce.getValue(), InformationPackage.Literals.OPERATION);
          if (listMelo.size() != 0) {
            CapellaElement opOrigin = listMelo.get(0);
            if (opOrigin == itemSce_p.getValue()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * Check if one operation is delegate between Interface given in parameter 'itemItf_p' and list Interface given in parameter 'itemListItf_p'
   */
  private boolean checkServicesDelegated(DecompositionItem itemItf_p, List<DecompositionItem> itemListItf_p) {
    for (DecompositionItemService itemSce : itemItf_p.getServiceItems()) {
      if (checkServiceDelegated(itemSce, itemListItf_p, itemItf_p.isInterfaceUsage())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Detaches an interface from the target component
   * @param sourceComponent_p the component from which the interface is detached
   * @param item_p the item
   * @return true if the interface is successfully detached
   */
  public boolean detachInterface(DecompositionComponent component_p, DecompositionItem item_p) {
    component_p.removeItem(item_p);
    fireInterfaceDetached(component_p, item_p);
    refreshStatus(component_p.getParentDecomposition(), item_p.isInterfaceUsage());
    return true;
  }

  public boolean detachService(DecompositionItem itemItf_p, DecompositionItemService itemSce_p) {

    DecompositionComponent component = itemItf_p.getParentComponent();
    itemItf_p.removeItem(itemSce_p);

    fireInterfaceAttached(component, itemItf_p);
    refreshStatus(component.getParentDecomposition(), itemItf_p.isInterfaceUsage());
    return true;

  }

  /**
   * Finishes Decomposition
   * @return true if successfully decomposed
   */
  public boolean finishDecomposition() {
    DecompositionModelEvent event = new DecompositionModelEvent(this, null, null, null, null, DecompositionModelEvent.DECOMPOSITION_FINISHED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionRemoved Event for all decompositions
   * @return true if the operation is successful
   */
  protected boolean fireAllDecompositionRemoved() {
    DecompositionModelEvent event = new DecompositionModelEvent(getSourceComponent(), null, null, null, DecompositionModelEvent.DECOMPOSITION_ALL_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires TargetComponentRemoved Event for all the components
   * @param decomposition_p the decomposition from which all the target components have to be removed
   * @return true if the operation is successful
   */
  protected boolean fireAllTargetComponentRemoved(Decomposition decomposition_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition_p, null, DecompositionModelEvent.TARGET_COMPONENT_ALL_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionAdded Event
   * @param newDecomposition_p the new decomposition
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionAdded(Decomposition newDecomposition_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, newDecomposition_p, null, DecompositionModelEvent.DECOMPOSITION_ADDED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionRemoved Event
   * @param decomposition_p the decomposition to be removed
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionRemoved(Decomposition decomposition_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition_p, null, DecompositionModelEvent.DECOMPOSITION_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionRenamed Event
   * @param decomposition_p the decomposition to be renamed
   * @param newName_p the new name of the decomposition
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionRenamed(Decomposition decomposition_p, String newName_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition_p, newName_p, DecompositionModelEvent.DECOMPOSITION_RENAMED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires InterfaceAttached Event
   * @param targetComponent_p the target component to which the interface has to be attached
   * @param DecompositionItem_p wrapper for the interface
   * @return true if the operation is successful
   */
  protected boolean fireInterfaceAttached(DecompositionComponent targetComponent_p, DecompositionItem DecompositionItem_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComponent_p, null, DecompositionItem_p,
            DecompositionModelEvent.TARGET_COMPONENT_INTERFACE_ATTACHED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires InterfaceDetached Event
   * @param targetComponent_p the target component from which the interface has to be removed
   * @param DecompositionItem_p wrapper for the interface
   * @return true if the operation is successful
   */
  protected boolean fireInterfaceDetached(DecompositionComponent targetComponent_p, DecompositionItem DecompositionItem_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComponent_p, null, DecompositionItem_p,
            DecompositionModelEvent.TARGET_COMPONENT_INTERFACE_DETACHED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires the ModelChanged Event which will trigger all the listeners attached with <code>this</code> object
   * @param event the DecompositionModelEvent
   */
  protected void fireModelChanged(DecompositionModelEvent event) {
    for (DecompositionModelListener listener : getListeners()) {
      listener.decompositionChanged(event);
    }
  }

  /**
   * Fires TargetComponentAdded Event
   * @param decomposition_p the decomposition onto which the target component is added
   * @param comp_p the target component
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentAdded(Decomposition decomposition_p, DecompositionComponent comp_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), comp_p, decomposition_p, null, DecompositionModelEvent.TARGET_COMPONENT_ADDED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires TargetComponentRemoved Event
   * @param decomposition_p the decomposition from which the target component is removed
   * @param targetComp_p the target component
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentRemoved(Decomposition decomposition_p, DecompositionComponent targetComp_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComp_p, decomposition_p, null, DecompositionModelEvent.TARGET_COMPONENT_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires TargetComponentRenamedEvent
   * @param targetComp_p the target component to be renamed
   * @param newName_p the new name
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentRenamed(DecompositionComponent targetComp_p, String newName_p) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComp_p, null, newName_p, DecompositionModelEvent.TARGET_COMPONENT_RENAMED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Gets the assignment count of a {@link DecompositionItem} in a {@link Decomposition}
   * @param item_p the {@link DecompositionItem}
   * @param decomp_p the {@link Decomposition}
   * @return the count of assignment
   */
  public int getAssignmentCount(DecompositionItem item_p, Decomposition decomp_p) {
    int count = 0;
    for (DecompositionComponent comp : decomp_p.getTargetComponents()) {
      for (DecompositionItem item : comp.getItems()) {
        if (item.equals(item_p) && (item.isInterfaceUsage() == item_p.isInterfaceUsage())) {
          count++;
        }
      }
    }

    // Add assignment count for delegated Operation
    if (count == 0) {
      // Only Case when Interface assignment count is 'UnAssigned'
      for (DecompositionItemService itemSce : item_p.getServiceItems()) {
        boolean assigned = false;
        for (DecompositionComponent comp : decomp_p.getTargetComponents()) {
          if (checkServiceDelegated(itemSce, comp.getItems(), item_p.isInterfaceUsage())) {
            assigned = true;
          }
        }
        if (!assigned) {
          return count;
        }
      }
      if (item_p.getServiceItems().isEmpty()) {
        return count;
      }

      count = 1;
    }

    return count;
  }

  public List<DecompositionItem> getDecompositionItemRemoved() {
    return _decompositionItemRemoved;
  }

  /**
   * @return the decompositions
   */
  public List<Decomposition> getDecompositions() {
    return _decompositions;
  }


  /**
   * @return the imgRegistry
   */
  public ImageRegistry getImgRegistry() {
    return _imgRegistry;
  }

  private List<DecompositionItem> getItemInterfaceListContainSce(List<DecompositionItem> decompositionItems_p, DecompositionItemService itemSce_p,
      INTERFACE_KIND itfKind_p) {
    List<DecompositionItem> itemItfContainSc = new ArrayList<DecompositionItem>();
    for (DecompositionItem currentItf : decompositionItems_p) {
      if (itfKind_p == INTERFACE_KIND.Internal) {
        // Filtering on Internal Interface
        if (currentItf.isInternal() && isServiceDefinedInDecompositionItem(currentItf.getServiceItems(), itemSce_p)) {
          itemItfContainSc.add(currentItf);
        }
      } else if (itfKind_p == INTERFACE_KIND.External) {
        if (!currentItf.isInternal() && isServiceDefinedInDecompositionItem(currentItf.getServiceItems(), itemSce_p)) {
          itemItfContainSc.add(currentItf);
        }
      }
    }
    return itemItfContainSc;
  }

  /**
   * @return the listeners
   */
  public List<DecompositionModelListener> getListeners() {
    return _listenersList;
  }

  /**
   * @return the reusedComponents
   */
  public List<DecompositionComponent> getReusableComponents() {
    return _reusableComponents;
  }

  /**
   * @return the sourceData
   */
  public DecompositionComponent getSourceComponent() {
    return _sourceComponent;
  }

  // /////////////////////////////////////////////////////////////////////////////////
  // ///////////////////// FIRE METHODS
  // /////////////////////////////////////////////
  // ///////////////////////////////////////////////////////////////////////////////

  private String getUniqName(List<DecompositionItem> itemsList_p) {
    String uniqName, prefixName = "Interface"; //$NON-NLS-1$
    int i = -1;
    List<String> nameList = new ArrayList<String>();

    for (DecompositionItem itemItf : itemsList_p) {
      int pos = itemItf.getName().lastIndexOf(" [Refined]"); //$NON-NLS-1$
      if (pos != -1) {
        // Suppress suffix name information
        nameList.add(itemItf.getName().substring(0, pos));
      } else {
        nameList.add(itemItf.getName());
      }
    }

    uniqName = prefixName;
    while (nameList.contains(uniqName)) {
      i++;
      uniqName = prefixName.concat(" " + Integer.toString(i)); //$NON-NLS-1$
    }
    return uniqName;
  }

  /**
   * @param decomposition_p
   * @param targetComp_p
   * @return
   */
  private DecompositionComponent getWrappedReusedComponent(Object value_p) {
    DecompositionModelEvent event = new DecompositionModelEvent(getSourceComponent(), null, null, value_p, DecompositionModelEvent.TARGET_COMPONENT_REUSED);
    fireModelChanged(event);
    return event.getReusedComponent();

  }

  /**
   * Checks whether detach is allowed on an item
   * @param data_p the item selected
   * @return true if the item can be detached
   */
  public boolean isDetachAllowed(Object data_p) {
    if (data_p instanceof DecompositionItem) {
      DecompositionItem item = (DecompositionItem) data_p;
      if (item.getParentComponent().isReusedComponent()) {
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * Checks whether drag is allowed on an item
   * @param source_p the item being dragged
   * @return true if the source can be dragged
   */
  public boolean isDragAllowed(Object source_p) {
    boolean flag = false;
    if (source_p == null) {
      return false;
    }
    if (source_p instanceof DecompositionItem) {
      flag = true;
      if (((DecompositionItem) source_p).getParentComponent().isReusedComponent()) {
        flag = false;
      }
    } else if (source_p instanceof DecompositionItemService) {
      // Add test for Allow Service Drag only for delegated operation
      flag = isOperationDelegated((DecompositionItemService) source_p);
    }
    return flag;
  }

  /**
   * Checks whether drop is allowed on the component
   * @param target_p the target component
   * @return true if drop is allowed on the component
   */
  public boolean isDropAllowed(Object target_p) {
    if (target_p == null) {
      return false;
    }
    if (target_p instanceof DecompositionComponent) {
      return true;
    } else if (target_p instanceof DecompositionItem) { // Allow the drop sce into interface
      return true;
    }

    return false;
  }

  /**
   * Checks if the node already contains the item, and returns the possibility of dropping the item
   * @param node_p the node on which the item is to be dropped
   * @param item_p the item to be dropped
   * @return true if the node does not already contain the item
   */
  public boolean isDropPossible(Object node_p, Object item_p) {
    if (null == node_p) {
      return false;
    }

    if ((node_p instanceof DecompositionComponent) && (item_p instanceof DecompositionItem)) {
      // Case : Drop Interface under target Component
      DecompositionComponent comp = (DecompositionComponent) node_p;
      DecompositionItem itemItf = (DecompositionItem) item_p;
      // (Adding a check to the existing Rule)
      // drop interface which can be used and implemented by same component
      List<DecompositionItem> items = comp.getItems();
      for (DecompositionItem decompositionItem : items) {
        if (decompositionItem.equals(item_p)) {
          if (decompositionItem.isInterfaceUsage() && ((DecompositionItem) item_p).isInterfaceUsage()) {
            return false;
          }
          if (!decompositionItem.isInterfaceUsage() && !((DecompositionItem) item_p).isInterfaceUsage()) {
            return false;
          }
        }
      }
      return !checkServicesDelegated(itemItf, comp.getItems()) && !comp.isReusedComponent();

    } else if (item_p instanceof DecompositionItemService) {
      // Case : Drop Service
      if (node_p instanceof DecompositionItem) {
        // Towards target Interface
        DecompositionItem itemItf = (DecompositionItem) node_p;
        DecompositionItemService itemSce = (DecompositionItemService) item_p;

        // Target Internal interface check && Service not already defined in current target Internal Interface or another Internal Interface under owner
        // Component target
        return itemItf.isInternal() && (itemItf.isInterfaceUsage() == itemSce.isUsed())
               && (getItemInterfaceListContainSce(itemItf.getParentComponent().getItems(), itemSce, INTERFACE_KIND.Internal).size() == 0);

      } else if (node_p instanceof DecompositionComponent) {
        // Towards target Component
        DecompositionComponent itemComponent = (DecompositionComponent) node_p;
        DecompositionItemService itemSce = (DecompositionItemService) item_p;

        // Check Service not already defined in another Internal Interface under target Component
        return getItemInterfaceListContainSce(itemComponent.getItems(), itemSce, INTERFACE_KIND.Internal).size() == 0;
      }
    }
    return false;
  }

  /**
   * Return true if 'ItemService' given in parameter is delegated Return false if 'ItemService' given in parameter is operation added manually
   */
  private boolean isOperationDelegated(DecompositionItemService itemSce_p) {
    boolean flag = true;
    CapellaElement op = (CapellaElement) itemSce_p.getValue();

    // Check traceability link on operation existing
    if (RefinementLinkExt.getRefinementRelatedTargetElements(op, InformationPackage.Literals.OPERATION).size() == 0) {
      List<CapellaElement> listOp = new ArrayList<CapellaElement>();
      // Check operation coming from Interface(s) used/realized by source Component
      for (DecompositionItem decItemSrc : _sourceComponent.getItems()) {
        for (DecompositionItemService itemSceSrc : decItemSrc.getServiceItems()) {
          listOp.add((CapellaElement) itemSceSrc.getValue());
        }
      }
      if (!listOp.contains(op)) {
        flag = false;
      }
    }
    return flag;
  }

  /**
   * Checks whether removal of the target is allowed
   * @param target_p the component to be removed
   * @return true if the target can be removed
   */
  public boolean isRemoveAllowed(Object target_p) {
    if (target_p instanceof DecompositionComponent) {
      return true;
    } else if (target_p instanceof DecompositionItem) {
      // Add check for allow remove internal interface
      if (((DecompositionItem) target_p).isInternal()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether renaming is allowed on the target
   * @param target_p the target to be renamed
   * @return true if the target can be renamed
   */
  public boolean isRenameAllowed(Object target_p) {
    if (target_p instanceof DecompositionComponent) {
      return true;
    }
    return false;
  }

  /**
   * Check if ItemService list contain the ItemService element given in parameter
   */
  private boolean isServiceDefinedInDecompositionItem(List<DecompositionItemService> serviceItems_p, DecompositionItemService itemSce_p) {
    for (DecompositionItemService currentItemSce : serviceItems_p) {
      if (currentItemSce.getValue() == itemSce_p.getValue()) {
        return true;
      }

      // Check equality between Operation by Refinement link
      List<CapellaElement> listMelo =
          RefinementLinkExt.getRefinementRelatedTargetElements((CapellaElement) currentItemSce.getValue(), InformationPackage.Literals.OPERATION);
      if (listMelo.size() != 0) {
        CapellaElement opOrigin = listMelo.get(0);
        if (opOrigin == itemSce_p.getValue()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param string_p
   */
  public boolean isValidName(String name_p) {
    for (Decomposition decomp : _decompositions) {
      if (decomp.getName().equals(name_p)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Refreshes the status of the items for a selected decomposition
   * @param decomposition_p the selected decomposition
   */
  public void refreshStatus(Decomposition decomposition_p) {
    for (DecompositionItem item : _sourceComponent.getItems()) {
      int count = getAssignmentCount(item, decomposition_p);
      setStatus(item, count);
    }
  }

  /**
   * Refreshes the status of the items for a selected decomposition
   * @param decomposition_p the selected decomposition
   */
  public void refreshStatus(Decomposition decomposition_p, boolean isUsed_p) {
    for (DecompositionItem item : _sourceComponent.getItems()) {
      if (item.isInterfaceUsage() == isUsed_p) {
        int count = getAssignmentCount(item, decomposition_p);
        setStatus(item, count);
      }
    }
  }

  @SuppressWarnings("boxing")
  /**
   * Refreshes the status of the model for synthesis check
   */
  public void refreshStatusForSynthesisCheck() {
    for (DecompositionItem item : _sourceComponent.getItems()) {
      item.clearMessages();
      for (Decomposition decomp : getDecompositions()) {
        int count = getAssignmentCount(item, decomp);

        if (count == 0) {
          item.setStatus(DecompositionItem.UNASSIGNED);
        } else if (count == 1) {
          item.setStatus(DecompositionItem.ASSIGNED);
          item.removeStatus(decomp.getName());
        } else if (count > 1) {
          item.setStatus(DecompositionItem.AMBIGUOUS);
        }
        if (count != 1) {
          item.addDecompositionNameForStatus(item.getStatus(), decomp.getName());
        }
      }
      Set<Integer> keys = item.getStatusKeys();

      if (keys.contains(DecompositionItem.UNASSIGNED)) {
        for (String name : item.getDecompositionNames(DecompositionItem.UNASSIGNED)) {
          item.addStatusMessage(Messages.getString("LCDecomp.interface.child.unassignedmsg") + name); //$NON-NLS-1$
        }
        item.setStatus(DecompositionItem.UNASSIGNED);
      }
      if (keys.contains(DecompositionItem.AMBIGUOUS)) {
        for (String name : item.getDecompositionNames(DecompositionItem.AMBIGUOUS)) {
          item.addStatusMessage(Messages.getString("LCDecomp.interface.child.ambiguousmsg") + name); //$NON-NLS-1$
        }
        item.setStatus(DecompositionItem.AMBIGUOUS);
      }
    }
  }

  /**
   * Removes all decompositions from the source component
   * @return true if all the decompositions are successfully removed
   */
  public boolean removeAllDecomposition() {
    _decompositions.clear();
    fireAllDecompositionRemoved();
    return true;
  }

  /**
   * Removes all the decompositions from the decomposition
   * @param decomposition_p the Decomposition
   * @return true if all the target components are removed
   */
  public boolean removeAllTargetComponents(Decomposition decomposition_p) {
    decomposition_p.removeAllTargetComponents();
    _reusableComponents.clear();
    fireAllTargetComponentRemoved(decomposition_p);
    refreshStatus(decomposition_p);
    return true;
  }

  /**
   * Removes a Decomposition
   * @param decomposition_p the Decomposition
   * @return true if the decomposition is successfully removed
   */
  public boolean removeDecomposition(Decomposition decomposition_p) {
    _decompositions.remove(decomposition_p);
    fireDecompositionRemoved(decomposition_p);
    return true;
  }

  /**
   * Removes a DecompositionModelListener from the listener list
   * @param listener_p the DecompositionModelListener
   */
  public void removeDecompositionModelListener(DecompositionModelListener listener_p) {
    _listenersList.remove(listener_p);
  }

  /**
   * @param comp_p
   */
  public void removeReusedComponent(DecompositionComponent comp_p) {
    _reusableComponents.remove(comp_p);
  }

  /**
   * Removes a target component from the decomposition
   * @param decomposition_p the Decomposition
   * @param targetComp_p the target
   * @return true if the target component is removed
   */
  public boolean removeTargetComponent(Decomposition decomposition_p, DecompositionComponent targetComp_p) {
    decomposition_p.removeTargetComponent(targetComp_p);
    if (targetComp_p.isReusedComponent()) {
      removeReusedComponent(targetComp_p);
    }
    fireTargetComponentRemoved(decomposition_p, targetComp_p);
    refreshStatus(targetComp_p.getParentDecomposition());
    return true;
  }

  /**
   * Renames a Decomposition
   * @param decomposition_p the Decomposition
   * @param name_p the new name for the decomposition
   * @return true if the decomposition is successfully renamed
   */
  public boolean renameDecomposition(Decomposition decomposition_p, String name_p) {
    decomposition_p.setName(name_p);
    fireDecompositionRenamed(decomposition_p, name_p);
    for (DecompositionComponent comp : decomposition_p.getTargetComponents()) {
      setPathForNewTargetComponent(comp);
    }
    return true;
  }

  /**
   * Renames a target component
   * @param targetComp_p the target component to be renamed
   * @param newName_p the new name for the target component
   * @return true if the target component is successfully renamed
   */
  public boolean renameTargetComponent(DecompositionComponent targetComp_p, String newName_p) {
    targetComp_p.setName(newName_p);
    fireTargetComponentRenamed(targetComp_p, newName_p);
    return true;
  }

  /**
   * Adds a new reusable target component to the decomposition
   * @param decomposition_p the decomposition onto which target component to be added
   * @param targetComp_p the target component
   * @return true if the target component can be added
   */
  public boolean reuseTargetComponent(Decomposition decomposition_p, Object value_p) {
    for (DecompositionComponent comp : decomposition_p.getTargetComponents()) {
      if (value_p.equals(comp.getReusedTarget())) {
        return false;
      }
    }
    DecompositionComponent shortcutComp = getWrappedReusedComponent(value_p);
    decomposition_p.addTargetComponent(shortcutComp);
    addReusedComponent(shortcutComp);
    fireTargetComponentAdded((_decompositions.size() == 1) ? null : decomposition_p, shortcutComp);
    refreshStatus(decomposition_p);
    return true;
  }

  /**
   * Setter for decomposition list
   * @param decompositions_p the decompositions to set
   */
  protected void setDecompositions(List<Decomposition> decompositions_p) {
    _decompositions = decompositions_p;
  }

  /**
   * @param imgRegistry_p the imgRegistry to set
   */
  public void setImgRegistry(ImageRegistry imgRegistry_p) {
    _imgRegistry = imgRegistry_p;
  }

  public void setPathForNewTargetComponent(DecompositionComponent targetComponent_p) {
    if (null != targetComponent_p) {
      if (!targetComponent_p.isReusedComponent()) {
        StringBuilder builder = new StringBuilder(getSourceComponent().getPath());
        builder.append("::"); //$NON-NLS-1$
        if (getDecompositions().size() > 1) {
          builder.append(targetComponent_p.getParentDecomposition().getName());
          builder.append("::"); //$NON-NLS-1$
        }
        builder.append(targetComponent_p.getName());
        targetComponent_p.setPath(builder.toString());
      }
    }
  }

  /**
   * @param reusedComponents_p the reusedComponents to set
   */
  public void setReusableComponents(List<DecompositionComponent> reusedComponents_p) {
    _reusableComponents = reusedComponents_p;
  }

  /**
   * Setter for source component
   * @param sourceData_p the sourceData to set
   */
  protected void setSourceComponent(DecompositionComponent sourceData_p) {
    _sourceComponent = sourceData_p;
  }

  /**
   * Sets the status of an item based on assignment count
   * @param item_p the {@link DecompositionItem}
   * @param count_p the assignment count
   */
  public void setStatus(DecompositionItem item_p, int count_p) {
    if (count_p == 0) {
      item_p.setStatus(DecompositionItem.UNASSIGNED);
      item_p.setStatusMessage(Messages.getString("LCDecomp.interface.unassigned.tooltip")); //$NON-NLS-1$
    } else if (count_p == 1) {
      item_p.setStatus(DecompositionItem.ASSIGNED);
      item_p.setStatusMessage(Messages.getString("LCDecomp.interface.assigned.tooltip")); //$NON-NLS-1$
    } else if (count_p > 1) {
      item_p.setStatus(DecompositionItem.AMBIGUOUS);
      item_p.setStatusMessage(Messages.getString("LCDecomp.interface.ambiguous.tooltip")); //$NON-NLS-1$
    }
  }

}
