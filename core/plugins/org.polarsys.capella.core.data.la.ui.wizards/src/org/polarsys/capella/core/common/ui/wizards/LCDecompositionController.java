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
package org.polarsys.capella.core.common.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.ui.toolkit.decomposition.Decomposition;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItemService;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.ui.toolkit.decomposition.Messages;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;

/**
 * Controller for creating and showing the model and view for decomposition wizard
 */
public class LCDecompositionController {

  private DecompositionModel _model;
  private LCDecompositionWizard _wizard;
  public static final String PATH_SEPARATOR = "::"; //$NON-NLS-1$
  private boolean _userHasDeletedSubComponent;

  /**
   * Wraps all the interfaces wrapped into {@link DecompositionItem} in a {@link LogicalComponent}
   * 
   * @param logicalComponent
   *          the logical component
   * @return list of interfaces
   */
  public List<DecompositionItem> getWrappedInterfaces(LogicalComponent logicalComponent, boolean alreadyDecomposed) {
    List<DecompositionItem> list = new ArrayList<DecompositionItem>(1);
    List<Interface> usedInterfaces = ComponentExt.getAllUsedAndRequiredInterfaces(logicalComponent);
    list.addAll(getDecompositionItemList(usedInterfaces, true, alreadyDecomposed));
    List<Interface> implementedInterfaces = ComponentExt.getAllImplementedAndProvidedInterfaces(logicalComponent);
    list.addAll(getDecompositionItemList(implementedInterfaces, false, alreadyDecomposed));
    return list;
  }

  /**
   * Wraps all the interfaces and communication links wrapped into {@link DecompositionItem} in a
   * {@link LogicalComponent}
   * 
   * @param logicalComponent
   *          the logical component
   * @return list of interfaces
   */
  public List<DecompositionItem> getWrappedCommunicationLinks(LogicalComponent logicalComponent,
      boolean alreadyDecomposed) {
    List<DecompositionItem> list = new ArrayList<DecompositionItem>(1);
    List<CommunicationLink> communicationLinks = logicalComponent.getOwnedCommunicationLinks();
    list.addAll(getDecompositionItemList(communicationLinks, true, alreadyDecomposed));
    return list;
  }

