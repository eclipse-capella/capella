//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.core.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class createNamesetID {
  protected static String nl;

  public static synchronized createNamesetID create(String lineSeparator) {
    nl = lineSeparator;
    createNamesetID result = new createNamesetID();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "        String newBusinessId = ";
  protected final String TEXT_2 = ".createId();";
  protected final String TEXT_3 = NL + "        ";
  protected final String TEXT_4 = ".setId(newBusinessId);" + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL;

  public createNamesetID() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> genModelList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genClassList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genModelParameter : genModelList) {
      for (Object genClassParameter : genClassList) {

        this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
        this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;

        if (preCondition(ctx)) {
          ctx.setNode(new Node.Container(currentNode, getClass()));
          orchestration(ctx);
        }

      }
    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    method_body(new StringBuffer(), ictx);

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genModel", this.genModel);
      parameterValues.put("genClass", this.genClass);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  protected org.eclipse.emf.codegen.ecore.genmodel.GenModel genModel = null;

  public void set_genModel(org.eclipse.emf.codegen.ecore.genmodel.GenModel object) {
    this.genModel = object;
  }

  protected org.eclipse.emf.codegen.ecore.genmodel.GenClass genClass = null;

  public void set_genClass(org.eclipse.emf.codegen.ecore.genmodel.GenClass object) {
    this.genClass = object;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("genModel", this.genModel);
    parameters.put("genClass", this.genClass);
    return parameters;
  }

  protected void method_body(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    //IN: GenClass genClass
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genModel.getImportedName("org.polarsys.capella.common.lib.IdGenerator"));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_4);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", stringBuffer.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    return true;
  }
}