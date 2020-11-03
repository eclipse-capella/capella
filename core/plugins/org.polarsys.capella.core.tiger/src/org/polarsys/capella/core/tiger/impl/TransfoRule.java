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
package org.polarsys.capella.core.tiger.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 *
 */
public abstract class TransfoRule implements ITransfoRule, Comparable<TransfoRule> {

  public static String __cr = System.getProperty("line.separator"); //$NON-NLS-1$  
  public static String __br = "<br/>"; //$NON-NLS-1$
  private String _longName;
  private String _shortName;

  /**
   * Type of the Source, Target Element and traceability link manipulated by current rule
   */
  private EClass _eSourceType, _eTargetType, _eSpecificLinkKind;

  /**
   * Specifies whether this is an active rule
   */
  private boolean _active = true;

  /**
   * Specifies whether this is an abstract rule
   */
  private boolean _abstract = false;

  /**
   * Specifies whether this is a deprecated rule
   */
  private boolean _deprecated = false;

  /**
   * Specifies whether this is a deprecated rule
   */
  private boolean _needsContext = false;

  protected List<String> _updatedAttributes = new ArrayList<String>();

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  
  public TransfoRule(EClass sourceType, EClass targetType) {
    _eSourceType = sourceType;
    _eTargetType = targetType;

    setShortName(getClass().getSimpleName());
    setLongName(" (" + _eSourceType.getName() + " -> " + _eTargetType.getName() + ")");

    if (ModellingcorePackage.eINSTANCE.getAbstractNamedElement().isSuperTypeOf(targetType)) {
      _updatedAttributes.add(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
      _updatedAttributes.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
      _updatedAttributes.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
    }
  }

  /**
   * Specific constructor for take account the kind of link for couple elements
   */
  public TransfoRule(EClass sourceType, EClass targetType, EClass specificLinkKind) {
    this(sourceType, targetType);
    _eSpecificLinkKind = specificLinkKind;
  }

  /**
   * constructor if source class and target class are the same.
   * @param eclass
   */
  public TransfoRule(EClass eclass) {
    this(eclass, eclass);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getName()
   */
  public String getName() {
    return _shortName + " " + _longName; //$NON-NLS-1$
  }

  public String getDescription() {
    String sourceType = getSourceType().getName();
    String targetType = getTargetType().getName();

    String description = "<b>" + "Transforms a " + sourceType + " into a " + targetType; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    if (sourceType == targetType) {
      description += " (Clone)"; //$NON-NLS-1$
    }

    description += "</b>"; //$NON-NLS-1$

    return description;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#isActive()
   */
  public boolean isActive() {
    return _active && !_abstract && !_deprecated;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setLongName(java.lang.String)
   */
  public void setLongName(String name) {
    _longName = name;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getShortName()
   */
  public String getShortName() {
    return _shortName;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setShortName(java.lang.String)
   */
  public void setShortName(String shortName) {
    _shortName = shortName;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getSourceType()
   */
  public EClass getSourceType() {
    return _eSourceType;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getTargetType()
   */
  public EClass getTargetType() {
    return _eTargetType;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#toString()
   */
  @Override
  public String toString() {
    return _longName;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public boolean when(EObject element, ITransfo transfo) {
    return _eSourceType.isInstance(element);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#retrieveRelatedElements(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final List<EObject> retrieveRelatedElements(EObject element, ITransfo transfo) {

    List<EObject> relatedElements = retrieveRelatedElements_(element, transfo);

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#retrieveRelatedElements_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract List<EObject> retrieveRelatedElements_(EObject element, ITransfo transfo);

  /**
   * @see org.polarsys.capella.core.tiger.ITransfoRule#requireTransformation(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  public boolean requireTransformation(EObject element, ITransfo transfo) {
    Object transformedElement = Query.retrieveTransformedElement(element, transfo);
   
    return ((transformedElement == null) || ((transformedElement instanceof List<?>) && (((List<?>) transformedElement).size() == 0)) || ((transformedElement instanceof EObject) && !EcoreUtil2
        .isEqualOrSuperClass(getTargetType(), ((EObject) transformedElement).eClass())));

  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#transform(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final Object transform(EObject element, ITransfo transfo) {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Transform " + EObjectExt.getText(element) + " by " + getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return transform_(element, transfo);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract Object transform_(EObject element, ITransfo transfo);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#update(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final void update(EObject element, ITransfo transfo) {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Update " + EObjectExt.getText(element) + " by " + getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    update_(element, transfo);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#update_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public void update_(EObject element, ITransfo transfo) {
    if (element instanceof AbstractNamedElement) {

      ///////////////////////////////////////////////
      // B y   I n t r o s p e c t i o n
      TigerRelationshipHelper.updateElementByProperty(element, _updatedAttributes, transfo);

      /*
      ///////////////////////////////////////////////
      // E x p l i c i t e
      NamedElement sourceElement = (NamedElement) element;
      NamedElement targetElement = (NamedElement) Query.retrieveTransformedElement(sourceElement, transfo);
      targetElement.setName(sourceElement.getName() + " (*)");
      */

      ///////////////////////////////////////////////
      // D e b u g:
      // ----------
      // This code is useful to mark elements with
      // the number of the rules which have created them.
      ///////////////////////////////////////////////
      //       NamedElement sourceElement = (NamedElement) element;
      //       NamedElement targetElement 
      //         = (NamedElement) Query.retrieveTransformedElement(sourceElement, transfo);
      //       if(targetElement!=null) {
      //        targetElement.setName(targetElement.getName() + " (Rule " + getName() + ")");      
      //       }
      ///////////////////////////////////////////////
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#attach(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final void attach(EObject element, ITransfo transfo) throws TransfoException {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Attach " + EObjectExt.getText(element) + " by " + getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    attach_(element, transfo);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract void attach_(EObject element, ITransfo transfo) throws TransfoException;

  /**
   * @return the abstract
   */
  public boolean isAbstract() {
    return _abstract;
  }

  /**
   * @param isAbstract the abstract to set
   */
  public void setAbstract(boolean isAbstract) {
    _abstract = isAbstract;
  }

  /**
   * @return the deprecated
   */
  public boolean isDeprecated() {
    return _deprecated;
  }

  /**
   * @param deprecated the deprecated to set
   */
  public void setDeprecated(boolean deprecated) {
    _deprecated = deprecated;
  }

  /**
   * @param active the active to set
   */
  public void setActive(boolean active) {
    _active = active;
  }

  /**
   * @return the needsContext
   */
  protected boolean isNeedsContext() {
    return _needsContext;
  }

  /**
   * An now-useless old-stuff ?
   * @param needsContext the needsContext to set
   */
  protected void setNeedsContext(boolean needsContext) {
    _needsContext = needsContext;
  }

  public String toHtml(boolean standalone) {
    StringBuilder htmlDoc = new StringBuilder();
    Class<?> class_ = getClass();
    htmlDoc.append("<td valign='top' id='").append(class_.getSimpleName()).append("'><b>").append(__cr); //$NON-NLS-1$ //$NON-NLS-2$
    htmlDoc.append(getShortName());
    htmlDoc.append("</b></td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    Class<?> superClass = getClass().getSuperclass();
    String superclassName = TransfoRule.class != superClass ? superClass.getSimpleName() : ""; //$NON-NLS-1$
    if (!superclassName.equals("")) { //$NON-NLS-1$
      htmlDoc.append("<a href='#").append(superclassName).append("'>").append(__cr); //$NON-NLS-1$ //$NON-NLS-2$
      htmlDoc.append(superclassName);
      htmlDoc.append("</a>").append(__cr); //$NON-NLS-1$
    } else {
      htmlDoc.append("-"); //$NON-NLS-1$
    }
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(isActive() ? "X" : "-"); //$NON-NLS-1$ //$NON-NLS-2$
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(isNeedsContext() ? "C" : "-"); //$NON-NLS-1$ //$NON-NLS-2$
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(isAbstract() ? "A" : "-"); //$NON-NLS-1$ //$NON-NLS-2$
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(isDeprecated() ? "D" : "-"); //$NON-NLS-1$ //$NON-NLS-2$
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    for (String updatedAttribute : _updatedAttributes) {
      htmlDoc.append(updatedAttribute).append(__br).append(__cr);
    }
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    htmlDoc.append("<td valign='top'>").append(__cr); //$NON-NLS-1$
    htmlDoc.append(getDescription());
    htmlDoc.append("</td>").append(__cr); //$NON-NLS-1$
    return htmlDoc.toString();
  }

  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(TransfoRule arg0) {
    return getName().compareTo(arg0.getName());
  }

  public EClass getSpecificLinkKind() {
    return _eSpecificLinkKind;
  }

  /**
   * Register a new Attribute in the list of attributes to update
   * @param att the EAttribute 
   */
  protected void registerAttributeUpdate(EAttribute att) {
    _updatedAttributes.add(att.getName());

  }
}
