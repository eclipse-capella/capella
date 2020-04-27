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
package org.polarsys.capella.core.commands.preferences.ui;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.PreferencesUIMessages;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.CategoryTreeNode;
import org.polarsys.capella.core.commands.preferences.model.ICategoryTreeNode;
import org.polarsys.capella.core.commands.preferences.model.IItemNode;
import org.polarsys.capella.core.commands.preferences.model.ItemNode;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.IItemFilter;
import org.polarsys.capella.core.commands.preferences.util.GUIUtil;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Encapsulation of the control for browsing and manipulating items in the items preference page.
 * <p>
 * Clients can use this class to implement their own filtered items preference page.
 * </p>
 */
public class CommandSelectionContainer {

  /** name of the lock icon file */
  private static final String LOCK_ICON = "icons/lock.gif"; //$NON-NLS-1$

  /*
   * 
   */
  static final String CATEGORIES_PROMPT = PreferencesUIMessages.prefs_categories_prompt;

  /*
   * 
   */
  static final String CONSTRAINTS_PROMPT = PreferencesUIMessages.prefs_constraints_prompt;

  /*
   * 
   */
  static final String NO_SELECTION = PreferencesUIMessages.prefs_no_selection;

  /*
   * 
   */
  static final String NO_CATEGORY_DESCRIPTION = PreferencesUIMessages.prefs_no_description_category;

  /*
   * 
   */
  private CheckboxTreeViewer categoryTree;

  /*
   * 
   */
  private CheckboxTableViewer itemList;

  /*
   * 
   */
  private StyledText detailsArea;

  /*
   * 
   */

  private Mediator mediator;

  /*
   * 
   */

  private ICategoryTreeNode rootcategory;

  /*
   * 
   */
  private final IItemFilter filter;

  /*
     * 
     */
  private PreferencesFilter preferenceFilter;

  /*
   * 
   */
  private CategoriesPreferencesFilter categoriesFilter;

  /**
   * Initializes me without a constraint filter.
   */
  public CommandSelectionContainer() {
    this(IItemFilter.IDENTITY_INSTANCE);
  }

  /**
   * Initializes me with a constraint filter.
   * 
   * @param filter
   *          used to filter the items which are presented in this composite (must not be <code>null</code>)
   */
  public CommandSelectionContainer(IItemFilter filter) {
    if (filter == null) {
      throw new IllegalArgumentException("null filter"); //$NON-NLS-1$
    }

    this.filter = filter;
  }

  /**
   * Content provider for the category tree.
   */
  private class CategoryTreeContents implements ITreeContentProvider {

    // implements the inherited method
    public Object[] getChildren(Object parentElement) {
      return ((ICategoryTreeNode) parentElement).getChildren();
    }

    // implements the inherited method
    public Object getParent(Object element) {
      return ((ICategoryTreeNode) element).getParent();
    }

    // implements the inherited method
    public boolean hasChildren(Object element) {
      return ((ICategoryTreeNode) element).hasChildren();
    }

    // implements the inherited method
    public Object[] getElements(Object inputElement) {
      return getChildren(inputElement);
    }

    // implements the inherited method
    @Override
    public void dispose() {
      // no cached resources to dispose
    }

