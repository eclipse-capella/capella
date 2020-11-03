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
package org.polarsys.capella.core.commands.preferences.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.PreferencesUIMessages;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.IItemFilter;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;

/**
 * Concrete implementation of a node in the category selection tree.
 * 
 */
public class CategoryTreeNode extends AbstractCategoryTreeNode {
	private boolean grayed;
	private boolean checked;
	
	/**
	 * For internal nodes that have associated items, the delegate is a
	 * special child node that allows the user to enable or disable those
	 * items.  The items are "delegated to" this node.
	 */
	private ICategoryTreeNode delegate;
	
	private List<IItemNode> items;
	
	/**
	 * Implementation of the root node of the category selection tree.
	 * 
	 */
	private static class RootNode extends AbstractCategoryTreeNode {
		/**
		 * Initializes me with just the tree that owns me.
		 * 
		 * @param tree the tree that owns me
         * @param filter a filter that defines which items I am interested in
		 */
		RootNode(CheckboxTreeViewer tree, IItemFilter filter) {
			super(tree, null, null, filter);
		}
		
		// implements the inherited method to wrap the top-level categories
		@Override
        protected List<ICategoryTreeNode> createChildren() {
		    CategoryPreferencesManager instance = CategoryPreferencesManager.getInstance();
		    instance.initDefaultCategory();
		
			Collection<CategoryPreferences> topLevel = instance.getFirstCategoriesLevel();
			List<ICategoryTreeNode> result = new java.util.ArrayList<ICategoryTreeNode>(topLevel.size());
			
			for (CategoryPreferences next : topLevel) {
				if (!isRecursivelyEmpty(next)) {
					CategoryTreeNode categoryTreeNode = new CategoryTreeNode(getTree(), next, this, getFilter());
					result.add(categoryTreeNode);
				}
			}
			
			return result;
		}
		
		// implements the inherited method
		public boolean isChecked() {
			return false;
		}

		// implements the inherited method
		public boolean isGrayed() {
			return false;
		}

		// implements the inherited method
		public void checkStateChanged(CheckStateChangedEvent event) {
			// I cannot be selected by the user, so there is never a transition
		}

		// implements the inherited method
		public void updateCheckState(ICategoryTreeNode child) {
			// I am never visible and don't represent a category, anyway
		}

		// implements the inherited method
		public void updateCheckState(IItemNode constraint) {
			// I am never visible and don't represent a category, anyway
		}
		
		// redefines the inherited method
		@Override
        public String getDescription() {
			return ""; //$NON-NLS-1$
		}
		
		// redefines the inherited method
		public List<IItemNode> getItems() {
			return Collections.emptyList();
		}
	}
	
	/**
	 * A special leaf node that acts as the delegate of an internal node that
	 * has items.
	 * 
	 * 
	 * @see CategoryTreeNode#delegate
	 */
	private static class DelegateNode extends CategoryTreeNode {
		/**
		 * Initializes me.
		 * 
		 * @param tree the tree that owns me
		 * @param category the category that I represent
		 * @param parent my parent, which delegates to me
         * @param filter a filter that defines which items I am interested in
		 */
		DelegateNode(
			CheckboxTreeViewer tree,
			CategoryPreferences category,
			ICategoryTreeNode parent,
			IItemFilter filter) {
			
			super(tree, category, parent, filter);
		}
		
		// redefines the inherited method
		@Override
        protected List<ICategoryTreeNode> createChildren() {
			// delegates are leaf nodes, by definition
			return Collections.emptyList();
		}
		
		// extends the inherited method
		@Override
        public String toString() {
			String result = super.toString();
			
			StringBuffer buf = new StringBuffer(result.length() + 2);
			
			buf.append(NLS.bind(PreferencesUIMessages.prefs_delegate_name,
				new Object[] {result}));
			
			result = buf.toString(); 
			
			return result;
		}
	}
	
	/**
	 * Initializes me.
	 * 
	 * @param tree the tree that owns me
	 * @param category the category that I represent
	 * @param parent my parent in the tree
     * @param filter a filter that defines which items I am interested in
	 */
	private CategoryTreeNode(
			CheckboxTreeViewer tree,
			CategoryPreferences category,
			ICategoryTreeNode parent,
			IItemFilter filter) {
		
		super(tree, category, parent, filter);
	}
	
