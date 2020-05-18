/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import java.text.Collator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;


/**
 * <p>
 * A constraint category, defining a hierarchical organization of
 * items.  Categories can be individually and separately (no dependency
 * on the hierarchy) enabled or disabled by the user.  Enablement indicates
 * whether the items in the category should be applied to the user's
 * models.
 * </p>
 * <p>
 * Categories are naturally sorted by {@link #getName name} for display
 * purposes.
 * </p>
 * <p>
 * This class is intended to be used by clients of the validation framework.
 * </p>
 * 
 */
public class CategoryPreferences implements Comparable<CategoryPreferences> {
	static final String SLASH = "/"; //$NON-NLS-1$
	
	/**
	 * The global namespace is the parent of all top-level categories.
	 * This ensures that I never have to worry about NULL parents (the global
	 * namespace is the only namespace that has no parent).
	 */
	public static final CategoryPreferences GLOBAL_NAMESPACE = new CategoryPreferences("", null); //$NON-NLS-1$
	
	/**
	 * The "default" category is the one that contains all items that are
	 * not otherwise assigned to a known category.
	 */
	static final CategoryPreferences DEFAULT_CATEGORY = new CategoryPreferences("", GLOBAL_NAMESPACE); //$NON-NLS-1$
	
	private static Collator collator;
	
	private final String id;
	
	private String path;
	
	private String name;
	private String qualifiedName;
	private String description;
	
	private boolean mandatory;
	
	private final CategoryPreferences parent;
	private final Map<String, CategoryPreferences> childrens = 	new HashMap<String, CategoryPreferences>();
	
	private  Set<IItemDescriptor> items = 	new HashSet<IItemDescriptor>();
	
	public void setItems(Set<IItemDescriptor> items) {
		this.items = items;
	}

	/**
	 * Initializes me with my ID and parent category.
	 * 
	 * @param id my ID (must not be <code>null</code>)
	 * @param parent my parent category, or <code>null</code> if none
	 *    (which should only be the case for the {@link #GLOBAL_NAMESPACE}
	 */
	public CategoryPreferences(String id, CategoryPreferences parent) {
		assert id != null;
		this.id = id;
		this.parent = parent;
		if (parent != null) {
			parent.addChild(this);
		}
		
		
		
	}

	/**
	 * Obtains my ID, which is unique within my parent's ID (or just unique
	 * within the global namespace if I have no parent).
	 * 
	 * @return my ID
	 */
	public final String getId() {
		return id;
	}
	
	/**
	 * Obtains my path, which is my fully-qualified slash-separated ID that
	 * is unique within the global namespace.
	 * 
	 * @return my unique path
	 */
	public final String getPath() {
		if (path == null) {
			if (getParent() == null) {
				path = getId();
			} else {
				path = getParent().getPath() + SLASH + getId();
			}
		}
		
		return path;
	}
	
	/**
	 * Obtains my user-presentable name.
	 * 
	 * @return my name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Obtains my qualified name, which includes my ancestors' names separated
	 * by slashes.
	 * 
	 * @return my qualified name
	 */
	public final String getQualifiedName() {
		if (qualifiedName == null) {
			if (getParent() == null) {
				qualifiedName = getName();
			} else {
				qualifiedName = getParent().getQualifiedName() + SLASH
					+ getName();
			}
		}
		
		return qualifiedName;
	}
	
	/**
	 * Obtains a string which (briefly) describes my purpose to the user.
	 * 
	 * @return my description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Obtains my items.
	 * 
	 * @return the {@link IItemDescriptor}s that are members of me as an
	 *     unmodifiable set
	 */
	public Set<IItemDescriptor> getItems() {
		
		
		
		return Collections.unmodifiableSet(items);
	}
	
	/**
	 * Adds a constraint to me.
	 * 
	 * @param constraint my constraint
	 */
	public void addItem(IItemDescriptor constraint) {
		if (!items.contains(constraint)) {
			items.add(constraint);
			constraint.addCategory(this);
		}
	}
	
	/**
	 * Removes a item from me.
	 * 
	 * @param item a constraint
	 */
	public void removeItem(IItemDescriptor item) {
		if (items.contains(item)) {
			items.remove(item);
			item.removeCategory(this);
		}
	}
	
