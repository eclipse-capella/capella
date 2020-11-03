/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public abstract class FieldEditorPropertyPreferencePage extends FieldEditorPreferencePage
    implements IFieldEditorPropertyPreferencePage, IWorkbenchPropertyPage, IWorkbenchPreferencePage {

  // Stores all created field editors
  private List<FieldEditor> editors = new ArrayList<FieldEditor>();

  // Stores owning element of properties
  private IAdaptable element;

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
      // Set overlay store as current preference store
      PropertyStore store = new PropertyStore((IResource) getElement(), Activator.getDefault().getPreferenceStore());
      setPreferenceStore(store);
      
    } else {
      ScopedPreferenceStore store = (ScopedPreferenceStore) doGetPreferenceStore();
      setPreferenceStore(store);
    }
    
    super.createControl(parent);
  }

  public void setPreferenceStore(IPreferenceStore store) {
    super.setPreferenceStore(store);

    Iterator<FieldEditor> it = editors.iterator();
    while (it.hasNext()) {
      FieldEditor editor = it.next();
      editor.setPreferenceStore(store);
    }
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
  
  @Override
  public boolean performCancel() {
    IPreferenceStore store = (IPreferenceStore) getPreferenceStore();
    if (store instanceof PropertyStore) {
      ((PropertyStore) store).setCanceled(true);
    }
    return true;
  }

  public void init(IWorkbench workbench) {
    // Nothing to do.
  }

}
