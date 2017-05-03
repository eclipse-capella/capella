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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.Messages;
import org.polarsys.capella.core.platform.sirius.ui.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.preferences.ProtectedElementsPreferences;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;
import org.polarsys.kitalpha.emde.model.Element;

import com.google.common.collect.Iterables;

/**
 */
public class CapellaDeleteAction extends AbstractCommandActionHandler implements IPropertyChangeListener {

  /**
   * Constructor.
   * @param executionManager_p
   */
  public CapellaDeleteAction() {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Delete_menu_item"));
    // Add a listener to be notified when protected elements preferences change.
    CapellaActionsActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.DeleteAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection_p) {
    return new CapellaDeleteCommand(TransactionHelper.getExecutionManager(filterSelection(selection_p)), selection_p);
  }

  /**
   * Dispose this action.
   */
  public void dispose() {
    // Add a listener to be notified when protected elements preferences change.
    CapellaActionsActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
  }

  /**
   * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
   */
  @Override
  public void propertyChange(PropertyChangeEvent event_p) {
    String property = event_p.getProperty();
    if (property.startsWith(ProtectedElementsPreferences.PREFERENCE_DELETE_RESTRICTION)) {
      // Re-compute the new state due to preference changes.
      setEnabled(updateSelection(getStructuredSelection()));
    } else if (property.equals(IDeletePreferences.PREFERENCE_CONFIRM_DELETE)) {
      setEnabled(updateSelection(getStructuredSelection()));
    }
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
   */
  @Override
  public void run() {
    // Get it into a runnable.
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
        monitor_p.beginTask(Messages.CapellaDeleteCommand_Label, IProgressMonitor.UNKNOWN);
        getCommand().execute();
      }
    };
    try {
      // Don't fork the runnable it causes dead locks between this job and the main eclipse decorator one.
      new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, runnable);
    } catch (Exception exception_p) {
      // Harsh times.
      throw new RuntimeException(exception_p);
    }
  }

  /**
   * Depending on use cases we have to override this methods to call canDelete.
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean updateSelection(IStructuredSelection selection_p) {
    // Check selected elements are deletable based on preferences.
    List<EObject> selectedElements = getStructuredSelection().toList();
    boolean result = canDelete(selectedElements);
    if (result) {
      result = super.updateSelection(selection_p);
    }
    return result;
  }

  /**
   * Can delete selected elements ?
   * @param selectedElement_p must be not <code>null</code>.
   * @return <code>true</code> or <code>false</code> if one of them is a protected model element.
   * @see ActionsPreferenceInitializer#isMetaclassProtected(org.eclipse.emf.ecore.EClass).
   */
  public static boolean canDelete(Collection<?> selectedElement_p) {
    // Empty selection -> can not delete.
    if (selectedElement_p.isEmpty()) {
      return false;
    }
    // Iterate over selected ModelElements to look for protected elements.
    Iterable<Element> selectedModelElements = Iterables.filter(selectedElement_p, Element.class);
    for (Element selectedModelElement : selectedModelElements) {
      // If the model element or its meta-class are protected -> can not delete.
      if (CapellaActionsActivator.getDefault().isMetaclassProtected(selectedModelElement.eClass()) || isElementProtected(selectedModelElement)) {
        return false;
      }
    }
    // No protected element amongst selected elements -> can delete.
    return true;
  }

  /**
   * @param elt_p
   * @return
   */
  protected static boolean isElementProtected(Element elt_p) {
    if (elt_p instanceof AbstractFunction) {
      return (EcoreUtil2.isContainedBy(elt_p, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG) && !EcoreUtil2.isContainedBy(elt_p,
          OaPackage.Literals.OPERATIONAL_ACTIVITY))
             || (EcoreUtil2.isContainedBy(elt_p, CtxPackage.Literals.SYSTEM_FUNCTION_PKG) && !EcoreUtil2.isContainedBy(elt_p,
                 CtxPackage.Literals.SYSTEM_FUNCTION))
             || (EcoreUtil2.isContainedBy(elt_p, LaPackage.Literals.LOGICAL_FUNCTION_PKG) && !EcoreUtil2.isContainedBy(elt_p,
                 LaPackage.Literals.LOGICAL_FUNCTION))
             || (EcoreUtil2.isContainedBy(elt_p, PaPackage.Literals.PHYSICAL_FUNCTION_PKG) && !EcoreUtil2.isContainedBy(elt_p,
                 PaPackage.Literals.PHYSICAL_FUNCTION));
    } else if (elt_p instanceof LogicalComponent) {
      return !EcoreUtil2.isContainedBy(elt_p, LaPackage.Literals.LOGICAL_COMPONENT);
    } else if (elt_p instanceof PhysicalComponent) {
      return !EcoreUtil2.isContainedBy(elt_p, PaPackage.Literals.PHYSICAL_COMPONENT);
    } else if (elt_p instanceof ConfigurationItem) {
      return !EcoreUtil2.isContainedBy(elt_p, EpbsPackage.Literals.CONFIGURATION_ITEM);
    } else if (elt_p instanceof Part) {
      AbstractType type = ((Part) elt_p).getAbstractType();
      if (type != null) {
        return CapellaActionsActivator.getDefault().isMetaclassProtected(type.eClass()) || isElementProtected(type);
      }
    } else if (elt_p instanceof SequenceMessage) {
      SequenceMessage msg = (SequenceMessage) elt_p;
      if (msg.getReceivingEnd() == null || msg.getKind() == MessageKind.CREATE || msg.getKind() == MessageKind.DELETE) {
        return false;
      } 
      return true;
    } else if (elt_p instanceof ModelInformation) {
      return true;
    }
    return false;
  }
}
