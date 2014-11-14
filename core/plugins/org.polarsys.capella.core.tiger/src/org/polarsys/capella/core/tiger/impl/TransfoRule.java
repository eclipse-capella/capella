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
package org.polarsys.capella.core.tiger.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.DebugHelper;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 *
 */
@SuppressWarnings("nls")
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
  
  public TransfoRule(EClass sourceType_p, EClass targetType_p) {
    _eSourceType = sourceType_p;
    _eTargetType = targetType_p;

    setShortName(getClass().getSimpleName());
    setLongName(" (" + _eSourceType.getName() + " -> " + _eTargetType.getName() + ")");

    if (ModellingcorePackage.eINSTANCE.getAbstractNamedElement().isSuperTypeOf(targetType_p)) {
      _updatedAttributes.add(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
      _updatedAttributes.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
      _updatedAttributes.add(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
    }
  }

  /**
   * Specific constructor for take account the kind of link for couple elements
   */
  public TransfoRule(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    this(sourceType_p, targetType_p);
    _eSpecificLinkKind = _eSpecificLinkKind_p;
  }

  /**
   * constructor if source class and target class are the same.
   * @param eclass_p
   */
  public TransfoRule(EClass eclass_p) {
    this(eclass_p, eclass_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getName()
   */
  public String getName() {
    return _shortName + " " + _longName;
  }

  public String getDescription() {
    String sourceType = getSourceType().getName();
    String targetType = getTargetType().getName();

    String description = "<b>" + "Transforms a " + sourceType + " into a " + targetType;

    if (sourceType == targetType) {
      description += " (Clone)";
    }

    description += "</b>";

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
  public void setLongName(String name_p) {
    _longName = name_p;
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
  public void setShortName(String shortName_p) {
    _shortName = shortName_p;
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
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return _eSourceType.isInstance(element_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#retrieveRelatedElements(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final List<EObject> retrieveRelatedElements(EObject element_p, ITransfo transfo_p) {

    List<EObject> relatedElements = retrieveRelatedElements_(element_p, transfo_p);

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#retrieveRelatedElements_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p);

  /**
   * @see org.polarsys.capella.core.tiger.ITransfoRule#requireTransformation(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  public boolean requireTransformation(EObject element_p, ITransfo transfo_p) {
    Object transformedElement = Query.retrieveTransformedElement(element_p, transfo_p);

    return ((transformedElement == null) || ((transformedElement instanceof List<?>) && (((List<?>) transformedElement).size() == 0)) || ((transformedElement instanceof EObject) && !EcoreUtil2
        .isEqualOrSuperClass(getTargetType(), ((EObject) transformedElement).eClass())));

  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#transform(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final Object transform(EObject element_p, ITransfo transfo_p) {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Transform " + DebugHelper.elementToString(element_p) + " by " + getName());
    }
    return transform_(element_p, transfo_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract Object transform_(EObject element_p, ITransfo transfo_p);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#update(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final void update(EObject element_p, ITransfo transfo_p) {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Update " + DebugHelper.elementToString(element_p) + " by " + getName());
    }
    update_(element_p, transfo_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#update_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public void update_(EObject element_p, ITransfo transfo_p) {
    if (element_p instanceof AbstractNamedElement) {

      ///////////////////////////////////////////////
      // B y   I n t r o s p e c t i o n
      TigerRelationshipHelper.updateElementByProperty(element_p, _updatedAttributes, transfo_p);

      /*
      ///////////////////////////////////////////////
      // E x p l i c i t e
      NamedElement sourceElement = (NamedElement) element_p;
      NamedElement targetElement = (NamedElement) Query.retrieveTransformedElement(sourceElement, transfo_p);
      targetElement.setName(sourceElement.getName() + " (*)");
      */

      ///////////////////////////////////////////////
      // D e b u g:
      // ----------
      // This code is useful to mark elements with
      // the number of the rules which have created them.
      ///////////////////////////////////////////////
      //       NamedElement sourceElement = (NamedElement) element_p;
      //       NamedElement targetElement 
      //         = (NamedElement) Query.retrieveTransformedElement(sourceElement, transfo_p);
      //       if(targetElement!=null) {
      //        targetElement.setName(targetElement.getName() + " (Rule " + getName() + ")");      
      //       }
      ///////////////////////////////////////////////
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#attach(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public final void attach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    if (_logger.isDebugEnabled()){
      _logger.debug("     - Attach " + DebugHelper.elementToString(element_p) + " by " + getName());
    }
    attach_(element_p, transfo_p);
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public abstract void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException;

  /**
   * @return the abstract
   */
  public boolean isAbstract() {
    return _abstract;
  }

  /**
   * @param abstract_p the abstract to set
   */
  public void setAbstract(boolean abstract_p) {
    _abstract = abstract_p;
  }

  /**
   * @return the deprecated
   */
  public boolean isDeprecated() {
    return _deprecated;
  }

  /**
   * @param deprecated_p the deprecated to set
   */
  public void setDeprecated(boolean deprecated_p) {
    _deprecated = deprecated_p;
  }

  /**
   * @param active_p the active to set
   */
  public void setActive(boolean active_p) {
    _active = active_p;
  }

  /**
   * @return the needsContext
   */
  protected boolean isNeedsContext() {
    return _needsContext;
  }

  /**
   * An now-useless old-stuff ?
   * @param needsContext_p the needsContext to set
   */
  protected void setNeedsContext(boolean needsContext_p) {
    _needsContext = needsContext_p;
  }

  public String toHtml(boolean standalone_p) {
    StringBuilder htmlDoc = new StringBuilder();
    Class<?> class_ = getClass();
    htmlDoc.append("<td valign='top' id='" + class_.getSimpleName() + "'><b>" + __cr);
    htmlDoc.append(getShortName());
    htmlDoc.append("</b></td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    Class<?> superClass = getClass().getSuperclass();
    String superclassName = TransfoRule.class != superClass ? superClass.getSimpleName() : "";
    if (!superclassName.equals("")) {
      htmlDoc.append("<a href='#" + superclassName + "'>" + __cr);
      htmlDoc.append(superclassName);
      htmlDoc.append("</a>" + __cr);
    } else {
      htmlDoc.append("-");
    }
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    htmlDoc.append(isActive() ? "X" : "-");
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    htmlDoc.append(isNeedsContext() ? "C" : "-");
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    htmlDoc.append(isAbstract() ? "A" : "-");
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    htmlDoc.append(isDeprecated() ? "D" : "-");
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    for (String updatedAttribute : _updatedAttributes) {
      htmlDoc.append(updatedAttribute + __br + __cr);
    }
    htmlDoc.append("</td>" + __cr);
    htmlDoc.append("<td valign='top'>" + __cr);
    htmlDoc.append(getDescription());
    htmlDoc.append("</td>" + __cr);
    return htmlDoc.toString();
  }

  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(TransfoRule arg0_p) {
    return getName().compareTo(arg0_p.getName());
  }

  public EClass getSpecificLinkKind() {
    return _eSpecificLinkKind;
  }

  /**
   * Register a new Attribute in the list of attributes to update
   * @param att_p the EAttribute 
   */
  protected void registerAttributeUpdate(EAttribute att_p) {
    _updatedAttributes.add(att_p.getName());

  }

}
