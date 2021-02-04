package a8se2020ws.Hangler_MoserSchwaiger;

import java.lang.reflect.*;
import java.util.HashSet;

/**
 * Reconstructs the source skeleton from a .class file/a class by using Java Reflection
 */

public class CodeReconstruction {

    private final StringBuilder classCode;
    private final StringBuilder code;
    private final HashSet<String> imports;
    private String packageName = "";
    private final String oneTab = "    ";
    private final String twoTabs = "        ";

    /**
     * constructs a {@link CodeReconstruction} object
     */
    public CodeReconstruction() {
        code = new StringBuilder();
        classCode = new StringBuilder();
        imports = new HashSet<>();
    }

    /**
     * reconstructs the Java code of a given class
     * @param c the class to reconstruct
     * @return the reconstructed java code as a string
     */
    public String constructJavaCode(Class<?> c) {
        getPackages(c);
        getClassSignature(c);
        getParameters(c);
        getConstructors(c);
        getMethods(c);

        this.classCode.deleteCharAt(this.classCode.length()-1).append("}");

        getImports();
        this.code.append(this.classCode);
        //System.out.println(this.code.toString());
        return this.code.toString();
    }

    /**
     * gets the packages of a class and adds it to the output code if it exists
     * @param c the class to get the packages of
     */
    private void getPackages(Class<?> c){
        if(c.getPackage().getName().equals(""))
            return;

        this.packageName = c.getPackage().getName();
        this.code.append("package ").append(this.packageName).append(";\n\n");
    }

    /**
     * gets the class signature and adds it to the output code
     * @param c the class to get the signature of
     */
    private void getClassSignature(Class<?> c) {
        getModifier(c.getModifiers());
        this.classCode.append("class ").append(c.getSimpleName());
        getSuperClass(c);
        getInterfaces(c);
        this.classCode.append(" {\n\n");

    }

    /**
     * gets the parameters of the class and adds them to the output code if they exist
     * @param c the class to get the parameters of
     */
    private void getParameters(Class<?> c) {
        Field[] f = c.getDeclaredFields();
        if(f.length > 0){
            for(Field field : f){
                this.classCode.append(oneTab);
                getModifier(field.getModifiers());

                this.classCode.append(field.getType().getSimpleName())
                        .append(" ")
                        .append(field.getName())
                        .append(" = ")
                        .append(getDefaultValue(field.getType().getSimpleName()))
                        .append(";\n");

                addToImport(field.getType().toString());
            }
            this.classCode.append("\n");
        }
    }

    /**
     * gets the constructors of a class and adds them to the output code if they exist
     * @param c the class to get the constructor of
     */
    private void getConstructors(Class<?> c) {
        Constructor<?>[] constructors = c.getConstructors();

        if(constructors.length > 0){
            for(Constructor<?> cons : constructors) {
                this.classCode.append(oneTab);

                getModifier(cons.getModifiers());
                this.classCode.append(c.getSimpleName())
                        .append("(");
                getMethodsParams(cons.getParameters());

                this.classCode.append(")");
                getMethodException(cons.getExceptionTypes());
                this.classCode.append(" {\n")
                        .append(twoTabs)
                        .append("System.out.println(\"constructor\");")
                        .append("\n")
                        .append(oneTab)
                        .append("}\n\n");
            }
        }
    }

    /**
     * gets the methods of a class and adds them to the output code
     * @param c the class to get the methods of
     */
    private void getMethods(Class<?> c) {
        Method[] classMethods = c.getDeclaredMethods();

        if(classMethods.length > 0){
            for(Method m : classMethods) {
                this.classCode.append(oneTab);

                getModifier(m.getModifiers());

                this.classCode.append(m.getReturnType().getSimpleName())
                        .append(" ")
                        .append(m.getName())
                        .append("(");

                getMethodsParams(m.getParameters());
                this.classCode.append(")");
                getMethodException(m.getExceptionTypes());
                this.classCode.append(" {\n");
                getMethodContent(m);

                this.classCode.append(oneTab)
                        .append("}\n\n");

                addToImport(m.getReturnType().toString());
            }
        }
    }

    /**
     * adds the name of the method as System.out.println and
     * adds dummy a return value to a method if needed
     * @param m the method to add content to
     */
    private void getMethodContent(Method m) {
        this.classCode.append(twoTabs)
                .append("System.out.println(\"")
                .append(m.getName())
                .append("\");\n");

        if(!m.getReturnType().getSimpleName().equals("void"))
            this.classCode.append(twoTabs)
                    .append("return ")
                    .append(getDefaultValue(m.getReturnType().getSimpleName()))
                    .append(";\n");
    }

