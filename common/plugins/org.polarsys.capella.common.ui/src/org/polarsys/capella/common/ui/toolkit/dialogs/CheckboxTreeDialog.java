/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.RegExpCheckboxTreeViewer;

/**
 * Dialog that displays a {@link CheckboxTreeViewer}.<br>
 * This tree viewer only handles <b>root nodes</b> with <b>leaf</b> nodes as children i.e depth == 1.
 */
public class CheckboxTreeDialog<T extends EObject, U extends EObject> extends AbstractViewerDialog {

  public static final String CHECKBOX_TREE_DIALOG = "org.polarsys.capella.common.ui.toolkit.dialogs.checkboxTree";
  
  /**
   * A proxy to a semantic element, that will be displayed in the checkbox tree viewer.<br>
   * Internal usage only.
   */
  class ElementNode extends TreeNode {
    /**
     * Children.
     */
    private Collection<ElementNode> _children;
    /**
     * Initial checked state.
     */
    private boolean _initialCheckedState;

    /**
     * Constructor.
     * @param value Real semantic element.
     */
    ElementNode(Object value) {
      super(value);
    }

    /**
     * Add a new child.
     * @param child
     */
    void addChild(ElementNode child) {
      // Create children structure, if needed.
      if (null == _children) {
        _children = new ArrayList<ElementNode>(1);
      }
      // Do not use a set instead, order might be of interest here.
      if (!_children.contains(child)) {
        _children.add(child);
        child.setParent(this);
      }
    }

    /**
     * @see org.eclipse.jface.viewers.TreeNode#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
      boolean result = false;
      if (object instanceof TreeNode) {
        TreeNode treeNode = (TreeNode) object;
        result = Util.equals(this.value, treeNode.getValue());
        result = (result) ? Util.equals(getParent(), treeNode.getParent()) : false;
      }
      return result;
    }

    /**
     * @see org.eclipse.jface.viewers.TreeNode#getChildren()
     */
    @Override
    public TreeNode[] getChildren() {
      return (null != _children) ? _children.toArray(new TreeNode[_children.size()]) : null;
    }

    /**
     * @see org.eclipse.jface.viewers.TreeNode#getParent()
     */
    @SuppressWarnings("unchecked")
    @Override
    public ElementNode getParent() {
      return (ElementNode) super.getParent();
    }

    /**
     * @see org.eclipse.jface.viewers.TreeNode#hasChildren()
     */
    @Override
    public boolean hasChildren() {
      return (null != _children);
    }

    /**
     * @see org.eclipse.jface.viewers.TreeNode#hashCode()
     */
    @Override
    public int hashCode() {
      // Based on Thinking In Java book : Overriding hashCode( ) chapter 11
      int result = 17;
      result = 37 * result + Util.hashCode(value);
      if (null != getParent()) {
        result = 37 * result + Util.hashCode(getParent().value);
      }
      return result;
    }

    /**
     * @return the initialCheckState
     */
    boolean isInitialCheckedState() {
      return _initialCheckedState;
    }

    /**
     * Remove an existing child.
     * @param child
     */
    void removeChild(ElementNode child) {
      if (null != _children) {
        _children.remove(child);
      }
    }

    /**
     * @param initialCheckState the initialCheckState to set
     */
    void setInitialCheckedState(boolean initialCheckState) {
      _initialCheckedState = initialCheckState;
    }

    /**
     * Set parent element node.
     * @param parent
     */
    void setParent(ElementNode parent) {
      ElementNode previousParent = getParent();
      // Remove child from previously known parent.
      if (null != previousParent) {
        previousParent.removeChild(this);
      }
      // Set new parent.
      super.setParent(parent);
      // Add this as child to new parent.
      if (null != parent) {
        parent.addChild(this);
      }
    }

  }

