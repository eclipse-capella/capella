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
package org.polarsys.capella.common.helpers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;

/**
 * This class contains convenient static methods for working with EMF objects.
 */
public class EcoreUtil2 {
  public static String defaultPattern = "{1} {0}"; //$NON-NLS-1$

  /**
   * Attach 'tgtElt_p' to the same container as 'srcElt_p', with the same containment feature.
   * @param srcElt_p
   * @param tgtElt_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void attachLikeSourceContainer(EObject srcElt_p, EObject tgtElt_p) {
    EObject container = srcElt_p.eContainer();
    EStructuralFeature eStructFeatureFound = srcElt_p.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        container.eSet(eStructFeatureFound, tgtElt_p);
      } else {
        ((Collection) container.eGet(eStructFeatureFound)).add(tgtElt_p);
      }
    }
  }

  /**
   * Attach 'tgtElt_p' to its container 'tgtContainer_p' with the same containment feature as the one between 'srcElt_p' and its own container.
   * @param srcElt_p
   * @param tgtElt_p
   * @param tgtContainer_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void attachLikeSourceContainmentFeature(EObject srcElt_p, EObject tgtElt_p, EObject tgtContainer_p) {
    EStructuralFeature eStructFeatureFound = srcElt_p.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        tgtContainer_p.eSet(eStructFeatureFound, tgtElt_p);
      } else {
        ((Collection) tgtContainer_p.eGet(eStructFeatureFound)).add(tgtElt_p);
      }
    }
  }

  /**
   * @param current_p
   * @param nameString_p
   * @param feature_p
   * @param attribute_p
   */
  private static boolean checkElementName(EObject current_p, String nameString_p, EStructuralFeature feature_p, EAttribute attribute_p) {
    Object name = current_p.eGet(attribute_p);
    if ((name != null) && name.equals(nameString_p)) {
      return false;
    }
    return true;
  }

  /**
   * @param list_p
   * @param nameString_p
   * @param feature_p
   * @param attribute_p
   */
  private static boolean checkListName(Collection<? extends EObject> list_p, String nameString_p, EStructuralFeature feature_p, EAttribute attribute_p) {
    Iterator<? extends EObject> elements = list_p.iterator();
    boolean isUnique = true;
    while (elements.hasNext() && isUnique) {
      EObject currentEObject = elements.next();
      isUnique = checkElementName(currentEObject, nameString_p, feature_p, attribute_p);
    }
    return isUnique;
  }

  /**
   * Get all contents for given elements.<br>
   * Returned collection contains given elements and their subtree elements.
   * @param elements_p
   * @return a not <code>null</code> collection.
   */
  public static Collection<?> getAllContents(Collection<?> elements_p) {
    Set<Object> allElements = new HashSet<Object>(elements_p);
    TreeIterator<EObject> eAllContents = org.eclipse.emf.ecore.util.EcoreUtil.getAllContents(elements_p, true);
    while (eAllContents.hasNext()) {
      allElements.add(eAllContents.next());
    }
    return allElements;
  }

  public static EObject getCommonAncestor(Collection<? extends EObject> list_p) {
    if (list_p.isEmpty()) {
      return null;
    }
    Iterator<? extends EObject> listIterator = list_p.iterator();
    EObject commonAncestor = listIterator.next();
    while (listIterator.hasNext()) {
      EObject anObject = listIterator.next();
      commonAncestor = getCommonAncestor(commonAncestor, anObject);
    }
    return commonAncestor;
  }

  /**
   * @param e1
   * @param e2
   * @return the common ancestor of e1 and e2
   */
  public static EObject getCommonAncestor(EObject e1, EObject e2) {
    EObject contextContainer = e1;
    if (org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(e1, e2)) {
      return e1;
    } else if (org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(e2, e1)) {
      return e2;
    } else {
      while (!org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(contextContainer, e2) && (contextContainer != null)) {
        contextContainer = contextContainer.eContainer();
      }
    }
    return contextContainer;
  }

  /**
   * This method returns the number of container to be parsed before to reach the specified class type
   * @param elt_p
   * @return
   */
  public static int getContainmentLevel(EObject elt_p, EClass cls_p) {
    int level = 0;

    if (elt_p != null) {
      if (cls_p.isSuperTypeOf(elt_p.eClass())) {
        level = 0;
      } else {
        EObject container = elt_p.eContainer();
        if (container != null) {
          level = 1 + getContainmentLevel(container, cls_p);
        }
      }
    }

    return level;
  }

