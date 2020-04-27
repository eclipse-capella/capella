/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  public EditorPage page;
  // The element store.
  private IPreferenceStore store;

  /**
   * Constructs the generic editor.
   * @param pageContentProvider The page content provider.
   * @param store The data binding controller.
   */
  public Editor(IPageContentProvider pageContentProvider, IPreferenceStore store) {
    this.page = new EditorPage(pageContentProvider);
    this.store = store;
  }

  /**
   * @see Wizard#addPages()
   */
  @Override
  public final void addPages() {
    addPage(page);
    page.setPageComplete(true);
  }

  /**
   * Sets the current editor enable or not.
   * @param enabled <code>True</code> to enable the editor else false.
   */
  public void setEnabled(boolean enabled) {
    if (null != page) {
      page.setEnabled(enabled);
    }
  }

  /**
   * Turns the editor into the consult mode.
   * @param consult <code>True</code> to enable the consult mode else <code>false</code>.
   */
  public void setConsultMode(boolean consult) {
    if (null != page) {
      page.setConsultMode(consult);
    }
  }

  /**
   * Sets the title of the underlined page.
   * @param title The page title.
   */
  public void setPageTitle(String title) {
    if (null != page) {
      page.setTitle(title);
    }
  }

  /**
   * Sets the image of the page header.
   * @param image The image of the page header.
   */
  public void setPageImage(ImageDescriptor image) {
    if (null != page) {
      page.setImageDescriptor(image);
    }
  }

  /**
   * Gets the title of the underlined page.
   * @return The page title.
   */
  public String getPageTitle() {
    if (null != page) {
      return page.getTitle();
    }
    return null;
  }

  /**
   * Sets the description of the underlined page.
   * @param description The page description.
   */
  public void setPageDescription(String description) {
    if (null != page) {
      page.setDescription(description);
    }
  }

  /**
   * Gets the description of the underlined page.
   * @return The page description.
   */
  public String getPageDescription() {
    if (null != page) {
      return page.getDescription();
    }
    return null;
  }

  /**
   * Gets the data binding controller.
   * @return The data binding controller.
   */
  public IPreferenceStore getStore() {
    return store;
  }

  /**
   * Gets the element this editor applies to.
   * @return The element this editor applies to.
   */
  public EClass getElementType() {
    return page.getElementType();
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
     * @param contentProvider The page content provider.
     */
    public EditorPage(IPageContentProvider contentProvider) {
      super(contentProvider.getId());
      _contentProvider = contentProvider;
      setTitle(contentProvider.getPageTitle());
      setDescription(contentProvider.getPageDescription());
      setPageComplete(false);
    }

    /**
     * Constructs the editor page.
     * @param contentProvider The page content provider.
     * @param titleImage The page title image.
     */
    public EditorPage(IPageContentProvider contentProvider, ImageDescriptor titleImage) {
      super(contentProvider.getId(), contentProvider.getPageTitle(), titleImage);
      _contentProvider = contentProvider;
      setDescription(contentProvider.getPageDescription());
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
    public void createControl(Composite parent) {
      ITabDescriptor[] descriptors = _contentProvider.getTabDescriptors();
      if ((null == descriptors) || (0 == descriptors.length)) {
        return;
      }

      // Adds the tab folder, if descriptors exists.
      _folder = new TabFolder(parent, SWT.TOP);

      // Parses descriptors list and builds tab items.
      for (int i = 0; i < descriptors.length; i++) {
        ITabDescriptor descriptor = descriptors[i];

        TabItem tabItem = new TabItem(_folder, SWT.NONE);
        tabItem.setText(descriptor.getLabel());
        FieldsViewer content = descriptor.getContent(_folder);
        if (null != content) {
          content.setStore(store);

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
     * @param consult <code>True</code> to enable the consult mode else <code>false</code>.
     */
    public void setConsultMode(boolean consult) {
      _consultMode = consult;
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
     * @param enabled <code>True</code> to enable the page else <code>false</code>.
     */
    public void setEnabled(boolean enabled) {
      if (!_consultMode) {
        // Registers the new state.
        _isEnabled = enabled;

        // Updates state.
        if (null != _folder) {
          for (int i = 0; i < _folder.getItemCount(); i++) {
            TabItem tab = _folder.getItem(i);
            tab.getControl().setEnabled(enabled);
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
    public void propertyChange(PropertyChangeEvent event) {
      // Filters the events.
      if (FieldEditor.IS_VALID.equals(event.getProperty())) {
        if (event.getSource() instanceof MdeFieldEditor) {
          MdeFieldEditor field = (MdeFieldEditor) event.getSource();

          if (((Boolean) event.getNewValue()).booleanValue()) {
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
