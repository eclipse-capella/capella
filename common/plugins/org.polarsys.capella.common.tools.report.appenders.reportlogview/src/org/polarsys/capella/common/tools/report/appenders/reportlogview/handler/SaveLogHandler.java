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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

public class SaveLogHandler extends AbstractViewHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {

    
    String res = getView(event).getHtml();
    
    FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.SAVE);
    fd.setFileName("information.html"); //$NON-NLS-1$
    String[] filters = { "*.html" }; //$NON-NLS-1$
    fd.setFilterExtensions(filters);

    final String fileName = fd.open();
    if (null != fileName) {
      try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
        fos.write(res.getBytes());
        fos.close();
        IEditorDescriptor editor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(fileName);
        String editorId = editor.getId();

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

        IPathEditorInput editorInput = new IPathEditorInput() {
          public boolean exists() {
            return true;
          }

          public ImageDescriptor getImageDescriptor() {
            return ImageDescriptor.getMissingImageDescriptor();
          }

          public String getName() {
            return "editorInput"; //$NON-NLS-1$
          }

          public IPersistableElement getPersistable() {
            /*
             * Returns null as this editor input cannot be persisted.
             */
            return null;
          }

          public String getToolTipText() {
            return "Opening external editor"; //$NON-NLS-1$
          }

          @SuppressWarnings("rawtypes")
          public Object getAdapter(Class adapter) {
            Object returnObject = null;
            if (adapter == IPathEditorInput.class) {
              returnObject = this;
            }
            return returnObject;
          }

          public IPath getPath() {
            return new Path(fileName);
          }

        };

        page.openEditor(editorInput, editorId);

      } catch (IOException e) {
        MessageBox msg = new MessageBox(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.ICON_ERROR | SWT.OK);
        msg.setText("Save error"); //$NON-NLS-1$
        msg.setMessage("Error while saving " + fileName); //$NON-NLS-1$
        msg.open();
      } catch (WorkbenchException e) {
        MessageBox msg = new MessageBox(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.ICON_ERROR | SWT.OK);
        msg.setText("Open error"); //$NON-NLS-1$
        msg.setMessage("Error while opening file " + fileName); //$NON-NLS-1$
        msg.open();
      }
    }
    return null;
  }

}
