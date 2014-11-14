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
package org.polarsys.capella.core.common.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.widgets.Display;

import org.polarsys.capella.core.ui.toolkit.decomposition.Decomposition;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItemService;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.ui.toolkit.decomposition.Messages;
import org.polarsys.capella.core.ui.toolkit.dialogs.CapellaWizardDialog;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

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
   * @param logicalComponent_p the logical component
   * @return list of interfaces
   */
  public List<DecompositionItem> getWrappedInterfaces(LogicalComponent logicalComponent_p, boolean alreadyDecomposed_p) {
    List<DecompositionItem> list = new ArrayList<DecompositionItem>(1);
    List<Interface> usedInterfaces = ComponentExt.getUsedInterfaces(logicalComponent_p);
    list.addAll(getDecompositionItemList(usedInterfaces, true, alreadyDecomposed_p));
    List<Interface> implementedInterfaces = ComponentExt.getImplementedInterfaces(logicalComponent_p);
    list.addAll(getDecompositionItemList(implementedInterfaces, false, alreadyDecomposed_p));
    return list;
  }

  /**
   * Gets the list of {@link DecompositionItem}. Actual wrapping happens here.
   * @param currentList_p List of interfaces
   * @param isUsed flag to check whether the interface is used / implemented
   * @return list of DecompositionItem
   */
  @SuppressWarnings("rawtypes")
  public List<DecompositionItem> getDecompositionItemList(List currentList_p, boolean isUsed, boolean alreadyDecomposed_p) {
    List<DecompositionItem> list = new ArrayList<DecompositionItem>(1);

    for (Object element : currentList_p) {
      if (element instanceof Interface) {
        String name = ((Interface) element).getName();
        String path = getElementPath(element);
        DecompositionItem item = new DecompositionItem(name, element, DecompositionItem.UNASSIGNED, isUsed, path);
        item.setAlreadyDecomposed(alreadyDecomposed_p);
        // Gets the list of DecompositionItemServices from current Interface
        List<DecompositionItemService> listItemSces = getDecompositionItemServiceList(((Interface) element).getOwnedExchangeItemAllocations(), item, isUsed);
        item.setServiceItems(listItemSces);

        list.add(item);
      }
    }
    return list;
  }

  /**
   * Return DecompositionItemService list from Operation list given in parameter
   * @param item
   */
  public List<DecompositionItemService> getDecompositionItemServiceList(List<ExchangeItemAllocation> listOp_p, DecompositionItem item, boolean used_p) {
    List<DecompositionItemService> list = new ArrayList<DecompositionItemService>(1);

    for (ExchangeItemAllocation op : listOp_p) {
      String path = getElementPath(op);
      DecompositionItemService itemSce = new DecompositionItemService(op.getAllocatedItem().getLabel(), op, used_p, path);
      itemSce.setParentDecompositionItem(item);
      list.add(itemSce);
    }
    return list;
  }

  /**
   * Gets the list of all decompositions in a logical component
   * @param logicalComponent_p the logical component
   * @return list of all decompositions
   */
  public List<Decomposition> getWrappedDecompositions(LogicalComponent logicalComponent_p) {
    List<Decomposition> list = new ArrayList<Decomposition>(1);

    if (logicalComponent_p.getOwnedLogicalArchitectures().isEmpty()) {
      list.add(getDecomposition(logicalComponent_p));
    } else {
      for (LogicalArchitecture logArch : logicalComponent_p.getOwnedLogicalArchitectures()) {
        list.add(getDecomposition(logArch));
      }
    }
    return list;
  }

  /**
   * Wraps a logical architecture into a decomposition
   * @param logicalArchitecture_p the logical architecture
   * @return the decomposition
   */
  public Decomposition getDecomposition(LogicalArchitecture logicalArchitecture_p) {
    Decomposition decomp = new Decomposition();
    decomp.setName(logicalArchitecture_p.getName());
    decomp.setValue(logicalArchitecture_p);

    DecompositionComponent comp = createDecompositionComponent(logicalArchitecture_p.getOwnedLogicalComponent());
    comp.setParentDecomposition(decomp);
    decomp.addTargetComponent(comp);

    return decomp;
  }

  public DecompositionComponent createDecompositionComponent(LogicalComponent component_p) {
    DecompositionComponent comp = new DecompositionComponent();
    comp.setName(component_p.getName());
    comp.setValue(component_p);
    comp.setItems(getWrappedInterfaces(component_p, true));
    comp.setComposite(ComponentExt.isComposite(component_p));

    if (comp.isReusedComponent()) {
      comp.setPath(getElementPath(comp.getReusedTarget()));
    } else {
      comp.setPath(getElementPath(comp.getValue()));
    }
    return comp;
  }

  /**
   * Gets the decomposition from a logical component if it has a single decomposition
   * @param logicalComponent_p the logical component
   * @return the decomposition
   */
  public Decomposition getDecomposition(LogicalComponent logicalComponent_p) {
    Decomposition decomp = new Decomposition();
    decomp.setName(""); //$NON-NLS-1$
    decomp.setValue(Decomposition.DUMMY_VALUE);
    for (LogicalComponent component : logicalComponent_p.getSubLogicalComponents()) {
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
   * @param sourceLC_p the source LC
   * @return the decomposition model
   */
  public DecompositionModel createDecompositionModel(LogicalComponent sourceLC_p) {

    DecompositionComponent sourceComp = new DecompositionComponent();
    sourceComp.setName(sourceLC_p.getName());
    sourceComp.setValue(sourceLC_p);
    sourceComp.setSourceComponent(true);
    List<DecompositionItem> tmp = getWrappedInterfaces(sourceLC_p, false);
    sourceComp.setPath(getElementPath(sourceComp.getValue()));
    sourceComp.setItems(tmp);

    _model = new DecompositionModel(sourceComp);
    LCDecompositionOperations operations = new LCDecompositionOperations();
    operations.setController(this);
    _model.addDecompositionModelListener(operations);

    List<Decomposition> decompositions = getWrappedDecompositions(sourceLC_p);
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

  public void setUserHasDeletedSubComponent(boolean boolean_p) {
    _userHasDeletedSubComponent = boolean_p;
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

  public String getElementPath(Object object_p) {
    if (object_p instanceof NamedElement) {
      NamedElement element = (NamedElement) object_p;

      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

      return getPath(systemEngineering, element);
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * Gets the path.
   * @param sysEng_p tghe system engineering.
   * @param target_p The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng_p, NamedElement target_p) {
    String name = (target_p instanceof ExchangeItemAllocation) ? ((ExchangeItemAllocation) target_p).getAllocatedItem().getName() : target_p.getName();
    if (null == name) {
      name = Util.ZERO_LENGTH_STRING;
    }
    StringBuffer path = new StringBuffer(name);

    EObject container = target_p.eContainer();
    if (container instanceof NamedElement) {
      NamedElement parent = (NamedElement) target_p.eContainer();
      if (parent != sysEng_p) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng_p, parent) + PATH_SEPARATOR);
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
   * @param model_p the model to set
   */
  public void setModel(DecompositionModel model_p) {
    _model = model_p;
  }

  /**
   * Creates and shows the decomposition wizard for a logical component
   * @param component_p the logical component to be decomposed
   */
  public void createAndShowDecompositionWizard(LogicalComponent component_p) {
    setWizard(new LCDecompositionWizard());
    _wizard.setController(this);
    createDecompositionModel(component_p);
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
   * @param wizard_p the wizard to set
   */
  public void setWizard(LCDecompositionWizard wizard_p) {
    _wizard = wizard_p;
  }

}
