//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.common.extension.migration.egf;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.egf.common.helper.*;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.egf.core.EGFCorePlugin;

public class genPackage {

  public genPackage() {
    //Here is the constructor
    // add initialisation of the pattern variables (declaration has been already done).
  }

  public void generate(Object argument) throws Exception {
    InternalPatternContext ctx = (InternalPatternContext) argument;
    IQuery.ParameterDescription paramDesc = null;
    Map<String, String> queryCtx = null;
    Node.Container currentNode = ctx.getNode();
    List<Object> genPackageList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genPackageParameter : genPackageList) {

      this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration((PatternContext) argument);

      }
    }
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    Node.Container currentNode = ictx.getNode();
    method_body(new StringBuffer(), ictx);
    ictx.setNode(currentNode);
    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genPackage", this.genPackage);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  protected void method_body(final StringBuffer out, final PatternContext ctx) throws Exception {
    EPackage ecorePackage = genPackage.getEcorePackage();
    String annotation = null;

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory",
        "trackResourceModification");
    if (annotation != null)
      EcoreUtil.setAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
          "trackResourceModification", annotation);

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory",
        "useUUIDs");
    if (annotation != null)
      EcoreUtil.setAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension", "useUUIDs",
          annotation);

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory",
        "useIDAttributes");
    if (annotation != null)
      EcoreUtil.setAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension", "useIDAttributes",
          annotation);

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory",
        "extensibleProviderFactory");
    if (annotation != null) {
      EcoreUtil.setAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
          "extensibleProviderFactory", annotation);
      genPackage.setExtensibleProviderFactory(Boolean.parseBoolean(annotation));
    }

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory",
        "childCreationExtenders");
    if (annotation != null) {
      EcoreUtil.setAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
          "childCreationExtenders", annotation);
      genPackage.setChildCreationExtenders(Boolean.parseBoolean(annotation));
    }

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
        "childCreationExtenders");
    if (annotation != null) {
      genPackage.setChildCreationExtenders(Boolean.parseBoolean(annotation));
    }

    annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
        "extensibleProviderFactory");
    if (annotation != null) {
      genPackage.setExtensibleProviderFactory(Boolean.parseBoolean(annotation));
    }

    genPackage.eResource().save(Collections.EMPTY_MAP);
    genPackage.getEcorePackage().eResource().save(Collections.EMPTY_MAP);
    //EGFCorePlugin.getDefault().logInfo(annotation);

    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", out.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    return true;
  }

  protected org.eclipse.emf.codegen.ecore.genmodel.GenPackage genPackage;

  public void set_genPackage(org.eclipse.emf.codegen.ecore.genmodel.GenPackage genPackage) {
    this.genPackage = genPackage;
  }

  public Map<String, Object> getParameters() {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("genPackage", this.genPackage);
    return parameters;
  }

}