  /**
   * Return the file where is persisted given EMF object.
   * @param resource_p
   * @return <code>null</code> if given object is not persisted.
   */
  public static IFile getFile(Resource resource_p) {
    IFile result = null;
    // Precondition.
    if (null == resource_p) {
      return result;
    }
    result = WorkspaceSynchronizer.getFile(resource_p);
    return result;
  }

  /**
   * Returns the URI from the path (with compatible encoding for EMF.getResource(URI) method)
   */
  public static URI getURI(IFile file_p) {
    return URI.createPlatformResourceURI(file_p.getFullPath().toOSString(), true);
  }

  /**
   * Return the project where is persisted given EMF object.
   * @param object_p
   * @return <code>null</code> if given object is not persisted.
   */
  public static IProject getProject(EObject object_p) {
    IProject result = null;
    // Preconditions.
    if ((null == object_p) || (object_p.eResource() == null)) {
      return result;
    }
    IFile res = WorkspaceSynchronizer.getFile(object_p.eResource());
    if (res != null) {
      result = res.getProject();
    }
    return result;
  }

  /**
   * Gets the first container with the specified class type of the specified element.
   * @param elt_p The element to check container.
   * @param cls_p The expected container class.
   * @return The corresponding container elsewhere <code>null</code>.
   */
  public static EObject getFirstContainer(EObject elt_p, EClass cls_p) {
    EObject container = null;

    if (elt_p != null) {
      container = elt_p.eContainer();
    }

    if (container == null) {
      return null;
    }

    if (cls_p.isSuperTypeOf(container.eClass())) {
      return container;
    }

    return getFirstContainer(container, cls_p);
  }

  /**
   * Gets the first container with the specified class type of the specified elements.
   * @param elt_p The element to check container.
   * @param cls_p The expected container classes list.
   * @return The corresponding container elsewhere <code>null</code>.
   */
  public static EObject getFirstContainer(EObject elt_p, List<EClass> cls_p) {
    EObject container = null;

    if (elt_p != null) {
      container = elt_p.eContainer();
    }

    if (container == null) {
      return null;
    }

    for (EClass cls : cls_p) {
      if (cls.isSuperTypeOf(container.eClass())) {
        return container;
      }
    }

    return getFirstContainer(container, cls_p);
  }

  /**
   * Collect all elements that reference (containment relationships are not taken into account) given one.<br>
   * In fact that collects Non Navigable Inverse References for given element.<br>
   * Derived features are ignored too.
   * @param referencedElement_p
   * @param crossReferencer_p cross referencer used to retrieve referencing elements.
   * @return a not <code>null</code> collection.
   */
  public static Collection<EObject> getReferencingElements(final EObject referencedElement_p, ECrossReferenceAdapter crossReferencer_p) {
    HashSet<EObject> referencingElements = new HashSet<EObject>(0);
    // Search inverses relations for selected model element.
    Collection<Setting> inverseReferences = crossReferencer_p.getNonNavigableInverseReferences(referencedElement_p, true);
    // Elements that reference selected model element.
    // Loop over inverse references to collect referencing elements.
    for (Setting setting : inverseReferences) {
      EStructuralFeature feature = setting.getEStructuralFeature();
      boolean shouldAdd = true;
      if (feature instanceof EReference) {
        // Filter out containment reference.
        EReference eReference = (EReference) feature;
        if (eReference.isContainment() || eReference.isDerived()) {
          shouldAdd = false;
        }
      }
      if (shouldAdd) {
        referencingElements.add(setting.getEObject());
      }
    }
    return referencingElements;
  }

  /**
   * Get the resource container i.e the first parent container serialized in its own resource.
   * @param eObject_p
   * @return should be not <code>null</code>.
   */
  public static EObject getResourceContainer(EObject eObject_p) {
    EObject result = eObject_p;
    if (null != eObject_p) {
      EObject parentContainer = eObject_p.eContainer();
      if (null == parentContainer) {
        result = eObject_p;
      } else {
        if (null != ((InternalEObject) parentContainer).eDirectResource()) {
          result = parentContainer;
        } else {
          result = getResourceContainer(parentContainer);
        }
      }
    }
    return result;
  }

