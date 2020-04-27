/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 */
public class AbstractMessageDialog extends MessageDialog {

  /**
   * @param parentShell
   * @param dialogTitle
   * @param dialogTitleImage
   * @param dialogMessage
   * @param dialogImageType
   * @param dialogButtonLabels
   * @param defaultIndex
   */
  public AbstractMessageDialog(Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType,
      String[] dialogButtonLabels, int defaultIndex) {
    super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
  }

  /**
   * This implementation of the <code>Dialog</code> framework method creates and lays out a composite and calls <code>createMessageArea</code> and
   * <code>createCustomArea</code> to populate it. Subclasses should override <code>createCustomArea</code> to add contents below the message.
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    GridLayout headerLayout = new GridLayout(2, false);
    headerLayout.marginLeft = 0;
    headerLayout.marginTop = 0;
    headerLayout.marginBottom = 0;
    headerLayout.marginWidth = 0;

    GridLayout conetntLayout = new GridLayout();
    conetntLayout.numColumns = 1;
    conetntLayout.marginTop = 0;

    GridData headerLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
    headerLayoutData.minimumHeight = 37;
    headerLayoutData.horizontalIndent = 0;
    headerLayoutData.verticalIndent = 0;

    GridData contentLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
    contentLayoutData.horizontalIndent = 0;
    contentLayoutData.horizontalSpan = 2;
    contentLayoutData.verticalIndent = 0;

    parent.setLayout(conetntLayout);
    parent.setLayoutData(contentLayoutData);

    Composite header = new Composite(parent, SWT.FILL);
    header.setLayout(headerLayout);
    header.setLayoutData(headerLayoutData);

    // create message area
    createMessageArea(header);

    ScrolledComposite scrolled = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
    scrolled.setLayout(conetntLayout);
    scrolled.setLayoutData(contentLayoutData);

    Composite dialogAreaComposite = new Composite(scrolled, GridData.FILL | SWT.RESIZE);
    dialogAreaComposite.setLayout(conetntLayout);
    dialogAreaComposite.setLayoutData(contentLayoutData);
    dialogAreaComposite.setFont(parent.getFont());

    createCustomArea(dialogAreaComposite);

    scrolled.setExpandVertical(true);
    scrolled.setExpandHorizontal(true);
    scrolled.setAlwaysShowScrollBars(false);
    scrolled.setMinSize(390, 420);
    scrolled.setVisible(true);
    scrolled.setContent(dialogAreaComposite);

    return dialogAreaComposite;
  }

  /**
   * Creates and returns the contents of the upper part of this dialog (above the button bar).
   * <p>
   * The <code>Dialog</code> implementation of this framework method creates and returns a new <code>Composite</code> with no margins and spacing. Subclasses
   * should override.
   * </p>
   * @param parent The parent composite to contain the dialog area
   * @return the dialog area control
   */
  protected Control createDialogAreaComposite(Composite parent) {
    // create the top level composite for the dialog area
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    layout.verticalSpacing = 0;
    layout.horizontalSpacing = 0;
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    composite.setFont(parent.getFont());
    // Build the separator line
    Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
    titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    return composite;
  }

}
