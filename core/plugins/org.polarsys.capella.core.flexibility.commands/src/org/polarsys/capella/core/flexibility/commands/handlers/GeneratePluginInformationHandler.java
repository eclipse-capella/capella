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
package org.polarsys.capella.core.flexibility.commands.handlers;

import java.io.ByteArrayInputStream;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.flexibility.commands.dynamic.DynamicCommandsContentProvider;
import org.polarsys.capella.core.flexibility.commands.menus.policy.PolicyProvider;
import org.polarsys.capella.core.flexibility.commands.menus.provider.MenuInput;

/**
 */
public class GeneratePluginInformationHandler extends AbstractUiHandler {

  /**
   * {@inheritDoc}
   */
  public Object execute(final ExecutionEvent event_p) throws ExecutionException {
    final Logger logger = Logger.getLogger(IReportManagerDefaultComponents.MODEL);

    ISelectionProvider selectionProvider = new ISelectionProvider() {

      public void setSelection(ISelection selection_p) {
        //Do nothing.
      }

      public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
        //Do nothing.
      }

      public ISelection getSelection() {
        return new StructuredSelection();
      }

      public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
        //Do nothing.
      }
    };

    ITreeContentProvider contentProvider = new DynamicCommandsContentProvider();
    MenuInput menuInput = new MenuInput(PlatformUI.getWorkbench(), selectionProvider);
    contentProvider.inputChanged(null, null, menuInput);

    StringBuffer sb = new StringBuffer();

    sb.append("<html>\n<head>\n" + "  <style TYPE=\"text/css\">" + "   span.bold { padding-right:10px; font-weight:bold; } " + " </style></head>\n");
    sb.append("<body><h1>All defined commands</h1>");

    output(sb, "", contentProvider, menuInput);
    contentProvider.getElements(menuInput);

    sb.append("</body>\n");
    sb.append("</html>\n");

    if (getSelection().getFirstElement() instanceof IFile) {
      IFile file = (IFile) getSelection().getFirstElement();
      IFile fileOutput = file.getProject().getFile("index.html");
      if (fileOutput.exists()) {

        try {
          fileOutput.delete(true, new NullProgressMonitor());
        } catch (CoreException exception_p) {
          exception_p.printStackTrace();

        }
      }

      try {
        fileOutput.create(new ByteArrayInputStream(sb.toString().getBytes()), isEnabled(), new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }

    }

    return null;
  }

  /**
   * @param sb_p
   * @param string_p
   * @param contentProvider_p
   * @param menuInput_p
   */
  private void output(StringBuffer sb_p, String string_p, ITreeContentProvider contentProvider_p, Object menuInput_p) {

    for (Object child : contentProvider_p.getElements(menuInput_p)) {
      if (child instanceof Category) {
        Category category = (Category) child;
        sb_p.append(string_p + "<li>\n");
        sb_p.append(string_p + "<span class='bold'>");
        try {
          sb_p.append(category.getName());
        } catch (NotDefinedException exception_p) {
          sb_p.append(category.getId());
        }
        sb_p.append("</span>");

        sb_p.append(string_p + "<span>");
        try {
          sb_p.append(category.getDescription());
        } catch (NotDefinedException exception_p) {
          sb_p.append(category.getId());
        }
        sb_p.append("</span>\n");

        sb_p.append(string_p + "<ul>\n");

        output(sb_p, string_p + "  ", contentProvider_p, child);

        sb_p.append(string_p + "</ul>\n");
        sb_p.append(string_p + "</li>\n");

      } else if (child instanceof Command) {
        Command command = (Command) child;

        if (!PolicyProvider.INSTANCE.getPolicy().cover(command.getId())) {
          continue;
        }
        sb_p.append(string_p + "<li>\n");

        sb_p.append(string_p + "<span class='bold'>");
        try {
          sb_p.append(command.getName());
        } catch (NotDefinedException exception_p) {
          sb_p.append(command.getId());
        }
        sb_p.append("</span>");

        sb_p.append(string_p + "<span>");
        try {
          sb_p.append(command.getDescription());
        } catch (NotDefinedException exception_p) {
          sb_p.append(command.getId());
        }
        sb_p.append("</span>\n");

        sb_p.append(string_p + "</li>\n");
      }
    }

  }

  protected IStructuredSelection getSelection() {
    IWorkbenchPart activePart = null;
    IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench != null) {
      IWorkbenchWindow windows = workbench.getActiveWorkbenchWindow();
      if (windows != null) {
        IWorkbenchPage page = windows.getActivePage();
        if (page != null) {
          activePart = page.getActivePart();
        }
      }
    }

    if (activePart != null) {
      ISelection selection = activePart.getSite().getSelectionProvider().getSelection();
      if (selection instanceof IStructuredSelection) {
        return (IStructuredSelection) selection;
      }
    }
    return new StructuredSelection();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
