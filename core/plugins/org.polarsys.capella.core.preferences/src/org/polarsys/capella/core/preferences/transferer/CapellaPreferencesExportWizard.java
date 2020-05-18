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

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.wizards.preferences.PreferencesExportWizard;
import org.eclipse.ui.internal.wizards.preferences.PreferencesMessages;

/**
 * Standard workbench wizard for exporting preferences from the workspace
 * to the local file system.
 * <p>
 * This class may be instantiated and used without further configuration;
 * this class is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * <pre>
 * IWizard wizard = new PreferencesExportWizard();
 * wizard.init(workbench, selection);
 * WizardDialog dialog = new WizardDialog(shell, wizard);
 * dialog.open();
 * </pre>
 * During the call to <code>open</code>, the wizard dialog is presented to the
 * user. When the user hits Finish, the user-selected workspace preferences 
 * are exported to the user-specified location in the local file system,
 * the dialog closes, and the call to <code>open</code> returns.
 * </p>
 * 
 * @since 3.1
 * 
 */
public class CapellaPreferencesExportWizard extends PreferencesExportWizard implements IExportWizard {

    private WizardPreferencesTransfererExportPage mainPage;

    /**
     * Creates a wizard for exporting workspace preferences to the local file system.
     */
    public CapellaPreferencesExportWizard() {
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings section = workbenchSettings
                .getSection("PreferencesExportWizard");//$NON-NLS-1$
        if (section == null) {
			section = workbenchSettings.addNewSection("PreferencesExportWizard");//$NON-NLS-1$
		}
        setDialogSettings(section);
    }

    /* (non-Javadoc)
     * Method declared on IWizard.
     */
   
    public void addPages() {
       // super.addPages();
        mainPage = new WizardPreferencesTransfererExportPage();
        addPage(mainPage);
    }

    /* (non-Javadoc)
     * Method declared on IWorkbenchWizard.
     */
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        setWindowTitle(PreferencesMessages.PreferencesExportWizard_export);
        setDefaultPageImageDescriptor(WorkbenchImages
                .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_PREF_WIZ));
        setNeedsProgressMonitor(true);
    }

    /* (non-Javadoc)
     * Method declared on IWizard.
     */
    public boolean performFinish() {
        return mainPage.finish();
    }
    
    
}
