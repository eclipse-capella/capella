/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.viewers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

import org.polarsys.capella.common.ui.toolkit.fields.CStringFieldEditor;
import org.polarsys.capella.common.ui.toolkit.fields.MdeFieldEditor;

/**
 * The fields viewer provides a viewer with MDE fields support. This component allows to manage fields by providing <code>registerField()</code> and
 * <code>unregisterField()</code> services. Each field representing a data model value must be registered to its parent viewer otherwise the value will never be
 * applied to the model instance target.
 */
public abstract class FieldsViewer extends Viewer {
  // The internal composite.
  private Composite _composite;
  // The event listeners map.
  private HashMap<Listener, Integer> _eventListeners;
  // The event providers map.
  private HashMap<Control, Integer> _eventProviders;
  // The fields list.
  protected ArrayList<FieldEditor> _fields;
  // The Multiple Selection option
  protected boolean _isMultipleSelection;
  // Viewer style.
  private int _style;

  /** Expand level for the tree viewer. */
  protected int _viewerExpandLevel;

  /**
   * Constructor.
   * @param parent_p The parent composite.
   */
  public FieldsViewer(Composite parent_p) {
    this(parent_p, SWT.NONE /* No style */);
  }

  /**
   * Constructor.
   * @param parent_p The parent composite.
   */
  public FieldsViewer(Composite parent_p, boolean isMultipleSelection_p) {
    this(parent_p, isMultipleSelection_p, SWT.NONE /* No style */, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * @param parent_p The parent composite.
   * @param isMultipleSelection_p
   * @param style_p
   *  @param viewerExpandLevel_p
   */
  public FieldsViewer(Composite parent_p, boolean isMultipleSelection_p, int style_p, int viewerExpandLevel_p) {
    _isMultipleSelection = isMultipleSelection_p;
    _viewerExpandLevel = viewerExpandLevel_p;
    initialize(parent_p, style_p);
  }

  /**
   * Constructor.
   * @param parent_p The parent composite.
   * @param page_p The page where fields display messages.
   */
  public FieldsViewer(Composite parent_p, DialogPage page_p) {
    this(parent_p, SWT.NONE /* No style */);
    setPage(page_p);
  }

  /**
   * Constructor.
   * @param parent_p The parent composite.
   * @param style_p
   */
  public FieldsViewer(Composite parent_p, int style_p) {
    initialize(parent_p, style_p);
  }

  /**
   * Creates the control.
   * @param parent_p The parent composite.
   */
  protected void createControl(Composite parent_p) {
    parent_p.addDisposeListener(new DisposeListener() {
      /**
       * {@inheritDoc}
       */
      public void widgetDisposed(DisposeEvent e_p) {
        dispose();
      }
    });
  }

  /**
   * Creates the internal composite.
   * @param parent_p The parent composite.
   * @return The internal composite.
   */
  protected Composite createInternalComposite(Composite parent_p) {
    return new Composite(parent_p, SWT.NONE);
  }

  /**
   * Dispose.
   */
  public void dispose() {
    if (null != _fields) {
      for (FieldEditor fieldEditor : _fields) {
        fieldEditor.dispose();
      }
      _fields.clear();
      _fields = null;
    }
    if (null != _eventListeners) {
      _eventListeners.clear();
      _eventListeners = null;
    }
    if (null != _eventProviders) {
      _eventProviders.clear();
      _eventProviders = null;
    }
  }

  /**
   * Gets the control.
   * @return The control.
   */
  @Override
  public Composite getControl() {
    return _composite;
  }

  /**
   * Gets the event listeners map.
   * @param eventType_p The event type.
   * @return The event listeners map.
   */
  public HashMap<Listener, Integer> getEventListeners(int eventType_p) {
    return _eventListeners;
  }

  /**
   * Gets the events providers map.
   * @param eventType_p The event type.
   * @return the event providers map.
   */
  public HashMap<Control, Integer> getEventProviders(int eventType_p) {
    return _eventProviders;
  }

  /**
   * Gets the fields list.
   * @return The fields list.
   */
  public List<FieldEditor> getFields() {
    return _fields;
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public abstract Object getInput();

  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ISelection getSelection() {
    return null;
  }

  /**
   * Get viewer's style.
   * @return the style
   */
  protected int getStyle() {
    return _style;
  }

  /**
   * Registers the specified control to listen the specified event types.
   * @param eventTypes_p The event types support OR'ed values (use the '|' character to separate values).
   * @param control_p The control which listen to the event.
   * @see SWT#Activate
   * @see SWT#Close
   * @see SWT#Collapse
   * @see SWT#Deactivate
   * @see SWT#DefaultSelection
   * @see SWT#Deiconify
   * @see SWT#Dispose
   * @see SWT#Expand
   * @see SWT#FocusIn
   * @see SWT#FocusOut
   * @see SWT#Help
   * @see SWT#Hide
   * @see SWT#Iconify
   * @see SWT#KeyDown
   * @see SWT#KeyUp
   * @see SWT#Modify
   * @see SWT#MouseDoubleClick
   * @see SWT#MouseDown
   * @see SWT#MouseEnter
   * @see SWT#MouseExit
   * @see SWT#MouseHover
   * @see SWT#MouseMove
   * @see SWT#MouseUp
   * @see SWT#MouseWheel
   * @see SWT#Paint
   * @see SWT#Resize
   * @see SWT#Selection
   * @see SWT#Show
   * @see SWT#Traverse
   * @see SWT#Verify
   */
  public void handleEvents(int eventTypes_p, Listener control_p) {
    _eventListeners.put(control_p, Integer.valueOf(eventTypes_p));
  }

  /**
   * Initialize this viewer.
   * @param parent_p
   * @param style_p
   */
  protected void initialize(Composite parent_p, int style_p) {
    _fields = new ArrayList<FieldEditor>(0);
    _style = style_p;
    _composite = createInternalComposite(parent_p);
    createControl(_composite);
  }

  // ///////////////////// Viewer API //////////////////////////////

  /**
   * Loads values from the model element.
   */
  public void load() {
    for (FieldEditor field : _fields) {
      field.load();
    }
  }

  /**
   * Registers the specified control to propagate the specified event types.
   * @param eventTypes_p The event types support OR'ed values (use the '|' character to separate values).
   * @param control_p The control which listen to the event.
   * @see SWT#Activate
   * @see SWT#Close
   * @see SWT#Collapse
   * @see SWT#Deactivate
   * @see SWT#DefaultSelection
   * @see SWT#Deiconify
   * @see SWT#Dispose
   * @see SWT#Expand
   * @see SWT#FocusIn
   * @see SWT#FocusOut
   * @see SWT#Help
   * @see SWT#Hide
   * @see SWT#Iconify
   * @see SWT#KeyDown
   * @see SWT#KeyUp
   * @see SWT#Modify
   * @see SWT#MouseDoubleClick
   * @see SWT#MouseDown
   * @see SWT#MouseEnter
   * @see SWT#MouseExit
   * @see SWT#MouseHover
   * @see SWT#MouseMove
   * @see SWT#MouseUp
   * @see SWT#MouseWheel
   * @see SWT#Paint
   * @see SWT#Resize
   * @see SWT#Selection
   * @see SWT#Show
   * @see SWT#Traverse
   * @see SWT#Verify
   */
  public void propagateEvents(int eventTypes_p, Control control_p) {
    _eventProviders.put(control_p, Integer.valueOf(eventTypes_p));
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    for (FieldEditor field : _fields) {
      field.load();
    }
  }

  /**
   * Registers the specified field to the current viewer.
   * @param field_p The field to register.
   */
  public void registerField(FieldEditor field_p) {
    if (!_fields.contains(field_p)) {
      _fields.add(field_p);
    }
  }

  /**
   * Enable the viewer or not.
   * @param enabled_p <code>true</code> to enable the viewer else <code>false</code>.
   */
  public void setEnabled(boolean enabled_p) {
    for (FieldEditor field : _fields) {
      // Enable the vertical scrollbar and disable the Text field only for CSstringFieldEditor
      if (field instanceof CStringFieldEditor) {
        CStringFieldEditor cstField = (CStringFieldEditor) field;
        cstField.getTextControl().setEditable(enabled_p);
      } else if (field instanceof MdeFieldEditor) {
        MdeFieldEditor mdeField = (MdeFieldEditor) field;
        mdeField.setEnabled(enabled_p);
      } else {
        field.setEnabled(enabled_p, this.getControl());
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public abstract void setInput(Object input_p);


  /**
   * Sets the page where field controls displays messages.
   * @param page_p The page where to display messages.
   */
  public void setPage(DialogPage page_p) {
    for (FieldEditor field : _fields) {
      field.setPage(page_p);
      if (page_p instanceof IPropertyChangeListener) {
        field.setPropertyChangeListener((IPropertyChangeListener) page_p);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    // Do nothing.
  }

  /**
   * Loads data into the viewer.
   * @param store_p The store.
   */
  public void setStore(IPreferenceStore store_p) {
    for (FieldEditor field : _fields) {
      field.setPreferenceStore(store_p);
    }
  }

  /**
   * Stores changes into the model element.
   */
  public void store() {
    for (FieldEditor field : _fields) {
      field.store();
    }
  }

  /**
   * Unregisters the specified field from the current viewer.
   * @param field_p The field to unregister.
   */
  public void unregisterField(FieldEditor field_p) {
    if (_fields.contains(field_p)) {
      _fields.remove(field_p);
      field_p.setPropertyChangeListener(null);
    }
  }
}
