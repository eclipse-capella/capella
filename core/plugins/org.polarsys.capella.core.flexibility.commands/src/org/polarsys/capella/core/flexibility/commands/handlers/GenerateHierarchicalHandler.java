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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 *
 */
public class GenerateHierarchicalHandler extends AbstractUiHandler {
  private class GetText {

    Shell shell;
    String value;

    GetText(Shell shell_p) {
      shell = shell_p;
    }

    public void setValue(String value_p) {
      value = value_p;
    }

    public String getValue() {
      TitleAreaDialog dlg = new TitleAreaDialog(shell) {
        Text t;

        /**
         * {@inheritDoc}
         */
        @Override
        protected Control createDialogArea(Composite parent_p) {
          Control control = super.createDialogArea(parent_p);

          // Create a multiple line text field
          t = new Text(parent_p, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
          t.setLayoutData(new GridData(GridData.FILL_BOTH));
          setMessage("Choose a text value"); //$NON-NLS-1$
          return control;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void okPressed() {
          setValue(t.getText());
          super.okPressed();

        }

      };
      dlg.open();
      return value;
    }
  }

  /**
   * {@inheritDoc}
   */
  public Object execute(final ExecutionEvent event_p) throws ExecutionException {

    Resource resource = null;
    final List<EObject> objects = new ArrayList<EObject>();
    IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event_p);
    final EObject root = (EObject) selection.iterator().next();

    TransactionHelper.getExecutionManager(root).execute(new AbstractReadWriteCommand() {
      public void run() {
        BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(root);
        HashMap<String, Class> clazz = new HashMap<String, Class>();
        HashMapSet<String, String> hierarchical = new HashMapSet<String, String>();

        String value = new GetText(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).getValue();
        if (value != null) {
          String[] values = value.split("\r\n");
          for (String val : values) {
            String[] ids = val.split(">");
            if (ids.length > 1) {
              hierarchical.put(ids[0].trim(), ids[1].trim());
            }
          }
        }

        for (String link : hierarchical.keySet()) {
          if (!(clazz.containsKey(link))) {
            Class clazzI = InformationFactory.eINSTANCE.createClass(link);
            clazz.put(link, clazzI);
            architecture.getOwnedDataPkg().getOwnedClasses().add(clazzI);
          }
        }
        for (Collection<String> links : hierarchical.values()) {
          for (String link : links) {
            if (!(clazz.containsKey(link))) {
              Class clazzI = InformationFactory.eINSTANCE.createClass(link);
              clazz.put(link, clazzI);
              architecture.getOwnedDataPkg().getOwnedClasses().add(clazzI);
            }
          }
        }

        for (String id1 : hierarchical.keySet()) {
          Class clazz1 = clazz.get(id1);
          for (String id2 : hierarchical.get(id1)) {
            Class clazz2 = clazz.get(id2);
            Generalization gene = CapellacoreFactory.eINSTANCE.createGeneralization();
            gene.setSub(clazz1);
            gene.setSuper(clazz2);
            clazz1.getOwnedGeneralizations().add(gene);
          }
        }
      }
    });

    return event_p;
  }
}