	/**
	 * Creates a root node for the specified <code>tree</code> viewer.
	 * This root node will automatically discover the complete tree structure,
	 * as needed.
	 * 
	 * @param tree the tree viewer for which to create a root node
     * @param filter a filter that defines which items I am interested in	 
     * @return a suitable root node
	 */
	public static ICategoryTreeNode createRoot(CheckboxTreeViewer tree, final IItemFilter filter) {
	    // only show in the UI those items that are registered, because
	    // otherwise the provider wanted to hide them and we couldn't manage
	    // their preferences
	    IItemFilter registeredOnly = new IItemFilter() {
            public boolean accept(IItemDescriptor constraint, Object target) {
                if (PreferencesItemsRegistry.getInstance().getDescriptor(
                    constraint.getId()) == constraint) {
                    
                    return filter.accept(constraint, target);
                }
                
                return true;
            }};
            
		ICategoryTreeNode result = new RootNode(tree, registeredOnly);
		
		result.revertFromPreferences();
		
		return result;
	}
	
	// implements the interface method
	public boolean isChecked() {
		return checked;
	}
	
	// implements the interface method
	public boolean isGrayed() {
		return grayed;
	}
	
	// implements the inherited method
	@Override
    protected List<ICategoryTreeNode> createChildren() {
		final CategoryPreferences category = getCategory();
		Collection<CategoryPreferences> categoryChildren = category.getChildren();
		
		List<ICategoryTreeNode> result;
		
		if (!categoryChildren.isEmpty() && !getItems(category, getFilter()).isEmpty()) {
			// add one for the delegate child that contains my own items
			result = new java.util.ArrayList<ICategoryTreeNode>(categoryChildren.size() + 1);
			
			// add my delegate
			delegate = new DelegateNode(getTree(), category, this, getFilter());
			result.add(delegate);
		} else {
			result = new java.util.ArrayList<ICategoryTreeNode>(categoryChildren.size());
		}
		
		for (CategoryPreferences next : categoryChildren) {
			if (!isRecursivelyEmpty(next)) {
				result.add(new CategoryTreeNode(
						getTree(),
						next,
						this,
						getFilter()));
			}
		}
		
		return result;
	}
	
	// implements the interface method
	public void checkStateChanged(CheckStateChangedEvent event) {
		boolean newState = event.getChecked();
		
		if (isGrayed()) {
			// always transition to fully checked from the grayed state
			internalSetChecked(true);
		} else {
			// transition from unchecked to fully checked or vice-versa
			internalSetChecked(newState);
		}
	}
	
	/**
	 * Sets my new check state and propagates it down the tree.  This either
	 * completely checks or completely unchecks (according to the
	 * <code>newState</code>, and minding mandatory categories) the entire
	 * subtree rooted at me.  My state propagates up the tree, also, to possibly
	 * change the gray-state of my ancestors.
	 *  
	 * @param newState my new checked state
	 */
	protected void internalSetChecked(boolean newState) {
		if (!hasDelegate() && getCategory().isMandatory()) {
			checked = true;
		} else {
			checked = newState;
		}
		
		grayed = false;
		
		propagateToItems(newState);
		propagateDown(newState);
		
		updateTree();  // propagates my state up the tree, too
	}
	
	/**
	 * Propagates my new selection state to my items.  If any of my
	 * items cannot be made to reflect my new checked state, then I will
	 * be grayed.
	 * 
	 * @param newState the new checked state to propagate to my items
	 *     (those that will accept it)
	 */
	private void propagateToItems(boolean newChecked) {
		if (!hasDelegate()) {
			for (IItemNode next : getItems()) {
				// my items update my check state as necessary
				next.setChecked(newChecked);
			}
		}
	}
	
	/**
	 * Updates the GUI to reflect my current checked state, and propagates my
	 * state up the tree to my ancestors.
	 */
	private void updateTree() {
		CheckboxTreeViewer tree = getTree();
		
		if (tree.getChecked(this) != isChecked()) {
			tree.setChecked(this, isChecked());
		}
		
		if (tree.getGrayed(this) != isGrayed()) {
			tree.setGrayed(this, isGrayed());
		}
		
		updateParent();
	}
	
