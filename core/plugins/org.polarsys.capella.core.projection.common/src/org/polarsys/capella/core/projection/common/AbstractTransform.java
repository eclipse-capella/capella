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
package org.polarsys.capella.core.projection.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoEngine;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.TransfoException;

/**
 * Default abstract template transformation.
 */
public abstract class AbstractTransform implements ITransform {
  /**
   * Transformation tag indicating whether the transformation should remain shallow {@link Boolean#TRUE} or go deep {@link Boolean#FALSE}.
   */
  public static final String START_ELEMENT_TRANSFORMATION = "startElementTransormation"; //$NON-NLS-1$

  /**
   * Whole transformation context.
   */
  protected List<EObject> _context;

  /**
   * Constructor.<br>
   * Initializes the context structure.
   */
  public AbstractTransform() {
    _context = new ArrayList<EObject>(0);
  }

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#execute()
   */
  public void execute() {
    try {

      ITransfoRuleBase ruleBase = createTransfoRuleBase();
      ITransfoEngine engine = createTransfoEngine();
      ITransfo transfo = createTransfo(ruleBase);
      for (EObject contextElement : _context) {
        if (retainContextElement(contextElement, transfo)) {
          doExecuteTransformation(contextElement, engine, transfo);
        }
      }
    } catch (OperationCanceledException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

  }

  /**
   * Should specified context element be retained by the transformation ?
   * @param contextElement
   * @param transfo
   * @return <code>true</code> if so, a transformation is then triggered, <code>false</code> otherwise (element is skipped as far as the transformation is
   *         concerned).
   */
  protected abstract boolean retainContextElement(EObject contextElement, ITransfo transfo);

  /**
   * Apply transformation to specified context element.
   * @param modelElement_p
   * @param engine_p
   * @param transfo_p
   * @throws TransfoException
   */
  protected void doExecuteTransformation(EObject modelElement_p, ITransfoEngine engine_p, ITransfo transfo_p) throws TransfoException {
    try {
      // engine_p.inihtialize(transfo_p);
      engine_p.execute(transfo_p);
    } finally {
      HoldingResourceHelper.flushHoldingResource(TransactionUtil.getEditingDomain(modelElement_p));
    } 
  }

  /**
   * Create transformation.
   * @param ruleBase_p
   * @return A not <code>null</code> rule {@link ITransfo}.
   */
  protected abstract ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws Exception;

  /**
   * Create transformation engine.
   * @return A not <code>null</code> rule {@link ITransfoEngine}.
   */
  protected ITransfoEngine createTransfoEngine() {
    return new ProjectionEngine();
  }

  /**
   * Create transformation rule base.
   * @return A not <code>null</code> rule {@link ITransfoRuleBase}.
   */
  protected ITransfoRuleBase createTransfoRuleBase() {
    return new ProjectionRuleBase();
  }
}