    /**
     * gets a modifiers (public, static...) and adds it to the output code
     * @param m the int value of the modifier
     */
    private void getModifier(int m){
        if(Modifier.toString(m).length() > 0)
            classCode.append(Modifier.toString(m))
                    .append(" ");
    }

    /**
     * gets the parameters of a method and adds them to the output code
     * @param types array of the parameter types of a method
     */
    private void getMethodsParams(Parameter[] types){
        if(types.length > 0){
            for(Parameter t : types) {
                classCode.append(t.getType().getSimpleName())
                        .append(" ")
                        .append(t.getName())
                        .append(", ");

                addToImport(t.getType().toString());
            }
            deleteLastTwo();
        }
    }

    /**
     * gets the method exceptions of a method and adds them to the output code
     * @param e array of the exception types of a method
     */
    private void getMethodException(Class<?>[] e) {
        if(e.length > 0){
            classCode.append(" throws ");
            for(Class<?> c : e) {
                classCode.append(c.getSimpleName())
                        .append(" ,");

                addToImport(c.getName());
            }
            deleteLastTwo();
        }
    }

    /**
     * gets the superClass of a class and adds it to the output code if it exists
     * @param c the class to get the superClass of
     */
    private void getSuperClass(Class<?> c){
        if(c.getSuperclass().getSimpleName().length() > 0) {
            classCode.append(" extends ").append(c.getSuperclass().getSimpleName());

            addToImport(c.getSuperclass().getName());
        }
    }

    /**
     * gets the interface/s of a class and adds it/them to the output code if existing
     * @param c the class to get the interface of
     */
    private void getInterfaces(Class<?> c){
        Class<?>[] interfaces = c.getInterfaces();

        if(interfaces.length > 0) {
            classCode.append(" implements ");
            for (Class<?> z : interfaces) {
                classCode.append(z.getSimpleName()).append(", ");
                addToImport(z.getName());
            }
            deleteLastTwo();
        }
    }

    /**
     * adds import statements to the output code if the given type requires it (e.g. ArrayList)
     * @param type data type as a string
     */
    private void addToImport(String type){
        String[] splitImp;

        //if type is not primitive
        if(type.contains(".")) {
            //if a '$' is in the name
            // (i.e. MethodeHandles.LookUp is the param type -> gets with Java.Reflection MethodeHandles$LookUp)
            type = type.replace('$','.');
            String[] imp = type.split(" ");

            if(type.contains("java.lang")) {
                splitImp = imp[imp.length-1].split("\\.");

                //if it is not directly imported from java.lang --> therefore it needs an import
                if(splitImp.length >= 4 )
                    this.imports.add(imp[imp.length - 1]);

                return;
            }

            if(type.contains(this.packageName)){
                String[] splitPackName = this.packageName.split("\\.");
                splitImp = imp[imp.length-1].split("\\.");

                //if it is not directly imported from the package --> therefore it needs an import
                if(splitImp.length > splitPackName.length+1)
                    this.imports.add(imp[imp.length-1]);

                return;
            }
            //normal import
            this.imports.add(imp[imp.length-1]);
        }
    }

    /**
     * adds the imports to the output code if they exist
     */
    private void getImports(){
        if(this.imports.size() > 0){
            for(String s : this.imports){
                this.code.append("import ")
                        .append(s)
                        .append(";\n");
            }
            this.code.append("\n");
        }
    }

    /**
     * gets the default value for a specific data type and returns it
     * @param returnType the type to get the default value of
     * @return the default value of the type
     */
    private String getDefaultValue(String returnType) {
        if (returnType.equals("byte"))
            return "0";
        if (returnType.equals("short"))
            return "0";
        if (returnType.equals("int"))
            return "0";
        if (returnType.equals("long"))
            return "0";
        if (returnType.equals("char"))
            return "'\u0000'";
        if (returnType.equals("float"))
            return "0.0";
        if (returnType.equals("double"))
            return "0.0";
        if (returnType.equals("boolean"))
            return "false";
        else
            return "null";
    }

    /**
     * deletes the last two characters of the output code
     */
    private void deleteLastTwo(){
        classCode.delete(classCode.length()-2, classCode.length());
    }
}
