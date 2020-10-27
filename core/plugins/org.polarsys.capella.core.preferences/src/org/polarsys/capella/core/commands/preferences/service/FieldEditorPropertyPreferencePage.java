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
package org.polarsys.capella.core.commands.preferences.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public abstract class FieldEditorPropertyPreferencePage extends FieldEditorPreferencePage
    implements IFieldEditorPropertyPreferencePage, IWorkbenchPropertyPage, IWorkbenchPreferencePage {

  private static final Logger __logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  public static final String USEPROJECTSETTINGS = "useProjectSettings"; //$NON-NLS-1$

  private static final String TRUE = "true"; //$NON-NLS-1$

  // Stores all created field editors
  private List<FieldEditor> editors = new ArrayList<FieldEditor>();

  // Stores owning element of properties
  private IAdaptable element;

  // Overlay preference store for property pages
  private IPreferenceStore propertiesStore;

  // Cache for page id
  private String pageId;

  /**
   * Constructor
   * 
   * @param style
   *          - layout style
   */
  public FieldEditorPropertyPreferencePage(int style) {
    super(style);
  }

  /**
   * Constructor
   * 
   * @param title
   *          - title string
   * @param style
   *          - layout style
   */
  public FieldEditorPropertyPreferencePage(String title, int style) {
    super(title, style);
  }

  /**
   * Constructor
   * 
   * @param title
   *          - title string
   * @param image
   *          - title image
   * @param style
   *          - layout style
   */
  public FieldEditorPropertyPreferencePage(String title, ImageDescriptor image, int style) {
    super(title, image, style);
  }

  /**
   * Returns the id of the current preference page as defined in plugin.xml Subclasses must implement.
   * 
   * @return - the qualifier
   */
  protected abstract String getPageId();

  /**
   * Receives the object that owns the properties shown in this property page.
   * 
   * @see org.eclipse.ui.IWorkbenchPropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
   */
  public void setElement(IAdaptable element) {
    this.element = element;
  }

  /**
   * Delivers the object that owns the properties shown in this property page.
   * 
   * @see org.eclipse.ui.IWorkbenchPropertyPage#getElement()
   */
  public IAdaptable getElement() {
    return element;
  }

  /**
   * Returns true if this instance represents a property page
   * 
   * @return - true for property pages, false for preference pages
   */
  public boolean isPropertyPage() {
    return (getElement() instanceof IResource);
  }

  /**
   * We override the addField method. This allows us to store each field editor added by subclasses in a list for later
   * processing.
   * 
   * @see org.eclipse.jface.preference.FieldEditorPreferencePage#addField(org.eclipse.jface.preference.FieldEditor)
   */
  @Override
  protected void addField(FieldEditor editor) {
    editors.add(editor);
    super.addField(editor);
  }

  /**
   * We override the createControl method. In case of property pages we create a new PropertyStore as local preference
   * store. After all control have been create, we enable/disable these controls.
   * 
   * @see org.eclipse.jface.preference.PreferencePage#createControl()
   */
  @Override
  public void createControl(Composite parent) {
    // Special treatment for property pages
    if (isPropertyPage()) {
      // Cache the page id
      pageId = getPageId();
      // Create an overlay preference store and fill it with properties
      propertiesStore = new PropertyStore((IResource) getElement(), Activator.getDefault()
          .getPreferenceStore() /*
                                 * new ScopedPreferenceStore(new InstanceScope(), Activator.PLUGIN_ID)
                                 */, pageId);
      // Set overlay store as current preference store

    }
    super.createControl(parent);
    // Update state of all subclass controls
    if (isPropertyPage()) {
      updateFieldEditors();
    }
  }

  /**
   * Returns in case of property pages the overlay store, in case of preference pages the standard preference store
   * 
   * @see org.eclipse.jface.preference.PreferencePage#getPreferenceStore()
   */
  @Override
  public IPreferenceStore getPreferenceStore() {
    if (isPropertyPage()) {
      return propertiesStore;
    }
    return super.getPreferenceStore();
  }

  /*
   * Enables or disables the field editors and buttons of this page
   */
  private void updateFieldEditors() {
    Activator.getDefault().setPropertyStore((IResource) getElement(), propertiesStore);
    ((IPropertyPersistentPreferenceStore) this.propertiesStore).initilizeGuestListeners();
  }

  /**
   * Enables or disables the field editors and buttons of this page Subclasses may override.
   * 
   * @param enabled
   *          - true if enabled
   */
  protected void updateFieldEditors(boolean enabled) {
    Composite parent = getFieldEditorParent();
    Iterator<FieldEditor> it = editors.iterator();
    while (it.hasNext()) {
      FieldEditor editor = it.next();
      editor.setEnabled(enabled, parent);
    }
  }

  /**
   * We override the performOk method. In case of property pages we copy the values in the overlay store into the
   * property values of the selected project. We also save the state of the radio buttons.
   * 
   * @see org.eclipse.jface.preference.IPreferencePage#performOk()
   */
  @Override
  public boolean performOk() {
    boolean result = super.performOk();
    if (isPropertyPage()) {

      // Save state of radiobuttons in project properties
      IResource resource = (IResource) getElement();
      try {
        resource.setPersistentProperty(new QualifiedName(pageId, USEPROJECTSETTINGS), TRUE);

        resource.getPersistentProperty(new QualifiedName(pageId, USEPROJECTSETTINGS));
        if ((this.propertiesStore != null) && (this.propertiesStore instanceof IPropertyPersistentPreferenceStore)) {
          try {
            ((IPropertyPersistentPreferenceStore) this.propertiesStore).save();

          } catch (IOException exception_p) {
            StringBuilder loggerMessage = new StringBuilder("FieldEditorPropertyPreferencePage.performOk(..) _ "); //$NON-NLS-1$
            __logger.warn(loggerMessage.toString(), exception_p);
          }
        }
      } catch (Exception exception_p) {

      }

    }

    return result;
  }

  @Override
  public boolean performCancel() {
    if (propertiesStore != null) {
      ((PropertyStore) this.propertiesStore).setCanceled(true);
    }
    return false;
  }

  public void init(IWorkbench workbench) {
    // Nothing to do.
  }

}