  /**
   * Gets the list of {@link DecompositionItem}. Actual wrapping happens here.
   * 
   * @param currentList
   *          List of interfaces
   * @param isUsed
   *          flag to check whether the interface is used / implemented
   * @return list of DecompositionItem
   */
  @SuppressWarnings("rawtypes")
  public List<DecompositionItem> getDecompositionItemList(List currentList, boolean isUsed, boolean alreadyDecomposed) {
    List<DecompositionItem> list = new ArrayList<DecompositionItem>(1);

    for (Object element : currentList) {
      String path = getElementPath(element);
      String name = null;
      if (element instanceof Interface) {
        name = ((Interface) element).getName();
      } else if (element instanceof CommunicationLink) {
        CommunicationLink link = (CommunicationLink) element;
        CommunicationLinkKind kind = link.getKind();
        ExchangeItem exchangeItem = link.getExchangeItem();
        name = "[" + kind.getName() + "] " + exchangeItem.getName() + " (" + exchangeItem.getExchangeMechanism() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        CommunicationLinkProtocol protocol = link.getProtocol();
        if (protocol != CommunicationLinkProtocol.UNSET) {
          name += " [" + protocol + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
      DecompositionItem item = new DecompositionItem(name, element, DecompositionItem.UNASSIGNED, isUsed, path);
      if (element instanceof Interface) {
        // Gets the list of DecompositionItemServices from current Interface
        List<DecompositionItemService> listItemSces = getDecompositionItemServiceList(
            ((Interface) element).getOwnedExchangeItemAllocations(), item, isUsed);
        item.setServiceItems(listItemSces);
      }
      item.setAlreadyDecomposed(alreadyDecomposed);
      list.add(item);
    }
    return list;
  }

  /**
   * Return DecompositionItemService list from Operation list given in parameter
   * 
   * @param item
   */
  public List<DecompositionItemService> getDecompositionItemServiceList(List<ExchangeItemAllocation> listOp,
      DecompositionItem item, boolean used) {
    List<DecompositionItemService> list = new ArrayList<DecompositionItemService>(1);

    for (ExchangeItemAllocation op : listOp) {
      String path = getElementPath(op);
      DecompositionItemService itemSce = new DecompositionItemService(op.getAllocatedItem().getLabel(), op, used, path);
      itemSce.setParentDecompositionItem(item);
      list.add(itemSce);
    }
    return list;
  }

  /**
   * Gets the list of all decompositions in a logical component
   * 
   * @param logicalComponent
   *          the logical component
   * @return list of all decompositions
   */
  public List<Decomposition> getWrappedDecompositions(LogicalComponent logicalComponent) {
    List<Decomposition> list = new ArrayList<Decomposition>(1);

    if (logicalComponent.getOwnedLogicalArchitectures().isEmpty()) {
      list.add(getDecomposition(logicalComponent));
    } else {
      for (LogicalArchitecture logArch : logicalComponent.getOwnedLogicalArchitectures()) {
        list.add(getDecomposition(logArch));
      }
    }
    return list;
  }

  /**
   * Wraps a logical architecture into a decomposition
   * 
   * @param logicalArchitecture
   *          the logical architecture
   * @return the decomposition
   */
  public Decomposition getDecomposition(LogicalArchitecture logicalArchitecture) {
    Decomposition decomp = new Decomposition();
    decomp.setName(logicalArchitecture.getName());
    decomp.setValue(logicalArchitecture);

    DecompositionComponent comp = createDecompositionComponent((LogicalComponent)logicalArchitecture.getSystem());
    comp.setParentDecomposition(decomp);
    decomp.addTargetComponent(comp);

    return decomp;
  }

  public DecompositionComponent createDecompositionComponent(LogicalComponent component) {
    DecompositionComponent comp = new DecompositionComponent();
    comp.setName(component.getName());
    comp.setValue(component);
    List<DecompositionItem> items = new ArrayList<DecompositionItem>();
    items.addAll(getWrappedInterfaces(component, true));
    items.addAll(getWrappedCommunicationLinks(component, true));
    comp.setItems(items);
    comp.setComposite(ComponentExt.isComposite(component));

    if (comp.isReusedComponent()) {
      comp.setPath(getElementPath(comp.getReusedTarget()));
    } else {
      comp.setPath(getElementPath(comp.getValue()));
    }
    return comp;
  }

  /**
   * Gets the decomposition from a logical component if it has a single decomposition
   * 
   * @param logicalComponent
   *          the logical component
   * @return the decomposition
   */
  public Decomposition getDecomposition(LogicalComponent logicalComponent) {
    Decomposition decomp = new Decomposition();
    decomp.setName(""); //$NON-NLS-1$
    decomp.setValue(Decomposition.DUMMY_VALUE);
    for (Component tmpComp : ComponentExt.getSubUsedComponents(logicalComponent)) {
      LogicalComponent component = (LogicalComponent) tmpComp;
      DecompositionComponent comp = createDecompositionComponent(component);

      boolean flag = false;
      List<DecompositionComponent> targetComponents = decomp.getTargetComponents();
      for (DecompositionComponent decompositionComponent : targetComponents) {
        if (decompositionComponent.getValue().equals(comp.getValue())) {
          flag = true;
        }
      }
      if (!flag) {
        comp.setParentDecomposition(decomp);
        decomp.addTargetComponent(comp);
      }
    }
    return decomp;
  }

  /**
   * Creates and gets the DecompositionModel
   * 
   * @param sourceLC
   *          the source LC
   * @return the decomposition model
   */
  public DecompositionModel createDecompositionModel(LogicalComponent sourceLC) {

    DecompositionComponent sourceComp = new DecompositionComponent();
    sourceComp.setName(sourceLC.getName());
    sourceComp.setValue(sourceLC);
    sourceComp.setSourceComponent(true);
    List<DecompositionItem> tmp = getWrappedInterfaces(sourceLC, false);
    tmp.addAll(getWrappedCommunicationLinks(sourceLC, false));
    sourceComp.setPath(getElementPath(sourceComp.getValue()));
    sourceComp.setItems(tmp);

    _model = new DecompositionModel(sourceComp);
    LCDecompositionOperations operations = new LCDecompositionOperations();
    operations.setController(this);
    _model.addDecompositionModelListener(operations);

    List<Decomposition> decompositions = getWrappedDecompositions(sourceLC);
    for (Decomposition decomp : decompositions) {
      _model.addDecomposition(decomp, true);
      for (DecompositionComponent comp : decomp.getTargetComponents()) {
        comp.setAlreadyDecomposed(true);
        _model.removeReusedComponent(comp);
      }
    }

    return _model;
  }

  /**
   * Checks whether the decomposition is complete.
   * 
   * @return true if decompostions are done
   */
  public boolean isDecompositionComplete() {
    for (Decomposition decomp : _model.getDecompositions()) {
      if (decomp.getTargetComponents().size() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return true if the user has delete at least one component
   */
  public boolean userHasDeletedSubComponent() {
    return _userHasDeletedSubComponent;
  }

  public void setUserHasDeletedSubComponent(boolean value) {
    _userHasDeletedSubComponent = value;
  }

  public boolean canFlipToNextPage() {
    return (_model.getDecompositions().size() > 1);
  }

  /**
   * Triggers the view for Next, Finish actions
   */
  public void triggerView() {
    _wizard.trigger();
  }

  public String getElementPath(Object object) {
    NamedElement element = null;
    if (object instanceof NamedElement) {
      element = (NamedElement) object;
    } else if (object instanceof CommunicationLink) {
      element = ((CommunicationLink) object).getExchangeItem();
    }

    if (element != null) {
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

      return getPath(systemEngineering, element);
    }

    return ""; //$NON-NLS-1$
  }

  /**
   * Gets the path.
   * 
   * @param sysEng
   *          tghe system engineering.
   * @param target
   *          The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng, NamedElement target) {
    String name = (target instanceof ExchangeItemAllocation) ? ((ExchangeItemAllocation) target).getAllocatedItem()
        .getName() : target.getName();
    if (null == name) {
      name = Util.ZERO_LENGTH_STRING;
    }
    StringBuffer path = new StringBuffer(name);

    EObject container = target.eContainer();
    if (container instanceof NamedElement) {
      NamedElement parent = (NamedElement) target.eContainer();
      if (parent != sysEng) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng, parent) + PATH_SEPARATOR);
      } else {
        path.insert(path.indexOf(path.toString()), parent.getName() + PATH_SEPARATOR);
      }
    }
    return path.toString();
  }

  /**
   * @return the model
   */
  public DecompositionModel getModel() {
    return _model;
  }

  /**
   * @param model
   *          the model to set
   */
  public void setModel(DecompositionModel model) {
    _model = model;
  }

  /**
   * Creates and shows the decomposition wizard for a logical component
   * 
   * @param component
   *          the logical component to be decomposed
   */
  public void createAndShowDecompositionWizard(LogicalComponent component) {
    setWizard(new LCDecompositionWizard());
    _wizard.setController(this);
    createDecompositionModel(component);
    getModel().setImgRegistry(LCDWizardPlugin.getDefault().getImageRegistry());

    _wizard.setDecompositionModel(getModel());
    _wizard.initComponents();
    _wizard.trigger();
    _wizard.setWindowTitle(Messages.getString("LCDecomp.window.title")); //$NON-NLS-1$

    CapellaWizardDialog dlg = new CapellaWizardDialog(Display.getCurrent().getActiveShell(), _wizard);
    dlg.setPageSize(820, 420);
    dlg.updateSize();
    dlg.open();
  }

  /**
   * @return the wizard
   */
  public LCDecompositionWizard getWizard() {
    return _wizard;
  }

  /**
   * @param wizard
   *          the wizard to set
   */
  public void setWizard(LCDecompositionWizard wizard) {
    _wizard = wizard;
  }

}
