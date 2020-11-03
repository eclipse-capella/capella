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
package org.polarsys.capella.common.helpers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;

/**
 * This class contains convenient static methods for working with EMF objects.
 */
public class EcoreUtil2 {
  public static String defaultPattern = "{1} {0}"; //$NON-NLS-1$

  /**
   * Attach 'tgtElt' to the same container as 'srcElt', with the same containment feature.
   * @param srcElt
   * @param tgtElt
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void attachLikeSourceContainer(EObject srcElt, EObject tgtElt) {
    EObject container = srcElt.eContainer();
    EStructuralFeature eStructFeatureFound = srcElt.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        container.eSet(eStructFeatureFound, tgtElt);
      } else {
        ((Collection) container.eGet(eStructFeatureFound)).add(tgtElt);
      }
    }
  }

  /**
   * Attach 'tgtElt' to its container 'tgtContainer' with the same containment feature as the one between 'srcElt' and its own container.
   * @param srcElt
   * @param tgtElt
   * @param tgtContainer
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void attachLikeSourceContainmentFeature(EObject srcElt, EObject tgtElt, EObject tgtContainer) {
    EStructuralFeature eStructFeatureFound = srcElt.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        tgtContainer.eSet(eStructFeatureFound, tgtElt);
      } else {
        ((Collection) tgtContainer.eGet(eStructFeatureFound)).add(tgtElt);
      }
    }
  }

  /**
   * @param current
   * @param nameString
   * @param feature
   * @param attribute
   */
  private static boolean checkElementName(EObject current, String nameString, EStructuralFeature feature, EAttribute attribute) {
    Object name = current.eGet(attribute);
    if ((name != null) && name.equals(nameString)) {
      return false;
    }
    return true;
  }

  /**
   * @param list
   * @param nameString
   * @param feature
   * @param attribute
   */
  private static boolean checkListName(Collection<? extends EObject> list, String nameString, EStructuralFeature feature, EAttribute attribute) {
    Iterator<? extends EObject> elements = list.iterator();
    boolean isUnique = true;
    while (elements.hasNext() && isUnique) {
      EObject currentEObject = elements.next();
      isUnique = checkElementName(currentEObject, nameString, feature, attribute);
    }
    return isUnique;
  }

  /**
   * Get all contents for given elements.<br>
   * Returned collection contains given elements and their subtree elements.
   * @param elements
   * @return a not <code>null</code> collection.
   */
  public static Collection<?> getAllContents(Collection<?> elements) {
    Set<Object> allElements = new HashSet<>(elements);
    TreeIterator<EObject> eAllContents = org.eclipse.emf.ecore.util.EcoreUtil.getAllContents(elements, true);
    while (eAllContents.hasNext()) {
      allElements.add(eAllContents.next());
    }
    return allElements;
  }