    // implements the inherited method
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      // nothing to do
    }
  }

  /**
   * Content provider for the constraints list.
   */
  private class ItemListContents implements IStructuredContentProvider, ICheckStateListener {

    private CheckboxTableViewer viewer;
    private ICategoryTreeNode category;

    public Object[] getElements(Object inputElement) {
      if (inputElement == null) {
        return new Object[0];
      } else {
        category = (ICategoryTreeNode) inputElement;

        return category.getItems().toArray();
      }
    }

    @Override
    public void dispose() {
      // nothing to dispose
    }

    @Override
    public void inputChanged(Viewer newViewer, Object oldInput, Object newInput) {
      if (viewer != null) {
        viewer.removeCheckStateListener(this);
      }

      viewer = (CheckboxTableViewer) newViewer;
      category = (ICategoryTreeNode) newInput;

      if (viewer != null) {
        viewer.addCheckStateListener(this);
      }
    }

    /*
     * (non-Javadoc) Redefines/Implements/Extends the inherited method.
     */
    public void checkStateChanged(CheckStateChangedEvent event) {
      category.updateCheckState((IItemNode) event.getElement());
    }
  }

  /**
   * A <i>Mediator</i> to coordinate the category tree, item list, and details pane in the GUI.
   */
  private class Mediator implements ISelectionChangedListener, ICheckStateListener {

    private boolean respondingToUserSelection;

    // implements the interface method
    public void checkStateChanged(CheckStateChangedEvent event) {
      Object element = event.getElement();

      if (element instanceof ICategoryTreeNode) {
        ICategoryTreeNode node = (ICategoryTreeNode) element;

        if (!respondingToUserSelection) {
          respondingToUserSelection = true;

          try {
            node.checkStateChanged(event);

            // update the constraint selections of the currently
            // selected category (because the one that changed
            // might be an ancestory
            IStructuredSelection selection = (IStructuredSelection) getCategoryTree().getSelection();

            if (!selection.isEmpty()) {
              selectCategory((ICategoryTreeNode) selection.getFirstElement());
            }
          } finally {
            respondingToUserSelection = false;
          }
        }
      } else {
        IItemNode node = (IItemNode) element;

        if (!respondingToUserSelection) {
          respondingToUserSelection = true;

          try {
            node.checkStateChanged(event);
          } finally {
            respondingToUserSelection = false;
          }
        }
      }
    }

    // implements the interface method
    public void selectionChanged(SelectionChangedEvent event) {
      IStructuredSelection selection = (IStructuredSelection) event.getSelection();

      if (event.getSource().equals(getCategoryTree())) {
        handleCategorySelection(selection);
      } else if (event.getSource().equals(getItemList())) {
        handleItemSelection(selection);
      }
    }

    /**
     * Handles a selection change in the category tree.
     * 
     * @param selection
     *          the new selection
     */
    private void handleCategorySelection(IStructuredSelection selection) {
      if (!selection.isEmpty()) {
        selectCategory((ICategoryTreeNode) selection.getFirstElement());
      } else {
        getItemList().setInput(null);
        clearDetailsArea();
      }
    }

    /**
     * Selects the specified category in the items list.
     * 
     * @param category
     *          the category to select
     */
    private void selectCategory(ICategoryTreeNode category) {
      getItemList().setInput(category);
      selectItems(category);
      setDetails(category);
    }

    /**
     * Select, in the table viewer, the currently enabled items.
     * 
     * @param categoryNode
     *          the currently selected category node
     */
    private void selectItems(ICategoryTreeNode categoryNode) {
      getItemList().setCheckedElements(categoryNode.getSelectedItems());
    }

    /**
     * Handles a selection change in the items list.
     * 
     * @param selection
     *          the new selection
     */
    private void handleItemSelection(IStructuredSelection selection) {
      if (!selection.isEmpty()) {
        setDetails((IItemNode) selection.getFirstElement());
      } else {
        clearDetailsArea();
      }
    }

    /**
     * Clears my details area, showing the "no selection" message.
     */
    void clearDetailsArea() {
      getDetailsArea().setText(NO_SELECTION);
    }

    /**
     * Sets the details area to show the currently selected <code>category</code>'s category details.
     * 
     * @param category
     *          the category in the category tree
     */
    private void setDetails(ICategoryTreeNode category) {
      String description = category == null ? null : category.getDescription();

      if (description == null) {
        description = NO_CATEGORY_DESCRIPTION;
      }

      CategoryPreferences actualCategory = category != null ? category.getCategory() : null;
      // If we are a mandatory category then we must provide some cue to this fact.
      if ((actualCategory != null) && actualCategory.isMandatory()) {
        getDetailsArea().setText(
            MessageFormat.format(PreferencesUIMessages.prefs_mandatory_category, description));

      } else {
        getDetailsArea().setText(description);
      }
    }

    /**
     * Sets the details area to show the currently selected <code>constraint</code>'s details.
     * 
     * @param constraint
     *          the constraint meta-data
     */
    private void setDetails(IItemNode constraint) {
      // lots of style info
      List<StyleRange> styles = new java.util.ArrayList<>(32);
      getDetailsArea().setText(constraint.getDescription());
      getDetailsArea().setStyleRanges(styles.toArray(new StyleRange[styles.size()]));
    }
  }

  /**
   * Creates the items selection composite on the given parent and filters the composite based on the provided filter.
   * 
   * @param parent
   *          parent for the newly created composite
   * @return the resulting constraint selection composite
   */
  public Composite createComposite(Composite parent) {
    SashForm parentFormComposite = new SashForm(parent, SWT.VERTICAL);

    parentFormComposite.setFont(parent.getFont());

    final Text searchText = new Text(parentFormComposite, SWT.BORDER | SWT.SEARCH);
    searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

    GUIUtil.addPrompt(searchText, PreferencesUIMessages.CommandSelectionContainer_Filtering);

    SashForm topPart = new SashForm(parentFormComposite, SWT.HORIZONTAL);
    createCategoryTree(topPart);

    createItemList(topPart);

    createDetailsArea(parentFormComposite);

    searchText.addFocusListener(new FocusListener() {

      @Override
      public void focusLost(FocusEvent e) {
        preferenceFilter.setSearchValue(searchText.getText());
        itemList.refresh();
        categoryTree.refresh();
      }

      @Override
      public void focusGained(FocusEvent e) {
        // Nothing here
      }
    });

    searchText.addKeyListener(new KeyListener() {

      @Override
      public void keyReleased(KeyEvent e) {
        preferenceFilter.setSearchValue(searchText.getText());
        itemList.refresh();
        categoryTree.refresh();
      }

      @Override
      public void keyPressed(KeyEvent e) {
        preferenceFilter.setSearchValue(searchText.getText());
        itemList.refresh();
        categoryTree.refresh();
      }
    });

    preferenceFilter = new PreferencesFilter();
    categoriesFilter = new CategoriesPreferencesFilter();
    itemList.addFilter(preferenceFilter);
    categoryTree.addFilter(categoriesFilter);

    parentFormComposite.setWeights(new int[] { 7, 80, 13 });

    return parentFormComposite;
  }

  /**
   * Helper method to create the category-tree part of the GUI. The result is a form containing the checkbox tree and a
   * prompt label.
   * 
   * @param parent
   *          the parent composite in which to create the tree
   * @return the tree part of the GUI (itself a composite form)
   */
  private Control createCategoryTree(Composite parent) {
    Composite form = new Composite(parent, SWT.NONE);
    FormLayout layout = new FormLayout();
    form.setLayout(layout);

    Label prompt = new Label(form, SWT.NONE);
    prompt.setText(CATEGORIES_PROMPT);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    prompt.setLayoutData(data);

    categoryTree = new CheckboxTreeViewer(form);
    data = new FormData();
    data.top = new FormAttachment(prompt, 4);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    data.bottom = new FormAttachment(100, 0);
    categoryTree.getControl().setLayoutData(data);

    rootcategory = CategoryTreeNode.createRoot(categoryTree, filter);
    categoryTree.setLabelProvider(new LabelProvider() {
      private final Image lockImage = Activator.getImageDescriptor(LOCK_ICON).createImage(true);

      @Override
      public void dispose() {
        lockImage.dispose();
        super.dispose();
      }

      @Override
      public Image getImage(Object element) {
        ICategoryTreeNode node = (ICategoryTreeNode) element;

        if (node.getCategory() != null && node.getCategory().isMandatory()) {
            return lockImage;
        }

        return null;
      }
    });
    categoryTree.setContentProvider(new CategoryTreeContents());
    categoryTree.setInput(rootcategory);
    markEnabledCategories(rootcategory);

    categoryTree.addCheckStateListener(getMediator());
    categoryTree.addSelectionChangedListener(getMediator());

    return categoryTree.getTree();
  }

  /**
   * Helper method to create the constraint-list part of the GUI. The result is a form containing the items list and a
   * prompt label.
   * 
   * @param parent
   *          the parent composite in which to create the list
   * @return the list part of the GUI (itself a composite form)
   */
  private Control createItemList(Composite parent) {
    Composite form = new Composite(parent, SWT.NONE);
    FormLayout layout = new FormLayout();
    form.setLayout(layout);

    Label prompt = new Label(form, SWT.NONE);
    prompt.setText(CONSTRAINTS_PROMPT);
    FormData data = new FormData();
    data.top = new FormAttachment(0, 0);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    prompt.setLayoutData(data);

    itemList = CheckboxTableViewer.newCheckList(form, SWT.CHECK | SWT.BORDER);
    data = new FormData();
    data.top = new FormAttachment(prompt, 4);
    data.left = new FormAttachment(0, 0);
    data.right = new FormAttachment(100, 0);
    data.bottom = new FormAttachment(100, 0);
    itemList.getControl().setLayoutData(data);

    itemList.setContentProvider(new ItemListContents());

    itemList.setLabelProvider(new LabelProvider() {

      // redefines the inherited method
      @Override
      public String getText(Object element) {
        return ((IItemNode) element).getName();
      }
    }

    );

    itemList.setSorter(new ViewerSorter());

    itemList.addCheckStateListener(getMediator());
    itemList.addSelectionChangedListener(getMediator());

    return itemList.getControl();
  }

  /**
   * Helper method to create the details are of the GUI.
   * 
   * @param parent
   *          the parent composite in which to create the details area
   * @return the details text area
   */
  private Control createDetailsArea(Composite parent) {
    detailsArea = new StyledText(parent, SWT.READ_ONLY | SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

    getMediator().clearDetailsArea();

    return detailsArea;
  }

  /**
   * Obtains my category tree.
   * 
   * @return the tree
   */
  private CheckboxTreeViewer getCategoryTree() {
    return categoryTree;
  }

  /**
   * Obtains my item list.
   * 
   * @return the list
   */
  private CheckboxTableViewer getItemList() {
    return itemList;
  }

  /**
   * Obtains my details area.
   * 
   * @return the details text area
   */
  private StyledText getDetailsArea() {
    return detailsArea;
  }

  /**
   * Obtains my mediator.
   * 
   * @return my mediator
   */
  private Mediator getMediator() {
    if (mediator == null) {
      mediator = new Mediator();
    }

    return mediator;
  }

  /**
   * Saves the constraint enablement changes made in the composite to the validation preferences
   */
  public boolean performOk() {
    rootcategory.applyToPreferences();
    ConfigurabilityPreferences.save();

    return true;
  }

  /**
   * Restores the defaults for the items listed in the composite
   */
  public void performDefaults() {
    rootcategory.restoreDefaults();

    // update the checked state of the current contents of the Items
    // list (if any)
    CheckboxTableViewer viewer = getItemList();
    Object input = viewer.getInput();

    if (input != null) {
      Object[] elements = ((IStructuredContentProvider) getItemList().getContentProvider()).getElements(input);

      if (elements != null) {
        int length = elements.length;

        for (int i = 0; i < length; i++) {
          IItemNode node = (IItemNode) elements[i];
          viewer.setChecked(node, node.isChecked());
        }
      }
    }
  }

  /**
   * Obtains the currently selected category, if any.
   * 
   * @return the current category
   */
  private CategoryPreferences getCurrentCategorySelection() {
    IStructuredSelection selection = (IStructuredSelection) getCategoryTree().getSelection();

    if (selection.isEmpty()) {
      return null;
    } else {
      return ((ICategoryTreeNode) selection.getFirstElement()).getCategory();
    }
  }

  /**
   * Helper method to set the currently enabled categories in the tree. Also sets gray states as appropriate.
   * 
   * @param root
   *          the root of the tree model
   */
  private void markEnabledCategories(ICategoryTreeNode root) {
    markEnabledCategories(root.getChildren());
  }

  private void markEnabledCategories(ICategoryTreeNode[] categories) {
    for (ICategoryTreeNode next : categories) {
      getCategoryTree().setChecked(next, next.isChecked());
      getCategoryTree().setGrayed(next, next.isGrayed());

      markEnabledCategories(next.getChildren());
    }
  }

  /*
   * (non-Javadoc) Extends the inherited method.
   */
  public void dispose() {
    // clean up the cached item nodes
    ItemNode.flushCache();
  }
}
