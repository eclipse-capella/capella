/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;

/**
 * An API gathering together launchers for GUI capella actions. All these actions are headless (they do not block on GUI
 * windows and does not need user interaction).
 * 
 * @author Erwan Brottier
 */
public class GuiActions {

  /**
   * Open a session by using the capella action @see OpenSessionAction.
   * 
   * @param airdFile
   *          the aird file
   */
  public static void openSession(IFile airdFile) {
    OpenSessionAction olsa = new OpenSessionAction();
    olsa.selectionChanged(new StructuredSelection(airdFile));
    olsa.run();
    flushASyncGuiThread();
  }

  /**
   * Prevents that all async thread on UI Thread has been executed before returning. FIXME It is the best implementation
   * to date. May be insufficient.
   */
  public static void flushASyncGuiThread() {
    try {
      Display.getCurrent().update();
      while (Display.getCurrent().readAndDispatch()) {
        // do nothing
      }
    } catch (Exception e) {
      // do nothing
    }
  }

  public static void renameModelFile(IFile modelFile_p, final String newName_p) {
    Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    RenameResourceAction renameAction = new RenameResourceAction(activeShell) {
      @Override
      protected String queryNewResourceName(final IResource resource) {
        return newName_p;
      }
    };
    IStructuredSelection selection = new StructuredSelection(modelFile_p);
    renameAction.selectionChanged(selection);
    renameAction.run();
  }

  /**
   * Simulate a click on model detachment button. Do NOT perform a model detach, it's just to evaluate detach
   * preconditions.
   * 
   * @param airdFile
   * @throws RuntimeException
   *           if one precondition is false
   */
  public static void lauchDetachModelAction(IFile airdFile) throws RuntimeException {
    IActionDelegate modelDetachAction = retrieveActionFromRegistry("org.polarsys.kitalpha.model.detachment.ui",
        "Model Detachment");
    Action nullAction = new Action() {
    };
    modelDetachAction.selectionChanged(nullAction, new StructuredSelection(airdFile));
    modelDetachAction.run(nullAction);
  }

  private static IActionDelegate retrieveActionFromRegistry(String nameSpaceIdentifier, String label) {
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IConfigurationElement[] confElts = registry.getConfigurationElementsFor("org.eclipse.ui.popupMenus");

    for (IConfigurationElement elt : confElts) {
      if (!elt.getNamespaceIdentifier().equals(nameSpaceIdentifier)) {
        continue;
      }

      IConfigurationElement[] children = elt.getChildren("action");
      for (IConfigurationElement child : children) {

        if (!child.getAttribute("label").equals(label)) {
          continue;
        }

        try {
          return (IActionDelegate) child.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }
}
