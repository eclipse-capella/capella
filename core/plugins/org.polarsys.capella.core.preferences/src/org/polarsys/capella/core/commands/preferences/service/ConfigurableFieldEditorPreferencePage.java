/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.osgi.service.prefs.BackingStoreException;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;
import org.polarsys.capella.core.preferences.Activator;

/**
 * A special abstract preference page to host field editors.
 * <p>
 * Subclasses must implement the <code>createFieldEditors</code> method and should override <code>createLayout</code> if a special layout of the field editors
 * is needed.
 * </p>
 */
public abstract class ConfigurableFieldEditorPreferencePage extends FieldEditorPropertyPreferencePage implements IConfigurableFieldEditorPreferencePage,
    IWorkbenchPropertyPage, IPageChangeProvider, IPropertyChangeListener {

  private String pageId;

  private IAdaptable element;

  @Override
  public void setElement(IAdaptable elt) {
    this.element = elt;
  }

  @Override
  public IAdaptable getElement() {
    return element;
  }

  /*
	 * 
	 */
  private ListenerList pageChangedListener = new ListenerList();

  /*
	 * 
	 */

  public static Map<FieldEditor, Composite> EXPERT_FIEL_EDITORS = new HashMap<>(0);
  
  
  public static List<Composite> COMPOSITE_FIEL_EDITORS = new ArrayList<>(0);

  /**
   * Creates a new field editor preference page with the given style, an empty title, and no image.
   * @param style either <code>GRID</code> or <code>FLAT</code>
   */
  protected ConfigurableFieldEditorPreferencePage(int style) {
    super(style);

  }

  /**
   * Creates a new field editor preference page with the given style, an empty title, and no image.
   */
  protected ConfigurableFieldEditorPreferencePage(String id) {
    super(SWT.FLAT);
    this.pageId = id;
    getPropertyPagesIdentifients().add(pageId);

  }

  /**
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
    // Nothing to do.
    this.addPageChangedListener(new EclipseNodePreferencesChangeListener());
  }

  public void propertyChange(Preferences.PropertyChangeEvent event) {
  }

  /**
   * @param editor
   * @param userProfileModeEnum
   * @param parent
   */
  protected void addField(FieldEditor editor, UserProfileModeEnum userProfileModeEnum, Composite parent) {
    if (UserProfileModeEnum.Expert.equals(userProfileModeEnum) && (parent != null) && !parent.isDisposed()) {
      EXPERT_FIEL_EDITORS.put(editor, parent);
      editor.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID), parent);
      parent.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
    }
    editor.setPreferenceStore(Activator.getDefault().getPreferenceStore());
    super.addField(editor);
  }

  @Override
  protected String getPageId() {
    return this.pageId;
  }

  /**
   * @return
   */
  @Override
  public boolean isPropertyPage() {
    return (element != null) && (getElement() instanceof IResource);
  }

  /**
   * @param editor
   * @param userProfileModeEnum
   * @param parent
   */
  protected void addField(FieldEditor editor, UserProfileModeEnum userProfileModeEnum, Composite parent, Class scope) {
    if (UserProfileModeEnum.Expert.equals(userProfileModeEnum) && (parent != null) && !parent.isDisposed()) {
      EXPERT_FIEL_EDITORS.put(editor, parent);
      editor.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID), parent);
      parent.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
    } else if(parent != null){
      editor.setEnabled(true, parent);
      parent.setEnabled(true);
    }
    ScopedCapellaPreferencesStore.fields.put(editor, null);
    editor.setPreferenceStore(Activator.getDefault().getPreferenceStore());
    super.addField(editor);
  }

  @Override
  protected void addField(FieldEditor editor) {
    editor.setPreferenceStore(Activator.getDefault().getPreferenceStore());
    this.addField(editor, UserProfileModeEnum.User, null);
  }

  @Override
  protected void createFieldEditors() {

  }

  @Override
  protected void checkState() {
    super.checkState();

    Object[] lisObjects = pageChangedListener.getListeners();
    for (Object object : lisObjects) {
      EclipseNodePreferencesChangeListener listener = (EclipseNodePreferencesChangeListener) object;
      listener.pageChanged(new PageChangedEvent(this, getSelectedPage()));
    }
  }

  @Override
  public Object getSelectedPage() {

    return getFieldEditorParent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addPageChangedListener(IPageChangedListener listener) {
    pageChangedListener.add(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removePageChangedListener(IPageChangedListener listener) {
    pageChangedListener.remove(listener);

  }

  @Override
  public Control getControl() {
    return super.getControl();
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    if (isPropertyPage()) {
      return super.getPreferenceStore();
    }

    return Activator.getDefault().getPreferenceStore();
  }

  @Override
  public boolean performOk() {
    super.performOk();
    try {
      Platform.getPreferencesService().getRootNode().flush();
    } catch (BackingStoreException exception) {
      StringBuilder loggerMessage = new StringBuilder("ConfigurableFieldEditorPreferencePage.performOk(..) _ "); //$NON-NLS-1$
    }
    ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID).save();
    return super.performOk();
  }

  public void enablePreferencesPage(boolean isEnable) {
	  
	  
	  for (int i = 0; i < COMPOSITE_FIEL_EDITORS.size(); i++) {
		  
		  final Composite composite = COMPOSITE_FIEL_EDITORS.get(i);
	      if ((composite != null) && composite.isDisposed()) {
	    	  COMPOSITE_FIEL_EDITORS.remove(i);
	      } else if (composite != null) {
	    	  composite.setEnabled(isEnable);
	    	  composite.pack();
	      }
		
	}
	  

	  
    Collection<FieldEditor> fields = EXPERT_FIEL_EDITORS.keySet();
    for (Object field : fields) {
      FieldEditor fieldEditorPreferencePage = (FieldEditor) field;
      final Composite composite = EXPERT_FIEL_EDITORS.get(fieldEditorPreferencePage);
      if ((composite != null) && composite.isDisposed()) {
        EXPERT_FIEL_EDITORS.remove(composite);
      } else if (composite != null) {
        fieldEditorPreferencePage.setEnabled(isEnable, composite);
      }
    }

    Iterator it = EXPERT_FIEL_EDITORS.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pairs = (Map.Entry) it.next();
      final Composite composite = (Composite) pairs.getValue();
      if ((composite != null) && composite.isDisposed()) {
        EXPERT_FIEL_EDITORS.remove(composite);
      } else if (composite != null) {
        composite.setEnabled(isEnable);
      }
      it.remove(); // avoids a ConcurrentModificationException
    }
  }
}