	/**
	 * Obtains my parent category, or <code>null</code> if I am a top-level
	 * category.
	 * 
	 * @return my parent, if I have one
	 */
	public CategoryPreferences getParent() {
		CategoryPreferences result = getParentInternal();
		
		if (result == GLOBAL_NAMESPACE) {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * Gets my real parent (which may be the {@link #GLOBAL_NAMESPACE} if I am
	 * a top-level category.
	 * 
	 * @return my real parent
	 */
	private CategoryPreferences getParentInternal() {
		return parent;
	}
	
	/**
	 * Obtains my children.
	 * 
	 * @return an unmodifiable set of the {@link CategoryPreferences}s that are
	 *     my children, sorted by {@link #getName name}.  May be an empty set
	 */
	public SortedSet<CategoryPreferences> getChildren() {
		return Collections.unmodifiableSortedSet(new TreeSet<CategoryPreferences>(childrens.values()));
	}
	
	/**
	 * Obtains the child category of mine that has the specified
	 * <code>childId</code>.
	 * 
	 * @param childId the ID to find
	 * @return the matching category, or <code>null</code> if not found
	 */
	public CategoryPreferences getChild(String childId) {
		return childrens.get(childId);
	}
	
	/**
	 * Obtains the descendent category of mine that has the specified
	 * <code>descendentPath</code> relative to my path.
	 * 
	 * @param descendentPath the relative path to find
	 * @return the matching category, or <code>null</code> if not found
	 * 
	 * @see #getPath
	 */
	public CategoryPreferences getDescendent(String descendentPath) {
		return getDescendent(descendentPath, false);
	}
	
	
	
	public boolean hasChildren(){
		return !this.childrens.values().isEmpty();
	}
	/**
	 * Obtains my descendent category having the specified <code>descendentPath</code>
	 * relative to me.  It will be <code>create</code>d, if desired by the
	 * caller, if it does not already exist.
	 * 
	 * @param descendentPath the relative path of my descendent
	 * @param create whether to create it if it does not exist
	 * @return the descendent, or <code>null</code> if it was not found and
	 *     <code>create</code> is <code>false</code>
	 */
	CategoryPreferences getDescendent(String descendentPath, boolean create) {
		// recursively strip leading slashes until we have an ID at the start
		if (descendentPath.startsWith(SLASH)) {
			return getDescendent(descendentPath.substring(1), create);
		}
		
		int slash = descendentPath.indexOf(SLASH);  // known BMP character
		if (slash < 0) {
			CategoryPreferences result = getChild(descendentPath);
			
			if ((result == null && create)) {
				result = new CategoryPreferences(descendentPath, this);
			}
					
			return result;
		} else {
			final String childId = descendentPath.substring(0, slash);
			CategoryPreferences child = getChild(childId);
			
			if (child == null) {
				if (!create) {
					return null;
				} else {
					child = new CategoryPreferences(childId, this);
				}
			}
			
			return child.getDescendent(descendentPath.substring(slash + 1), create);
		}
	}
	
	/**
	 * Sets my localized name.
	 * 
	 * @param name my name (may not be <code>null</code>)
	 */
	public final void setName(String name) {
		assert name != null;
		this.name = name;
		this.qualifiedName = null; // force lazy recalculation
	}
	
	/**
	 * Sets my localized description.
	 * 
	 * @param description my description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Queries whether I am a mandatory category, which the user may not
	 * deselect.
	 * 
	 * @return whether I am mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}
	
	/**
	 * Sets whether I am mandatory.  If I am mandatory, then I change my state
	 * to enabled if it wasn't already.
	 * 
	 * @param b whether I am mandatory
	 */
	public void setMandatory(boolean b) {
		mandatory = b;
	}
	
	/**
	 * Adds the specified <code>child</code> category to me.  Note that this
	 * must only be called from the constructor of <code>child</code>.
	 * 
	 * @param child my new child category
	 */
	private void addChild(CategoryPreferences child) {
		final String newId = child.getId();
		
		if (childrens.containsKey(newId)) {
			// remove any existing child having the incoming child's ID
			removeChild(newId);
		}
		
		childrens.put(newId, child);
	}
	
	/**
	 * Removes the child having the specified ID from me.
	 * 
	 * @param childId the ID of the child category to be removed
	 */
	void removeChild(String childId) {
		childrens.remove(childId);
	}
	
	// redefines the inherited method
	@Override
	public int hashCode() {
		return getPath().hashCode();
	}
	
	// redefines the inherited method
	@Override
	public boolean equals(Object other) {
		return (other instanceof CategoryPreferences)
			&& ((CategoryPreferences)other).getPath().equals(getPath());
	}
	
	static Collator getCollator() {
		if (collator == null) {
			collator = Collator.getInstance();
		}
		
		return collator;
	}
	
	// implements the interface method
	public int compareTo(CategoryPreferences other) {
		Collator aCollator = getCollator();
		
		int result = aCollator.compare(getName(), other.getName());
		
		if (result == 0) {
			// sort by path to ensure that equivalent sort implies equality
			//    and vice-versa
			result = aCollator.compare(getPath(), other.getPath());
		}
		
		return result;
	}
	
	// redefines the inherited method
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(32);
		result.append("Category[path="); //$NON-NLS-1$
		result.append(getPath());
		result.append(", name="); //$NON-NLS-1$
		result.append(getName());
		result.append(']');
		return result.toString();
	}

	/**
	 * Fills the specified <code>collection</code> with the categories that
	 * are mandatory in my sub-tree.
	 * 
	 * @param collection the collection of mandatory categories to which I append
	 */
	void getMandatoryCategories(Collection<? super CategoryPreferences> collection) {
		if (isMandatory()) {
			collection.add(this);
		}
		
		for (CategoryPreferences next : getChildren()) {
			next.getMandatoryCategories(collection);
		}
	}
}
