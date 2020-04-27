/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.transferer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.dialogs.ImportExportWizard;

/**
 *
 */
public class CapellaImportExportPreferences extends ImportExportWizard {

	/**
	 * Constant used to to specify to the import/export wizard
	 * which page should initially be shown. 
	 */
	public static final String IMPORT = "import";	//$NON-NLS-1$
	/**
	 * Constant used to to specify to the import/export wizard
	 * which page should initially be shown. 
	 */
	public static final String EXPORT = "export";	//$NON-NLS-1$
		
    private IWorkbench workbench;
    private IStructuredSelection selection;
    private PreferencesImportExportPage importExportPage;
    private String page = null;
	private boolean isPageCompleted;
    
   

	/**
     * Create an import/export wizard and show the page 
     * with the given id.
     * 
     * @param pageId
     */
    public CapellaImportExportPreferences(String pageId){
    	super(pageId);
    	page = pageId;
    }
    
    /**
     * Subclasses must implement this <code>IWizard</code> method 
     * to perform any special finish processing for their wizard.
     */
    public boolean performFinish() {
    	importExportPage.saveWidgetValues();
        return true;
    }

    /**
     * Creates the wizard's pages lazily.
     */
    @Override
    public void addPages() {
    	if (page.equals(IMPORT)) {
		} else if (page.equals(EXPORT)) {
			importExportPage = new PreferencesImportExportPage(this.workbench, this.selection);
			
		}
        if (importExportPage != null) {
			addPage(importExportPage);
		}
    }

    /**
     * Initializes the wizard.
     * 
     * @param aWorkbench the workbench
     * @param currentSelection the current selectio
     */
    public void init(IWorkbench aWorkbench,
            IStructuredSelection currentSelection) {
        this.workbench = aWorkbench;
        this.selection = currentSelection;

        ImageDescriptor wizardBannerImage = null;
        if (IMPORT.equals(page)){
        	wizardBannerImage = WorkbenchImages
                .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_WIZ);
        	setWindowTitle(WorkbenchMessages.ImportWizard_title);
        }
        else if (EXPORT.equals(page)){
        	wizardBannerImage = WorkbenchImages
                    .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ);
        	setWindowTitle(WorkbenchMessages.ExportWizard_title);
        }
        if (wizardBannerImage != null) {
			setDefaultPageImageDescriptor(wizardBannerImage);
		}
        setNeedsProgressMonitor(true);
    }

	@Override
	public boolean canFinish() {
		return   this.isPageCompleted();
	}

	/**
	 * @return
	 */
	private boolean isPageCompleted() {
		return this.isPageCompleted;
	}
    
	 public void setPageCompleted(boolean isPageCompleted_p) {
			isPageCompleted = isPageCompleted_p;
		}
    
    
}
