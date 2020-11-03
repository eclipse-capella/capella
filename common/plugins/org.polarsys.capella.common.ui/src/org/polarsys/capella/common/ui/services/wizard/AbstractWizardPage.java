/*******************************************************************************
 *  Copyright (c) 2007, 2020 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
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
   * @param pageName
   */
  public AbstractWizardPage(String pageName) {
    super(pageName);
    initialize();
  }

  /**
   * Constructor.
   * @param pageName
   * @param title
   * @param titleImage
   */
  public AbstractWizardPage(String pageName, String title, ImageDescriptor titleImage) {
    super(pageName, title, titleImage);
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
  public void createControl(Composite parent) {
    // Configure page title and description.
    setTitle(getPageTitle());
    setDescription(getPageDescription());
    // Create parent composite.
    Composite content = createComposite(parent, getContentNumColumn());
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
    GridData gridData = (GridData) parent.getLayoutData();
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
   * @param content
   */
  protected abstract void handlePageContentEnablement(Composite content);

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
   * @param parent
   */
  protected abstract void createPageArea(Composite parent);

  /**
   * Handle the layout of this page.
   * @param parent
   */
  protected void handlePageLayout(Composite parent) {
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
   * @param parent
   * @param numColumns
   * @return
   */
  private Composite createComposite(Composite parent, int numColumns) {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = numColumns;
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
