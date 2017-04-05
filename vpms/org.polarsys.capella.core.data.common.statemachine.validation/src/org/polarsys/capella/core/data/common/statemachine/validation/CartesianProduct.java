/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

/**
 * This Class provides a tools to compute the Cartesian product of an {@link EList} of {@link EList}s that contain elements of the type T provided in parameter.
 * @param <T> the type of the {@link EList}s elements .
 */
public class CartesianProduct<T> {

  private EList<EList<T>> sets = new BasicEList<EList<T>>();

  public CartesianProduct(EList<EList<T>> sets) {
    this.sets = sets;
  }

  /**
   * Compute the Cartesian product of an {@link EList} of {@link EList}s that contain elements of the type T provided in parameter.
   * @return The Cartesian product
   */
  public EList<EList<T>> compute() {
    EList<EList<T>> res = new BasicEList<EList<T>>();

    for (EList<T> set : sets) {
      if (res.isEmpty()) {
        res = initResult(set);
        continue;
      }
      res = _compute(res, set);
    }
    return res;
  }

  private EList<EList<T>> initResult(EList<T> set) {
    EList<EList<T>> tmpRes = new BasicEList<EList<T>>();
    for (T element : set) {
      EList<T> comb = new BasicEList<T>();
      comb.add(element);
      tmpRes.add(comb);
    }
    return tmpRes;
  }

  /**
   * Compute the Cartesian product of two {@link EList}s.
   * @param list1
   * @param list2
   * @return The Cartesian product
   */
  private EList<EList<T>> _compute(EList<EList<T>> list1, EList<T> list2) {
    EList<EList<T>> tmpRes = new BasicEList<EList<T>>();
    for (EList<T> elementList1 : list1) {
      for (T elementList2 : list2) {
        EList<T> comb = new BasicEList<T>();
        comb.addAll(elementList1);
        comb.add(elementList2);
        tmpRes.add(comb);
      }
    }
    return tmpRes;
  }
}
