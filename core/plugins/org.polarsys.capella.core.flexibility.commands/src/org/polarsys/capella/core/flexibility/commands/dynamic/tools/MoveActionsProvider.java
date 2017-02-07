/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalStructure;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;

/**
 */
public class MoveActionsProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell, ISelectionProvider selectionProvider) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();

    list.add(new ReplaceFunctionalExchangeAccessor(shell, selectionProvider));
    list.add(new ReplaceComponentExchangeAccessor(shell, selectionProvider));
    list.add(new ReplacePhysicalLinkAccessor(shell, selectionProvider));

    return list;
  }

  private abstract class MoveElementAction extends DefaultAction {

    public MoveElementAction(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    public abstract EClass getRelatedEClass();

    @Override
    public String getCategory() {
      return DefaultCategories.MOVE_ELEMENT_CATEGORY;
    }

    @Override
    public void execute() {
      Logger logger = getLogger();
      logger.info(new EmbeddedMessage(getDescription(), IReportManagerDefaultComponents.UI));
      logger.info(new EmbeddedMessage("To select an element. Right click/Show in Project Explorer", IReportManagerDefaultComponents.UI));

      boolean isPerformed = false;
      for (EObject source : getSelectedEObjects()) {
        if (source instanceof CapellaElement) {

          if (getRelatedEClass().isInstance(source)) {
            isPerformed = process(source, logger) | isPerformed;
          }

          for (EObject element : EObjectExt.getAll(source, getRelatedEClass())) {
            if (getRelatedEClass().isInstance(element)) {
              isPerformed = process(element, logger) | isPerformed;
            }
          }

        }
      }

      if (!isPerformed) {
        logger.info(new EmbeddedMessage(NLS.bind("No {0} have been moved.", getText()), IReportManagerDefaultComponents.UI));
      }

    }

    protected abstract void moveExchange(EObject exchange);

    /**
     * @param source
     */
    private boolean process(EObject exchange, Logger logger) {
      EObject container = exchange.eContainer();
      moveExchange(exchange);

      if ((container != exchange.eContainer())) {
        List<EObject> related = new ArrayList<EObject>();
        related.add(exchange);
        related.add(container);
        related.add(exchange.eContainer());
        logger.info(new EmbeddedMessage(NLS.bind(
            "''{0}'' has been moved from ''{1}'' to ''{2}''.",
            new Object[] { EObjectLabelProviderHelper.getText(exchange), EObjectLabelProviderHelper.getText(container),
                          EObjectLabelProviderHelper.getText(exchange.eContainer()) }), IReportManagerDefaultComponents.UI, related));
        return true;
      }
      return false;
    }

  }

  public class ReplaceFunctionalExchangeAccessor extends MoveElementAction {

    @Override
    public String getDescription() {
      return NLS.bind("This action moves any owned {0} in the common ancestor.", getText());
    }

    @Override
    protected String getIconFile() {
      return "FunctionalExchange.gif";
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(ModelElement.class).size() == 1) {
        EObject element = (EObject) getSelection(ModelElement.class).get(0);
        return (element instanceof SystemEngineering) || (element instanceof BlockArchitecture) || (element instanceof FunctionPkg)
               || (element instanceof AbstractFunction) || (element instanceof FunctionalExchange);
      }
      return false;
    }

    public ReplaceFunctionalExchangeAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      if (getSelection(ModelElement.class).size() > 0) {
        if (BlockArchitectureExt.getRootBlockArchitecture((EObject) getSelection(ModelElement.class).get(0)) instanceof OperationalAnalysis) {
          return "Interactions";
        }
      }
      return "Functional Exchanges";
    }

    /**
     * @see org.polarsys.capella.core.flexibility.commands.dynamic.tools.MoveActionsProvider.MoveElementAction#getRelatedEClass()
     */
    @Override
    public EClass getRelatedEClass() {
      return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
    }

    /**
     * Move an exchange in a visible place on both source/target
     * @param exchange the given functionalExchange
     * From FaServices (since 1.5.1)
     */
    @Override
    protected void moveExchange(EObject exchange) {
      FunctionalExchange exch = (FunctionalExchange) exchange;
      FunctionalExchangeExt.attachToDefaultContainer(exch);
    }

  };

  public class ReplaceComponentExchangeAccessor extends MoveElementAction {

    @Override
    public String getDescription() {
      return NLS.bind("This action moves any owned {0} in the common ancestor.", getText());
    }

    @Override
    protected String getIconFile() {
      if (getSelection(ModelElement.class).size() > 0) {
        if (BlockArchitectureExt.getRootBlockArchitecture((EObject) getSelection(ModelElement.class).get(0)) instanceof OperationalAnalysis) {
          return "CommunicationMean.gif";
        }
      }
      return "ComponentExchange.gif";
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(ModelElement.class).size() == 1) {
        EObject element = (EObject) getSelection(ModelElement.class).get(0);
        return (element instanceof SystemEngineering) || (element instanceof BlockArchitecture) || (element instanceof Component)
               || (element instanceof ComponentExchange) || (element instanceof AbstractFunctionalStructure);
      }
      return false;
    }

    public ReplaceComponentExchangeAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      if (getSelection(ModelElement.class).size() > 0) {
        if (BlockArchitectureExt.getRootBlockArchitecture((EObject) getSelection(ModelElement.class).get(0)) instanceof OperationalAnalysis) {
          return "Communication Means";
        }
      }
      return "Component Exchanges";
    }

    /**
     * @see org.polarsys.capella.core.flexibility.commands.dynamic.tools.MoveActionsProvider.MoveElementAction#getRelatedEClass()
     */
    @Override
    public EClass getRelatedEClass() {
      return FaPackage.Literals.COMPONENT_EXCHANGE;
    }

    /**
     * Move an exchange in a visible place on both source/target
     * @param exchange the given componentExchange
     */
    @Override
    protected void moveExchange(EObject exchange) {
      ComponentExchange exch = (ComponentExchange) exchange;
      ComponentExchangeExt.attachToDefaultContainer(exch);
    }
  };

  public class ReplacePhysicalLinkAccessor extends MoveElementAction {

    @Override
    public String getDescription() {
      return NLS.bind("This action moves any owned {0} in the common ancestor. It doesn't move {0} linked to an actor.", getText());
    }

    @Override
    protected String getIconFile() {
      return "PhysicalLink.gif";
    }

    @Override
    public boolean isSelectionCompatible() {
      if (getSelection(ModelElement.class).size() == 1) {
        EObject element = (EObject) getSelection(ModelElement.class).get(0);
        return ((element instanceof SystemEngineering) || (element instanceof BlockArchitecture) || (element instanceof Component) || (element instanceof PhysicalLink));
      }
      return false;
    }

    public ReplacePhysicalLinkAccessor(Shell shell, ISelectionProvider selectionProvider) {
      super(shell, selectionProvider);
    }

    @Override
    public String getText() {
      return "Physical Links";
    }

    /**
     * Move an exchange in a visible place on both source/target
     * @param exchange the given PhysicalLink
     */
    @Override
    protected void moveExchange(EObject exchange) {
      PhysicalLink exch = (PhysicalLink) exchange;

      EObject sourceComponent = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getSourceComponent(exch);
      if ((sourceComponent == null) || (sourceComponent instanceof AbstractActor)) {
        //Disable move of CE linked from actor
        return;
      }

      EObject targetComponent = org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt.getTargetComponent(exch);
      if ((targetComponent == null) || (targetComponent instanceof AbstractActor)) {
        //Disable move of CE linked to actor
        return;
      }
      org.polarsys.capella.core.model.helpers.PhysicalLinkExt.attachToDefaultContainer(exch);
    }

    /**
     * @see org.polarsys.capella.core.flexibility.commands.dynamic.tools.MoveActionsProvider.MoveElementAction#getRelatedEClass()
     */
    @Override
    public EClass getRelatedEClass() {
      return CsPackage.Literals.PHYSICAL_LINK;
    }

  };

}