	/**
	 * Updates a parent, if I have one, to reflect my new state.
	 */
	private void updateParent() {
		if (getParent() != null) {
			getParent().updateCheckState(this);
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public void updateCheckState(ICategoryTreeNode child) {
		if (child.isGrayed()) {
			// easy case
			grayed = true;
			checked = true;
			
			updateTree();
		} else {
			boolean newValue = child.isChecked();
			
			ICategoryTreeNode[] children = getChildren();
			
			for (int i = 0; i < children.length; i++) {
				if (children[i].isGrayed()
						|| (children[i].isChecked() != newValue)) {
					grayed = true;
					checked = true;
					
					updateTree();
					
					return;
				}
			}
			
			// children have the same check state, and none is gray, so I'm
			// not gray and have the same check-state as they
			
			grayed = false;
			checked = newValue;
			
			updateTree();
		}
	}
	
	// implements the interface method
	public void updateCheckState(IItemNode constraint) {
		boolean newValue = constraint.isChecked();
		boolean newChecked = newValue;
		boolean newGrayed = false;
		
		for (IItemNode next : getItems()) {
			if (next != constraint) {
				if (next.isChecked() != newValue) {
					newGrayed = true;
					newChecked = true;
					
					// can quit as soon as I have found that I am gray-checked
					break;
				}
			}
		}
		
		checked = newChecked;
		grayed = newGrayed;
		
		updateTree();
	}
	
	/**
	 * Recursively propagates a new <code>checkedState</code> down my sub-tree.
	 * Note that if <code>checkedState</code> is <code>false</code>, mandatory
	 * categories will nonetheless remain checked.
	 * 
	 * @param checkedState the new state
	 */
	private void propagateDown(boolean checkedState) {
		ICategoryTreeNode[] children = getChildren();
		
		for (int i = 0; i < children.length; i++) {
			((CategoryTreeNode)children[i]).internalSetChecked(checkedState);
		}
	}
	
	// extends the inherited method
	@Override
    public void applyToPreferences() {
		if (!hasDelegate()) {
			for (IItemNode next : getItems()) {
				next.applyToPreferences();
			}
		}
		
		super.applyToPreferences();
	}
	
	// extends the inherited method
	@Override
    public void revertFromPreferences() {
		if (!hasDelegate()) {
			IItemNode node = null;
			
			for (Iterator<IItemNode> iter = getItems().iterator();
			        iter.hasNext();) {
			    
				node = iter.next();
				
				node.revertFromPreferences();
			}
			
			if (node != null) {
				// force a recomputation of my check state
				updateCheckState(node);
			}
		}
		
		super.revertFromPreferences();
	}
	
	// extends the inherited method
	@Override
    public void restoreDefaults() {
		if (!hasDelegate()) {
			IItemNode node = null;
			
			for (Iterator<IItemNode> iter = getItems().iterator();
			        iter.hasNext();) {
			    
				node = iter.next();
				
				node.restoreDefaults();
			}
			
			if (node != null) {
				// force a recomputation of my check state
				updateCheckState(node);
			}
		}
		
		super.restoreDefaults();
	}
	
	/**
	 * Extends the superclass method to return <code>null</code> if I have
	 * a child node to which I delegate my own category's items.
	 * 
	 * @return <code>null</code> if I have a delegate node; the superclass
	 *     result, otherwise
	 */
	@Override
    public CategoryPreferences getCategory() {
		if (hasDelegate()) {
			return null;
		} else {
			return super.getCategory();
		}
	}
	
	// implements the inherited method
	public List<IItemNode> getItems() {
		if (hasDelegate()) {
			items = Collections.emptyList();
		} else if (items == null) {
		    Collection<IItemDescriptor> descriptors = getItems(
		        getCategory(), getFilter());
			items = new java.util.ArrayList<IItemNode>(
		            descriptors.size());
		    
			for (IItemDescriptor next : descriptors) {
				IItemNode node = ItemNode.getInstance(next);
				
				node.addCategory(this);
				items.add(node);
			}
		}
		
		return items;
	}
	
	// implements the inherited method
	@Override
    public IItemNode[] getSelectedItems() {
		List<IItemNode> result = new java.util.ArrayList<IItemNode>(
		        getItems().size());
		
		for (IItemNode next : getItems()) {
			if (next.isChecked()) {
				result.add(next);
			}
		}
		
		return result.toArray(
			new IItemNode[result.size()]);
	}
	
	/**
	 * Queries whether I have a delegate node that actually makes my
	 * category's items available as a leaf node.
	 * 
	 * @return whether I have a delegate check-state node
	 */
	private boolean hasDelegate() {
		return delegate != null;
	}
	
	/**
	 * Overrides the superclass behaviour to ensure that when I don't have a
	 * category (because my delegate has it), I return my delegate's category's
	 * name.
	 */
	@Override
    public String toString() {
		if ((getCategory() == null) && hasDelegate()) {
			return delegate.getCategory().getName();
		} else {
			return super.toString();
		}
	}
}