  /**
   * Specific label provider based on EMF edit capabilities.<br>
   * Internal usage only.
   */
  class LabelProvider extends MDEAdapterFactoryLabelProvider {
    LabelProvider() {
      super();
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnImage(java.lang.Object, int)
     */
    @Override
    public Image getColumnImage(Object object, int columnIndex) {
      return super.getColumnImage(((TreeNode) object).getValue(), columnIndex);
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnText(java.lang.Object, int)
     */
    @Override
    public String getColumnText(Object object, int columnIndex) {
      return super.getColumnText(((TreeNode) object).getValue(), columnIndex);
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object object) {
      return super.getImage(((TreeNode) object).getValue());
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
      return super.getText(((TreeNode) object).getValue());
    }
  }

  private RegExpCheckboxTreeViewer _checkboxTreeViewer;
  private Map<T, Collection<U>> _results;
  private List<ElementNode> _rootNodes;

  /**
   * Constructor.
   * @param parentShell
   * @param title
   * @param message
   */
  public CheckboxTreeDialog(Shell parentShell, String title, String message) {
    super(parentShell, title, message, Messages.CheckboxTreeDialog_Window_Title);
  }

  /**
   * Check initial selection.
   * @param viewer
   * @param rootNodes
   */
  @SuppressWarnings("unchecked")
  private void checkInitialSelection(CheckboxTreeViewer viewer, List<ElementNode> rootNodes) {
    ArrayList<TreeNode> checkedElements = new ArrayList<TreeNode>(0);
    // Loop over root nodes.
    for (ElementNode rootNode : rootNodes) {
      // Is child node checked for current root node ?
      for (TreeNode childNode : rootNode.getChildren()) {
        if (((ElementNode) childNode).isInitialCheckedState()) {
          checkedElements.add(childNode);
        }
      }
    }
    // Check all child nodes that have to be checked !
    viewer.setCheckedElements(checkedElements.toArray());
  }

  /**
   * Create the {@link CheckboxTreeViewer}.
   * @param dialogAreaComposite
   * @return a not <code>null</code> instance.
   */
  protected RegExpCheckboxTreeViewer createCheckboxTreeViewer(Composite dialogAreaComposite) {
    return new RegExpCheckboxTreeViewer(dialogAreaComposite) {
      @Override
      public String getContextMenuLocation() {
        return CHECKBOX_TREE_DIALOG;
      }
    };
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // Create the checkbox tree viewer with a regular expression.
    _checkboxTreeViewer = createCheckboxTreeViewer(parent);
    // Get the client viewer i.e the checkbox tree viewer itself.
    CheckboxTreeViewer viewer = _checkboxTreeViewer.getClientViewer();
    // Install a sorter which uses the default collator to sort strings.
    viewer.setSorter(new ViewerSorter());
    // Set its content provider.
    viewer.setContentProvider(new TreeNodeContentProvider());
    // Set its label provider.
    viewer.setLabelProvider(new LabelProvider());
    // Set its input.
    viewer.setInput(_rootNodes.toArray(new TreeNode[_rootNodes.size()]));
    // Check initial selection.
    checkInitialSelection(viewer, _rootNodes);
    // Customize this dialog appearance.
    constrainViewer(viewer, getViewerHeighthint());
  }

  /**
   * Get checked elements by the end-user.<br>
   * In the resulting map, the key is related to a node, and the value to a collection of checked children for this node.
   * @return a not <code>null</code> map.
   */
  @Override
  public Map<T, Collection<U>> getResult() {
    return _results;
  }

  /**
   * Get initial input.
   * @return a not <code>null</code> object.
   */
  protected List<ElementNode> getRootNodes(Map<T, Collection<U>> allElements, Map<T, Collection<U>> initialCheckedElements) {
    // Create a root node.
    ArrayList<ElementNode> nodes = new ArrayList<ElementNode>();
    Iterator<Entry<T, Collection<U>>> iterator = allElements.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<T, Collection<U>> entry = iterator.next();
      // Get semantic object used as key.
      EObject semanticKey = entry.getKey();
      // Is this semantic object contained in initial checked elements ?
      boolean isKeyChecked = initialCheckedElements.containsKey(semanticKey);
      Collection<U> checkedElementsForKey = null;
      if (isKeyChecked) {
        checkedElementsForKey = initialCheckedElements.get(semanticKey);
      }
      // Create its node.
      ElementNode node = new ElementNode(semanticKey);
      // Add element node according to entry value.
      for (EObject semanticChild : entry.getValue()) {
        ElementNode childNode = new ElementNode(semanticChild);
        node.addChild(childNode);
        if (isKeyChecked) {
          childNode.setInitialCheckedState(checkedElementsForKey.contains(semanticChild));
        }
      }
      nodes.add(node);
    }
    return nodes;
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#okPressed()
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void okPressed() {
    _results = new HashMap<T, Collection<U>>(0);
    Object[] checkedElements = _checkboxTreeViewer.getClientViewer().getCheckedElements();
    for (Object object : checkedElements) {
      ElementNode elementNode = (ElementNode) object;
      ElementNode parentNode = elementNode.getParent();
      if (null != parentNode) {
        // Leaf Node case.
        Collection<U> collection = _results.get(parentNode.getValue());
        if (null == collection) {
          collection = new ArrayList<U>(1);
          _results.put((T) parentNode.getValue(), collection);
        }
        collection.add((U) elementNode.getValue());
      }
    }
    super.okPressed();
  }

  /**
   * Set data input.
   * @param selectableElements all selectable (i.e checkable) elements.<br>
   *          <code>T</code> corresponds to a <b>root</b> node with its collection of <b>leaf</b> nodes i.e <code>Collection&lt;U&gt;</code>.
   * @param checkedElements checked elements at creation time.<br>
   *          Same explanations as previous parameter <code>selectableElements</code>.
   */
  public void setInput(Map<T, Collection<U>> selectableElements, Map<T, Collection<U>> checkedElements) {
    _rootNodes = getRootNodes(selectableElements, checkedElements);
  }
}
