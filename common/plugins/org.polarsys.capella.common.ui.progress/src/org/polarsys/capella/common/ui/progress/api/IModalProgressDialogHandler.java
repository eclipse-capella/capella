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
package org.polarsys.capella.common.ui.progress.api;

/**
 * This interface should be implemented by platform specific classes allowing to display a modal progress bar reflecting a given operation progress.
 * 
 * 
 */
public interface IModalProgressDialogHandler {
  /**
   * Runs the given thread displaying a modal platform progress bar, using the given task name. This method does not allow to give any tip about the progression
   * percentage. So this method use a progress dialog displaying an unknown amount of work. The run Runnable is run asynchronously.
   * @param runnable the Runnable to run
   * @param taskName_p the task name
   */
  public void asynchronousRunWithProgress(Runnable runnable, final String taskName_p);

  /**
   * Runs the given thread displaying a modal platform progress bar, using the given task name. This method does not allow to give any tip about the progression
   * percentage. So this method use a progress dialog displaying an unknown amount of work. The run Runnable is run synchronously.
   * @param runnable the Runnable to run
   * @param taskName_p the task name
   */
  public void synchronousRunWithProgress(Runnable runnable, final String taskName_p);

  /**
   * Runs the given thread displaying a modal platform progress bar, using the given task name. This method handles the worked percentage using a
   * IProgressHandler. The run Runnable is run asynchronously. IMPORTANT: the given Runnable MUST correctly handle the iteration other the amount of work done
   * calling <code>worked(amountDone)</code> and <code>done()</code> on the given <code>IProgressHandler</code> when convenient.
   * @param runnable the Runnable to run
   * @param taskName_p the task name
   * @param progressHandler the progress handler
   */
  public void asynchronousRunWithProgress(final Runnable runnable_p, final String taskName_p, final int amountOfWork_p);

  /**
   * Runs the given thread displaying a modal platform progress bar, using the given task name. This method handles the worked percentage using a
   * IProgressHandler. The run Runnable is run synchronously. IMPORTANT: the given Runnable MUST correctly handle the iteration other the amount of work done
   * calling <code>worked(amountDone)</code> and <code>done()</code> on the given <code>IProgressHandler</code> when convenient.
   * @param runnable the Runnable to run
   * @param taskName_p the task name
   * @param progressHandler the progress handler
   */
  public void synchronousRunWithProgress(final Runnable runnable_p, final String taskName_p, final int amountOfWork_p);

  /**
   * Creates a platform Progress Handler, for example, Progress Monitors in Eclipse,
   * which will be used by the modal dialog displaying the progress.
   * The progress handler is ONLY used when the total amount of work is known.
   * On top of that, when using a progress handler, the reported process
   * MUST call <code>IProgressHandler::worked(int amount)</code> and 
   * <code>IProgressHandler::done()</code>.
   * @see IProgressHandler
   * @return a Progress Handler
   */
  public IProgressHandler initializeProgressHandler();
}
