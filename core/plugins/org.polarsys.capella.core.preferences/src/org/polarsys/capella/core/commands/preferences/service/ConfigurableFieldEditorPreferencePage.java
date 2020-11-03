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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;

/**
 * A special abstract preference page to host field editors.
 * <p>
 * Subclasses must implement the <code>createFieldEditors</code> method and should override <code>createLayout</code> if
 * a special layout of the field editors is needed.
 * </p>
 */
public abstract class ConfigurableFieldEditorPreferencePage extends FieldEditorPropertyPreferencePage
    implements IConfigurableFieldEditorPreferencePage, IPageChangeProvider {

  private String pageId;

  private ListenerList pageChangedListener = new ListenerList();

  public static Map<FieldEditor, Composite> EXPERT_FIELD_EDITORS = new HashMap<>(0);

  public static List<Composite> COMPOSITE_FIELD_EDITORS = new ArrayList<>(0);

  /**
   * Creates a new field editor preference page with the given style, an empty title, and no image.
   * 
   * @param style
   *          either <code>GRID</code> or <code>FLAT</code>
   */
  protected ConfigurableFieldEditorPreferencePage(int style) {
    super(style);
  }

  /**
   * Creates a new field editor preference page with the style FLAT, an empty title, and no image.
   */
  protected ConfigurableFieldEditorPreferencePage(String id) {
    super(SWT.FLAT);
    this.pageId = id;
  }

  /**
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
    // Nothing to do.
    this.addPageChangedListener(new EclipseNodePreferencesChangeListener());
  }

  /**
   * @param editor
   * @param userProfileModeEnum
   * @param parent
   */
  protected void addField(FieldEditor editor, UserProfileModeEnum userProfileModeEnum, Composite parent) {
    if (UserProfileModeEnum.Expert.equals(userProfileModeEnum) && (parent != null) && !parent.isDisposed()) {
      EXPERT_FIELD_EDITORS.put(editor, parent);
      editor.setEnabled(
          ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID),
          parent);
      parent.setEnabled(
          ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
    }
    super.addField(editor);
  }

  protected String getPageId() {
    return pageId;
  }

  /**
   * @param editor
   * @param userProfileModeEnum
   * @param parent
   */
  protected void addField(FieldEditor editor, UserProfileModeEnum userProfileModeEnum, Composite parent, Class scope) {
    if (UserProfileModeEnum.Expert.equals(userProfileModeEnum) && (parent != null) && !parent.isDisposed()) {
      EXPERT_FIELD_EDITORS.put(editor, parent);
      editor.setEnabled(
          ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID),
          parent);
      parent.setEnabled(
          ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
    } else if (parent != null) {
      editor.setEnabled(true, parent);
      parent.setEnabled(true);
    }
    super.addField(editor);
  }

  @Override
  protected void addField(FieldEditor editor) {
    this.addField(editor, UserProfileModeEnum.User, null);
  }
  
  /**
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  protected void setEnable(Composite parent, UserProfileModeEnum userMode) {
    parent.setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(XmlPreferencesConfig.USER_PROFILE_MODE_ID));
    COMPOSITE_FIELD_EDITORS.add(parent);
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

  public void enablePreferencesPage(boolean isEnable) {

    for (int i = 0; i < COMPOSITE_FIELD_EDITORS.size(); i++) {

      final Composite composite = COMPOSITE_FIELD_EDITORS.get(i);
      if ((composite != null) && composite.isDisposed()) {
        COMPOSITE_FIELD_EDITORS.remove(i);
      } else if (composite != null) {
        composite.setEnabled(isEnable);
        composite.pack();
      }

    }

    Collection<FieldEditor> fields = EXPERT_FIELD_EDITORS.keySet();
    for (Object field : fields) {
      FieldEditor fieldEditorPreferencePage = (FieldEditor) field;
      final Composite composite = EXPERT_FIELD_EDITORS.get(fieldEditorPreferencePage);
      if ((composite != null) && composite.isDisposed()) {
        EXPERT_FIELD_EDITORS.remove(composite);
      } else if (composite != null) {
        fieldEditorPreferencePage.setEnabled(isEnable, composite);
      }
    }

    Iterator it = EXPERT_FIELD_EDITORS.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pairs = (Map.Entry) it.next();
      final Composite composite = (Composite) pairs.getValue();
      if ((composite != null) && composite.isDisposed()) {
        EXPERT_FIELD_EDITORS.remove(composite);
      } else if (composite != null) {
        composite.setEnabled(isEnable);
      }
      it.remove(); // avoids a ConcurrentModificationException
    }
  }
}