  /**
   * Get resource URI.
   * @param resource_p
   * @return
   */
  public static URI getResourceURI(Resource resource_p) {
    ResourceSet resourceSet = resource_p.getResourceSet();
    URI uri = resource_p.getURI();
    if (null != resourceSet) {
      uri = resourceSet.getURIConverter().normalize(uri);
    }
    return uri;
  }

  /**
   * @param namedElement_p
   * @param attribute_p
   * @param prefix_p
   * @param space_p
   * @param recursive_p
   */
  @SuppressWarnings("unchecked")
  public static String getUniqueName(EObject namedElement_p, EAttribute attribute_p, String prefix_p, boolean space_p, boolean recursive_p) {
    EObject parent = namedElement_p.eContainer();
    StringBuilder name = new StringBuilder(prefix_p);
    if (space_p) {
      name.append(" "); //$NON-NLS-1$
    }

    String nameString = name.toString();
    if (parent == null) {
      return nameString;
    }

    EStructuralFeature feature = namedElement_p.eContainingFeature();
    if (recursive_p) {
      EStructuralFeature parentFeature = parent.eContainingFeature();
      while (feature == parentFeature) {
        parent = parent.eContainer();
        parentFeature = parent.eContainingFeature();
      }
    }

    if ((feature instanceof EReference) && feature.isMany()) {
      boolean isUnique = false;

      Collection<? extends EObject> list = new ArrayList((Collection<? extends EObject>) parent.eGet(feature));
      list.remove(namedElement_p);

      int i = list.size() + 1;
      name.append(i);
      while (!isUnique) {
        nameString = name.toString();
        isUnique = checkListName(list, nameString, feature, attribute_p);
        if (!isUnique) {
          if (nameString.endsWith(Integer.valueOf(i).toString())) {
            name.delete(name.length() - Integer.valueOf(i).toString().length(), name.length());
          }
          i = i + 1;
          name.append(i);
        }
      }
    }
    return nameString;
  }

  /**
   * Gets a unique name for the specified object.
   * @param object_p the object to set name.
   * @param container_p the object container.
   * @param feature_p the feature.
   * @param attribute_p The name attribute.
   * @param defaultString_p The default string.
   * @param pattern_p The name pattern.
   */
  @SuppressWarnings("unchecked")
  public static String getUniqueName(EObject object_p, EObject container_p, EStructuralFeature feature_p, EAttribute attribute_p, String defaultString_p) {
    String resultName = ICommonConstants.EMPTY_STRING;

    if ((feature_p == null) || feature_p.isMany()) {
      int counter = 0;

      List<EObject> siblings = new ArrayList<EObject>();
      if (feature_p != null) {
        siblings.addAll((Collection<EObject>) container_p.eGet(feature_p));
      } else {
        siblings.addAll(container_p.eContents());
      }
      siblings.remove(object_p);

      // retrieving the naming attribute feature.
      if ((siblings != null) && !siblings.isEmpty()) {
        List<String> existingNames = new ArrayList<String>();
        // list existing names.
        if (attribute_p != null) {
          for (Object sibling : siblings) {
            EObject eSibling = (EObject) sibling;
            
            //eGet doesn't raise an exception if 'java assert' option is not enabled
            //See https://polarsys.org/bugs/show_bug.cgi?id=389
            if(!eSibling.eClass().getEAllStructuralFeatures().contains(attribute_p)){
            	continue;
            }
            
            Object attributeValue = eSibling.eGet(attribute_p);
            if (attributeValue instanceof String) {
              String name = (String) attributeValue;
              if (!name.equals(ICommonConstants.EMPTY_STRING)) {
                existingNames.add(name);
              }
            }
          }
          counter = siblings.size();
          do {
            resultName = MessageFormat.format(defaultPattern, new Object[] { Integer.valueOf(++counter), defaultString_p });
          } while (existingNames.contains(resultName));
        }
      } else {
        resultName = MessageFormat.format(defaultPattern, new Object[] { Integer.valueOf(++counter), defaultString_p });
      }
    } else {
      resultName = defaultString_p;
    }
    return resultName;
  }

