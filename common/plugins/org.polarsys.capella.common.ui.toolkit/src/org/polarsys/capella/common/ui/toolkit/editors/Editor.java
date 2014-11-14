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
package org.polarsys.capella.common.ui.toolkit.editors;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.polarsys.capella.common.ui.toolkit.fields.MdeFieldEditor;
import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;

/**
 * The generic editor. Editors are Eclipse wizards and they contain only one page.
 */
public abstract class Editor extends Wizard {
  // The editor page.
  public EditorPage _page;
  // The element store.
  private IPreferenceStore _store;

  /**
   * Constructs the generic editor.
   * @param pageContentProvider_p The page content provider.
   * @param store_p The data binding controller.
   */
  public Editor(IPageContentProvider pageContentProvider_p, IPreferenceStore store_p) {
    _page = new EditorPage(pageContentProvider_p);
    _store = store_p;
  }

  /**
   * @see Wizard#addPages()
   */
  @Override
  public final void addPages() {
    addPage(_page);
    _page.setPageComplete(true);
  }

  /**
   * Sets the current editor enable or not.
   * @param enabled_p <code>True</code> to enable the editor else false.
   */
  public void setEnabled(boolean enabled_p) {
    if (null != _page) {
      _page.setEnabled(enabled_p);
    }
  }

  /**
   * Turns the editor into the consult mode.
   * @param consult_p <code>True</code> to enable the consult mode else <code>false</code>.
   */
  public void setConsultMode(boolean consult_p) {
    if (null != _page) {
      _page.setConsultMode(consult_p);
    }
  }

  /**
   * Sets the title of the underlined page.
   * @param title_p The page title.
   */
  public void setPageTitle(String title_p) {
    if (null != _page) {
      _page.setTitle(title_p);
    }
  }

  /**
   * Sets the image of the page header.
   * @param image_p The image of the page header.
   */
  public void setPageImage(ImageDescriptor image_p) {
    if (null != _page) {
      _page.setImageDescriptor(image_p);
    }
  }

  /**
   * Gets the title of the underlined page.
   * @return The page title.
   */
  public String getPageTitle() {
    if (null != _page) {
      return _page.getTitle();
    }
    return null;
  }

  /**
   * Sets the description of the underlined page.
   * @param description_p The page description.
   */
  public void setPageDescription(String description_p) {
    if (null != _page) {
      _page.setDescription(description_p);
    }
  }

  /**
   * Gets the description of the underlined page.
   * @return The page description.
   */
  public String getPageDescription() {
    if (null != _page) {
      return _page.getDescription();
    }
    return null;
  }

  /**
   * Gets the data binding controller.
   * @return The data binding controller.
   */
  public IPreferenceStore getStore() {
    return _store;
  }

  /**
   * Gets the element this editor applies to.
   * @return The element this editor applies to.
   */
  public EClass getElementType() {
    return _page.getElementType();
  }

  /**
   * The editor page.
   */
  protected class EditorPage extends WizardPage implements IPropertyChangeListener {
    // The page content provider.
    private IPageContentProvider _contentProvider = null;
    // The tab folder.
    private TabFolder _folder = null;
    // The viewers list.
    private ArrayList<FieldsViewer> _containers = new ArrayList<FieldsViewer>();
    // The consultation mode.
    private boolean _consultMode = false;
    // The page state.
    private boolean _isEnabled = true;

    /**
     * Constructs the editor page.
     * @param contentProvider_p The page content provider.
     */
    public EditorPage(IPageContentProvider contentProvider_p) {
      super(contentProvider_p.getId());
      _contentProvider = contentProvider_p;
      setTitle(contentProvider_p.getPageTitle());
      setDescription(contentProvider_p.getPageDescription());
      setPageComplete(false);
    }

