/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.sirius.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The viewpoint UI plugin activator.
 */
public class SiriusUIPlugin extends AbstractUIActivator {
  // The shared instance.
  private static SiriusUIPlugin __plugin;

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __plugin = null;
    super.stop(context_p);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static SiriusUIPlugin getDefault() {
    return __plugin;
  }

  /**
   * @param obj
   * @return the bundle containing the object's class or the class's name if it's not contained in a bundle
   */
  private String getBundleId(Object obj) {
    Bundle bundle = FrameworkUtil.getBundle(obj.getClass());
    if (bundle != null) {
      return bundle.getSymbolicName();
    }
    return obj.getClass().getCanonicalName();
  }

  /**
   * Run an operation performing a save of a Sirius Session.
   * 
   * We log exceptions directly in the StatusManager to avoid update of LogListeners 
   * registered on Eclipse Logger
   * 
   * This is mainly due to org.eclipse.sirius.ui.business.internal.dialect.LogThroughActiveDialectEditorLogListener
   * displaying unwanted popup while session save
   */
  public void runSaveOperation(Shell activeShell, IRunnableWithProgress operation) {

    try {
      ProgressMonitorDialog monitor = new ProgressMonitorDialog(activeShell);
      monitor.run(false, false, operation);

    } catch (InvocationTargetException ite) {
      // Log exception as it has been reported (not encapsulated through InvocationTargetException)
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, getBundleId(ite.getCause()), ite.getCause().getMessage(), ite.getCause()),
          StatusManager.BLOCK);

    } catch (InterruptedException ie) {
      // Not really useful for InterruptedException, but this is to be consistent with InvocationTargetException
      StatusManager.getManager().handle(new Status(IStatus.ERROR, getBundleId(ie), ie.getMessage(), ie));
    }
  }
}
