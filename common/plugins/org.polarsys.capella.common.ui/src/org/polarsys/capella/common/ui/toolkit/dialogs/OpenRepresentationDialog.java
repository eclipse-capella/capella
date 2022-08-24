/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author etraisnel
 *
 */
public class OpenRepresentationDialog extends AbstractSelectionDialog<DRepresentationDescriptor> {

  HashSet<DRepresentationDescriptor> descriptors;

  DRepresentationDescriptor selectedDescriptor;

  public OpenRepresentationDialog(Shell parentShell, Collection<DRepresentationDescriptor> descriptors) {
    super(parentShell);
    this.descriptors = new HashSet<>(descriptors);
  }

  @Override
  public void create() {
    setTitle("Open existing representation");
    super.create();
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);
    setMessage(Messages.openRepresentationMessage);
    // Create label
    createMessageArea(composite);

    GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
        | GridData.VERTICAL_ALIGN_CENTER);
    data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);

    // Create list viewer
    ComboViewer comboViewer = new ComboViewer(composite,
        SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
    comboViewer.getCombo().setFont(parent.getFont());
    comboViewer.getCombo().setLayoutData(data);
    // Set the label provider
    comboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        DRepresentationDescriptor descriptor = (DRepresentationDescriptor) element;
        IInterpreter interpreter = InterpreterUtil.getInterpreter(descriptor);
        String newName = "";

        String titleExpression = descriptor.getName();
        if (!StringUtil.isEmpty(titleExpression)) {
          try {
            newName = interpreter.evaluateString(descriptor, titleExpression);
          } catch (EvaluationException e) {
            SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
          }
        }
        return newName; // $NON-NLS-1$
      }
    });

    // Set the content provider
    comboViewer.setContentProvider(ArrayContentProvider.getInstance());
    comboViewer.setInput(descriptors);

    // Add a selection change listener
    comboViewer.addSelectionChangedListener(
        selectionChangedEvent -> selectedDescriptor = (DRepresentationDescriptor) ((StructuredSelection) selectionChangedEvent
            .getSelection()).getFirstElement());

    setInitialSelection(descriptors.iterator().next());
    comboViewer.setSelection(new StructuredSelection(getInitialSelection()));

    // Add double-click listener
    comboViewer.addDoubleClickListener(event -> okPressed());
    return composite;
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  protected boolean isResizable() {
    return false;
  }

  @Override
  public DialogTray getTray() {
    return null;
  }

  public DRepresentationDescriptor getSelectedDescriptor() {
    return selectedDescriptor;
  }
}