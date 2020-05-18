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
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.dialogs.ExportPage;
import org.eclipse.ui.internal.dialogs.ImportExportPage;
import org.eclipse.ui.internal.dialogs.ImportPage;

/**
 *
 */
public class PreferenceImportExportWizard extends Wizard implements IWorkbenchWizard {

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
    private ImportExportPage importExportPage;
    private String page = null;
    
    /**
     * Create an import/export wizard and show the page 
     * with the given id.
     * 
     * @param pageId
     */
    public PreferenceImportExportWizard(String pageId){
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
    public void addPages() {
    	if (page.equals(IMPORT)) {
			importExportPage = new ImportPage(this.workbench, this.selection);
		} else if (page.equals(EXPORT)) {
			importExportPage = new ExportPage(this.workbench, this.selection);
		}
        if (importExportPage != null) {
			addPage(importExportPage);
		}
    }

    /**
     * Initializes the wizard.
     * 
     * @param aWorkbench the workbench
     * @param currentSelection the current selection
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
}
