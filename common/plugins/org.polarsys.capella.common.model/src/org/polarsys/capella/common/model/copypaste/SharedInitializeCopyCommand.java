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
package org.polarsys.capella.common.model.copypaste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 */
public class SharedInitializeCopyCommand extends InitializeCopyCommand {

  private static final String REFERENCE_NAME_ATTRIBUTE = "referenceName";

/**
   * Qualifies the source of a link pending after a copy / paste.
   */
  public class PendingQualification {
    private EObject _src;
    private EReference _reference;

    /**
     * 
     */
    public PendingQualification(EObject src_p, EReference reference_p) {
      _src = src_p;
      _reference = reference_p;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj_p) {
      if (!(obj_p instanceof PendingQualification))
        return false;
      PendingQualification other = (PendingQualification) obj_p;
      return _src.equals(other._src) && _reference.equals(other._reference);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return _src.hashCode() + _reference.hashCode();
    }

  }

private static final String PLUGIN_ID = "org.polarsys.capella.common.model.DroppedReferencesOnCopy";

  private static Map<PendingQualification, Object> _pendingReferences = new HashMap<PendingQualification, Object>();
  private static List<String> _droppedReferences = null;

  /**
   * @param domain_p
   * @param owner_p
   * @param copyHelper_p
   */
  public SharedInitializeCopyCommand(EditingDomain domain_p, EObject owner_p, Helper copyHelper_p) {
    super(domain_p, owner_p, copyHelper_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<? extends EAttribute> getAttributesToCopy() {
    // Get all default attributes to copy.
    HashSet<? extends EAttribute> attributesToCopy = new LinkedHashSet<EAttribute>(super.getAttributesToCopy());
    attributesToCopy.remove(owner.eClass().getEIDAttribute());
    return attributesToCopy;
  }

  /**
   * This method will iterate over the references of the owner object and sets them. accordingly in the copy.
   */
  @Override
  protected void copyReferences() {
    for (EReference reference : getReferencesToCopy()) {
      if (isDroppedReference(reference)|| !reference.isChangeable() || reference.isDerived() || !isReferenceSet(reference)/* || !owner.eIsSet(reference) */) {
        continue;
      }

      EReference reverseReference = reference.getEOpposite();

      Object value = owner.eGet(reference);
      if (value == null) {
        // It must be an unsettable feature to be null and considered set,
        // or a pending reference, that i need to find the corresponding target in pending map.
        value = _pendingReferences.get(new PendingQualification(owner, reference));
        if (value == null) {
          copy.eSet(reference, null);
        } else {
          copy.eSet(reference, value);
        }
        continue;
      }

      boolean copiedTargetRequired = reverseReference != null || reference.isContainment();
      if (reference.isMany()) {
        @SuppressWarnings("unchecked")
        List<EObject> valueList = (List<EObject>) value;
        @SuppressWarnings("unchecked")
        EList<EObject> copyList = (EList<EObject>) copy.eGet(reference);
        if (valueList.isEmpty()) {
          // It must be an unsettable feature to be empty and considered set.
          //
          copyList.clear();
        } else {
          int index = 0;
          for (EObject item : valueList) {
            EObject target = copyHelper.getCopyTarget(item, copiedTargetRequired);
            if (target == null)
              break; // if one is null, they'll all be null
            if (reverseReference != null) {
              int position = copyList.indexOf(target);
              if (position == -1) {
                copyList.add(index, target);
              } else {
                copyList.move(index, target);
              }
            } else {
              copyList.add(target);
            }
            ++index;
          }
        }
      } else {
        EObject target = copyHelper.getCopyTarget((EObject) value, copiedTargetRequired);
        if (target != null) {
          copy.eSet(reference, target);
        } else {
          // dropped object, pending reference to register
        		_pendingReferences.put(new PendingQualification(copy, reference), value);
        }
      }
    }
  }

  private boolean isDroppedReference(EReference reference) {
	if (_droppedReferences  == null){
		loadDroppedReferences ();
	}
	return _droppedReferences.contains (reference.getName());
}

private void loadDroppedReferences() {
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    IConfigurationElement[] elementsForPlugin = extensionRegistry.getConfigurationElementsFor(PLUGIN_ID);
	_droppedReferences = new ArrayList<String>(elementsForPlugin.length);
    for (IConfigurationElement configurationElement : elementsForPlugin) {
    	String refName = configurationElement.getAttribute(REFERENCE_NAME_ATTRIBUTE);
    	_droppedReferences.add(refName);
    }
	
}

/**
   * {@inheritDoc}
   */
  @Override
  public void doExecute() {
    super.doExecute();
    SharedCopyPasteElements.getInstance().put(copy, owner);
  }

  /**
   * Check if the reference is value on the owner, either by direct reference or table pending.
   * @return
   */
  private boolean isReferenceSet(EReference reference_p) {
    PendingQualification pq = new PendingQualification(owner, reference_p);
    return owner.eIsSet(reference_p) || _pendingReferences.containsKey(pq);
  }

}
