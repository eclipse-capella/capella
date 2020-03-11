/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.Messages;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;
import org.polarsys.kitalpha.emde.model.Element;

/**
 */
public class CapellaDeleteAction extends AbstractCommandActionHandler implements IPropertyChangeListener {

  /**
   * Constructor.
   * 
   * @param executionManager
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
  public Command createCommand(Collection<Object> selection) {
    return new CapellaDeleteCommand(TransactionHelper.getExecutionManager(filterSelection(selection)), selection);
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
  public void propertyChange(PropertyChangeEvent event) {
    String property = event.getProperty();
    if (property.equals(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS)
        || property.equals(IDeletePreferences.PREFERENCE_CONFIRM_DELETE)) {
      // Re-compute the new state due to preference changes.
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
      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.CapellaDeleteCommand_Label, IProgressMonitor.UNKNOWN);
        getCommand().execute();
      }
    };
    try {
      // Don't fork the runnable it causes dead locks between this job and the main eclipse decorator one.
      new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, runnable);
    } catch (Exception exception) {
      // Harsh times.
      throw new RuntimeException(exception);
    }
  }

  /**
   * Can delete selected elements ?
   * 
   * @param selectedElements
   *          must be not <code>null</code>.
   * @return <code>true</code> or <code>false</code> if one of them is a protected model element.
   * @see ActionsPreferenceInitializer#isMetaclassProtected(org.eclipse.emf.ecore.EClass).
   */
  public static boolean canDelete(Collection<?> selectedElements) {
    // Empty selection -> can not delete.
    if (selectedElements.isEmpty()) {
      return false;
    }

    return selectedElements.stream().filter(Element.class::isInstance).map(Element.class::cast)
        .noneMatch(CapellaDeleteAction::isElementProtected);
  }

  /**
   * @param element
   * @return
   */
  public static boolean isElementProtected(Element element) {

    boolean specialElementProtectionAllowed = CapellaModelPreferencesPlugin.getDefault()
        .isSpecialElementProtectionAllowed();

    if (specialElementProtectionAllowed) {

      if (element instanceof Component) {
        BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(element);
        return architecture != null && element.equals(architecture.getSystem());
      }

      if (element instanceof FunctionPkg || element instanceof AbstractCapabilityPkg || element instanceof ComponentPkg
          || element instanceof InterfacePkg || element instanceof DataPkg || element instanceof RolePkg
          || element instanceof MissionPkg) {
        return element.eContainer() instanceof BlockArchitecture;
      }

      if (element instanceof AbstractFunction) {
        return (EcoreUtil2.isContainedBy(element, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG)
            && !EcoreUtil2.isContainedBy(element, OaPackage.Literals.OPERATIONAL_ACTIVITY))
            || (EcoreUtil2.isContainedBy(element, CtxPackage.Literals.SYSTEM_FUNCTION_PKG)
                && !EcoreUtil2.isContainedBy(element, CtxPackage.Literals.SYSTEM_FUNCTION))
            || (EcoreUtil2.isContainedBy(element, LaPackage.Literals.LOGICAL_FUNCTION_PKG)
                && !EcoreUtil2.isContainedBy(element, LaPackage.Literals.LOGICAL_FUNCTION))
            || (EcoreUtil2.isContainedBy(element, PaPackage.Literals.PHYSICAL_FUNCTION_PKG)
                && !EcoreUtil2.isContainedBy(element, PaPackage.Literals.PHYSICAL_FUNCTION));

      } else if (element instanceof Part) {
        AbstractType type = ((Part) element).getAbstractType();
        if (type != null) {
          return isElementProtected(type);
        }
      } else if (element instanceof SequenceMessage) {
        SequenceMessage msg = (SequenceMessage) element;
        return !(msg.getReceivingEnd() == null || msg.getKind() == MessageKind.CREATE
            || msg.getKind() == MessageKind.DELETE);
      }

      return element instanceof Project || element instanceof SystemEngineering || element instanceof BlockArchitecture
          || element instanceof ModelInformation;
    }

    return false;
  }
}
