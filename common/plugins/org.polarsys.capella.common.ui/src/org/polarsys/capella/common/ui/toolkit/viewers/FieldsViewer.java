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
   * @param parent The parent composite.
   */
  public FieldsViewer(Composite parent) {
    this(parent, SWT.NONE /* No style */);
  }

  /**
   * Constructor.
   * @param parent The parent composite.
   */
  public FieldsViewer(Composite parent, boolean isMultipleSelection) {
    this(parent, isMultipleSelection, SWT.NONE /* No style */, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * @param parent The parent composite.
   * @param isMultipleSelection
   * @param style
   *  @param viewerExpandLevel
   */
  public FieldsViewer(Composite parent, boolean isMultipleSelection, int style, int viewerExpandLevel) {
    _isMultipleSelection = isMultipleSelection;
    _viewerExpandLevel = viewerExpandLevel;
    initialize(parent, style);
  }

  /**
   * Constructor.
   * @param parent The parent composite.
   * @param page The page where fields display messages.
   */
  public FieldsViewer(Composite parent, DialogPage page) {
    this(parent, SWT.NONE /* No style */);
    setPage(page);
  }

  /**
   * Constructor.
   * @param parent The parent composite.
   * @param style
   */
  public FieldsViewer(Composite parent, int style) {
    initialize(parent, style);
  }

  /**
   * Creates the control.
   * @param parent The parent composite.
   */
  protected void createControl(Composite parent) {
    parent.addDisposeListener(new DisposeListener() {
      /**
       * {@inheritDoc}
       */
      public void widgetDisposed(DisposeEvent e) {
        dispose();
      }
    });
  }

  /**
   * Creates the internal composite.
   * @param parent The parent composite.
   * @return The internal composite.
   */
  protected Composite createInternalComposite(Composite parent) {
    return new Composite(parent, SWT.NONE);
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
   * @param eventType The event type.
   * @return The event listeners map.
   */
  public HashMap<Listener, Integer> getEventListeners(int eventType) {
    return _eventListeners;
  }

  /**
   * Gets the events providers map.
   * @param eventType The event type.
   * @return the event providers map.
   */
  public HashMap<Control, Integer> getEventProviders(int eventType) {
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
   * @param eventTypes The event types support OR'ed values (use the '|' character to separate values).
   * @param control The control which listen to the event.
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
  public void handleEvents(int eventTypes, Listener control) {
    _eventListeners.put(control, Integer.valueOf(eventTypes));
  }

  /**
   * Initialize this viewer.
   * @param parent
   * @param style
   */
  protected void initialize(Composite parent, int style) {
    _fields = new ArrayList<FieldEditor>(0);
    _style = style;
    _composite = createInternalComposite(parent);
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
   * @param eventTypes The event types support OR'ed values (use the '|' character to separate values).
   * @param control The control which listen to the event.
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
  public void propagateEvents(int eventTypes, Control control) {
    _eventProviders.put(control, Integer.valueOf(eventTypes));
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
   * @param field The field to register.
   */
  public void registerField(FieldEditor field) {
    if (!_fields.contains(field)) {
      _fields.add(field);
    }
  }

  /**
   * Enable the viewer or not.
   * @param enabled <code>true</code> to enable the viewer else <code>false</code>.
   */
  public void setEnabled(boolean enabled) {
    for (FieldEditor field : _fields) {
      // Enable the vertical scrollbar and disable the Text field only for CSstringFieldEditor
      if (field instanceof CStringFieldEditor) {
        CStringFieldEditor cstField = (CStringFieldEditor) field;
        cstField.getTextControl().setEditable(enabled);
      } else if (field instanceof MdeFieldEditor) {
        MdeFieldEditor mdeField = (MdeFieldEditor) field;
        mdeField.setEnabled(enabled);
      } else {
        field.setEnabled(enabled, this.getControl());
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public abstract void setInput(Object input);


  /**
   * Sets the page where field controls displays messages.
   * @param page The page where to display messages.
   */
  public void setPage(DialogPage page) {
    for (FieldEditor field : _fields) {
      field.setPage(page);
      if (page instanceof IPropertyChangeListener) {
        field.setPropertyChangeListener((IPropertyChangeListener) page);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection, boolean reveal) {
    // Do nothing.
  }

  /**
   * Loads data into the viewer.
   * @param store The store.
   */
  public void setStore(IPreferenceStore store) {
    for (FieldEditor field : _fields) {
      field.setPreferenceStore(store);
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
   * @param field The field to unregister.
   */
  public void unregisterField(FieldEditor field) {
    if (_fields.contains(field)) {
      _fields.remove(field);
      field.setPropertyChangeListener(null);
    }
  }
}