    /**
     * Constructs the editor page.
     * @param contentProvider_p The page content provider.
     * @param titleImage_p The page title image.
     */
    public EditorPage(IPageContentProvider contentProvider_p, ImageDescriptor titleImage_p) {
      super(contentProvider_p.getId(), contentProvider_p.getPageTitle(), titleImage_p);
      _contentProvider = contentProvider_p;
      setDescription(contentProvider_p.getPageDescription());
      setPageComplete(false);
    }

    /**
     * Gets the content provider.
     * @return The content provider.
     */
    public IPageContentProvider getContentProvider() {
      return _contentProvider;
    }

    /**
     * Gets the element this editor applies to.
     * @return The element this editor applies to.
     */
    public EClass getElementType() {
      return _contentProvider.getElementType();
    }

    /**
     * @see WizardPage#createControl(Composite)
     */
    @SuppressWarnings("synthetic-access")
    public void createControl(Composite parent_p) {
      ITabDescriptor[] descriptors = _contentProvider.getTabDescriptors();
      if ((null == descriptors) || (0 == descriptors.length)) {
        return;
      }

      // Adds the tab folder, if descriptors exists.
      _folder = new TabFolder(parent_p, SWT.TOP);

      // Parses descriptors list and builds tab items.
      for (int i = 0; i < descriptors.length; i++) {
        ITabDescriptor descriptor = descriptors[i];

        TabItem tabItem = new TabItem(_folder, SWT.NONE);
        tabItem.setText(descriptor.getLabel());
        FieldsViewer content = descriptor.getContent(_folder);
        if (null != content) {
          content.setStore(_store);

          content.setPage(this);
          content.load();
          _containers.add(content);

          tabItem.setControl(content.getControl());

          if (_consultMode) {
            content.setEnabled(false);
          }
        }
      }

      // Sets the page control.
      setControl(_folder);
    }

    /**
     * Loads data from the model element to the editor through the store.
     */
    public void load() {
      for (FieldsViewer composite : _containers) {
        composite.load();
      }
    }

    /**
     * Stores data to the model element from the editor through the store.
     */
    public void store() {
      for (FieldsViewer composite : _containers) {
        composite.store();
      }
    }


    /**
     * Turns the editor page into consult mode.
     * @param consult_p <code>True</code> to enable the consult mode else <code>false</code>.
     */
    public void setConsultMode(boolean consult_p) {
      _consultMode = consult_p;
    }

    /**
     * Checks if the current page is in consult mode.
     * @return <code>True</code> if the page is in his consultation mode else <code>false</code>.
     */
    public boolean isConsultMode() {
      return _consultMode;
    }

    /**
     * Enable or not the page content.
     * @param enabled_p <code>True</code> to enable the page else <code>false</code>.
     */
    public void setEnabled(boolean enabled_p) {
      if (!_consultMode) {
        // Registers the new state.
        _isEnabled = enabled_p;

        // Updates state.
        if (null != _folder) {
          for (int i = 0; i < _folder.getItemCount(); i++) {
            TabItem tab = _folder.getItem(i);
            tab.getControl().setEnabled(enabled_p);
          }
        }
      }
    }

    /**
     * Checks if the current page is enabled or not.
     * @return <code>True</code> if the page is enabled else <code>false</code>.
     */
    public boolean isEnabled() {
      return _isEnabled;
    }


    // private String oldMessage = null;
    private Color foreground = null;

    /**
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event_p) {
      // Filters the events.
      if (FieldEditor.IS_VALID.equals(event_p.getProperty())) {
        if (event_p.getSource() instanceof MdeFieldEditor) {
          MdeFieldEditor field = (MdeFieldEditor) event_p.getSource();

          if (((Boolean) event_p.getNewValue()).booleanValue()) {
            // Update the foreground color.
            if (null != field.getValueControl()) {
              if (null != foreground) {
                field.getValueControl().setForeground(foreground);
                foreground = null;
              }
            }
          } else {
            // Update the foreground color.
            if (null != field.getValueControl()) {
              foreground = field.getValueControl().getForeground();
              field.getValueControl().setForeground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
            }
          }
        }

        setPageComplete(getErrorMessage() == null);
      }
    }
  }

}