  /**
   * Is given object contained by an object which is an instance of specified container class.
   * @param eObject_p the element to check the container.
   * @param containerClass_p The expected container class.
   * @return <code>true</code> if the container class matches, <code>false</code> otherwise.
   */
  public static boolean isContainedBy(EObject eObject_p, final Class<?> containerClass_p) {
    // Precondition.
    if (null == containerClass_p) {
      return false;
    }
    boolean result = isContainnedBy(eObject_p, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject container = getObject();
        setResult(Boolean.valueOf(containerClass_p.isInstance(container)));
      }
    });
    return result;
  }

  /**
   * Checks if the specified element container class is same as specified class.
   * @param elt_p the element to check the container.
   * @param cls_p The expected container class.
   * @return <code>True</code> if the container class is good else <code>false</code>.
   */
  public static boolean isContainedBy(EObject elt_p, final EClass cls_p) {
    // Precondition.
    if (null == cls_p) {
      return false;
    }
    boolean result = isContainnedBy(elt_p, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject container = getObject();
        setResult(Boolean.valueOf(cls_p.isSuperTypeOf(container.eClass())));
      }
    });
    return result;
  }

  /**
   * Checks if the specified element is contained by the specified container.
   * @param elt_p the element to check.
   * @param container_p The container to check.
   * @return <code>True</code> if the container class is good else <code>false</code>.
   */
  public static boolean isContainedBy(EObject elt_p, final EObject container_p) {
    // Precondition.
    if (null == container_p) {
      return false;
    }
    boolean result = isContainnedBy(elt_p, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject container = getObject();
        setResult(Boolean.valueOf(container_p == container));
      }
    });
    return result;
  }

  /**
   * Is specified object contained by a container matching specified condition.
   * @param eObject_p
   * @param containmentCondition_p
   * @return
   */
  private static boolean isContainnedBy(EObject eObject_p, RunnableWithBooleanResult containmentCondition_p) {
    boolean result = false;
    // Preconditions:
    if ((null == eObject_p) || (null == containmentCondition_p)) {
      return result;
    }
    // Get object's container.
    EObject container = eObject_p.eContainer();
    if (null == container) {
      return result;
    }
    // Set the container.
    containmentCondition_p.setObject(container);
    // Execute the runnable.
    containmentCondition_p.run();
    if (containmentCondition_p.getResult().booleanValue()) {
      result = true;
    } else {
      result = isContainnedBy(container, containmentCondition_p);
    }
    return result;
  }

  /**
   * Allows to know if an <code>EClass</code> is equal or a super class of another <code>EClass</code>
   * @param class1_p an EClass
   * @param class2_p an EClass
   * @return <code>true</code> if class1_p is equal to class_2 or is a super class of class_2, <code>false</code> otherwise.
   */
  public static boolean isEqualOrSuperClass(EClass class1_p, EClass class2_p) {
    return (null != class1_p) && (null != class2_p) && (class1_p.equals(class2_p) || class2_p.getEAllSuperTypes().contains(class1_p));
  }

  /**
   * @param elt_p
   * @param cls_p
   * @return
   */
  public static boolean isOrIsContainedBy(EObject elt_p, EClass cls_p) {
    boolean result = true;

    result = cls_p.isSuperTypeOf(elt_p.eClass());
    if (!result) {
      result |= isContainedBy(elt_p, cls_p);
    }

    return result;
  }

  /**
   * @param elt_p
   * @param container_p
   * @return
   */
  public static boolean isOrIsContainedBy(EObject elt_p, EObject container_p) {
    boolean result = true;

    result = elt_p == container_p;
    if (!result) {
      result |= isContainedBy(elt_p, container_p);
    }

    return result;
  }

  /**
   * Is given file resource in read only ?
   * @param resource_p
   * @return <code>false</code> if given resource is not file-based; otherwise it depends on the underlying file.
   */
  public static boolean isReadOnly(Resource resource_p) {
    // Precondition.
    if (null == resource_p) {
      return false;
    }
    IFile file = getFile(resource_p);
    return (null != file) ? file.isReadOnly() : false;
  }

  /**
   * Removes the element's container.
   * @param elt_p
   */
  public static void removeContainer(EObject elt_p) {
    EObject container = elt_p.eContainer();
    EStructuralFeature eStructFeatureFound = elt_p.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        container.eSet(eStructFeatureFound, null);
      } else {
        ((Collection<?>) container.eGet(eStructFeatureFound)).remove(elt_p);
      }
    }
  }
}
