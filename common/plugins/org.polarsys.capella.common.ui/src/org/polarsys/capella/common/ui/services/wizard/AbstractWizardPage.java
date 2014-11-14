/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.services.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * Base class to implement wizard pages.
 */
public abstract class AbstractWizardPage extends WizardPage {
  /**
   * Cache the value of complete status for this page.
   */
  private boolean _computeCompleteStatus;
  /**
   * Determines whether or not the complete status has to be computed.
   */
  private boolean _completeStatus;

  /**
   * Constructor.
   * @param pageName_p
   */
  public AbstractWizardPage(String pageName_p) {
    super(pageName_p);
    initialize();
  }

  /**
   * Constructor.
   * @param pageName_p
   * @param title_p
   * @param titleImage_p
   */
  public AbstractWizardPage(String pageName_p, String title_p, ImageDescriptor titleImage_p) {
    super(pageName_p, title_p, titleImage_p);
    initialize();
  }

  /**
   * Initialize fields.
   */
  protected void initialize() {
    // At startup, we want to compute the 'complete status'.
    _computeCompleteStatus = true;
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    // Configure page title and description.
    setTitle(getPageTitle());
    setDescription(getPageDescription());
    // Create parent composite.
    Composite content = createComposite(parent_p, getContentNumColumn());
    // Initialize dialog units.
    initializeDialogUnits(content);
    // Handle the composite enable property depending on implementors.
    handlePageContentEnablement(content);
    // Create page content
    createPageArea(content);
    // Update buttons bar.
    updateButtons();
    // Compute the preferred size of the content
    Point size = content.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    // Modify the layout data of the content's parent.
    GridData gridData = (GridData) parent_p.getLayoutData();
    // Set the height hint of the max between current value and the new
    // one based on the created content.
    gridData.heightHint = Math.max(size.y + 10, gridData.heightHint);
    // Set the Help system if provided on the wizard page content composite.
    // We have to register the help context id and to implement the performHelp method.
    String helpContextId = getHelpContextId();
    if (null != helpContextId) {
      PlatformUI.getWorkbench().getHelpSystem().setHelp(content, helpContextId);
    }
  }

  /**
   * Handle page content enablement.
   * @param content_p
   */
  protected abstract void handlePageContentEnablement(Composite content_p);

  /**
   * Update page buttons according data status.
   */
  final public void updateButtons() {
    // Something has changed, mark the flag to recompute the complete status.
    _computeCompleteStatus = true;
    setPageComplete(computeCompleteStatus());
  }

  /**
   * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
   */
  @Override
  public boolean isPageComplete() {
    return computeCompleteStatus();
  }

  /**
   * Check conditions to get the page completed
   * @return true the page is complete; false otherwise.
   */
  protected abstract boolean getCompleteStatus();

  /**
   * Create the page content.
   * @param parent_p
   */
  protected abstract void createPageArea(Composite parent_p);

  /**
   * Handle the layout of this page.
   * @param parent_p
   */
  protected void handlePageLayout(Composite parent_p) {
    // Do nothing.
  }

  /**
   * Get the number of columns to be displayed in the content.<br>
   * @return Default implementation return 1 column.
   */
  protected int getContentNumColumn() {
    return 1;
  }

  /**
   * Get the page title.
   * @return
   */
  protected abstract String getPageTitle();

  /**
   * Get the page description.
   * @return
   */
  protected abstract String getPageDescription();

  /**
   * Return the Help context id if any.<br>
   * Default implementation returns <code>null</code>.
   * @return null or an help context id.
   */
  protected String getHelpContextId() {
    return null;
  }

  /**
   * Compute the complete status.
   */
  private boolean computeCompleteStatus() {
    // If something has changed, recompute the complete status; otherwise return the cached value.
    if (_computeCompleteStatus) {
      _completeStatus = getCompleteStatus();
      // Complete status is up-to-date. mark the flag that no computation is needed until a change is made.
      _computeCompleteStatus = false;
    }
    return _completeStatus;
  }

  /**
   * Create a default composite to host page widgets.
   * @param parent_p
   * @param numColumns_p
   * @return
   */
  private Composite createComposite(Composite parent_p, int numColumns_p) {
    Composite composite = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = numColumns_p;
    composite.setLayout(layout);
    setControl(composite);
    return composite;
  }

  /**
   * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
   */
  @Override
  public void performHelp() {
    // This method is called when the wizard dialog shell is the receiver of the SWT 'Help' event.
    String helpContextId = getHelpContextId();
    // If provided, show the current page help.
    if (null != helpContextId) {
      PlatformUI.getWorkbench().getHelpSystem().displayHelp(helpContextId);
    }
  }
}
