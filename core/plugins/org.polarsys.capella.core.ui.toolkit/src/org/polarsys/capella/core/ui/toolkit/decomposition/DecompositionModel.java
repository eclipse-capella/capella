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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.resource.ImageRegistry;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
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

  protected boolean hideTechnicalInterfaces = true;
  
  private List<DecompositionItem> decompositionItemRemoved;

  private List<Decomposition> decompositions;

//  private String _fieldNameLiteral;

  private ImageRegistry imgRegistry;

  private List<DecompositionModelListener> listenersList;

//  private CapellaElementStore _capellaElementStore;

  private List<DecompositionComponent> reusableComponents;

  private DecompositionComponent sourceComponent;

  /**
   * Constructor
   */
  protected DecompositionModel() {
    setSourceComponent(null);
    setDecompositions(new ArrayList<Decomposition>(1));
    listenersList = new ArrayList<DecompositionModelListener>(1);
    setReusableComponents(new ArrayList<DecompositionComponent>(1));
    decompositionItemRemoved = new ArrayList<DecompositionItem>();
  }

  /**
   * Constructor
   * @param sourceComponent the source component for the model
   */
  public DecompositionModel(DecompositionComponent sourceComponent) {
    this();
    setSourceComponent(sourceComponent);
  }

  public boolean doesHideTechnicalInterfaces() {
  	return hideTechnicalInterfaces;
  }
  
  public void setHideTechnicalInterfaces(boolean state) {
  	hideTechnicalInterfaces = state;
  }  
  
  /**
   * Adds a new Decomposition
   * @param decomposition the Decomposition
   * @return true if the decomposition is successfully added
   */
  public boolean addDecomposition(Decomposition decomposition) {
    decomposition.setDecompositionModel(this);
    decomposition.setValue(Decomposition.DUMMY_VALUE);
    decompositions.add(decomposition);
    fireDecompositionAdded(decomposition);
    return true;
  }

  public boolean addDecomposition(Decomposition decomposition, boolean alreadyCreated) {
    decomposition.setDecompositionModel(this);
    decompositions.add(decomposition);
    return true;
  }

  /**
   * Adds a new Decomposition
   * @param name the name of the decomposition
   * @return true if the decomposition is successfully added
   */
  public boolean addDecomposition(String name) {
    Decomposition decomposition = new Decomposition();
    decomposition.setName(name);
    return addDecomposition(decomposition);
  }

  public void addDecompositionItemRemoved(DecompositionItem decItem) {
    decompositionItemRemoved.add(decItem);

  }

  /**
   * Adds a DecompositionModelListener to the listener list
   * @param listener the DecompositionModelListener
   */
  public void addDecompositionModelListener(DecompositionModelListener listener) {
    listenersList.add(listener);
  }

  /**
   * Adds a new target component to the decomposition
   * @param decomposition the decomposition onto which target component to be added
   * @param targetComp the target component
   * @return true if the target component can be added
   */
  public boolean addNewTargetComponent(Decomposition decomposition, DecompositionComponent targetComp) {

    decomposition.addTargetComponent(targetComp);
    fireTargetComponentAdded((decompositions.size() == 1) ? null : decomposition, targetComp);
    setPathForNewTargetComponent(targetComp);
    return true;
  }

  /**
   * @param comp
   */
  public void addReusedComponent(DecompositionComponent comp) {
    reusableComponents.add(comp);
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
  public boolean attachService(DecompositionComponent component, DecompositionItemService itemSce, DecompositionItem item) {

    // Get and Remove External Interface should contains the ItemService delegated
    List<DecompositionItem> itfWithScesFoundList = getItemInterfaceListContainSce(component.getItems(), itemSce, INTERFACE_KIND.External);
    for (DecompositionItem currentItf : itfWithScesFoundList) {
      detachInterface(component, currentItf);
    }

    DecompositionItem decItem = new DecompositionItem(getUniqName(component.getItems()), null, DecompositionItem.UNASSIGNED);
    decItem.setInterfaceUsage(itemSce.isUsed());
    decItem.addServiceItems(itemSce);
    decItem.addOriginInterfaces(item.getValue());
    itemSce.setParentDecompositionItem(decItem);
    return attachInterface(component, decItem);
  }

  /**
   * Attach a service on existing target Interface
   * @param component
   * @param copy
   * @return
   */
  public boolean attachService(DecompositionItem itf, DecompositionItemService sce) {
    return attachService(itf, sce, itf);
  }

  /**
   * Attach a service on existing target Interface
   * @param component
   * @param copy
   * @return
   */
  public boolean attachService(DecompositionItem itf, DecompositionItemService sce, DecompositionItem item) {

    // Get and Remove External Interface should contains the ItemService delegated
    List<DecompositionItem> itfWithScesFoundList = getItemInterfaceListContainSce(itf.getParentComponent().getItems(), sce, INTERFACE_KIND.External);
    for (DecompositionItem currentItf : itfWithScesFoundList) {
      detachInterface(itf.getParentComponent(), currentItf);
    }

    itf.addServiceItems(sce);
    itf.addOriginInterfaces(item.getValue());
    sce.setParentDecompositionItem(itf);
    fireInterfaceAttached(itf.getParentComponent(), itf);
    refreshStatus(itf.getParentComponent().getParentDecomposition(), itf.isInterfaceUsage());
    return true;
  }

  private boolean checkServiceDelegated(DecompositionItemService itemSce, List<DecompositionItem> itemListItf, boolean isUsed) {
    for (DecompositionItem itemItf : itemListItf) {
      if (itemItf.isInterfaceUsage() == isUsed) {
        for (DecompositionItemService serviceItem : itemItf.getServiceItems()) {
          if (checkEqualDecompositionItemService(itemSce, serviceItem)) {
            return true;
          }

          // Check equality between Operation by Refinement link
          List<CapellaElement> listMelo =
              RefinementLinkExt.getRefinementRelatedTargetElements((CapellaElement) serviceItem.getValue(), InformationPackage.Literals.OPERATION);
          if (listMelo.size() != 0) {
            CapellaElement opOrigin = listMelo.get(0);
            if (opOrigin == itemSce.getValue()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private boolean checkEqualDecompositionItemService(DecompositionItemService itemSce1,
      DecompositionItemService itemSce2) {
    //If the DecompositionItemService represents an ExchangeItemAllocation, check the equality of there allocated items
    if (itemSce1.getValue() instanceof ExchangeItemAllocation && itemSce2.getValue() instanceof ExchangeItemAllocation)
      return ((ExchangeItemAllocation)itemSce1.getValue()).getAllocatedItem() == ((ExchangeItemAllocation)itemSce2.getValue()).getAllocatedItem();
    return itemSce1.getValue() == itemSce2.getValue();
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
   * @param item the item
   * @return true if the interface is successfully detached
   */
  public boolean detachInterface(DecompositionComponent component, DecompositionItem item) {
    component.removeItem(item);
    fireInterfaceDetached(component, item);
    refreshStatus(component.getParentDecomposition(), item.isInterfaceUsage());
    return true;
  }

  public boolean detachService(DecompositionItem itemItf, DecompositionItemService itemSce) {

    DecompositionComponent component = itemItf.getParentComponent();
    itemItf.removeItem(itemSce);

    fireInterfaceAttached(component, itemItf);
    refreshStatus(component.getParentDecomposition(), itemItf.isInterfaceUsage());
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
   * @param decomposition the decomposition from which all the target components have to be removed
   * @return true if the operation is successful
   */
  protected boolean fireAllTargetComponentRemoved(Decomposition decomposition) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition, null, DecompositionModelEvent.TARGET_COMPONENT_ALL_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionAdded Event
   * @param newDecomposition the new decomposition
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionAdded(Decomposition newDecomposition) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, newDecomposition, null, DecompositionModelEvent.DECOMPOSITION_ADDED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionRemoved Event
   * @param decomposition the decomposition to be removed
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionRemoved(Decomposition decomposition) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition, null, DecompositionModelEvent.DECOMPOSITION_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires DecompositionRenamed Event
   * @param decomposition the decomposition to be renamed
   * @param newName the new name of the decomposition
   * @return true if the operation is successful
   */
  protected boolean fireDecompositionRenamed(Decomposition decomposition, String newName) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), null, decomposition, newName, DecompositionModelEvent.DECOMPOSITION_RENAMED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires InterfaceAttached Event
   * @param targetComponent the target component to which the interface has to be attached
   * @param decompositionItem wrapper for the interface
   * @return true if the operation is successful
   */
  protected boolean fireInterfaceAttached(DecompositionComponent targetComponent, DecompositionItem decompositionItem) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComponent, null, decompositionItem,
            DecompositionModelEvent.TARGET_COMPONENT_INTERFACE_ATTACHED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires InterfaceDetached Event
   * @param targetComponent the target component from which the interface has to be removed
   * @param decompositionItem wrapper for the interface
   * @return true if the operation is successful
   */
  protected boolean fireInterfaceDetached(DecompositionComponent targetComponent, DecompositionItem decompositionItem) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComponent, null, decompositionItem,
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
   * @param decomposition the decomposition onto which the target component is added
   * @param comp the target component
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentAdded(Decomposition decomposition, DecompositionComponent comp) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), comp, decomposition, null, DecompositionModelEvent.TARGET_COMPONENT_ADDED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires TargetComponentRemoved Event
   * @param decomposition the decomposition from which the target component is removed
   * @param targetComp the target component
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentRemoved(Decomposition decomposition, DecompositionComponent targetComp) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComp, decomposition, null, DecompositionModelEvent.TARGET_COMPONENT_REMOVED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Fires TargetComponentRenamedEvent
   * @param targetComp the target component to be renamed
   * @param newName the new name
   * @return true if the operation is successful
   */
  protected boolean fireTargetComponentRenamed(DecompositionComponent targetComp, String newName) {
    DecompositionModelEvent event =
        new DecompositionModelEvent(getSourceComponent(), targetComp, null, newName, DecompositionModelEvent.TARGET_COMPONENT_RENAMED);
    fireModelChanged(event);
    return event.isOperationSuccess();
  }

  /**
   * Gets the assignment count of a {@link DecompositionItem} in a {@link Decomposition}
   * @param decompositionItem the {@link DecompositionItem}
   * @param decomp the {@link Decomposition}
   * @return the count of assignment
   */
  public int getAssignmentCount(DecompositionItem decompositionItem, Decomposition decomp) {
    int count = 0;
    for (DecompositionComponent comp : decomp.getTargetComponents()) {
      for (DecompositionItem item : comp.getItems()) {
        if (item.equals(decompositionItem)) {
          count++;
        }
      }
    }

    // Add assignment count for delegated Operation
    if (count == 0) {
      // Only Case when Interface assignment count is 'UnAssigned'
      for (DecompositionItemService itemSce : decompositionItem.getServiceItems()) {
        boolean assigned = false;
        for (DecompositionComponent comp : decomp.getTargetComponents()) {
          if (checkServiceDelegated(itemSce, comp.getItems(), decompositionItem.isInterfaceUsage())) {
            assigned = true;
          }
        }
        if (!assigned) {
          return count;
        }
      }
      if (decompositionItem.getServiceItems().isEmpty()) {
        return count;
      }

      count = 1;
    }

    return count;
  }

  public List<DecompositionItem> getDecompositionItemRemoved() {
    return decompositionItemRemoved;
  }

  /**
   * @return the decompositions
   */
  public List<Decomposition> getDecompositions() {
    return decompositions;
  }


  /**
   * @return the imgRegistry
   */
  public ImageRegistry getImgRegistry() {
    return imgRegistry;
  }

  private List<DecompositionItem> getItemInterfaceListContainSce(List<DecompositionItem> decompositionItems, DecompositionItemService itemSce,
      INTERFACE_KIND itfKind) {
    List<DecompositionItem> itemItfContainSc = new ArrayList<DecompositionItem>();
    for (DecompositionItem currentItf : decompositionItems) {
      if (itfKind == INTERFACE_KIND.Internal) {
        // Filtering on Internal Interface
        if (currentItf.isInternal() && isServiceDefinedInDecompositionItem(currentItf.getServiceItems(), itemSce)) {
          itemItfContainSc.add(currentItf);
        }
      } else if (itfKind == INTERFACE_KIND.External) {
        if (!currentItf.isInternal() && isServiceDefinedInDecompositionItem(currentItf.getServiceItems(), itemSce)) {
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
    return listenersList;
  }

  /**
   * @return the reusedComponents
   */
  public List<DecompositionComponent> getReusableComponents() {
    return reusableComponents;
  }

  /**
   * @return the sourceData
   */
  public DecompositionComponent getSourceComponent() {
    return sourceComponent;
  }

  // /////////////////////////////////////////////////////////////////////////////////
  // ///////////////////// FIRE METHODS
  // /////////////////////////////////////////////
  // ///////////////////////////////////////////////////////////////////////////////

  private String getUniqName(List<DecompositionItem> itemsList) {
    String uniqName, prefixName = "Interface"; //$NON-NLS-1$
    int i = -1;
    List<String> nameList = new ArrayList<String>();

    for (DecompositionItem itemItf : itemsList) {
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
  private DecompositionComponent getWrappedReusedComponent(Object value) {
    DecompositionModelEvent event = new DecompositionModelEvent(getSourceComponent(), null, null, value, DecompositionModelEvent.TARGET_COMPONENT_REUSED);
    fireModelChanged(event);
    return event.getReusedComponent();

  }

  /**
   * Checks whether detach is allowed on an item
   * @param data the item selected
   * @return true if the item can be detached
   */
  public boolean isDetachAllowed(Object data) {
    if (data instanceof DecompositionItem) {
      DecompositionItem item = (DecompositionItem) data;
      if (item.getParentComponent().isReusedComponent()) {
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * Checks whether drag is allowed on an item
   * @param source the item being dragged
   * @return true if the source can be dragged
   */
  public boolean isDragAllowed(Object source) {
    boolean flag = false;
    if (source == null) {
      return false;
    }
    if (source instanceof DecompositionItem) {
      flag = true;
      if (((DecompositionItem) source).getParentComponent().isReusedComponent()) {
        flag = false;
      }
    } else if (source instanceof DecompositionItemService) {
      // Add test for Allow Service Drag only for delegated operation
      flag = isOperationDelegated((DecompositionItemService) source);
    }
    return flag;
  }

  /**
   * Checks whether drop is allowed on the component
   * @param target the target component
   * @return true if drop is allowed on the component
   */
  public boolean isDropAllowed(Object target) {
    if (target == null) {
      return false;
    }
    if (target instanceof DecompositionComponent) {
      return true;
    } else if (target instanceof DecompositionItem) { // Allow the drop sce into interface
      return true;
    }

    return false;
  }

  /**
   * Checks if the node already contains the item, and returns the possibility of dropping the item
   * @param node the node on which the item is to be dropped
   * @param item the item to be dropped
   * @return true if the node does not already contain the item
   */
  public boolean isDropPossible(Object node, Object item) {
    if (null == node) {
      return false;
    }

    if ((node instanceof DecompositionComponent) && (item instanceof DecompositionItem)) {
      // Case : Drop Interface under target Component
      DecompositionComponent comp = (DecompositionComponent) node;
      DecompositionItem itemItf = (DecompositionItem) item;
      // (Adding a check to the existing Rule)
      // drop interface which can be used and implemented by same component
      List<DecompositionItem> items = comp.getItems();
      for (DecompositionItem decompositionItem : items) {
        if (decompositionItem.equals(item)) {
          if (decompositionItem.isInterfaceUsage() && ((DecompositionItem) item).isInterfaceUsage()) {
            return false;
          }
          if (!decompositionItem.isInterfaceUsage() && !((DecompositionItem) item).isInterfaceUsage()) {
            return false;
          }
        }
      }
      return !checkServicesDelegated(itemItf, comp.getItems()) && !comp.isReusedComponent();

    } else if (item instanceof DecompositionItemService) {
      // Case : Drop Service
      if (node instanceof DecompositionItem) {
        // Towards target Interface
        DecompositionItem itemItf = (DecompositionItem) node;
        DecompositionItemService itemSce = (DecompositionItemService) item;

        // Target Internal interface check && Service not already defined in current target Internal Interface or another Internal Interface under owner
        // Component target
        return itemItf.isInternal() && (itemItf.isInterfaceUsage() == itemSce.isUsed())
               && (getItemInterfaceListContainSce(itemItf.getParentComponent().getItems(), itemSce, INTERFACE_KIND.Internal).size() == 0);

      } else if (node instanceof DecompositionComponent) {
        // Towards target Component
        DecompositionComponent itemComponent = (DecompositionComponent) node;
        DecompositionItemService itemSce = (DecompositionItemService) item;

        // Check Service not already defined in another Internal Interface under target Component
        return getItemInterfaceListContainSce(itemComponent.getItems(), itemSce, INTERFACE_KIND.Internal).size() == 0;
      }
    }
    return false;
  }

  /**
   * Return true if 'ItemService' given in parameter is delegated Return false if 'ItemService' given in parameter is operation added manually
   */
  private boolean isOperationDelegated(DecompositionItemService itemSce) {
    boolean flag = true;
    CapellaElement op = (CapellaElement) itemSce.getValue();

    // Check traceability link on operation existing
    if (RefinementLinkExt.getRefinementRelatedTargetElements(op, InformationPackage.Literals.OPERATION).size() == 0) {
      List<CapellaElement> listOp = new ArrayList<CapellaElement>();
      // Check operation coming from Interface(s) used/realized by source Component
      for (DecompositionItem decItemSrc : sourceComponent.getItems()) {
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
   * @param target the component to be removed
   * @return true if the target can be removed
   */
  public boolean isRemoveAllowed(Object target) {
    if (target instanceof DecompositionComponent) {
      return true;
    } else if (target instanceof DecompositionItem) {
      // Add check for allow remove internal interface
      if (((DecompositionItem) target).isInternal()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether renaming is allowed on the target
   * @param target the target to be renamed
   * @return true if the target can be renamed
   */
  public boolean isRenameAllowed(Object target) {
    if (target instanceof DecompositionComponent) {
      return true;
    }
    return false;
  }

  /**
   * Check if ItemService list contain the ItemService element given in parameter
   */
  private boolean isServiceDefinedInDecompositionItem(List<DecompositionItemService> serviceItems, DecompositionItemService itemSce) {
    for (DecompositionItemService currentItemSce : serviceItems) {
      if (checkEqualDecompositionItemService(currentItemSce, itemSce)) {
        return true;
      }

      // Check equality between Operation by Refinement link
      List<CapellaElement> listMelo =
          RefinementLinkExt.getRefinementRelatedTargetElements((CapellaElement) currentItemSce.getValue(), InformationPackage.Literals.OPERATION);
      if (listMelo.size() != 0) {
        CapellaElement opOrigin = listMelo.get(0);
        if (opOrigin == itemSce.getValue()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param string_p
   */
  public boolean isValidName(String name) {
    for (Decomposition decomp : decompositions) {
      if (decomp.getName().equals(name)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Refreshes the status of the items for a selected decomposition
   * @param decomposition the selected decomposition
   */
  public void refreshStatus(Decomposition decomposition) {
    for (DecompositionItem item : sourceComponent.getItems()) {
      int count = getAssignmentCount(item, decomposition);
      setStatus(item, count);
    }
  }

  /**
   * Refreshes the status of the items for a selected decomposition
   * @param decomposition the selected decomposition
   */
  public void refreshStatus(Decomposition decomposition, boolean isUsed) {
    for (DecompositionItem item : sourceComponent.getItems()) {
      if (item.isInterfaceUsage() == isUsed) {
        int count = getAssignmentCount(item, decomposition);
        setStatus(item, count);
      }
    }
  }

  @SuppressWarnings("boxing")
  /**
   * Refreshes the status of the model for synthesis check
   */
  public void refreshStatusForSynthesisCheck() {
    for (DecompositionItem item : sourceComponent.getItems()) {
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
    decompositions.clear();
    fireAllDecompositionRemoved();
    return true;
  }

  /**
   * Removes all the decompositions from the decomposition
   * @param decomposition the Decomposition
   * @return true if all the target components are removed
   */
  public boolean removeAllTargetComponents(Decomposition decomposition) {
    decomposition.removeAllTargetComponents();
    reusableComponents.clear();
    fireAllTargetComponentRemoved(decomposition);
    refreshStatus(decomposition);
    return true;
  }

  /**
   * Removes a Decomposition
   * @param decomposition the Decomposition
   * @return true if the decomposition is successfully removed
   */
  public boolean removeDecomposition(Decomposition decomposition) {
    decompositions.remove(decomposition);
    fireDecompositionRemoved(decomposition);
    return true;
  }

  /**
   * Removes a DecompositionModelListener from the listener list
   * @param listener the DecompositionModelListener
   */
  public void removeDecompositionModelListener(DecompositionModelListener listener) {
    listenersList.remove(listener);
  }

  /**
   * @param comp
   */
  public void removeReusedComponent(DecompositionComponent comp) {
    reusableComponents.remove(comp);
  }

  /**
   * Removes a target component from the decomposition
   * @param decomposition the Decomposition
   * @param targetComp the target
   * @return true if the target component is removed
   */
  public boolean removeTargetComponent(Decomposition decomposition, DecompositionComponent targetComp) {
    decomposition.removeTargetComponent(targetComp);
    if (targetComp.isReusedComponent()) {
      removeReusedComponent(targetComp);
    }
    fireTargetComponentRemoved(decomposition, targetComp);
    refreshStatus(targetComp.getParentDecomposition());
    return true;
  }

  /**
   * Renames a Decomposition
   * @param decomposition the Decomposition
   * @param name the new name for the decomposition
   * @return true if the decomposition is successfully renamed
   */
  public boolean renameDecomposition(Decomposition decomposition, String name) {
    decomposition.setName(name);
    fireDecompositionRenamed(decomposition, name);
    for (DecompositionComponent comp : decomposition.getTargetComponents()) {
      setPathForNewTargetComponent(comp);
    }
    return true;
  }

  /**
   * Renames a target component
   * @param targetComp the target component to be renamed
   * @param newName the new name for the target component
   * @return true if the target component is successfully renamed
   */
  public boolean renameTargetComponent(DecompositionComponent targetComp, String newName) {
    targetComp.setName(newName);
    fireTargetComponentRenamed(targetComp, newName);
    return true;
  }

  /**
   * Adds a new reusable target component to the decomposition
   * @param decomposition the decomposition onto which target component to be added
   * @param targetComp_p the target component
   * @return true if the target component can be added
   */
  public boolean reuseTargetComponent(Decomposition decomposition, Object value) {
    for (DecompositionComponent comp : decomposition.getTargetComponents()) {
      if (value.equals(comp.getReusedTarget())) {
        return false;
      }
    }
    DecompositionComponent shortcutComp = getWrappedReusedComponent(value);
    decomposition.addTargetComponent(shortcutComp);
    addReusedComponent(shortcutComp);
    fireTargetComponentAdded((decompositions.size() == 1) ? null : decomposition, shortcutComp);
    refreshStatus(decomposition);
    return true;
  }

  /**
   * Setter for decomposition list
   * @param decompositions the decompositions to set
   */
  protected void setDecompositions(List<Decomposition> decompositions) {
    this.decompositions = decompositions;
  }

  /**
   * @param imgRegistry the imgRegistry to set
   */
  public void setImgRegistry(ImageRegistry imgRegistry) {
    this.imgRegistry = imgRegistry;
  }

  public void setPathForNewTargetComponent(DecompositionComponent targetComponent) {
    if (null != targetComponent) {
      if (!targetComponent.isReusedComponent()) {
        StringBuilder builder = new StringBuilder(getSourceComponent().getPath());
        builder.append("::"); //$NON-NLS-1$
        if (getDecompositions().size() > 1) {
          builder.append(targetComponent.getParentDecomposition().getName());
          builder.append("::"); //$NON-NLS-1$
        }
        builder.append(targetComponent.getName());
        targetComponent.setPath(builder.toString());
      }
    }
  }

  /**
   * @param reusedComponents the reusedComponents to set
   */
  public void setReusableComponents(List<DecompositionComponent> reusedComponents) {
    this.reusableComponents = reusedComponents;
  }

  /**
   * Setter for source component
   * @param sourceData the sourceData to set
   */
  protected void setSourceComponent(DecompositionComponent sourceData) {
    sourceComponent = sourceData;
  }

  /**
   * Sets the status of an item based on assignment count
   * @param item the {@link DecompositionItem}
   * @param count the assignment count
   */
  public void setStatus(DecompositionItem item, int count) {
    if (count == 0) {
      item.setStatus(DecompositionItem.UNASSIGNED);
      item.setStatusMessage(Messages.getString("LCDecomp.interface.unassigned.tooltip")); //$NON-NLS-1$
    } else if (count == 1) {
      item.setStatus(DecompositionItem.ASSIGNED);
      item.setStatusMessage(Messages.getString("LCDecomp.interface.assigned.tooltip")); //$NON-NLS-1$
    } else if (count > 1) {
      item.setStatus(DecompositionItem.AMBIGUOUS);
      item.setStatusMessage(Messages.getString("LCDecomp.interface.ambiguous.tooltip")); //$NON-NLS-1$
    }
  }

	public boolean isTechnicalInterface(DecompositionItem item) {
		Object value = item.getValue();
		return value instanceof Interface && !((Interface)value).isStructural();
	}

}
