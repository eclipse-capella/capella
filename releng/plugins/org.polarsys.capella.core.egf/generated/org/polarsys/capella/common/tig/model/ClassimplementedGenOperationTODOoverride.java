//Generated with EGF 1.6.3.202110291409
package org.polarsys.capella.common.tig.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class ClassimplementedGenOperationTODOoverride extends org.eclipse.egf.emf.pattern.model.call.Class.ClassimplementedGenOperationTODOoverride {
    protected static String nl;

    public static synchronized ClassimplementedGenOperationTODOoverride create(String lineSeparator) {
        nl = lineSeparator;
        ClassimplementedGenOperationTODOoverride result = new ClassimplementedGenOperationTODOoverride();
        nl = null;
        return result;
    }

    public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;

    protected final String TEXT_1 = "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL
            + "\t\tthrow new UnsupportedOperationException();";

    protected final String TEXT_2 = NL + "\t\tif (";

    protected final String TEXT_3 = " == null) {" + NL + "\t\t\t";

    protected final String TEXT_4 = " eOperation = ";

    protected final String TEXT_5 = ".getEOperations().get(";

    protected final String TEXT_6 = ");" + NL + "\t\t\t";

    protected final String TEXT_7 = ".Helper helper = OCL_ENV.createOCLHelper();" + NL + "\t\t\thelper.setOperationContext(";

    protected final String TEXT_8 = ", eOperation);" + NL + "\t\t\t";

    protected final String TEXT_9 = " ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);" + NL + "\t\t\tString body = ocl.getDetails().get(\"body\");";

    protected final String TEXT_10 = NL + "\t\t\t" + NL + "\t\t\ttry {" + NL + "\t\t\t\t";

    protected final String TEXT_11 = " = helper.createQuery(body);" + NL + "\t\t\t} catch (";

    protected final String TEXT_12 = " e) {" + NL + "\t\t\t\tthrow new UnsupportedOperationException(e.getLocalizedMessage());" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t";

    protected final String TEXT_13 = "<";

    protected final String TEXT_14 = ", ?, ?> query = OCL_ENV.createQuery(";

    protected final String TEXT_15 = ");" + NL + "\t";

    protected final String TEXT_16 = " " + NL + "\t\t";

    protected final String TEXT_17 = "<?, ?, ?, ?, ?> evalEnv = query.getEvaluationEnvironment();" + NL + "\t\t";

    protected final String TEXT_18 = NL + "\t\tevalEnv.add(\"";

    protected final String TEXT_19 = "\", ";

    protected final String TEXT_20 = ");";

    protected final String TEXT_21 = NL + "\t  ";

    protected final String TEXT_22 = NL + "\t\t@SuppressWarnings(\"unchecked\")" + NL + "\t\t";

    protected final String TEXT_23 = "> result = (";

    protected final String TEXT_24 = ">) query.evaluate(this);" + NL + "\t\treturn new ";

    protected final String TEXT_25 = ".UnmodifiableEList<";

    protected final String TEXT_26 = ">(result.size(), result.toArray());" + NL + "\t";

    protected final String TEXT_27 = NL + "\t\treturn ((";

    protected final String TEXT_28 = ") query.evaluate(this)).";

    protected final String TEXT_29 = "();" + NL + "\t";

    protected final String TEXT_30 = NL + "\t\treturn (";

    protected final String TEXT_31 = ") query.evaluate(this);" + NL + "\t";

    protected final String TEXT_32 = NL;

    public ClassimplementedGenOperationTODOoverride() {
        //Here is the constructor
        StringBuffer stringBuffer = new StringBuffer();

        // add initialisation of the pattern variables (declaration has been already done).
        oclNsURI = org.polarsys.capella.common.model.helpers.IModelConstants.OCL_ANNOTATION_SOURCE;

    }

    public String generate(Object argument) throws Exception {
        final StringBuffer stringBuffer = new StringBuffer();

        InternalPatternContext ctx = (InternalPatternContext) argument;
        Map<String, String> queryCtx = null;
        IQuery.ParameterDescription paramDesc = null;
        Node.Container currentNode = ctx.getNode();

        List<Object> genOperationList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genClassList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genPackageList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> genModelList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> isInterfaceList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> isImplementationList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> publicStaticFinalFlagList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> singleWildcardList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> negativeOffsetCorrectionList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)
        List<Object> positiveOffsetCorrectionList = null;
        //this pattern can only be called by another (i.e. it's not an entry point in execution)

        for (Object genOperationParameter : genOperationList) {
            for (Object genClassParameter : genClassList) {
                for (Object genPackageParameter : genPackageList) {
                    for (Object genModelParameter : genModelList) {
                        for (Object isInterfaceParameter : isInterfaceList) {
                            for (Object isImplementationParameter : isImplementationList) {
                                for (Object publicStaticFinalFlagParameter : publicStaticFinalFlagList) {
                                    for (Object singleWildcardParameter : singleWildcardList) {
                                        for (Object negativeOffsetCorrectionParameter : negativeOffsetCorrectionList) {
                                            for (Object positiveOffsetCorrectionParameter : positiveOffsetCorrectionList) {

                                                this.genOperation = (org.eclipse.emf.codegen.ecore.genmodel.GenOperation) genOperationParameter;
                                                this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
                                                this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
                                                this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
                                                this.isInterface = (java.lang.Boolean) isInterfaceParameter;
                                                this.isImplementation = (java.lang.Boolean) isImplementationParameter;
                                                this.publicStaticFinalFlag = (java.lang.String) publicStaticFinalFlagParameter;
                                                this.singleWildcard = (java.lang.String) singleWildcardParameter;
                                                this.negativeOffsetCorrection = (java.lang.String) negativeOffsetCorrectionParameter;
                                                this.positiveOffsetCorrection = (java.lang.String) positiveOffsetCorrectionParameter;

                                                if (preCondition(ctx)) {
                                                    ctx.setNode(new Node.Container(currentNode, getClass()));
                                                    orchestration(ctx);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ctx.setNode(currentNode);
        if (ctx.useReporter()) {
            ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
        }

        stringBuffer.append(TEXT_32);
        stringBuffer.append(TEXT_32);
        return stringBuffer.toString();
    }

    public String orchestration(PatternContext ctx) throws Exception {
        InternalPatternContext ictx = (InternalPatternContext) ctx;

        super.orchestration(new SuperOrchestrationContext(ictx));

        if (ictx.useReporter()) {
            Map<String, Object> parameterValues = new HashMap<String, Object>();
            parameterValues.put("genOperation", this.genOperation);
            parameterValues.put("genClass", this.genClass);
            parameterValues.put("genPackage", this.genPackage);
            parameterValues.put("genModel", this.genModel);
            parameterValues.put("isInterface", this.isInterface);
            parameterValues.put("isImplementation", this.isImplementation);
            parameterValues.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
            parameterValues.put("singleWildcard", this.singleWildcard);
            parameterValues.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
            parameterValues.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
            String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
            String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
            ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
        }
        return null;
    }

    protected java.lang.String oclNsURI = null;

    public void set_oclNsURI(java.lang.String object) {
        this.oclNsURI = object;
    }

    public Map<String, Object> getParameters() {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("genOperation", this.genOperation);
        parameters.put("genClass", this.genClass);
        parameters.put("genPackage", this.genPackage);
        parameters.put("genModel", this.genModel);
        parameters.put("isInterface", this.isInterface);
        parameters.put("isImplementation", this.isImplementation);
        parameters.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
        parameters.put("singleWildcard", this.singleWildcard);
        parameters.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
        parameters.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
        return parameters;
    }

    protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

        String body = null;
        EOperation eOperation = genOperation.getEcoreOperation();
        EAnnotation ocl = eOperation.getEAnnotation(oclNsURI);
        if (ocl != null)
            body = ocl.getDetails().get("body");
        if (body == null) {
            stringBuffer.append(TEXT_1);
        } else {
            final String expr = genOperation.getName() + "BodyOCL";
            stringBuffer.append(TEXT_2);
            stringBuffer.append(expr);
            stringBuffer.append(TEXT_3);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
            stringBuffer.append(TEXT_4);
            stringBuffer.append(genOperation.getGenClass().getQualifiedClassifierAccessor());
            stringBuffer.append(TEXT_5);
            stringBuffer.append(genOperation.getGenClass().getGenOperations().indexOf(genOperation));
            stringBuffer.append(TEXT_6);
            stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
            stringBuffer.append(TEXT_7);
            stringBuffer.append(genOperation.getGenClass().getQualifiedClassifierAccessor());
            stringBuffer.append(TEXT_8);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EAnnotation"));
            stringBuffer.append(TEXT_9);
            stringBuffer.append(genModel.getNonNLS());
            stringBuffer.append(TEXT_10);
            stringBuffer.append(expr);
            stringBuffer.append(TEXT_11);
            stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ParserException"));
            stringBuffer.append(TEXT_12);
            stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.Query"));
            stringBuffer.append(TEXT_13);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
            stringBuffer.append(TEXT_14);
            stringBuffer.append(expr);
            stringBuffer.append(TEXT_15);
            if (!genOperation.getEcoreOperation().getEParameters().isEmpty()) {
                stringBuffer.append(TEXT_16);
                stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.EvaluationEnvironment"));
                stringBuffer.append(TEXT_17);
                for (EParameter param : genOperation.getEcoreOperation().getEParameters()) {
                    stringBuffer.append(TEXT_18);
                    stringBuffer.append(param.getName());
                    stringBuffer.append(TEXT_19);
                    stringBuffer.append(param.getName());
                    stringBuffer.append(TEXT_20);
                    stringBuffer.append(genModel.getNonNLS());
                    stringBuffer.append(TEXT_21);
                }
            }

            if (genOperation.isListType()) {
                stringBuffer.append(TEXT_22);
                stringBuffer.append(genModel.getImportedName("java.util.Collection"));
                stringBuffer.append(TEXT_13);
                stringBuffer.append(genOperation.getListItemType());
                stringBuffer.append(TEXT_23);
                stringBuffer.append(genModel.getImportedName("java.util.Collection"));
                stringBuffer.append(TEXT_13);
                stringBuffer.append(genOperation.getListItemType());
                stringBuffer.append(TEXT_24);
                stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
                stringBuffer.append(TEXT_25);
                stringBuffer.append(genOperation.getListItemType());
                stringBuffer.append(TEXT_26);
            } else if (genOperation.isPrimitiveType()) {
                stringBuffer.append(TEXT_27);
                stringBuffer.append(genOperation.getObjectType());
                stringBuffer.append(TEXT_28);
                stringBuffer.append(genOperation.getPrimitiveValueFunction());
                stringBuffer.append(TEXT_29);
            } else {
                stringBuffer.append(TEXT_30);
                stringBuffer.append(genOperation.getImportedType());
                stringBuffer.append(TEXT_31);
            }
        }
        stringBuffer.append(TEXT_32);
        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
    }
}