  public static EObject getCommonAncestor(Collection<? extends EObject> list) {
    if (list.isEmpty()) {
      return null;
    }
    Iterator<? extends EObject> listIterator = list.iterator();
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
   * @param elt
   * @return
   */
  public static int getContainmentLevel(EObject elt, EClass cls) {
    int level = 0;

    if (elt != null) {
      if (cls.isSuperTypeOf(elt.eClass())) {
        level = 0;
      } else {
        EObject container = elt.eContainer();
        if (container != null) {
          level = 1 + getContainmentLevel(container, cls);
        }
      }
    }

    return level;
  }

  /**
   * Return the file where is persisted given EMF object.
   * @param resource
   * @return <code>null</code> if given object is not persisted.
   */
  public static IFile getFile(Resource resource) {
    IFile result = null;
    // Precondition.
    if (null == resource) {
      return result;
    }
    result = WorkspaceSynchronizer.getFile(resource);
    return result;
  }

  /**
   * Returns the URI from the path (with compatible encoding for EMF.getResource(URI) method)
   */
  public static URI getURI(IFile file) {
    return URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
  }

  /**
   * Return the project where is persisted given EMF object.
   * @param eObject
   * @return <code>null</code> if given object is not persisted.
   */
  public static IProject getProject(EObject eObject) {
    IProject result = null;
    Resource resource;
    // Preconditions.
    if ((null == eObject) || ((resource = eObject.eResource()) == null)) {
      return result;
    }
    IFile res = WorkspaceSynchronizer.getFile(resource);
    if (res != null) {
      result = res.getProject();
    }
    return result;
  }

  /**
   * Gets the first container with the specified class type of the specified element.
   * @param elt The element to check container.
   * @param cls The expected container class.
   * @return The corresponding container elsewhere <code>null</code>.
   */
  public static EObject getFirstContainer(EObject elt, EClass cls) {
    EObject container = null;

    if (elt != null) {
      container = elt.eContainer();
    }

    if (container == null) {
      return null;
    }

    if (cls.isSuperTypeOf(container.eClass())) {
      return container;
    }

    return getFirstContainer(container, cls);
  }

  /**
   * Gets the first container with the specified class type of the specified elements.
   * @param elt The element to check container.
   * @param cls The expected container classes list.
   * @return The corresponding container elsewhere <code>null</code>.
   */
  public static EObject getFirstContainer(EObject elt, List<EClass> cls) {
    EObject container = null;

    if (elt != null) {
      container = elt.eContainer();
    }

    if (container == null) {
      return null;
    }

    for (EClass c : cls) {
      if (c.isSuperTypeOf(container.eClass())) {
        return container;
      }
    }

    return getFirstContainer(container, cls);
  }

  /**
   * 
   * @param eObj
   * @param condition
   * @return the first container that satisfies the given condition
   */
  public static EObject getFirstContainer(EObject eObj, Predicate<EObject> condition) {
    EObject container = null;

    if (eObj != null) {
      container = eObj.eContainer();
    }

    if (container == null) {
      return null;
    }

    if (condition.test(container)) {
      return container;
    }

    return getFirstContainer(container, condition);
  }
  
  /**
   * Collect all elements that reference (containment relationships are not taken into account) given one.<br>
   * In fact that collects Non Navigable Inverse References for given element.<br>
   * Derived features are ignored too.
   * @param referencedElement
   * @param crossReferencer cross referencer used to retrieve referencing elements.
   * @return a not <code>null</code> collection.
   */
  public static Collection<EObject> getReferencingElements(final EObject referencedElement, ECrossReferenceAdapter crossReferencer) {
    HashSet<EObject> referencingElements = new HashSet<>(0);
    // Search inverses relations for selected model element.
    Collection<Setting> inverseReferences = crossReferencer.getNonNavigableInverseReferences(referencedElement, true);
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
   * @param eObject
   * @return should be not <code>null</code>.
   */
  public static EObject getResourceContainer(EObject eObject) {
    EObject result = eObject;
    if (null != eObject) {
      EObject parentContainer = eObject.eContainer();
      if (null == parentContainer) {
        result = eObject;
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
   * @param resource
   * @return
   */
  public static URI getResourceURI(Resource resource) {
    ResourceSet resourceSet = resource.getResourceSet();
    URI uri = resource.getURI();
    if (null != resourceSet) {
      uri = resourceSet.getURIConverter().normalize(uri);
    }
    return uri;
  }

  /**
   * @param namedElement
   * @param attribute
   * @param prefix
   * @param space
   * @param recursive
   */
  @SuppressWarnings("unchecked")
  public static String getUniqueName(EObject namedElement, EAttribute attribute, String prefix, boolean space, boolean recursive) {
    EObject parent = namedElement.eContainer();
    StringBuilder name = new StringBuilder(prefix);
    if (space) {
      name.append(" "); //$NON-NLS-1$
    }

    String nameString = name.toString();
    if (parent == null) {
      return nameString;
    }

    EStructuralFeature feature = namedElement.eContainingFeature();
    if (recursive) {
      EStructuralFeature parentFeature = parent.eContainingFeature();
      while (feature == parentFeature) {
        parent = parent.eContainer();
        parentFeature = parent.eContainingFeature();
      }
    }

    if ((feature instanceof EReference) && feature.isMany()) {
      boolean isUnique = false;

      Collection<? extends EObject> list = new ArrayList((Collection<? extends EObject>) parent.eGet(feature));
      list.remove(namedElement);

      int i = list.size() + 1;
      name.append(i);
      while (!isUnique) {
        nameString = name.toString();
        isUnique = checkListName(list, nameString, feature, attribute);
        if (!isUnique) {
          if (nameString.endsWith(Integer.toString(i))) {
            name.delete(name.length() - Integer.toString(i).length(), name.length());
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
   * @param object the object to set name.
   * @param container the object container.
   * @param feature the feature.
   * @param attribute The name attribute.
   * @param defaultString The default string.
   */
  @SuppressWarnings("unchecked")
  public static String getUniqueName(EObject object, EObject container, EStructuralFeature feature, EAttribute attribute, String defaultString) {
    String resultName = ICommonConstants.EMPTY_STRING;

    if ((feature == null) || feature.isMany()) {
      int counter = 0;

      List<EObject> siblings = new ArrayList<>();
      if (feature != null) {
        siblings.addAll((Collection<EObject>) container.eGet(feature));
      } else {
        siblings.addAll(container.eContents());
      }
      siblings.remove(object);

      // retrieving the naming attribute feature.
      if (!siblings.isEmpty()) {
        List<String> existingNames = new ArrayList<>();
        // list existing names.
        if (attribute != null) {
          for (Object sibling : siblings) {
            EObject eSibling = (EObject) sibling;

            //eGet doesn't raise an exception if 'java assert' option is not enabled
            //See https://polarsys.org/bugs/show_bug.cgi?id=389
            if(!eSibling.eClass().getEAllStructuralFeatures().contains(attribute)){
            	continue;
            }

            Object attributeValue = eSibling.eGet(attribute);
            if (attributeValue instanceof String) {
              String name = (String) attributeValue;
              if (!name.equals(ICommonConstants.EMPTY_STRING)) {
                existingNames.add(name);
              }
            }
          }
          counter = siblings.size();
          do {
            resultName = MessageFormat.format(defaultPattern, new Object[] { Integer.valueOf(++counter), defaultString });
          } while (existingNames.contains(resultName));
        }
      } else {
        resultName = MessageFormat.format(defaultPattern, new Object[] { Integer.valueOf(++counter), defaultString });
      }
    } else {
      resultName = defaultString;
    }
    return resultName;
  }

  /**
   * Is given object contained by an object which is an instance of specified container class.
   * @param eObject the element to check the container.
   * @param containerClass The expected container class.
   * @return <code>true</code> if the container class matches, <code>false</code> otherwise.
   */
  public static boolean isContainedBy(EObject eObject, final Class<?> containerClass) {
    // Precondition.
    if (null == containerClass) {
      return false;
    }
    return isContainnedBy(eObject, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void run() {
        EObject container = getObject();
        setResult(Boolean.valueOf(containerClass.isInstance(container)));
      }
    });
  }

  /**
   * Checks if the specified element container class is same as specified class.
   * @param elt the element to check the container.
   * @param cls The expected container class.
   * @return <code>True</code> if the container class is good else <code>false</code>.
   */
  public static boolean isContainedBy(EObject elt, final EClass cls) {
    // Precondition.
    if (null == cls) {
      return false;
    }
    return isContainnedBy(elt, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void run() {
        EObject container = getObject();
        setResult(Boolean.valueOf(cls.isSuperTypeOf(container.eClass())));
      }
    });
  }

  /**
   * Checks if the specified element is contained by the specified container.
   * @param elt the element to check.
   * @param container The container to check.
   * @return <code>True</code> if the container class is good else <code>false</code>.
   */
  public static boolean isContainedBy(EObject elt, final EObject container) {
    // Precondition.
    if (null == container) {
      return false;
    }
    return isContainnedBy(elt, new RunnableWithBooleanResult() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void run() {
        EObject owner = getObject();
        setResult(Boolean.valueOf(container == owner));
      }
    });
  }

  /**
   * Is specified object contained by a container matching specified condition.
   * @param eObject
   * @param containmentCondition
   * @return
   */
  private static boolean isContainnedBy(EObject eObject, RunnableWithBooleanResult containmentCondition) {
    boolean result = false;
    // Preconditions:
    if ((null == eObject) || (null == containmentCondition)) {
      return result;
    }
    // Get object's container.
    EObject container = eObject.eContainer();
    if (null == container) {
      return result;
    }
    // Set the container.
    containmentCondition.setObject(container);
    // Execute the runnable.
    containmentCondition.run();
    if (containmentCondition.getResult().booleanValue()) {
      result = true;
    } else {
      result = isContainnedBy(container, containmentCondition);
    }
    return result;
  }

  /**
   * Allows to know if an <code>EClass</code> is equal or a super class of another <code>EClass</code>
   * @param class1 an EClass
   * @param class2 an EClass
   * @return <code>true</code> if class1 is equal to class_2 or is a super class of class_2, <code>false</code> otherwise.
   */
  public static boolean isEqualOrSuperClass(EClass class1, EClass class2) {
    return (null != class1) && (null != class2) && (class1.equals(class2) || class2.getEAllSuperTypes().contains(class1));
  }

  /**
   * @param elt
   * @param cls
   * @return
   */
  public static boolean isOrIsContainedBy(EObject elt, EClass cls) {
    boolean result = true;

    result = cls.isSuperTypeOf(elt.eClass());
    if (!result) {
      result |= isContainedBy(elt, cls);
    }

    return result;
  }

  /**
   * @param elt
   * @param container
   * @return
   */
  public static boolean isOrIsContainedBy(EObject elt, EObject container) {
    boolean result = true;

    result = elt == container;
    if (!result) {
      result |= isContainedBy(elt, container);
    }

    return result;
  }

  /**
   * Is given file resource in read only ?
   * @param resource
   * @return <code>false</code> if given resource is not file-based; otherwise it depends on the underlying file.
   */
  public static boolean isReadOnly(Resource resource) {
    // Precondition.
    if (null == resource) {
      return false;
    }
    IFile file = getFile(resource);
    return (null != file) ? file.isReadOnly() : Boolean.FALSE;
  }

  /**
   * Removes the element's container.
   * @param elt
   */
  public static void removeContainer(EObject elt) {
    EObject container = elt.eContainer();
    EStructuralFeature eStructFeatureFound = elt.eContainingFeature();
    if (eStructFeatureFound != null) {
      if (!eStructFeatureFound.isMany()) {
        container.eSet(eStructFeatureFound, null);
      } else {
        ((Collection<?>) container.eGet(eStructFeatureFound)).remove(elt);
      }
    }
  }

  /**
   * If all objects in elements have the same editing domain return that domain, otherwise return null.
   * @param elements
   * @return the common EditingDomain for elements, or null if elements have different editing domains
   */
  public static TransactionalEditingDomain getEditingDomain(Collection<EObject> elements) {
    TransactionalEditingDomain commonDomain = null;
    for (EObject e : elements) {
      TransactionalEditingDomain eDomain = TransactionUtil.getEditingDomain(e);
      if (eDomain == null) {
        return null;
      } else {
        if (commonDomain == null) {
          commonDomain = eDomain;
        } else if (commonDomain != eDomain) {
          return null;
        }
      }
    }
    return commonDomain;
  }
  
  /**
   * Make all objects referencing the source object to reference the target object instead
   * 
   * @param srcObject
   *          the source object
   * @param targetObj
   *          the target object
   * @param isContainmentIncluded
   *          whether containment features are involved.
   * @param isProxyResolved
   *          should proxy references be resolved?
   * 
   */
  public static void replaceReferencingFeatures(EObject srcObj, EObject targetObj, boolean isContainmentIncluded,
      boolean isProxyResolved) {
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(srcObj);
    if (domain != null) {
      ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(srcObj);
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(srcObj, isProxyResolved);
      for (Setting setting : inverseReferences) {
        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
        if (eStructuralFeature instanceof EReference && !isContainmentIncluded
            && ((EReference) eStructuralFeature).isContainment()) {
          continue;
        }
        if (!eStructuralFeature.isMany()) {
          setting.getEObject().eSet(eStructuralFeature, targetObj);
        }
        else {
          EList list = ((EList) setting.getEObject().eGet(eStructuralFeature));
          if (list.contains(srcObj)) {
            list.remove(srcObj);
            list.add(targetObj);
          }
        }
      }
    }
  }
  
  /**
   * Make all objects referencing the source object to reference the target object instead
   * 
   * @param srcObject
   *          the source object
   * @param targetObj
   *          the target object
   * @param isContainmentIncluded
   *          whether containment features are involved.
   * @param isProxyResolved
   *          should proxy references be resolved?
   * @param excludedFeatures
   *          features to be excluded
   * 
   */
  public static void replaceReferencingFeatures(EObject srcObj, EObject targetObj, boolean isContainmentIncluded,
      boolean isProxyResolved, List<EStructuralFeature> excludedFeatures) {
    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(srcObj);
    if (domain != null) {
      ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(srcObj);
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(srcObj, isProxyResolved);
      for (Setting setting : inverseReferences) {
        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
        if ((eStructuralFeature instanceof EReference && !isContainmentIncluded
            && ((EReference) eStructuralFeature).isContainment()) || (excludedFeatures.contains(eStructuralFeature))) {
          continue;
        }
        if (!eStructuralFeature.isMany()) {
          setting.getEObject().eSet(eStructuralFeature, targetObj);
        } else {
          EList list = ((EList) setting.getEObject().eGet(eStructuralFeature));
          if (list.contains(srcObj)) {
            list.remove(srcObj);
            list.add(targetObj);
          }
        }
      }
    }
  }
}
